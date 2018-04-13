package retrofit2.adapter.rxjava2;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import retrofit2.c;
import retrofit2.c.a;
import retrofit2.l;
import retrofit2.m;

public final class g extends a {
    private final Scheduler a;
    private final boolean b;

    public static g a() {
        return new g(null, false);
    }

    private g(Scheduler scheduler, boolean z) {
        this.a = scheduler;
        this.b = z;
    }

    public c<?, ?> a(Type type, Annotation[] annotationArr, m mVar) {
        Class a = a.a(type);
        if (a == Completable.class) {
            return new f(Void.class, this.a, this.b, false, true, false, false, false, true);
        }
        boolean z = a == Flowable.class;
        boolean z2 = a == Single.class;
        boolean z3 = a == Maybe.class;
        if (a != Observable.class && !z && !z2 && !z3) {
            return null;
        }
        boolean z4 = false;
        boolean z5 = false;
        if (type instanceof ParameterizedType) {
            Type a2;
            Type a3 = a.a(0, (ParameterizedType) type);
            Class a4 = a.a(a3);
            if (a4 == l.class) {
                if (a3 instanceof ParameterizedType) {
                    a2 = a.a(0, (ParameterizedType) a3);
                } else {
                    throw new IllegalStateException("Response must be parameterized as Response<Foo> or Response<? extends Foo>");
                }
            } else if (a4 != d.class) {
                z5 = true;
                a2 = a3;
            } else if (a3 instanceof ParameterizedType) {
                a2 = a.a(0, (ParameterizedType) a3);
                z4 = true;
            } else {
                throw new IllegalStateException("Result must be parameterized as Result<Foo> or Result<? extends Foo>");
            }
            return new f(a2, this.a, this.b, z4, z5, z, z2, z3, false);
        }
        String str = z ? "Flowable" : z2 ? "Single" : z3 ? "Maybe" : "Observable";
        throw new IllegalStateException(str + " return type must be parameterized as " + str + "<Foo> or " + str + "<? extends Foo>");
    }
}
