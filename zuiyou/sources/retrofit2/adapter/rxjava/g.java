package retrofit2.adapter.rxjava;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import javax.annotation.Nullable;
import retrofit2.c;
import retrofit2.c$a;
import retrofit2.l;
import retrofit2.m;
import rx.b;
import rx.d;
import rx.h;

public final class g extends c$a {
    @Nullable
    private final rx.g a;
    private final boolean b;

    public static g a() {
        return new g(null, false);
    }

    public static g a(rx.g gVar) {
        if (gVar != null) {
            return new g(gVar, false);
        }
        throw new NullPointerException("scheduler == null");
    }

    private g(@Nullable rx.g gVar, boolean z) {
        this.a = gVar;
        this.b = z;
    }

    public c<?, ?> a(Type type, Annotation[] annotationArr, m mVar) {
        Class a = c$a.a(type);
        boolean z = a == h.class;
        Object obj = a == b.class ? 1 : null;
        if (a != d.class && !z && obj == null) {
            return null;
        }
        if (obj != null) {
            return new f(Void.class, this.a, this.b, false, true, false, true);
        }
        boolean z2 = false;
        boolean z3 = false;
        if (type instanceof ParameterizedType) {
            Type a2;
            Type a3 = c$a.a(0, (ParameterizedType) type);
            a = c$a.a(a3);
            if (a == l.class) {
                if (a3 instanceof ParameterizedType) {
                    a2 = c$a.a(0, (ParameterizedType) a3);
                } else {
                    throw new IllegalStateException("Response must be parameterized as Response<Foo> or Response<? extends Foo>");
                }
            } else if (a != d.class) {
                z3 = true;
                a2 = a3;
            } else if (a3 instanceof ParameterizedType) {
                a2 = c$a.a(0, (ParameterizedType) a3);
                z2 = true;
            } else {
                throw new IllegalStateException("Result must be parameterized as Result<Foo> or Result<? extends Foo>");
            }
            return new f(a2, this.a, this.b, z2, z3, z, false);
        }
        String str = z ? "Single" : "Observable";
        throw new IllegalStateException(str + " return type must be parameterized as " + str + "<Foo> or " + str + "<? extends Foo>");
    }
}
