package io.reactivex.internal.subscribers;

import com.facebook.common.time.Clock;
import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.fuseable.SimplePlainQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.QueueDrain;
import io.reactivex.internal.util.QueueDrainHelper;
import org.a.c;

public abstract class QueueDrainSubscriber<T, U, V> extends QueueDrainSubscriberPad4 implements FlowableSubscriber<T>, QueueDrain<U, V> {
    protected final c<? super V> actual;
    protected volatile boolean cancelled;
    protected volatile boolean done;
    protected Throwable error;
    protected final SimplePlainQueue<U> queue;

    public QueueDrainSubscriber(c<? super V> cVar, SimplePlainQueue<U> simplePlainQueue) {
        this.actual = cVar;
        this.queue = simplePlainQueue;
    }

    public final boolean cancelled() {
        return this.cancelled;
    }

    public final boolean done() {
        return this.done;
    }

    public final boolean enter() {
        return this.wip.getAndIncrement() == 0;
    }

    public final boolean fastEnter() {
        return this.wip.get() == 0 && this.wip.compareAndSet(0, 1);
    }

    protected final void fastPathEmitMax(U u, boolean z, Disposable disposable) {
        c cVar = this.actual;
        SimplePlainQueue simplePlainQueue = this.queue;
        if (this.wip.get() == 0 && this.wip.compareAndSet(0, 1)) {
            long j = this.requested.get();
            if (j != 0) {
                if (accept(cVar, u) && j != Clock.MAX_TIME) {
                    produced(1);
                }
                if (leave(-1) == 0) {
                    return;
                }
            }
            disposable.dispose();
            cVar.onError(new MissingBackpressureException("Could not emit buffer due to lack of requests"));
            return;
        }
        simplePlainQueue.offer(u);
        if (!enter()) {
            return;
        }
        QueueDrainHelper.drainMaxLoop(simplePlainQueue, cVar, z, disposable, this);
    }

    protected final void fastPathOrderedEmitMax(U u, boolean z, Disposable disposable) {
        c cVar = this.actual;
        SimplePlainQueue simplePlainQueue = this.queue;
        if (this.wip.get() == 0 && this.wip.compareAndSet(0, 1)) {
            long j = this.requested.get();
            if (j == 0) {
                this.cancelled = true;
                disposable.dispose();
                cVar.onError(new MissingBackpressureException("Could not emit buffer due to lack of requests"));
                return;
            } else if (simplePlainQueue.isEmpty()) {
                if (accept(cVar, u) && j != Clock.MAX_TIME) {
                    produced(1);
                }
                if (leave(-1) == 0) {
                    return;
                }
            } else {
                simplePlainQueue.offer(u);
            }
        } else {
            simplePlainQueue.offer(u);
            if (!enter()) {
                return;
            }
        }
        QueueDrainHelper.drainMaxLoop(simplePlainQueue, cVar, z, disposable, this);
    }

    public boolean accept(c<? super V> cVar, U u) {
        return false;
    }

    public final Throwable error() {
        return this.error;
    }

    public final int leave(int i) {
        return this.wip.addAndGet(i);
    }

    public final long requested() {
        return this.requested.get();
    }

    public final long produced(long j) {
        return this.requested.addAndGet(-j);
    }

    public final void requested(long j) {
        if (SubscriptionHelper.validate(j)) {
            BackpressureHelper.add(this.requested, j);
        }
    }
}
