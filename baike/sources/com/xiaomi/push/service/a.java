package com.xiaomi.push.service;

import com.xiaomi.push.service.XMPushService.h;
import com.xiaomi.slim.b;
import com.xiaomi.smack.packet.c;

class a extends h {
    private XMPushService a = null;
    private c[] b;
    private b[] c;

    public a(XMPushService xMPushService, b[] bVarArr) {
        super(4);
        this.a = xMPushService;
        this.c = bVarArr;
    }

    public a(XMPushService xMPushService, c[] cVarArr) {
        super(4);
        this.a = xMPushService;
        this.b = cVarArr;
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
        return "batch send message.";
    }
}
