package qsbk.app.slide;

import qsbk.app.video.SimpleVideoPlayer;
import qsbk.app.video.SimpleVideoPlayer.OnVideoEventListener;

class ax implements OnVideoEventListener {
    final /* synthetic */ SingleArticleFragment a;

    ax(SingleArticleFragment singleArticleFragment) {
        this.a = singleArticleFragment;
    }

    public void onVideoCompletion(SimpleVideoPlayer simpleVideoPlayer) {
        this.a.B();
    }

    public void onVideoPrepared(SimpleVideoPlayer simpleVideoPlayer) {
        this.a.B();
    }

    public void onVideoError(SimpleVideoPlayer simpleVideoPlayer, int i, int i2) {
    }

    public void onVideoBuffering(SimpleVideoPlayer simpleVideoPlayer, int i) {
    }
}
