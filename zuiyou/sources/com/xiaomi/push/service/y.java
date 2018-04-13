package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.service.XMPushService.h;
import com.xiaomi.xmpush.thrift.af;

final class y extends h {
    final /* synthetic */ XMPushService b;
    final /* synthetic */ af c;

    y(int i, XMPushService xMPushService, af afVar) {
        this.b = xMPushService;
        this.c = afVar;
        super(i);
    }

    public void a() {
        try {
            f.a(this.b, f.a(this.c.j(), this.c.h()));
        } catch (Exception e) {
            b.a((Throwable) e);
            this.b.a(10, e);
        }
    }

    public String b() {
        return "send app absent message.";
    }
}
