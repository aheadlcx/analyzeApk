package qsbk.app.activity;

import qsbk.app.video.SimpleVideoPlayer;
import qsbk.app.video.SimpleVideoPlayer.OnVideoEventListener;

class aer implements OnVideoEventListener {
    final /* synthetic */ VideoFullScreenActivity a;

    aer(VideoFullScreenActivity videoFullScreenActivity) {
        this.a = videoFullScreenActivity;
    }

    public void onVideoCompletion(SimpleVideoPlayer simpleVideoPlayer) {
        VideoFullScreenActivity.a(this.a, true);
        if (VideoFullScreenActivity.j(this.a)) {
            VideoFullScreenActivity.k(this.a);
        }
    }

    public void onVideoPrepared(SimpleVideoPlayer simpleVideoPlayer) {
        VideoFullScreenActivity.a(this.a, false);
    }

    public void onVideoError(SimpleVideoPlayer simpleVideoPlayer, int i, int i2) {
    }

    public void onVideoBuffering(SimpleVideoPlayer simpleVideoPlayer, int i) {
    }
}
