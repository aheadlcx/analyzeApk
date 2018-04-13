package rx;

import java.util.concurrent.TimeUnit;
import rx.b.g;
import rx.e.c;
import rx.exceptions.OnErrorFailedException;
import rx.f.a;
import rx.internal.operators.EmptyObservableHolder;
import rx.internal.operators.OnSubscribeCreate;
import rx.internal.operators.OperatorMerge;
import rx.internal.operators.OperatorReplay;
import rx.internal.operators.b;
import rx.internal.operators.e;
import rx.internal.operators.f;
import rx.internal.operators.h;
import rx.internal.operators.i;
import rx.internal.operators.j;
import rx.internal.operators.k;
import rx.internal.operators.l;
import rx.internal.operators.m;
import rx.internal.operators.n;
import rx.internal.operators.o;
import rx.internal.operators.p;
import rx.internal.operators.q;
import rx.internal.util.InternalObservableUtils;
import rx.internal.util.ScalarSynchronousObservable;
import rx.internal.util.UtilityFunctions;

public class d<T> {
    final d$a<T> a;

    protected d(d$a<T> d_a) {
        this.a = d_a;
    }

    @Deprecated
    public static <T> d<T> a(d$a<T> d_a) {
        return new d(c.a(d_a));
    }

    public static <T> d<T> b(d$a<T> d_a) {
        return new d(c.a(d_a));
    }

    public final <R> d<R> a(d$b<? extends R, ? super T> d_b) {
        return b(new rx.internal.operators.c(this.a, d_b));
    }

    public h<T> a() {
        return new h(e.a(this));
    }

    public b b() {
        return b.a(this);
    }

    public static <T> d<T> c() {
        return EmptyObservableHolder.instance();
    }

    public static d<Long> a(long j, TimeUnit timeUnit) {
        return a(j, j, timeUnit, a.b());
    }

    public static d<Long> a(long j, long j2, TimeUnit timeUnit, g gVar) {
        return b(new f(j, j2, timeUnit, gVar));
    }

    public static <T> d<T> a(T t) {
        return ScalarSynchronousObservable.b(t);
    }

    public static <T> d<T> a(d<? extends d<? extends T>> dVar) {
        if (dVar.getClass() == ScalarSynchronousObservable.class) {
            return ((ScalarSynchronousObservable) dVar).f(UtilityFunctions.b());
        }
        return dVar.a(OperatorMerge.a(false));
    }

    public final d<T> a(rx.b.a aVar) {
        return a(new h(aVar));
    }

    public final d<T> b(rx.b.a aVar) {
        return a(new i(aVar));
    }

    public final d<T> a(g<? super T, Boolean> gVar) {
        return b(new b(this, gVar));
    }

    public final d<T> b(g<? super T, Boolean> gVar) {
        return e(gVar).f();
    }

    public final <R> d<R> c(g<? super T, ? extends d<? extends R>> gVar) {
        if (getClass() == ScalarSynchronousObservable.class) {
            return ((ScalarSynchronousObservable) this).f(gVar);
        }
        return a(d(gVar));
    }

    public final <R> d<R> d(g<? super T, ? extends R> gVar) {
        return b(new rx.internal.operators.d(this, gVar));
    }

    public final d<T> a(g gVar) {
        return a(gVar, rx.internal.util.e.b);
    }

    public final d<T> a(g gVar, int i) {
        return a(gVar, false, i);
    }

    public final d<T> a(g gVar, boolean z, int i) {
        if (this instanceof ScalarSynchronousObservable) {
            return ((ScalarSynchronousObservable) this).d(gVar);
        }
        return a(new j(gVar, z, i));
    }

    public final d<T> d() {
        return a(k.a());
    }

    public final rx.c.a<T> e() {
        return OperatorReplay.b(this);
    }

    public final rx.c.a<T> a(int i) {
        return OperatorReplay.a(this, i);
    }

