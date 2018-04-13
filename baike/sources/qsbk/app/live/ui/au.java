package qsbk.app.live.ui;

import qsbk.app.live.model.LiveGiftMessage;
import qsbk.app.live.model.LiveMessage;

class au implements Runnable {
    final /* synthetic */ LiveGiftMessage a;
    final /* synthetic */ LiveMessage b;
    final /* synthetic */ LiveBaseActivity c;

    au(LiveBaseActivity liveBaseActivity, LiveGiftMessage liveGiftMessage, LiveMessage liveMessage) {
        this.c = liveBaseActivity;
        this.a = liveGiftMessage;
        this.b = liveMessage;
    }

    public void run() {
        if (!this.c.ao) {
            if (!this.c.aa() || !this.c.A()) {
                if (this.c.ag.isSupportLargeAnim(this.a)) {
                    this.c.ag.addGift(this.a);
                } else if (this.b.getMessageType() == 6) {
                    this.c.ac.addGift(this.a);
                    this.c.a(this.a);
                }
                if (this.b.getMessageType() == 6 && this.c.isMe(this.a) && this.a.getGiftCount() >= 99) {
                    this.c.C();
                }
            }
        }
    }
}
