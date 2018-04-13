package cn.xiaochuan.daemon;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class WakeUpReceiver extends BroadcastReceiver {

    public static class WakeUpAutoStartReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            if (a.c) {
                a.a(a.b);
            }
        }
    }

    public void onReceive(Context context, Intent intent) {
        if (intent != null && "cn.xiaochuan.daemon.CANCEL_JOB_ALARM_SUB".equals(intent.getAction())) {
            WatchDogService.a();
        } else if (a.c) {
            a.a(a.b);
        }
    }
}
