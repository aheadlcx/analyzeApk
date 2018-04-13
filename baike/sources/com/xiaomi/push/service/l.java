package com.xiaomi.push.service;

import com.xiaomi.mipush.sdk.ErrorCode;
import com.xiaomi.push.service.ak.b.a;
import com.xiaomi.push.service.ak.c;

final class l implements a {
    final /* synthetic */ XMPushService a;

    l(XMPushService xMPushService) {
        this.a = xMPushService;
    }

    public void a(c cVar, c cVar2, int i) {
        if (cVar2 == c.binded) {
            r.a(this.a);
            r.b(this.a);
        } else if (cVar2 == c.unbind) {
            r.a(this.a, ErrorCode.ERROR_SERVICE_UNAVAILABLE, " the push is not connected.");
        }
    }
}
