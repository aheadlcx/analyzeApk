package qsbk.app.live.ui;

import qsbk.app.live.model.LiveGlobalRedEnvelopesMessage;

class bb implements Runnable {
    final /* synthetic */ LiveGlobalRedEnvelopesMessage a;
    final /* synthetic */ LiveBaseActivity b;

    bb(LiveBaseActivity liveBaseActivity, LiveGlobalRedEnvelopesMessage liveGlobalRedEnvelopesMessage) {
        this.b = liveBaseActivity;
        this.a = liveGlobalRedEnvelopesMessage;
    }

    public void run() {
        this.b.ae.addBarrage(this.a);
    }
}
