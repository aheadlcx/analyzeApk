package bolts;

import java.util.concurrent.Executor;

class f implements Continuation<TResult, Void> {
    final /* synthetic */ TaskCompletionSource a;
    final /* synthetic */ Continuation b;
    final /* synthetic */ Executor c;
    final /* synthetic */ CancellationToken d;
    final /* synthetic */ Task e;

    f(Task task, TaskCompletionSource taskCompletionSource, Continuation continuation, Executor executor, CancellationToken cancellationToken) {
        this.e = task;
        this.a = taskCompletionSource;
        this.b = continuation;
        this.c = executor;
        this.d = cancellationToken;
    }

    public Void then(Task<TResult> task) {
        Task.d(this.a, this.b, task, this.c, this.d);
        return null;
    }
}
