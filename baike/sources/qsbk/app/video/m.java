package qsbk.app.video;

import android.text.TextUtils;
import qsbk.app.ye.videotools.player.VideoPlayer;
import qsbk.app.ye.videotools.player.VideoPlayer.OnInfoListener;

class m implements OnInfoListener {
    final /* synthetic */ SimpleVideoPlayer a;

    m(SimpleVideoPlayer simpleVideoPlayer) {
        this.a = simpleVideoPlayer;
    }

    public void onInfo(VideoPlayer videoPlayer, int i, int i2) {
        switch (i) {
            case 1:
                if (i2 >= 100 && !TextUtils.isEmpty(this.a.f)) {
                    this.a.onPlayerCached();
                }
                if (this.a.r != null) {
                    this.a.r.onVideoCachePercent(this.a, i2);
                    return;
                }
                return;
            case 2:
                this.a.onPlayerBuffering(i2);
                return;
            case 3:
                this.a.onPlayerBuffering(100);
                return;
            default:
                return;
        }
    }
}
