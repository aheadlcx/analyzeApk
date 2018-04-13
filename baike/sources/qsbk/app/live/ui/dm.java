package qsbk.app.live.ui;

import qsbk.app.core.utils.LogUtils;
import qsbk.app.ye.videotools.player.VideoPlayer;
import qsbk.app.ye.videotools.player.VideoPlayer.OnCompletionListener;

class dm implements OnCompletionListener {
    final /* synthetic */ LivePullActivity a;

    dm(LivePullActivity livePullActivity) {
        this.a = livePullActivity;
    }

    public void onCompletion(VideoPlayer videoPlayer) {
        LogUtils.e(LivePullActivity.TAG, "live pull error on completion");
        this.a.h();
        if (this.a.az) {
            this.a.a(this.a.bY);
            return;
        }
        this.a.an();
        this.a.c();
    }
}
