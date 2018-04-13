package com.xiaomi.smack;

import com.xiaomi.push.service.XMPushService.h;

class i extends h {
    final /* synthetic */ long a;
    final /* synthetic */ h b;

    i(h hVar, int i, long j) {
        this.b = hVar;
        this.a = j;
        super(i);
    }

    public void a() {
        Thread.yield();
        if (this.b.k() && !this.b.a(this.a)) {
            this.b.r.a(22, null);
            this.b.r.a(true);
        }
    }

    public String b() {
        return "check the ping-pong." + this.a;
    }
}
