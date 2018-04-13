package com.crashlytics.android;

import android.content.Context;
import com.crashlytics.android.internal.aa;
import java.util.concurrent.CountDownLatch;

final class ay extends aa {
    private /* synthetic */ Context a;
    private /* synthetic */ float b;
    private /* synthetic */ CountDownLatch c;
    private /* synthetic */ Crashlytics d;

    ay(Crashlytics crashlytics, Context context, float f, CountDownLatch countDownLatch) {
        this.d = crashlytics;
        this.a = context;
        this.b = f;
        this.c = countDownLatch;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a() {
        /*
        r4 = this;
        r0 = r4.d;	 Catch:{ Exception -> 0x001b }
        r1 = r4.a;	 Catch:{ Exception -> 0x001b }
        r2 = r4.b;	 Catch:{ Exception -> 0x001b }
        r0 = r0.a(r1, r2);	 Catch:{ Exception -> 0x001b }
        if (r0 == 0) goto L_0x0015;
    L_0x000c:
        r0 = r4.d;	 Catch:{ Exception -> 0x001b }
        r0 = r0.d;	 Catch:{ Exception -> 0x001b }
        r0.e();	 Catch:{ Exception -> 0x001b }
    L_0x0015:
        r0 = r4.c;
        r0.countDown();
    L_0x001a:
        return;
    L_0x001b:
        r0 = move-exception;
        r1 = com.crashlytics.android.internal.v.a();	 Catch:{ all -> 0x0031 }
        r1 = r1.b();	 Catch:{ all -> 0x0031 }
        r2 = "Crashlytics";
        r3 = "Problem encountered during Crashlytics initialization.";
        r1.a(r2, r3, r0);	 Catch:{ all -> 0x0031 }
        r0 = r4.c;
        r0.countDown();
        goto L_0x001a;
    L_0x0031:
        r0 = move-exception;
        r1 = r4.c;
        r1.countDown();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.crashlytics.android.ay.a():void");
    }
}
