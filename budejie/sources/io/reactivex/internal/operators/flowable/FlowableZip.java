package io.reactivex.internal.operators.flowable;

import com.facebook.common.time.Clock;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.QueueSubscription;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.b;
import org.a.c;
import org.a.d;

public final class FlowableZip<T, R> extends Flowable<R> {
    final int bufferSize;
    final boolean delayError;
    final b<? extends T>[] sources;
    final Iterable<? extends b<? extends T>> sourcesIterable;
    final Function<? super Object[], ? extends R> zipper;

    static final class ZipCoordinator<T, R> extends AtomicInteger implements d {
        private static final long serialVersionUID = -2434867452883857743L;
        final c<? super R> actual;
        volatile boolean cancelled;
        final Object[] current;
        final boolean delayErrors;
        final AtomicThrowable errors;
        final AtomicLong requested;
        final ZipSubscriber<T, R>[] subscribers;
        final Function<? super Object[], ? extends R> zipper;

        ZipCoordinator(c<? super R> cVar, Function<? super Object[], ? extends R> function, int i, int i2, boolean z) {
            this.actual = cVar;
            this.zipper = function;
            this.delayErrors = z;
            ZipSubscriber[] zipSubscriberArr = new ZipSubscriber[i];
            for (int i3 = 0; i3 < i; i3++) {
                zipSubscriberArr[i3] = new ZipSubscriber(this, i2);
            }
            this.current = new Object[i];
            this.subscribers = zipSubscriberArr;
            this.requested = new AtomicLong();
            this.errors = new AtomicThrowable();
        }

