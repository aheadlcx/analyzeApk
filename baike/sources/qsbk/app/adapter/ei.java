package qsbk.app.adapter;

import qsbk.app.adapter.VideoImmersionAdapter.VideoImmersionCell;
import qsbk.app.video.CircleVideoPlayerView.OnFullScreenClick;

class ei implements OnFullScreenClick {
    final /* synthetic */ VideoImmersionCell a;

    ei(VideoImmersionCell videoImmersionCell) {
        this.a = videoImmersionCell;
    }

    public void onFullScreenClick() {
        this.a.a();
    }
}
