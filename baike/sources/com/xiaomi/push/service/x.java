package com.xiaomi.push.service;

import com.xiaomi.push.service.XMPushService.h;
import com.xiaomi.slim.b;
import com.xiaomi.smack.packet.d;

class x extends h {
    private XMPushService a = null;
    private d b;
    private b c;

    public x(XMPushService xMPushService, b bVar) {
        super(4);
        this.a = xMPushService;
        this.c = bVar;
    }

    public x(XMPushService xMPushService, d dVar) {
        super(4);
        this.a = xMPushService;
        this.b = dVar;
    }

    public void a() {
        try {
            if (this.b != null) {
                this.a.a(this.b);
            } else {
                this.a.a(this.c);
            }
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.logger.b.a((Throwable) e);
            this.a.a(10, e);
        }
    }

    public String b() {
        return "send a message.";
    }
}
