package com.facebook.imagepipeline.producers;

import com.facebook.c.b;
import com.facebook.c.c;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.memory.g;
import com.facebook.common.util.TriState;
import com.facebook.imagepipeline.common.d;
import com.facebook.imagepipeline.g.e;
import com.facebook.imagepipeline.request.ImageRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

public class ao implements ai<e> {
    private final Executor a;
    private final g b;
    private final boolean c;
    private final ai<e> d;
    private final boolean e;

    private class a extends m<e, e> {
        final /* synthetic */ ao a;
        private final aj b;
        private boolean c = false;
        private final JobScheduler d;

        public a(final ao aoVar, final j<e> jVar, aj ajVar) {
            this.a = aoVar;
            super(jVar);
            this.b = ajVar;
            this.d = new JobScheduler(aoVar.a, new com.facebook.imagepipeline.producers.JobScheduler.a(this) {
                final /* synthetic */ a b;

                public void a(e eVar, boolean z) {
                    this.b.b(eVar, z);
                }
            }, 100);
            this.b.a(new e(this) {
                final /* synthetic */ a c;

                public void c() {
                    if (this.c.b.h()) {
                        this.c.d.b();
                    }
                }

                public void a() {
                    this.c.d.a();
                    this.c.c = true;
                    jVar.b();
                }
            });
        }

