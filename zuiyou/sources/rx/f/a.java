package rx.f;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
import rx.e.c;
import rx.e.f;
import rx.g;
import rx.internal.schedulers.i;

public final class a {
    private static final AtomicReference<a> d = new AtomicReference();
    private final g a;
    private final g b;
    private final g c;

    private static a e() {
        a aVar;
        while (true) {
            aVar = (a) d.get();
            if (aVar == null) {
                aVar = new a();
                if (d.compareAndSet(null, aVar)) {
                    break;
                }
                aVar.d();
            } else {
                break;
            }
        }
        return aVar;
    }

    private a() {
        rx.e.g g = f.a().g();
        g d = g.d();
        if (d != null) {
            this.a = d;
        } else {
            this.a = rx.e.g.a();
        }
        d = g.e();
        if (d != null) {
            this.b = d;
        } else {
            this.b = rx.e.g.b();
        }
        g f = g.f();
        if (f != null) {
            this.c = f;
        } else {
            this.c = rx.e.g.c();
        }
    }

    public static g a() {
        return c.c(e().c);
    }

    public static g b() {
        return c.a(e().a);
    }

    public static g c() {
        return c.b(e().b);
    }

    public static g a(Executor executor) {
        return new rx.internal.schedulers.c(executor);
    }

    synchronized void d() {
        if (this.a instanceof i) {
            ((i) this.a).d();
        }
        if (this.b instanceof i) {
            ((i) this.b).d();
        }
        if (this.c instanceof i) {
            ((i) this.c).d();
        }
    }
}
