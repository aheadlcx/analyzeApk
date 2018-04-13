package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.service.XMPushService.h;
import com.xiaomi.xmpush.thrift.ab;

final class bk extends h {
    final /* synthetic */ XMPushService a;
    final /* synthetic */ ab b;
    final /* synthetic */ String c;

    bk(int i, XMPushService xMPushService, ab abVar, String str) {
        this.a = xMPushService;
        this.b = abVar;
        this.c = str;
        super(i);
    }

    public void a() {
        try {
            ab a = s.a(this.a, this.b);
            a.m().a("absent_target_package", this.c);
            j.a(this.a, a);
        } catch (Exception e) {
            b.a((Throwable) e);
            this.a.a(10, e);
        }
    }

    public String b() {
        return "send app absent ack message for message.";
    }
}
