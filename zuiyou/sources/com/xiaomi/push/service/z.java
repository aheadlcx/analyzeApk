package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.service.XMPushService.h;
import com.xiaomi.xmpush.thrift.af;

final class z extends h {
    final /* synthetic */ XMPushService b;
    final /* synthetic */ af c;

    z(int i, XMPushService xMPushService, af afVar) {
        this.b = xMPushService;
        this.c = afVar;
        super(i);
    }

    public void a() {
        try {
            f.a(this.b, an.a(this.b, this.c));
        } catch (Exception e) {
            b.a((Throwable) e);
            this.b.a(10, e);
        }
    }

    public String b() {
        return "send ack message for message.";
    }
}
