package retrofit2;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import retrofit2.c.a;

final class f extends a {
    static final a a = new f();

    f() {
    }

    public c<?, ?> a(Type type, Annotation[] annotationArr, m mVar) {
        if (a.a(type) != b.class) {
            return null;
        }
        final Type e = o.e(type);
        return new c<Object, b<?>>(this) {
            final /* synthetic */ f b;

            public /* synthetic */ Object a(b bVar) {
                return b(bVar);
            }

            public Type a() {
                return e;
            }

            public b<Object> b(b<Object> bVar) {
                return bVar;
            }
        };
    }
}
