package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.b;
import org.a.c;
import org.a.d;

public final class FlowableAmb<T> extends Flowable<T> {
    final b<? extends T>[] sources;
    final Iterable<? extends b<? extends T>> sourcesIterable;

    static final class AmbCoordinator<T> implements d {
        final c<? super T> actual;
        final AmbInnerSubscriber<T>[] subscribers;
        final AtomicInteger winner = new AtomicInteger();

        AmbCoordinator(c<? super T> cVar, int i) {
            this.actual = cVar;
            this.subscribers = new AmbInnerSubscriber[i];
        }

        public void subscribe(b<? extends T>[] bVarArr) {
            int i = 0;
            AmbInnerSubscriber[] ambInnerSubscriberArr = this.subscribers;
            int length = ambInnerSubscriberArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                ambInnerSubscriberArr[i2] = new AmbInnerSubscriber(this, i2 + 1, this.actual);
            }
            this.winner.lazySet(0);
            this.actual.onSubscribe(this);
            while (i < length && this.winner.get() == 0) {
                bVarArr[i].subscribe(ambInnerSubscriberArr[i]);
                i++;
            }
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                int i = this.winner.get();
                if (i > 0) {
                    this.subscribers[i - 1].request(j);
                } else if (i == 0) {
                    for (AmbInnerSubscriber request : this.subscribers) {
                        request.request(j);
                    }
                }
            }
        }

        public boolean win(int i) {
            int i2 = 0;
            if (this.winner.get() != 0 || !this.winner.compareAndSet(0, i)) {
                return false;
            }
            AmbInnerSubscriber[] ambInnerSubscriberArr = this.subscribers;
            int length = ambInnerSubscriberArr.length;
            while (i2 < length) {
                if (i2 + 1 != i) {
                    ambInnerSubscriberArr[i2].cancel();
                }
                i2++;
            }
            return true;
        }

        public void cancel() {
            if (this.winner.get() != -1) {
                this.winner.lazySet(-1);
                for (AmbInnerSubscriber cancel : this.subscribers) {
                    cancel.cancel();
                }
            }
        }
    }

    static final class AmbInnerSubscriber<T> extends AtomicReference<d> implements FlowableSubscriber<T>, d {
        private static final long serialVersionUID = -1185974347409665484L;
        final c<? super T> actual;
        final int index;
        final AtomicLong missedRequested = new AtomicLong();
        final AmbCoordinator<T> parent;
        boolean won;

        AmbInnerSubscriber(AmbCoordinator<T> ambCoordinator, int i, c<? super T> cVar) {
            this.parent = ambCoordinator;
            this.index = i;
            this.actual = cVar;
        }

        public void onSubscribe(d dVar) {
            SubscriptionHelper.deferredSetOnce(this, this.missedRequested, dVar);
        }

        public void request(long j) {
            SubscriptionHelper.deferredRequest(this, this.missedRequested, j);
        }

        public void onNext(T t) {
            if (this.won) {
                this.actual.onNext(t);
            } else if (this.parent.win(this.index)) {
                this.won = true;
                this.actual.onNext(t);
            } else {
                ((d) get()).cancel();
            }
        }

        public void onError(Throwable th) {
            if (this.won) {
                this.actual.onError(th);
            } else if (this.parent.win(this.index)) {
                this.won = true;
                this.actual.onError(th);
            } else {
                ((d) get()).cancel();
                RxJavaPlugins.onError(th);
            }
        }

        public void onComplete() {
            if (this.won) {
                this.actual.onComplete();
            } else if (this.parent.win(this.index)) {
                this.won = true;
                this.actual.onComplete();
            } else {
                ((d) get()).cancel();
            }
        }

        public void cancel() {
            SubscriptionHelper.cancel(this);
        }
    }

    public FlowableAmb(b<? extends T>[] bVarArr, Iterable<? extends b<? extends T>> iterable) {
        this.sources = bVarArr;
        this.sourcesIterable = iterable;
    }

    public void subscribeActual(c<? super T> cVar) {
        b[] bVarArr = this.sources;
        if (bVarArr == null) {
            Object obj = new b[8];
            try {
                int i = 0;
                for (b bVar : this.sourcesIterable) {
                    if (bVar == null) {
                        EmptySubscription.error(new NullPointerException("One of the sources is null"), cVar);
                        return;
                    }
                    if (i == obj.length) {
                        Object obj2 = new b[((i >> 2) + i)];
                        System.arraycopy(obj, 0, obj2, 0, i);
                        obj = obj2;
                    }
                    int i2 = i + 1;
                    obj[i] = bVar;
                    i = i2;
                }
                int i3 = i;
                bVarArr = obj;
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                EmptySubscription.error(th, cVar);
                return;
            }
        }
        i3 = bVarArr.length;
        if (i3 == 0) {
            EmptySubscription.complete(cVar);
        } else if (i3 == 1) {
            bVarArr[0].subscribe(cVar);
        } else {
            new AmbCoordinator(cVar, i3).subscribe(bVarArr);
        }
    }
}