        void subscribe(b<? extends T>[] bVarArr, int i) {
            ZipSubscriber[] zipSubscriberArr = this.subscribers;
            int i2 = 0;
            while (i2 < i && !this.cancelled) {
                if (this.delayErrors || this.errors.get() == null) {
                    bVarArr[i2].subscribe(zipSubscriberArr[i2]);
                    i2++;
                } else {
                    return;
                }
            }
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.add(this.requested, j);
                drain();
            }
        }

        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                cancelAll();
            }
        }

        void error(ZipSubscriber<T, R> zipSubscriber, Throwable th) {
            if (this.errors.addThrowable(th)) {
                zipSubscriber.done = true;
                drain();
                return;
            }
            RxJavaPlugins.onError(th);
        }

        void cancelAll() {
            for (ZipSubscriber cancel : this.subscribers) {
                cancel.cancel();
            }
        }

        void drain() {
            if (getAndIncrement() == 0) {
                c cVar = this.actual;
                ZipSubscriber[] zipSubscriberArr = this.subscribers;
                int length = zipSubscriberArr.length;
                Object obj = this.current;
                int i = 1;
                while (true) {
                    Object obj2;
                    Object obj3;
                    long j = this.requested.get();
                    long j2 = 0;
                    while (j != j2) {
                        if (!this.cancelled) {
                            if (this.delayErrors || this.errors.get() == null) {
                                obj2 = null;
                                for (int i2 = 0; i2 < length; i2++) {
                                    ZipSubscriber zipSubscriber = zipSubscriberArr[i2];
                                    if (obj[i2] == null) {
                                        try {
                                            boolean z = zipSubscriber.done;
                                            SimpleQueue simpleQueue = zipSubscriber.queue;
                                            Object poll = simpleQueue != null ? simpleQueue.poll() : null;
                                            obj3 = poll == null ? 1 : null;
                                            if (z && obj3 != null) {
                                                cancelAll();
                                                if (((Throwable) this.errors.get()) != null) {
                                                    cVar.onError(this.errors.terminate());
                                                    return;
                                                } else {
                                                    cVar.onComplete();
                                                    return;
                                                }
                                            } else if (obj3 == null) {
                                                obj[i2] = poll;
                                            } else {
                                                obj2 = 1;
                                            }
                                        } catch (Throwable th) {
                                            Exceptions.throwIfFatal(th);
                                            this.errors.addThrowable(th);
                                            if (this.delayErrors) {
                                                obj2 = 1;
                                            } else {
                                                cancelAll();
                                                cVar.onError(this.errors.terminate());
                                                return;
                                            }
                                        }
                                    }
                                }
                                if (obj2 != null) {
                                    break;
                                }
                                try {
                                    cVar.onNext(ObjectHelper.requireNonNull(this.zipper.apply(obj.clone()), "The zipper returned a null value"));
                                    long j3 = 1 + j2;
                                    Arrays.fill(obj, null);
                                    j2 = j3;
                                } catch (Throwable th2) {
                                    Exceptions.throwIfFatal(th2);
                                    cancelAll();
                                    this.errors.addThrowable(th2);
                                    cVar.onError(this.errors.terminate());
                                    return;
                                }
                            }
                            cancelAll();
                            cVar.onError(this.errors.terminate());
                            return;
                        }
                        return;
                    }
                    if (j == j2) {
                        if (!this.cancelled) {
                            if (this.delayErrors || this.errors.get() == null) {
                                for (int i3 = 0; i3 < length; i3++) {
                                    ZipSubscriber zipSubscriber2 = zipSubscriberArr[i3];
                                    if (obj[i3] == null) {
                                        try {
                                            boolean z2 = zipSubscriber2.done;
                                            SimpleQueue simpleQueue2 = zipSubscriber2.queue;
                                            obj3 = simpleQueue2 != null ? simpleQueue2.poll() : null;
                                            obj2 = obj3 == null ? 1 : null;
                                            if (z2 && obj2 != null) {
                                                cancelAll();
                                                if (((Throwable) this.errors.get()) != null) {
                                                    cVar.onError(this.errors.terminate());
                                                    return;
                                                } else {
                                                    cVar.onComplete();
                                                    return;
                                                }
                                            } else if (obj2 == null) {
                                                obj[i3] = obj3;
                                            }
                                        } catch (Throwable th22) {
                                            Exceptions.throwIfFatal(th22);
                                            this.errors.addThrowable(th22);
                                            if (!this.delayErrors) {
                                                cancelAll();
                                                cVar.onError(this.errors.terminate());
                                                return;
                                            }
                                        }
                                    }
                                }
                            } else {
                                cancelAll();
                                cVar.onError(this.errors.terminate());
                                return;
                            }
                        }
                        return;
                    }
                    if (j2 != 0) {
                        for (ZipSubscriber request : zipSubscriberArr) {
                            request.request(j2);
                        }
                        if (j != Clock.MAX_TIME) {
                            this.requested.addAndGet(-j2);
                        }
                    }
                    int addAndGet = addAndGet(-i);
                    if (addAndGet != 0) {
                        i = addAndGet;
                    } else {
                        return;
                    }
                }
            }
        }
    }

    static final class ZipSubscriber<T, R> extends AtomicReference<d> implements FlowableSubscriber<T>, d {
        private static final long serialVersionUID = -4627193790118206028L;
        volatile boolean done;
        final int limit;
        final ZipCoordinator<T, R> parent;
        final int prefetch;
        long produced;
        SimpleQueue<T> queue;
        int sourceMode;

        ZipSubscriber(ZipCoordinator<T, R> zipCoordinator, int i) {
            this.parent = zipCoordinator;
            this.prefetch = i;
            this.limit = i - (i >> 2);
        }

        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.setOnce(this, dVar)) {
                if (dVar instanceof QueueSubscription) {
                    QueueSubscription queueSubscription = (QueueSubscription) dVar;
                    int requestFusion = queueSubscription.requestFusion(7);
                    if (requestFusion == 1) {
                        this.sourceMode = requestFusion;
                        this.queue = queueSubscription;
                        this.done = true;
                        this.parent.drain();
                        return;
                    } else if (requestFusion == 2) {
                        this.sourceMode = requestFusion;
                        this.queue = queueSubscription;
                        dVar.request((long) this.prefetch);
                        return;
                    }
                }
                this.queue = new SpscArrayQueue(this.prefetch);
                dVar.request((long) this.prefetch);
            }
        }

        public void onNext(T t) {
            if (this.sourceMode != 2) {
                this.queue.offer(t);
            }
            this.parent.drain();
        }

        public void onError(Throwable th) {
            this.parent.error(this, th);
        }

        public void onComplete() {
            this.done = true;
            this.parent.drain();
        }

        public void cancel() {
            SubscriptionHelper.cancel(this);
        }

        public void request(long j) {
            if (this.sourceMode != 1) {
                long j2 = this.produced + j;
                if (j2 >= ((long) this.limit)) {
                    this.produced = 0;
                    ((d) get()).request(j2);
                    return;
                }
                this.produced = j2;
            }
        }
    }

    public FlowableZip(b<? extends T>[] bVarArr, Iterable<? extends b<? extends T>> iterable, Function<? super Object[], ? extends R> function, int i, boolean z) {
        this.sources = bVarArr;
        this.sourcesIterable = iterable;
        this.zipper = function;
        this.bufferSize = i;
        this.delayError = z;
    }

    public void subscribeActual(c<? super R> cVar) {
        int i;
        b[] bVarArr;
        b[] bVarArr2 = this.sources;
        if (bVarArr2 == null) {
            Object obj = new b[8];
            int i2 = 0;
            Object obj2 = obj;
            for (b bVar : this.sourcesIterable) {
                Object obj3;
                if (i2 == obj2.length) {
                    obj3 = new b[((i2 >> 2) + i2)];
                    System.arraycopy(obj2, 0, obj3, 0, i2);
                } else {
                    obj3 = obj2;
                }
                i = i2 + 1;
                obj3[i2] = bVar;
                i2 = i;
                obj2 = obj3;
            }
            bVarArr = obj2;
            i = i2;
        } else {
            i = bVarArr2.length;
            bVarArr = bVarArr2;
        }
        if (i == 0) {
            EmptySubscription.complete(cVar);
            return;
        }
        obj = new ZipCoordinator(cVar, this.zipper, i, this.bufferSize, this.delayError);
        cVar.onSubscribe(obj);
        obj.subscribe(bVarArr, i);
    }
}
