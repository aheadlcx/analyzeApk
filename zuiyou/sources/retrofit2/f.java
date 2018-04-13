package retrofit2;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

final class f extends c$a {
    static final c$a a = new f();

    f() {
    }

    public c<?, ?> a(Type type, Annotation[] annotationArr, m mVar) {
        if (a(type) != b.class) {
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
