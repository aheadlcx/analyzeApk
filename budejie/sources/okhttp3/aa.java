package okhttp3;

import java.io.Closeable;

public final class aa implements Closeable {
    final y a;
    final Protocol b;
    final int c;
    final String d;
    final q e;
    final r f;
    final ab g;
    final aa h;
    final aa i;
    final aa j;
    final long k;
    final long l;
    private volatile d m;

    public static class a {
        y a;
        Protocol b;
        int c;
        String d;
        q e;
        okhttp3.r.a f;
        ab g;
        aa h;
        aa i;
        aa j;
        long k;
        long l;

        public a() {
            this.c = -1;
            this.f = new okhttp3.r.a();
        }

        a(aa aaVar) {
            this.c = -1;
            this.a = aaVar.a;
            this.b = aaVar.b;
            this.c = aaVar.c;
            this.d = aaVar.d;
            this.e = aaVar.e;
            this.f = aaVar.f.b();
            this.g = aaVar.g;
            this.h = aaVar.h;
            this.i = aaVar.i;
            this.j = aaVar.j;
            this.k = aaVar.k;
            this.l = aaVar.l;
        }

        public a a(y yVar) {
            this.a = yVar;
            return this;
        }

        public a a(Protocol protocol) {
            this.b = protocol;
            return this;
        }

        public a a(int i) {
            this.c = i;
            return this;
        }

        public a a(String str) {
            this.d = str;
            return this;
        }

        public a a(q qVar) {
            this.e = qVar;
            return this;
        }

        public a a(String str, String str2) {
            this.f.c(str, str2);
            return this;
        }

        public a b(String str, String str2) {
            this.f.a(str, str2);
            return this;
        }

        public a b(String str) {
            this.f.b(str);
            return this;
        }

        public a a(r rVar) {
            this.f = rVar.b();
            return this;
        }

        public a a(ab abVar) {
            this.g = abVar;
            return this;
        }

        public a a(aa aaVar) {
            if (aaVar != null) {
                a("networkResponse", aaVar);
            }
            this.h = aaVar;
            return this;
        }

        public a b(aa aaVar) {
            if (aaVar != null) {
                a("cacheResponse", aaVar);
            }
            this.i = aaVar;
            return this;
        }

        private void a(String str, aa aaVar) {
            if (aaVar.g != null) {
                throw new IllegalArgumentException(str + ".body != null");
            } else if (aaVar.h != null) {
                throw new IllegalArgumentException(str + ".networkResponse != null");
            } else if (aaVar.i != null) {
                throw new IllegalArgumentException(str + ".cacheResponse != null");
            } else if (aaVar.j != null) {
                throw new IllegalArgumentException(str + ".priorResponse != null");
            }
        }

        public a c(aa aaVar) {
            if (aaVar != null) {
                d(aaVar);
            }
            this.j = aaVar;
            return this;
        }

        private void d(aa aaVar) {
            if (aaVar.g != null) {
                throw new IllegalArgumentException("priorResponse.body != null");
            }
        }

        public a a(long j) {
            this.k = j;
            return this;
        }

        public a b(long j) {
            this.l = j;
            return this;
        }

        public aa a() {
            if (this.a == null) {
                throw new IllegalStateException("request == null");
            } else if (this.b == null) {
                throw new IllegalStateException("protocol == null");
            } else if (this.c >= 0) {
                return new aa(this);
            } else {
                throw new IllegalStateException("code < 0: " + this.c);
            }
        }
    }

    aa(a aVar) {
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = aVar.d;
        this.e = aVar.e;
        this.f = aVar.f.a();
        this.g = aVar.g;
        this.h = aVar.h;
        this.i = aVar.i;
        this.j = aVar.j;
        this.k = aVar.k;
        this.l = aVar.l;
    }

    public y a() {
        return this.a;
    }

    public Protocol b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public boolean d() {
        return this.c >= 200 && this.c < 300;
    }

    public String e() {
        return this.d;
    }

    public q f() {
        return this.e;
    }

    public String a(String str) {
        return a(str, null);
    }

    public String a(String str, String str2) {
        String a = this.f.a(str);
        return a != null ? a : str2;
    }

    public r g() {
        return this.f;
    }

    public ab h() {
        return this.g;
    }

    public a i() {
        return new a(this);
    }

    public aa j() {
        return this.h;
    }

    public d k() {
        d dVar = this.m;
        if (dVar != null) {
            return dVar;
        }
        dVar = d.a(this.f);
        this.m = dVar;
        return dVar;
    }

    public long l() {
        return this.k;
    }

    public long m() {
        return this.l;
    }

    public void close() {
        this.g.close();
    }

    public String toString() {
        return "Response{protocol=" + this.b + ", code=" + this.c + ", message=" + this.d + ", url=" + this.a.a() + '}';
    }
}
