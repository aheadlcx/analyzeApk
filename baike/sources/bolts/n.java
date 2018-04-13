package bolts;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;

final class n implements Runnable {
    final /* synthetic */ CancellationToken a;
    final /* synthetic */ TaskCompletionSource b;
    final /* synthetic */ Callable c;

    n(CancellationToken cancellationToken, TaskCompletionSource taskCompletionSource, Callable callable) {
        this.a = cancellationToken;
        this.b = taskCompletionSource;
        this.c = callable;
    }

    public void run() {
        if (this.a == null || !this.a.isCancellationRequested()) {
            try {
                this.b.setResult(this.c.call());
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
