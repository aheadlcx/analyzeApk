package bolts;

import java.util.concurrent.atomic.AtomicBoolean;

final class p implements Continuation<Object, Void> {
    final /* synthetic */ AtomicBoolean a;
    final /* synthetic */ TaskCompletionSource b;

    p(AtomicBoolean atomicBoolean, TaskCompletionSource taskCompletionSource) {
        this.a = atomicBoolean;
        this.b = taskCompletionSource;
    }

    public Void then(Task<Object> task) {
        if (this.a.compareAndSet(false, true)) {
            this.b.setResult(task);
        } else {
            task.getError();
        }
        return null;
    }
}
