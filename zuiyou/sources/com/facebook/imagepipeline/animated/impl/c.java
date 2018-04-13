package com.facebook.imagepipeline.animated.impl;

import android.app.ActivityManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.support.v4.util.SparseArrayCompat;
import bolts.f;
import com.facebook.common.b.g;
import com.facebook.common.time.b;
import com.facebook.imagepipeline.animated.a.a;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableFrameInfo.DisposalMethod;
import com.facebook.imagepipeline.animated.base.d;
import com.facebook.imagepipeline.animated.base.e;
import com.facebook.imagepipeline.animated.base.m;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.concurrent.GuardedBy;

public class c extends m implements e {
    private static final Class<?> a = c.class;
    private static final AtomicInteger b = new AtomicInteger();
    private final g c;
    private final a d;
    private final ActivityManager e;
    private final b f;
    private final d g;
    private final com.facebook.imagepipeline.animated.base.g h;
    private final AnimatedImageCompositor i;
    private final com.facebook.common.references.c<Bitmap> j;
    private final double k;
    private final double l;
    @GuardedBy
    private final List<Bitmap> m;
    @GuardedBy
    private final SparseArrayCompat<bolts.g<Object>> n;
    @GuardedBy
    private final SparseArrayCompat<com.facebook.common.references.a<Bitmap>> o;
    @GuardedBy
    private final h p;
    @GuardedBy
    private int q;

    public /* synthetic */ d a(Rect rect) {
        return b(rect);
    }

    public c(g gVar, ActivityManager activityManager, a aVar, b bVar, d dVar, com.facebook.imagepipeline.animated.base.g gVar2) {
        double d;
        super(dVar);
        this.c = gVar;
        this.e = activityManager;
        this.d = aVar;
        this.f = bVar;
        this.g = dVar;
        this.h = gVar2;
        if (gVar2.d >= 0) {
            d = (double) (gVar2.d / 1024);
        } else {
            d = (double) (a(activityManager) / 1024);
        }
        this.k = d;
        this.i = new AnimatedImageCompositor(dVar, new AnimatedImageCompositor.a(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void a(int i, Bitmap bitmap) {
                this.a.a(i, bitmap);
            }

            public com.facebook.common.references.a<Bitmap> a(int i) {
                return this.a.i(i);
            }
        });
        this.j = new com.facebook.common.references.c<Bitmap>(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public /* synthetic */ void release(Object obj) {
                a((Bitmap) obj);
            }

            public void a(Bitmap bitmap) {
                this.a.a(bitmap);
            }
        };
        this.m = new ArrayList();
        this.n = new SparseArrayCompat(10);
        this.o = new SparseArrayCompat(10);
        this.p = new h(this.g.c());
        this.l = (double) ((((this.g.g() * this.g.h()) / 1024) * this.g.c()) * 4);
    }

    protected synchronized void finalize() throws Throwable {
        super.finalize();
        if (this.o.size() > 0) {
            com.facebook.common.c.a.b(a, "Finalizing with rendered bitmaps");
        }
        b.addAndGet(-this.m.size());
        this.m.clear();
    }

    private Bitmap m() {
        com.facebook.common.c.a.a(a, "Creating new bitmap");
        b.incrementAndGet();
        com.facebook.common.c.a.a(a, "Total bitmaps: %d", Integer.valueOf(b.get()));
        return Bitmap.createBitmap(this.g.g(), this.g.h(), Config.ARGB_8888);
    }

    public void a(int i, Canvas canvas) {
        throw new IllegalStateException();
    }

    public com.facebook.common.references.a<Bitmap> g(int i) {
        this.q = i;
        com.facebook.common.references.a<Bitmap> a = a(i, false);
        o();
        return a;
    }

    public com.facebook.common.references.a<Bitmap> l() {
        return a().c();
    }

    public e b(Rect rect) {
        d a = this.g.a(rect);
        return a == this.g ? this : new c(this.c, this.e, this.d, this.f, a, this.h);
    }

    public synchronized void k() {
        this.p.a(false);
        q();
        for (Bitmap recycle : this.m) {
            recycle.recycle();
            b.decrementAndGet();
        }
        this.m.clear();
        this.g.k();
        com.facebook.common.c.a.a(a, "Total bitmaps: %d", Integer.valueOf(b.get()));
    }

    public int j() {
        int i;
        int i2 = 0;
        synchronized (this) {
            i = 0;
            for (Bitmap a : this.m) {
                i += this.d.a(a);
            }
            while (i2 < this.o.size()) {
                i += this.d.a((Bitmap) ((com.facebook.common.references.a) this.o.valueAt(i2)).a());
                i2++;
            }
        }
        return this.g.j() + i;
    }

