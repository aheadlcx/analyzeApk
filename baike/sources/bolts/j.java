package bolts;

import java.util.concurrent.CancellationException;

final class j implements Runnable {
    final /* synthetic */ CancellationToken a;
    final /* synthetic */ TaskCompletionSource b;
    final /* synthetic */ Continuation c;
    final /* synthetic */ Task d;

    j(CancellationToken cancellationToken, TaskCompletionSource taskCompletionSource, Continuation continuation, Task task) {
        this.a = cancellationToken;
        this.b = taskCompletionSource;
        this.c = continuation;
        this.d = task;
    }

    public void run() {
        if (this.a == null || !this.a.isCancellationRequested()) {
            try {
                Task task = (Task) this.c.then(this.d);
                if (task == null) {
                    this.b.setResult(null);
                    return;
                } else {
                    task.continueWith(new k(this));
                    return;
                }
            } catch (CancellationException e) {
                this.b.setCancelled();
                return;
            } catch (Exception e2) {
                this.b.setError(e2);
                return;
            }
        }
        this.b.setCancelled();
    }
}
