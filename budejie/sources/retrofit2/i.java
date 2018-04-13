package retrofit2;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Map;
import java.util.Map.Entry;
import okhttp3.r;
import okhttp3.z;
import org.apache.http.entity.mime.MIME;

abstract class i<T> {

    static final class a<T> extends i<T> {
        private final e<T, z> a;

        a(e<T, z> eVar) {
            this.a = eVar;
        }

        void a(k kVar, T t) {
            if (t == null) {
                throw new IllegalArgumentException("Body parameter value must not be null.");
            }
            try {
                kVar.a((z) this.a.a(t));
            } catch (Throwable e) {
                throw new RuntimeException("Unable to convert " + t + " to RequestBody", e);
            }
        }
    }

    static final class b<T> extends i<T> {
        private final String a;
        private final e<T, String> b;
        private final boolean c;

        b(String str, e<T, String> eVar, boolean z) {
            this.a = (String) o.a((Object) str, "name == null");
            this.b = eVar;
            this.c = z;
        }

        void a(k kVar, T t) throws IOException {
            if (t != null) {
                kVar.c(this.a, (String) this.b.a(t), this.c);
            }
        }
    }

    static final class c<T> extends i<Map<String, T>> {
        private final e<T, String> a;
        private final boolean b;

        c(e<T, String> eVar, boolean z) {
            this.a = eVar;
            this.b = z;
        }

        void a(k kVar, Map<String, T> map) throws IOException {
            if (map == null) {
                throw new IllegalArgumentException("Field map was null.");
            }
            for (Entry entry : map.entrySet()) {
                String str = (String) entry.getKey();
                if (str == null) {
                    throw new IllegalArgumentException("Field map contained null key.");
                }
                Object value = entry.getValue();
                if (value == null) {
                    throw new IllegalArgumentException("Field map contained null value for key '" + str + "'.");
                }
                kVar.c(str, (String) this.a.a(value), this.b);
            }
        }
    }

    static final class d<T> extends i<T> {
        private final String a;
        private final e<T, String> b;

        d(String str, e<T, String> eVar) {
            this.a = (String) o.a((Object) str, "name == null");
            this.b = eVar;
        }

        void a(k kVar, T t) throws IOException {
            if (t != null) {
                kVar.a(this.a, (String) this.b.a(t));
            }
        }
    }

    static final class e<T> extends i<Map<String, T>> {
        private final e<T, String> a;

        e(e<T, String> eVar) {
            this.a = eVar;
        }

        void a(k kVar, Map<String, T> map) throws IOException {
            if (map == null) {
                throw new IllegalArgumentException("Header map was null.");
            }
            for (Entry entry : map.entrySet()) {
                String str = (String) entry.getKey();
                if (str == null) {
                    throw new IllegalArgumentException("Header map contained null key.");
                }
                Object value = entry.getValue();
                if (value == null) {
                    throw new IllegalArgumentException("Header map contained null value for key '" + str + "'.");
                }
                kVar.a(str, (String) this.a.a(value));
            }
        }
    }

    static final class f<T> extends i<T> {
        private final r a;
        private final e<T, z> b;

        f(r rVar, e<T, z> eVar) {
            this.a = rVar;
            this.b = eVar;
        }

        void a(k kVar, T t) {
            if (t != null) {
                try {
                    kVar.a(this.a, (z) this.b.a(t));
                } catch (Throwable e) {
                    throw new RuntimeException("Unable to convert " + t + " to RequestBody", e);
                }
            }
        }
    }

    static final class g<T> extends i<Map<String, T>> {
        private final e<T, z> a;
        private final String b;

        g(e<T, z> eVar, String str) {
            this.a = eVar;
            this.b = str;
        }

