package com.xiaomi.mipush.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.xiaomi.mipush.sdk.MessageHandleService.a;

public abstract class PushMessageReceiver extends BroadcastReceiver {
    public void a(Context context, MiPushCommandMessage miPushCommandMessage) {
    }

    public void a(Context context, MiPushMessage miPushMessage) {
    }

    public void b(Context context, MiPushCommandMessage miPushCommandMessage) {
    }

    public void b(Context context, MiPushMessage miPushMessage) {
    }

    public void c(Context context, MiPushMessage miPushMessage) {
    }

    @Deprecated
    public void d(Context context, MiPushMessage miPushMessage) {
    }

    public final void onReceive(Context context, Intent intent) {
        MessageHandleService.addJob(new a(intent, this));
        try {
            context.startService(new Intent(context, MessageHandleService.class));
        } catch (Exception e) {
        }
    }
}
