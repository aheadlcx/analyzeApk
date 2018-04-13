package qsbk.app.video;

import qsbk.app.ye.videotools.player.VideoPlayer;
import qsbk.app.ye.videotools.player.VideoPlayer.OnPreparedListener;

class j implements OnPreparedListener {
    final /* synthetic */ SimpleVideoPlayer a;

    j(SimpleVideoPlayer simpleVideoPlayer) {
        this.a = simpleVideoPlayer;
    }

    public void onPrepared(VideoPlayer videoPlayer) {
        this.a.onPlayerPrepared();
    }
}
