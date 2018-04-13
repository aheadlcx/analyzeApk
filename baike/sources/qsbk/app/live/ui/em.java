package qsbk.app.live.ui;

import com.xiaomi.mipush.sdk.Constants;
import qsbk.app.core.utils.LogUtils;
import qsbk.app.live.R;
import qsbk.app.ye.videotools.live.MediaPublisher;
import qsbk.app.ye.videotools.live.MediaPublisher.OnInfoListener;

class em implements OnInfoListener {
    final /* synthetic */ LivePushActivity a;

    em(LivePushActivity livePushActivity) {
        this.a = livePushActivity;
    }

    public void onInfo(MediaPublisher mediaPublisher, int i, int i2) {
        LogUtils.d(LivePushActivity.TAG, "live push info " + i + Constants.ACCEPT_TIME_SEPARATOR_SP + i2);
        switch (i) {
            case 1:
                if (LogUtils.LOGGABLE) {
                    LivePushActivity livePushActivity = this.a;
                    StringBuilder append = new StringBuilder().append("当前为");
                    String str = i2 == 0 ? "高" : i2 == 3 ? "低" : "一般";
                    livePushActivity.f(append.append(str).append("画质").toString());
                    break;
                }
                break;
            case 2:
                this.a.g("a");
                this.a.H();
                this.a.a(R.string.live_network_not_well);
                break;
        }
        this.a.statEvent("live_push_info", Integer.toString(i), Long.toString(this.a.ax.getOriginId()), Integer.toString(i2), "");
    }
}
