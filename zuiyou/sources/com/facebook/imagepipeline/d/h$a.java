package com.facebook.imagepipeline.d;

import android.content.Context;
import android.graphics.Bitmap.Config;
import com.facebook.common.internal.g;
import com.facebook.common.internal.i;
import com.facebook.common.memory.c;
import com.facebook.imagepipeline.animated.factory.f;
import com.facebook.imagepipeline.c.o;
import com.facebook.imagepipeline.c.u;
import com.facebook.imagepipeline.f.b;
import com.facebook.imagepipeline.f.d;
import com.facebook.imagepipeline.memory.PoolFactory;
import com.facebook.imagepipeline.producers.af;
import java.util.Set;

public class h$a {
    private f a;
    private Config b;
    private i<u> c;
    private com.facebook.imagepipeline.c.f d;
    private final Context e;
    private boolean f;
    private i<u> g;
    private e h;
    private o i;
    private b j;
    private i<Boolean> k;
    private com.facebook.cache.disk.b l;
    private c m;
    private af n;
    private com.facebook.imagepipeline.b.f o;
    private PoolFactory p;
    private d q;
    private Set<com.facebook.imagepipeline.h.b> r;
    private boolean s;
    private com.facebook.cache.disk.b t;
    private f u;
    private com.facebook.imagepipeline.f.c v;
    private final i$a w;

    private h$a(Context context) {
        this.f = false;
        this.s = true;
        this.w = new i$a(this);
        this.e = (Context) g.a((Object) context);
    }

    public h$a a(Config config) {
        this.b = config;
        return this;
    }

    public h$a a(boolean z) {
        this.f = z;
        return this;
    }

    public h$a a(com.facebook.cache.disk.b bVar) {
        this.l = bVar;
        return this;
    }

    public h$a a(af afVar) {
        this.n = afVar;
        return this;
    }

    public h$a a(PoolFactory poolFactory) {
        this.p = poolFactory;
        return this;
    }

    public h a() {
        return new h(this, null);
    }
}
