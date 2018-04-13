package com.xiaomi.stats;

import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.ap.b;
import com.xiaomi.push.service.ap.c;

class a implements com.xiaomi.push.service.ap.b.a {
    private XMPushService a;
    private b b;
    private com.xiaomi.smack.a c;
    private c d;
    private int e;
    private boolean f = false;

    a(XMPushService xMPushService, b bVar) {
        this.a = xMPushService;
        this.d = c.binding;
        this.b = bVar;
    }

    private void b() {
        this.b.b((com.xiaomi.push.service.ap.b.a) this);
    }

    private void c() {
        b();
        if (this.f && this.e != 11) {
            com.xiaomi.push.thrift.b f = f.a().f();
            switch (c.a[this.d.ordinal()]) {
                case 1:
                    if (this.e != 17) {
                        if (this.e != 21) {
                            try {
                                a c = d.c(f.b().a());
                                f.b = c.a.a();
                                f.c(c.b);
                                break;
                            } catch (NullPointerException e) {
                                f = null;
                                break;
                            }
                        }
                        f.b = com.xiaomi.push.thrift.a.BIND_TIMEOUT.a();
                        break;
                    }
                    f.b = com.xiaomi.push.thrift.a.BIND_TCP_READ_TIMEOUT.a();
                    break;
                case 3:
                    f.b = com.xiaomi.push.thrift.a.BIND_SUCCESS.a();
                    break;
            }
            if (f != null) {
                f.b(this.c.e());
                f.d(this.b.b);
                f.c = 1;
                try {
                    f.a((byte) Integer.parseInt(this.b.h));
                } catch (NumberFormatException e2) {
                }
                f.a().a(f);
            }
        }
    }

    void a() {
        this.b.a((com.xiaomi.push.service.ap.b.a) this);
        this.c = this.a.h();
    }

    public void a(c cVar, c cVar2, int i) {
        if (!this.f && cVar == c.binding) {
            this.d = cVar2;
            this.e = i;
            this.f = true;
        }
        this.a.a(new b(this, 4));
    }
}
