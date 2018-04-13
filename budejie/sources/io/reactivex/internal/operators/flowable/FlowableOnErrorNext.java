package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.subscriptions.SubscriptionArbiter;
import io.reactivex.plugins.RxJavaPlugins;
import org.a.b;
import org.a.c;
import org.a.d;

public final class FlowableOnErrorNext<T> extends AbstractFlowableWithUpstream<T, T> {
    final boolean allowFatal;
    final Function<? super Throwable, ? extends b<? extends T>> nextSupplier;

    static final class OnErrorNextSubscriber<T> implements FlowableSubscriber<T> {
        final c<? super T> actual;
        final boolean allowFatal;
        final SubscriptionArbiter arbiter = new SubscriptionArbiter();
        boolean done;
        final Function<? super Throwable, ? extends b<? extends T>> nextSupplier;
        boolean once;

        OnErrorNextSubscriber(c<? super T> cVar, Function<? super Throwable, ? extends b<? extends T>> function, boolean z) {
            this.actual = cVar;
            this.nextSupplier = function;
            this.allowFatal = z;
        }

        public void onSubscribe(d dVar) {
            this.arbiter.setSubscription(dVar);
        }

        public void onNext(T t) {
            if (!this.done) {
                this.actual.onNext(t);
                if (!this.once) {
                    this.arbiter.produced(1);
                }
            }
        }

        public void onError(Throwable th) {
            Throwable nullPointerException;
            if (!this.once) {
                this.once = true;
                if (!this.allowFatal || (th instanceof Exception)) {
                    try {
                        b bVar = (b) this.nextSupplier.apply(th);
                        if (bVar == null) {
                            nullPointerException = new NullPointerException("Publisher is null");
                            nullPointerException.initCause(th);
                            this.actual.onError(nullPointerException);
                            return;
                        }
                        bVar.subscribe(this);
                        return;
                    } catch (Throwable nullPointerException2) {
                        Exceptions.throwIfFatal(nullPointerException2);
                        this.actual.onError(new CompositeException(th, nullPointerException2));
                        return;
                    }
                }
                this.actual.onError(th);
            } else if (this.done) {
                RxJavaPlugins.onError(th);
            } else {
                this.actual.onError(th);
            }
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.once = true;
                this.actual.onComplete();
            }
        }
    }

    public FlowableOnErrorNext(Flowable<T> flowable, Function<? super Throwable, ? extends b<? extends T>> function, boolean z) {
        super(flowable);
        this.nextSupplier = function;
        this.allowFatal = z;
    }

    protected void subscribeActual(c<? super T> cVar) {
        FlowableSubscriber onErrorNextSubscriber = new OnErrorNextSubscriber(cVar, this.nextSupplier, this.allowFatal);
        cVar.onSubscribe(onErrorNextSubscriber.arbiter);
        this.source.subscribe(onErrorNextSubscriber);
    }
}
