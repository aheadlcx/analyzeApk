package com.xiaomi.push.service;

import com.xiaomi.push.service.ap.b.a;
import com.xiaomi.push.service.ap.c;

final class ag implements a {
    final /* synthetic */ XMPushService a;

    ag(XMPushService xMPushService) {
        this.a = xMPushService;
    }

    public void a(c cVar, c cVar2, int i) {
        if (cVar2 == c.binded) {
            w.a(this.a);
            w.b(this.a);
        } else if (cVar2 == c.unbind) {
            w.a(this.a, 70000001, " the push is not connected.");
        }
    }
}
