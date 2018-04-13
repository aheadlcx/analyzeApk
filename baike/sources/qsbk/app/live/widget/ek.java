package qsbk.app.live.widget;

import qsbk.app.live.widget.GlobalGiftView.MarqueeItem;

class ek implements Runnable {
    final /* synthetic */ MarqueeItem a;

    ek(MarqueeItem marqueeItem) {
        this.a = marqueeItem;
    }

    public void run() {
        this.a.startMarqueeAnim();
    }
}
