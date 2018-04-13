package com.facebook.imagepipeline.producers;

import android.util.Pair;
import com.facebook.common.internal.g;
import com.facebook.common.internal.h;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.producers.ad$a.a;
import java.io.Closeable;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

class ad$a {
    final /* synthetic */ ad a;
    private final K b;
    private final CopyOnWriteArraySet<Pair<j<T>, aj>> c = h.b();
    @GuardedBy
    @Nullable
    private T d;
    @GuardedBy
    private float e;
    @GuardedBy
    @Nullable
    private d f;
    @GuardedBy
    @Nullable
    private a g;

    public ad$a(ad adVar, K k) {
        this.a = adVar;
        this.b = k;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(com.facebook.imagepipeline.producers.j<T> r7, com.facebook.imagepipeline.producers.aj r8) {
        /*
        r6 = this;
        r0 = 0;
        r1 = android.util.Pair.create(r7, r8);
        monitor-enter(r6);
        r2 = r6.a;	 Catch:{ all -> 0x0050 }
        r3 = r6.b;	 Catch:{ all -> 0x0050 }
        r2 = com.facebook.imagepipeline.producers.ad.a(r2, r3);	 Catch:{ all -> 0x0050 }
        if (r2 == r6) goto L_0x0012;
    L_0x0010:
        monitor-exit(r6);	 Catch:{ all -> 0x0050 }
    L_0x0011:
        return r0;
    L_0x0012:
        r0 = r6.c;	 Catch:{ all -> 0x0050 }
        r0.add(r1);	 Catch:{ all -> 0x0050 }
        r2 = r6.b();	 Catch:{ all -> 0x0050 }
        r3 = r6.f();	 Catch:{ all -> 0x0050 }
        r4 = r6.d();	 Catch:{ all -> 0x0050 }
        r0 = r6.d;	 Catch:{ all -> 0x0050 }
        r5 = r6.e;	 Catch:{ all -> 0x0050 }
        monitor-exit(r6);	 Catch:{ all -> 0x0050 }
        com.facebook.imagepipeline.producers.d.b(r2);
        com.facebook.imagepipeline.producers.d.d(r3);
        com.facebook.imagepipeline.producers.d.c(r4);
        monitor-enter(r1);
        monitor-enter(r6);	 Catch:{ all -> 0x005f }
        r2 = r6.d;	 Catch:{ all -> 0x005c }
        if (r0 == r2) goto L_0x0053;
    L_0x0037:
        r0 = 0;
    L_0x0038:
        monitor-exit(r6);	 Catch:{ all -> 0x005c }
        if (r0 == 0) goto L_0x004a;
    L_0x003b:
        r2 = 0;
        r2 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1));
        if (r2 <= 0) goto L_0x0043;
    L_0x0040:
        r7.b(r5);	 Catch:{ all -> 0x005f }
    L_0x0043:
        r2 = 0;
        r7.b(r0, r2);	 Catch:{ all -> 0x005f }
        r6.a(r0);	 Catch:{ all -> 0x005f }
    L_0x004a:
        monitor-exit(r1);	 Catch:{ all -> 0x005f }
        r6.a(r1, r8);
        r0 = 1;
        goto L_0x0011;
    L_0x0050:
        r0 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x0050 }
        throw r0;
    L_0x0053:
        if (r0 == 0) goto L_0x0038;
    L_0x0055:
        r2 = r6.a;	 Catch:{ all -> 0x005c }
        r0 = r2.a(r0);	 Catch:{ all -> 0x005c }
        goto L_0x0038;
    L_0x005c:
        r0 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x005c }
        throw r0;	 Catch:{ all -> 0x005f }
    L_0x005f:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x005f }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.ad$a.a(com.facebook.imagepipeline.producers.j, com.facebook.imagepipeline.producers.aj):boolean");
    }

    private void a(Pair<j<T>, aj> pair, aj ajVar) {
        ajVar.a(new ad$a$1(this, pair));
    }

    private void a() {
        boolean z = true;
        synchronized (this) {
            g.a(this.f == null);
            if (this.g != null) {
                z = false;
            }
            g.a(z);
            if (this.c.isEmpty()) {
                ad.a(this.a, this.b, this);
                return;
            }
            aj ajVar = (aj) ((Pair) this.c.iterator().next()).second;
            this.f = new d(ajVar.a(), ajVar.b(), ajVar.c(), ajVar.d(), ajVar.e(), c(), e(), g());
            this.g = new ad$a$a(this, null);
            aj ajVar2 = this.f;
            j jVar = this.g;
            ad.a(this.a).a(jVar, ajVar2);
        }
    }

    @Nullable
    private synchronized List<ak> b() {
        List<ak> list;
        if (this.f == null) {
            list = null;
        } else {
            list = this.f.a(c());
        }
        return list;
    }

    private synchronized boolean c() {
        boolean z;
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            if (!((aj) ((Pair) it.next()).second).f()) {
                z = false;
                break;
            }
        }
        z = true;
        return z;
    }

    @Nullable
    private synchronized List<ak> d() {
        List<ak> list;
        if (this.f == null) {
            list = null;
        } else {
            list = this.f.b(e());
        }
        return list;
    }

    private synchronized boolean e() {
        boolean z;
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            if (((aj) ((Pair) it.next()).second).h()) {
                z = true;
                break;
            }
        }
        z = false;
        return z;
    }

    @Nullable
    private synchronized List<ak> f() {
        List<ak> list;
        if (this.f == null) {
            list = null;
        } else {
            list = this.f.a(g());
        }
        return list;
    }

    private synchronized Priority g() {
        Priority priority;
        Priority priority2 = Priority.LOW;
        Iterator it = this.c.iterator();
        priority = priority2;
        while (it.hasNext()) {
            priority = Priority.getHigherPriority(priority, ((aj) ((Pair) it.next()).second).g());
        }
        return priority;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.facebook.imagepipeline.producers.ad$a.a r4, java.lang.Throwable r5) {
        /*
        r3 = this;
        monitor-enter(r3);
        r0 = r3.g;	 Catch:{ all -> 0x003c }
        if (r0 == r4) goto L_0x0007;
    L_0x0005:
        monitor-exit(r3);	 Catch:{ all -> 0x003c }
    L_0x0006:
        return;
    L_0x0007:
        r0 = r3.c;	 Catch:{ all -> 0x003c }
        r2 = r0.iterator();	 Catch:{ all -> 0x003c }
        r0 = r3.c;	 Catch:{ all -> 0x003c }
        r0.clear();	 Catch:{ all -> 0x003c }
        r0 = r3.a;	 Catch:{ all -> 0x003c }
        r1 = r3.b;	 Catch:{ all -> 0x003c }
        com.facebook.imagepipeline.producers.ad.a(r0, r1, r3);	 Catch:{ all -> 0x003c }
        r0 = r3.d;	 Catch:{ all -> 0x003c }
        r3.a(r0);	 Catch:{ all -> 0x003c }
        r0 = 0;
        r3.d = r0;	 Catch:{ all -> 0x003c }
        monitor-exit(r3);	 Catch:{ all -> 0x003c }
    L_0x0022:
        r0 = r2.hasNext();
        if (r0 == 0) goto L_0x0006;
    L_0x0028:
        r0 = r2.next();
        r1 = r0;
        r1 = (android.util.Pair) r1;
        monitor-enter(r1);
        r0 = r1.first;	 Catch:{ all -> 0x0039 }
        r0 = (com.facebook.imagepipeline.producers.j) r0;	 Catch:{ all -> 0x0039 }
        r0.b(r5);	 Catch:{ all -> 0x0039 }
        monitor-exit(r1);	 Catch:{ all -> 0x0039 }
        goto L_0x0022;
    L_0x0039:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0039 }
        throw r0;
    L_0x003c:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x003c }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.ad$a.a(com.facebook.imagepipeline.producers.ad$a$a, java.lang.Throwable):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.facebook.imagepipeline.producers.ad$a.a r4, T r5, boolean r6) {
        /*
        r3 = this;
        monitor-enter(r3);
        r0 = r3.g;	 Catch:{ all -> 0x0047 }
        if (r0 == r4) goto L_0x0007;
    L_0x0005:
        monitor-exit(r3);	 Catch:{ all -> 0x0047 }
    L_0x0006:
        return;
    L_0x0007:
        r0 = r3.d;	 Catch:{ all -> 0x0047 }
        r3.a(r0);	 Catch:{ all -> 0x0047 }
        r0 = 0;
        r3.d = r0;	 Catch:{ all -> 0x0047 }
        r0 = r3.c;	 Catch:{ all -> 0x0047 }
        r2 = r0.iterator();	 Catch:{ all -> 0x0047 }
        if (r6 != 0) goto L_0x003a;
    L_0x0017:
        r0 = r3.a;	 Catch:{ all -> 0x0047 }
        r0 = r0.a(r5);	 Catch:{ all -> 0x0047 }
        r3.d = r0;	 Catch:{ all -> 0x0047 }
    L_0x001f:
        monitor-exit(r3);	 Catch:{ all -> 0x0047 }
    L_0x0020:
        r0 = r2.hasNext();
        if (r0 == 0) goto L_0x0006;
    L_0x0026:
        r0 = r2.next();
        r1 = r0;
        r1 = (android.util.Pair) r1;
        monitor-enter(r1);
        r0 = r1.first;	 Catch:{ all -> 0x0037 }
        r0 = (com.facebook.imagepipeline.producers.j) r0;	 Catch:{ all -> 0x0037 }
        r0.b(r5, r6);	 Catch:{ all -> 0x0037 }
        monitor-exit(r1);	 Catch:{ all -> 0x0037 }
        goto L_0x0020;
    L_0x0037:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0037 }
        throw r0;
    L_0x003a:
        r0 = r3.c;	 Catch:{ all -> 0x0047 }
        r0.clear();	 Catch:{ all -> 0x0047 }
        r0 = r3.a;	 Catch:{ all -> 0x0047 }
        r1 = r3.b;	 Catch:{ all -> 0x0047 }
        com.facebook.imagepipeline.producers.ad.a(r0, r1, r3);	 Catch:{ all -> 0x0047 }
        goto L_0x001f;
    L_0x0047:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0047 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.ad$a.a(com.facebook.imagepipeline.producers.ad$a$a, java.io.Closeable, boolean):void");
    }

    public void a(a aVar) {
        synchronized (this) {
            if (this.g != aVar) {
                return;
            }
            this.g = null;
            this.f = null;
            a(this.d);
            this.d = null;
            a();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.facebook.imagepipeline.producers.ad$a.a r4, float r5) {
        /*
        r3 = this;
        monitor-enter(r3);
        r0 = r3.g;	 Catch:{ all -> 0x002a }
        if (r0 == r4) goto L_0x0007;
    L_0x0005:
        monitor-exit(r3);	 Catch:{ all -> 0x002a }
    L_0x0006:
        return;
    L_0x0007:
        r3.e = r5;	 Catch:{ all -> 0x002a }
        r0 = r3.c;	 Catch:{ all -> 0x002a }
        r2 = r0.iterator();	 Catch:{ all -> 0x002a }
        monitor-exit(r3);	 Catch:{ all -> 0x002a }
    L_0x0010:
        r0 = r2.hasNext();
        if (r0 == 0) goto L_0x0006;
    L_0x0016:
        r0 = r2.next();
        r1 = r0;
        r1 = (android.util.Pair) r1;
        monitor-enter(r1);
        r0 = r1.first;	 Catch:{ all -> 0x0027 }
        r0 = (com.facebook.imagepipeline.producers.j) r0;	 Catch:{ all -> 0x0027 }
        r0.b(r5);	 Catch:{ all -> 0x0027 }
        monitor-exit(r1);	 Catch:{ all -> 0x0027 }
        goto L_0x0010;
    L_0x0027:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0027 }
        throw r0;
    L_0x002a:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x002a }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.ad$a.a(com.facebook.imagepipeline.producers.ad$a$a, float):void");
    }

    private void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
    }
}
