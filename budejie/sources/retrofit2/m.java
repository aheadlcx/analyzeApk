package retrofit2;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import okhttp3.ab;
import okhttp3.s;
import okhttp3.w;
import okhttp3.z;

public final class m {
    final okhttp3.e.a a;
    final s b;
    final List<retrofit2.e.a> c;
    final List<retrofit2.c.a> d;
    final Executor e;
    final boolean f;
    private final Map<Method, n<?, ?>> g = new ConcurrentHashMap();

    public static final class a {
        private final j a;
        private okhttp3.e.a b;
        private s c;
        private final List<retrofit2.e.a> d;
        private final List<retrofit2.c.a> e;
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

        public a a(w wVar) {
            return a((okhttp3.e.a) o.a((Object) wVar, "client == null"));
        }

        public a a(okhttp3.e.a aVar) {
            this.b = (okhttp3.e.a) o.a((Object) aVar, "factory == null");
            return this;
        }

        public a a(String str) {
            o.a((Object) str, "baseUrl == null");
            s e = s.e(str);
            if (e != null) {
                return a(e);
            }
            throw new IllegalArgumentException("Illegal URL: " + str);
        }

        public a a(s sVar) {
            o.a((Object) sVar, "baseUrl == null");
            List j = sVar.j();
            if ("".equals(j.get(j.size() - 1))) {
                this.c = sVar;
                return this;
            }
            throw new IllegalArgumentException("baseUrl must end in /: " + sVar);
        }

        public a a(retrofit2.e.a aVar) {
            this.d.add(o.a((Object) aVar, "factory == null"));
            return this;
        }

        public a a(retrofit2.c.a aVar) {
            this.e.add(o.a((Object) aVar, "factory == null"));
            return this;
        }

        public m a() {
            if (this.c == null) {
                throw new IllegalStateException("Base URL required.");
            }
            okhttp3.e.a aVar = this.b;
            if (aVar == null) {
                aVar = new w();
            }
            Executor executor = this.f;
            if (executor == null) {
                executor = this.a.b();
            }
            List arrayList = new ArrayList(this.e);
            arrayList.add(this.a.a(executor));
            return new m(aVar, this.c, new ArrayList(this.d), arrayList, executor, this.g);
        }
    }

    m(okhttp3.e.a aVar, s sVar, List<retrofit2.e.a> list, List<retrofit2.c.a> list2, Executor executor, boolean z) {
        this.a = aVar;
        this.b = sVar;
        this.c = Collections.unmodifiableList(list);
        this.d = Collections.unmodifiableList(list2);
        this.e = executor;
        this.f = z;
    }

