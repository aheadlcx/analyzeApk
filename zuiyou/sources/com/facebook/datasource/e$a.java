package com.facebook.datasource;

import com.facebook.common.b.a;
import com.facebook.common.internal.i;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
class e$a extends AbstractDataSource<T> {
    final /* synthetic */ e a;
    private int b = 0;
    private b<T> c = null;
    private b<T> d = null;

    public e$a(e eVar) {
        this.a = eVar;
        if (!j()) {
            a(new RuntimeException("No data source supplier or supplier returned null."));
        }
    }

    @Nullable
    public synchronized T d() {
        b l;
        l = l();
        return l != null ? l.d() : null;
    }

    public synchronized boolean c() {
        boolean z;
        b l = l();
        z = l != null && l.c();
        return z;
    }

    public boolean h() {
        synchronized (this) {
            if (super.h()) {
                b bVar = this.c;
                this.c = null;
                b bVar2 = this.d;
                this.d = null;
                e(bVar2);
                e(bVar);
                return true;
            }
            return false;
        }
    }

    private boolean j() {
        i k = k();
        b bVar = k != null ? (b) k.b() : null;
        if (!a(bVar) || bVar == null) {
            e(bVar);
            return false;
        }
        bVar.a(new e$a$a(this, null), a.a());
        return true;
    }

    @Nullable
    private synchronized i<b<T>> k() {
        i<b<T>> iVar;
        if (a() || this.b >= e.a(this.a).size()) {
            iVar = null;
        } else {
            List a = e.a(this.a);
            int i = this.b;
            this.b = i + 1;
            iVar = (i) a.get(i);
        }
        return iVar;
    }

    private synchronized boolean a(b<T> bVar) {
        boolean z;
        if (a()) {
            z = false;
        } else {
            this.c = bVar;
            z = true;
        }
        return z;
    }

    private synchronized boolean b(b<T> bVar) {
        boolean z;
        if (a() || bVar != this.c) {
            z = false;
        } else {
            this.c = null;
            z = true;
        }
        return z;
    }

    @Nullable
    private synchronized b<T> l() {
        return this.d;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(com.facebook.datasource.b<T> r3, boolean r4) {
        /*
        r2 = this;
        r0 = 0;
        monitor-enter(r2);
        r1 = r2.c;	 Catch:{ all -> 0x001b }
        if (r3 != r1) goto L_0x000a;
    L_0x0006:
        r1 = r2.d;	 Catch:{ all -> 0x001b }
        if (r3 != r1) goto L_0x000c;
    L_0x000a:
        monitor-exit(r2);	 Catch:{ all -> 0x001b }
    L_0x000b:
        return;
    L_0x000c:
        r1 = r2.d;	 Catch:{ all -> 0x001b }
        if (r1 == 0) goto L_0x0012;
    L_0x0010:
        if (r4 == 0) goto L_0x0016;
    L_0x0012:
        r0 = r2.d;	 Catch:{ all -> 0x001b }
        r2.d = r3;	 Catch:{ all -> 0x001b }
    L_0x0016:
        monitor-exit(r2);	 Catch:{ all -> 0x001b }
        r2.e(r0);
        goto L_0x000b;
    L_0x001b:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x001b }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.datasource.e$a.a(com.facebook.datasource.b, boolean):void");
    }

    private void c(b<T> bVar) {
        if (b(bVar)) {
            if (bVar != l()) {
                e(bVar);
            }
            if (!j()) {
                a(bVar.f());
            }
        }
    }

    private void d(b<T> bVar) {
        a((b) bVar, bVar.b());
        if (bVar == l()) {
            a(null, bVar.b());
        }
    }

    private void e(b<T> bVar) {
        if (bVar != null) {
            bVar.h();
        }
    }
}
