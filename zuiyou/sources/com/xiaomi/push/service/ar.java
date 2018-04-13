package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.service.XMPushService.h;

class ar extends h {
    final /* synthetic */ c b;

    ar(c cVar, int i) {
        this.b = cVar;
        super(i);
    }

    public void a() {
        if (this.b.b == this.b.a.t) {
            b.b("clean peer, chid = " + this.b.a.h);
            this.b.a.t = null;
        }
    }

    public String b() {
        return "clear peer job";
    }
}
