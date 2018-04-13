package io.reactivex.subscribers;

import com.facebook.common.time.Clock;
import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.EndConsumerHelper;
import org.a.d;

public abstract class DefaultSubscriber<T> implements FlowableSubscriber<T> {
    private d s;

    public final void onSubscribe(d dVar) {
        if (EndConsumerHelper.validate(this.s, dVar, getClass())) {
            this.s = dVar;
            onStart();
        }
    }

    protected final void request(long j) {
        d dVar = this.s;
        if (dVar != null) {
            dVar.request(j);
        }
    }

    protected final void cancel() {
        d dVar = this.s;
        this.s = SubscriptionHelper.CANCELLED;
        dVar.cancel();
    }

    protected void onStart() {
        request(Clock.MAX_TIME);
    }
}
