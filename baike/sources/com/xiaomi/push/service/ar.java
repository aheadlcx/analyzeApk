package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.network.d;
import com.xiaomi.push.service.XMPushService.h;

class ar extends h {
    final /* synthetic */ XMPushService a;

    ar(XMPushService xMPushService, int i) {
        this.a = xMPushService;
        super(i);
    }

    public void a() {
        j.a(this.a);
        if (d.d(this.a)) {
            this.a.a(true);
        }
    }

    public String b() {
        return "prepare the mi push account.";
    }
}
