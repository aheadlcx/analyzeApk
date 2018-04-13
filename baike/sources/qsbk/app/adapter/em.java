package qsbk.app.adapter;

import qsbk.app.adapter.VideoImmersionAdapter.VideoImmersionCell;
import qsbk.app.video.LightActuator;

class em extends LightActuator {
    final /* synthetic */ VideoImmersionCell a;

    em(VideoImmersionCell videoImmersionCell, int i, int i2) {
        this.a = videoImmersionCell;
        super(i, i2);
    }

    public void onColorUpdate(int i) {
        this.a.d.setBackgroundColor(i);
        this.a.f.setBackgroundColor(i);
    }
}
