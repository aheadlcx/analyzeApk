package com.xiaomi.mipush.sdk;

import android.text.TextUtils;
import com.xiaomi.channel.commonutils.android.d;
import com.xiaomi.xmpush.thrift.ai;
import java.util.HashMap;
import org.apache.thrift.a;

final class ap implements Runnable {
    ap() {
    }

    public void run() {
        if (d.c(MiPushClient.access$000()) != null) {
            a aiVar = new ai();
            aiVar.b(c.a(MiPushClient.access$000()).c());
            aiVar.c("client_info_update");
            aiVar.a(MiPushClient.generatePacketID());
            aiVar.a(new HashMap());
            Object a = com.xiaomi.channel.commonutils.string.d.a(d.c(MiPushClient.access$000()));
            Object e = d.e(MiPushClient.access$000());
            if (!TextUtils.isEmpty(e)) {
                a = a + "," + e;
            }
            aiVar.i().put("imei_md5", a);
            int b = d.b();
            if (b >= 0) {
                aiVar.i().put("space_id", Integer.toString(b));
            }
            ac.a(MiPushClient.access$000()).a(aiVar, com.xiaomi.xmpush.thrift.a.Notification, false, null);
        }
    }
}
