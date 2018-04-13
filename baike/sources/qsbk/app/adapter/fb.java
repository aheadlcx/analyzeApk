package qsbk.app.adapter;

import qsbk.app.adapter.VideoImmersionCircleAdapter.VideoImmersionCell;
import qsbk.app.video.LightActuator;

class fb extends LightActuator {
    final /* synthetic */ VideoImmersionCell a;

    fb(VideoImmersionCell videoImmersionCell, int i, int i2) {
        this.a = videoImmersionCell;
        super(i, i2);
    }

    public void onColorUpdate(int i) {
        this.a.e.setBackgroundColor(i);
        this.a.g.setBackgroundColor(i);
    }
}
