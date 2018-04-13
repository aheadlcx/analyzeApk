package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.internal.g;
import com.facebook.common.references.a;
import com.facebook.imagepipeline.g.c;
import com.facebook.imagepipeline.g.d;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

class ah$a extends m<a<c>, a<c>> {
    final /* synthetic */ ah a;
    private final al b;
    private final String c;
    private final com.facebook.imagepipeline.request.c d;
    @GuardedBy
    private boolean e;
    @GuardedBy
    @Nullable
    private a<c> f = null;
    @GuardedBy
    private boolean g = false;
    @GuardedBy
    private boolean h = false;
    @GuardedBy
    private boolean i = false;

    public ah$a(ah ahVar, j<a<c>> jVar, al alVar, String str, com.facebook.imagepipeline.request.c cVar, aj ajVar) {
        this.a = ahVar;
        super(jVar);
        this.b = alVar;
        this.c = str;
        this.d = cVar;
        ajVar.a(new ah$a$1(this, ahVar));
    }

    protected void a(a<c> aVar, boolean z) {
        if (a.a((a) aVar)) {
            b(aVar, z);
        } else if (z) {
            d(null, true);
        }
    }

    protected void a(Throwable th) {
        c(th);
    }

    protected void a() {
        g();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void b(@javax.annotation.Nullable com.facebook.common.references.a<com.facebook.imagepipeline.g.c> r3, boolean r4) {
        /*
        r2 = this;
        monitor-enter(r2);
        r0 = r2.e;	 Catch:{ all -> 0x0022 }
        if (r0 == 0) goto L_0x0007;
    L_0x0005:
        monitor-exit(r2);	 Catch:{ all -> 0x0022 }
    L_0x0006:
        return;
    L_0x0007:
        r0 = r2.f;	 Catch:{ all -> 0x0022 }
        r1 = com.facebook.common.references.a.b(r3);	 Catch:{ all -> 0x0022 }
        r2.f = r1;	 Catch:{ all -> 0x0022 }
        r2.g = r4;	 Catch:{ all -> 0x0022 }
        r1 = 1;
        r2.h = r1;	 Catch:{ all -> 0x0022 }
        r1 = r2.f();	 Catch:{ all -> 0x0022 }
        monitor-exit(r2);	 Catch:{ all -> 0x0022 }
        com.facebook.common.references.a.c(r0);
        if (r1 == 0) goto L_0x0006;
    L_0x001e:
        r2.c();
        goto L_0x0006;
    L_0x0022:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0022 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.ah$a.b(com.facebook.common.references.a, boolean):void");
    }

    private void c() {
        ah.a(this.a).execute(new ah$a$2(this));
    }

    private void e() {
        synchronized (this) {
            this.i = false;
            boolean f = f();
        }
        if (f) {
            c();
        }
    }

    private synchronized boolean f() {
        boolean z = true;
        synchronized (this) {
            if (this.e || !this.h || this.i || !a.a(this.f)) {
                z = false;
            } else {
                this.i = true;
            }
        }
        return z;
    }

    private void c(a<c> aVar, boolean z) {
        g.a(a.a((a) aVar));
        if (a((c) aVar.a())) {
            this.b.a(this.c, "PostprocessorProducer");
            a aVar2 = null;
            try {
                aVar2 = b((c) aVar.a());
                this.b.a(this.c, "PostprocessorProducer", a(this.b, this.c, this.d));
                d(aVar2, z);
            } catch (Throwable e) {
                this.b.a(this.c, "PostprocessorProducer", e, a(this.b, this.c, this.d));
                c(e);
            } finally {
                a.c(aVar2);
            }
        } else {
            d(aVar, z);
        }
    }

    private Map<String, String> a(al alVar, String str, com.facebook.imagepipeline.request.c cVar) {
        if (alVar.b(str)) {
            return ImmutableMap.of("Postprocessor", cVar.b());
        }
        return null;
    }

    private boolean a(c cVar) {
        return cVar instanceof d;
    }

    private a<c> b(c cVar) {
        d dVar = (d) cVar;
        a a = this.d.a(dVar.f(), ah.b(this.a));
        try {
            a<c> a2 = a.a(new d(a, cVar.g(), dVar.h()));
            return a2;
        } finally {
            a.c(a);
        }
    }

    private void d(a<c> aVar, boolean z) {
        if ((!z && !h()) || (z && i())) {
            d().b(aVar, z);
        }
    }

    private void c(Throwable th) {
        if (i()) {
            d().b(th);
        }
    }

    private void g() {
        if (i()) {
            d().b();
        }
    }

    private synchronized boolean h() {
        return this.e;
    }

    private boolean i() {
        boolean z = true;
        synchronized (this) {
            if (this.e) {
                z = false;
            } else {
                a aVar = this.f;
                this.f = null;
                this.e = true;
                a.c(aVar);
            }
        }
        return z;
    }
}
