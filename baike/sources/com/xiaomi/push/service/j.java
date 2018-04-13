package com.xiaomi.push.service;

import android.content.Context;
import com.xiaomi.mipush.sdk.ErrorCode;
import com.xiaomi.slim.b;
import com.xiaomi.smack.l;
import com.xiaomi.smack.packet.d;
import com.xiaomi.xmpush.thrift.ab;
import com.xiaomi.xmpush.thrift.ae;
import com.xiaomi.xmpush.thrift.aq;
import com.xiaomi.xmpush.thrift.u;
import java.nio.ByteBuffer;
import org.apache.thrift.a;
import org.eclipse.paho.client.mqttv3.MqttTopic;

final class j {
    static b a(XMPushService xMPushService, byte[] bArr) {
        ab abVar = new ab();
        try {
            aq.a((a) abVar, bArr);
            return a(o.a(xMPushService), (Context) xMPushService, abVar);
        } catch (Throwable e) {
            com.xiaomi.channel.commonutils.logger.b.a(e);
            return null;
        }
    }

    static b a(n nVar, Context context, ab abVar) {
        try {
            b bVar = new b();
            bVar.a(5);
            bVar.c(nVar.a);
            bVar.b(abVar.f);
            bVar.a("SECMSG", "message");
            String str = nVar.a;
            abVar.g.b = str.substring(0, str.indexOf("@"));
            abVar.g.d = str.substring(str.indexOf(MqttTopic.TOPIC_LEVEL_SEPARATOR) + 1);
            bVar.a(aq.a(abVar), nVar.c);
            bVar.a((short) 1);
            com.xiaomi.channel.commonutils.logger.b.a("try send mi push message. packagename:" + abVar.f + " action:" + abVar.a);
            return bVar;
        } catch (Throwable e) {
            com.xiaomi.channel.commonutils.logger.b.a(e);
            return null;
        }
    }

    static ab a(String str, String str2) {
        a aeVar = new ae();
        aeVar.b(str2);
        aeVar.c("package uninstalled");
        aeVar.a(d.j());
        aeVar.a(false);
        return a(str, str2, aeVar, com.xiaomi.xmpush.thrift.a.Notification);
    }

    static <T extends a<T, ?>> ab a(String str, String str2, T t, com.xiaomi.xmpush.thrift.a aVar) {
        byte[] a = aq.a(t);
        ab abVar = new ab();
        u uVar = new u();
        uVar.a = 5;
        uVar.b = "fakeid";
        abVar.a(uVar);
        abVar.a(ByteBuffer.wrap(a));
        abVar.a(aVar);
        abVar.c(true);
        abVar.b(str);
        abVar.a(false);
        abVar.a(str2);
        return abVar;
    }

    static void a(XMPushService xMPushService) {
        if (o.a(xMPushService.getApplicationContext()) != null) {
            ak.b a = o.a(xMPushService.getApplicationContext()).a(xMPushService);
            a(xMPushService, a);
            ak.a().a(a);
        }
    }

    static void a(XMPushService xMPushService, ak.b bVar) {
        bVar.a(new l(xMPushService));
    }

    static void a(XMPushService xMPushService, ab abVar) {
        com.xiaomi.smack.a h = xMPushService.h();
        if (h == null) {
            throw new l("try send msg while connection is null.");
        } else if (h.a()) {
            b a = a(o.a(xMPushService), (Context) xMPushService, abVar);
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
        } else if (h.a()) {
            b a = a(xMPushService, bArr);
            if (a != null) {
                h.b(a);
            } else {
                r.a(xMPushService, str, bArr, ErrorCode.ERROR_INVALID_PAYLOAD, "not a valid message");
            }
        } else {
            throw new l("Don't support XMPP connection.");
        }
    }
}
