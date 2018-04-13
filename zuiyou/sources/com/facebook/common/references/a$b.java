package com.facebook.common.references;

import com.facebook.common.internal.g;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import javax.annotation.concurrent.GuardedBy;

class a$b<T> extends a<T> {
    private static final ReferenceQueue<a> a = new ReferenceQueue();
    private final SharedReference<T> b;
    private final a c;

    private static class a extends PhantomReference<a> {
        @GuardedBy
        private static a a;
        private final SharedReference b;
        @GuardedBy
        private a c;
        @GuardedBy
        private a d;
        @GuardedBy
        private boolean e;

        public a(a$b a_b, ReferenceQueue<? super a> referenceQueue) {
            super(a_b, referenceQueue);
            this.b = a_b.b;
            synchronized (a.class) {
                if (a != null) {
                    a.c = this;
                    this.d = a;
                }
                a = this;
            }
        }

        public synchronized boolean a() {
            return this.e;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a(boolean r7) {
            /*
            r6 = this;
            r5 = 1;
            monitor-enter(r6);
            r0 = r6.e;	 Catch:{ all -> 0x0061 }
            if (r0 == 0) goto L_0x0008;
        L_0x0006:
            monitor-exit(r6);	 Catch:{ all -> 0x0061 }
        L_0x0007:
            return;
        L_0x0008:
            r0 = 1;
            r6.e = r0;	 Catch:{ all -> 0x0061 }
            monitor-exit(r6);	 Catch:{ all -> 0x0061 }
            r1 = com.facebook.common.references.a.b.a.class;
            monitor-enter(r1);
            r0 = r6.d;	 Catch:{ all -> 0x0069 }
            if (r0 == 0) goto L_0x0019;
        L_0x0013:
            r0 = r6.d;	 Catch:{ all -> 0x0069 }
            r2 = r6.c;	 Catch:{ all -> 0x0069 }
            r0.c = r2;	 Catch:{ all -> 0x0069 }
        L_0x0019:
            r0 = r6.c;	 Catch:{ all -> 0x0069 }
            if (r0 == 0) goto L_0x0064;
        L_0x001d:
            r0 = r6.c;	 Catch:{ all -> 0x0069 }
            r2 = r6.d;	 Catch:{ all -> 0x0069 }
            r0.d = r2;	 Catch:{ all -> 0x0069 }
        L_0x0023:
            monitor-exit(r1);	 Catch:{ all -> 0x0069 }
            if (r7 != 0) goto L_0x005b;
        L_0x0026:
            r0 = com.facebook.common.references.a.f();
            r1 = "GCed without closing: %x %x (type = %s)";
            r2 = 3;
            r2 = new java.lang.Object[r2];
            r3 = 0;
            r4 = java.lang.System.identityHashCode(r6);
            r4 = java.lang.Integer.valueOf(r4);
            r2[r3] = r4;
            r3 = r6.b;
            r3 = java.lang.System.identityHashCode(r3);
            r3 = java.lang.Integer.valueOf(r3);
            r2[r5] = r3;
            r3 = 2;
            r4 = r6.b;
            r4 = r4.a();
            r4 = r4.getClass();
            r4 = r4.getSimpleName();
            r2[r3] = r4;
            com.facebook.common.c.a.b(r0, r1, r2);
        L_0x005b:
            r0 = r6.b;
            r0.d();
            goto L_0x0007;
        L_0x0061:
            r0 = move-exception;
            monitor-exit(r6);	 Catch:{ all -> 0x0061 }
            throw r0;
        L_0x0064:
            r0 = r6.d;	 Catch:{ all -> 0x0069 }
            a = r0;	 Catch:{ all -> 0x0069 }
            goto L_0x0023;
        L_0x0069:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0069 }
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.common.references.a.b.a.a(boolean):void");
        }
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return b();
    }

    static {
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        ((a) a$b.a.remove()).a(false);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }, "CloseableReferenceDestructorThread").start();
    }

    private a$b(SharedReference<T> sharedReference) {
        this.b = (SharedReference) g.a((Object) sharedReference);
        sharedReference.c();
        this.c = new a(this, a);
    }

    private a$b(T t, c<T> cVar) {
        this.b = new SharedReference(t, cVar);
        this.c = new a(this, a);
    }

    public void close() {
        this.c.a(true);
    }

    public T a() {
        T a;
        synchronized (this.c) {
            g.b(!this.c.a());
            a = this.b.a();
        }
        return a;
    }

    public a<T> b() {
        a a_b;
        synchronized (this.c) {
            g.b(!this.c.a());
            a_b = new a$b(this.b);
        }
        return a_b;
    }

    public a<T> c() {
        a<T> aVar;
        synchronized (this.c) {
            if (this.c.a()) {
                aVar = null;
            } else {
                aVar = new a$b(this.b);
            }
        }
        return aVar;
    }

    public boolean d() {
        return !this.c.a();
    }

    public int e() {
        int identityHashCode;
        synchronized (this.c) {
            identityHashCode = d() ? System.identityHashCode(this.b.a()) : 0;
        }
        return identityHashCode;
    }
}
