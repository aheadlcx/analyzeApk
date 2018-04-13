package com.xiaomi.mipush.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.ixintui.smartlink.a.a;

public class PushMessageReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        MessageHandleService.a(new a(intent, this));
        try {
            context.startService(new Intent(context, MessageHandleService.class));
        } catch (Exception e) {
            a.a(e);
        }
    }

    public void onReceivePassThroughMessage(Context context, Object obj) {
    }

    public void onNotificationMessageClicked(Context context, Object obj) {
    }

    public void onNotificationMessageArrived(Context context, Object obj) {
    }

    @Deprecated
    public void onReceiveMessage(Context context, Object obj) {
    }

    public void onReceiveRegisterResult(Context context, Object obj) {
    }

    public void onCommandResult(Context context, Object obj) {
    }
}
