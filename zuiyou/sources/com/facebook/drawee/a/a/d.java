package com.facebook.drawee.a.a;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import com.facebook.cache.common.b;
import com.facebook.common.internal.ImmutableList;
import com.facebook.common.internal.g;
import com.facebook.common.internal.i;
import com.facebook.drawee.controller.a;
import com.facebook.drawee.drawable.m;
import com.facebook.drawee.drawable.n;
import com.facebook.drawee.drawable.n$b;
import com.facebook.imagepipeline.c.t;
import com.facebook.imagepipeline.g.c;
import com.facebook.imagepipeline.g.f;
import java.util.Iterator;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

public class d extends a<com.facebook.common.references.a<c>, f> {
    private static final Class<?> a = d.class;
    private final Resources b;
    private final com.facebook.imagepipeline.animated.factory.a c;
    @Nullable
    private final ImmutableList<a> d;
    @Nullable
    private t<b, c> e;
    private b f;
    private i<com.facebook.datasource.b<com.facebook.common.references.a<c>>> g;
    private boolean h;
    private final a i = new d$1(this);

    protected /* synthetic */ void a(@Nullable Object obj) {
        d((com.facebook.common.references.a) obj);
    }

    protected /* synthetic */ int b(@Nullable Object obj) {
        return c((com.facebook.common.references.a) obj);
    }

    protected /* synthetic */ Object c() {
        return b();
    }

    protected /* synthetic */ Object c(Object obj) {
        return b((com.facebook.common.references.a) obj);
    }

    protected /* synthetic */ Drawable d(Object obj) {
        return a((com.facebook.common.references.a) obj);
    }

    public d(Resources resources, com.facebook.drawee.components.a aVar, com.facebook.imagepipeline.animated.factory.a aVar2, Executor executor, t<b, c> tVar, i<com.facebook.datasource.b<com.facebook.common.references.a<c>>> iVar, String str, b bVar, Object obj, @Nullable ImmutableList<a> immutableList) {
        super(aVar, executor, str, obj);
        this.b = resources;
        this.c = aVar2;
        this.e = tVar;
        this.f = bVar;
        this.d = immutableList;
        a((i) iVar);
    }

    public void a(i<com.facebook.datasource.b<com.facebook.common.references.a<c>>> iVar, String str, b bVar, Object obj) {
        super.a(str, obj);
        a((i) iVar);
        this.f = bVar;
    }

    public void a(boolean z) {
        this.h = z;
    }

    private void a(i<com.facebook.datasource.b<com.facebook.common.references.a<c>>> iVar) {
        this.g = iVar;
        a(null);
    }

    protected com.facebook.datasource.b<com.facebook.common.references.a<c>> a() {
        if (com.facebook.common.c.a.a(2)) {
            com.facebook.common.c.a.a(a, "controller %x: getDataSource", Integer.valueOf(System.identityHashCode(this)));
        }
        return (com.facebook.datasource.b) this.g.b();
    }

    protected Drawable a(com.facebook.common.references.a<c> aVar) {
        Drawable b;
        g.b(com.facebook.common.references.a.a((com.facebook.common.references.a) aVar));
        c cVar = (c) aVar.a();
        a(cVar);
        if (this.d != null) {
            Iterator it = this.d.iterator();
            while (it.hasNext()) {
                a aVar2 = (a) it.next();
                if (aVar2.a(cVar)) {
                    b = aVar2.b(cVar);
                    if (b != null) {
                        return b;
                    }
                }
            }
        }
        b = this.i.b(cVar);
        if (b != null) {
            return b;
        }
        throw new UnsupportedOperationException("Unrecognized image class: " + cVar);
    }

    public void a(@Nullable com.facebook.drawee.d.b bVar) {
        super.a(bVar);
        a(null);
    }

    private void a(@Nullable c cVar) {
        n$b n_b = null;
        if (this.h) {
            Drawable j = j();
            if (j == null) {
                j = new com.facebook.drawee.b.a();
                b(j);
            }
            if (j instanceof com.facebook.drawee.b.a) {
                com.facebook.drawee.b.a aVar = (com.facebook.drawee.b.a) j;
                aVar.a(e());
                com.facebook.drawee.d.b i = i();
                if (i != null) {
                    m a = n.a(i.a());
                    if (a != null) {
                        n_b = a.b();
                    }
                }
                aVar.a(n_b);
                if (cVar != null) {
                    aVar.a(cVar.a(), cVar.b());
                    aVar.a(cVar.d());
                    return;
                }
                aVar.a();
            }
        }
    }

    protected f b(com.facebook.common.references.a<c> aVar) {
        g.b(com.facebook.common.references.a.a((com.facebook.common.references.a) aVar));
        return (f) aVar.a();
    }

    protected int c(@Nullable com.facebook.common.references.a<c> aVar) {
        return aVar != null ? aVar.e() : 0;
    }

    protected void d(@Nullable com.facebook.common.references.a<c> aVar) {
        com.facebook.common.references.a.c(aVar);
    }

    protected void a(@Nullable Drawable drawable) {
        if (drawable instanceof com.facebook.b.a.a) {
            ((com.facebook.b.a.a) drawable).a();
        }
    }

    protected com.facebook.common.references.a<c> b() {
        if (this.e == null || this.f == null) {
            return null;
        }
        com.facebook.common.references.a<c> a = this.e.a(this.f);
        if (a == null || ((c) a.a()).g().c()) {
            return a;
        }
        a.close();
        return null;
    }

    public String toString() {
        return com.facebook.common.internal.f.a((Object) this).a("super", super.toString()).a("dataSourceSupplier", this.g).toString();
    }
}
