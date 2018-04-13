package qsbk.app.live.ui;

import qsbk.app.live.model.LiveGameMessage;
import qsbk.app.live.model.LiveMessage;

class aw implements Runnable {
    final /* synthetic */ LiveMessage a;
    final /* synthetic */ LiveBaseActivity b;

    aw(LiveBaseActivity liveBaseActivity, LiveMessage liveMessage) {
        this.b = liveBaseActivity;
        this.a = liveMessage;
    }

    public void run() {
        if (this.b.Z()) {
            this.b.aj.loadGameData((LiveGameMessage) this.a);
            if (this.b.aj.getVisibility() != 0) {
                this.b.ab();
            }
        }
    }
}
