package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.service.XMPushService.h;
import com.xiaomi.xmpush.thrift.ab;

final class bg extends h {
    final /* synthetic */ XMPushService a;
    final /* synthetic */ ab b;

    bg(int i, XMPushService xMPushService, ab abVar) {
        this.a = xMPushService;
        this.b = abVar;
        super(i);
    }

    public void a() {
        try {
            j.a(this.a, j.a(this.b.j(), this.b.h()));
        } catch (Exception e) {
            b.a((Throwable) e);
            this.a.a(10, e);
        }
    }

    public String b() {
        return "send app absent message.";
    }
}
