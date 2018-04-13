package bolts;

import java.util.concurrent.atomic.AtomicBoolean;

final class o implements Continuation<TResult, Void> {
    final /* synthetic */ AtomicBoolean a;
    final /* synthetic */ TaskCompletionSource b;

    o(AtomicBoolean atomicBoolean, TaskCompletionSource taskCompletionSource) {
        this.a = atomicBoolean;
        this.b = taskCompletionSource;
    }

    public Void then(Task<TResult> task) {
        if (this.a.compareAndSet(false, true)) {
            this.b.setResult(task);
        } else {
            task.getError();
        }
        return null;
    }
}
