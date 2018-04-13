package qsbk.app.live.ui;

import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.LogUtils;
import qsbk.app.live.model.LiveMessage;
import qsbk.app.live.ui.helper.StopLiveHelper;
import qsbk.app.ye.videotools.live.MediaPublisher;
import qsbk.app.ye.videotools.live.MediaPublisher.OnConnectedListener;

class el implements OnConnectedListener {
    final /* synthetic */ LivePushActivity a;

    el(LivePushActivity livePushActivity) {
        this.a = livePushActivity;
    }

    public void onConnected(MediaPublisher mediaPublisher) {
        RuntimeException e;
        LogUtils.d(LivePushActivity.TAG, "live push connected success");
        this.a.cy = true;
        this.a.aJ = 0;
        AppUtils.getInstance().setLiving(true);
        StopLiveHelper.setLivingActivity(this.a);
        this.a.i();
        this.a.bB.mute(this.a.cC);
        try {
            this.a.bB.start();
        } catch (IllegalStateException e2) {
            e = e2;
            this.a.e(true);
            e.printStackTrace();
        } catch (IllegalArgumentException e3) {
            e = e3;
            this.a.e(true);
            e.printStackTrace();
        } catch (Exception e4) {
            e4.printStackTrace();
        }
        this.a.by.setSink(this.a.bB);
        this.a.aG();
        this.a.T();
        if (this.a.cp) {
            this.a.cp = false;
            this.a.aD.sendMessage(LiveMessage.createAnchorContinueMessage(this.a.ax.getOriginId()));
        }
        if (this.a.cr > 0 && this.a.ax != null) {
            long currentTimeMillis = System.currentTimeMillis() - this.a.cr;
            LogUtils.d(LivePushActivity.TAG, "First Ready to push live begin at: " + this.a.cr + "; loadTime: " + currentTimeMillis);
            this.a.statEventDuration("live_push_load_time", this.a.ch, currentTimeMillis, Long.toString(this.a.ax.getOriginId()), "", "");
            this.a.cr = -1;
        }
    }
}
