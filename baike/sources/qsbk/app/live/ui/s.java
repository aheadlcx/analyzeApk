package qsbk.app.live.ui;

class s implements Runnable {
    final /* synthetic */ LiveBaseActivity a;

    s(LiveBaseActivity liveBaseActivity) {
        this.a = liveBaseActivity;
    }

    public void run() {
        this.a.forwardIfNotLogin();
    }
}
