package com.xiaomi.push.service;

import android.content.Context;
import android.content.Intent;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.service.module.a;
import java.util.Iterator;

class bq implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ bp d;

    bq(bp bpVar, Context context, String str, String str2) {
        this.d = bpVar;
        this.a = context;
        this.b = str;
        this.c = str2;
    }

    public void run() {
        Iterator it = j.a(this.a).c(this.b).iterator();
        while (it.hasNext()) {
            a aVar = (a) it.next();
            if (XMPushService.a(aVar.e(), this.c)) {
                if (aVar.a() >= System.currentTimeMillis()) {
                    byte[] d = aVar.d();
                    if (d == null) {
                        b.a("Geo canBeShownMessage content null");
                    } else {
                        Intent a = an.a(d, System.currentTimeMillis());
                        if (a == null) {
                            b.a("Geo canBeShownMessage intent null");
                        } else {
                            an.a(this.d.a, null, d, a, true);
                            if (j.a(this.d.a).a(aVar.b()) == 0) {
                                b.a("show some exit geofence message. then remove this message failed. message_id:" + aVar.b());
                            }
                        }
                    }
                } else if (j.a(this.a).a(aVar.b()) == 0) {
                    b.a("XMPushService remove some geofence message failed. message_id:" + aVar.b());
                }
            }
        }
    }
}
