package retrofit2;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import okhttp3.HttpUrl;
import okhttp3.ab;
import okhttp3.e$a;
import okhttp3.w;
import okhttp3.z;
import retrofit2.a.d;

public final class m {
    final e$a a;
    final HttpUrl b;
    final List<e$a> c;
    final List<c$a> d;
    @Nullable
    final Executor e;
    final boolean f;
    private final Map<Method, n<?, ?>> g = new ConcurrentHashMap();

    public static final class a {
        private final j a;
        @Nullable
        private e$a b;
        private HttpUrl c;
        private final List<e$a> d;
        private final List<c$a> e;
        @Nullable
        private Executor f;
        private boolean g;

        a(j jVar) {
            this.d = new ArrayList();
            this.e = new ArrayList();
            this.a = jVar;
            this.d.add(new a());
        }

        public a() {
            this(j.a());
        }

        public a a(e$a e_a) {
            this.b = (e$a) o.a(e_a, "factory == null");
            return this;
        }

        public a a(String str) {
            o.a(str, "baseUrl == null");
            HttpUrl e = HttpUrl.e(str);
            if (e != null) {
                return a(e);
            }
            throw new IllegalArgumentException("Illegal URL: " + str);
        }

        public a a(HttpUrl httpUrl) {
            o.a(httpUrl, "baseUrl == null");
            List j = httpUrl.j();
            if ("".equals(j.get(j.size() - 1))) {
                this.c = httpUrl;
                return this;
            }
            throw new IllegalArgumentException("baseUrl must end in /: " + httpUrl);
        }

        public a a(e$a e_a) {
            this.d.add(o.a(e_a, "factory == null"));
            return this;
        }

        public a a(c$a c_a) {
            this.e.add(o.a(c_a, "factory == null"));
            return this;
        }

        public m a() {
            if (this.c == null) {
                throw new IllegalStateException("Base URL required.");
            }
            e$a e_a = this.b;
            if (e_a == null) {
                e_a = new w();
            }
            Executor executor = this.f;
            if (executor == null) {
                executor = this.a.b();
            }
            List arrayList = new ArrayList(this.e);
            arrayList.add(this.a.a(executor));
            return new m(e_a, this.c, new ArrayList(this.d), arrayList, executor, this.g);
        }
    }

    m(e$a e_a, HttpUrl httpUrl, List<e$a> list, List<c$a> list2, @Nullable Executor executor, boolean z) {
        this.a = e_a;
        this.b = httpUrl;
        this.c = Collections.unmodifiableList(list);
        this.d = Collections.unmodifiableList(list2);
        this.e = executor;
        this.f = z;
    }

