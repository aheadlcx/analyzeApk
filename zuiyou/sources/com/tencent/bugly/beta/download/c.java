package com.tencent.bugly.beta.download;

public class c {
    final int a;
    final Object[] b;

    public c(int i, Object... objArr) {
        this.a = i;
        this.b = objArr;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(int r5, int r6) {
        /*
        r4 = this;
        r3 = 1;
        monitor-enter(r4);
        r0 = r4.a;	 Catch:{ Exception -> 0x0029 }
        switch(r0) {
            case 1: goto L_0x0009;
            case 2: goto L_0x0037;
            default: goto L_0x0007;
        };
    L_0x0007:
        monitor-exit(r4);
        return;
    L_0x0009:
        switch(r6) {
            case 1: goto L_0x000d;
            case 2: goto L_0x000c;
            case 3: goto L_0x000d;
            case 4: goto L_0x000d;
            default: goto L_0x000c;
        };
    L_0x000c:
        goto L_0x0007;
    L_0x000d:
        r0 = com.tencent.bugly.proguard.p.a;	 Catch:{ Exception -> 0x0029 }
        r0 = r0.a();	 Catch:{ Exception -> 0x0029 }
        r0 = (java.util.ArrayList) r0;	 Catch:{ Exception -> 0x0029 }
        if (r0 == 0) goto L_0x0007;
    L_0x0017:
        r1 = r0.isEmpty();	 Catch:{ Exception -> 0x0029 }
        if (r1 != 0) goto L_0x0007;
    L_0x001d:
        r1 = com.tencent.bugly.beta.upgrade.b.a;	 Catch:{ Exception -> 0x0029 }
        r2 = new com.tencent.bugly.proguard.x;	 Catch:{ Exception -> 0x0029 }
        r2.<init>(r0);	 Catch:{ Exception -> 0x0029 }
        r0 = 1;
        r1.a(r2, r0);	 Catch:{ Exception -> 0x0029 }
        goto L_0x0007;
    L_0x0029:
        r0 = move-exception;
        r1 = com.tencent.bugly.proguard.an.b(r0);	 Catch:{ all -> 0x0034 }
        if (r1 != 0) goto L_0x0007;
    L_0x0030:
        r0.printStackTrace();	 Catch:{ all -> 0x0034 }
        goto L_0x0007;
    L_0x0034:
        r0 = move-exception;
        monitor-exit(r4);
        throw r0;
    L_0x0037:
        r0 = r4.b;	 Catch:{ Exception -> 0x0029 }
        r1 = 0;
        r0 = r0[r1];	 Catch:{ Exception -> 0x0029 }
        r0 = (com.tencent.bugly.beta.download.DownloadTask) r0;	 Catch:{ Exception -> 0x0029 }
        r1 = r4.b;	 Catch:{ Exception -> 0x0029 }
        r2 = 2;
        r1 = r1[r2];	 Catch:{ Exception -> 0x0029 }
        r1 = (com.tencent.bugly.beta.ui.f) r1;	 Catch:{ Exception -> 0x0029 }
        if (r6 != r3) goto L_0x0061;
    L_0x0047:
        r0.download();	 Catch:{ Exception -> 0x0029 }
        r0 = "continue download";
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ Exception -> 0x0029 }
        com.tencent.bugly.proguard.an.c(r0, r2);	 Catch:{ Exception -> 0x0029 }
        r0 = r4.b;	 Catch:{ Exception -> 0x0029 }
        r2 = 1;
        r3 = 0;
        r3 = java.lang.Boolean.valueOf(r3);	 Catch:{ Exception -> 0x0029 }
        r0[r2] = r3;	 Catch:{ Exception -> 0x0029 }
        r1.a();	 Catch:{ Exception -> 0x0029 }
        goto L_0x0007;
    L_0x0061:
        if (r5 == r3) goto L_0x0070;
    L_0x0063:
        r2 = r4.b;	 Catch:{ Exception -> 0x0029 }
        r3 = 1;
        r2 = r2[r3];	 Catch:{ Exception -> 0x0029 }
        r2 = (java.lang.Boolean) r2;	 Catch:{ Exception -> 0x0029 }
        r2 = r2.booleanValue();	 Catch:{ Exception -> 0x0029 }
        if (r2 == 0) goto L_0x0007;
    L_0x0070:
        r0.stop();	 Catch:{ Exception -> 0x0029 }
        r0 = "wifi network change to mobile network, stop download";
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ Exception -> 0x0029 }
        com.tencent.bugly.proguard.an.c(r0, r2);	 Catch:{ Exception -> 0x0029 }
        r0 = com.tencent.bugly.beta.global.e.E;	 Catch:{ Exception -> 0x0029 }
        r0 = r0.S;	 Catch:{ Exception -> 0x0029 }
        if (r0 != 0) goto L_0x0007;
    L_0x0082:
        r0 = r4.b;	 Catch:{ Exception -> 0x0029 }
        r2 = 1;
        r3 = 1;
        r3 = java.lang.Boolean.valueOf(r3);	 Catch:{ Exception -> 0x0029 }
        r0[r2] = r3;	 Catch:{ Exception -> 0x0029 }
        r0 = com.tencent.bugly.beta.global.e.E;	 Catch:{ Exception -> 0x0029 }
        r0 = r0.s;	 Catch:{ Exception -> 0x0029 }
        r0 = com.tencent.bugly.crashreport.common.info.b.f(r0);	 Catch:{ Exception -> 0x0029 }
        if (r0 == 0) goto L_0x0007;
    L_0x0096:
        r0 = 1;
        com.tencent.bugly.beta.ui.g.a(r1, r0);	 Catch:{ Exception -> 0x0029 }
        r0 = r4.b;	 Catch:{ Exception -> 0x0029 }
        r1 = 1;
        r2 = 0;
        r2 = java.lang.Boolean.valueOf(r2);	 Catch:{ Exception -> 0x0029 }
        r0[r1] = r2;	 Catch:{ Exception -> 0x0029 }
        goto L_0x0007;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.beta.download.c.a(int, int):void");
    }
}
