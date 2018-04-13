package com.xiaomi.slim;

import android.text.TextUtils;
import com.google.protobuf.micro.a;
import com.google.protobuf.micro.d;
import com.xiaomi.push.protobuf.b.j;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.ak;
import com.xiaomi.smack.b;
import com.xiaomi.smack.h;
import com.xiaomi.smack.l;
import com.xiaomi.smack.util.g;

public class f extends h {
    private Thread v;
    private c w;
    private d x;

    public f(XMPushService xMPushService, b bVar) {
        super(xMPushService, bVar);
    }

    private b c(boolean z) {
        b bVar = new b();
        bVar.a("PING", null);
        if (z) {
            bVar.a("1");
        } else {
            bVar.a("0");
        }
        j jVar = new j();
        byte[] a = c().a();
        if (a != null) {
            try {
                jVar.a(com.xiaomi.push.protobuf.b.b.b(a));
            } catch (d e) {
            }
        }
        a = com.xiaomi.stats.h.c();
        if (a != null) {
            jVar.a(a.a(a));
        }
        bVar.a(jVar.c(), null);
        return bVar;
    }

    private void v() {
        try {
            this.w = new c(this.p.getInputStream(), this);
            this.x = new d(this.p.getOutputStream(), this);
            this.v = new g(this, "Blob Reader (" + this.l + ")");
            this.v.start();
        } catch (Throwable e) {
            throw new l("Error to init reader and writer", e);
        }
    }

    protected synchronized void a(int i, Exception exception) {
        if (this.w != null) {
            this.w.b();
            this.w = null;
        }
        if (this.x != null) {
            try {
                this.x.b();
            } catch (Throwable e) {
                com.xiaomi.channel.commonutils.logger.b.a(e);
            }
            this.x = null;
        }
        super.a(i, exception);
    }

    public synchronized void a(ak.b bVar) {
        a.a(bVar, q(), this);
    }

    void a(b bVar) {
        if (bVar != null) {
            if (bVar.d()) {
                com.xiaomi.channel.commonutils.logger.b.a("[Slim] RCV blob chid=" + bVar.c() + "; id=" + bVar.h() + "; errCode=" + bVar.e() + "; err=" + bVar.f());
            }
            if (bVar.c() == 0) {
                if ("PING".equals(bVar.a())) {
                    com.xiaomi.channel.commonutils.logger.b.a("[Slim] RCV ping id=" + bVar.h());
                    u();
                } else if ("CLOSE".equals(bVar.a())) {
                    c(13, null);
                }
            }
            for (com.xiaomi.smack.a.a a : this.g.values()) {
                a.a(bVar);
            }
        }
    }

    @Deprecated
    public void a(com.xiaomi.smack.packet.d dVar) {
        b(b.a(dVar, null));
    }

    public synchronized void a(String str, String str2) {
        a.a(str, str2, this);
    }

    protected void a(boolean z) {
        if (this.x != null) {
            b c = c(z);
            com.xiaomi.channel.commonutils.logger.b.a("[Slim] SND ping id=" + c.h());
            b(c);
            t();
            return;
        }
        throw new l("The BlobWriter is null.");
    }

    public void a(b[] bVarArr) {
        for (b b : bVarArr) {
            b(b);
        }
    }

    public void a(com.xiaomi.smack.packet.d[] dVarArr) {
        for (com.xiaomi.smack.packet.d a : dVarArr) {
            a(a);
        }
    }

    public boolean a() {
        return true;
    }

    protected synchronized void b() {
        v();
        this.x.a();
    }

    public void b(b bVar) {
        if (this.x != null) {
            try {
                int a = this.x.a(bVar);
                Object i = bVar.i();
                if (!TextUtils.isEmpty(i)) {
                    g.a(this.n, i, (long) a, false, System.currentTimeMillis());
                }
                for (com.xiaomi.smack.a.a a2 : this.h.values()) {
                    a2.a(bVar);
                }
                return;
            } catch (Throwable e) {
                throw new l(e);
            }
        }
        throw new l("the writer is null.");
    }

    void b(com.xiaomi.smack.packet.d dVar) {
        if (dVar != null) {
            for (com.xiaomi.smack.a.a a : this.g.values()) {
                a.a(dVar);
            }
        }
    }
}
