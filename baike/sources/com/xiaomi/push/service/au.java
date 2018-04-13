package com.xiaomi.push.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.smack.util.e;
import cz.msebera.android.httpclient.HttpHeaders;

class au extends BroadcastReceiver {
    final /* synthetic */ XMPushService a;

    au(XMPushService xMPushService) {
        this.a = xMPushService;
    }

    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("com.xiaomi.metok.geofencing.state_change")) {
            String stringExtra = intent.getStringExtra(HttpHeaders.LOCATION);
            String stringExtra2 = intent.getStringExtra("Describe");
            String stringExtra3 = intent.getStringExtra("State");
            if (!TextUtils.isEmpty(stringExtra2)) {
                if (!this.a.a(stringExtra3, stringExtra2, context)) {
                    stringExtra3 = "Unknown";
                    b.a(" updated geofence statue about geo_id:" + stringExtra2 + " falied. current_statue:" + stringExtra3);
                }
                e.a(new ax(this, context, stringExtra2, stringExtra3));
                b.c("ownresilt结果:state= " + stringExtra3 + "\n describe=" + stringExtra2 + "\n location=" + stringExtra);
            }
        }
    }
}
