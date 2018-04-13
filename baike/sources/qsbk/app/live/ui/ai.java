package qsbk.app.live.ui;

class ai implements Runnable {
    final /* synthetic */ LiveBaseActivity a;

    ai(LiveBaseActivity liveBaseActivity) {
        this.a = liveBaseActivity;
    }

    public void run() {
        LiveBaseActivity.t(this.a).setAlpha(1.0f);
    }
}
