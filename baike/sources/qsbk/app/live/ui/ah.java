package qsbk.app.live.ui;

class ah implements Runnable {
    final /* synthetic */ LiveBaseActivity a;

    ah(LiveBaseActivity liveBaseActivity) {
        this.a = liveBaseActivity;
    }

    public void run() {
        LiveBaseActivity.l(this.a).setAlpha(1.0f);
    }
}
