package qsbk.app.video;

import qsbk.app.ye.videotools.player.VideoPlayer;
import qsbk.app.ye.videotools.player.VideoPlayer.OnErrorListener;

class l implements OnErrorListener {
    final /* synthetic */ SimpleVideoPlayer a;

    l(SimpleVideoPlayer simpleVideoPlayer) {
        this.a = simpleVideoPlayer;
    }

    public void onError(VideoPlayer videoPlayer, int i, int i2) {
        this.a.onPlayerError(i, i2);
    }
}