        void a(k kVar, Map<String, T> map) throws IOException {
            if (map == null) {
                throw new IllegalArgumentException("Part map was null.");
            }
            for (Entry entry : map.entrySet()) {
                String str = (String) entry.getKey();
                if (str == null) {
                    throw new IllegalArgumentException("Part map contained null key.");
                }
                Object value = entry.getValue();
                if (value == null) {
                    throw new IllegalArgumentException("Part map contained null value for key '" + str + "'.");
                }
                kVar.a(r.a(MIME.CONTENT_DISPOSITION, "form-data; name=\"" + str + "\"", MIME.CONTENT_TRANSFER_ENC, this.b), (z) this.a.a(value));
            }
        }
    }

    static final class h<T> extends i<T> {
        private final String a;
        private final e<T, String> b;
        private final boolean c;

        h(String str, e<T, String> eVar, boolean z) {
            this.a = (String) o.a((Object) str, "name == null");
            this.b = eVar;
            this.c = z;
        }

        void a(k kVar, T t) throws IOException {
            if (t == null) {
                throw new IllegalArgumentException("Path parameter \"" + this.a + "\" value must not be null.");
            }
            kVar.a(this.a, (String) this.b.a(t), this.c);
        }
    }

    static final class i<T> extends i<T> {
        private final String a;
        private final e<T, String> b;
        private final boolean c;

        i(String str, e<T, String> eVar, boolean z) {
            this.a = (String) o.a((Object) str, "name == null");
            this.b = eVar;
            this.c = z;
        }

        void a(k kVar, T t) throws IOException {
            if (t != null) {
                kVar.b(this.a, (String) this.b.a(t), this.c);
            }
        }
    }

    static final class j<T> extends i<Map<String, T>> {
        private final e<T, String> a;
        private final boolean b;

        j(e<T, String> eVar, boolean z) {
            this.a = eVar;
            this.b = z;
        }

        void a(k kVar, Map<String, T> map) throws IOException {
            if (map == null) {
                throw new IllegalArgumentException("Query map was null.");
            }
            for (Entry entry : map.entrySet()) {
                String str = (String) entry.getKey();
                if (str == null) {
                    throw new IllegalArgumentException("Query map contained null key.");
                }
                Object value = entry.getValue();
                if (value == null) {
                    throw new IllegalArgumentException("Query map contained null value for key '" + str + "'.");
                }
                kVar.b(str, (String) this.a.a(value), this.b);
            }
        }
    }

    static final class k<T> extends i<T> {
        private final e<T, String> a;
        private final boolean b;

        k(e<T, String> eVar, boolean z) {
            this.a = eVar;
            this.b = z;
        }

        void a(k kVar, T t) throws IOException {
            if (t != null) {
                kVar.b((String) this.a.a(t), null, this.b);
            }
        }
    }

    static final class l extends i<okhttp3.v.b> {
        static final l a = new l();

        private l() {
        }

        void a(k kVar, okhttp3.v.b bVar) throws IOException {
            if (bVar != null) {
                kVar.a(bVar);
            }
        }
    }

    static final class m extends i<Object> {
        m() {
        }

        void a(k kVar, Object obj) {
            kVar.a(obj);
        }
    }

    abstract void a(k kVar, T t) throws IOException;

    i() {
    }

    final i<Iterable<T>> a() {
        return new i<Iterable<T>>(this) {
            final /* synthetic */ i a;

            {
                this.a = r1;
            }

            void a(k kVar, Iterable<T> iterable) throws IOException {
                if (iterable != null) {
                    for (T a : iterable) {
                        this.a.a(kVar, a);
                    }
                }
            }
        };
    }

    final i<Object> b() {
        return new i<Object>(this) {
            final /* synthetic */ i a;

            {
                this.a = r1;
            }

            void a(k kVar, Object obj) throws IOException {
                if (obj != null) {
                    int length = Array.getLength(obj);
                    for (int i = 0; i < length; i++) {
                        this.a.a(kVar, Array.get(obj, i));
                    }
                }
            }
        };
    }
}
