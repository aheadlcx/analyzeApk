package com.facebook.imagepipeline.d;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap.Config;
import com.facebook.common.g.a;
import com.facebook.common.internal.g;
import com.facebook.common.internal.i;
import com.facebook.common.memory.c;
import com.facebook.imagepipeline.animated.factory.f;
import com.facebook.imagepipeline.c.j;
import com.facebook.imagepipeline.c.k;
import com.facebook.imagepipeline.c.o;
import com.facebook.imagepipeline.c.u;
import com.facebook.imagepipeline.c.x;
import com.facebook.imagepipeline.f.b;
import com.facebook.imagepipeline.f.d;
import com.facebook.imagepipeline.memory.PoolConfig;
import com.facebook.imagepipeline.memory.PoolFactory;
import com.facebook.imagepipeline.producers.af;
import com.facebook.imagepipeline.producers.t;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nullable;

public class h {
    private static h$b x = new h$b(null);
    @Nullable
    private final f a;
    private final Config b;
    private final i<u> c;
    private final com.facebook.imagepipeline.c.f d;
    private final Context e;
    private final boolean f;
    private final f g;
    private final i<u> h;
    private final e i;
    private final o j;
    @Nullable
    private final b k;
    private final i<Boolean> l;
    private final com.facebook.cache.disk.b m;
    private final c n;
    private final af o;
    @Nullable
    private final com.facebook.imagepipeline.b.f p;
    private final PoolFactory q;
    private final d r;
    private final Set<com.facebook.imagepipeline.h.b> s;
    private final boolean t;
    private final com.facebook.cache.disk.b u;
    @Nullable
    private final com.facebook.imagepipeline.f.c v;
    private final i w;

    private h(h$a h_a) {
        i iVar;
        Config config;
        com.facebook.imagepipeline.c.f a;
        f bVar;
        o i;
        com.facebook.cache.disk.b b;
        c a2;
        af tVar;
        PoolFactory poolFactory;
        d fVar;
        Set hashSet;
        e aVar;
        this.w = h$a.a(h_a).a();
        this.a = h$a.b(h_a);
        if (h$a.c(h_a) == null) {
            iVar = new com.facebook.imagepipeline.c.i((ActivityManager) h$a.d(h_a).getSystemService("activity"));
        } else {
            iVar = h$a.c(h_a);
        }
        this.c = iVar;
        if (h$a.e(h_a) == null) {
            config = Config.ARGB_8888;
        } else {
            config = h$a.e(h_a);
        }
        this.b = config;
        if (h$a.f(h_a) == null) {
            a = j.a();
        } else {
            a = h$a.f(h_a);
        }
        this.d = a;
        this.e = (Context) g.a(h$a.d(h_a));
        if (h$a.g(h_a) == null) {
            bVar = new b(new d());
        } else {
            bVar = h$a.g(h_a);
        }
        this.g = bVar;
        this.f = h$a.h(h_a);
        if (h$a.i(h_a) == null) {
            iVar = new k();
        } else {
            iVar = h$a.i(h_a);
        }
        this.h = iVar;
        if (h$a.j(h_a) == null) {
            i = x.i();
        } else {
            i = h$a.j(h_a);
        }
        this.j = i;
        this.k = h$a.k(h_a);
        if (h$a.l(h_a) == null) {
            iVar = new h$1(this);
        } else {
            iVar = h$a.l(h_a);
        }
        this.l = iVar;
        if (h$a.m(h_a) == null) {
            b = b(h$a.d(h_a));
        } else {
            b = h$a.m(h_a);
        }
        this.m = b;
        if (h$a.n(h_a) == null) {
            a2 = com.facebook.common.memory.d.a();
        } else {
            a2 = h$a.n(h_a);
        }
        this.n = a2;
        if (h$a.o(h_a) == null) {
            tVar = new t();
        } else {
            tVar = h$a.o(h_a);
        }
        this.o = tVar;
        this.p = h$a.p(h_a);
        if (h$a.q(h_a) == null) {
            poolFactory = new PoolFactory(PoolConfig.newBuilder().build());
        } else {
            poolFactory = h$a.q(h_a);
        }
        this.q = poolFactory;
        if (h$a.r(h_a) == null) {
            fVar = new com.facebook.imagepipeline.f.f();
        } else {
            fVar = h$a.r(h_a);
        }
        this.r = fVar;
        if (h$a.s(h_a) == null) {
            hashSet = new HashSet();
        } else {
            hashSet = h$a.s(h_a);
        }
        this.s = hashSet;
        this.t = h$a.t(h_a);
        if (h$a.u(h_a) == null) {
            b = this.m;
        } else {
            b = h$a.u(h_a);
        }
        this.u = b;
        this.v = h$a.v(h_a);
        int flexByteArrayPoolMaxNumThreads = this.q.getFlexByteArrayPoolMaxNumThreads();
        if (h$a.w(h_a) == null) {
            aVar = new a(flexByteArrayPoolMaxNumThreads);
        } else {
            aVar = h$a.w(h_a);
        }
        this.i = aVar;
        com.facebook.common.g.b i2 = this.w.i();
        if (i2 != null) {
            a(i2, this.w, new com.facebook.imagepipeline.b.d(p()));
        } else if (this.w.f() && com.facebook.common.g.c.a) {
            i2 = com.facebook.common.g.c.a();
            if (i2 != null) {
                a(i2, this.w, new com.facebook.imagepipeline.b.d(p()));
            }
        }
    }

    private static void a(com.facebook.common.g.b bVar, i iVar, a aVar) {
        com.facebook.common.g.c.d = bVar;
        com.facebook.common.g.b.a h = iVar.h();
        if (h != null) {
            bVar.a(h);
        }
        if (aVar != null) {
            bVar.a(aVar);
        }
    }

    private static com.facebook.cache.disk.b b(Context context) {
        return com.facebook.cache.disk.b.a(context).a();
    }

    public Config a() {
        return this.b;
    }

    public i<u> b() {
        return this.c;
    }

    public com.facebook.imagepipeline.c.f c() {
        return this.d;
    }

    public Context d() {
        return this.e;
    }

    public static h$b e() {
        return x;
    }

    public f f() {
        return this.g;
    }

    public boolean g() {
        return this.f;
    }

    public i<u> h() {
        return this.h;
    }

    public e i() {
        return this.i;
    }

    public o j() {
        return this.j;
    }

    @Nullable
    public b k() {
        return this.k;
    }

    public i<Boolean> l() {
        return this.l;
    }

    public com.facebook.cache.disk.b m() {
        return this.m;
    }

    public c n() {
        return this.n;
    }

    public af o() {
        return this.o;
    }

    public PoolFactory p() {
        return this.q;
    }

    public d q() {
        return this.r;
    }

    public Set<com.facebook.imagepipeline.h.b> r() {
        return Collections.unmodifiableSet(this.s);
    }

    public boolean s() {
        return this.t;
    }

    public com.facebook.cache.disk.b t() {
        return this.u;
    }

    @Nullable
    public com.facebook.imagepipeline.f.c u() {
        return this.v;
    }

    public i v() {
        return this.w;
    }

    public static h$a a(Context context) {
        return new h$a(context, null);
    }
}
