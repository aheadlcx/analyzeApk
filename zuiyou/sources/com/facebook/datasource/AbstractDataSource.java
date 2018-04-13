package com.facebook.datasource;

import android.util.Pair;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public abstract class AbstractDataSource<T> implements b<T> {
    @GuardedBy
    private AbstractDataSource$DataSourceStatus a = AbstractDataSource$DataSourceStatus.IN_PROGRESS;
    @GuardedBy
    private boolean b = false;
    @GuardedBy
    @Nullable
    private T c = null;
    @GuardedBy
    private Throwable d = null;
    @GuardedBy
    private float e = 0.0f;
    private final ConcurrentLinkedQueue<Pair<d<T>, Executor>> f = new ConcurrentLinkedQueue();

    protected AbstractDataSource() {
    }

    public synchronized boolean a() {
        return this.b;
    }

    public synchronized boolean b() {
        return this.a != AbstractDataSource$DataSourceStatus.IN_PROGRESS;
    }

    public synchronized boolean c() {
        return this.c != null;
    }

    @Nullable
    public synchronized T d() {
        return this.c;
    }

    public synchronized boolean e() {
        return this.a == AbstractDataSource$DataSourceStatus.FAILURE;
    }

    @Nullable
    public synchronized Throwable f() {
        return this.d;
    }

    public synchronized float g() {
        return this.e;
    }

    public boolean h() {
        boolean z = true;
        synchronized (this) {
            if (this.b) {
                z = false;
            } else {
                this.b = true;
                Object obj = this.c;
                this.c = null;
                if (obj != null) {
                    a(obj);
                }
                if (!b()) {
                    j();
                }
                synchronized (this) {
                    this.f.clear();
                }
            }
        }
        return z;
    }

    protected void a(@Nullable T t) {
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.facebook.datasource.d<T> r3, java.util.concurrent.Executor r4) {
        /*
        r2 = this;
        com.facebook.common.internal.g.a(r3);
        com.facebook.common.internal.g.a(r4);
        monitor-enter(r2);
        r0 = r2.b;	 Catch:{ all -> 0x0040 }
        if (r0 == 0) goto L_0x000d;
    L_0x000b:
        monitor-exit(r2);	 Catch:{ all -> 0x0040 }
    L_0x000c:
        return;
    L_0x000d:
        r0 = r2.a;	 Catch:{ all -> 0x0040 }
        r1 = com.facebook.datasource.AbstractDataSource$DataSourceStatus.IN_PROGRESS;	 Catch:{ all -> 0x0040 }
        if (r0 != r1) goto L_0x001c;
    L_0x0013:
        r0 = r2.f;	 Catch:{ all -> 0x0040 }
        r1 = android.util.Pair.create(r3, r4);	 Catch:{ all -> 0x0040 }
        r0.add(r1);	 Catch:{ all -> 0x0040 }
    L_0x001c:
        r0 = r2.c();	 Catch:{ all -> 0x0040 }
        if (r0 != 0) goto L_0x002e;
    L_0x0022:
        r0 = r2.b();	 Catch:{ all -> 0x0040 }
        if (r0 != 0) goto L_0x002e;
    L_0x0028:
        r0 = r2.k();	 Catch:{ all -> 0x0040 }
        if (r0 == 0) goto L_0x003e;
    L_0x002e:
        r0 = 1;
    L_0x002f:
        monitor-exit(r2);	 Catch:{ all -> 0x0040 }
        if (r0 == 0) goto L_0x000c;
    L_0x0032:
        r0 = r2.e();
        r1 = r2.k();
        r2.a(r3, r4, r0, r1);
        goto L_0x000c;
    L_0x003e:
        r0 = 0;
        goto L_0x002f;
    L_0x0040:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0040 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.datasource.AbstractDataSource.a(com.facebook.datasource.d, java.util.concurrent.Executor):void");
    }

    private void j() {
        boolean e = e();
        boolean k = k();
        Iterator it = this.f.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            a((d) pair.first, (Executor) pair.second, e, k);
        }
    }

    private void a(d<T> dVar, Executor executor, boolean z, boolean z2) {
        executor.execute(new AbstractDataSource$1(this, z, dVar, z2));
    }

    private synchronized boolean k() {
        boolean z;
        z = a() && !b();
        return z;
    }

    protected boolean a(@Nullable T t, boolean z) {
        boolean b = b(t, z);
        if (b) {
            j();
        }
        return b;
    }

    protected boolean a(Throwable th) {
        boolean b = b(th);
        if (b) {
            j();
        }
        return b;
    }

    protected boolean a(float f) {
        boolean b = b(f);
        if (b) {
            i();
        }
        return b;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean b(@javax.annotation.Nullable T r4, boolean r5) {
        /*
        r3 = this;
        r1 = 0;
        monitor-enter(r3);	 Catch:{ all -> 0x003a }
        r0 = r3.b;	 Catch:{ all -> 0x002f }
        if (r0 != 0) goto L_0x000c;
    L_0x0006:
        r0 = r3.a;	 Catch:{ all -> 0x002f }
        r2 = com.facebook.datasource.AbstractDataSource$DataSourceStatus.IN_PROGRESS;	 Catch:{ all -> 0x002f }
        if (r0 == r2) goto L_0x0014;
    L_0x000c:
        r0 = 0;
        monitor-exit(r3);	 Catch:{ all -> 0x003d }
        if (r4 == 0) goto L_0x0013;
    L_0x0010:
        r3.a(r4);
    L_0x0013:
        return r0;
    L_0x0014:
        if (r5 == 0) goto L_0x001e;
    L_0x0016:
        r0 = com.facebook.datasource.AbstractDataSource$DataSourceStatus.SUCCESS;	 Catch:{ all -> 0x002f }
        r3.a = r0;	 Catch:{ all -> 0x002f }
        r0 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r3.e = r0;	 Catch:{ all -> 0x002f }
    L_0x001e:
        r0 = r3.c;	 Catch:{ all -> 0x002f }
        if (r0 == r4) goto L_0x0042;
    L_0x0022:
        r1 = r3.c;	 Catch:{ all -> 0x002f }
        r3.c = r4;	 Catch:{ all -> 0x003f }
        r4 = r1;
    L_0x0027:
        r0 = 1;
        monitor-exit(r3);	 Catch:{ all -> 0x003d }
        if (r4 == 0) goto L_0x0013;
    L_0x002b:
        r3.a(r4);
        goto L_0x0013;
    L_0x002f:
        r0 = move-exception;
        r4 = r1;
    L_0x0031:
        monitor-exit(r3);	 Catch:{ all -> 0x003d }
        throw r0;	 Catch:{ all -> 0x0033 }
    L_0x0033:
        r0 = move-exception;
    L_0x0034:
        if (r4 == 0) goto L_0x0039;
    L_0x0036:
        r3.a(r4);
    L_0x0039:
        throw r0;
    L_0x003a:
        r0 = move-exception;
        r4 = r1;
        goto L_0x0034;
    L_0x003d:
        r0 = move-exception;
        goto L_0x0031;
    L_0x003f:
        r0 = move-exception;
        r4 = r1;
        goto L_0x0031;
    L_0x0042:
        r4 = r1;
        goto L_0x0027;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.datasource.AbstractDataSource.b(java.lang.Object, boolean):boolean");
    }

    private synchronized boolean b(Throwable th) {
        boolean z;
        if (this.b || this.a != AbstractDataSource$DataSourceStatus.IN_PROGRESS) {
            z = false;
        } else {
            this.a = AbstractDataSource$DataSourceStatus.FAILURE;
            this.d = th;
            z = true;
        }
        return z;
    }

    private synchronized boolean b(float f) {
        boolean z = false;
        synchronized (this) {
            if (!this.b && this.a == AbstractDataSource$DataSourceStatus.IN_PROGRESS) {
                if (f >= this.e) {
                    this.e = f;
                    z = true;
                }
            }
        }
        return z;
    }

    protected void i() {
        Iterator it = this.f.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            ((Executor) pair.second).execute(new AbstractDataSource$2(this, (d) pair.first));
        }
    }
}
