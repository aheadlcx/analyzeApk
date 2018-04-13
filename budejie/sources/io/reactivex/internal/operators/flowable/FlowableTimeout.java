package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.subscribers.FullArbiterSubscriber;
import io.reactivex.internal.subscriptions.FullArbiter;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.subscribers.DisposableSubscriber;
import io.reactivex.subscribers.SerializedSubscriber;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;
import org.a.b;
import org.a.c;
import org.a.d;

public final class FlowableTimeout<T, U, V> extends AbstractFlowableWithUpstream<T, T> {
    final b<U> firstTimeoutIndicator;
    final Function<? super T, ? extends b<V>> itemTimeoutIndicator;
    final b<? extends T> other;

    interface OnTimeout {
        void onError(Throwable th);

        void timeout(long j);
    }

    static final class TimeoutInnerSubscriber<T, U, V> extends DisposableSubscriber<Object> {
        boolean done;
        final long index;
        final OnTimeout parent;

        TimeoutInnerSubscriber(OnTimeout onTimeout, long j) {
            this.parent = onTimeout;
            this.index = j;
        }

        public void onNext(Object obj) {
            if (!this.done) {
                this.done = true;
                cancel();
                this.parent.timeout(this.index);
            }
        }

        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.done = true;
            this.parent.onError(th);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.parent.timeout(this.index);
            }
        }
    }

    static final class TimeoutOtherSubscriber<T, U, V> implements FlowableSubscriber<T>, Disposable, OnTimeout {
        final c<? super T> actual;
        final FullArbiter<T> arbiter;
        volatile boolean cancelled;
        boolean done;
        final b<U> firstTimeoutIndicator;
        volatile long index;
        final Function<? super T, ? extends b<V>> itemTimeoutIndicator;
        final b<? extends T> other;
        d s;
        final AtomicReference<Disposable> timeout = new AtomicReference();

        TimeoutOtherSubscriber(c<? super T> cVar, b<U> bVar, Function<? super T, ? extends b<V>> function, b<? extends T> bVar2) {
            this.actual = cVar;
            this.firstTimeoutIndicator = bVar;
            this.itemTimeoutIndicator = function;
            this.other = bVar2;
            this.arbiter = new FullArbiter(cVar, this, 8);
        }

        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.s, dVar)) {
                this.s = dVar;
                if (this.arbiter.setSubscription(dVar)) {
                    c cVar = this.actual;
                    b bVar = this.firstTimeoutIndicator;
                    if (bVar != null) {
                        c timeoutInnerSubscriber = new TimeoutInnerSubscriber(this, 0);
                        if (this.timeout.compareAndSet(null, timeoutInnerSubscriber)) {
                            cVar.onSubscribe(this.arbiter);
                            bVar.subscribe(timeoutInnerSubscriber);
                            return;
                        }
                        return;
                    }
                    cVar.onSubscribe(this.arbiter);
                }
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                long j = 1 + this.index;
                this.index = j;
                if (this.arbiter.onNext(t, this.s)) {
                    Disposable disposable = (Disposable) this.timeout.get();
                    if (disposable != null) {
                        disposable.dispose();
                    }
                    try {
                        b bVar = (b) ObjectHelper.requireNonNull(this.itemTimeoutIndicator.apply(t), "The publisher returned is null");
                        c timeoutInnerSubscriber = new TimeoutInnerSubscriber(this, j);
                        if (this.timeout.compareAndSet(disposable, timeoutInnerSubscriber)) {
                            bVar.subscribe(timeoutInnerSubscriber);
                        }
                    } catch (Throwable th) {
                        Exceptions.throwIfFatal(th);
                        this.actual.onError(th);
                    }
                }
            }
        }

        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.done = true;
            dispose();
            this.arbiter.onError(th, this.s);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                dispose();
                this.arbiter.onComplete(this.s);
            }
        }

        public void dispose() {
            this.cancelled = true;
            this.s.cancel();
            DisposableHelper.dispose(this.timeout);
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        public void timeout(long j) {
            if (j == this.index) {
                dispose();
                this.other.subscribe(new FullArbiterSubscriber(this.arbiter));
            }
        }
    }

    static final class TimeoutSubscriber<T, U, V> implements FlowableSubscriber<T>, OnTimeout, d {
        final c<? super T> actual;
        volatile boolean cancelled;
        final b<U> firstTimeoutIndicator;
        volatile long index;
        final Function<? super T, ? extends b<V>> itemTimeoutIndicator;
        d s;
        final AtomicReference<Disposable> timeout = new AtomicReference();

        TimeoutSubscriber(c<? super T> cVar, b<U> bVar, Function<? super T, ? extends b<V>> function) {
            this.actual = cVar;
            this.firstTimeoutIndicator = bVar;
            this.itemTimeoutIndicator = function;
        }

        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.s, dVar)) {
                this.s = dVar;
                if (!this.cancelled) {
                    c cVar = this.actual;
                    b bVar = this.firstTimeoutIndicator;
                    if (bVar != null) {
                        c timeoutInnerSubscriber = new TimeoutInnerSubscriber(this, 0);
                        if (this.timeout.compareAndSet(null, timeoutInnerSubscriber)) {
                            cVar.onSubscribe(this);
                            bVar.subscribe(timeoutInnerSubscriber);
                            return;
                        }
                        return;
                    }
                    cVar.onSubscribe(this);
                }
            }
        }

        public void onNext(T t) {
            long j = 1 + this.index;
            this.index = j;
            this.actual.onNext(t);
            Disposable disposable = (Disposable) this.timeout.get();
            if (disposable != null) {
                disposable.dispose();
            }
            try {
                b bVar = (b) ObjectHelper.requireNonNull(this.itemTimeoutIndicator.apply(t), "The publisher returned is null");
                c timeoutInnerSubscriber = new TimeoutInnerSubscriber(this, j);
                if (this.timeout.compareAndSet(disposable, timeoutInnerSubscriber)) {
                    bVar.subscribe(timeoutInnerSubscriber);
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                cancel();
                this.actual.onError(th);
            }
        }

        public void onError(Throwable th) {
            cancel();
            this.actual.onError(th);
        }

        public void onComplete() {
            cancel();
            this.actual.onComplete();
        }

        public void request(long j) {
            this.s.request(j);
        }

        public void cancel() {
            this.cancelled = true;
            this.s.cancel();
            DisposableHelper.dispose(this.timeout);
        }

        public void timeout(long j) {
            if (j == this.index) {
                cancel();
                this.actual.onError(new TimeoutException());
            }
        }
    }

    public FlowableTimeout(Flowable<T> flowable, b<U> bVar, Function<? super T, ? extends b<V>> function, b<? extends T> bVar2) {
        super(flowable);
        this.firstTimeoutIndicator = bVar;
        this.itemTimeoutIndicator = function;
        this.other = bVar2;
    }

    protected void subscribeActual(c<? super T> cVar) {
        if (this.other == null) {
            this.source.subscribe(new TimeoutSubscriber(new SerializedSubscriber(cVar), this.firstTimeoutIndicator, this.itemTimeoutIndicator));
        } else {
            this.source.subscribe(new TimeoutOtherSubscriber(cVar, this.firstTimeoutIndicator, this.itemTimeoutIndicator, this.other));
        }
    }
}
