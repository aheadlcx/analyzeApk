package qsbk.app.live.ui;

class ad implements Runnable {
    final /* synthetic */ LiveBaseActivity a;

    ad(LiveBaseActivity liveBaseActivity) {
        this.a = liveBaseActivity;
    }

    public void run() {
        this.a.x();
    }
}
