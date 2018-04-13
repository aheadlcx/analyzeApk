package android.support.v4.app;

class g implements Runnable {
    final /* synthetic */ Fragment a;

    g(Fragment fragment) {
        this.a = fragment;
    }

    public void run() {
        this.a.callStartTransitionListener();
    }
}
