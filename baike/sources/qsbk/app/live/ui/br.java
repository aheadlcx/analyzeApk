package qsbk.app.live.ui;

import qsbk.app.live.model.LiveMessage;
import qsbk.app.live.model.LiveProTopMessage;

class br implements Runnable {
    final /* synthetic */ LiveMessage a;
    final /* synthetic */ LiveBaseActivity b;

    br(LiveBaseActivity liveBaseActivity, LiveMessage liveMessage) {
        this.b = liveBaseActivity;
        this.a = liveMessage;
    }

    public void run() {
        this.b.ai.addProMessage((LiveProTopMessage) this.a);
    }
}
