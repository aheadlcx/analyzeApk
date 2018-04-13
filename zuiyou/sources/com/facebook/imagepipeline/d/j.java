package com.facebook.imagepipeline.d;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.v4.util.Pools.SynchronizedPool;
import com.facebook.cache.common.b;
import com.facebook.common.internal.g;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.imagepipeline.animated.factory.d;
import com.facebook.imagepipeline.b.f;
import com.facebook.imagepipeline.c.a;
import com.facebook.imagepipeline.c.e;
import com.facebook.imagepipeline.c.h;
import com.facebook.imagepipeline.c.m;
import com.facebook.imagepipeline.c.n;
import com.facebook.imagepipeline.c.r;
import com.facebook.imagepipeline.c.s;
import com.facebook.imagepipeline.c.t;
import com.facebook.imagepipeline.c.y;
import com.facebook.imagepipeline.g.c;
import com.facebook.imagepipeline.memory.PoolFactory;
import com.facebook.imagepipeline.producers.as;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class j {
    private static j a = null;
    private final as b;
    private final h c;
    private h<b, c> d;
    private t<b, c> e;
    private h<b, PooledByteBuffer> f;
    private t<b, PooledByteBuffer> g;
    private e h;
    private com.facebook.cache.disk.h i;
    private com.facebook.imagepipeline.f.b j;
    private g k;
    private l l;
    private m m;
    private e n;
    private com.facebook.cache.disk.h o;
    private r p;
    private f q;
    private com.facebook.imagepipeline.i.e r;
    private com.facebook.imagepipeline.animated.factory.c s;

    public static j a() {
        return (j) g.a(a, (Object) "ImagePipelineFactory was not initialized!");
    }

    public static void a(Context context) {
        a(h.a(context).a());
    }

    public static void a(h hVar) {
        a = new j(hVar);
    }

    public j(h hVar) {
        this.c = (h) g.a((Object) hVar);
        this.b = new as(hVar.i().e());
    }

    public com.facebook.imagepipeline.animated.factory.c b() {
        if (this.s == null) {
            this.s = d.a(j(), this.c.i());
        }
        return this.s;
    }

    public h<b, c> c() {
        if (this.d == null) {
            this.d = a.a(this.c.b(), this.c.n(), j(), this.c.v().a());
        }
        return this.d;
    }

    public t<b, c> d() {
        if (this.e == null) {
            this.e = com.facebook.imagepipeline.c.b.a(c(), this.c.j());
        }
        return this.e;
    }

    public h<b, PooledByteBuffer> e() {
        if (this.f == null) {
            this.f = m.a(this.c.h(), this.c.n(), j());
        }
        return this.f;
    }

    public t<b, PooledByteBuffer> f() {
        if (this.g == null) {
            this.g = n.a(e(), this.c.j());
        }
        return this.g;
    }

    private com.facebook.imagepipeline.f.b n() {
        if (this.j == null) {
            if (this.c.k() != null) {
                this.j = this.c.k();
            } else {
                com.facebook.imagepipeline.animated.factory.f a;
                if (b() != null) {
                    a = b().a();
                } else {
                    a = null;
                }
                if (this.c.u() == null) {
                    this.j = new com.facebook.imagepipeline.f.a(a, k(), this.c.a());
                } else {
                    this.j = new com.facebook.imagepipeline.f.a(a, k(), this.c.a(), this.c.u().a());
                    com.facebook.c.d.a().a(this.c.u().b());
                }
            }
        }
        return this.j;
    }

    public e g() {
        if (this.h == null) {
            this.h = new e(h(), this.c.p().getPooledByteBufferFactory(), this.c.p().getPooledByteStreams(), this.c.i().a(), this.c.i().b(), this.c.j());
        }
        return this.h;
    }

    public com.facebook.cache.disk.h h() {
        if (this.i == null) {
            this.i = this.c.f().a(this.c.m());
        }
        return this.i;
    }

    public g i() {
        if (this.k == null) {
            this.k = new g(p(), this.c.r(), this.c.l(), d(), f(), g(), q(), this.c.c(), this.b, com.facebook.common.internal.j.a(Boolean.valueOf(false)));
        }
        return this.k;
    }

    public static f a(PoolFactory poolFactory, com.facebook.imagepipeline.i.e eVar) {
        if (VERSION.SDK_INT >= 21) {
            return new com.facebook.imagepipeline.b.a(poolFactory.getBitmapPool());
        }
        if (VERSION.SDK_INT >= 11) {
            return new com.facebook.imagepipeline.b.e(new com.facebook.imagepipeline.b.b(poolFactory.getPooledByteBufferFactory()), eVar);
        }
        return new com.facebook.imagepipeline.b.c();
    }

    public f j() {
        if (this.q == null) {
            this.q = a(this.c.p(), k());
        }
        return this.q;
    }

    public static com.facebook.imagepipeline.i.e a(PoolFactory poolFactory, boolean z) {
        if (VERSION.SDK_INT >= 21) {
            int flexByteArrayPoolMaxNumThreads = poolFactory.getFlexByteArrayPoolMaxNumThreads();
            return new com.facebook.imagepipeline.i.a(poolFactory.getBitmapPool(), flexByteArrayPoolMaxNumThreads, new SynchronizedPool(flexByteArrayPoolMaxNumThreads));
        } else if (!z || VERSION.SDK_INT >= 19) {
            return new com.facebook.imagepipeline.i.d(poolFactory.getFlexByteArrayPool());
        } else {
            return new com.facebook.imagepipeline.i.c();
        }
    }

    public com.facebook.imagepipeline.i.e k() {
        if (this.r == null) {
            this.r = a(this.c.p(), this.c.v().f());
        }
        return this.r;
    }

    private l o() {
        if (this.l == null) {
            this.l = new l(this.c.d(), this.c.p().getSmallByteArrayPool(), n(), this.c.q(), this.c.g(), this.c.s(), this.c.v().g(), this.c.i(), this.c.p().getPooledByteBufferFactory(), d(), f(), g(), q(), m(), this.c.v().d(), this.c.c(), j(), this.c.v().b());
        }
        return this.l;
    }

    private m p() {
        if (this.m == null) {
            this.m = new m(o(), this.c.o(), this.c.s(), this.c.v().f(), this.b, this.c.v().e());
        }
        return this.m;
    }

    public com.facebook.cache.disk.h l() {
        if (this.o == null) {
            this.o = this.c.f().a(this.c.t());
        }
        return this.o;
    }

    private e q() {
        if (this.n == null) {
            this.n = new e(l(), this.c.p().getPooledByteBufferFactory(), this.c.p().getPooledByteStreams(), this.c.i().a(), this.c.i().b(), this.c.j());
        }
        return this.n;
    }

    public r m() {
        if (this.p == null) {
            this.p = this.c.v().c() ? new s(this.c.d(), this.c.i().a(), this.c.i().b()) : new y();
        }
        return this.p;
    }
}
