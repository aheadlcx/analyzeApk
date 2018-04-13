package qsbk.app.adapter;

import qsbk.app.adapter.VideoImmersionCircleAdapter.VideoImmersionCell;
import qsbk.app.video.CircleVideoPlayerView.OnFullScreenClick;

class ey implements OnFullScreenClick {
    final /* synthetic */ VideoImmersionCell a;

    ey(VideoImmersionCell videoImmersionCell) {
        this.a = videoImmersionCell;
    }

    public void onFullScreenClick() {
        this.a.a();
    }
}
