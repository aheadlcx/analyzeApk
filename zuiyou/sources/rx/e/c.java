package rx.e;

import java.util.concurrent.ScheduledExecutorService;
import rx.b.b;
import rx.b.f;
import rx.b.g;
import rx.b.h;
import rx.d;
import rx.d$a;
import rx.d$b;
import rx.h.a;
import rx.internal.operators.r;
import rx.internal.operators.t;
import rx.k;

public final class c {
    static volatile b<Throwable> a;
    static volatile g<d$a, d$a> b;
    static volatile g<a, a> c;
    static volatile g<rx.b.a, rx.b.a> d;
    static volatile h<d, d$a, d$a> e;
    static volatile h<rx.h, a, a> f;
    static volatile h<rx.b, rx.b.a, rx.b.a> g;
    static volatile g<rx.g, rx.g> h;
    static volatile g<rx.g, rx.g> i;
    static volatile g<rx.g, rx.g> j;
    static volatile g<rx.b.a, rx.b.a> k;
    static volatile g<k, k> l;
    static volatile g<k, k> m;
    static volatile f<? extends ScheduledExecutorService> n;
    static volatile g<Throwable, Throwable> o;
    static volatile g<Throwable, Throwable> p;
    static volatile g<Throwable, Throwable> q;
    static volatile g<d$b, d$b> r;
    static volatile g<d$b, d$b> s;
    static volatile g<rx.b.b, rx.b.b> t;

    static {
        a();
    }

    static void a() {
        a = new b<Throwable>() {
            public /* synthetic */ void call(Object obj) {
                a((Throwable) obj);
            }

            public void a(Throwable th) {
                f.a().b().a(th);
            }
        };
        e = new h<d, d$a, d$a>() {
            public d$a a(d dVar, d$a d_a) {
                return f.a().c().a(dVar, d_a);
            }
        };
        l = new g<k, k>() {
            public /* synthetic */ Object call(Object obj) {
                return a((k) obj);
            }

            public k a(k kVar) {
                return f.a().c().a(kVar);
            }
        };
        f = new h<rx.h, a, a>() {
            public a a(rx.h hVar, a aVar) {
                h d = f.a().d();
                return d == i.a() ? aVar : new r(d.a(hVar, new t(aVar)));
            }
        };
        m = new g<k, k>() {
            public /* synthetic */ Object call(Object obj) {
                return a((k) obj);
            }

            public k a(k kVar) {
                return f.a().d().a(kVar);
            }
        };
        g = new h<rx.b, rx.b.a, rx.b.a>() {
            public rx.b.a a(rx.b bVar, rx.b.a aVar) {
                return f.a().e().a(bVar, aVar);
            }
        };
        k = new g<rx.b.a, rx.b.a>() {
            public /* synthetic */ Object call(Object obj) {
                return a((rx.b.a) obj);
            }

            public rx.b.a a(rx.b.a aVar) {
                return f.a().g().a(aVar);
            }
        };
        o = new g<Throwable, Throwable>() {
            public /* synthetic */ Object call(Object obj) {
                return a((Throwable) obj);
            }

            public Throwable a(Throwable th) {
                return f.a().c().a(th);
            }
        };
        r = new g<d$b, d$b>() {
            public /* synthetic */ Object call(Object obj) {
                return a((d$b) obj);
            }

            public d$b a(d$b d_b) {
                return f.a().c().a(d_b);
            }
        };
        p = new g<Throwable, Throwable>() {
            public /* synthetic */ Object call(Object obj) {
                return a((Throwable) obj);
            }

            public Throwable a(Throwable th) {
                return f.a().d().a(th);
            }
        };
        s = new g<d$b, d$b>() {
            public /* synthetic */ Object call(Object obj) {
                return a((d$b) obj);
            }

            public d$b a(d$b d_b) {
                return f.a().d().a(d_b);
            }
        };
        q = new g<Throwable, Throwable>() {
            public /* synthetic */ Object call(Object obj) {
                return a((Throwable) obj);
            }

            public Throwable a(Throwable th) {
                return f.a().e().a(th);
            }
        };
        t = new g<rx.b.b, rx.b.b>() {
            public /* synthetic */ Object call(Object obj) {
                return a((rx.b.b) obj);
            }

            public rx.b.b a(rx.b.b bVar) {
                return f.a().e().a(bVar);
            }
        };
        b();
    }

