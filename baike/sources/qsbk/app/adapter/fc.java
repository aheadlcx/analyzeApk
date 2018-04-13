package qsbk.app.adapter;

import qsbk.app.adapter.VideoImmersionCircleAdapter.VideoImmersionCell;
import qsbk.app.video.LightActuator;

class fc extends LightActuator {
    final /* synthetic */ VideoImmersionCell a;

    fc(VideoImmersionCell videoImmersionCell, int i, int i2) {
        this.a = videoImmersionCell;
        super(i, i2);
    }

    public void onColorUpdate(int i) {
        this.a.f.setBackgroundColor(i);
    }
}
