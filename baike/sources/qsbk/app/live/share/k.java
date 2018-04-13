package qsbk.app.live.share;

class k implements Runnable {
    final /* synthetic */ LiveShareActivity a;

    k(LiveShareActivity liveShareActivity) {
        this.a = liveShareActivity;
    }

    public void run() {
        this.a.doShareItemClicked(this.a.k);
        this.a.finish();
    }
}
