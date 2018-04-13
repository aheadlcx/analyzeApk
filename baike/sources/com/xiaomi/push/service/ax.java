package com.xiaomi.push.service;

import android.content.Context;
import android.content.Intent;
import com.xiaomi.push.service.module.b;
import java.util.Iterator;

class ax implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ au d;

    ax(au auVar, Context context, String str, String str2) {
        this.d = auVar;
        this.a = context;
        this.b = str;
        this.c = str2;
    }

    public void run() {
        Iterator it = g.a(this.a).c(this.b).iterator();
        while (it.hasNext()) {
            b bVar = (b) it.next();
            if (XMPushService.a(bVar.e(), this.c)) {
                if (bVar.a() >= System.currentTimeMillis()) {
                    byte[] d = bVar.d();
                    if (d == null) {
                        com.xiaomi.channel.commonutils.logger.b.a("Geo canBeShownMessage content null");
                    } else {
                        Intent a = s.a(d, System.currentTimeMillis());
                        if (a == null) {
                            com.xiaomi.channel.commonutils.logger.b.a("Geo canBeShownMessage intent null");
                        } else {
                            s.a(this.d.a, null, d, a, true);
                            if (g.a(this.d.a).a(bVar.b()) == 0) {
                                com.xiaomi.channel.commonutils.logger.b.a("show some exit geofence message. then remove this message failed. message_id:" + bVar.b());
                            }
                        }
                    }
                } else if (g.a(this.a).a(bVar.b()) == 0) {
                    com.xiaomi.channel.commonutils.logger.b.a("XMPushService remove some geofence message failed. message_id:" + bVar.b());
                }
            }
        }
    }
}
