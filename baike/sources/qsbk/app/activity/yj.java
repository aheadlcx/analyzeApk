package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.baidu.mobstat.StatService;
import qsbk.app.QsbkApp;
import qsbk.app.push.PushTest;

class yj implements OnClickListener {
    final /* synthetic */ NotificationSettingActivity a;

    yj(NotificationSettingActivity notificationSettingActivity) {
        this.a = notificationSettingActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        StatService.onEvent(this.a, "cancel_close_push_on_setting", "");
        this.a.b.setChecked(true);
        PushTest.initMiPush(QsbkApp.mContext);
    }
}
