package okhttp3.internal.a;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import okhttp3.aa;
import okhttp3.internal.a;
import okhttp3.internal.b.d;
import okhttp3.internal.b.e;
import okhttp3.s;
import okhttp3.s$a;
import okhttp3.y;

public class c$a {
    final long a;
    final y b;
    final aa c;
    private Date d;
    private String e;
    private Date f;
    private String g;
    private Date h;
    private long i;
    private long j;
    private String k;
    private int l = -1;

    public c$a(long j, y yVar, aa aaVar) {
        this.a = j;
        this.b = yVar;
        this.c = aaVar;
        if (aaVar != null) {
            this.i = aaVar.l();
            this.j = aaVar.m();
            s f = aaVar.f();
            int a = f.a();
            for (int i = 0; i < a; i++) {
                String a2 = f.a(i);
                String b = f.b(i);
                if ("Date".equalsIgnoreCase(a2)) {
                    this.d = d.a(b);
                    this.e = b;
                } else if ("Expires".equalsIgnoreCase(a2)) {
                    this.h = d.a(b);
                } else if ("Last-Modified".equalsIgnoreCase(a2)) {
                    this.f = d.a(b);
                    this.g = b;
                } else if ("ETag".equalsIgnoreCase(a2)) {
                    this.k = b;
                } else if ("Age".equalsIgnoreCase(a2)) {
                    this.l = e.b(b, -1);
                }
            }
        }
    }

    public c a() {
        c b = b();
        if (b.a == null || !this.b.g().i()) {
            return b;
        }
        return new c(null, null);
    }

    private c b() {
        long j = 0;
        if (this.c == null) {
            return new c(this.b, null);
        }
        if (this.b.h() && this.c.e() == null) {
            return new c(this.b, null);
        }
        if (!c.a(this.c, this.b)) {
            return new c(this.b, null);
        }
        okhttp3.d g = this.b.g();
        if (g.a() || a(this.b)) {
            return new c(this.b, null);
        }
        long toMillis;
        long d = d();
        long c = c();
        if (g.c() != -1) {
            c = Math.min(c, TimeUnit.SECONDS.toMillis((long) g.c()));
        }
        if (g.h() != -1) {
            toMillis = TimeUnit.SECONDS.toMillis((long) g.h());
        } else {
            toMillis = 0;
        }
        okhttp3.d k = this.c.k();
        if (!(k.f() || g.g() == -1)) {
            j = TimeUnit.SECONDS.toMillis((long) g.g());
        }
        if (k.a() || d + toMillis >= r4 + c) {
            String str;
            String str2;
            if (this.k != null) {
                str = "If-None-Match";
                str2 = this.k;
            } else if (this.f != null) {
                str = "If-Modified-Since";
                str2 = this.g;
            } else if (this.d == null) {
                return new c(this.b, null);
            } else {
                str = "If-Modified-Since";
                str2 = this.e;
            }
            s$a b = this.b.c().b();
            a.a.a(b, str, str2);
            return new c(this.b.f().a(b.a()).d(), this.c);
        }
        aa.a h = this.c.h();
        if (toMillis + d >= c) {
            h.a("Warning", "110 HttpURLConnection \"Response is stale\"");
        }
        if (d > com.umeng.analytics.a.i && e()) {
            h.a("Warning", "113 HttpURLConnection \"Heuristic expiration\"");
        }
        return new c(null, h.a());
    }

    private long c() {
        okhttp3.d k = this.c.k();
        if (k.c() != -1) {
            return TimeUnit.SECONDS.toMillis((long) k.c());
        }
        long time;
        if (this.h != null) {
            if (this.d != null) {
                time = this.d.getTime();
            } else {
                time = this.j;
            }
            time = this.h.getTime() - time;
            if (time <= 0) {
                time = 0;
            }
            return time;
        } else if (this.f == null || this.c.a().a().l() != null) {
            return 0;
        } else {
            if (this.d != null) {
                time = this.d.getTime();
            } else {
                time = this.i;
            }
            time -= this.f.getTime();
            if (time > 0) {
                return time / 10;
            }
            return 0;
        }
    }

    private long d() {
        long j = 0;
        if (this.d != null) {
            j = Math.max(0, this.j - this.d.getTime());
        }
        if (this.l != -1) {
            j = Math.max(j, TimeUnit.SECONDS.toMillis((long) this.l));
        }
        return (j + (this.j - this.i)) + (this.a - this.j);
    }

    private boolean e() {
        return this.c.k().c() == -1 && this.h == null;
    }

    private static boolean a(y yVar) {
        return (yVar.a("If-Modified-Since") == null && yVar.a("If-None-Match") == null) ? false : true;
    }
}
