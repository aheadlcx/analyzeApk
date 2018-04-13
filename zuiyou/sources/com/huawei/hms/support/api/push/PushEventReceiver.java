package com.huawei.hms.support.api.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.huawei.hms.support.log.a;

public class PushEventReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (context != null && intent != null) {
            String action = intent.getAction();
            if (a.b()) {
                a.b("PushEventReceiver", "receive self show message, action is " + action);
            }
            if ("com.huawei.intent.action.PUSH".equals(action) && intent.hasExtra("selfshow_info")) {
                a(context, intent);
            } else if (a.a()) {
                a.a("PushEventReceiver", "invalid action.");
            }
        } else if (a.a()) {
            a.a("PushEventReceiver", "context== null or intent == null");
        }
    }

    private static void a(Context context, Intent intent) {
        if (com.huawei.hms.support.api.push.a.a.a(context, HuaweiApiAvailability.SERVICES_PACKAGE)) {
            if (a.a()) {
                a.a("PushEventReceiver", "transfer this message to HMS to depose selfshow msg");
            }
            Intent intent2 = new Intent(intent.getAction());
            Bundle extras = intent.getExtras();
            if (extras != null) {
                intent2.putExtras(extras);
                intent2.setFlags(32);
                intent2.setPackage(HuaweiApiAvailability.SERVICES_PACKAGE);
                context.sendBroadcast(intent2);
            } else if (a.a()) {
                a.a("PushEventReceiver", "self show failure, msg is null");
            }
        } else if (a.c()) {
            a.c("PushEventReceiver", "HMS is not installed, can't depose selfshow message");
        }
    }
}
