package bolts;

class h implements Continuation<TResult, Task<TContinuationResult>> {
    final /* synthetic */ CancellationToken a;
    final /* synthetic */ Continuation b;
    final /* synthetic */ Task c;

    h(Task task, CancellationToken cancellationToken, Continuation continuation) {
        this.c = task;
        this.a = cancellationToken;
        this.b = continuation;
    }

    public Task<TContinuationResult> then(Task<TResult> task) {
        if (this.a != null && this.a.isCancellationRequested()) {
            return Task.cancelled();
        }
        if (task.isFaulted()) {
            return Task.forError(task.getError());
        }
        if (task.isCancelled()) {
            return Task.cancelled();
        }
        return task.continueWithTask(this.b);
    }
}
