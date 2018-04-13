package com.facebook.imagepipeline.producers;

import com.facebook.common.references.a;
import com.facebook.imagepipeline.g.c;
import com.facebook.imagepipeline.request.d;
import com.facebook.imagepipeline.request.e;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

class ah$b extends m<a<c>, a<c>> implements e {
    final /* synthetic */ ah a;
    @GuardedBy
    private boolean b;
    @GuardedBy
    @Nullable
    private a<c> c;

    private ah$b(ah ahVar, ah$a ah_a, d dVar, aj ajVar) {
        this.a = ahVar;
        super(ah_a);
        this.b = false;
        this.c = null;
        dVar.a(this);
        ajVar.a(new ah$b$1(this, ahVar));
    }

    protected void a(a<c> aVar, boolean z) {
        if (z) {
            a((a) aVar);
            c();
        }
    }

    protected void a(Throwable th) {
        if (e()) {
            d().b(th);
        }
    }

    protected void a() {
        if (e()) {
            d().b();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void c() {
        /*
        r3 = this;
        monitor-enter(r3);
        r0 = r3.b;	 Catch:{ all -> 0x001a }
        if (r0 == 0) goto L_0x0007;
    L_0x0005:
        monitor-exit(r3);	 Catch:{ all -> 0x001a }
    L_0x0006:
        return;
    L_0x0007:
        r0 = r3.c;	 Catch:{ all -> 0x001a }
        r1 = com.facebook.common.references.a.b(r0);	 Catch:{ all -> 0x001a }
        monitor-exit(r3);	 Catch:{ all -> 0x001a }
        r0 = r3.d();	 Catch:{ all -> 0x001d }
        r2 = 0;
        r0.b(r1, r2);	 Catch:{ all -> 0x001d }
        com.facebook.common.references.a.c(r1);
        goto L_0x0006;
    L_0x001a:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x001a }
        throw r0;
    L_0x001d:
        r0 = move-exception;
        com.facebook.common.references.a.c(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.ah$b.c():void");
    }

    private void a(a<c> aVar) {
        synchronized (this) {
            if (this.b) {
                return;
            }
            a aVar2 = this.c;
            this.c = a.b(aVar);
            a.c(aVar2);
        }
    }

    private boolean e() {
        boolean z = true;
        synchronized (this) {
            if (this.b) {
                z = false;
            } else {
                a aVar = this.c;
                this.c = null;
                this.b = true;
                a.c(aVar);
            }
        }
        return z;
    }
}
