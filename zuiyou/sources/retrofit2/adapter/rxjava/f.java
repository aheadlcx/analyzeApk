package retrofit2.adapter.rxjava;

import java.lang.reflect.Type;
import javax.annotation.Nullable;
import retrofit2.b;
import retrofit2.c;
import rx.d;
import rx.d$a;
import rx.g;

final class f<R> implements c<R, Object> {
    private final Type a;
    @Nullable
    private final g b;
    private final boolean c;
    private final boolean d;
    private final boolean e;
    private final boolean f;
    private final boolean g;

    f(Type type, @Nullable g gVar, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        this.a = type;
        this.b = gVar;
        this.c = z;
        this.d = z2;
        this.e = z3;
        this.f = z4;
        this.g = z5;
    }

    public Type a() {
        return this.a;
    }

    public Object a(b<R> bVar) {
        d$a bVar2;
        Object aVar;
        if (this.c) {
            bVar2 = new b(bVar);
        } else {
            bVar2 = new c(bVar);
        }
        if (this.d) {
            bVar2 = new e(bVar2);
        } else if (this.e) {
            aVar = new a(bVar2);
        }
        aVar = d.a(bVar2);
        if (this.b != null) {
            aVar = aVar.b(this.b);
        }
        if (this.f) {
            return aVar.a();
        }
        if (this.g) {
            return aVar.b();
        }
        return aVar;
    }
}
