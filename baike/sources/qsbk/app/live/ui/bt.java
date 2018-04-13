package qsbk.app.live.ui;

class bt implements Runnable {
    final /* synthetic */ LiveBaseActivity a;

    bt(LiveBaseActivity liveBaseActivity) {
        this.a = liveBaseActivity;
    }

    public void run() {
        this.a.an.playCoinEffect();
    }
}
