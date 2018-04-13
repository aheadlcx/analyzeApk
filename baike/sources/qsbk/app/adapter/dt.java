package qsbk.app.adapter;

import qsbk.app.adapter.VideoImmersionAdapter.GdtVideoImmersionCell;
import qsbk.app.video.LightActuator;

class dt extends LightActuator {
    final /* synthetic */ GdtVideoImmersionCell a;

    dt(GdtVideoImmersionCell gdtVideoImmersionCell, int i, int i2) {
        this.a = gdtVideoImmersionCell;
        super(i, i2);
    }

    public void onColorUpdate(int i) {
        this.a.m.setBackgroundColor(i);
    }
}
