package retrofit2;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import okhttp3.ab;
import okhttp3.z;
import retrofit2.a.w;

final class a extends e$a {

    static final class a implements e<ab, ab> {
        static final a a = new a();

        a() {
        }

        public /* synthetic */ Object b(Object obj) throws IOException {
            return a((ab) obj);
        }

        public ab a(ab abVar) throws IOException {
            try {
                ab a = o.a(abVar);
                return a;
            } finally {
                abVar.close();
            }
        }
    }

    static final class b implements e<z, z> {
        static final b a = new b();

        b() {
        }

        public /* synthetic */ Object b(Object obj) throws IOException {
            return a((z) obj);
        }

        public z a(z zVar) throws IOException {
            return zVar;
        }
    }

    static final class c implements e<ab, ab> {
        static final c a = new c();

        c() {
        }

        public /* synthetic */ Object b(Object obj) throws IOException {
            return a((ab) obj);
        }

        public ab a(ab abVar) throws IOException {
            return abVar;
        }
    }

    static final class d implements e<Object, String> {
        static final d a = new d();

        d() {
        }

        public /* synthetic */ Object b(Object obj) throws IOException {
            return a(obj);
        }

        public String a(Object obj) {
            return obj.toString();
        }
    }

    static final class e implements e<ab, Void> {
        static final e a = new e();

        e() {
        }

        public /* synthetic */ Object b(Object obj) throws IOException {
            return a((ab) obj);
        }

        public Void a(ab abVar) throws IOException {
            abVar.close();
            return null;
        }
    }

    a() {
    }

    public e<ab, ?> a(Type type, Annotation[] annotationArr, m mVar) {
        if (type == ab.class) {
            if (o.a(annotationArr, w.class)) {
                return c.a;
            }
            return a.a;
        } else if (type == Void.class) {
            return e.a;
        } else {
            return null;
        }
    }

    public e<?, z> a(Type type, Annotation[] annotationArr, Annotation[] annotationArr2, m mVar) {
        if (z.class.isAssignableFrom(o.a(type))) {
            return b.a;
        }
        return null;
    }
}
