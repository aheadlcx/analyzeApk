package com.facebook.imagepipeline.e;

import com.facebook.common.internal.g;
import com.facebook.datasource.AbstractDataSource;
import com.facebook.imagepipeline.h.b;
import com.facebook.imagepipeline.producers.ai;
import com.facebook.imagepipeline.producers.ap;
import com.facebook.imagepipeline.producers.j;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public abstract class a<T> extends AbstractDataSource<T> {
    private final ap a;
    private final b b;

    protected a(ai<T> aiVar, ap apVar, b bVar) {
        this.a = apVar;
        this.b = bVar;
        this.b.a(apVar.a(), this.a.d(), this.a.b(), this.a.f());
        aiVar.a(j(), apVar);
    }

    private j<T> j() {
        return new com.facebook.imagepipeline.producers.b<T>(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            protected void a(@Nullable T t, boolean z) {
                this.a.b(t, z);
            }

            protected void a(Throwable th) {
                this.a.b(th);
            }

            protected void a() {
                this.a.k();
            }

            protected void a(float f) {
                this.a.a(f);
            }
        };
    }

    protected void b(@Nullable T t, boolean z) {
        if (super.a(t, z) && z) {
            this.b.a(this.a.a(), this.a.b(), this.a.f());
        }
    }

    private void b(Throwable th) {
        if (super.a(th)) {
            this.b.a(this.a.a(), this.a.b(), th, this.a.f());
        }
    }

    private synchronized void k() {
        g.b(a());
    }

    public boolean h() {
        if (!super.h()) {
            return false;
        }
        if (!super.b()) {
            this.b.a(this.a.b());
            this.a.i();
        }
        return true;
    }
}
