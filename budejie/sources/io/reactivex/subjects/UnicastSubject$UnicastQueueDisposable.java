package io.reactivex.subjects;

import io.reactivex.annotations.Nullable;
import io.reactivex.internal.observers.BasicIntQueueDisposable;

final class UnicastSubject$UnicastQueueDisposable extends BasicIntQueueDisposable<T> {
    private static final long serialVersionUID = 7926949470189395511L;
    final /* synthetic */ UnicastSubject this$0;

    UnicastSubject$UnicastQueueDisposable(UnicastSubject unicastSubject) {
        this.this$0 = unicastSubject;
    }

    public int requestFusion(int i) {
        if ((i & 2) == 0) {
            return 0;
        }
        this.this$0.enableOperatorFusion = true;
        return 2;
    }

    @Nullable
    public T poll() throws Exception {
        return this.this$0.queue.poll();
    }

    public boolean isEmpty() {
        return this.this$0.queue.isEmpty();
    }

    public void clear() {
        this.this$0.queue.clear();
    }

    public void dispose() {
        if (!this.this$0.disposed) {
            this.this$0.disposed = true;
            this.this$0.doTerminate();
            this.this$0.actual.lazySet(null);
            if (this.this$0.wip.getAndIncrement() == 0) {
                this.this$0.actual.lazySet(null);
                this.this$0.queue.clear();
            }
        }
    }

    public boolean isDisposed() {
        return this.this$0.disposed;
    }
}
