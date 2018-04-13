package io.reactivex.internal.operators.maybe;

import io.reactivex.Flowable;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.NotificationLite;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.c;
import org.a.d;

public final class MaybeConcatArray<T> extends Flowable<T> {
    final MaybeSource<? extends T>[] sources;

    static final class ConcatMaybeObserver<T> extends AtomicInteger implements MaybeObserver<T>, d {
        private static final long serialVersionUID = 3520831347801429610L;
        final c<? super T> actual;
        final AtomicReference<Object> current = new AtomicReference(NotificationLite.COMPLETE);
        final SequentialDisposable disposables = new SequentialDisposable();
        int index;
        long produced;
        final AtomicLong requested = new AtomicLong();
        final MaybeSource<? extends T>[] sources;

        ConcatMaybeObserver(c<? super T> cVar, MaybeSource<? extends T>[] maybeSourceArr) {
            this.actual = cVar;
            this.sources = maybeSourceArr;
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.add(this.requested, j);
                drain();
            }
        }

        public void cancel() {
            this.disposables.dispose();
        }

        public void onSubscribe(Disposable disposable) {
            this.disposables.replace(disposable);
        }

        public void onSuccess(T t) {
            this.current.lazySet(t);
            drain();
        }

        public void onError(Throwable th) {
            this.actual.onError(th);
        }

        public void onComplete() {
            this.current.lazySet(NotificationLite.COMPLETE);
            drain();
        }

        void drain() {
            if (getAndIncrement() == 0) {
                AtomicReference atomicReference = this.current;
                c cVar = this.actual;
                Disposable disposable = this.disposables;
                while (!disposable.isDisposed()) {
                    NotificationLite notificationLite = atomicReference.get();
                    if (notificationLite != null) {
                        Object obj;
                        int i;
                        if (notificationLite != NotificationLite.COMPLETE) {
                            long j = this.produced;
                            if (j != this.requested.get()) {
                                this.produced = j + 1;
                                atomicReference.lazySet(null);
                                cVar.onNext(notificationLite);
                                obj = 1;
                            } else {
                                obj = null;
                            }
                        } else {
                            atomicReference.lazySet(null);
                            i = 1;
                        }
                        if (!(obj == null || disposable.isDisposed())) {
                            i = this.index;
                            if (i == this.sources.length) {
                                cVar.onComplete();
                                return;
                            } else {
                                this.index = i + 1;
                                this.sources[i].subscribe(this);
                            }
                        }
                    }
                    if (decrementAndGet() == 0) {
                        return;
                    }
                }
                atomicReference.lazySet(null);
            }
        }
    }

    public MaybeConcatArray(MaybeSource<? extends T>[] maybeSourceArr) {
        this.sources = maybeSourceArr;
    }

    protected void subscribeActual(c<? super T> cVar) {
        Object concatMaybeObserver = new ConcatMaybeObserver(cVar, this.sources);
        cVar.onSubscribe(concatMaybeObserver);
        concatMaybeObserver.drain();
    }
}
