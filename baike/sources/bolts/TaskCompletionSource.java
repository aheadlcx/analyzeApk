package bolts;

public class TaskCompletionSource<TResult> {
    private final Task<TResult> a = new Task();

    public Task<TResult> getTask() {
        return this.a;
    }

    public boolean trySetCancelled() {
        return this.a.a();
    }

    public boolean trySetResult(TResult tResult) {
        return this.a.a((Object) tResult);
    }

    public boolean trySetError(Exception exception) {
        return this.a.a(exception);
    }

    public void setCancelled() {
        if (!trySetCancelled()) {
            throw new IllegalStateException("Cannot cancel a completed task.");
        }
    }

    public void setResult(TResult tResult) {
        if (!trySetResult(tResult)) {
            throw new IllegalStateException("Cannot set the result of a completed task.");
        }
    }

    public void setError(Exception exception) {
        if (!trySetError(exception)) {
            throw new IllegalStateException("Cannot set the error on a completed task.");
        }
    }
}
