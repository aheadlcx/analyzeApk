package io.reactivex.subscribers;

import com.facebook.common.time.Clock;
import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.EndConsumerHelper;
import java.util.concurrent.atomic.AtomicReference;
import org.a.d;

public abstract class DisposableSubscriber<T> implements FlowableSubscriber<T>, Disposable {
    final AtomicReference<d> s = new AtomicReference();

    public final void onSubscribe(d dVar) {
        if (EndConsumerHelper.setOnce(this.s, dVar, getClass())) {
            onStart();
        }
    }

    protected void onStart() {
        ((d) this.s.get()).request(Clock.MAX_TIME);
    }

    protected final void request(long j) {
        ((d) this.s.get()).request(j);
    }

    protected final void cancel() {
        dispose();
    }

    public final boolean isDisposed() {
        return this.s.get() == SubscriptionHelper.CANCELLED;
    }

    public final void dispose() {
        SubscriptionHelper.cancel(this.s);
    }
}
