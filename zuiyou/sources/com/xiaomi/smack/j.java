package com.xiaomi.smack;

import com.xiaomi.push.service.XMPushService.h;

class j extends h {
    final /* synthetic */ int b;
    final /* synthetic */ Exception c;
    final /* synthetic */ h d;

    j(h hVar, int i, int i2, Exception exception) {
        this.d = hVar;
        this.b = i2;
        this.c = exception;
        super(i);
    }

    public void a() {
        this.d.s.a(this.b, this.c);
    }

    public String b() {
        return "shutdown the connection. " + this.b + ", " + this.c;
    }
}
