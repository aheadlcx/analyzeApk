package qsbk.app.adapter;

import qsbk.app.adapter.VideoImmersionAdapter.GdtVideoImmersionCell;
import qsbk.app.video.LightActuator;

class ds extends LightActuator {
    final /* synthetic */ GdtVideoImmersionCell a;

    ds(GdtVideoImmersionCell gdtVideoImmersionCell, int i, int i2) {
        this.a = gdtVideoImmersionCell;
        super(i, i2);
    }

    public void onColorUpdate(int i) {
        this.a.l.setBackgroundColor(i);
        this.a.n.setBackgroundColor(i);
    }
}
