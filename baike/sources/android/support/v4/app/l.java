package android.support.v4.app;

class l implements Runnable {
    final /* synthetic */ k a;

    l(k kVar) {
        this.a = kVar;
    }

    public void run() {
        this.a.execPendingActions();
    }
}