    public void a(StringBuilder stringBuilder) {
        if (this.h.b) {
            stringBuilder.append("Pinned To Memory");
        } else {
            if (this.l < this.k) {
                stringBuilder.append("within ");
            } else {
                stringBuilder.append("exceeds ");
            }
            this.d.a(stringBuilder, (int) this.k);
        }
        if (p() && this.h.c) {
            stringBuilder.append(" MT");
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.facebook.common.references.a<android.graphics.Bitmap> a(int r9, boolean r10) {
        /*
        r8 = this;
        r2 = 1;
        r6 = 10;
        r3 = 0;
        r0 = r8.f;
        r4 = r0.now();
        monitor-enter(r8);	 Catch:{ all -> 0x007d }
        r0 = r8.p;	 Catch:{ all -> 0x007a }
        r1 = 1;
        r0.a(r9, r1);	 Catch:{ all -> 0x007a }
        r1 = r8.i(r9);	 Catch:{ all -> 0x007a }
        if (r1 == 0) goto L_0x003b;
    L_0x0017:
        monitor-exit(r8);	 Catch:{ all -> 0x007a }
        r0 = r8.f;
        r2 = r0.now();
        r2 = r2 - r4;
        r0 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
        if (r0 <= 0) goto L_0x0039;
    L_0x0023:
        r0 = "";
        r0 = "ok";
        r4 = a;
        r5 = "obtainBitmap for frame %d took %d ms (%s)";
        r6 = java.lang.Integer.valueOf(r9);
        r2 = java.lang.Long.valueOf(r2);
        com.facebook.common.c.a.a(r4, r5, r6, r2, r0);
    L_0x0039:
        r0 = r1;
    L_0x003a:
        return r0;
    L_0x003b:
        monitor-exit(r8);	 Catch:{ all -> 0x007a }
        if (r10 == 0) goto L_0x00ad;
    L_0x003e:
        r3 = r8.n();	 Catch:{ all -> 0x00a9 }
        r1 = r8.i;	 Catch:{ all -> 0x00a4 }
        r0 = r3.a();	 Catch:{ all -> 0x00a4 }
        r0 = (android.graphics.Bitmap) r0;	 Catch:{ all -> 0x00a4 }
        r1.a(r9, r0);	 Catch:{ all -> 0x00a4 }
        r8.a(r9, r3);	 Catch:{ all -> 0x00a4 }
        r1 = r3.b();	 Catch:{ all -> 0x00a4 }
        r3.close();	 Catch:{ all -> 0x00a9 }
        r0 = r8.f;
        r2 = r0.now();
        r2 = r2 - r4;
        r0 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
        if (r0 <= 0) goto L_0x0078;
    L_0x0062:
        r0 = "";
        r0 = "renderedOnCallingThread";
        r4 = a;
        r5 = "obtainBitmap for frame %d took %d ms (%s)";
        r6 = java.lang.Integer.valueOf(r9);
        r2 = java.lang.Long.valueOf(r2);
        com.facebook.common.c.a.a(r4, r5, r6, r2, r0);
    L_0x0078:
        r0 = r1;
        goto L_0x003a;
    L_0x007a:
        r0 = move-exception;
        monitor-exit(r8);	 Catch:{ all -> 0x007a }
        throw r0;	 Catch:{ all -> 0x007d }
    L_0x007d:
        r0 = move-exception;
        r1 = r0;
        r0 = r3;
    L_0x0080:
        r2 = r8.f;
        r2 = r2.now();
        r2 = r2 - r4;
        r4 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
        if (r4 <= 0) goto L_0x00a3;
    L_0x008b:
        r4 = "";
        if (r0 == 0) goto L_0x00d2;
    L_0x0090:
        r0 = "renderedOnCallingThread";
    L_0x0093:
        r4 = a;
        r5 = "obtainBitmap for frame %d took %d ms (%s)";
        r6 = java.lang.Integer.valueOf(r9);
        r2 = java.lang.Long.valueOf(r2);
        com.facebook.common.c.a.a(r4, r5, r6, r2, r0);
    L_0x00a3:
        throw r1;
    L_0x00a4:
        r0 = move-exception;
        r3.close();	 Catch:{ all -> 0x00a9 }
        throw r0;	 Catch:{ all -> 0x00a9 }
    L_0x00a9:
        r0 = move-exception;
        r1 = r0;
        r0 = r2;
        goto L_0x0080;
    L_0x00ad:
        r1 = 0;
        r0 = r8.f;
        r2 = r0.now();
        r2 = r2 - r4;
        r0 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
        if (r0 <= 0) goto L_0x00cf;
    L_0x00b9:
        r0 = "";
        r0 = "deferred";
        r4 = a;
        r5 = "obtainBitmap for frame %d took %d ms (%s)";
        r6 = java.lang.Integer.valueOf(r9);
        r2 = java.lang.Long.valueOf(r2);
        com.facebook.common.c.a.a(r4, r5, r6, r2, r0);
    L_0x00cf:
        r0 = r1;
        goto L_0x003a;
    L_0x00d2:
        r0 = "ok";
        goto L_0x0093;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.animated.impl.c.a(int, boolean):com.facebook.common.references.a<android.graphics.Bitmap>");
    }

    private void a(int i, Bitmap bitmap) {
        Object obj = null;
        synchronized (this) {
            if (this.p.a(i) && this.o.get(i) == null) {
                obj = 1;
            }
        }
        if (obj != null) {
            b(i, bitmap);
        }
    }

    private void b(int i, Bitmap bitmap) {
        com.facebook.common.references.a n = n();
        try {
            Canvas canvas = new Canvas((Bitmap) n.a());
            canvas.drawColor(0, Mode.SRC);
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, null);
            a(i, n);
        } finally {
            n.close();
        }
    }

    private com.facebook.common.references.a<Bitmap> n() {
        Object m;
        synchronized (this) {
            long nanoTime = System.nanoTime();
            long convert = TimeUnit.NANOSECONDS.convert(20, TimeUnit.MILLISECONDS) + nanoTime;
            while (this.m.isEmpty() && nanoTime < convert) {
                try {
                    TimeUnit.NANOSECONDS.timedWait(this, convert - nanoTime);
                    nanoTime = System.nanoTime();
                } catch (Throwable e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
            }
            if (this.m.isEmpty()) {
                m = m();
            } else {
                Bitmap bitmap = (Bitmap) this.m.remove(this.m.size() - 1);
            }
        }
        return com.facebook.common.references.a.a(m, this.j);
    }

    synchronized void a(Bitmap bitmap) {
        this.m.add(bitmap);
    }

    private synchronized void o() {
        int i = 1;
        synchronized (this) {
            int i2;
            int i3;
            int i4 = this.g.a(this.q).g == DisposalMethod.DISPOSE_TO_PREVIOUS ? 1 : 0;
            int i5 = this.q;
            if (i4 != 0) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            i2 = Math.max(0, i5 - i2);
            if (this.h.c) {
                i3 = 3;
            } else {
                i3 = 0;
            }
            if (i4 == 0) {
                i = 0;
            }
            int max = Math.max(i3, i);
            i = (i2 + max) % this.g.c();
            b(i2, i);
            if (!p()) {
                this.p.a(true);
                this.p.a(i2, i);
                for (i = i2; i >= 0; i--) {
                    if (this.o.get(i) != null) {
                        this.p.a(i, true);
                        break;
                    }
                }
                q();
            }
            if (this.h.c) {
                a(i2, max);
            } else {
                b(this.q, this.q);
            }
        }
    }

    private static int a(ActivityManager activityManager) {
        if (activityManager.getMemoryClass() > 32) {
            return 5242880;
        }
        return 3145728;
    }

    private boolean p() {
        if (!this.h.b && this.l >= this.k) {
            return false;
        }
        return true;
    }

    private synchronized void a(int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            final int c = (i + i3) % this.g.c();
            bolts.g gVar = (bolts.g) this.n.get(c);
            if (!j(c) && gVar == null) {
                gVar = bolts.g.a(new Callable<Object>(this) {
                    final /* synthetic */ c b;

                    public Object call() {
                        this.b.h(c);
                        return null;
                    }
                }, this.c);
                this.n.put(c, gVar);
                gVar.a(new f<Object, Object>(this) {
                    final /* synthetic */ c c;

                    public Object a(bolts.g<Object> gVar) throws Exception {
                        this.c.a(gVar, c);
                        return null;
                    }
                });
            }
        }
    }

    private void h(int r6) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.imagepipeline.animated.impl.c.h(int):void. bs: [B:16:0x001f, B:22:0x002a]
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:86)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r5 = this;
        monitor-enter(r5);
        r0 = r5.p;	 Catch:{ all -> 0x0013 }
        r0 = r0.a(r6);	 Catch:{ all -> 0x0013 }
        if (r0 != 0) goto L_0x000b;	 Catch:{ all -> 0x0013 }
    L_0x0009:
        monitor-exit(r5);	 Catch:{ all -> 0x0013 }
    L_0x000a:
        return;	 Catch:{ all -> 0x0013 }
    L_0x000b:
        r0 = r5.j(r6);	 Catch:{ all -> 0x0013 }
        if (r0 == 0) goto L_0x0016;	 Catch:{ all -> 0x0013 }
    L_0x0011:
        monitor-exit(r5);	 Catch:{ all -> 0x0013 }
        goto L_0x000a;	 Catch:{ all -> 0x0013 }
    L_0x0013:
        r0 = move-exception;	 Catch:{ all -> 0x0013 }
        monitor-exit(r5);	 Catch:{ all -> 0x0013 }
        throw r0;
    L_0x0016:
        monitor-exit(r5);	 Catch:{ all -> 0x0013 }
        r0 = r5.g;
        r1 = r0.e(r6);
        if (r1 == 0) goto L_0x0026;
    L_0x001f:
        r5.a(r6, r1);	 Catch:{ all -> 0x0048 }
    L_0x0022:
        com.facebook.common.references.a.c(r1);
        goto L_0x000a;
    L_0x0026:
        r2 = r5.n();	 Catch:{ all -> 0x0048 }
        r3 = r5.i;	 Catch:{ all -> 0x004d }
        r0 = r2.a();	 Catch:{ all -> 0x004d }
        r0 = (android.graphics.Bitmap) r0;	 Catch:{ all -> 0x004d }
        r3.a(r6, r0);	 Catch:{ all -> 0x004d }
        r5.a(r6, r2);	 Catch:{ all -> 0x004d }
        r0 = a;	 Catch:{ all -> 0x004d }
        r3 = "Prefetch rendered frame %d";	 Catch:{ all -> 0x004d }
        r4 = java.lang.Integer.valueOf(r6);	 Catch:{ all -> 0x004d }
        com.facebook.common.c.a.a(r0, r3, r4);	 Catch:{ all -> 0x004d }
        r2.close();	 Catch:{ all -> 0x0048 }
        goto L_0x0022;
    L_0x0048:
        r0 = move-exception;
        com.facebook.common.references.a.c(r1);
        throw r0;
    L_0x004d:
        r0 = move-exception;
        r2.close();	 Catch:{ all -> 0x0048 }
        throw r0;	 Catch:{ all -> 0x0048 }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.animated.impl.c.h(int):void");
    }

    private synchronized void a(bolts.g<?> gVar, int i) {
        int indexOfKey = this.n.indexOfKey(i);
        if (indexOfKey >= 0 && ((bolts.g) this.n.valueAt(indexOfKey)) == gVar) {
            this.n.removeAt(indexOfKey);
            if (gVar.f() != null) {
                com.facebook.common.c.a.a(a, gVar.f(), "Failed to render frame %d", Integer.valueOf(i));
            }
        }
    }

    private synchronized void b(int i, int i2) {
        int i3 = 0;
        while (i3 < this.n.size()) {
            int i4;
            if (a.a(i, i2, this.n.keyAt(i3))) {
                bolts.g gVar = (bolts.g) this.n.valueAt(i3);
                this.n.removeAt(i3);
                i4 = i3;
            } else {
                i4 = i3 + 1;
            }
            i3 = i4;
        }
    }

    private synchronized void q() {
        int i = 0;
        while (i < this.o.size()) {
            int i2;
            if (this.p.a(this.o.keyAt(i))) {
                i2 = i + 1;
            } else {
                com.facebook.common.references.a aVar = (com.facebook.common.references.a) this.o.valueAt(i);
                this.o.removeAt(i);
                aVar.close();
                i2 = i;
            }
            i = i2;
        }
    }

    private synchronized void a(int i, com.facebook.common.references.a<Bitmap> aVar) {
        if (this.p.a(i)) {
            int indexOfKey = this.o.indexOfKey(i);
            if (indexOfKey >= 0) {
                ((com.facebook.common.references.a) this.o.valueAt(indexOfKey)).close();
                this.o.removeAt(indexOfKey);
            }
            this.o.put(i, aVar.b());
        }
    }

    private synchronized com.facebook.common.references.a<Bitmap> i(int i) {
        com.facebook.common.references.a<Bitmap> b;
        b = com.facebook.common.references.a.b((com.facebook.common.references.a) this.o.get(i));
        if (b == null) {
            b = this.g.e(i);
        }
        return b;
    }

    private synchronized boolean j(int i) {
        boolean z;
        z = this.o.get(i) != null || this.g.f(i);
        return z;
    }
}
