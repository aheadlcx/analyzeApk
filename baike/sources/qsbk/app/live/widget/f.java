package qsbk.app.live.widget;

import qsbk.app.live.widget.BarrageLayout.BarrageItem;

class f implements Runnable {
    final /* synthetic */ BarrageItem a;

    f(BarrageItem barrageItem) {
        this.a = barrageItem;
    }

    public void run() {
        this.a.startMarqueeAnim();
    }
}
