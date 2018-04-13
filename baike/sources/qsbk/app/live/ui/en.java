package qsbk.app.live.ui;

import qsbk.app.core.utils.LogUtils;
import qsbk.app.ye.videotools.live.MediaPublisher;
import qsbk.app.ye.videotools.live.MediaPublisher.OnErrorListener;

class en implements OnErrorListener {
    final /* synthetic */ LivePushActivity a;

    en(LivePushActivity livePushActivity) {
        this.a = livePushActivity;
    }

    public void onError(MediaPublisher mediaPublisher, int i, int i2) {
        LogUtils.e(LivePushActivity.TAG, "live push error " + i);
        this.a.h();
        switch (i) {
            case 0:
            case 2:
            case 3:
                this.a.H();
                break;
            case 1:
                this.a.aJ = 0;
                this.a.H();
                break;
            default:
                this.a.f("Error:" + i);
                break;
        }
        if (i2 == 5) {
            this.a.cz = this.a.cz + 1;
        } else if (i2 == 110) {
            this.a.cA = this.a.cA + 1;
        }
        this.a.statEvent("live_push_error", Integer.toString(i), Long.toString(this.a.ax.getOriginId()), Integer.toString(i2), "");
    }
}
