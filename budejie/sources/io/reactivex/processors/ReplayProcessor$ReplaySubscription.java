package io.reactivex.processors;

import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.a.c;
import org.a.d;

final class ReplayProcessor$ReplaySubscription<T> extends AtomicInteger implements d {
    private static final long serialVersionUID = 466549804534799122L;
    final c<? super T> actual;
    volatile boolean cancelled;
    Object index;
    final AtomicLong requested = new AtomicLong();
    final ReplayProcessor<T> state;

    ReplayProcessor$ReplaySubscription(c<? super T> cVar, ReplayProcessor<T> replayProcessor) {
        this.actual = cVar;
        this.state = replayProcessor;
    }

    public void request(long j) {
        if (SubscriptionHelper.validate(j)) {
            BackpressureHelper.add(this.requested, j);
            this.state.buffer.replay(this);
        }
    }

    public void cancel() {
        if (!this.cancelled) {
            this.cancelled = true;
            this.state.remove(this);
        }
    }
}
