package qsbk.app.live.widget;

import qsbk.app.live.model.LiveGiftMessage;

class ft implements Runnable {
    final /* synthetic */ LiveGiftMessage a;
    final /* synthetic */ LargeGiftLayout b;

    ft(LargeGiftLayout largeGiftLayout, LiveGiftMessage liveGiftMessage) {
        this.b = largeGiftLayout;
        this.a = liveGiftMessage;
    }

    public void run() {
        this.b.h(this.a);
    }
}
