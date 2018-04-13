package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionArbiter;
import org.a.b;
import org.a.c;
import org.a.d;

public final class FlowableSwitchIfEmpty<T> extends AbstractFlowableWithUpstream<T, T> {
    final b<? extends T> other;

    static final class SwitchIfEmptySubscriber<T> implements FlowableSubscriber<T> {
        final c<? super T> actual;
        final SubscriptionArbiter arbiter = new SubscriptionArbiter();
        boolean empty = true;
        final b<? extends T> other;

        SwitchIfEmptySubscriber(c<? super T> cVar, b<? extends T> bVar) {
            this.actual = cVar;
            this.other = bVar;
        }

        public void onSubscribe(d dVar) {
            this.arbiter.setSubscription(dVar);
        }

        public void onNext(T t) {
            if (this.empty) {
                this.empty = false;
            }
            this.actual.onNext(t);
        }

        public void onError(Throwable th) {
            this.actual.onError(th);
        }

        public void onComplete() {
            if (this.empty) {
                this.empty = false;
                this.other.subscribe(this);
                return;
            }
            this.actual.onComplete();
        }
    }

    public FlowableSwitchIfEmpty(Flowable<T> flowable, b<? extends T> bVar) {
        super(flowable);
        this.other = bVar;
    }

    protected void subscribeActual(c<? super T> cVar) {
        FlowableSubscriber switchIfEmptySubscriber = new SwitchIfEmptySubscriber(cVar, this.other);
        cVar.onSubscribe(switchIfEmptySubscriber.arbiter);
        this.source.subscribe(switchIfEmptySubscriber);
    }
}
