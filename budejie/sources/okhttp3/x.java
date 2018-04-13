package okhttp3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.internal.b;
import okhttp3.internal.b.g;
import okhttp3.internal.b.j;
import okhttp3.internal.e.e;

final class x implements e {
    final w a;
    final j b;
    final y c;
    final boolean d;
    private boolean e;

    final class a extends b {
        final /* synthetic */ x a;
        private final f c;

        a(x xVar, f fVar) {
            this.a = xVar;
            super("OkHttp %s", xVar.g());
            this.c = fVar;
        }

        String a() {
            return this.a.c.a().f();
        }

        protected void b() {
            Throwable e;
            Object obj = 1;
            Object obj2 = null;
            try {
                aa h = this.a.h();
                if (this.a.b.b()) {
                    try {
                        this.c.onFailure(this.a, new IOException("Canceled"));
                    } catch (IOException e2) {
                        e = e2;
                        if (obj == null) {
                            this.c.onFailure(this.a, e);
                        } else {
                            try {
                                e.b().a(4, "Callback failure for " + this.a.f(), e);
                            } catch (Throwable th) {
                                this.a.a.t().b(this);
                            }
                        }
                        this.a.a.t().b(this);
                    }
                }
                this.c.onResponse(this.a, h);
                this.a.a.t().b(this);
            } catch (IOException e3) {
                e = e3;
                obj = obj2;
                if (obj == null) {
                    e.b().a(4, "Callback failure for " + this.a.f(), e);
                } else {
                    this.c.onFailure(this.a, e);
                }
                this.a.a.t().b(this);
            }
        }
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return e();
    }

    x(w wVar, y yVar, boolean z) {
        this.a = wVar;
        this.c = yVar;
        this.d = z;
        this.b = new j(wVar, z);
    }

    public y a() {
        return this.c;
    }

    public aa b() throws IOException {
        synchronized (this) {
            if (this.e) {
                throw new IllegalStateException("Already Executed");
            }
            this.e = true;
        }
        i();
        try {
            this.a.t().a(this);
            aa h = h();
            if (h != null) {
                return h;
            }
            throw new IOException("Canceled");
        } finally {
            this.a.t().b(this);
        }
    }

    private void i() {
        this.b.a(e.b().a("response.body().close()"));
    }

    public void a(f fVar) {
        synchronized (this) {
            if (this.e) {
                throw new IllegalStateException("Already Executed");
            }
            this.e = true;
        }
        i();
        this.a.t().a(new a(this, fVar));
    }

    public void c() {
        this.b.a();
    }

    public boolean d() {
        return this.b.b();
    }

    public x e() {
        return new x(this.a, this.c, this.d);
    }

    String f() {
        return (d() ? "canceled " : "") + (this.d ? "web socket" : "call") + " to " + g();
    }

    String g() {
        return this.c.a().n();
    }

    aa h() throws IOException {
        List arrayList = new ArrayList();
        arrayList.addAll(this.a.w());
        arrayList.add(this.b);
        arrayList.add(new okhttp3.internal.b.a(this.a.f()));
        arrayList.add(new okhttp3.internal.a.a(this.a.h()));
        arrayList.add(new okhttp3.internal.connection.a(this.a));
        if (!this.d) {
            arrayList.addAll(this.a.x());
        }
        arrayList.add(new okhttp3.internal.b.b(this.d));
        return new g(arrayList, null, null, null, 0, this.c).a(this.c);
    }
}
