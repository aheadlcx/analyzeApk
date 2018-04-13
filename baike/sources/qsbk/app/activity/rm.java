package qsbk.app.activity;

import com.baidu.mobstat.StatService;
import com.qiushibaike.statsdk.StatSDK;
import qsbk.app.core.utils.NotificationUtils;
import qsbk.app.push.PushMessageManager;
import qsbk.app.push.PushTest;
import qsbk.app.utils.MiUiUtils;

class rm implements Runnable {
    final /* synthetic */ MainActivity a;

    rm(MainActivity mainActivity) {
        this.a = mainActivity;
    }

    public void run() {
        if (MiUiUtils.isMiUiSystemPushEnable()) {
            PushTest.initMiPush(this.a);
        } else {
            PushMessageManager.initIXinTui(this.a);
        }
        MainActivity.f(this.a);
        StatSDK.onEvent(this.a, "notification_enable", NotificationUtils.isNotificationAllow(this.a) ? "1" : "0");
        StatService.onEvent(this.a, "notification_enable", NotificationUtils.isNotificationAllow(this.a) ? "1" : "0");
    }
}
