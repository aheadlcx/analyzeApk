package qsbk.app.live.ui;

import qsbk.app.live.model.LiveBarrageMessage;

class az implements Runnable {
    final /* synthetic */ LiveBarrageMessage a;
    final /* synthetic */ LiveBaseActivity b;

    az(LiveBaseActivity liveBaseActivity, LiveBarrageMessage liveBarrageMessage) {
        this.b = liveBaseActivity;
        this.a = liveBarrageMessage;
    }

    public void run() {
        this.b.ad.addBarrage(this.a);
    }
}
