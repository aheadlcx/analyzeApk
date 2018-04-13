package retrofit2;

import java.io.IOException;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import okhttp3.aa;
import okhttp3.ab;
import okhttp3.e;

final class h<T> implements b<T> {
    private final n<T, ?> a;
    @Nullable
    private final Object[] b;
    private volatile boolean c;
    @GuardedBy
    @Nullable
    private e d;
    @GuardedBy
    @Nullable
    private Throwable e;
    @GuardedBy
    private boolean f;

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return e();
    }

    public /* synthetic */ b d() {
        return e();
    }

    h(n<T, ?> nVar, @Nullable Object[] objArr) {
        this.a = nVar;
        this.b = objArr;
    }

    public h<T> e() {
        return new h(this.a, this.b);
    }

    public void a(d<T> dVar) {
        e f;
        o.a(dVar, "callback == null");
        synchronized (this) {
            if (this.f) {
                throw new IllegalStateException("Already executed.");
            }
            this.f = true;
            e eVar = this.d;
            Throwable th = this.e;
            if (eVar == null && th == null) {
                try {
                    f = f();
                    this.d = f;
                } catch (Throwable th2) {
                    th = th2;
                    this.e = th;
                    f = eVar;
                }
            } else {
                f = eVar;
            }
        }
        if (th != null) {
            dVar.a(this, th);
            return;
        }
        if (this.c) {
            f.b();
        }
        f.a(new h$1(this, dVar));
    }

    public l<T> a() throws IOException {
        e eVar;
        Throwable e;
        synchronized (this) {
            if (this.f) {
                throw new IllegalStateException("Already executed.");
            }
            this.f = true;
            if (this.e == null) {
                eVar = this.d;
                if (eVar == null) {
                    try {
                        eVar = f();
                        this.d = eVar;
                    } catch (IOException e2) {
                        e = e2;
                        this.e = e;
                        throw e;
                    } catch (RuntimeException e3) {
                        e = e3;
                        this.e = e;
                        throw e;
                    }
                }
            } else if (this.e instanceof IOException) {
                throw ((IOException) this.e);
            } else {
                throw ((RuntimeException) this.e);
            }
        }
        if (this.c) {
            eVar.b();
        }
        return a(eVar.a());
    }

    private e f() throws IOException {
        e a = this.a.c.a(this.a.a(this.b));
        if (a != null) {
            return a;
        }
        throw new NullPointerException("Call.Factory returned null.");
    }

    l<T> a(aa aaVar) throws IOException {
        ab g = aaVar.g();
        aa a = aaVar.h().a(new h$b(g.contentType(), g.contentLength())).a();
        int b = a.b();
        if (b < 200 || b >= 300) {
            try {
                l<T> a2 = l.a(o.a(g), a);
                return a2;
            } finally {
                g.close();
            }
        } else if (b == 204 || b == 205) {
            g.close();
            return l.a(null, a);
        } else {
            ab h_a = new h$a(g);
            try {
                return l.a(this.a.a(h_a), a);
            } catch (RuntimeException e) {
                h_a.a();
                throw e;
            }
        }
    }

    public void b() {
        this.c = true;
        synchronized (this) {
            e eVar = this.d;
        }
        if (eVar != null) {
            eVar.b();
        }
    }

    public boolean c() {
        boolean z = true;
        if (!this.c) {
            synchronized (this) {
                if (this.d == null || !this.d.c()) {
                    z = false;
                }
            }
        }
        return z;
    }
}
