package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BiFunction;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.a.c;
import org.a.d;

public final class FlowableScan<T> extends AbstractFlowableWithUpstream<T, T> {
    final BiFunction<T, T, T> accumulator;

    static final class ScanSubscriber<T> implements FlowableSubscriber<T>, d {
        final BiFunction<T, T, T> accumulator;
        final c<? super T> actual;
        boolean done;
        d s;
        T value;

        ScanSubscriber(c<? super T> cVar, BiFunction<T, T, T> biFunction) {
            this.actual = cVar;
            this.accumulator = biFunction;
        }

        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.s, dVar)) {
                this.s = dVar;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                c cVar = this.actual;
                Object obj = this.value;
                if (obj == null) {
                    this.value = t;
                    cVar.onNext(t);
                    return;
                }
                try {
                    obj = ObjectHelper.requireNonNull(this.accumulator.apply(obj, t), "The value returned by the accumulator is null");
                    this.value = obj;
                    cVar.onNext(obj);
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    this.s.cancel();
                    onError(th);
                }
            }
        }

        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.done = true;
            this.actual.onError(th);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.actual.onComplete();
            }
        }

        public void request(long j) {
            this.s.request(j);
        }

        public void cancel() {
            this.s.cancel();
        }
    }

    public FlowableScan(Flowable<T> flowable, BiFunction<T, T, T> biFunction) {
        super(flowable);
        this.accumulator = biFunction;
    }

    protected void subscribeActual(c<? super T> cVar) {
        this.source.subscribe(new ScanSubscriber(cVar, this.accumulator));
    }
}
