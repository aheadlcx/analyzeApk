package io.reactivex.internal.operators.flowable;

import com.facebook.common.time.Clock;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import java.util.concurrent.atomic.AtomicBoolean;
import org.a.c;
import org.a.d;

public final class FlowableTake<T> extends AbstractFlowableWithUpstream<T, T> {
    final long limit;

    static final class TakeSubscriber<T> extends AtomicBoolean implements FlowableSubscriber<T>, d {
        private static final long serialVersionUID = -5636543848937116287L;
        final c<? super T> actual;
        boolean done;
        final long limit;
        long remaining;
        d subscription;

        TakeSubscriber(c<? super T> cVar, long j) {
            this.actual = cVar;
            this.limit = j;
            this.remaining = j;
        }

        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.subscription, dVar)) {
                this.subscription = dVar;
                if (this.limit == 0) {
                    dVar.cancel();
                    this.done = true;
                    EmptySubscription.complete(this.actual);
                    return;
                }
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                long j = this.remaining;
                this.remaining = j - 1;
                if (j > 0) {
                    Object obj = this.remaining == 0 ? 1 : null;
                    this.actual.onNext(t);
                    if (obj != null) {
                        this.subscription.cancel();
                        onComplete();
                    }
                }
            }
        }

        public void onError(Throwable th) {
            if (!this.done) {
                this.done = true;
                this.subscription.cancel();
                this.actual.onError(th);
            }
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.actual.onComplete();
            }
        }

        public void request(long j) {
            if (!SubscriptionHelper.validate(j)) {
                return;
            }
            if (get() || !compareAndSet(false, true) || j < this.limit) {
                this.subscription.request(j);
            } else {
                this.subscription.request(Clock.MAX_TIME);
            }
        }

        public void cancel() {
            this.subscription.cancel();
        }
    }

    public FlowableTake(Flowable<T> flowable, long j) {
        super(flowable);
        this.limit = j;
    }

    protected void subscribeActual(c<? super T> cVar) {
        this.source.subscribe(new TakeSubscriber(cVar, this.limit));
    }
}
