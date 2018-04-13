package bolts;

class k implements Continuation<TContinuationResult, Void> {
    final /* synthetic */ j a;

    k(j jVar) {
        this.a = jVar;
    }

    public Void then(Task<TContinuationResult> task) {
        if (this.a.a != null && this.a.a.isCancellationRequested()) {
            this.a.b.setCancelled();
        } else if (task.isCancelled()) {
            this.a.b.setCancelled();
        } else if (task.isFaulted()) {
            this.a.b.setError(task.getError());
        } else {
            this.a.b.setResult(task.getResult());
        }
        return null;
    }
}
