package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.misc.a;
import com.xiaomi.push.service.XMPushService.h;
import com.xiaomi.smack.b;

class br extends h {
    final /* synthetic */ int b;
    final /* synthetic */ byte[] c;
    final /* synthetic */ String d;
    final /* synthetic */ XMPushService e;

    br(XMPushService xMPushService, int i, int i2, byte[] bArr, String str) {
        this.e = xMPushService;
        this.b = i2;
        this.c = bArr;
        this.d = str;
        super(i);
    }

    public void a() {
        t.c(this.e);
        ap.a().a("5");
        a.a(this.b);
        this.e.d.c(b.b());
        this.e.a(this.c, this.d);
    }

    public String b() {
        return "clear account cache.";
    }
}
