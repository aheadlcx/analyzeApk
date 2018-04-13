package qsbk.app.adapter;

import qsbk.app.adapter.VideoImmersionCircleAdapter.GdtVideoImmersionCell;
import qsbk.app.video.LightActuator;

class ep extends LightActuator {
    final /* synthetic */ GdtVideoImmersionCell a;

    ep(GdtVideoImmersionCell gdtVideoImmersionCell, int i, int i2) {
        this.a = gdtVideoImmersionCell;
        super(i, i2);
    }

    public void onColorUpdate(int i) {
        this.a.l.setBackgroundColor(i);
        this.a.n.setBackgroundColor(i);
    }
}
