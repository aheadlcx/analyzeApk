package qsbk.app.live.ui;

class ag implements Runnable {
    final /* synthetic */ LiveBaseActivity a;

    ag(LiveBaseActivity liveBaseActivity) {
        this.a = liveBaseActivity;
    }

    public void run() {
        LiveBaseActivity.l(this.a).removeAllViews();
    }
}
