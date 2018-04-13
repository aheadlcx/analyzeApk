package okhttp3;

import java.util.List;
import okhttp3.internal.b.f;

public final class y {
    final s a;
    final String b;
    final r c;
    final z d;
    final Object e;
    private volatile d f;

    public static class a {
        s a;
        String b;
        okhttp3.r.a c;
        z d;
        Object e;

        public a() {
            this.b = "GET";
            this.c = new okhttp3.r.a();
        }

        a(y yVar) {
            this.a = yVar.a;
            this.b = yVar.b;
            this.d = yVar.d;
            this.e = yVar.e;
            this.c = yVar.c.b();
        }

        public a a(s sVar) {
            if (sVar == null) {
                throw new NullPointerException("url == null");
            }
            this.a = sVar;
            return this;
        }

        public a a(String str) {
            if (str == null) {
                throw new NullPointerException("url == null");
            }
            if (str.regionMatches(true, 0, "ws:", 0, 3)) {
                str = "http:" + str.substring(3);
            } else {
                if (str.regionMatches(true, 0, "wss:", 0, 4)) {
                    str = "https:" + str.substring(4);
                }
            }
            s e = s.e(str);
            if (e != null) {
                return a(e);
            }
            throw new IllegalArgumentException("unexpected url: " + str);
        }

        public a a(String str, String str2) {
            this.c.c(str, str2);
            return this;
        }

        public a b(String str, String str2) {
            this.c.a(str, str2);
            return this;
        }

        public a b(String str) {
            this.c.b(str);
            return this;
        }

        public a a(r rVar) {
            this.c = rVar.b();
            return this;
        }

        public a a(d dVar) {
            String dVar2 = dVar.toString();
            if (dVar2.isEmpty()) {
                return b("Cache-Control");
            }
            return a("Cache-Control", dVar2);
        }

        public a a() {
            return a("GET", null);
        }

        public a a(z zVar) {
            return a("POST", zVar);
        }

        public a a(String str, z zVar) {
            if (str == null) {
                throw new NullPointerException("method == null");
            } else if (str.length() == 0) {
                throw new IllegalArgumentException("method.length() == 0");
            } else if (zVar != null && !f.c(str)) {
                throw new IllegalArgumentException("method " + str + " must not have a request body.");
            } else if (zVar == null && f.b(str)) {
                throw new IllegalArgumentException("method " + str + " must have a request body.");
            } else {
                this.b = str;
                this.d = zVar;
                return this;
            }
        }

        public y b() {
            if (this.a != null) {
                return new y(this);
            }
            throw new IllegalStateException("url == null");
        }
    }

    y(a aVar) {
        Object obj;
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c.a();
        this.d = aVar.d;
        if (aVar.e != null) {
            obj = aVar.e;
        } else {
            y yVar = this;
        }
        this.e = obj;
    }

    public s a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public r c() {
        return this.c;
    }

    public String a(String str) {
        return this.c.a(str);
    }

    public List<String> b(String str) {
        return this.c.b(str);
    }

    public z d() {
        return this.d;
    }

    public a e() {
        return new a(this);
    }

    public d f() {
        d dVar = this.f;
        if (dVar != null) {
            return dVar;
        }
        dVar = d.a(this.c);
        this.f = dVar;
        return dVar;
    }

    public boolean g() {
        return this.a.c();
    }

    public String toString() {
        return "Request{method=" + this.b + ", url=" + this.a + ", tag=" + (this.e != this ? this.e : null) + '}';
    }
}