    static void b() {
        b = new g<d$a, d$a>() {
            public /* synthetic */ Object call(Object obj) {
                return a((d$a) obj);
            }

            public d$a a(d$a d_a) {
                return f.a().c().a(d_a);
            }
        };
        c = new g<a, a>() {
            public /* synthetic */ Object call(Object obj) {
                return a((a) obj);
            }

            public a a(a aVar) {
                return f.a().d().a(aVar);
            }
        };
        d = new g<rx.b.a, rx.b.a>() {
            public /* synthetic */ Object call(Object obj) {
                return a((rx.b.a) obj);
            }

            public rx.b.a a(rx.b.a aVar) {
                return f.a().e().a(aVar);
            }
        };
    }

    public static void a(Throwable th) {
        b bVar = a;
        if (bVar != null) {
            try {
                bVar.call(th);
                return;
            } catch (Throwable th2) {
                System.err.println("The onError handler threw an Exception. It shouldn't. => " + th2.getMessage());
                th2.printStackTrace();
                b(th2);
            }
        }
        b(th);
    }

    static void b(Throwable th) {
        Thread currentThread = Thread.currentThread();
        currentThread.getUncaughtExceptionHandler().uncaughtException(currentThread, th);
    }

    public static <T> d$a<T> a(d$a<T> d_a) {
        g gVar = b;
        if (gVar != null) {
            return (d$a) gVar.call(d_a);
        }
        return d_a;
    }

    public static <T> a<T> a(a<T> aVar) {
        g gVar = c;
        if (gVar != null) {
            return (a) gVar.call(aVar);
        }
        return aVar;
    }

    public static rx.b.a a(rx.b.a aVar) {
        g gVar = d;
        if (gVar != null) {
            return (rx.b.a) gVar.call(aVar);
        }
        return aVar;
    }

    public static rx.g a(rx.g gVar) {
        g gVar2 = h;
        if (gVar2 != null) {
            return (rx.g) gVar2.call(gVar);
        }
        return gVar;
    }

    public static rx.g b(rx.g gVar) {
        g gVar2 = i;
        if (gVar2 != null) {
            return (rx.g) gVar2.call(gVar);
        }
        return gVar;
    }

    public static rx.g c(rx.g gVar) {
        g gVar2 = j;
        if (gVar2 != null) {
            return (rx.g) gVar2.call(gVar);
        }
        return gVar;
    }

    public static rx.b.a a(rx.b.a aVar) {
        g gVar = k;
        if (gVar != null) {
            return (rx.b.a) gVar.call(aVar);
        }
        return aVar;
    }

    public static <T> d$a<T> a(d<T> dVar, d$a<T> d_a) {
        h hVar = e;
        if (hVar != null) {
            return (d$a) hVar.a(dVar, d_a);
        }
        return d_a;
    }

    public static k a(k kVar) {
        g gVar = l;
        if (gVar != null) {
            return (k) gVar.call(kVar);
        }
        return kVar;
    }

    public static Throwable c(Throwable th) {
        g gVar = o;
        if (gVar != null) {
            return (Throwable) gVar.call(th);
        }
        return th;
    }

    public static <T, R> d$b<R, T> a(d$b<R, T> d_b) {
        g gVar = r;
        if (gVar != null) {
            return (d$b) gVar.call(d_b);
        }
        return d_b;
    }

    public static <T, R> d$b<R, T> b(d$b<R, T> d_b) {
        g gVar = s;
        if (gVar != null) {
            return (d$b) gVar.call(d_b);
        }
        return d_b;
    }

    public static f<? extends ScheduledExecutorService> c() {
        return n;
    }
}
