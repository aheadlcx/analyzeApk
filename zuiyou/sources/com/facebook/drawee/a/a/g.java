package com.facebook.drawee.a.a;

import android.content.res.Resources;
import com.facebook.cache.common.b;
import com.facebook.common.internal.ImmutableList;
import com.facebook.common.internal.i;
import com.facebook.drawee.components.a;
import com.facebook.imagepipeline.c.t;
import com.facebook.imagepipeline.g.c;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

public class g {
    private Resources a;
    private a b;
    private com.facebook.imagepipeline.animated.factory.a c;
    private Executor d;
    private t<b, c> e;
    @Nullable
    private ImmutableList<a> f;
    @Nullable
    private i<Boolean> g;

    public void a(Resources resources, a aVar, com.facebook.imagepipeline.animated.factory.a aVar2, Executor executor, t<b, c> tVar, @Nullable ImmutableList<a> immutableList, @Nullable i<Boolean> iVar) {
        this.a = resources;
        this.b = aVar;
        this.c = aVar2;
        this.d = executor;
        this.e = tVar;
        this.f = immutableList;
        this.g = iVar;
    }

    public d a(i<com.facebook.datasource.b<com.facebook.common.references.a<c>>> iVar, String str, b bVar, Object obj) {
        com.facebook.common.internal.g.b(this.a != null, "init() not called");
        d a = a(this.a, this.b, this.c, this.d, this.e, this.f, iVar, str, bVar, obj);
        if (this.g != null) {
            a.a(((Boolean) this.g.b()).booleanValue());
        }
        return a;
    }

    protected d a(Resources resources, a aVar, com.facebook.imagepipeline.animated.factory.a aVar2, Executor executor, t<b, c> tVar, @Nullable ImmutableList<a> immutableList, i<com.facebook.datasource.b<com.facebook.common.references.a<c>>> iVar, String str, b bVar, Object obj) {
        return new d(resources, aVar, aVar2, executor, tVar, iVar, str, bVar, obj, immutableList);
    }
}
