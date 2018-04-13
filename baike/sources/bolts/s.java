package bolts;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

class s implements Continuation<Void, Task<Void>> {
    final /* synthetic */ CancellationToken a;
    final /* synthetic */ Callable b;
    final /* synthetic */ Continuation c;
    final /* synthetic */ Executor d;
    final /* synthetic */ Capture e;
    final /* synthetic */ Task f;

    s(Task task, CancellationToken cancellationToken, Callable callable, Continuation continuation, Executor executor, Capture capture) {
        this.f = task;
        this.a = cancellationToken;
        this.b = callable;
        this.c = continuation;
        this.d = executor;
        this.e = capture;
    }

    public Task<Void> then(Task<Void> task) throws Exception {
        if (this.a != null && this.a.isCancellationRequested()) {
            return Task.cancelled();
        }
        if (((Boolean) this.b.call()).booleanValue()) {
            return Task.forResult(null).onSuccessTask(this.c, this.d).onSuccessTask((Continuation) this.e.get(), this.d);
        }
        return Task.forResult(null);
    }
}
