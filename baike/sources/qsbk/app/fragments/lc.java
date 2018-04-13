package qsbk.app.fragments;

class lc implements Runnable {
    final /* synthetic */ SubscribeFragment a;

    lc(SubscribeFragment subscribeFragment) {
        this.a = subscribeFragment;
    }

    public void run() {
        this.a.V.getLocation(this.a);
    }
}
