package com.liulishuo.filedownloader.services;

import com.liulishuo.filedownloader.g.c.b;
import com.liulishuo.filedownloader.g.c.d;
import com.liulishuo.filedownloader.g.c.e;
import com.liulishuo.filedownloader.g.f;

public class c {
    private final a a;

    public static class a {
        com.liulishuo.filedownloader.g.c.c a;
        Integer b;
        e c;
        b d;
        com.liulishuo.filedownloader.g.c.a e;
        d f;

        public a a(com.liulishuo.filedownloader.g.c.c cVar) {
            this.a = cVar;
            return this;
        }

        public void a() {
        }

        public String toString() {
            return f.a("component: database[%s], maxNetworkCount[%s], outputStream[%s], connection[%s], connectionCountAdapter[%s]", this.a, this.b, this.c, this.d, this.e);
        }
    }

    public c() {
        this.a = null;
    }

    public c(a aVar) {
        this.a = aVar;
    }

    public int a() {
        if (this.a == null) {
            return h();
        }
        Integer num = this.a.b;
        if (num == null) {
            return h();
        }
        if (com.liulishuo.filedownloader.g.d.a) {
            com.liulishuo.filedownloader.g.d.c(this, "initial FileDownloader manager with the customize maxNetworkThreadCount: %d", num);
        }
        return com.liulishuo.filedownloader.g.e.a(num.intValue());
    }

    public com.liulishuo.filedownloader.b.a b() {
        if (this.a == null || this.a.a == null) {
            return i();
        }
        com.liulishuo.filedownloader.b.a a = this.a.a.a();
        if (a == null) {
            return i();
        }
        if (!com.liulishuo.filedownloader.g.d.a) {
            return a;
        }
        com.liulishuo.filedownloader.g.d.c(this, "initial FileDownloader manager with the customize database: %s", a);
        return a;
    }

    public e c() {
        if (this.a == null) {
            return j();
        }
        e eVar = this.a.c;
        if (eVar == null) {
            return j();
        }
        if (!com.liulishuo.filedownloader.g.d.a) {
            return eVar;
        }
        com.liulishuo.filedownloader.g.d.c(this, "initial FileDownloader manager with the customize output stream: %s", eVar);
        return eVar;
    }

    public b d() {
        if (this.a == null) {
            return k();
        }
        b bVar = this.a.d;
        if (bVar == null) {
            return k();
        }
        if (!com.liulishuo.filedownloader.g.d.a) {
            return bVar;
        }
        com.liulishuo.filedownloader.g.d.c(this, "initial FileDownloader manager with the customize connection creator: %s", bVar);
        return bVar;
    }

    public com.liulishuo.filedownloader.g.c.a e() {
        if (this.a == null) {
            return l();
        }
        com.liulishuo.filedownloader.g.c.a aVar = this.a.e;
        if (aVar == null) {
            return l();
        }
        if (!com.liulishuo.filedownloader.g.d.a) {
            return aVar;
        }
        com.liulishuo.filedownloader.g.d.c(this, "initial FileDownloader manager with the customize connection count adapter: %s", aVar);
        return aVar;
    }

    public d f() {
        if (this.a == null) {
            return g();
        }
        d dVar = this.a.f;
        if (dVar == null) {
            return g();
        }
        if (!com.liulishuo.filedownloader.g.d.a) {
            return dVar;
        }
        com.liulishuo.filedownloader.g.d.c(this, "initial FileDownloader manager with the customize id generator: %s", dVar);
        return dVar;
    }

    private d g() {
        return new b();
    }

    private int h() {
        return com.liulishuo.filedownloader.g.e.a().e;
    }

    private com.liulishuo.filedownloader.b.a i() {
        return new com.liulishuo.filedownloader.b.c();
    }

    private e j() {
        return new com.liulishuo.filedownloader.f.b.a();
    }

    private b k() {
        return new com.liulishuo.filedownloader.a.c.b();
    }

    private com.liulishuo.filedownloader.g.c.a l() {
        return new com.liulishuo.filedownloader.a.a();
    }
}
