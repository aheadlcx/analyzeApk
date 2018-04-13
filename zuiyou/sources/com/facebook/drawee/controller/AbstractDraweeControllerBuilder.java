package com.facebook.drawee.controller;

import android.content.Context;
import com.facebook.common.internal.g;
import com.facebook.common.internal.i;
import com.facebook.datasource.b;
import com.facebook.datasource.c;
import com.facebook.datasource.e;
import com.facebook.datasource.f;
import com.facebook.drawee.d.a;
import com.facebook.drawee.d.d;
import com.facebook.infer.annotation.ReturnsOwnership;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Nullable;

public abstract class AbstractDraweeControllerBuilder<BUILDER extends AbstractDraweeControllerBuilder<BUILDER, REQUEST, IMAGE, INFO>, REQUEST, IMAGE, INFO> implements d {
    private static final c<Object> a = new AbstractDraweeControllerBuilder$1();
    private static final NullPointerException b = new NullPointerException("No image request was specified!");
    private static final AtomicLong r = new AtomicLong();
    private final Context c;
    private final Set<c> d;
    @Nullable
    private Object e;
    @Nullable
    private REQUEST f;
    @Nullable
    private REQUEST g;
    @Nullable
    private REQUEST[] h;
    private boolean i;
    @Nullable
    private i<b<IMAGE>> j;
    @Nullable
    private c<? super INFO> k;
    @Nullable
    private d l;
    private boolean m;
    private boolean n;
    private boolean o;
    private String p;
    @Nullable
    private a q;

    protected abstract b<IMAGE> a(REQUEST request, Object obj, AbstractDraweeControllerBuilder$CacheLevel abstractDraweeControllerBuilder$CacheLevel);

    protected abstract BUILDER c();

    @ReturnsOwnership
    protected abstract a d();

    public /* synthetic */ d b(@Nullable a aVar) {
        return a(aVar);
    }

    public /* synthetic */ d d(Object obj) {
        return a(obj);
    }

    public /* synthetic */ a p() {
        return k();
    }

    protected AbstractDraweeControllerBuilder(Context context, Set<c> set) {
        this.c = context;
        this.d = set;
        a();
    }

    private void a() {
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = true;
        this.k = null;
        this.l = null;
        this.m = false;
        this.n = false;
        this.q = null;
        this.p = null;
    }

    public BUILDER a(Object obj) {
        this.e = obj;
        return c();
    }

    @Nullable
    public Object e() {
        return this.e;
    }

    public BUILDER b(REQUEST request) {
        this.f = request;
        return c();
    }

    @Nullable
    public REQUEST f() {
        return this.f;
    }

    public BUILDER a(boolean z) {
        this.m = z;
        return c();
    }

    public boolean g() {
        return this.o;
    }

    public BUILDER b(boolean z) {
        this.n = z;
        return c();
    }

    public BUILDER a(c<? super INFO> cVar) {
        this.k = cVar;
        return c();
    }

    @Nullable
    public d h() {
        return this.l;
    }

    @Nullable
    public String i() {
        return this.p;
    }

    public BUILDER a(@Nullable a aVar) {
        this.q = aVar;
        return c();
    }

    @Nullable
    public a j() {
        return this.q;
    }

    public a k() {
        l();
        if (this.f == null && this.h == null && this.g != null) {
            this.f = this.g;
            this.g = null;
        }
        return m();
    }

    protected void l() {
        boolean z = false;
        boolean z2 = this.h == null || this.f == null;
        g.b(z2, "Cannot specify both ImageRequest and FirstAvailableImageRequests!");
        if (this.j == null || (this.h == null && this.f == null && this.g == null)) {
            z = true;
        }
        g.b(z, "Cannot specify DataSourceSupplier with other ImageRequests! Use one or the other.");
    }

    protected a m() {
        a d = d();
        d.b(g());
        d.a(i());
        d.a(h());
        b(d);
        a(d);
        return d;
    }

    protected static String n() {
        return String.valueOf(r.getAndIncrement());
    }

    protected i<b<IMAGE>> o() {
        if (this.j != null) {
            return this.j;
        }
        i<b<IMAGE>> iVar = null;
        if (this.f != null) {
            iVar = c(this.f);
        } else if (this.h != null) {
            iVar = a(this.h, this.i);
        }
        if (!(iVar == null || this.g == null)) {
            List arrayList = new ArrayList(2);
            arrayList.add(iVar);
            arrayList.add(c(this.g));
            iVar = f.a(arrayList);
        }
        if (iVar == null) {
            return c.b(b);
        }
        return iVar;
    }

    protected i<b<IMAGE>> a(REQUEST[] requestArr, boolean z) {
        int i = 0;
        List arrayList = new ArrayList(requestArr.length * 2);
        if (z) {
            for (Object a : requestArr) {
                arrayList.add(a(a, AbstractDraweeControllerBuilder$CacheLevel.BITMAP_MEMORY_CACHE));
            }
        }
        while (i < requestArr.length) {
            arrayList.add(c(requestArr[i]));
            i++;
        }
        return e.a(arrayList);
    }

    protected i<b<IMAGE>> c(REQUEST request) {
        return a((Object) request, AbstractDraweeControllerBuilder$CacheLevel.FULL_FETCH);
    }

    protected i<b<IMAGE>> a(REQUEST request, AbstractDraweeControllerBuilder$CacheLevel abstractDraweeControllerBuilder$CacheLevel) {
        return new AbstractDraweeControllerBuilder$2(this, request, e(), abstractDraweeControllerBuilder$CacheLevel);
    }

    protected void a(a aVar) {
        if (this.d != null) {
            for (c a : this.d) {
                aVar.a(a);
            }
        }
        if (this.k != null) {
            aVar.a(this.k);
        }
        if (this.n) {
            aVar.a(a);
        }
    }

    protected void b(a aVar) {
        if (this.m) {
            com.facebook.drawee.components.b f = aVar.f();
            if (f == null) {
                f = new com.facebook.drawee.components.b();
                aVar.a(f);
            }
            f.a(this.m);
            c(aVar);
        }
    }

    protected void c(a aVar) {
        if (aVar.g() == null) {
            aVar.a(com.facebook.drawee.c.a.a(this.c));
        }
    }
}
