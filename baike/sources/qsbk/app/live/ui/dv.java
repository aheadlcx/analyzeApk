package qsbk.app.live.ui;

class dv implements Runnable {
    final /* synthetic */ LivePullActivity a;

    dv(LivePullActivity livePullActivity) {
        this.a = livePullActivity;
    }

    public void run() {
        this.a.h(false);
    }
}
