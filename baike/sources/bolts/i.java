package bolts;

import java.util.concurrent.CancellationException;

final class i implements Runnable {
    final /* synthetic */ CancellationToken a;
    final /* synthetic */ TaskCompletionSource b;
    final /* synthetic */ Continuation c;
    final /* synthetic */ Task d;

    i(CancellationToken cancellationToken, TaskCompletionSource taskCompletionSource, Continuation continuation, Task task) {
        this.a = cancellationToken;
        this.b = taskCompletionSource;
        this.c = continuation;
        this.d = task;
    }

    public void run() {
        if (this.a == null || !this.a.isCancellationRequested()) {
            try {
                this.b.setResult(this.c.then(this.d));
                return;
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
