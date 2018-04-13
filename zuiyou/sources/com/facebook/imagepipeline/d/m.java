package com.facebook.imagepipeline.d;

import android.net.Uri;
import com.facebook.common.internal.g;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.references.a;
import com.facebook.imagepipeline.g.c;
import com.facebook.imagepipeline.g.e;
import com.facebook.imagepipeline.producers.af;
import com.facebook.imagepipeline.producers.ai;
import com.facebook.imagepipeline.producers.an;
import com.facebook.imagepipeline.producers.as;
import com.facebook.imagepipeline.producers.av;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequest$RequestLevel;
import java.util.HashMap;
import java.util.Map;

public class m {
    ai<a<c>> a;
    ai<e> b;
    ai<e> c;
    ai<a<PooledByteBuffer>> d;
    ai<a<PooledByteBuffer>> e;
    ai<a<c>> f;
    ai<a<c>> g;
    ai<a<c>> h;
    ai<a<c>> i;
    ai<a<c>> j;
    ai<a<c>> k;
    ai<a<c>> l;
    Map<ai<a<c>>, ai<a<c>>> m = new HashMap();
    Map<ai<a<c>>, ai<Void>> n = new HashMap();
    private final l o;
    private final af p;
    private final boolean q;
    private final boolean r;
    private final as s;
    private final boolean t;
    private ai<e> u;

    public m(l lVar, af afVar, boolean z, boolean z2, as asVar, boolean z3) {
        this.o = lVar;
        this.p = afVar;
        this.q = z;
        this.r = z2;
        this.s = asVar;
        this.t = z3;
    }

    public ai<a<PooledByteBuffer>> a(ImageRequest imageRequest) {
        c(imageRequest);
        Uri b = imageRequest.b();
        switch (imageRequest.c()) {
            case 0:
                return a();
            case 2:
            case 3:
                return b();
            default:
                throw new IllegalArgumentException("Unsupported uri scheme for encoded image fetch! Uri is: " + a(b));
        }
    }

    public ai<a<PooledByteBuffer>> a() {
        synchronized (this) {
            if (this.e == null) {
                this.e = new an(d());
            }
        }
        return this.e;
    }

    public ai<a<PooledByteBuffer>> b() {
        synchronized (this) {
            if (this.d == null) {
                this.d = new an(f());
            }
        }
        return this.d;
    }

    private static void c(ImageRequest imageRequest) {
        g.a((Object) imageRequest);
        g.a(imageRequest.m().getValue() <= ImageRequest$RequestLevel.ENCODED_MEMORY_CACHE.getValue());
    }

    public ai<a<c>> b(ImageRequest imageRequest) {
        ai<a<c>> d = d(imageRequest);
        if (imageRequest.p() != null) {
            return f(d);
        }
        return d;
    }

    private ai<a<c>> d(ImageRequest imageRequest) {
        g.a((Object) imageRequest);
        Uri b = imageRequest.b();
        g.a((Object) b, (Object) "Uri is null.");
        switch (imageRequest.c()) {
            case 0:
                return c();
            case 2:
                return h();
            case 3:
                return g();
            case 4:
                return i();
            case 5:
                return l();
            case 6:
                return k();
            case 7:
                return m();
            case 8:
                return j();
            default:
                throw new IllegalArgumentException("Unsupported uri scheme! Uri is: " + a(b));
        }
    }

    private synchronized ai<a<c>> c() {
        if (this.a == null) {
            this.a = b(e());
        }
        return this.a;
    }

    private synchronized ai<e> d() {
        if (this.c == null) {
            this.c = this.o.a(e(), this.s);
        }
        return this.c;
    }

    private synchronized ai<e> e() {
        if (this.u == null) {
            this.u = l.a(c(this.o.a(this.p)));
            this.u = this.o.a(this.u, this.q, this.t);
        }
        return this.u;
    }

    private synchronized ai<e> f() {
        if (this.b == null) {
            this.b = this.o.a(c(this.o.f()), this.s);
        }
        return this.b;
    }

    private synchronized ai<a<c>> g() {
        if (this.f == null) {
            this.f = a(this.o.f());
        }
        return this.f;
    }

    private synchronized ai<a<c>> h() {
        if (this.g == null) {
            this.g = e(this.o.i());
        }
        return this.g;
    }

    private synchronized ai<a<c>> i() {
        if (this.h == null) {
            this.h = a(this.o.c(), new av[]{this.o.d(), this.o.e()});
        }
        return this.h;
    }

    private synchronized ai<a<c>> j() {
        if (this.l == null) {
            this.l = a(this.o.g());
        }
        return this.l;
    }

    private synchronized ai<a<c>> k() {
        if (this.i == null) {
            this.i = a(this.o.h());
        }
        return this.i;
    }

    private synchronized ai<a<c>> l() {
        if (this.j == null) {
            this.j = a(this.o.b());
        }
        return this.j;
    }

    private synchronized ai<a<c>> m() {
        if (this.k == null) {
            ai a = this.o.a();
            if (com.facebook.common.g.c.a && (!this.r || com.facebook.common.g.c.d == null)) {
                a = this.o.n(a);
            }
            l lVar = this.o;
            this.k = b(this.o.a(l.a(a), true, this.t));
        }
        return this.k;
    }

    private ai<a<c>> a(ai<e> aiVar) {
        return a(aiVar, new av[]{this.o.e()});
    }

    private ai<a<c>> a(ai<e> aiVar, av<e>[] avVarArr) {
        return b(b(c((ai) aiVar), avVarArr));
    }

    private ai<a<c>> b(ai<e> aiVar) {
        return e(this.o.e(aiVar));
    }

    private ai<e> c(ai<e> aiVar) {
        ai n;
        if (com.facebook.common.g.c.a && (!this.r || com.facebook.common.g.c.d == null)) {
            n = this.o.n(aiVar);
        }
        return this.o.i(this.o.j(d(n)));
    }

    private ai<e> d(ai<e> aiVar) {
        return this.o.f(this.o.h(this.o.g(aiVar)));
    }

    private ai<a<c>> e(ai<a<c>> aiVar) {
        return this.o.b(this.o.a(this.o.c(this.o.d(aiVar)), this.s));
    }

    private ai<e> b(ai<e> aiVar, av<e>[] avVarArr) {
        ai m = this.o.m(this.o.a(l.a(aiVar), true, this.t));
        l lVar = this.o;
        return l.a(a((av[]) avVarArr), m);
    }

    private ai<e> a(av<e>[] avVarArr) {
        return this.o.a(this.o.a(avVarArr), true, this.t);
    }

    private synchronized ai<a<c>> f(ai<a<c>> aiVar) {
        if (!this.m.containsKey(aiVar)) {
            this.m.put(aiVar, this.o.k(this.o.l(aiVar)));
        }
        return (ai) this.m.get(aiVar);
    }

    private static String a(Uri uri) {
        String valueOf = String.valueOf(uri);
        return valueOf.length() > 30 ? valueOf.substring(0, 30) + "..." : valueOf;
    }
}
