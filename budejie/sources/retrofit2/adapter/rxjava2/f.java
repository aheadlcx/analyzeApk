package retrofit2.adapter.rxjava2;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Scheduler;
import java.lang.reflect.Type;
import retrofit2.b;
import retrofit2.c;

final class f<R> implements c<R, Object> {
    private final Type a;
    private final Scheduler b;
    private final boolean c;
    private final boolean d;
    private final boolean e;
    private final boolean f;
    private final boolean g;
    private final boolean h;
    private final boolean i;

    f(Type type, Scheduler scheduler, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7) {
        this.a = type;
        this.b = scheduler;
        this.c = z;
        this.d = z2;
        this.e = z3;
        this.f = z4;
        this.g = z5;
        this.h = z6;
        this.i = z7;
    }

    public Type a() {
        return this.a;
    }

    public Object a(b<R> bVar) {
        Object bVar2;
        if (this.c) {
            bVar2 = new b(bVar);
        } else {
            bVar2 = new c(bVar);
        }
        if (this.d) {
            bVar2 = new e(bVar2);
        } else if (this.e) {
            a aVar = new a(bVar2);
        }
        if (this.b != null) {
            bVar2 = bVar2.subscribeOn(this.b);
        }
        if (this.f) {
            return bVar2.toFlowable(BackpressureStrategy.LATEST);
        }
        if (this.g) {
            return bVar2.singleOrError();
        }
        if (this.h) {
            return bVar2.singleElement();
        }
        if (this.i) {
            return bVar2.ignoreElements();
        }
        return bVar2;
    }
}