    public <T> T a(Class<T> cls) {
        o.a(cls);
        if (this.f) {
            b(cls);
        }
        return Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new m$1(this, cls));
    }

    private void b(Class<?> cls) {
        j a = j.a();
        for (Method method : cls.getDeclaredMethods()) {
            if (!a.a(method)) {
                a(method);
            }
        }
    }

    n<?, ?> a(Method method) {
        n<?, ?> nVar = (n) this.g.get(method);
        if (nVar == null) {
            synchronized (this.g) {
                nVar = (n) this.g.get(method);
                if (nVar == null) {
                    nVar = new retrofit2.n.a(this, method).a();
                    this.g.put(method, nVar);
                }
            }
        }
        return nVar;
    }

    public e$a a() {
        return this.a;
    }

    public HttpUrl b() {
        return this.b;
    }

    public c<?, ?> a(Type type, Annotation[] annotationArr) {
        return a(null, type, annotationArr);
    }

    public c<?, ?> a(@Nullable c$a c_a, Type type, Annotation[] annotationArr) {
        int i;
        o.a(type, "returnType == null");
        o.a(annotationArr, "annotations == null");
        int indexOf = this.d.indexOf(c_a) + 1;
        int size = this.d.size();
        for (i = indexOf; i < size; i++) {
            c<?, ?> a = ((c$a) this.d.get(i)).a(type, annotationArr, this);
            if (a != null) {
                return a;
            }
        }
        StringBuilder append = new StringBuilder("Could not locate call adapter for ").append(type).append(".\n");
        if (c_a != null) {
            append.append("  Skipped:");
            for (i = 0; i < indexOf; i++) {
                append.append("\n   * ").append(((c$a) this.d.get(i)).getClass().getName());
            }
            append.append('\n');
        }
        append.append("  Tried:");
        i = this.d.size();
        while (indexOf < i) {
            append.append("\n   * ").append(((c$a) this.d.get(indexOf)).getClass().getName());
            indexOf++;
        }
        throw new IllegalArgumentException(append.toString());
    }

    public <T> e<T, z> a(Type type, Annotation[] annotationArr, Annotation[] annotationArr2) {
        return a(null, type, annotationArr, annotationArr2);
    }

    public <T> e<T, z> a(@Nullable e$a e_a, Type type, Annotation[] annotationArr, Annotation[] annotationArr2) {
        int i;
        o.a(type, "type == null");
        o.a(annotationArr, "parameterAnnotations == null");
        o.a(annotationArr2, "methodAnnotations == null");
        int indexOf = this.c.indexOf(e_a) + 1;
        int size = this.c.size();
        for (i = indexOf; i < size; i++) {
            e<T, z> a = ((e$a) this.c.get(i)).a(type, annotationArr, annotationArr2, this);
            if (a != null) {
                return a;
            }
        }
        StringBuilder append = new StringBuilder("Could not locate RequestBody converter for ").append(type).append(".\n");
        if (e_a != null) {
            append.append("  Skipped:");
            for (i = 0; i < indexOf; i++) {
                append.append("\n   * ").append(((e$a) this.c.get(i)).getClass().getName());
            }
            append.append('\n');
        }
        append.append("  Tried:");
        i = this.c.size();
        while (indexOf < i) {
            append.append("\n   * ").append(((e$a) this.c.get(indexOf)).getClass().getName());
            indexOf++;
        }
        throw new IllegalArgumentException(append.toString());
    }

    public <T> e<ab, T> b(Type type, Annotation[] annotationArr) {
        return a(null, type, annotationArr);
    }

    public <T> e<ab, T> a(@Nullable e$a e_a, Type type, Annotation[] annotationArr) {
        int i;
        o.a(type, "type == null");
        o.a(annotationArr, "annotations == null");
        int indexOf = this.c.indexOf(e_a) + 1;
        int size = this.c.size();
        for (i = indexOf; i < size; i++) {
            e<ab, T> a = ((e$a) this.c.get(i)).a(type, annotationArr, this);
            if (a != null) {
                return a;
            }
        }
        StringBuilder append = new StringBuilder("Could not locate ResponseBody converter for ").append(type).append(".\n");
        if (e_a != null) {
            append.append("  Skipped:");
            for (i = 0; i < indexOf; i++) {
                append.append("\n   * ").append(((e$a) this.c.get(i)).getClass().getName());
            }
            append.append('\n');
        }
        append.append("  Tried:");
        i = this.c.size();
        while (indexOf < i) {
            append.append("\n   * ").append(((e$a) this.c.get(indexOf)).getClass().getName());
            indexOf++;
        }
        throw new IllegalArgumentException(append.toString());
    }

    public <T> e<T, String> c(Type type, Annotation[] annotationArr) {
        o.a(type, "type == null");
        o.a(annotationArr, "annotations == null");
        int size = this.c.size();
        for (int i = 0; i < size; i++) {
            e<T, String> b = ((e$a) this.c.get(i)).b(type, annotationArr, this);
            if (b != null) {
                return b;
            }
        }
        return d.a;
    }

    @Nullable
    public Executor c() {
        return this.e;
    }
}
