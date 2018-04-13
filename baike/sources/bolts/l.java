package bolts;

import java.util.concurrent.ScheduledFuture;

final class l implements Runnable {
    final /* synthetic */ ScheduledFuture a;
    final /* synthetic */ TaskCompletionSource b;

    l(ScheduledFuture scheduledFuture, TaskCompletionSource taskCompletionSource) {
        this.a = scheduledFuture;
        this.b = taskCompletionSource;
    }

    public void run() {
        this.a.cancel(true);
        this.b.trySetCancelled();
    }
}
