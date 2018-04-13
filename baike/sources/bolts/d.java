package bolts;

final class d implements Runnable {
    final /* synthetic */ TaskCompletionSource a;

    d(TaskCompletionSource taskCompletionSource) {
        this.a = taskCompletionSource;
    }

    public void run() {
        this.a.trySetResult(null);
    }
}
