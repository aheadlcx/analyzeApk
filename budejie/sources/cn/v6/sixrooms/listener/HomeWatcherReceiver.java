package cn.v6.sixrooms.listener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import cn.v6.sixrooms.utils.LogUtils;

public class HomeWatcherReceiver extends BroadcastReceiver {
    private HomeWatcherReceiver$CallBackHomeKey a;

    public HomeWatcherReceiver(HomeWatcherReceiver$CallBackHomeKey homeWatcherReceiver$CallBackHomeKey) {
        this.a = homeWatcherReceiver$CallBackHomeKey;
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        LogUtils.i("HomeReceiver", "onReceive: action: " + action);
        if (action.equals("android.intent.action.CLOSE_SYSTEM_DIALOGS")) {
            action = intent.getStringExtra("reason");
            LogUtils.i("HomeReceiver", "reason: " + action);
            if ("homekey".equals(action)) {
                this.a.onPressHome(1);
            } else if ("assist".equals(action)) {
                this.a.onPressHome(2);
            } else if ("recentapps".equals(action)) {
                this.a.onShowRecentApp();
            } else if ("lock".equals(action)) {
                this.a.onLockScreen();
            }
        }
    }
}
