package qsbk.app.fragments;

class hg implements Runnable {
    final /* synthetic */ NearbyCircleFragment a;

    hg(NearbyCircleFragment nearbyCircleFragment) {
        this.a = nearbyCircleFragment;
    }

    public void run() {
        this.a.p.autoPlay();
    }
}
