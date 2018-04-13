package io.reactivex.processors;

import io.reactivex.annotations.Nullable;
import io.reactivex.internal.subscriptions.BasicIntQueueSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;

final class UnicastProcessor$UnicastQueueSubscription extends BasicIntQueueSubscription<T> {
    private static final long serialVersionUID = -4896760517184205454L;
    final /* synthetic */ UnicastProcessor this$0;

    UnicastProcessor$UnicastQueueSubscription(UnicastProcessor unicastProcessor) {
        this.this$0 = unicastProcessor;
    }

    @Nullable
    public T poll() {
        return this.this$0.queue.poll();
    }

    public boolean isEmpty() {
        return this.this$0.queue.isEmpty();
    }

    public void clear() {
        this.this$0.queue.clear();
    }

    public int requestFusion(int i) {
        if ((i & 2) == 0) {
            return 0;
        }
        this.this$0.enableOperatorFusion = true;
        return 2;
    }

    public void request(long j) {
        if (SubscriptionHelper.validate(j)) {
            BackpressureHelper.add(this.this$0.requested, j);
            this.this$0.drain();
        }
    }

    public void cancel() {
        if (!this.this$0.cancelled) {
            this.this$0.cancelled = true;
            this.this$0.doTerminate();
            if (!this.this$0.enableOperatorFusion && this.this$0.wip.getAndIncrement() == 0) {
                this.this$0.queue.clear();
                this.this$0.actual.lazySet(null);
            }
        }
    }
}
