package com.xiaomi.smack;

import com.xiaomi.push.service.XMPushService.h;

class j extends h {
    final /* synthetic */ int a;
    final /* synthetic */ Exception b;
    final /* synthetic */ h c;

    j(h hVar, int i, int i2, Exception exception) {
        this.c = hVar;
        this.a = i2;
        this.b = exception;
        super(i);
    }

    public void a() {
        this.c.r.a(this.a, this.b);
    }

    public String b() {
        return "shutdown the connection. " + this.a + ", " + this.b;
    }
}
