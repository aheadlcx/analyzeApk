package qsbk.app.live.ui;

class f implements Runnable {
    final /* synthetic */ LiveBaseActivity a;

    f(LiveBaseActivity liveBaseActivity) {
        this.a = liveBaseActivity;
    }

    public void run() {
        LiveBaseActivity.H(this.a).play();
    }
}
