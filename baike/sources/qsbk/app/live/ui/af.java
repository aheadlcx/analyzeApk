package qsbk.app.live.ui;

class af implements Runnable {
    final /* synthetic */ LiveBaseActivity a;

    af(LiveBaseActivity liveBaseActivity) {
        this.a = liveBaseActivity;
    }

    public void run() {
        this.a.closeMicConnect(true);
    }
}
