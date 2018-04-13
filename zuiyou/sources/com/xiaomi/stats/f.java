package com.xiaomi.stats;

import com.xiaomi.channel.commonutils.network.d;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.az;
import com.xiaomi.push.thrift.b;
import com.xiaomi.push.thrift.c;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import org.apache.thrift.protocol.e;

public class f {
    private String a;
    private boolean b = false;
    private int c;
    private long d;
    private e e;
    private com.xiaomi.channel.commonutils.stats.a f = com.xiaomi.channel.commonutils.stats.a.a();

    static class a {
        static final f a = new f();
    }

    private b a(com.xiaomi.channel.commonutils.stats.a.a aVar) {
        if (aVar.a == 0) {
            return aVar.c instanceof b ? (b) aVar.c : null;
        } else {
            b f = f();
            f.a(com.xiaomi.push.thrift.a.CHANNEL_STATS_COUNTER.a());
            f.c(aVar.a);
            f.c(aVar.b);
            return f;
        }
    }

    public static f a() {
        return a.a;
    }

    private c b(int i) {
        List arrayList = new ArrayList();
        c cVar = new c(this.a, arrayList);
        if (!d.e(this.e.a)) {
            cVar.a(com.xiaomi.channel.commonutils.android.d.j(this.e.a));
        }
        org.apache.thrift.transport.d bVar = new org.apache.thrift.transport.b(i);
        e a = new org.apache.thrift.protocol.k.a().a(bVar);
        try {
            cVar.b(a);
        } catch (org.apache.thrift.f e) {
        }
        LinkedList c = this.f.c();
        while (c.size() > 0) {
            try {
                b a2 = a((com.xiaomi.channel.commonutils.stats.a.a) c.getLast());
                if (a2 != null) {
                    a2.b(a);
                }
                if (bVar.o_() > i) {
                    break;
                }
                if (a2 != null) {
                    arrayList.add(a2);
                }
                c.removeLast();
            } catch (NoSuchElementException e2) {
            } catch (org.apache.thrift.f e3) {
            }
        }
        return cVar;
    }

    public static e b() {
        e eVar;
        synchronized (a.a) {
            eVar = a.a.e;
        }
        return eVar;
    }

    private void g() {
        if (this.b && System.currentTimeMillis() - this.d > ((long) this.c)) {
            this.b = false;
            this.d = 0;
        }
    }

    public void a(int i) {
        int i2 = 604800000;
        if (i > 0) {
            int i3 = i * 1000;
            if (i3 <= 604800000) {
                i2 = i3;
            }
            if (this.c != i2 || !this.b) {
                this.b = true;
                this.d = System.currentTimeMillis();
                this.c = i2;
                com.xiaomi.channel.commonutils.logger.b.c("enable dot duration = " + i2 + " start = " + this.d);
            }
        }
    }

    public synchronized void a(XMPushService xMPushService) {
        this.e = new e(xMPushService);
        this.a = "";
        az.a().a(new g(this));
    }

    synchronized void a(b bVar) {
        this.f.a(bVar);
    }

    public boolean c() {
        return this.b;
    }

    boolean d() {
        g();
        return this.b && this.f.b() > 0;
    }

    synchronized c e() {
        c cVar;
        cVar = null;
        if (d()) {
            int i = 750;
            if (!d.e(this.e.a)) {
                i = 375;
            }
            cVar = b(i);
        }
        return cVar;
    }

    synchronized b f() {
        b bVar;
        bVar = new b();
        bVar.a(d.k(this.e.a));
        bVar.a = (byte) 0;
        bVar.c = 1;
        bVar.d((int) (System.currentTimeMillis() / 1000));
        if (this.e.b != null) {
            bVar.e(this.e.b.g());
        }
        return bVar;
    }
}
