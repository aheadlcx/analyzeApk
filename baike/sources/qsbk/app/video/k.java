package qsbk.app.video;

import qsbk.app.ye.videotools.player.VideoPlayer;
import qsbk.app.ye.videotools.player.VideoPlayer.OnFirstFrameListener;

class k implements OnFirstFrameListener {
    final /* synthetic */ SimpleVideoPlayer a;

    k(SimpleVideoPlayer simpleVideoPlayer) {
        this.a = simpleVideoPlayer;
    }

    public void onFirstFrame(VideoPlayer videoPlayer) {
        this.a.onFirstFrameDone();
    }
}
