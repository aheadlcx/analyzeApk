package qsbk.app.live.ui;

class bk implements Runnable {
    final /* synthetic */ LiveBaseActivity a;

    bk(LiveBaseActivity liveBaseActivity) {
        this.a = liveBaseActivity;
    }

    public void run() {
        this.a.mHandler.removeCallbacks(this);
        this.a.U.setVisibility(8);
    }
}
