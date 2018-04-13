package com.umeng.commonsdk.statistics.noise;

import android.content.Context;
import com.umeng.commonsdk.proguard.g;
import com.umeng.commonsdk.statistics.idtracking.ImprintHandler;
import com.umeng.commonsdk.statistics.idtracking.ImprintHandler.a;
import com.umeng.commonsdk.statistics.internal.StatTracer;
import com.umeng.commonsdk.statistics.internal.d;
import qsbk.app.im.TimeUtils;

public class ImLatent implements d {
    private static ImLatent s = null;
    private final int a = 360;
    private final int b = 36;
    private final int c = 1;
    private final int d = 1800;
    private final long e = TimeUtils.HOUR;
    private final long f = 1296000000;
    private final long g = 129600000;
    private final int h = 1800000;
    private final int i = 10;
    private com.umeng.commonsdk.statistics.common.d j;
    private StatTracer k;
    private long l = 1296000000;
    private int m = 10;
    private long n = 0;
    private long o = 0;
    private boolean p = false;
    private Object q = new Object();
    private Context r;

    public static synchronized ImLatent getService(Context context, StatTracer statTracer) {
        ImLatent imLatent;
        synchronized (ImLatent.class) {
            if (s == null) {
                s = new ImLatent(context, statTracer);
                s.onImprintChanged(ImprintHandler.getImprintService(context).b());
            }
            imLatent = s;
        }
        return imLatent;
    }

    private ImLatent(Context context, StatTracer statTracer) {
        this.r = context;
        this.j = com.umeng.commonsdk.statistics.common.d.a(context);
        this.k = statTracer;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean shouldStartLatency() {
        /*
        r8 = this;
        r1 = 1;
        r0 = 0;
        r2 = r8.j;
        r2 = r2.c();
        if (r2 == 0) goto L_0x000b;
    L_0x000a:
        return r0;
    L_0x000b:
        r2 = r8.k;
        r2 = r2.isFirstRequest();
        if (r2 != 0) goto L_0x000a;
    L_0x0013:
        r2 = r8.q;
        monitor-enter(r2);
        r3 = r8.p;	 Catch:{ all -> 0x001c }
        if (r3 == 0) goto L_0x001f;
    L_0x001a:
        monitor-exit(r2);	 Catch:{ all -> 0x001c }
        goto L_0x000a;
    L_0x001c:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x001c }
        throw r0;
    L_0x001f:
        monitor-exit(r2);	 Catch:{ all -> 0x001c }
        r2 = r8.k;
        r2 = r2.getLastReqTime();
        r4 = java.lang.System.currentTimeMillis();
        r2 = r4 - r2;
        r4 = r8.l;
        r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r4 <= 0) goto L_0x004f;
    L_0x0032:
        r0 = r8.r;
        r0 = com.umeng.commonsdk.statistics.idtracking.Envelope.getSignature(r0);
        r4 = r8.q;
        monitor-enter(r4);
        r5 = r8.m;	 Catch:{ all -> 0x004c }
        r0 = com.umeng.commonsdk.statistics.common.DataHelper.random(r5, r0);	 Catch:{ all -> 0x004c }
        r6 = (long) r0;	 Catch:{ all -> 0x004c }
        r8.n = r6;	 Catch:{ all -> 0x004c }
        r8.o = r2;	 Catch:{ all -> 0x004c }
        r0 = 1;
        r8.p = r0;	 Catch:{ all -> 0x004c }
        monitor-exit(r4);	 Catch:{ all -> 0x004c }
        r0 = r1;
        goto L_0x000a;
    L_0x004c:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x004c }
        throw r0;
    L_0x004f:
        r4 = 129600000; // 0x7b98a00 float:2.7916815E-34 double:6.40309077E-316;
        r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r4 <= 0) goto L_0x000a;
    L_0x0056:
        r4 = r8.q;
        monitor-enter(r4);
        r6 = 0;
        r8.n = r6;	 Catch:{ all -> 0x0065 }
        r8.o = r2;	 Catch:{ all -> 0x0065 }
        r0 = 1;
        r8.p = r0;	 Catch:{ all -> 0x0065 }
        monitor-exit(r4);	 Catch:{ all -> 0x0065 }
        r0 = r1;
        goto L_0x000a;
    L_0x0065:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0065 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.statistics.noise.ImLatent.shouldStartLatency():boolean");
    }

    public boolean isLatentActivite() {
        boolean z;
        synchronized (this.q) {
            z = this.p;
        }
        return z;
    }

    public void latentDeactivite() {
        synchronized (this.q) {
            this.p = false;
        }
    }

    public long getDelayTime() {
        long j;
        synchronized (this.q) {
            j = this.n;
        }
        return j;
    }

    public long getElapsedTime() {
        return this.o;
    }

    public void onImprintChanged(a aVar) {
        int i = 360;
        int intValue = Integer.valueOf(aVar.a("latent_hours", String.valueOf(360))).intValue();
        if (intValue > 36) {
            i = intValue;
        }
        this.l = ((long) i) * TimeUtils.HOUR;
        i = Integer.valueOf(aVar.a(g.ax, "0")).intValue();
        if (i < 1 || i > 1800) {
            i = 0;
        }
        if (i != 0) {
            this.m = i;
        } else if (com.umeng.commonsdk.statistics.a.c <= 0 || com.umeng.commonsdk.statistics.a.c > 1800000) {
            this.m = 10;
        } else {
            this.m = com.umeng.commonsdk.statistics.a.c;
        }
    }
}
