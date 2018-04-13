package qsbk.app.live.ui;

import qsbk.app.core.utils.LogUtils;
import qsbk.app.ye.videotools.player.VideoPlayer;
import qsbk.app.ye.videotools.player.VideoPlayer.OnErrorListener;

class dl implements OnErrorListener {
    final /* synthetic */ LivePullActivity a;

    dl(LivePullActivity livePullActivity) {
        this.a = livePullActivity;
    }

    public void onError(VideoPlayer videoPlayer, int i, int i2) {
        LogUtils.e(LivePullActivity.TAG, "live pull error what:" + i + " extra:" + i2);
        this.a.h();
        switch (i) {
            case 3:
            case 4:
            case 5:
            case 6:
                if (!this.a.az) {
                    this.a.an();
                    this.a.c();
                    break;
                }
                this.a.a(this.a.bY);
                break;
        }
        if (i2 == 875574520) {
            this.a.bO = this.a.bO + 1;
        } else if (i2 == 110) {
            this.a.bP = this.a.bP + 1;
        }
        if (this.a.d != null) {
            this.a.statEvent("live_pull_error", this.a.d.getLiveUrl(), Long.toString(this.a.ax.getOriginId()), Integer.toString(i), Integer.toString(i2));
        }
    }
}
