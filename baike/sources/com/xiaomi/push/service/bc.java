package com.xiaomi.push.service;

import com.xiaomi.push.service.XMPushService.h;

class bc extends h {
    final /* synthetic */ XMPushService a;

    bc(XMPushService xMPushService, int i) {
        this.a = xMPushService;
        super(i);
    }

    public void a() {
        if (this.a.i != null) {
            this.a.i.h();
            this.a.i = null;
        }
    }

    public String b() {
        return "disconnect for disable push";
    }
}
