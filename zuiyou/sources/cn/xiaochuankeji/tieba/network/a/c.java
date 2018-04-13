package cn.xiaochuankeji.tieba.network.a;

import cn.xiaochuankeji.tieba.network.a;
import java.io.IOException;
import java.util.concurrent.Executor;
import retrofit2.b;
import retrofit2.d;
import retrofit2.l;

public class c<T> implements b<T> {
    private final b<T> a;
    private final Executor b;
    private final a c;

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return d();
    }

    public c(b<T> bVar, Executor executor, a aVar) {
        this.a = bVar;
        this.b = executor;
        this.c = aVar;
    }

    private void a(final Throwable th) {
        if (this.c != null) {
            this.b.execute(new Runnable(this) {
                final /* synthetic */ c b;

                public void run() {
                    this.b.c.a(th);
                }
            });
        }
    }

    private void a(final l lVar) {
        if (this.c != null) {
            this.b.execute(new Runnable(this) {
                final /* synthetic */ c b;

                public void run() {
                    this.b.c.a(lVar.a());
                }
            });
        }
    }

    public l<T> a() throws IOException {
        try {
            l a = this.a.a();
            if (!a.d()) {
                a(a);
            }
            return a;
        } catch (Throwable th) {
            a(th);
        }
    }

    public void a(final d<T> dVar) {
        this.a.a(new d<T>(this) {
            final /* synthetic */ c b;

            public void a(b<T> bVar, l<T> lVar) {
                if (dVar != null) {
                    dVar.a(bVar, lVar);
                }
                if (!lVar.d()) {
                    this.b.a((l) lVar);
                }
            }

            public void a(b<T> bVar, Throwable th) {
                if (dVar != null) {
                    dVar.a(bVar, th);
                }
                this.b.a(th);
            }
        });
    }

    public void b() {
        this.a.b();
    }

    public boolean c() {
        return this.a.c();
    }

    public b<T> d() {
        return new c(this.a.d(), this.b, this.c);
    }
}
