package qsbk.app.video;

import qsbk.app.ye.videotools.player.VideoPlayer;
import qsbk.app.ye.videotools.player.VideoPlayer.OnCompletionListener;

class n implements OnCompletionListener {
    final /* synthetic */ SimpleVideoPlayer a;

    n(SimpleVideoPlayer simpleVideoPlayer) {
        this.a = simpleVideoPlayer;
    }

    public void onCompletion(VideoPlayer videoPlayer) {
        this.a.onPlayerCompletion();
    }
}
