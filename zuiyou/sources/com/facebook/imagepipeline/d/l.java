package com.facebook.imagepipeline.d;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.memory.a;
import com.facebook.common.memory.g;
import com.facebook.imagepipeline.c.aa;
import com.facebook.imagepipeline.c.e;
import com.facebook.imagepipeline.c.f;
import com.facebook.imagepipeline.c.q;
import com.facebook.imagepipeline.c.r;
import com.facebook.imagepipeline.c.t;
import com.facebook.imagepipeline.c.z;
import com.facebook.imagepipeline.f.b;
import com.facebook.imagepipeline.f.d;
import com.facebook.imagepipeline.g.c;
import com.facebook.imagepipeline.producers.ab;
import com.facebook.imagepipeline.producers.ac;
import com.facebook.imagepipeline.producers.ae;
import com.facebook.imagepipeline.producers.af;
import com.facebook.imagepipeline.producers.ag;
import com.facebook.imagepipeline.producers.ah;
import com.facebook.imagepipeline.producers.ai;
import com.facebook.imagepipeline.producers.am;
import com.facebook.imagepipeline.producers.ao;
import com.facebook.imagepipeline.producers.ar;
import com.facebook.imagepipeline.producers.as;
import com.facebook.imagepipeline.producers.at;
import com.facebook.imagepipeline.producers.au;
import com.facebook.imagepipeline.producers.av;
import com.facebook.imagepipeline.producers.ax;
import com.facebook.imagepipeline.producers.h;
import com.facebook.imagepipeline.producers.i;
import com.facebook.imagepipeline.producers.k;
import com.facebook.imagepipeline.producers.n;
import com.facebook.imagepipeline.producers.o;
import com.facebook.imagepipeline.producers.u;
import com.facebook.imagepipeline.producers.v;
import com.facebook.imagepipeline.producers.w;
import com.facebook.imagepipeline.producers.x;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

public class l {
    private ContentResolver a;
    private Resources b;
    private AssetManager c;
    private final a d;
    private final b e;
    private final d f;
    private final boolean g;
    private final boolean h;
    private final boolean i;
    private final e j;
    private final g k;
    private final e l;
    private final e m;
    private final com.facebook.imagepipeline.c.l n;
    private final t<com.facebook.cache.common.b, PooledByteBuffer> o;
    private final t<com.facebook.cache.common.b, c> p;
    private final f q;
    private final r r;
    @Nullable
    private final q s;
    private final com.facebook.imagepipeline.b.f t;

    public l(Context context, a aVar, b bVar, d dVar, boolean z, boolean z2, boolean z3, e eVar, g gVar, t<com.facebook.cache.common.b, c> tVar, t<com.facebook.cache.common.b, PooledByteBuffer> tVar2, e eVar2, e eVar3, r rVar, @Nullable q qVar, f fVar, com.facebook.imagepipeline.b.f fVar2, int i) {
        this.a = context.getApplicationContext().getContentResolver();
        this.b = context.getApplicationContext().getResources();
        this.c = context.getApplicationContext().getAssets();
        this.d = aVar;
        this.e = bVar;
        this.f = dVar;
        this.g = z;
        this.h = z2;
        this.i = z3;
        this.j = eVar;
        this.k = gVar;
        this.p = tVar;
        this.o = tVar2;
        this.l = eVar2;
        this.m = eVar3;
        this.r = rVar;
        this.s = qVar;
        this.q = fVar;
        this.t = fVar2;
        if (i > 0) {
            this.n = new aa(eVar2, eVar3, fVar, i);
        } else {
            this.n = new z(eVar2, eVar3, fVar);
        }
    }

    public static com.facebook.imagepipeline.producers.a a(ai<com.facebook.imagepipeline.g.e> aiVar) {
        return new com.facebook.imagepipeline.producers.a(aiVar);
    }

    public com.facebook.imagepipeline.producers.f b(ai<com.facebook.common.references.a<c>> aiVar) {
        return new com.facebook.imagepipeline.producers.f(this.p, this.q, aiVar);
    }

    public com.facebook.imagepipeline.producers.g c(ai<com.facebook.common.references.a<c>> aiVar) {
        return new com.facebook.imagepipeline.producers.g(this.q, aiVar);
    }

    public h d(ai<com.facebook.common.references.a<c>> aiVar) {
        return new h(this.p, this.q, aiVar);
    }

    public static i a(ai<com.facebook.imagepipeline.g.e> aiVar, ai<com.facebook.imagepipeline.g.e> aiVar2) {
        return new i(aiVar, aiVar2);
    }

    public k a() {
        return new k(this.k);
    }

    public com.facebook.imagepipeline.producers.l e(ai<com.facebook.imagepipeline.g.e> aiVar) {
        return new com.facebook.imagepipeline.producers.l(this.d, this.j.c(), this.e, this.f, this.g, this.h, this.i, aiVar);
    }

    public n f(ai<com.facebook.imagepipeline.g.e> aiVar) {
        return new n(aiVar, this.n);
    }

    public o g(ai<com.facebook.imagepipeline.g.e> aiVar) {
        return new o(aiVar, this.n);
    }

    public ac h(ai<com.facebook.imagepipeline.g.e> aiVar) {
        return new ac(this.l, this.m, this.q, this.r, this.s, this.n, aiVar);
    }

    public com.facebook.imagepipeline.producers.q i(ai<com.facebook.imagepipeline.g.e> aiVar) {
        return new com.facebook.imagepipeline.producers.q(this.q, aiVar);
    }

    public com.facebook.imagepipeline.producers.r j(ai<com.facebook.imagepipeline.g.e> aiVar) {
        return new com.facebook.imagepipeline.producers.r(this.o, this.q, aiVar);
    }

    public u b() {
        return new u(this.j.a(), this.k, this.c);
    }

    public v c() {
        return new v(this.j.a(), this.k, this.a);
    }

    public w d() {
        return new w(this.j.a(), this.k, this.a);
    }

    public x e() {
        return new x(this.j.a(), this.k, this.a);
    }

    public au a(av<com.facebook.imagepipeline.g.e>[] avVarArr) {
        return new au(avVarArr);
    }

    public com.facebook.imagepipeline.producers.z f() {
        return new com.facebook.imagepipeline.producers.z(this.j.a(), this.k);
    }

    public am g() {
        return new am(this.j.a(), this.k, this.a);
    }

    public com.facebook.imagepipeline.producers.aa h() {
        return new com.facebook.imagepipeline.producers.aa(this.j.a(), this.k, this.b);
    }

    public ab i() {
        return new ab(this.j.a());
    }

    public ae a(af afVar) {
        return new ae(this.k, this.d, afVar);
    }

    public ag k(ai<com.facebook.common.references.a<c>> aiVar) {
        return new ag(this.p, this.q, aiVar);
    }

    public ah l(ai<com.facebook.common.references.a<c>> aiVar) {
        return new ah(aiVar, this.t, this.j.d());
    }

    public ao a(ai<com.facebook.imagepipeline.g.e> aiVar, boolean z, boolean z2) {
        Executor d = this.j.d();
        g gVar = this.k;
        boolean z3 = z && !this.g;
        return new ao(d, gVar, z3, aiVar, z2);
    }

    public <T> ar<T> a(ai<T> aiVar, as asVar) {
        return new ar(aiVar, asVar);
    }

    public <T> at<T> m(ai<T> aiVar) {
        return new at(5, this.j.e(), aiVar);
    }

    public ax n(ai<com.facebook.imagepipeline.g.e> aiVar) {
        return new ax(this.j.d(), this.k, aiVar);
    }
}
