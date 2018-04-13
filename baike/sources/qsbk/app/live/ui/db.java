package qsbk.app.live.ui;

class db implements Runnable {
    final /* synthetic */ LiveBaseActivity a;

    db(LiveBaseActivity liveBaseActivity) {
        this.a = liveBaseActivity;
    }

    public void run() {
        LiveBaseActivity.d(this.a, true);
        this.a.a(null);
    }
}
