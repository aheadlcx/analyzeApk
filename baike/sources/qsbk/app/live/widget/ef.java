package qsbk.app.live.widget;

import qsbk.app.live.widget.GlobalBarrageLayout.BarrageItem;

class ef implements Runnable {
    final /* synthetic */ BarrageItem a;

    ef(BarrageItem barrageItem) {
        this.a = barrageItem;
    }

    public void run() {
        this.a.startMarqueeAnim();
    }
}
