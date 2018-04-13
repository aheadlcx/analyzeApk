package com.xiaomi.push.service;

import com.xiaomi.push.service.XMPushService.h;

class ab extends h {
    final /* synthetic */ XMPushService a;

    ab(XMPushService xMPushService, int i) {
        this.a = xMPushService;
        super(i);
    }

    public void a() {
        if (this.a.i != null) {
            this.a.i.b(15, null);
            this.a.i = null;
        }
    }

    public String b() {
        return "disconnect for service destroy.";
    }
}
