package com.xiaomi.push.service;

import com.umeng.analytics.pro.b;
import com.xiaomi.push.service.XMPushService.h;
import com.xiaomi.xmpush.thrift.ab;

final class bl extends h {
    final /* synthetic */ XMPushService a;
    final /* synthetic */ ab b;
    final /* synthetic */ String c;
    final /* synthetic */ String d;

    bl(int i, XMPushService xMPushService, ab abVar, String str, String str2) {
        this.a = xMPushService;
        this.b = abVar;
        this.c = str;
        this.d = str2;
        super(i);
    }

    public void a() {
        try {
            ab a = s.a(this.a, this.b);
            a.h.a(b.J, this.c);
            a.h.a("reason", this.d);
            j.a(this.a, a);
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.logger.b.a((Throwable) e);
            this.a.a(10, e);
        }
    }

    public String b() {
        return "send wrong message ack for message.";
    }
}
