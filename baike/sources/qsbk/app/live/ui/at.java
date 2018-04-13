package qsbk.app.live.ui;

import qsbk.app.live.model.LiveGlobalGiftMessage;
import qsbk.app.live.model.LiveMessage;

class at implements Runnable {
    final /* synthetic */ LiveMessage a;
    final /* synthetic */ LiveBaseActivity b;

    at(LiveBaseActivity liveBaseActivity, LiveMessage liveMessage) {
        this.b = liveBaseActivity;
        this.a = liveMessage;
    }

    public void run() {
        this.b.m.addGlobalGift((LiveGlobalGiftMessage) this.a);
    }
}
