package qsbk.app.live.ui;

class cj implements Runnable {
    final /* synthetic */ LiveBaseActivity a;

    cj(LiveBaseActivity liveBaseActivity) {
        this.a = liveBaseActivity;
    }

    public void run() {
        if (this.a.aO < 1) {
            this.a.aO = 1;
        }
        this.a.u.setText(Long.toString(this.a.aO));
        LiveBaseActivity.A(this.a);
    }
}
