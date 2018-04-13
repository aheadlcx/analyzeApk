package qsbk.app.live.ui;

import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.LogUtils;
import qsbk.app.live.ui.helper.StopLiveHelper;
import qsbk.app.ye.videotools.player.VideoPlayer;
import qsbk.app.ye.videotools.player.VideoPlayer.OnPreparedListener;

class ed implements OnPreparedListener {
    final /* synthetic */ LivePullActivity a;

    ed(LivePullActivity livePullActivity) {
        this.a = livePullActivity;
    }

    public void onPrepared(VideoPlayer videoPlayer) {
        this.a.aJ = 0;
        this.a.bN = true;
        this.a.i();
        if (this.a.ah()) {
            this.a.a("Width:" + this.a.bv.getVideoWidth() + ", Height:" + this.a.bv.getVideoHeight(), 0);
        }
        if (this.a.az) {
            AppUtils.getInstance().setLiving(true);
            StopLiveHelper.setLivingActivity(this.a);
            videoPlayer.start();
            LogUtils.d(LivePullActivity.TAG, "enter to play time: " + (System.currentTimeMillis() - this.a.bX));
        }
        if (this.a.bK > 0 && this.a.ax != null) {
            long currentTimeMillis = System.currentTimeMillis() - this.a.bK;
            LogUtils.d(LivePullActivity.TAG, "First Ready to watch live begin at: " + this.a.bK + "; loadtime: " + currentTimeMillis);
            if (this.a.d != null) {
                this.a.statEventDuration("live_pull_load_time", this.a.d.getLiveUrl(), currentTimeMillis, Long.toString(this.a.ax.getOriginId()), "", "");
            }
            this.a.bK = -1;
        }
    }
}
