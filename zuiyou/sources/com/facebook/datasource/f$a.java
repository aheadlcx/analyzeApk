package com.facebook.datasource;

import com.facebook.common.b.a;
import com.facebook.common.internal.i;
import java.util.ArrayList;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
class f$a extends AbstractDataSource<T> {
    final /* synthetic */ f a;
    @GuardedBy
    @Nullable
    private ArrayList<b<T>> b;
    @GuardedBy
    private int c;

    public f$a(f fVar) {
        this.a = fVar;
        int size = f.a(fVar).size();
        this.c = size;
        this.b = new ArrayList(size);
        int i = 0;
        while (i < size) {
            b bVar = (b) ((i) f.a(fVar).get(i)).b();
            this.b.add(bVar);
            bVar.a(new f$a$a(this, i), a.a());
            if (!bVar.c()) {
                i++;
            } else {
                return;
            }
        }
    }

    @Nullable
    private synchronized b<T> a(int i) {
        b<T> bVar;
        bVar = (this.b == null || i >= this.b.size()) ? null : (b) this.b.get(i);
        return bVar;
    }

    @Nullable
    private synchronized b<T> b(int i) {
        b<T> bVar = null;
        synchronized (this) {
            if (this.b != null && i < this.b.size()) {
                bVar = (b) this.b.set(i, null);
            }
        }
        return bVar;
    }

    @Nullable
    private synchronized b<T> j() {
        return a(this.c);
    }

    @Nullable
    public synchronized T d() {
        b j;
        j = j();
        return j != null ? j.d() : null;
    }

    public synchronized boolean c() {
        boolean z;
        b j = j();
        z = j != null && j.c();
        return z;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean h() {
        /*
        r3 = this;
        r0 = 0;
        monitor-enter(r3);
        r1 = super.h();	 Catch:{ all -> 0x0026 }
        if (r1 != 0) goto L_0x000a;
    L_0x0008:
        monitor-exit(r3);	 Catch:{ all -> 0x0026 }
    L_0x0009:
        return r0;
    L_0x000a:
        r2 = r3.b;	 Catch:{ all -> 0x0026 }
        r1 = 0;
        r3.b = r1;	 Catch:{ all -> 0x0026 }
        monitor-exit(r3);	 Catch:{ all -> 0x0026 }
        if (r2 == 0) goto L_0x0029;
    L_0x0012:
        r1 = r0;
    L_0x0013:
        r0 = r2.size();
        if (r1 >= r0) goto L_0x0029;
    L_0x0019:
        r0 = r2.get(r1);
        r0 = (com.facebook.datasource.b) r0;
        r3.a(r0);
        r0 = r1 + 1;
        r1 = r0;
        goto L_0x0013;
    L_0x0026:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0026 }
        throw r0;
    L_0x0029:
        r0 = 1;
        goto L_0x0009;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.datasource.f$a.h():boolean");
    }

    private void a(int i, b<T> bVar) {
        a(i, (b) bVar, bVar.b());
        if (bVar == j()) {
            boolean z = i == 0 && bVar.b();
            a(null, z);
        }
    }

    private void b(int i, b<T> bVar) {
        a(c(i, bVar));
        if (i == 0) {
            a(bVar.f());
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(int r4, com.facebook.datasource.b<T> r5, boolean r6) {
        /*
        r3 = this;
        monitor-enter(r3);
        r0 = r3.c;	 Catch:{ all -> 0x002c }
        r1 = r3.c;	 Catch:{ all -> 0x002c }
        r2 = r3.a(r4);	 Catch:{ all -> 0x002c }
        if (r5 != r2) goto L_0x000f;
    L_0x000b:
        r2 = r3.c;	 Catch:{ all -> 0x002c }
        if (r4 != r2) goto L_0x0011;
    L_0x000f:
        monitor-exit(r3);	 Catch:{ all -> 0x002c }
    L_0x0010:
        return;
    L_0x0011:
        r2 = r3.j();	 Catch:{ all -> 0x002c }
        if (r2 == 0) goto L_0x001d;
    L_0x0017:
        if (r6 == 0) goto L_0x002f;
    L_0x0019:
        r2 = r3.c;	 Catch:{ all -> 0x002c }
        if (r4 >= r2) goto L_0x002f;
    L_0x001d:
        r3.c = r4;	 Catch:{ all -> 0x002c }
    L_0x001f:
        monitor-exit(r3);	 Catch:{ all -> 0x002c }
    L_0x0020:
        if (r0 <= r4) goto L_0x0010;
    L_0x0022:
        r1 = r3.b(r0);
        r3.a(r1);
        r0 = r0 + -1;
        goto L_0x0020;
    L_0x002c:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x002c }
        throw r0;
    L_0x002f:
        r4 = r1;
        goto L_0x001f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.datasource.f$a.a(int, com.facebook.datasource.b, boolean):void");
    }

    @Nullable
    private synchronized b<T> c(int i, b<T> bVar) {
        if (bVar == j()) {
            bVar = null;
        } else if (bVar == a(i)) {
            bVar = b(i);
        }
        return bVar;
    }

    private void a(b<T> bVar) {
        if (bVar != null) {
            bVar.h();
        }
    }
}
