package qsbk.app.live.ui;

class ar implements Runnable {
    final /* synthetic */ long a;
    final /* synthetic */ LiveBaseActivity b;

    ar(LiveBaseActivity liveBaseActivity, long j) {
        this.b = liveBaseActivity;
        this.a = j;
    }

    public void run() {
        this.b.updateBalance(this.a);
    }
}
