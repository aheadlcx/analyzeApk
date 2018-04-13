package okhttp3.internal.a;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import okhttp3.aa;
import okhttp3.internal.b.d;
import okhttp3.internal.b.e;
import okhttp3.r;
import okhttp3.y;

public final class c {
    public final y a;
    public final aa b;

    public static class a {
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

        public a(long j, y yVar, aa aaVar) {
            this.a = j;
            this.b = yVar;
            this.c = aaVar;
            if (aaVar != null) {
                this.i = aaVar.l();
                this.j = aaVar.m();
                r g = aaVar.g();
                int a = g.a();
                for (int i = 0; i < a; i++) {
                    String a2 = g.a(i);
                    String b = g.b(i);
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
            if (b.a == null || !this.b.f().i()) {
                return b;
            }
            return new c(null, null);
        }

        private c b() {
            long j = 0;
            if (this.c == null) {
                return new c(this.b, null);
            }
            if (this.b.g() && this.c.f() == null) {
                return new c(this.b, null);
            }
            if (!c.a(this.c, this.b)) {
                return new c(this.b, null);
            }
            okhttp3.d f = this.b.f();
            if (f.a() || a(this.b)) {
                return new c(this.b, null);
            }
            long toMillis;
            long d = d();
            long c = c();
            if (f.c() != -1) {
                c = Math.min(c, TimeUnit.SECONDS.toMillis((long) f.c()));
            }
            if (f.h() != -1) {
                toMillis = TimeUnit.SECONDS.toMillis((long) f.h());
            } else {
                toMillis = 0;
            }
            okhttp3.d k = this.c.k();
            if (!(k.f() || f.g() == -1)) {
                j = TimeUnit.SECONDS.toMillis((long) f.g());
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
                okhttp3.r.a b = this.b.c().b();
                okhttp3.internal.a.a.a(b, str, str2);
                return new c(this.b.e().a(b.a()).b(), this.c);
            }
            okhttp3.aa.a i = this.c.i();
            if (toMillis + d >= c) {
                i.b("Warning", "110 HttpURLConnection \"Response is stale\"");
            }
            if (d > com.umeng.analytics.a.i && e()) {
                i.b("Warning", "113 HttpURLConnection \"Heuristic expiration\"");
            }
            return new c(null, i.a());
        }

        private long c() {
            okhttp3.d k = this.c.k();
            if (k.c() != -1) {
                return TimeUnit.SECONDS.toMillis((long) k.c());
            }
            long time;
            if (this.h != null) {
                time = this.h.getTime() - (this.d != null ? this.d.getTime() : this.j);
                if (time <= 0) {
                    time = 0;
                }
                return time;
            } else if (this.f == null || this.c.a().a().l() != null) {
                return 0;
            } else {
                time = (this.d != null ? this.d.getTime() : this.i) - this.f.getTime();
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

    c(y yVar, aa aaVar) {
        this.a = yVar;
        this.b = aaVar;
    }

    public static boolean a(aa aaVar, y yVar) {
        switch (aaVar.c()) {
            case 200:
            case 203:
            case 204:
            case 300:
            case 301:
            case 308:
            case 404:
            case 405:
            case 410:
            case 414:
            case 501:
                break;
            case 302:
            case 307:
                if (aaVar.a("Expires") == null && aaVar.k().c() == -1 && !aaVar.k().e() && !aaVar.k().d()) {
                    return false;
                }
            default:
                return false;
        }
        return (aaVar.k().b() || yVar.f().b()) ? false : true;
    }
}
