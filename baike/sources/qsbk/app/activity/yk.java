package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.baidu.mobstat.StatService;

class yk implements OnClickListener {
    final /* synthetic */ NotificationSettingActivity a;

    yk(NotificationSettingActivity notificationSettingActivity) {
        this.a = notificationSettingActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        StatService.onEvent(this.a, "close_push_on_setting", "");
        dialogInterface.dismiss();
    }
}