        protected void a(@Nullable e eVar, boolean z) {
            if (!this.c) {
                if (eVar != null) {
                    TriState a = ao.c(this.b.a(), eVar, this.a.c);
                    if (!z && a == TriState.UNSET) {
                        return;
                    }
                    if (a != TriState.YES) {
                        d().b(eVar, z);
                    } else if (!this.d.a(eVar, z)) {
                    } else {
                        if (z || this.b.h()) {
                            this.d.b();
                        }
                    }
                } else if (z) {
                    d().b(null, true);
                }
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void b(com.facebook.imagepipeline.g.e r10, boolean r11) {
            /*
            r9 = this;
            r7 = 0;
            r0 = r9.b;
            r0 = r0.c();
            r1 = r9.b;
            r1 = r1.b();
            r2 = "ResizeAndRotateProducer";
            r0.a(r1, r2);
            r0 = r9.b;
            r2 = r0.a();
            r0 = r9.a;
            r0 = r0.b;
            r8 = r0.newOutputStream();
            r0 = r9.a;	 Catch:{ Exception -> 0x00c9, all -> 0x00be }
            r0 = r0.c;	 Catch:{ Exception -> 0x00c9, all -> 0x00be }
            r5 = com.facebook.imagepipeline.producers.ao.d(r2, r10, r0);	 Catch:{ Exception -> 0x00c9, all -> 0x00be }
            r0 = com.facebook.imagepipeline.producers.p.a(r2, r10);	 Catch:{ Exception -> 0x00c9, all -> 0x00be }
            r4 = com.facebook.imagepipeline.producers.ao.a(r0);	 Catch:{ Exception -> 0x00c9, all -> 0x00be }
            r0 = r9.a;	 Catch:{ Exception -> 0x00c9, all -> 0x00be }
            r0 = r0.e;	 Catch:{ Exception -> 0x00c9, all -> 0x00be }
            if (r0 == 0) goto L_0x0090;
        L_0x003d:
            r3 = r4;
        L_0x003e:
            r0 = r2.h();	 Catch:{ Exception -> 0x00c9, all -> 0x00be }
            r6 = com.facebook.imagepipeline.producers.ao.b(r0, r10);	 Catch:{ Exception -> 0x00c9, all -> 0x00be }
            r0 = r9;
            r1 = r10;
            r2 = r0.a(r1, r2, r3, r4, r5, r6);	 Catch:{ Exception -> 0x00c9, all -> 0x00be }
            r1 = r10.d();	 Catch:{ Exception -> 0x00cc, all -> 0x00be }
            r0 = 85;
            com.facebook.imagepipeline.nativecode.JpegTranscoder.a(r1, r8, r6, r3, r0);	 Catch:{ Exception -> 0x009c }
            r0 = r8.toByteBuffer();	 Catch:{ Exception -> 0x009c }
            r3 = com.facebook.common.references.a.a(r0);	 Catch:{ Exception -> 0x009c }
            r4 = new com.facebook.imagepipeline.g.e;	 Catch:{ all -> 0x0097 }
            r4.<init>(r3);	 Catch:{ all -> 0x0097 }
            r0 = com.facebook.c.b.a;	 Catch:{ all -> 0x0097 }
            r4.a(r0);	 Catch:{ all -> 0x0097 }
            r4.l();	 Catch:{ all -> 0x0092 }
            r0 = r9.b;	 Catch:{ all -> 0x0092 }
            r0 = r0.c();	 Catch:{ all -> 0x0092 }
            r5 = r9.b;	 Catch:{ all -> 0x0092 }
            r5 = r5.b();	 Catch:{ all -> 0x0092 }
            r6 = "ResizeAndRotateProducer";
            r0.a(r5, r6, r2);	 Catch:{ all -> 0x0092 }
            r0 = r9.d();	 Catch:{ all -> 0x0092 }
            r0.b(r4, r11);	 Catch:{ all -> 0x0092 }
            com.facebook.imagepipeline.g.e.d(r4);	 Catch:{ all -> 0x0097 }
            com.facebook.common.references.a.c(r3);	 Catch:{ Exception -> 0x009c }
            com.facebook.common.internal.b.a(r1);
            r8.close();
        L_0x008f:
            return;
        L_0x0090:
            r3 = r5;
            goto L_0x003e;
        L_0x0092:
            r0 = move-exception;
            com.facebook.imagepipeline.g.e.d(r4);	 Catch:{ all -> 0x0097 }
            throw r0;	 Catch:{ all -> 0x0097 }
        L_0x0097:
            r0 = move-exception;
            com.facebook.common.references.a.c(r3);	 Catch:{ Exception -> 0x009c }
            throw r0;	 Catch:{ Exception -> 0x009c }
        L_0x009c:
            r0 = move-exception;
            r7 = r2;
        L_0x009e:
            r2 = r9.b;	 Catch:{ all -> 0x00c7 }
            r2 = r2.c();	 Catch:{ all -> 0x00c7 }
            r3 = r9.b;	 Catch:{ all -> 0x00c7 }
            r3 = r3.b();	 Catch:{ all -> 0x00c7 }
            r4 = "ResizeAndRotateProducer";
            r2.a(r3, r4, r0, r7);	 Catch:{ all -> 0x00c7 }
            r2 = r9.d();	 Catch:{ all -> 0x00c7 }
            r2.b(r0);	 Catch:{ all -> 0x00c7 }
            com.facebook.common.internal.b.a(r1);
            r8.close();
            goto L_0x008f;
        L_0x00be:
            r0 = move-exception;
            r1 = r7;
        L_0x00c0:
            com.facebook.common.internal.b.a(r1);
            r8.close();
            throw r0;
        L_0x00c7:
            r0 = move-exception;
            goto L_0x00c0;
        L_0x00c9:
            r0 = move-exception;
            r1 = r7;
            goto L_0x009e;
        L_0x00cc:
            r0 = move-exception;
            r1 = r7;
            r7 = r2;
            goto L_0x009e;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.ao.a.b(com.facebook.imagepipeline.g.e, boolean):void");
        }

        private Map<String, String> a(e eVar, ImageRequest imageRequest, int i, int i2, int i3, int i4) {
            if (!this.b.c().b(this.b.b())) {
                return null;
            }
            Object obj;
            String str = eVar.g() + "x" + eVar.h();
            if (imageRequest.g() != null) {
                obj = imageRequest.g().a + "x" + imageRequest.g().b;
            } else {
                String str2 = "Unspecified";
            }
            Object obj2 = i > 0 ? i + "/8" : "";
            Map hashMap = new HashMap();
            hashMap.put("Original size", str);
            hashMap.put("Requested size", obj);
            hashMap.put("Fraction", obj2);
            hashMap.put("queueTime", String.valueOf(this.d.c()));
            hashMap.put("downsampleEnumerator", Integer.toString(i2));
            hashMap.put("softwareEnumerator", Integer.toString(i3));
            hashMap.put("rotationAngle", Integer.toString(i4));
            return ImmutableMap.copyOf(hashMap);
        }
    }

    public ao(Executor executor, g gVar, boolean z, ai<e> aiVar, boolean z2) {
        this.a = (Executor) com.facebook.common.internal.g.a((Object) executor);
        this.b = (g) com.facebook.common.internal.g.a((Object) gVar);
        this.c = z;
        this.d = (ai) com.facebook.common.internal.g.a((Object) aiVar);
        this.e = z2;
    }

    public void a(j<e> jVar, aj ajVar) {
        this.d.a(new a(this, jVar, ajVar), ajVar);
    }

    private static TriState c(ImageRequest imageRequest, e eVar, boolean z) {
        if (eVar == null || eVar.e() == c.a) {
            return TriState.UNSET;
        }
        if (eVar.e() != b.a) {
            return TriState.NO;
        }
        boolean z2 = c(imageRequest.h(), eVar) || b(d(imageRequest, eVar, z));
        return TriState.valueOf(z2);
    }

    static float a(com.facebook.imagepipeline.common.c cVar, int i, int i2) {
        if (cVar == null) {
            return 1.0f;
        }
        float max = Math.max(((float) cVar.a) / ((float) i), ((float) cVar.b) / ((float) i2));
        if (((float) i) * max > cVar.c) {
            max = cVar.c / ((float) i);
        }
        if (((float) i2) * max > cVar.c) {
            return cVar.c / ((float) i2);
        }
        return max;
    }

    static int a(float f, float f2) {
        return (int) ((8.0f * f) + f2);
    }

    private static int d(ImageRequest imageRequest, e eVar, boolean z) {
        if (!z) {
            return 8;
        }
        com.facebook.imagepipeline.common.c g = imageRequest.g();
        if (g == null) {
            return 8;
        }
        int h;
        int b = b(imageRequest.h(), eVar);
        Object obj = (b == 90 || b == 270) ? 1 : null;
        if (obj != null) {
            h = eVar.h();
        } else {
            h = eVar.g();
        }
        if (obj != null) {
            b = eVar.g();
        } else {
            b = eVar.h();
        }
        b = a(a(g, h, b), g.d);
        if (b > 8) {
            return 8;
        }
        return b < 1 ? 1 : b;
    }

    private static int b(d dVar, e eVar) {
        if (!dVar.e()) {
            return 0;
        }
        int a = a(eVar);
        return !dVar.d() ? (a + dVar.f()) % com.umeng.analytics.a.p : a;
    }

    private static int a(e eVar) {
        switch (eVar.f()) {
            case 90:
            case 180:
            case 270:
                return eVar.f();
            default:
                return 0;
        }
    }

    private static boolean b(int i) {
        return i < 8;
    }

    private static boolean c(d dVar, e eVar) {
        return (dVar.g() || b(dVar, eVar) == 0) ? false : true;
    }

    static int a(int i) {
        return Math.max(1, 8 / i);
    }
}
