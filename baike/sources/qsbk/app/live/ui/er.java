package qsbk.app.live.ui;

class er implements Runnable {
    final /* synthetic */ LivePushActivity a;

    er(LivePushActivity livePushActivity) {
        this.a = livePushActivity;
    }

    public void run() {
        this.a.ct.getLocation(this.a);
    }
}
