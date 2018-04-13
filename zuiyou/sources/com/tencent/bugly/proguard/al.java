package com.tencent.bugly.proguard;

import android.content.Context;
import android.support.v4.app.NotificationCompat;
import com.tencent.bugly.BuglyStrategy$a;
import com.tencent.bugly.crashreport.common.info.a;
import java.util.Map;
import java.util.UUID;

public class al implements Runnable {
    public int a;
    public int b;
    protected int c;
    protected long d;
    protected long e;
    protected boolean f;
    private final Context g;
    private final int h;
    private final byte[] i;
    private final a j;
    private final com.tencent.bugly.crashreport.common.strategy.a k;
    private final ai l;
    private final ak m;
    private final int n;
    private final aj o;
    private final aj p;
    private String q;
    private final String r;
    private final Map<String, String> s;
    private boolean t;

    public al(Context context, int i, int i2, byte[] bArr, String str, String str2, aj ajVar, boolean z, boolean z2) {
        this(context, i, i2, bArr, str, str2, ajVar, z, 2, BuglyStrategy$a.MAX_USERDATA_VALUE_LENGTH, z2, null);
    }

    public al(Context context, int i, int i2, byte[] bArr, String str, String str2, aj ajVar, boolean z, int i3, int i4, boolean z2, Map<String, String> map) {
        this.a = 2;
        this.b = BuglyStrategy$a.MAX_USERDATA_VALUE_LENGTH;
        this.q = null;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = true;
        this.t = false;
        this.g = context;
        this.j = a.a(context);
        this.i = bArr;
        this.k = com.tencent.bugly.crashreport.common.strategy.a.a();
        this.l = ai.a(context);
        this.m = ak.a();
        this.n = i;
        this.q = str;
        this.r = str2;
        this.o = ajVar;
        this.p = this.m.a;
        this.f = z;
        this.h = i2;
        if (i3 > 0) {
            this.a = i3;
        }
        if (i4 > 0) {
            this.b = i4;
        }
        this.t = z2;
        this.s = map;
    }

    protected void a() {
        this.m.a(this.n, System.currentTimeMillis());
        if (this.o != null) {
            this.o.a(this.h);
        }
        if (this.p != null) {
            this.p.a(this.h);
        }
    }

    protected void a(int i, String str) {
        an.e("[Upload] Failed to upload(%d): %s", new Object[]{Integer.valueOf(i), str});
    }

    protected void a(be beVar, boolean z, int i, String str, int i2) {
        String str2;
        switch (this.h) {
            case 630:
            case 830:
                str2 = "crash";
                break;
            case 640:
            case 840:
                str2 = "userinfo";
                break;
            default:
                str2 = String.valueOf(this.h);
                break;
        }
        if (z) {
            an.a("[Upload] Success: %s", new Object[]{str2});
        } else {
            an.e("[Upload] Failed to upload(%d) %s: %s", new Object[]{Integer.valueOf(i), str2, str});
            if (this.f) {
                this.m.a(i2, null);
            }
        }
        if (this.d + this.e > 0) {
            this.m.a((this.m.a(this.t) + this.d) + this.e, this.t);
        }
        if (this.o != null) {
            this.o.a(this.h, beVar, this.d, this.e, z, str);
        }
        if (this.p != null) {
            this.p.a(this.h, beVar, this.d, this.e, z, str);
        }
    }

