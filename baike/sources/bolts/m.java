package bolts;

class m implements Continuation<TResult, Task<Void>> {
    final /* synthetic */ Task a;

    m(Task task) {
        this.a = task;
    }

    public Task<Void> then(Task<TResult> task) throws Exception {
        if (task.isCancelled()) {
            return Task.cancelled();
        }
        if (task.isFaulted()) {
            return Task.forError(task.getError());
        }
        return Task.forResult(null);
    }
}
