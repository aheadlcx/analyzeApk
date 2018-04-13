package retrofit2;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.concurrent.Executor;
import okhttp3.y;

final class g extends retrofit2.c.a {
    final Executor a;

    static final class a<T> implements b<T> {
        final Executor a;
        final b<T> b;

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            return d();
        }

        a(Executor executor, b<T> bVar) {
            this.a = executor;
            this.b = bVar;
        }

        public void a(final d<T> dVar) {
            if (dVar == null) {
                throw new NullPointerException("callback == null");
            }
            this.b.a(new d<T>(this) {
                final /* synthetic */ a b;

                public void onResponse(b<T> bVar, final l<T> lVar) {
                    this.b.a.execute(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 b;

                        public void run() {
                            if (this.b.b.b.c()) {
                                dVar.onFailure(this.b.b, new IOException("Canceled"));
                            } else {
                                dVar.onResponse(this.b.b, lVar);
                            }
                        }
                    });
                }

                public void onFailure(b<T> bVar, final Throwable th) {
                    this.b.a.execute(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 b;

                        public void run() {
                            dVar.onFailure(this.b.b, th);
                        }
                    });
                }
            });
        }

        public l<T> a() throws IOException {
            return this.b.a();
        }

        public void b() {
            this.b.b();
        }

        public boolean c() {
            return this.b.c();
        }

        public b<T> d() {
            return new a(this.a, this.b.d());
        }

        public y e() {
            return this.b.e();
        }
    }

    g(Executor executor) {
        this.a = executor;
    }

    public c<?, ?> a(Type type, Annotation[] annotationArr, m mVar) {
        if (retrofit2.c.a.a(type) != b.class) {
            return null;
        }
        final Type e = o.e(type);
        return new c<Object, b<?>>(this) {
            final /* synthetic */ g b;

            public /* synthetic */ Object a(b bVar) {
                return b(bVar);
            }

            public Type a() {
                return e;
            }

            public b<Object> b(b<Object> bVar) {
                return new a(this.b.a, bVar);
            }
        };
    }
}