    public final rx.c.a<T> a(int i, long j, TimeUnit timeUnit, g gVar) {
        if (i >= 0) {
            return OperatorReplay.a(this, j, timeUnit, gVar, i);
        }
        throw new IllegalArgumentException("bufferSize < 0");
    }

    public final rx.c.a<T> a(long j, TimeUnit timeUnit, g gVar) {
        return OperatorReplay.a(this, j, timeUnit, gVar);
    }

    public final d<T> b(long j, TimeUnit timeUnit) {
        return b(j, timeUnit, a.b());
    }

    public final d<T> b(long j, TimeUnit timeUnit, g gVar) {
        return a(new l(j, timeUnit, gVar));
    }

    public final d<T> f() {
        return a(m.a());
    }

    public final k g() {
        return b(new rx.internal.util.a(rx.b.d.a(), InternalObservableUtils.ERROR_NOT_IMPLEMENTED, rx.b.d.a()));
    }

    public final k a(rx.b.b<? super T> bVar) {
        if (bVar != null) {
            return b(new rx.internal.util.a(bVar, InternalObservableUtils.ERROR_NOT_IMPLEMENTED, rx.b.d.a()));
        }
        throw new IllegalArgumentException("onNext can not be null");
    }

    public final k a(rx.b.b<? super T> bVar, rx.b.b<Throwable> bVar2) {
        if (bVar == null) {
            throw new IllegalArgumentException("onNext can not be null");
        } else if (bVar2 != null) {
            return b(new rx.internal.util.a(bVar, bVar2, rx.b.d.a()));
        } else {
            throw new IllegalArgumentException("onError can not be null");
        }
    }

    public final k a(e<? super T> eVar) {
        if (eVar instanceof j) {
            return b((j) eVar);
        }
        if (eVar != null) {
            return b(new rx.internal.util.b(eVar));
        }
        throw new NullPointerException("observer is null");
    }

    public final k a(j<? super T> jVar) {
        try {
            jVar.onStart();
            c.a(this, this.a).call(jVar);
            return c.a(jVar);
        } catch (Throwable th) {
            rx.exceptions.a.b(th);
            c.c(new OnErrorFailedException("Error occurred attempting to subscribe [" + th.getMessage() + "] and then again while trying to pass to onError.", th));
        }
    }

    public final k b(j<? super T> jVar) {
        return a((j) jVar, this);
    }

    static <T> k a(j<? super T> jVar, d<T> dVar) {
        if (jVar == null) {
            throw new IllegalArgumentException("subscriber can not be null");
        } else if (dVar.a == null) {
            throw new IllegalStateException("onSubscribe function can not be null.");
        } else {
            jVar.onStart();
            if (!(jVar instanceof rx.d.a)) {
                jVar = new rx.d.a(jVar);
            }
            try {
                c.a(dVar, dVar.a).call(jVar);
                return c.a(jVar);
            } catch (Throwable th) {
                rx.exceptions.a.b(th);
                c.c(new OnErrorFailedException("Error occurred attempting to subscribe [" + th.getMessage() + "] and then again while trying to pass to onError.", th));
            }
        }
    }

    public final d<T> b(g gVar) {
        return a(gVar, !(this.a instanceof OnSubscribeCreate));
    }

    public final d<T> a(g gVar, boolean z) {
        if (this instanceof ScalarSynchronousObservable) {
            return ((ScalarSynchronousObservable) this).d(gVar);
        }
        return b(new n(this, gVar, z));
    }

    public final d<T> b(int i) {
        return a(new o(i));
    }

    public final d<T> e(g<? super T, Boolean> gVar) {
        return a((g) gVar).b(1);
    }

    public final d<T> c(long j, TimeUnit timeUnit) {
        return c(j, timeUnit, a.b());
    }

    public final d<T> c(long j, TimeUnit timeUnit, g gVar) {
        return a(new p(j, timeUnit, gVar));
    }

    public final d<T> d(long j, TimeUnit timeUnit) {
        return b(j, timeUnit);
    }

    public final d<T> c(g gVar) {
        return a(new q(gVar));
    }
}
