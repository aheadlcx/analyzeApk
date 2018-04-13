package bolts;

class c implements Runnable {
    final /* synthetic */ CancellationTokenSource a;

    c(CancellationTokenSource cancellationTokenSource) {
        this.a = cancellationTokenSource;
    }

    public void run() {
        synchronized (this.a.a) {
            this.a.d = null;
        }
        this.a.cancel();
    }
}
