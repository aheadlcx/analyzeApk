package okhttp3;

import android.support.v4.app.NotificationCompat;
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
    final p c;
    final y d;
    final boolean e;
    private boolean f;

    final class a extends b {
        final /* synthetic */ x a;
        private final f c;

        a(x xVar, f fVar) {
            this.a = xVar;
            super("OkHttp %s", xVar.f());
            this.c = fVar;
        }

        String a() {
            return this.a.d.a().f();
        }

        x b() {
            return this.a;
        }

        protected void c() {
            IOException e;
            Object obj = 1;
            Object obj2 = null;
            try {
                aa g = this.a.g();
                if (this.a.b.b()) {
                    try {
                        this.c.a(this.a, new IOException("Canceled"));
                    } catch (IOException e2) {
                        e = e2;
                        if (obj == null) {
                            this.c.a(this.a, e);
                        } else {
                            try {
                                e.b().a(4, "Callback failure for " + this.a.e(), (Throwable) e);
                            } catch (Throwable th) {
                                this.a.a.s().b(this);
                            }
                        }
                        this.a.a.s().b(this);
                    }
                }
                this.c.a(this.a, g);
                this.a.a.s().b(this);
            } catch (IOException e3) {
                e = e3;
                obj = obj2;
                if (obj == null) {
                    e.b().a(4, "Callback failure for " + this.a.e(), (Throwable) e);
                } else {
                    this.c.a(this.a, e);
                }
                this.a.a.s().b(this);
            }
        }
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return d();
    }

    x(w wVar, y yVar, boolean z) {
        okhttp3.p.a x = wVar.x();
        this.a = wVar;
        this.d = yVar;
        this.e = z;
        this.b = new j(wVar, z);
        this.c = x.a(this);
    }

    public aa a() throws IOException {
        synchronized (this) {
            if (this.f) {
                throw new IllegalStateException("Already Executed");
            }
            this.f = true;
        }
        h();
        try {
            this.a.s().a(this);
            aa g = g();
            if (g != null) {
                return g;
            }
            throw new IOException("Canceled");
        } finally {
            this.a.s().b(this);
        }
    }

    private void h() {
        this.b.a(e.b().a("response.body().close()"));
    }

    public void a(f fVar) {
        synchronized (this) {
            if (this.f) {
                throw new IllegalStateException("Already Executed");
            }
            this.f = true;
        }
        h();
        this.a.s().a(new a(this, fVar));
    }

    public void b() {
        this.b.a();
    }

    public boolean c() {
        return this.b.b();
    }

    public x d() {
        return new x(this.a, this.d, this.e);
    }

    String e() {
        return (c() ? "canceled " : "") + (this.e ? "web socket" : NotificationCompat.CATEGORY_CALL) + " to " + f();
    }

    String f() {
        return this.d.a().n();
    }

    aa g() throws IOException {
        List arrayList = new ArrayList();
        arrayList.addAll(this.a.v());
        arrayList.add(this.b);
        arrayList.add(new okhttp3.internal.b.a(this.a.f()));
        arrayList.add(new okhttp3.internal.a.a(this.a.g()));
        arrayList.add(new okhttp3.internal.connection.a(this.a));
        if (!this.e) {
            arrayList.addAll(this.a.w());
        }
        arrayList.add(new okhttp3.internal.b.b(this.e));
        return new g(arrayList, null, null, null, 0, this.d).a(this.d);
    }
}
