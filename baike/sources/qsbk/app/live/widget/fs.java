package qsbk.app.live.widget;

import qsbk.app.live.model.LiveGiftMessage;

class fs implements Runnable {
    final /* synthetic */ LiveGiftMessage a;
    final /* synthetic */ LargeGiftLayout b;

    fs(LargeGiftLayout largeGiftLayout, LiveGiftMessage liveGiftMessage) {
        this.b = largeGiftLayout;
        this.a = liveGiftMessage;
    }

    public void run() {
        this.b.g(this.a);
    }
}
