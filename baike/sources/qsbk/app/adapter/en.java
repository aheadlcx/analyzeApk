package qsbk.app.adapter;

import qsbk.app.adapter.VideoImmersionAdapter.VideoImmersionCell;
import qsbk.app.video.LightActuator;

class en extends LightActuator {
    final /* synthetic */ VideoImmersionCell a;

    en(VideoImmersionCell videoImmersionCell, int i, int i2) {
        this.a = videoImmersionCell;
        super(i, i2);
    }

    public void onColorUpdate(int i) {
        this.a.e.setBackgroundColor(i);
    }
}