    public <T> T a(final Class<T> cls) {
        o.a((Class) cls);
        if (this.f) {
            b(cls);
        }
        return Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new InvocationHandler(this) {
            final /* synthetic */ m b;
            private final j c = j.a();

            public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
                if (method.getDeclaringClass() == Object.class) {
                    return method.invoke(this, objArr);
                }
                if (this.c.a(method)) {
                    return this.c.a(method, cls, obj, objArr);
                }
                n a = this.b.a(method);
                return a.d.a(new h(a, objArr));
            }
        });
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
                    nVar = new a(this, method).a();
                    this.g.put(method, nVar);
                }
            }
        }
        return nVar;
    }

    public okhttp3.e.a a() {
        return this.a;
    }

    public s b() {
        return this.b;
    }

    public c<?, ?> a(Type type, Annotation[] annotationArr) {
        return a(null, type, annotationArr);
    }

    public c<?, ?> a(retrofit2.c.a aVar, Type type, Annotation[] annotationArr) {
        int i;
        o.a((Object) type, "returnType == null");
        o.a((Object) annotationArr, "annotations == null");
        int indexOf = this.d.indexOf(aVar) + 1;
        int size = this.d.size();
        for (i = indexOf; i < size; i++) {
            c<?, ?> a = ((retrofit2.c.a) this.d.get(i)).a(type, annotationArr, this);
            if (a != null) {
                return a;
            }
        }
        StringBuilder append = new StringBuilder("Could not locate call adapter for ").append(type).append(".\n");
        if (aVar != null) {
            append.append("  Skipped:");
            for (i = 0; i < indexOf; i++) {
                append.append("\n   * ").append(((retrofit2.c.a) this.d.get(i)).getClass().getName());
            }
            append.append('\n');
        }
        append.append("  Tried:");
        i = this.d.size();
        while (indexOf < i) {
            append.append("\n   * ").append(((retrofit2.c.a) this.d.get(indexOf)).getClass().getName());
            indexOf++;
        }
        throw new IllegalArgumentException(append.toString());
    }

    public <T> e<T, z> a(Type type, Annotation[] annotationArr, Annotation[] annotationArr2) {
        return a(null, type, annotationArr, annotationArr2);
    }

    public <T> e<T, z> a(retrofit2.e.a aVar, Type type, Annotation[] annotationArr, Annotation[] annotationArr2) {
        int i;
        o.a((Object) type, "type == null");
        o.a((Object) annotationArr, "parameterAnnotations == null");
        o.a((Object) annotationArr2, "methodAnnotations == null");
        int indexOf = this.c.indexOf(aVar) + 1;
        int size = this.c.size();
        for (i = indexOf; i < size; i++) {
            e<T, z> a = ((retrofit2.e.a) this.c.get(i)).a(type, annotationArr, annotationArr2, this);
            if (a != null) {
                return a;
            }
        }
        StringBuilder append = new StringBuilder("Could not locate RequestBody converter for ").append(type).append(".\n");
        if (aVar != null) {
            append.append("  Skipped:");
            for (i = 0; i < indexOf; i++) {
                append.append("\n   * ").append(((retrofit2.e.a) this.c.get(i)).getClass().getName());
            }
            append.append('\n');
        }
        append.append("  Tried:");
        i = this.c.size();
        while (indexOf < i) {
            append.append("\n   * ").append(((retrofit2.e.a) this.c.get(indexOf)).getClass().getName());
            indexOf++;
        }
        throw new IllegalArgumentException(append.toString());
    }

    public <T> e<ab, T> b(Type type, Annotation[] annotationArr) {
        return a(null, type, annotationArr);
    }

    public <T> e<ab, T> a(retrofit2.e.a aVar, Type type, Annotation[] annotationArr) {
        int i;
        o.a((Object) type, "type == null");
        o.a((Object) annotationArr, "annotations == null");
        int indexOf = this.c.indexOf(aVar) + 1;
        int size = this.c.size();
        for (i = indexOf; i < size; i++) {
            e<ab, T> a = ((retrofit2.e.a) this.c.get(i)).a(type, annotationArr, this);
            if (a != null) {
                return a;
            }
        }
        StringBuilder append = new StringBuilder("Could not locate ResponseBody converter for ").append(type).append(".\n");
        if (aVar != null) {
            append.append("  Skipped:");
            for (i = 0; i < indexOf; i++) {
                append.append("\n   * ").append(((retrofit2.e.a) this.c.get(i)).getClass().getName());
            }
            append.append('\n');
        }
        append.append("  Tried:");
        i = this.c.size();
        while (indexOf < i) {
            append.append("\n   * ").append(((retrofit2.e.a) this.c.get(indexOf)).getClass().getName());
            indexOf++;
        }
        throw new IllegalArgumentException(append.toString());
    }

    public <T> e<T, String> c(Type type, Annotation[] annotationArr) {
        o.a((Object) type, "type == null");
        o.a((Object) annotationArr, "annotations == null");
        int size = this.c.size();
        for (int i = 0; i < size; i++) {
            e<T, String> b = ((retrofit2.e.a) this.c.get(i)).b(type, annotationArr, this);
            if (b != null) {
                return b;
            }
        }
        return d.a;
    }
}
