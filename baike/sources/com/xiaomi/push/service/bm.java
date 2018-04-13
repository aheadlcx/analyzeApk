package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.service.XMPushService.h;
import com.xiaomi.xmpush.thrift.ab;

final class bm extends h {
    final /* synthetic */ XMPushService a;
    final /* synthetic */ ab b;
    final /* synthetic */ boolean c;
    final /* synthetic */ boolean d;
    final /* synthetic */ boolean e;

    bm(int i, XMPushService xMPushService, ab abVar, boolean z, boolean z2, boolean z3) {
        this.a = xMPushService;
        this.b = abVar;
        this.c = z;
        this.d = z2;
        this.e = z3;
        super(i);
    }

    public void a() {
        try {
            j.a(this.a, s.a(this.a, this.b, this.c, this.d, this.e));
        } catch (Exception e) {
            b.a((Throwable) e);
            this.a.a(10, e);
        }
    }

    public String b() {
        return "send wrong message ack for message.";
    }
}
