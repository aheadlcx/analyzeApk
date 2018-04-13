package qsbk.app.live.ui;

class ax implements Runnable {
    final /* synthetic */ LiveBaseActivity a;

    ax(LiveBaseActivity liveBaseActivity) {
        this.a = liveBaseActivity;
    }

    public void run() {
        if (this.a.Z()) {
            this.a.a(false, 0, 0, null);
            this.a.d(true);
        }
    }
}
