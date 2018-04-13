package okhttp3.internal.a;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Protocol;
import okhttp3.aa;
import okhttp3.internal.b.e;
import okhttp3.internal.b.f;
import okhttp3.internal.b.h;
import okhttp3.internal.c;
import okhttp3.t;
import okhttp3.y;
import okio.d;
import okio.k;
import okio.q;
import okio.r;
import okio.s;

public final class a implements t {
    final f a;

    public a(f fVar) {
        this.a = fVar;
    }

    public aa intercept(okhttp3.t.a aVar) throws IOException {
        aa aaVar = null;
        aa a = this.a != null ? this.a.a(aVar.a()) : aaVar;
        c a2 = new okhttp3.internal.a.c.a(System.currentTimeMillis(), aVar.a(), a).a();
        y yVar = a2.a;
        aa aaVar2 = a2.b;
        if (this.a != null) {
            this.a.a(a2);
        }
        if (a != null && aaVar2 == null) {
            c.a(a.h());
        }
        if (yVar == null && aaVar2 == null) {
            return new okhttp3.aa.a().a(aVar.a()).a(Protocol.HTTP_1_1).a(504).a("Unsatisfiable Request (only-if-cached)").a(c.c).a(-1).b(System.currentTimeMillis()).a();
        }
        if (yVar == null) {
            return aaVar2.i().b(a(aaVar2)).a();
        }
        try {
            aaVar = aVar.a(yVar);
            if (aaVar2 != null) {
                if (aaVar.c() == 304) {
                    a = aaVar2.i().a(a(aaVar2.g(), aaVar.g())).a(aaVar.l()).b(aaVar.m()).b(a(aaVar2)).a(a(aaVar)).a();
                    aaVar.h().close();
                    this.a.a();
                    this.a.a(aaVar2, a);
                    return a;
                }
                c.a(aaVar2.h());
            }
            a = aaVar.i().b(a(aaVar2)).a(a(aaVar)).a();
            if (e.d(a)) {
                return a(a(a, aaVar.a(), this.a), a);
            }
            return a;
        } finally {
            if (aaVar == null && a != null) {
                c.a(a.h());
            }
        }
    }

    private static aa a(aa aaVar) {
        if (aaVar == null || aaVar.h() == null) {
            return aaVar;
        }
        return aaVar.i().a(null).a();
    }

    private b a(aa aaVar, y yVar, f fVar) throws IOException {
        if (fVar == null) {
            return null;
        }
        if (c.a(aaVar, yVar)) {
            return fVar.a(aaVar);
        }
        if (!f.a(yVar.b())) {
            return null;
        }
        try {
            fVar.b(yVar);
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    private aa a(final b bVar, aa aaVar) throws IOException {
        if (bVar == null) {
            return aaVar;
        }
        q b = bVar.b();
        if (b == null) {
            return aaVar;
        }
        final okio.e c = aaVar.h().c();
        final d a = k.a(b);
        return aaVar.i().a(new h(aaVar.g(), k.a(new r(this) {
            boolean a;
            final /* synthetic */ a e;

            public long a(okio.c cVar, long j) throws IOException {
                try {
                    long a = c.a(cVar, j);
                    if (a == -1) {
                        if (!this.a) {
                            this.a = true;
                            a.close();
                        }
                        return -1;
                    }
                    cVar.a(a.c(), cVar.b() - a, a);
                    a.w();
                    return a;
                } catch (IOException e) {
                    if (!this.a) {
                        this.a = true;
                        bVar.a();
                    }
                    throw e;
                }
            }

            public s a() {
                return c.a();
            }

            public void close() throws IOException {
                if (!(this.a || c.a((r) this, 100, TimeUnit.MILLISECONDS))) {
                    this.a = true;
                    bVar.a();
                }
                c.close();
            }
        }))).a();
    }

    private static okhttp3.r a(okhttp3.r rVar, okhttp3.r rVar2) {
        int i;
        int i2 = 0;
        okhttp3.r.a aVar = new okhttp3.r.a();
        int a = rVar.a();
        for (i = 0; i < a; i++) {
            String a2 = rVar.a(i);
            String b = rVar.b(i);
            if (!("Warning".equalsIgnoreCase(a2) && b.startsWith("1")) && (!a(a2) || rVar2.a(a2) == null)) {
                okhttp3.internal.a.a.a(aVar, a2, b);
            }
        }
        i = rVar2.a();
        while (i2 < i) {
            String a3 = rVar2.a(i2);
            if (!"Content-Length".equalsIgnoreCase(a3) && a(a3)) {
                okhttp3.internal.a.a.a(aVar, a3, rVar2.b(i2));
            }
            i2++;
        }
        return aVar.a();
    }

    static boolean a(String str) {
        if ("Connection".equalsIgnoreCase(str) || "Keep-Alive".equalsIgnoreCase(str) || "Proxy-Authenticate".equalsIgnoreCase(str) || "Proxy-Authorization".equalsIgnoreCase(str) || "TE".equalsIgnoreCase(str) || "Trailers".equalsIgnoreCase(str) || "Transfer-Encoding".equalsIgnoreCase(str) || "Upgrade".equalsIgnoreCase(str)) {
            return false;
        }
        return true;
    }
}
