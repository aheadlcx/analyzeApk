package com.facebook.common.references;

import com.facebook.common.internal.g;
import javax.annotation.concurrent.GuardedBy;

class a$a<T> extends a<T> {
    @GuardedBy
    private boolean a;
    private final SharedReference<T> b;

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return b();
    }

    private a$a(SharedReference<T> sharedReference) {
        this.a = false;
        this.b = (SharedReference) g.a((Object) sharedReference);
        sharedReference.c();
    }

    private a$a(T t, c<T> cVar) {
        this.a = false;
        this.b = new SharedReference(t, cVar);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void finalize() throws java.lang.Throwable {
        /*
        r5 = this;
        monitor-enter(r5);	 Catch:{ all -> 0x004b }
        r0 = r5.a;	 Catch:{ all -> 0x0048 }
        if (r0 == 0) goto L_0x000a;
    L_0x0005:
        monitor-exit(r5);	 Catch:{ all -> 0x0048 }
        super.finalize();
    L_0x0009:
        return;
    L_0x000a:
        monitor-exit(r5);	 Catch:{ all -> 0x0048 }
        r0 = com.facebook.common.references.a.f();	 Catch:{ all -> 0x004b }
        r1 = "Finalized without closing: %x %x (type = %s)";
        r2 = 3;
        r2 = new java.lang.Object[r2];	 Catch:{ all -> 0x004b }
        r3 = 0;
        r4 = java.lang.System.identityHashCode(r5);	 Catch:{ all -> 0x004b }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ all -> 0x004b }
        r2[r3] = r4;	 Catch:{ all -> 0x004b }
        r3 = 1;
        r4 = r5.b;	 Catch:{ all -> 0x004b }
        r4 = java.lang.System.identityHashCode(r4);	 Catch:{ all -> 0x004b }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ all -> 0x004b }
        r2[r3] = r4;	 Catch:{ all -> 0x004b }
        r3 = 2;
        r4 = r5.b;	 Catch:{ all -> 0x004b }
        r4 = r4.a();	 Catch:{ all -> 0x004b }
        r4 = r4.getClass();	 Catch:{ all -> 0x004b }
        r4 = r4.getSimpleName();	 Catch:{ all -> 0x004b }
        r2[r3] = r4;	 Catch:{ all -> 0x004b }
        com.facebook.common.c.a.b(r0, r1, r2);	 Catch:{ all -> 0x004b }
        r5.close();	 Catch:{ all -> 0x004b }
        super.finalize();
        goto L_0x0009;
    L_0x0048:
        r0 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x0048 }
        throw r0;	 Catch:{ all -> 0x004b }
    L_0x004b:
        r0 = move-exception;
        super.finalize();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.common.references.a$a.finalize():void");
    }

    public synchronized T a() {
        g.b(!this.a);
        return this.b.a();
    }

    public synchronized a<T> b() {
        g.b(d());
        return new a$a(this.b);
    }

    public synchronized a<T> c() {
        a<T> b;
        if (d()) {
            b = b();
        } else {
            b = null;
        }
        return b;
    }

    public synchronized boolean d() {
        return !this.a;
    }

    public int e() {
        return d() ? System.identityHashCode(this.b.a()) : 0;
    }

    public void close() {
        synchronized (this) {
            if (this.a) {
                return;
            }
            this.a = true;
            this.b.d();
        }
    }
}
