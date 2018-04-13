package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.service.XMPushService.h;
import com.xiaomi.xmpush.thrift.af;

final class ac extends h {
    final /* synthetic */ XMPushService b;
    final /* synthetic */ af c;
    final /* synthetic */ String d;

    ac(int i, XMPushService xMPushService, af afVar, String str) {
        this.b = xMPushService;
        this.c = afVar;
        this.d = str;
        super(i);
    }

    public void a() {
        try {
            af a = an.a(this.b, this.c);
            a.m().a("absent_target_package", this.d);
            f.a(this.b, a);
        } catch (Exception e) {
            b.a((Throwable) e);
            this.b.a(10, e);
        }
    }

    public String b() {
        return "send app absent ack message for message.";
    }
}
