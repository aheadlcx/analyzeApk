package com.facebook.imagepipeline.producers;

import android.graphics.Bitmap;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.util.ExceptionWithNoStacktrace;
import com.facebook.imagepipeline.f.d;
import com.facebook.imagepipeline.g.e;
import com.facebook.imagepipeline.g.g;
import com.facebook.imagepipeline.g.h;
import com.facebook.imagepipeline.request.ImageRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public class l implements ai<com.facebook.common.references.a<com.facebook.imagepipeline.g.c>> {
    private final com.facebook.common.memory.a a;
    private final Executor b;
    private final com.facebook.imagepipeline.f.b c;
    private final d d;
    private final ai<e> e;
    private final boolean f;
    private final boolean g;
    private final boolean h;

    private abstract class c extends m<e, com.facebook.common.references.a<com.facebook.imagepipeline.g.c>> {
        private final aj a;
        final /* synthetic */ l b;
        private final al c;
        private final com.facebook.imagepipeline.common.a d;
        @GuardedBy
        private boolean e = false;
        private final JobScheduler f;

        protected abstract int a(e eVar);

        protected abstract h c();

        public /* synthetic */ void a(Object obj, boolean z) {
            b((e) obj, z);
        }

        public c(final l lVar, j<com.facebook.common.references.a<com.facebook.imagepipeline.g.c>> jVar, final aj ajVar, final boolean z) {
            this.b = lVar;
            super(jVar);
            this.a = ajVar;
            this.c = ajVar.c();
            this.d = ajVar.a().i();
            this.f = new JobScheduler(lVar.b, new com.facebook.imagepipeline.producers.JobScheduler.a(this) {
                final /* synthetic */ c c;

                public void a(e eVar, boolean z) {
                    if (eVar != null) {
                        if (this.c.b.f) {
                            ImageRequest a = ajVar.a();
                            if (this.c.b.g || !com.facebook.common.util.d.a(a.b())) {
                                eVar.d(p.a(a, eVar));
                            }
                        }
                        this.c.c(eVar, z);
                    }
                }
            }, this.d.a);
            this.a.a(new e(this) {
                final /* synthetic */ c c;

                public void c() {
                    if (this.c.a.h()) {
                        this.c.f.b();
                    }
                }

                public void a() {
                    if (z) {
                        this.c.f();
                    }
                }
            });
        }

        public void b(e eVar, boolean z) {
            if (z && !e.e(eVar)) {
                c(new ExceptionWithNoStacktrace("Encoded image is not valid."));
            } else if (!a(eVar, z)) {
            } else {
                if (z || this.a.h()) {
                    this.f.b();
                }
            }
        }

        protected void a(float f) {
            super.a(0.99f * f);
        }

        public void a(Throwable th) {
            c(th);
        }

        public void a() {
            f();
        }

        protected boolean a(e eVar, boolean z) {
            return this.f.a(eVar, z);
        }

        private void c(e eVar, boolean z) {
            if (!e() && e.e(eVar)) {
                String a;
                String str;
                String valueOf;
                String str2;
                com.facebook.c.c e = eVar.e();
                if (e != null) {
                    a = e.a();
                } else {
                    a = "unknown";
                }
                if (eVar != null) {
                    str = eVar.g() + "x" + eVar.h();
                    valueOf = String.valueOf(eVar.i());
                } else {
                    str = "unknown";
                    valueOf = "unknown";
                }
                com.facebook.imagepipeline.common.c g = this.a.a().g();
                if (g != null) {
                    str2 = g.a + "x" + g.b;
                } else {
                    str2 = "unknown";
                }
                long c;
                h c2;
                com.facebook.imagepipeline.g.c cVar;
                try {
                    c = this.f.c();
                    int k = z ? eVar.k() : a(eVar);
                    c2 = z ? g.a : c();
                    this.c.a(this.a.b(), "DecodeProducer");
                    cVar = null;
                    cVar = this.b.c.a(eVar, k, c2, this.d);
                    this.c.a(this.a.b(), "DecodeProducer", a(cVar, c, c2, z, a, str, str2, valueOf));
                    a(cVar, z);
                } catch (Throwable e2) {
                    Throwable th = e2;
                    this.c.a(this.a.b(), "DecodeProducer", th, a(cVar, c, c2, z, a, str, str2, valueOf));
                    c(th);
                } finally {
                    e.d(eVar);
                }
            }
        }

        private Map<String, String> a(@Nullable com.facebook.imagepipeline.g.c cVar, long j, h hVar, boolean z, String str, String str2, String str3, String str4) {
            if (!this.c.b(this.a.b())) {
                return null;
            }
            String valueOf = String.valueOf(j);
            String valueOf2 = String.valueOf(hVar.b());
            String valueOf3 = String.valueOf(z);
            if (cVar instanceof com.facebook.imagepipeline.g.d) {
                Bitmap f = ((com.facebook.imagepipeline.g.d) cVar).f();
                String str5 = f.getWidth() + "x" + f.getHeight();
                Map hashMap = new HashMap(8);
                hashMap.put("bitmapSize", str5);
                hashMap.put("queueTime", valueOf);
                hashMap.put("hasGoodQuality", valueOf2);
                hashMap.put("isFinal", valueOf3);
                hashMap.put("encodedImageSize", str2);
                hashMap.put("imageFormat", str);
                hashMap.put("requestedImageSize", str3);
                hashMap.put("sampleSize", str4);
                return ImmutableMap.copyOf(hashMap);
            }
            Map hashMap2 = new HashMap(7);
            hashMap2.put("queueTime", valueOf);
            hashMap2.put("hasGoodQuality", valueOf2);
            hashMap2.put("isFinal", valueOf3);
            hashMap2.put("encodedImageSize", str2);
            hashMap2.put("imageFormat", str);
            hashMap2.put("requestedImageSize", str3);
            hashMap2.put("sampleSize", str4);
            return ImmutableMap.copyOf(hashMap2);
        }

        private synchronized boolean e() {
            return this.e;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void a(boolean r3) {
            /*
            r2 = this;
            monitor-enter(r2);
            if (r3 == 0) goto L_0x0007;
        L_0x0003:
            r0 = r2.e;	 Catch:{ all -> 0x001c }
            if (r0 == 0) goto L_0x0009;
        L_0x0007:
            monitor-exit(r2);	 Catch:{ all -> 0x001c }
        L_0x0008:
            return;
        L_0x0009:
            r0 = r2.d();	 Catch:{ all -> 0x001c }
            r1 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
            r0.b(r1);	 Catch:{ all -> 0x001c }
            r0 = 1;
            r2.e = r0;	 Catch:{ all -> 0x001c }
            monitor-exit(r2);	 Catch:{ all -> 0x001c }
            r0 = r2.f;
            r0.a();
            goto L_0x0008;
        L_0x001c:
            r0 = move-exception;
            monitor-exit(r2);	 Catch:{ all -> 0x001c }
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.l.c.a(boolean):void");
        }

        private void a(com.facebook.imagepipeline.g.c cVar, boolean z) {
            com.facebook.common.references.a a = com.facebook.common.references.a.a(cVar);
            try {
                a(z);
                d().b(a, z);
            } finally {
                com.facebook.common.references.a.c(a);
            }
        }

        private void c(Throwable th) {
            a(true);
            d().b(th);
        }

        private void f() {
            a(true);
            d().b();
        }
    }

    private class a extends c {
        final /* synthetic */ l a;

        public a(l lVar, j<com.facebook.common.references.a<com.facebook.imagepipeline.g.c>> jVar, aj ajVar, boolean z) {
            this.a = lVar;
            super(lVar, jVar, ajVar, z);
        }

        protected synchronized boolean a(e eVar, boolean z) {
            boolean a;
            if (z) {
                a = super.a(eVar, z);
            } else {
                a = false;
            }
            return a;
        }

        protected int a(e eVar) {
            return eVar.k();
        }

        protected h c() {
            return g.a(0, false, false);
        }
    }

    private class b extends c {
        final /* synthetic */ l a;
        private final com.facebook.imagepipeline.f.e c;
        private final d d;
        private int e = 0;

        public b(l lVar, j<com.facebook.common.references.a<com.facebook.imagepipeline.g.c>> jVar, aj ajVar, com.facebook.imagepipeline.f.e eVar, d dVar, boolean z) {
            this.a = lVar;
            super(lVar, jVar, ajVar, z);
            this.c = (com.facebook.imagepipeline.f.e) com.facebook.common.internal.g.a((Object) eVar);
            this.d = (d) com.facebook.common.internal.g.a((Object) dVar);
        }

        protected synchronized boolean a(e eVar, boolean z) {
            boolean z2 = false;
            synchronized (this) {
                boolean a = super.a(eVar, z);
                if (!z && e.e(eVar) && eVar.e() == com.facebook.c.b.a) {
                    if (this.c.a(eVar)) {
                        int b = this.c.b();
                        if (b > this.e && (b >= this.d.a(this.e) || this.c.c())) {
                            this.e = b;
                        }
                    }
                }
                z2 = a;
            }
            return z2;
        }

        protected int a(e eVar) {
            return this.c.a();
        }

        protected h c() {
            return this.d.b(this.c.b());
        }
    }

    public l(com.facebook.common.memory.a aVar, Executor executor, com.facebook.imagepipeline.f.b bVar, d dVar, boolean z, boolean z2, boolean z3, ai<e> aiVar) {
        this.a = (com.facebook.common.memory.a) com.facebook.common.internal.g.a((Object) aVar);
        this.b = (Executor) com.facebook.common.internal.g.a((Object) executor);
        this.c = (com.facebook.imagepipeline.f.b) com.facebook.common.internal.g.a((Object) bVar);
        this.d = (d) com.facebook.common.internal.g.a((Object) dVar);
        this.f = z;
        this.g = z2;
        this.e = (ai) com.facebook.common.internal.g.a((Object) aiVar);
        this.h = z3;
    }

    public void a(j<com.facebook.common.references.a<com.facebook.imagepipeline.g.c>> jVar, aj ajVar) {
        j bVar;
        if (com.facebook.common.util.d.a(ajVar.a().b())) {
            bVar = new b(this, jVar, ajVar, new com.facebook.imagepipeline.f.e(this.a), this.d, this.h);
        } else {
            bVar = new a(this, jVar, ajVar, this.h);
        }
        this.e.a(bVar, ajVar);
    }
}
