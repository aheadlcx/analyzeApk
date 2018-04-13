package qsbk.app.live.ui;

import qsbk.app.core.utils.LogUtils;
import qsbk.app.ye.videotools.player.VideoPlayer;
import qsbk.app.ye.videotools.player.VideoPlayer.OnInfoListener;

class dk implements OnInfoListener {
    final /* synthetic */ LivePullActivity a;

    dk(LivePullActivity livePullActivity) {
        this.a = livePullActivity;
    }

    public void onInfo(VideoPlayer videoPlayer, int i, int i2) {
        LogUtils.i(LivePullActivity.TAG, "live pull info what:" + i + " extra:" + i2);
        switch (i) {
            case 2:
                this.a.bM = this.a.bM + 1;
                this.a.g();
                break;
            case 3:
                this.a.i();
                break;
        }
        if (this.a.d != null) {
            this.a.statEvent("live_pull_info", this.a.d.getLiveUrl(), Long.toString(this.a.ax.getOriginId()), Integer.toString(i), Integer.toString(i2));
        }
    }
}
