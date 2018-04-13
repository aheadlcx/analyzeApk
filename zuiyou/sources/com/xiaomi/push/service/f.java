package com.xiaomi.push.service;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.slim.b;
import com.xiaomi.smack.l;
import com.xiaomi.smack.packet.d;
import com.xiaomi.xmpush.thrift.af;
import com.xiaomi.xmpush.thrift.ai;
import com.xiaomi.xmpush.thrift.au;
import com.xiaomi.xmpush.thrift.x;
import java.nio.ByteBuffer;
import org.apache.thrift.a;

final class f {
    static b a(XMPushService xMPushService, byte[] bArr) {
        af afVar = new af();
        try {
            au.a((a) afVar, bArr);
            return a(t.a(xMPushService), (Context) xMPushService, afVar);
        } catch (Throwable e) {
            com.xiaomi.channel.commonutils.logger.b.a(e);
            return null;
        }
    }

    static b a(s sVar, Context context, af afVar) {
        try {
            b bVar = new b();
            bVar.a(5);
            bVar.c(sVar.a);
            bVar.b(a(afVar));
            bVar.a("SECMSG", "message");
            String str = sVar.a;
            afVar.g.b = str.substring(0, str.indexOf("@"));
            afVar.g.d = str.substring(str.indexOf("/") + 1);
            bVar.a(au.a(afVar), sVar.c);
            bVar.a((short) 1);
            com.xiaomi.channel.commonutils.logger.b.a("try send mi push message. packagename:" + afVar.f + " action:" + afVar.a);
            return bVar;
        } catch (Throwable e) {
            com.xiaomi.channel.commonutils.logger.b.a(e);
            return null;
        }
    }

    static af a(String str, String str2) {
        a aiVar = new ai();
        aiVar.b(str2);
        aiVar.c("package uninstalled");
        aiVar.a(d.j());
        aiVar.a(false);
        return a(str, str2, aiVar, com.xiaomi.xmpush.thrift.a.Notification);
    }

    static <T extends a<T, ?>> af a(String str, String str2, T t, com.xiaomi.xmpush.thrift.a aVar) {
        byte[] a = au.a(t);
        af afVar = new af();
        x xVar = new x();
        xVar.a = 5;
        xVar.b = "fakeid";
        afVar.a(xVar);
        afVar.a(ByteBuffer.wrap(a));
        afVar.a(aVar);
        afVar.c(true);
        afVar.b(str);
        afVar.a(false);
        afVar.a(str2);
        return afVar;
    }

    private static String a(af afVar) {
        if (!(afVar.h == null || afVar.h.k == null)) {
            String str = (String) afVar.h.k.get("ext_traffic_source_pkg");
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
        }
        return afVar.f;
    }

    static void a(XMPushService xMPushService) {
        if (t.a(xMPushService.getApplicationContext()) != null) {
            ap.b a = t.a(xMPushService.getApplicationContext()).a(xMPushService);
            a(xMPushService, a);
            ap.a().a(a);
        }
    }

    static void a(XMPushService xMPushService, ap.b bVar) {
        bVar.a(null);
        bVar.a(new ag(xMPushService));
    }

    static void a(XMPushService xMPushService, af afVar) {
        com.xiaomi.smack.a h = xMPushService.h();
        if (h == null) {
            throw new l("try send msg while connection is null.");
        } else if (h.b()) {
            b a = a(t.a(xMPushService), (Context) xMPushService, afVar);
            if (a != null) {
                h.b(a);
            }
        } else {
            throw new l("Don't support XMPP connection.");
        }
    }

    static void a(XMPushService xMPushService, String str, byte[] bArr) {
        com.xiaomi.smack.a h = xMPushService.h();
        if (h == null) {
            throw new l("try send msg while connection is null.");
        } else if (h.b()) {
            b a = a(xMPushService, bArr);
            if (a != null) {
                h.b(a);
            } else {
                w.a(xMPushService, str, bArr, 70000003, "not a valid message");
            }
        } else {
            throw new l("Don't support XMPP connection.");
        }
    }
}