    protected boolean a(be beVar, a aVar, com.tencent.bugly.crashreport.common.strategy.a aVar2) {
        if (beVar == null) {
            an.d("resp == null!", new Object[0]);
            return false;
        } else if (beVar.a != (byte) 0) {
            an.e("resp result error %d", new Object[]{Byte.valueOf(beVar.a)});
            return false;
        } else {
            try {
                if (!(ap.a(beVar.d) || a.b().i().equals(beVar.d))) {
                    ae.a().a(com.tencent.bugly.crashreport.common.strategy.a.a, "key_ip", beVar.d.getBytes("UTF-8"), null, true);
                    aVar.d(beVar.d);
                }
                if (!(ap.a(beVar.g) || a.b().j().equals(beVar.g))) {
                    ae.a().a(com.tencent.bugly.crashreport.common.strategy.a.a, "key_imei", beVar.g.getBytes("UTF-8"), null, true);
                    aVar.e(beVar.g);
                }
            } catch (Throwable th) {
                an.a(th);
            }
            aVar.n = beVar.e;
            if (beVar.b == 510) {
                if (beVar.c == null) {
                    an.e("[Upload] Strategy data is null. Response cmd: %d", new Object[]{Integer.valueOf(beVar.b)});
                    return false;
                }
                bg bgVar = (bg) ah.a(beVar.c, bg.class);
                if (bgVar == null) {
                    an.e("[Upload] Failed to decode strategy from server. Response cmd: %d", new Object[]{Integer.valueOf(beVar.b)});
                    return false;
                }
                aVar2.a(bgVar);
            }
            return true;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r12 = this;
        r0 = 0;
        r12.c = r0;	 Catch:{ Throwable -> 0x0032 }
        r0 = 0;
        r12.d = r0;	 Catch:{ Throwable -> 0x0032 }
        r0 = 0;
        r12.e = r0;	 Catch:{ Throwable -> 0x0032 }
        r0 = r12.i;	 Catch:{ Throwable -> 0x0032 }
        r1 = r12.g;	 Catch:{ Throwable -> 0x0032 }
        r1 = com.tencent.bugly.crashreport.common.info.b.f(r1);	 Catch:{ Throwable -> 0x0032 }
        if (r1 != 0) goto L_0x0021;
    L_0x0015:
        r1 = 0;
        r2 = 0;
        r3 = 0;
        r4 = "network is not available";
        r5 = 0;
        r0 = r12;
        r0.a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0032 }
    L_0x0020:
        return;
    L_0x0021:
        if (r0 == 0) goto L_0x0026;
    L_0x0023:
        r1 = r0.length;	 Catch:{ Throwable -> 0x0032 }
        if (r1 != 0) goto L_0x003d;
    L_0x0026:
        r1 = 0;
        r2 = 0;
        r3 = 0;
        r4 = "request package is empty!";
        r5 = 0;
        r0 = r12;
        r0.a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0032 }
        goto L_0x0020;
    L_0x0032:
        r0 = move-exception;
        r1 = com.tencent.bugly.proguard.an.a(r0);
        if (r1 != 0) goto L_0x0020;
    L_0x0039:
        r0.printStackTrace();
        goto L_0x0020;
    L_0x003d:
        r1 = r12.m;	 Catch:{ Throwable -> 0x0032 }
        r2 = r12.t;	 Catch:{ Throwable -> 0x0032 }
        r2 = r1.a(r2);	 Catch:{ Throwable -> 0x0032 }
        r4 = 2097152; // 0x200000 float:2.938736E-39 double:1.0361308E-317;
        r1 = r0.length;	 Catch:{ Throwable -> 0x0032 }
        r6 = (long) r1;	 Catch:{ Throwable -> 0x0032 }
        r6 = r6 + r2;
        r1 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1));
        if (r1 < 0) goto L_0x008d;
    L_0x004f:
        r0 = "[Upload] Upload too much data, try next time: %d/%d";
        r1 = 2;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x0032 }
        r6 = 0;
        r2 = java.lang.Long.valueOf(r2);	 Catch:{ Throwable -> 0x0032 }
        r1[r6] = r2;	 Catch:{ Throwable -> 0x0032 }
        r2 = 1;
        r3 = java.lang.Long.valueOf(r4);	 Catch:{ Throwable -> 0x0032 }
        r1[r2] = r3;	 Catch:{ Throwable -> 0x0032 }
        com.tencent.bugly.proguard.an.e(r0, r1);	 Catch:{ Throwable -> 0x0032 }
        r1 = 0;
        r2 = 0;
        r3 = 0;
        r0 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0032 }
        r0.<init>();	 Catch:{ Throwable -> 0x0032 }
        r6 = "over net consume: ";
        r0 = r0.append(r6);	 Catch:{ Throwable -> 0x0032 }
        r6 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r4 = r4 / r6;
        r0 = r0.append(r4);	 Catch:{ Throwable -> 0x0032 }
        r4 = "K";
        r0 = r0.append(r4);	 Catch:{ Throwable -> 0x0032 }
        r4 = r0.toString();	 Catch:{ Throwable -> 0x0032 }
        r5 = 0;
        r0 = r12;
        r0.a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0032 }
        goto L_0x0020;
    L_0x008d:
        r1 = "[Upload] Run upload task with cmd: %d";
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x0032 }
        r3 = 0;
        r4 = r12.h;	 Catch:{ Throwable -> 0x0032 }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ Throwable -> 0x0032 }
        r2[r3] = r4;	 Catch:{ Throwable -> 0x0032 }
        com.tencent.bugly.proguard.an.c(r1, r2);	 Catch:{ Throwable -> 0x0032 }
        r1 = r12.g;	 Catch:{ Throwable -> 0x0032 }
        if (r1 == 0) goto L_0x00af;
    L_0x00a3:
        r1 = r12.j;	 Catch:{ Throwable -> 0x0032 }
        if (r1 == 0) goto L_0x00af;
    L_0x00a7:
        r1 = r12.k;	 Catch:{ Throwable -> 0x0032 }
        if (r1 == 0) goto L_0x00af;
    L_0x00ab:
        r1 = r12.l;	 Catch:{ Throwable -> 0x0032 }
        if (r1 != 0) goto L_0x00bc;
    L_0x00af:
        r1 = 0;
        r2 = 0;
        r3 = 0;
        r4 = "illegal access error";
        r5 = 0;
        r0 = r12;
        r0.a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0032 }
        goto L_0x0020;
    L_0x00bc:
        r1 = r12.k;	 Catch:{ Throwable -> 0x0032 }
        r7 = r1.c();	 Catch:{ Throwable -> 0x0032 }
        if (r7 != 0) goto L_0x00d1;
    L_0x00c4:
        r1 = 0;
        r2 = 0;
        r3 = 0;
        r4 = "illegal local strategy";
        r5 = 0;
        r0 = r12;
        r0.a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0032 }
        goto L_0x0020;
    L_0x00d1:
        r3 = 0;
        r8 = new java.util.HashMap;	 Catch:{ Throwable -> 0x0032 }
        r8.<init>();	 Catch:{ Throwable -> 0x0032 }
        r1 = "prodId";
        r2 = r12.j;	 Catch:{ Throwable -> 0x0032 }
        r2 = r2.f();	 Catch:{ Throwable -> 0x0032 }
        r8.put(r1, r2);	 Catch:{ Throwable -> 0x0032 }
        r1 = "bundleId";
        r2 = r12.j;	 Catch:{ Throwable -> 0x0032 }
        r2 = r2.d;	 Catch:{ Throwable -> 0x0032 }
        r8.put(r1, r2);	 Catch:{ Throwable -> 0x0032 }
        r1 = "appVer";
        r2 = r12.j;	 Catch:{ Throwable -> 0x0032 }
        r2 = r2.o;	 Catch:{ Throwable -> 0x0032 }
        r8.put(r1, r2);	 Catch:{ Throwable -> 0x0032 }
        r1 = r12.s;	 Catch:{ Throwable -> 0x0032 }
        if (r1 == 0) goto L_0x0100;
    L_0x00fb:
        r1 = r12.s;	 Catch:{ Throwable -> 0x0032 }
        r8.putAll(r1);	 Catch:{ Throwable -> 0x0032 }
    L_0x0100:
        r1 = r12.f;	 Catch:{ Throwable -> 0x0032 }
        if (r1 == 0) goto L_0x0173;
    L_0x0104:
        r1 = "cmd";
        r2 = r12.h;	 Catch:{ Throwable -> 0x0032 }
        r2 = java.lang.Integer.toString(r2);	 Catch:{ Throwable -> 0x0032 }
        r8.put(r1, r2);	 Catch:{ Throwable -> 0x0032 }
        r1 = "platformId";
        r2 = 1;
        r2 = java.lang.Byte.toString(r2);	 Catch:{ Throwable -> 0x0032 }
        r8.put(r1, r2);	 Catch:{ Throwable -> 0x0032 }
        r1 = "sdkVer";
        r2 = r12.j;	 Catch:{ Throwable -> 0x0032 }
        r2.getClass();	 Catch:{ Throwable -> 0x0032 }
        r2 = "2.6.5";
        r8.put(r1, r2);	 Catch:{ Throwable -> 0x0032 }
        r1 = "strategylastUpdateTime";
        r4 = r7.p;	 Catch:{ Throwable -> 0x0032 }
        r2 = java.lang.Long.toString(r4);	 Catch:{ Throwable -> 0x0032 }
        r8.put(r1, r2);	 Catch:{ Throwable -> 0x0032 }
        r1 = r12.m;	 Catch:{ Throwable -> 0x0032 }
        r1 = r1.a(r8);	 Catch:{ Throwable -> 0x0032 }
        if (r1 != 0) goto L_0x014a;
    L_0x013d:
        r1 = 0;
        r2 = 0;
        r3 = 0;
        r4 = "failed to add security info to HTTP headers";
        r5 = 0;
        r0 = r12;
        r0.a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0032 }
        goto L_0x0020;
    L_0x014a:
        r1 = 2;
        r0 = com.tencent.bugly.proguard.ap.a(r0, r1);	 Catch:{ Throwable -> 0x0032 }
        if (r0 != 0) goto L_0x015e;
    L_0x0151:
        r1 = 0;
        r2 = 0;
        r3 = 0;
        r4 = "failed to zip request body";
        r5 = 0;
        r0 = r12;
        r0.a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0032 }
        goto L_0x0020;
    L_0x015e:
        r1 = r12.m;	 Catch:{ Throwable -> 0x0032 }
        r0 = r1.a(r0);	 Catch:{ Throwable -> 0x0032 }
        if (r0 != 0) goto L_0x0173;
    L_0x0166:
        r1 = 0;
        r2 = 0;
        r3 = 0;
        r4 = "failed to encrypt request body";
        r5 = 0;
        r0 = r12;
        r0.a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0032 }
        goto L_0x0020;
    L_0x0173:
        r6 = r0;
        r12.a();	 Catch:{ Throwable -> 0x0032 }
        r2 = r12.q;	 Catch:{ Throwable -> 0x0032 }
        r5 = -1;
        r0 = 0;
        r1 = r0;
        r0 = r2;
    L_0x017d:
        r4 = r1 + 1;
        r2 = r12.a;	 Catch:{ Throwable -> 0x0032 }
        if (r1 >= r2) goto L_0x0433;
    L_0x0183:
        r1 = 1;
        if (r4 <= r1) goto L_0x01b0;
    L_0x0186:
        r1 = "[Upload] Failed to upload last time, wait and try(%d) again.";
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x0032 }
        r3 = 0;
        r9 = java.lang.Integer.valueOf(r4);	 Catch:{ Throwable -> 0x0032 }
        r2[r3] = r9;	 Catch:{ Throwable -> 0x0032 }
        com.tencent.bugly.proguard.an.d(r1, r2);	 Catch:{ Throwable -> 0x0032 }
        r1 = r12.b;	 Catch:{ Throwable -> 0x0032 }
        r2 = (long) r1;	 Catch:{ Throwable -> 0x0032 }
        com.tencent.bugly.proguard.ap.b(r2);	 Catch:{ Throwable -> 0x0032 }
        r1 = r12.a;	 Catch:{ Throwable -> 0x0032 }
        if (r4 != r1) goto L_0x01b0;
    L_0x01a0:
        r0 = "[Upload] Use the back-up url at the last time: %s";
        r1 = 1;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x0032 }
        r2 = 0;
        r3 = r12.r;	 Catch:{ Throwable -> 0x0032 }
        r1[r2] = r3;	 Catch:{ Throwable -> 0x0032 }
        com.tencent.bugly.proguard.an.d(r0, r1);	 Catch:{ Throwable -> 0x0032 }
        r0 = r12.r;	 Catch:{ Throwable -> 0x0032 }
    L_0x01b0:
        r1 = "[Upload] Send %d bytes";
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x0032 }
        r3 = 0;
        r9 = r6.length;	 Catch:{ Throwable -> 0x0032 }
        r9 = java.lang.Integer.valueOf(r9);	 Catch:{ Throwable -> 0x0032 }
        r2[r3] = r9;	 Catch:{ Throwable -> 0x0032 }
        com.tencent.bugly.proguard.an.c(r1, r2);	 Catch:{ Throwable -> 0x0032 }
        r1 = r12.f;	 Catch:{ Throwable -> 0x0032 }
        if (r1 == 0) goto L_0x043f;
    L_0x01c5:
        r0 = a(r0);	 Catch:{ Throwable -> 0x0032 }
        r2 = r0;
    L_0x01ca:
        r0 = "[Upload] Upload to %s with cmd %d (pid=%d | tid=%d).";
        r1 = 4;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x0032 }
        r3 = 0;
        r1[r3] = r2;	 Catch:{ Throwable -> 0x0032 }
        r3 = 1;
        r9 = r12.h;	 Catch:{ Throwable -> 0x0032 }
        r9 = java.lang.Integer.valueOf(r9);	 Catch:{ Throwable -> 0x0032 }
        r1[r3] = r9;	 Catch:{ Throwable -> 0x0032 }
        r3 = 2;
        r9 = android.os.Process.myPid();	 Catch:{ Throwable -> 0x0032 }
        r9 = java.lang.Integer.valueOf(r9);	 Catch:{ Throwable -> 0x0032 }
        r1[r3] = r9;	 Catch:{ Throwable -> 0x0032 }
        r3 = 3;
        r9 = android.os.Process.myTid();	 Catch:{ Throwable -> 0x0032 }
        r9 = java.lang.Integer.valueOf(r9);	 Catch:{ Throwable -> 0x0032 }
        r1[r3] = r9;	 Catch:{ Throwable -> 0x0032 }
        com.tencent.bugly.proguard.an.c(r0, r1);	 Catch:{ Throwable -> 0x0032 }
        r0 = r12.l;	 Catch:{ Throwable -> 0x0032 }
        r1 = r0.a(r2, r6, r12, r8);	 Catch:{ Throwable -> 0x0032 }
        if (r1 != 0) goto L_0x0209;
    L_0x01fd:
        r0 = 1;
        r1 = "Failed to upload for no response!";
        r12.a(r0, r1);	 Catch:{ Throwable -> 0x0032 }
        r3 = 1;
        r1 = r4;
        r0 = r2;
        goto L_0x017d;
    L_0x0209:
        r0 = r12.l;	 Catch:{ Throwable -> 0x0032 }
        r3 = r0.b;	 Catch:{ Throwable -> 0x0032 }
        r0 = r12.f;	 Catch:{ Throwable -> 0x0032 }
        if (r0 == 0) goto L_0x0356;
    L_0x0211:
        r0 = a(r3);	 Catch:{ Throwable -> 0x0032 }
        if (r0 != 0) goto L_0x0280;
    L_0x0217:
        r0 = "[Upload] Headers from server is not valid, just try again (pid=%d | tid=%d).";
        r1 = 2;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x0032 }
        r9 = 0;
        r10 = android.os.Process.myPid();	 Catch:{ Throwable -> 0x0032 }
        r10 = java.lang.Integer.valueOf(r10);	 Catch:{ Throwable -> 0x0032 }
        r1[r9] = r10;	 Catch:{ Throwable -> 0x0032 }
        r9 = 1;
        r10 = android.os.Process.myTid();	 Catch:{ Throwable -> 0x0032 }
        r10 = java.lang.Integer.valueOf(r10);	 Catch:{ Throwable -> 0x0032 }
        r1[r9] = r10;	 Catch:{ Throwable -> 0x0032 }
        com.tencent.bugly.proguard.an.c(r0, r1);	 Catch:{ Throwable -> 0x0032 }
        r0 = 1;
        r1 = "[Upload] Failed to upload for no status header.";
        r12.a(r0, r1);	 Catch:{ Throwable -> 0x0032 }
        if (r3 == 0) goto L_0x0272;
    L_0x023f:
        r0 = r3.entrySet();	 Catch:{ Throwable -> 0x0032 }
        r1 = r0.iterator();	 Catch:{ Throwable -> 0x0032 }
    L_0x0247:
        r0 = r1.hasNext();	 Catch:{ Throwable -> 0x0032 }
        if (r0 == 0) goto L_0x0272;
    L_0x024d:
        r0 = r1.next();	 Catch:{ Throwable -> 0x0032 }
        r0 = (java.util.Map.Entry) r0;	 Catch:{ Throwable -> 0x0032 }
        r3 = "[key]: %s, [value]: %s";
        r9 = 2;
        r9 = new java.lang.Object[r9];	 Catch:{ Throwable -> 0x0032 }
        r10 = 0;
        r11 = r0.getKey();	 Catch:{ Throwable -> 0x0032 }
        r9[r10] = r11;	 Catch:{ Throwable -> 0x0032 }
        r10 = 1;
        r0 = r0.getValue();	 Catch:{ Throwable -> 0x0032 }
        r9[r10] = r0;	 Catch:{ Throwable -> 0x0032 }
        r0 = java.lang.String.format(r3, r9);	 Catch:{ Throwable -> 0x0032 }
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x0032 }
        com.tencent.bugly.proguard.an.c(r0, r3);	 Catch:{ Throwable -> 0x0032 }
        goto L_0x0247;
    L_0x0272:
        r0 = "[Upload] Failed to upload for no status header.";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x0032 }
        com.tencent.bugly.proguard.an.c(r0, r1);	 Catch:{ Throwable -> 0x0032 }
        r3 = 1;
        r1 = r4;
        r0 = r2;
        goto L_0x017d;
    L_0x0280:
        r0 = "status";
        r0 = r3.get(r0);	 Catch:{ Throwable -> 0x0317 }
        r0 = (java.lang.String) r0;	 Catch:{ Throwable -> 0x0317 }
        r5 = java.lang.Integer.parseInt(r0);	 Catch:{ Throwable -> 0x0317 }
        r0 = "[Upload] Status from server is %d (pid=%d | tid=%d).";
        r9 = 3;
        r9 = new java.lang.Object[r9];	 Catch:{ Throwable -> 0x0317 }
        r10 = 0;
        r11 = java.lang.Integer.valueOf(r5);	 Catch:{ Throwable -> 0x0317 }
        r9[r10] = r11;	 Catch:{ Throwable -> 0x0317 }
        r10 = 1;
        r11 = android.os.Process.myPid();	 Catch:{ Throwable -> 0x0317 }
        r11 = java.lang.Integer.valueOf(r11);	 Catch:{ Throwable -> 0x0317 }
        r9[r10] = r11;	 Catch:{ Throwable -> 0x0317 }
        r10 = 2;
        r11 = android.os.Process.myTid();	 Catch:{ Throwable -> 0x0317 }
        r11 = java.lang.Integer.valueOf(r11);	 Catch:{ Throwable -> 0x0317 }
        r9[r10] = r11;	 Catch:{ Throwable -> 0x0317 }
        com.tencent.bugly.proguard.an.c(r0, r9);	 Catch:{ Throwable -> 0x0317 }
        if (r5 == 0) goto L_0x0356;
    L_0x02b5:
        r0 = 2;
        if (r5 != r0) goto L_0x0339;
    L_0x02b8:
        r0 = r12.d;	 Catch:{ Throwable -> 0x0032 }
        r2 = r12.e;	 Catch:{ Throwable -> 0x0032 }
        r0 = r0 + r2;
        r2 = 0;
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 <= 0) goto L_0x02d8;
    L_0x02c3:
        r0 = r12.m;	 Catch:{ Throwable -> 0x0032 }
        r1 = r12.t;	 Catch:{ Throwable -> 0x0032 }
        r0 = r0.a(r1);	 Catch:{ Throwable -> 0x0032 }
        r2 = r12.d;	 Catch:{ Throwable -> 0x0032 }
        r0 = r0 + r2;
        r2 = r12.e;	 Catch:{ Throwable -> 0x0032 }
        r0 = r0 + r2;
        r2 = r12.m;	 Catch:{ Throwable -> 0x0032 }
        r3 = r12.t;	 Catch:{ Throwable -> 0x0032 }
        r2.a(r0, r3);	 Catch:{ Throwable -> 0x0032 }
    L_0x02d8:
        r0 = r12.m;	 Catch:{ Throwable -> 0x0032 }
        r1 = 0;
        r0.a(r5, r1);	 Catch:{ Throwable -> 0x0032 }
        r0 = "[Upload] Session ID is invalid, will try again immediately (pid=%d | tid=%d).";
        r1 = 2;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x0032 }
        r2 = 0;
        r3 = android.os.Process.myPid();	 Catch:{ Throwable -> 0x0032 }
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ Throwable -> 0x0032 }
        r1[r2] = r3;	 Catch:{ Throwable -> 0x0032 }
        r2 = 1;
        r3 = android.os.Process.myTid();	 Catch:{ Throwable -> 0x0032 }
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ Throwable -> 0x0032 }
        r1[r2] = r3;	 Catch:{ Throwable -> 0x0032 }
        com.tencent.bugly.proguard.an.a(r0, r1);	 Catch:{ Throwable -> 0x0032 }
        r0 = r12.m;	 Catch:{ Throwable -> 0x0032 }
        r1 = r12.n;	 Catch:{ Throwable -> 0x0032 }
        r2 = r12.h;	 Catch:{ Throwable -> 0x0032 }
        r3 = r12.i;	 Catch:{ Throwable -> 0x0032 }
        r4 = r12.q;	 Catch:{ Throwable -> 0x0032 }
        r5 = r12.r;	 Catch:{ Throwable -> 0x0032 }
        r6 = r12.o;	 Catch:{ Throwable -> 0x0032 }
        r7 = r12.a;	 Catch:{ Throwable -> 0x0032 }
        r8 = r12.b;	 Catch:{ Throwable -> 0x0032 }
        r9 = 1;
        r10 = r12.s;	 Catch:{ Throwable -> 0x0032 }
        r0.a(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10);	 Catch:{ Throwable -> 0x0032 }
        goto L_0x0020;
    L_0x0317:
        r0 = move-exception;
        r0 = 1;
        r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0032 }
        r1.<init>();	 Catch:{ Throwable -> 0x0032 }
        r3 = "[Upload] Failed to upload for format of status header is invalid: ";
        r1 = r1.append(r3);	 Catch:{ Throwable -> 0x0032 }
        r3 = java.lang.Integer.toString(r5);	 Catch:{ Throwable -> 0x0032 }
        r1 = r1.append(r3);	 Catch:{ Throwable -> 0x0032 }
        r1 = r1.toString();	 Catch:{ Throwable -> 0x0032 }
        r12.a(r0, r1);	 Catch:{ Throwable -> 0x0032 }
        r3 = 1;
        r1 = r4;
        r0 = r2;
        goto L_0x017d;
    L_0x0339:
        r1 = 0;
        r2 = 0;
        r3 = 1;
        r0 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0032 }
        r0.<init>();	 Catch:{ Throwable -> 0x0032 }
        r4 = "status of server is ";
        r0 = r0.append(r4);	 Catch:{ Throwable -> 0x0032 }
        r0 = r0.append(r5);	 Catch:{ Throwable -> 0x0032 }
        r4 = r0.toString();	 Catch:{ Throwable -> 0x0032 }
        r0 = r12;
        r0.a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0032 }
        goto L_0x0020;
    L_0x0356:
        r0 = "[Upload] Received %d bytes";
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x0032 }
        r4 = 0;
        r6 = r1.length;	 Catch:{ Throwable -> 0x0032 }
        r6 = java.lang.Integer.valueOf(r6);	 Catch:{ Throwable -> 0x0032 }
        r2[r4] = r6;	 Catch:{ Throwable -> 0x0032 }
        com.tencent.bugly.proguard.an.c(r0, r2);	 Catch:{ Throwable -> 0x0032 }
        r0 = r12.f;	 Catch:{ Throwable -> 0x0032 }
        if (r0 == 0) goto L_0x03d0;
    L_0x036b:
        r0 = r1.length;	 Catch:{ Throwable -> 0x0032 }
        if (r0 != 0) goto L_0x03a7;
    L_0x036e:
        r0 = r3.entrySet();	 Catch:{ Throwable -> 0x0032 }
        r1 = r0.iterator();	 Catch:{ Throwable -> 0x0032 }
    L_0x0376:
        r0 = r1.hasNext();	 Catch:{ Throwable -> 0x0032 }
        if (r0 == 0) goto L_0x039a;
    L_0x037c:
        r0 = r1.next();	 Catch:{ Throwable -> 0x0032 }
        r0 = (java.util.Map.Entry) r0;	 Catch:{ Throwable -> 0x0032 }
        r2 = "[Upload] HTTP headers from server: key = %s, value = %s";
        r3 = 2;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x0032 }
        r4 = 0;
        r5 = r0.getKey();	 Catch:{ Throwable -> 0x0032 }
        r3[r4] = r5;	 Catch:{ Throwable -> 0x0032 }
        r4 = 1;
        r0 = r0.getValue();	 Catch:{ Throwable -> 0x0032 }
        r3[r4] = r0;	 Catch:{ Throwable -> 0x0032 }
        com.tencent.bugly.proguard.an.c(r2, r3);	 Catch:{ Throwable -> 0x0032 }
        goto L_0x0376;
    L_0x039a:
        r1 = 0;
        r2 = 0;
        r3 = 1;
        r4 = "response data from server is empty";
        r5 = 0;
        r0 = r12;
        r0.a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0032 }
        goto L_0x0020;
    L_0x03a7:
        r0 = r12.m;	 Catch:{ Throwable -> 0x0032 }
        r0 = r0.b(r1);	 Catch:{ Throwable -> 0x0032 }
        if (r0 != 0) goto L_0x03bc;
    L_0x03af:
        r1 = 0;
        r2 = 0;
        r3 = 1;
        r4 = "failed to decrypt response from server";
        r5 = 0;
        r0 = r12;
        r0.a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0032 }
        goto L_0x0020;
    L_0x03bc:
        r1 = 2;
        r0 = com.tencent.bugly.proguard.ap.b(r0, r1);	 Catch:{ Throwable -> 0x0032 }
        if (r0 != 0) goto L_0x03d1;
    L_0x03c3:
        r1 = 0;
        r2 = 0;
        r3 = 1;
        r4 = "failed unzip(Gzip) response from server";
        r5 = 0;
        r0 = r12;
        r0.a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0032 }
        goto L_0x0020;
    L_0x03d0:
        r0 = r1;
    L_0x03d1:
        r1 = r12.f;	 Catch:{ Throwable -> 0x0032 }
        r1 = com.tencent.bugly.proguard.ah.a(r0, r7, r1);	 Catch:{ Throwable -> 0x0032 }
        if (r1 != 0) goto L_0x03e6;
    L_0x03d9:
        r1 = 0;
        r2 = 0;
        r3 = 1;
        r4 = "failed to decode response package";
        r5 = 0;
        r0 = r12;
        r0.a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0032 }
        goto L_0x0020;
    L_0x03e6:
        r0 = r12.f;	 Catch:{ Throwable -> 0x0032 }
        if (r0 == 0) goto L_0x03ef;
    L_0x03ea:
        r0 = r12.m;	 Catch:{ Throwable -> 0x0032 }
        r0.a(r5, r1);	 Catch:{ Throwable -> 0x0032 }
    L_0x03ef:
        r2 = "[Upload] Response cmd is: %d, length of sBuffer is: %d";
        r0 = 2;
        r3 = new java.lang.Object[r0];	 Catch:{ Throwable -> 0x0032 }
        r0 = 0;
        r4 = r1.b;	 Catch:{ Throwable -> 0x0032 }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ Throwable -> 0x0032 }
        r3[r0] = r4;	 Catch:{ Throwable -> 0x0032 }
        r4 = 1;
        r0 = r1.c;	 Catch:{ Throwable -> 0x0032 }
        if (r0 != 0) goto L_0x0423;
    L_0x0403:
        r0 = 0;
    L_0x0404:
        r0 = java.lang.Integer.valueOf(r0);	 Catch:{ Throwable -> 0x0032 }
        r3[r4] = r0;	 Catch:{ Throwable -> 0x0032 }
        com.tencent.bugly.proguard.an.c(r2, r3);	 Catch:{ Throwable -> 0x0032 }
        r0 = r12.j;	 Catch:{ Throwable -> 0x0032 }
        r2 = r12.k;	 Catch:{ Throwable -> 0x0032 }
        r0 = r12.a(r1, r0, r2);	 Catch:{ Throwable -> 0x0032 }
        if (r0 != 0) goto L_0x0427;
    L_0x0417:
        r2 = 0;
        r3 = 2;
        r4 = "failed to process response package";
        r5 = 0;
        r0 = r12;
        r0.a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0032 }
        goto L_0x0020;
    L_0x0423:
        r0 = r1.c;	 Catch:{ Throwable -> 0x0032 }
        r0 = r0.length;	 Catch:{ Throwable -> 0x0032 }
        goto L_0x0404;
    L_0x0427:
        r2 = 1;
        r3 = 2;
        r4 = "successfully uploaded";
        r5 = 0;
        r0 = r12;
        r0.a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0032 }
        goto L_0x0020;
    L_0x0433:
        r1 = 0;
        r2 = 0;
        r4 = "failed after many attempts";
        r5 = 0;
        r0 = r12;
        r0.a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0032 }
        goto L_0x0020;
    L_0x043f:
        r2 = r0;
        goto L_0x01ca;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.al.run():void");
    }

    public void a(String str, long j, String str2) {
        this.c++;
        this.d += j;
    }

    public void a(long j) {
        this.e += j;
    }

    private static String a(String str) {
        if (!ap.a(str)) {
            try {
                str = String.format("%s?aid=%s", new Object[]{str, UUID.randomUUID().toString()});
            } catch (Throwable th) {
                an.a(th);
            }
        }
        return str;
    }

    private static boolean a(Map<String, String> map) {
        if (map == null || map.size() == 0) {
            an.d("[Upload] Headers is empty.", new Object[0]);
            return false;
        } else if (!map.containsKey(NotificationCompat.CATEGORY_STATUS)) {
            an.d("[Upload] Headers does not contain %s", new Object[]{NotificationCompat.CATEGORY_STATUS});
            return false;
        } else if (map.containsKey("Bugly-Version")) {
            if (((String) map.get("Bugly-Version")).contains("bugly")) {
                an.c("[Upload] Bugly version from headers is: %s", new Object[]{(String) map.get("Bugly-Version")});
                return true;
            }
            an.d("[Upload] Bugly version is not valid: %s", new Object[]{(String) map.get("Bugly-Version")});
            return false;
        } else {
            an.d("[Upload] Headers does not contain %s", new Object[]{"Bugly-Version"});
            return false;
        }
    }
}
