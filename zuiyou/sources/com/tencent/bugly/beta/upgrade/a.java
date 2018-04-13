package com.tencent.bugly.beta.upgrade;

import com.tencent.bugly.proguard.aj;

public class a implements aj {
    public final int a;
    public final int b;
    public final Object[] c;
    public boolean d = false;

    public a(int i, int i2, Object... objArr) {
        this.a = i;
        this.b = i2;
        this.c = objArr;
    }

    public void a(int i) {
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(int r8, com.tencent.bugly.proguard.be r9, long r10, long r12, boolean r14, java.lang.String r15) {
        /*
        r7 = this;
        monitor-enter(r7);
        r0 = r7.d;	 Catch:{ Exception -> 0x0090 }
        if (r0 != 0) goto L_0x0037;
    L_0x0005:
        r0 = r7.b;	 Catch:{ Exception -> 0x0090 }
        if (r8 != r0) goto L_0x0037;
    L_0x0009:
        r1 = "upload %s:[%d] [sended %d] [recevied %d]";
        r0 = 4;
        r2 = new java.lang.Object[r0];	 Catch:{ Exception -> 0x0090 }
        r3 = 0;
        if (r14 == 0) goto L_0x0039;
    L_0x0012:
        r0 = "succ";
    L_0x0015:
        r2[r3] = r0;	 Catch:{ Exception -> 0x0090 }
        r0 = 1;
        r3 = java.lang.Integer.valueOf(r8);	 Catch:{ Exception -> 0x0090 }
        r2[r0] = r3;	 Catch:{ Exception -> 0x0090 }
        r0 = 2;
        r3 = java.lang.Long.valueOf(r10);	 Catch:{ Exception -> 0x0090 }
        r2[r0] = r3;	 Catch:{ Exception -> 0x0090 }
        r0 = 3;
        r3 = java.lang.Long.valueOf(r12);	 Catch:{ Exception -> 0x0090 }
        r2[r0] = r3;	 Catch:{ Exception -> 0x0090 }
        com.tencent.bugly.proguard.an.a(r1, r2);	 Catch:{ Exception -> 0x0090 }
        r0 = r7.a;	 Catch:{ Exception -> 0x0090 }
        switch(r0) {
            case 1: goto L_0x003d;
            case 2: goto L_0x0116;
            default: goto L_0x0034;
        };	 Catch:{ Exception -> 0x0090 }
    L_0x0034:
        r0 = 1;
        r7.d = r0;	 Catch:{ Exception -> 0x0090 }
    L_0x0037:
        monitor-exit(r7);
        return;
    L_0x0039:
        r0 = "err";
        goto L_0x0015;
    L_0x003d:
        r0 = r7.c;	 Catch:{ Exception -> 0x0090 }
        r1 = 0;
        r0 = r0[r1];	 Catch:{ Exception -> 0x0090 }
        r0 = (java.lang.Boolean) r0;	 Catch:{ Exception -> 0x0090 }
        r1 = r0.booleanValue();	 Catch:{ Exception -> 0x0090 }
        r0 = r7.c;	 Catch:{ Exception -> 0x0090 }
        r2 = 1;
        r0 = r0[r2];	 Catch:{ Exception -> 0x0090 }
        r0 = (java.lang.Boolean) r0;	 Catch:{ Exception -> 0x0090 }
        r2 = r0.booleanValue();	 Catch:{ Exception -> 0x0090 }
        r0 = r7.b;	 Catch:{ Exception -> 0x0090 }
        r3 = 804; // 0x324 float:1.127E-42 double:3.97E-321;
        if (r0 != r3) goto L_0x0037;
    L_0x0059:
        r3 = 0;
        r4 = 0;
        if (r14 == 0) goto L_0x0141;
    L_0x005d:
        if (r9 == 0) goto L_0x0141;
    L_0x005f:
        r0 = r9.c;	 Catch:{ Exception -> 0x0090 }
        r5 = com.tencent.bugly.proguard.aa.class;
        r0 = com.tencent.bugly.proguard.ah.a(r0, r5);	 Catch:{ Exception -> 0x0090 }
        r0 = (com.tencent.bugly.proguard.aa) r0;	 Catch:{ Exception -> 0x0090 }
        if (r0 == 0) goto L_0x0141;
    L_0x006b:
        r3 = r0.a;	 Catch:{ Exception -> 0x0090 }
        r4 = r0.b;	 Catch:{ Exception -> 0x0090 }
        r0 = r3;
    L_0x0070:
        r3 = com.tencent.bugly.beta.upgrade.c.a;	 Catch:{ Exception -> 0x0090 }
        r3.a(r0);	 Catch:{ Exception -> 0x0090 }
        r0 = r7.c;	 Catch:{ Exception -> 0x0090 }
        r3 = 2;
        r0 = r0[r3];	 Catch:{ Exception -> 0x0090 }
        r0 = (com.tencent.bugly.beta.upgrade.BetaGrayStrategy) r0;	 Catch:{ Exception -> 0x0090 }
        if (r4 == 0) goto L_0x009e;
    L_0x007e:
        r0 = r4;
    L_0x007f:
        if (r0 == 0) goto L_0x0108;
    L_0x0081:
        r0 = r0.p;	 Catch:{ Exception -> 0x0090 }
        switch(r0) {
            case 1: goto L_0x00a5;
            case 2: goto L_0x0034;
            case 3: goto L_0x00b6;
            default: goto L_0x0086;
        };	 Catch:{ Exception -> 0x0090 }
    L_0x0086:
        r0 = "unexpected updatetype";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Exception -> 0x0090 }
        com.tencent.bugly.proguard.an.a(r0, r1);	 Catch:{ Exception -> 0x0090 }
        goto L_0x0034;
    L_0x0090:
        r0 = move-exception;
        r1 = com.tencent.bugly.proguard.an.b(r0);	 Catch:{ all -> 0x009b }
        if (r1 != 0) goto L_0x0037;
    L_0x0097:
        r0.printStackTrace();	 Catch:{ all -> 0x009b }
        goto L_0x0037;
    L_0x009b:
        r0 = move-exception;
        monitor-exit(r7);
        throw r0;
    L_0x009e:
        if (r0 == 0) goto L_0x00a3;
    L_0x00a0:
        r0 = r0.a;	 Catch:{ Exception -> 0x0090 }
        goto L_0x007f;
    L_0x00a3:
        r0 = 0;
        goto L_0x007f;
    L_0x00a5:
        r0 = com.tencent.bugly.beta.upgrade.c.a;	 Catch:{ Exception -> 0x0090 }
        if (r14 == 0) goto L_0x00b4;
    L_0x00a9:
        r3 = 0;
    L_0x00aa:
        r5 = r15;
        r0.a(r1, r2, r3, r4, r5);	 Catch:{ Exception -> 0x0090 }
        r0 = com.tencent.bugly.beta.global.e.E;	 Catch:{ Exception -> 0x0090 }
        r1 = 1;
        r0.ac = r1;	 Catch:{ Exception -> 0x0090 }
        goto L_0x0034;
    L_0x00b4:
        r3 = -1;
        goto L_0x00aa;
    L_0x00b6:
        r0 = com.tencent.bugly.beta.global.e.E;	 Catch:{ Exception -> 0x0090 }
        r3 = 3;
        r0.ac = r3;	 Catch:{ Exception -> 0x0090 }
        r3 = com.tencent.bugly.proguard.q.a;	 Catch:{ Exception -> 0x0090 }
        if (r14 == 0) goto L_0x00ef;
    L_0x00bf:
        r0 = 0;
    L_0x00c0:
        r5 = 0;
        r3.a(r0, r4, r5);	 Catch:{ Exception -> 0x0090 }
        r0 = com.tencent.bugly.beta.upgrade.c.a;	 Catch:{ Exception -> 0x0090 }
        r0 = r0.f;	 Catch:{ Exception -> 0x0090 }
        if (r0 == 0) goto L_0x00f1;
    L_0x00ca:
        r0 = new com.tencent.bugly.beta.global.d;	 Catch:{ Exception -> 0x0090 }
        r2 = 18;
        r3 = 3;
        r3 = new java.lang.Object[r3];	 Catch:{ Exception -> 0x0090 }
        r4 = 0;
        r5 = com.tencent.bugly.beta.upgrade.c.a;	 Catch:{ Exception -> 0x0090 }
        r5 = r5.f;	 Catch:{ Exception -> 0x0090 }
        r3[r4] = r5;	 Catch:{ Exception -> 0x0090 }
        r4 = 1;
        r5 = 1;
        r5 = java.lang.Integer.valueOf(r5);	 Catch:{ Exception -> 0x0090 }
        r3[r4] = r5;	 Catch:{ Exception -> 0x0090 }
        r4 = 2;
        r1 = java.lang.Boolean.valueOf(r1);	 Catch:{ Exception -> 0x0090 }
        r3[r4] = r1;	 Catch:{ Exception -> 0x0090 }
        r0.<init>(r2, r3);	 Catch:{ Exception -> 0x0090 }
        com.tencent.bugly.beta.utils.e.a(r0);	 Catch:{ Exception -> 0x0090 }
        goto L_0x0034;
    L_0x00ef:
        r0 = -1;
        goto L_0x00c0;
    L_0x00f1:
        if (r1 == 0) goto L_0x0034;
    L_0x00f3:
        if (r2 != 0) goto L_0x0034;
    L_0x00f5:
        r0 = new com.tencent.bugly.beta.global.d;	 Catch:{ Exception -> 0x0090 }
        r1 = 5;
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ Exception -> 0x0090 }
        r3 = 0;
        r4 = com.tencent.bugly.beta.Beta.strToastYourAreTheLatestVersion;	 Catch:{ Exception -> 0x0090 }
        r2[r3] = r4;	 Catch:{ Exception -> 0x0090 }
        r0.<init>(r1, r2);	 Catch:{ Exception -> 0x0090 }
        com.tencent.bugly.beta.utils.e.a(r0);	 Catch:{ Exception -> 0x0090 }
        goto L_0x0034;
    L_0x0108:
        r0 = com.tencent.bugly.beta.upgrade.c.a;	 Catch:{ Exception -> 0x0090 }
        if (r14 == 0) goto L_0x0114;
    L_0x010c:
        r3 = 0;
    L_0x010d:
        r4 = 0;
        r5 = r15;
        r0.a(r1, r2, r3, r4, r5);	 Catch:{ Exception -> 0x0090 }
        goto L_0x0034;
    L_0x0114:
        r3 = -1;
        goto L_0x010d;
    L_0x0116:
        r0 = r7.c;	 Catch:{ Exception -> 0x0090 }
        r1 = 0;
        r0 = r0[r1];	 Catch:{ Exception -> 0x0090 }
        r0 = (com.tencent.bugly.proguard.x) r0;	 Catch:{ Exception -> 0x0090 }
        r0 = r7.b;	 Catch:{ Exception -> 0x0090 }
        r1 = 803; // 0x323 float:1.125E-42 double:3.967E-321;
        if (r0 != r1) goto L_0x0037;
    L_0x0123:
        if (r14 == 0) goto L_0x0034;
    L_0x0125:
        if (r9 == 0) goto L_0x013a;
    L_0x0127:
        r0 = r9.c;	 Catch:{ Exception -> 0x0090 }
        r1 = com.tencent.bugly.proguard.aa.class;
        r0 = com.tencent.bugly.proguard.ah.a(r0, r1);	 Catch:{ Exception -> 0x0090 }
        r0 = (com.tencent.bugly.proguard.aa) r0;	 Catch:{ Exception -> 0x0090 }
        if (r0 == 0) goto L_0x013a;
    L_0x0133:
        r1 = com.tencent.bugly.beta.upgrade.c.a;	 Catch:{ Exception -> 0x0090 }
        r0 = r0.a;	 Catch:{ Exception -> 0x0090 }
        r1.a(r0);	 Catch:{ Exception -> 0x0090 }
    L_0x013a:
        r0 = com.tencent.bugly.proguard.p.a;	 Catch:{ Exception -> 0x0090 }
        r0.b();	 Catch:{ Exception -> 0x0090 }
        goto L_0x0034;
    L_0x0141:
        r0 = r3;
        goto L_0x0070;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.beta.upgrade.a.a(int, com.tencent.bugly.proguard.be, long, long, boolean, java.lang.String):void");
    }
}
