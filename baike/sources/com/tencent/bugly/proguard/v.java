package com.tencent.bugly.proguard;

import android.content.Context;
import com.tencent.bugly.crashreport.common.info.a;
import java.util.Map;
import java.util.UUID;
import qsbk.app.core.utils.PictureGetHelper;

public final class v implements Runnable {
    private int a;
    private int b;
    private final Context c;
    private final int d;
    private final byte[] e;
    private final a f;
    private final com.tencent.bugly.crashreport.common.strategy.a g;
    private final s h;
    private final u i;
    private final int j;
    private final t k;
    private final t l;
    private String m;
    private final String n;
    private final Map<String, String> o;
    private int p;
    private long q;
    private long r;
    private boolean s;
    private boolean t;

    public v(Context context, int i, int i2, byte[] bArr, String str, String str2, t tVar, boolean z, boolean z2) {
        this(context, i, i2, bArr, str, str2, tVar, z, 2, 30000, z2, null);
    }

    public v(Context context, int i, int i2, byte[] bArr, String str, String str2, t tVar, boolean z, int i3, int i4, boolean z2, Map<String, String> map) {
        this.a = 2;
        this.b = 30000;
        this.m = null;
        this.p = 0;
        this.q = 0;
        this.r = 0;
        this.s = true;
        this.t = false;
        this.c = context;
        this.f = a.a(context);
        this.e = bArr;
        this.g = com.tencent.bugly.crashreport.common.strategy.a.a();
        this.h = s.a(context);
        this.i = u.a();
        this.j = i;
        this.m = str;
        this.n = str2;
        this.k = tVar;
        u uVar = this.i;
        this.l = null;
        this.s = z;
        this.d = i2;
        if (i3 > 0) {
            this.a = i3;
        }
        if (i4 > 0) {
            this.b = i4;
        }
        this.t = z2;
        this.o = map;
    }

    private void a(an anVar, boolean z, int i, String str, int i2) {
        String str2;
        switch (this.d) {
            case 630:
            case 830:
                str2 = "crash";
                break;
            case PictureGetHelper.IMAGE_SIZE /*640*/:
            case 840:
                str2 = "userinfo";
                break;
            default:
                str2 = String.valueOf(this.d);
                break;
        }
        if (z) {
            x.a("[Upload] Success: %s", str2);
        } else {
            x.e("[Upload] Failed to upload(%d) %s: %s", Integer.valueOf(i), str2, str);
            if (this.s) {
                this.i.a(i2, null);
            }
        }
        if (this.q + this.r > 0) {
            this.i.a((this.i.a(this.t) + this.q) + this.r, this.t);
        }
        if (this.k != null) {
            t tVar = this.k;
            int i3 = this.d;
            long j = this.q;
            j = this.r;
            tVar.a(z);
        }
        if (this.l != null) {
            tVar = this.l;
            i3 = this.d;
            j = this.q;
            j = this.r;
            tVar.a(z);
        }
    }

    private static boolean a(an anVar, a aVar, com.tencent.bugly.crashreport.common.strategy.a aVar2) {
        if (anVar == null) {
            x.d("resp == null!", new Object[0]);
            return false;
        } else if (anVar.a != (byte) 0) {
            x.e("resp result error %d", Byte.valueOf(anVar.a));
            return false;
        } else {
            try {
                if (!(z.a(anVar.d) || a.b().i().equals(anVar.d))) {
                    p.a().a(com.tencent.bugly.crashreport.common.strategy.a.a, "key_ip", anVar.d.getBytes("UTF-8"), null, true);
                    aVar.d(anVar.d);
                }
                if (!(z.a(anVar.f) || a.b().j().equals(anVar.f))) {
                    p.a().a(com.tencent.bugly.crashreport.common.strategy.a.a, "key_imei", anVar.f.getBytes("UTF-8"), null, true);
                    aVar.e(anVar.f);
                }
            } catch (Throwable th) {
                x.a(th);
            }
            aVar.i = anVar.e;
            if (anVar.b == 510) {
                if (anVar.c == null) {
                    x.e("[Upload] Strategy data is null. Response cmd: %d", Integer.valueOf(anVar.b));
                    return false;
                }
                ap apVar = (ap) a.a(anVar.c, ap.class);
                if (apVar == null) {
                    x.e("[Upload] Failed to decode strategy from server. Response cmd: %d", Integer.valueOf(anVar.b));
                    return false;
                }
                aVar2.a(apVar);
            }
            return true;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
        r11 = this;
        r0 = 0;
        r11.p = r0;	 Catch:{ Throwable -> 0x0030 }
        r0 = 0;
        r11.q = r0;	 Catch:{ Throwable -> 0x0030 }
        r0 = 0;
        r11.r = r0;	 Catch:{ Throwable -> 0x0030 }
        r0 = r11.e;	 Catch:{ Throwable -> 0x0030 }
        r1 = r11.c;	 Catch:{ Throwable -> 0x0030 }
        r1 = com.tencent.bugly.crashreport.common.info.b.e(r1);	 Catch:{ Throwable -> 0x0030 }
        if (r1 != 0) goto L_0x0020;
    L_0x0015:
        r1 = 0;
        r2 = 0;
        r3 = 0;
        r4 = "network is not available";
        r5 = 0;
        r0 = r11;
        r0.a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0030 }
    L_0x001f:
        return;
    L_0x0020:
        if (r0 == 0) goto L_0x0025;
    L_0x0022:
        r1 = r0.length;	 Catch:{ Throwable -> 0x0030 }
        if (r1 != 0) goto L_0x003b;
    L_0x0025:
        r1 = 0;
        r2 = 0;
        r3 = 0;
        r4 = "request package is empty!";
        r5 = 0;
        r0 = r11;
        r0.a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0030 }
        goto L_0x001f;
    L_0x0030:
        r0 = move-exception;
        r1 = com.tencent.bugly.proguard.x.a(r0);
        if (r1 != 0) goto L_0x001f;
    L_0x0037:
        r0.printStackTrace();
        goto L_0x001f;
    L_0x003b:
        r1 = r11.i;	 Catch:{ Throwable -> 0x0030 }
        r2 = r11.t;	 Catch:{ Throwable -> 0x0030 }
        r2 = r1.a(r2);	 Catch:{ Throwable -> 0x0030 }
        r1 = r0.length;	 Catch:{ Throwable -> 0x0030 }
        r4 = (long) r1;	 Catch:{ Throwable -> 0x0030 }
        r4 = r4 + r2;
        r6 = 2097152; // 0x200000 float:2.938736E-39 double:1.0361308E-317;
        r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r1 < 0) goto L_0x0086;
    L_0x004d:
        r0 = "[Upload] Upload too much data, try next time: %d/%d";
        r1 = 2;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x0030 }
        r4 = 0;
        r2 = java.lang.Long.valueOf(r2);	 Catch:{ Throwable -> 0x0030 }
        r1[r4] = r2;	 Catch:{ Throwable -> 0x0030 }
        r2 = 1;
        r4 = 2097152; // 0x200000 float:2.938736E-39 double:1.0361308E-317;
        r3 = java.lang.Long.valueOf(r4);	 Catch:{ Throwable -> 0x0030 }
        r1[r2] = r3;	 Catch:{ Throwable -> 0x0030 }
        com.tencent.bugly.proguard.x.e(r0, r1);	 Catch:{ Throwable -> 0x0030 }
        r1 = 0;
        r2 = 0;
        r3 = 0;
        r0 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0030 }
        r4 = "over net consume: ";
        r0.<init>(r4);	 Catch:{ Throwable -> 0x0030 }
        r4 = 2048; // 0x800 float:2.87E-42 double:1.0118E-320;
        r0 = r0.append(r4);	 Catch:{ Throwable -> 0x0030 }
        r4 = "K";
        r0 = r0.append(r4);	 Catch:{ Throwable -> 0x0030 }
        r4 = r0.toString();	 Catch:{ Throwable -> 0x0030 }
        r5 = 0;
        r0 = r11;
        r0.a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0030 }
        goto L_0x001f;
    L_0x0086:
        r1 = "[Upload] Run upload task with cmd: %d";
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x0030 }
        r3 = 0;
        r4 = r11.d;	 Catch:{ Throwable -> 0x0030 }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ Throwable -> 0x0030 }
        r2[r3] = r4;	 Catch:{ Throwable -> 0x0030 }
        com.tencent.bugly.proguard.x.c(r1, r2);	 Catch:{ Throwable -> 0x0030 }
        r1 = r11.c;	 Catch:{ Throwable -> 0x0030 }
        if (r1 == 0) goto L_0x00a7;
    L_0x009b:
        r1 = r11.f;	 Catch:{ Throwable -> 0x0030 }
        if (r1 == 0) goto L_0x00a7;
    L_0x009f:
        r1 = r11.g;	 Catch:{ Throwable -> 0x0030 }
        if (r1 == 0) goto L_0x00a7;
    L_0x00a3:
        r1 = r11.h;	 Catch:{ Throwable -> 0x0030 }
        if (r1 != 0) goto L_0x00b3;
    L_0x00a7:
        r1 = 0;
        r2 = 0;
        r3 = 0;
        r4 = "illegal access error";
        r5 = 0;
        r0 = r11;
        r0.a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0030 }
        goto L_0x001f;
    L_0x00b3:
        r1 = r11.g;	 Catch:{ Throwable -> 0x0030 }
        r1 = r1.c();	 Catch:{ Throwable -> 0x0030 }
        if (r1 != 0) goto L_0x00c7;
    L_0x00bb:
        r1 = 0;
        r2 = 0;
        r3 = 0;
        r4 = "illegal local strategy";
        r5 = 0;
        r0 = r11;
        r0.a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0030 }
        goto L_0x001f;
    L_0x00c7:
        r3 = 0;
        r7 = new java.util.HashMap;	 Catch:{ Throwable -> 0x0030 }
        r7.<init>();	 Catch:{ Throwable -> 0x0030 }
        r2 = "prodId";
        r4 = r11.f;	 Catch:{ Throwable -> 0x0030 }
        r4 = r4.f();	 Catch:{ Throwable -> 0x0030 }
        r7.put(r2, r4);	 Catch:{ Throwable -> 0x0030 }
        r2 = "bundleId";
        r4 = r11.f;	 Catch:{ Throwable -> 0x0030 }
        r4 = r4.c;	 Catch:{ Throwable -> 0x0030 }
        r7.put(r2, r4);	 Catch:{ Throwable -> 0x0030 }
        r2 = "appVer";
        r4 = r11.f;	 Catch:{ Throwable -> 0x0030 }
        r4 = r4.j;	 Catch:{ Throwable -> 0x0030 }
        r7.put(r2, r4);	 Catch:{ Throwable -> 0x0030 }
        r2 = r11.o;	 Catch:{ Throwable -> 0x0030 }
        if (r2 == 0) goto L_0x00f3;
    L_0x00ee:
        r2 = r11.o;	 Catch:{ Throwable -> 0x0030 }
        r7.putAll(r2);	 Catch:{ Throwable -> 0x0030 }
    L_0x00f3:
        r2 = r11.s;	 Catch:{ Throwable -> 0x0030 }
        if (r2 == 0) goto L_0x015e;
    L_0x00f7:
        r2 = "cmd";
        r4 = r11.d;	 Catch:{ Throwable -> 0x0030 }
        r4 = java.lang.Integer.toString(r4);	 Catch:{ Throwable -> 0x0030 }
        r7.put(r2, r4);	 Catch:{ Throwable -> 0x0030 }
        r2 = "platformId";
        r4 = 1;
        r4 = java.lang.Byte.toString(r4);	 Catch:{ Throwable -> 0x0030 }
        r7.put(r2, r4);	 Catch:{ Throwable -> 0x0030 }
        r2 = "sdkVer";
        r4 = r11.f;	 Catch:{ Throwable -> 0x0030 }
        r4.getClass();	 Catch:{ Throwable -> 0x0030 }
        r4 = "2.6.5";
        r7.put(r2, r4);	 Catch:{ Throwable -> 0x0030 }
        r2 = "strategylastUpdateTime";
        r4 = r1.p;	 Catch:{ Throwable -> 0x0030 }
        r1 = java.lang.Long.toString(r4);	 Catch:{ Throwable -> 0x0030 }
        r7.put(r2, r1);	 Catch:{ Throwable -> 0x0030 }
        r1 = r11.i;	 Catch:{ Throwable -> 0x0030 }
        r1 = r1.a(r7);	 Catch:{ Throwable -> 0x0030 }
        if (r1 != 0) goto L_0x0137;
    L_0x012b:
        r1 = 0;
        r2 = 0;
        r3 = 0;
        r4 = "failed to add security info to HTTP headers";
        r5 = 0;
        r0 = r11;
        r0.a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0030 }
        goto L_0x001f;
    L_0x0137:
        r1 = 2;
        r0 = com.tencent.bugly.proguard.z.a(r0, r1);	 Catch:{ Throwable -> 0x0030 }
        if (r0 != 0) goto L_0x014a;
    L_0x013e:
        r1 = 0;
        r2 = 0;
        r3 = 0;
        r4 = "failed to zip request body";
        r5 = 0;
        r0 = r11;
        r0.a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0030 }
        goto L_0x001f;
    L_0x014a:
        r1 = r11.i;	 Catch:{ Throwable -> 0x0030 }
        r0 = r1.a(r0);	 Catch:{ Throwable -> 0x0030 }
        if (r0 != 0) goto L_0x015e;
    L_0x0152:
        r1 = 0;
        r2 = 0;
        r3 = 0;
        r4 = "failed to encrypt request body";
        r5 = 0;
        r0 = r11;
        r0.a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0030 }
        goto L_0x001f;
    L_0x015e:
        r6 = r0;
        r0 = r11.i;	 Catch:{ Throwable -> 0x0030 }
        r1 = r11.j;	 Catch:{ Throwable -> 0x0030 }
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ Throwable -> 0x0030 }
        r0.a(r1, r4);	 Catch:{ Throwable -> 0x0030 }
        r0 = r11.k;	 Catch:{ Throwable -> 0x0030 }
        if (r0 == 0) goto L_0x0172;
    L_0x016e:
        r0 = r11.k;	 Catch:{ Throwable -> 0x0030 }
        r0 = r11.d;	 Catch:{ Throwable -> 0x0030 }
    L_0x0172:
        r0 = r11.l;	 Catch:{ Throwable -> 0x0030 }
        if (r0 == 0) goto L_0x017a;
    L_0x0176:
        r0 = r11.l;	 Catch:{ Throwable -> 0x0030 }
        r0 = r11.d;	 Catch:{ Throwable -> 0x0030 }
    L_0x017a:
        r2 = r11.m;	 Catch:{ Throwable -> 0x0030 }
        r5 = -1;
        r0 = 0;
        r1 = r0;
        r0 = r2;
    L_0x0180:
        r4 = r1 + 1;
        r2 = r11.a;	 Catch:{ Throwable -> 0x0030 }
        if (r1 >= r2) goto L_0x04ac;
    L_0x0186:
        r1 = 1;
        if (r4 <= r1) goto L_0x01b1;
    L_0x0189:
        r1 = "[Upload] Failed to upload last time, wait and try(%d) again.";
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x0030 }
        r3 = 0;
        r8 = java.lang.Integer.valueOf(r4);	 Catch:{ Throwable -> 0x0030 }
        r2[r3] = r8;	 Catch:{ Throwable -> 0x0030 }
        com.tencent.bugly.proguard.x.d(r1, r2);	 Catch:{ Throwable -> 0x0030 }
        r1 = r11.b;	 Catch:{ Throwable -> 0x0030 }
        r2 = (long) r1;	 Catch:{ Throwable -> 0x0030 }
        com.tencent.bugly.proguard.z.b(r2);	 Catch:{ Throwable -> 0x0030 }
        r1 = r11.a;	 Catch:{ Throwable -> 0x0030 }
        if (r4 != r1) goto L_0x01b1;
    L_0x01a2:
        r0 = "[Upload] Use the back-up url at the last time: %s";
        r1 = 1;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x0030 }
        r2 = 0;
        r3 = r11.n;	 Catch:{ Throwable -> 0x0030 }
        r1[r2] = r3;	 Catch:{ Throwable -> 0x0030 }
        com.tencent.bugly.proguard.x.d(r0, r1);	 Catch:{ Throwable -> 0x0030 }
        r0 = r11.n;	 Catch:{ Throwable -> 0x0030 }
    L_0x01b1:
        r1 = "[Upload] Send %d bytes";
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x0030 }
        r3 = 0;
        r8 = r6.length;	 Catch:{ Throwable -> 0x0030 }
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ Throwable -> 0x0030 }
        r2[r3] = r8;	 Catch:{ Throwable -> 0x0030 }
        com.tencent.bugly.proguard.x.c(r1, r2);	 Catch:{ Throwable -> 0x0030 }
        r1 = r11.s;	 Catch:{ Throwable -> 0x0030 }
        if (r1 == 0) goto L_0x04b7;
    L_0x01c5:
        r0 = a(r0);	 Catch:{ Throwable -> 0x0030 }
        r2 = r0;
    L_0x01ca:
        r0 = "[Upload] Upload to %s with cmd %d (pid=%d | tid=%d).";
        r1 = 4;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x0030 }
        r3 = 0;
        r1[r3] = r2;	 Catch:{ Throwable -> 0x0030 }
        r3 = 1;
        r8 = r11.d;	 Catch:{ Throwable -> 0x0030 }
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ Throwable -> 0x0030 }
        r1[r3] = r8;	 Catch:{ Throwable -> 0x0030 }
        r3 = 2;
        r8 = android.os.Process.myPid();	 Catch:{ Throwable -> 0x0030 }
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ Throwable -> 0x0030 }
        r1[r3] = r8;	 Catch:{ Throwable -> 0x0030 }
        r3 = 3;
        r8 = android.os.Process.myTid();	 Catch:{ Throwable -> 0x0030 }
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ Throwable -> 0x0030 }
        r1[r3] = r8;	 Catch:{ Throwable -> 0x0030 }
        com.tencent.bugly.proguard.x.c(r0, r1);	 Catch:{ Throwable -> 0x0030 }
        r0 = r11.h;	 Catch:{ Throwable -> 0x0030 }
        r1 = r0.a(r2, r6, r11, r7);	 Catch:{ Throwable -> 0x0030 }
        if (r1 != 0) goto L_0x0216;
    L_0x01fc:
        r0 = "Failed to upload for no response!";
        r1 = "[Upload] Failed to upload(%d): %s";
        r3 = 2;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x0030 }
        r8 = 0;
        r9 = 1;
        r9 = java.lang.Integer.valueOf(r9);	 Catch:{ Throwable -> 0x0030 }
        r3[r8] = r9;	 Catch:{ Throwable -> 0x0030 }
        r8 = 1;
        r3[r8] = r0;	 Catch:{ Throwable -> 0x0030 }
        com.tencent.bugly.proguard.x.e(r1, r3);	 Catch:{ Throwable -> 0x0030 }
        r3 = 1;
        r1 = r4;
        r0 = r2;
        goto L_0x0180;
    L_0x0216:
        r0 = r11.h;	 Catch:{ Throwable -> 0x0030 }
        r3 = r0.a;	 Catch:{ Throwable -> 0x0030 }
        r0 = r11.s;	 Catch:{ Throwable -> 0x0030 }
        if (r0 == 0) goto L_0x03d8;
    L_0x021e:
        if (r3 == 0) goto L_0x0226;
    L_0x0220:
        r0 = r3.size();	 Catch:{ Throwable -> 0x0030 }
        if (r0 != 0) goto L_0x0298;
    L_0x0226:
        r0 = "[Upload] Headers is empty.";
        r8 = 0;
        r8 = new java.lang.Object[r8];	 Catch:{ Throwable -> 0x0030 }
        com.tencent.bugly.proguard.x.d(r0, r8);	 Catch:{ Throwable -> 0x0030 }
        r0 = 0;
    L_0x022f:
        if (r0 != 0) goto L_0x0300;
    L_0x0231:
        r0 = "[Upload] Headers from server is not valid, just try again (pid=%d | tid=%d).";
        r1 = 2;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x0030 }
        r8 = 0;
        r9 = android.os.Process.myPid();	 Catch:{ Throwable -> 0x0030 }
        r9 = java.lang.Integer.valueOf(r9);	 Catch:{ Throwable -> 0x0030 }
        r1[r8] = r9;	 Catch:{ Throwable -> 0x0030 }
        r8 = 1;
        r9 = android.os.Process.myTid();	 Catch:{ Throwable -> 0x0030 }
        r9 = java.lang.Integer.valueOf(r9);	 Catch:{ Throwable -> 0x0030 }
        r1[r8] = r9;	 Catch:{ Throwable -> 0x0030 }
        com.tencent.bugly.proguard.x.c(r0, r1);	 Catch:{ Throwable -> 0x0030 }
        r0 = "[Upload] Failed to upload for no status header.";
        r1 = "[Upload] Failed to upload(%d): %s";
        r8 = 2;
        r8 = new java.lang.Object[r8];	 Catch:{ Throwable -> 0x0030 }
        r9 = 0;
        r10 = 1;
        r10 = java.lang.Integer.valueOf(r10);	 Catch:{ Throwable -> 0x0030 }
        r8[r9] = r10;	 Catch:{ Throwable -> 0x0030 }
        r9 = 1;
        r8[r9] = r0;	 Catch:{ Throwable -> 0x0030 }
        com.tencent.bugly.proguard.x.e(r1, r8);	 Catch:{ Throwable -> 0x0030 }
        if (r3 == 0) goto L_0x02f3;
    L_0x0266:
        r0 = r3.entrySet();	 Catch:{ Throwable -> 0x0030 }
        r1 = r0.iterator();	 Catch:{ Throwable -> 0x0030 }
    L_0x026e:
        r0 = r1.hasNext();	 Catch:{ Throwable -> 0x0030 }
        if (r0 == 0) goto L_0x02f3;
    L_0x0274:
        r0 = r1.next();	 Catch:{ Throwable -> 0x0030 }
        r0 = (java.util.Map.Entry) r0;	 Catch:{ Throwable -> 0x0030 }
        r3 = "[key]: %s, [value]: %s";
        r8 = 2;
        r8 = new java.lang.Object[r8];	 Catch:{ Throwable -> 0x0030 }
        r9 = 0;
        r10 = r0.getKey();	 Catch:{ Throwable -> 0x0030 }
        r8[r9] = r10;	 Catch:{ Throwable -> 0x0030 }
        r9 = 1;
        r0 = r0.getValue();	 Catch:{ Throwable -> 0x0030 }
        r8[r9] = r0;	 Catch:{ Throwable -> 0x0030 }
        r0 = java.lang.String.format(r3, r8);	 Catch:{ Throwable -> 0x0030 }
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x0030 }
        com.tencent.bugly.proguard.x.c(r0, r3);	 Catch:{ Throwable -> 0x0030 }
        goto L_0x026e;
    L_0x0298:
        r0 = "status";
        r0 = r3.containsKey(r0);	 Catch:{ Throwable -> 0x0030 }
        if (r0 != 0) goto L_0x02af;
    L_0x02a0:
        r0 = "[Upload] Headers does not contain %s";
        r8 = 1;
        r8 = new java.lang.Object[r8];	 Catch:{ Throwable -> 0x0030 }
        r9 = 0;
        r10 = "status";
        r8[r9] = r10;	 Catch:{ Throwable -> 0x0030 }
        com.tencent.bugly.proguard.x.d(r0, r8);	 Catch:{ Throwable -> 0x0030 }
        r0 = 0;
        goto L_0x022f;
    L_0x02af:
        r0 = "Bugly-Version";
        r0 = r3.containsKey(r0);	 Catch:{ Throwable -> 0x0030 }
        if (r0 != 0) goto L_0x02c7;
    L_0x02b7:
        r0 = "[Upload] Headers does not contain %s";
        r8 = 1;
        r8 = new java.lang.Object[r8];	 Catch:{ Throwable -> 0x0030 }
        r9 = 0;
        r10 = "Bugly-Version";
        r8[r9] = r10;	 Catch:{ Throwable -> 0x0030 }
        com.tencent.bugly.proguard.x.d(r0, r8);	 Catch:{ Throwable -> 0x0030 }
        r0 = 0;
        goto L_0x022f;
    L_0x02c7:
        r0 = "Bugly-Version";
        r0 = r3.get(r0);	 Catch:{ Throwable -> 0x0030 }
        r0 = (java.lang.String) r0;	 Catch:{ Throwable -> 0x0030 }
        r8 = "bugly";
        r8 = r0.contains(r8);	 Catch:{ Throwable -> 0x0030 }
        if (r8 != 0) goto L_0x02e5;
    L_0x02d7:
        r8 = "[Upload] Bugly version is not valid: %s";
        r9 = 1;
        r9 = new java.lang.Object[r9];	 Catch:{ Throwable -> 0x0030 }
        r10 = 0;
        r9[r10] = r0;	 Catch:{ Throwable -> 0x0030 }
        com.tencent.bugly.proguard.x.d(r8, r9);	 Catch:{ Throwable -> 0x0030 }
        r0 = 0;
        goto L_0x022f;
    L_0x02e5:
        r8 = "[Upload] Bugly version from headers is: %s";
        r9 = 1;
        r9 = new java.lang.Object[r9];	 Catch:{ Throwable -> 0x0030 }
        r10 = 0;
        r9[r10] = r0;	 Catch:{ Throwable -> 0x0030 }
        com.tencent.bugly.proguard.x.c(r8, r9);	 Catch:{ Throwable -> 0x0030 }
        r0 = 1;
        goto L_0x022f;
    L_0x02f3:
        r0 = "[Upload] Failed to upload for no status header.";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x0030 }
        com.tencent.bugly.proguard.x.c(r0, r1);	 Catch:{ Throwable -> 0x0030 }
        r3 = 1;
        r1 = r4;
        r0 = r2;
        goto L_0x0180;
    L_0x0300:
        r0 = "status";
        r0 = r3.get(r0);	 Catch:{ Throwable -> 0x0394 }
        r0 = (java.lang.String) r0;	 Catch:{ Throwable -> 0x0394 }
        r5 = java.lang.Integer.parseInt(r0);	 Catch:{ Throwable -> 0x0394 }
        r0 = "[Upload] Status from server is %d (pid=%d | tid=%d).";
        r8 = 3;
        r8 = new java.lang.Object[r8];	 Catch:{ Throwable -> 0x0394 }
        r9 = 0;
        r10 = java.lang.Integer.valueOf(r5);	 Catch:{ Throwable -> 0x0394 }
        r8[r9] = r10;	 Catch:{ Throwable -> 0x0394 }
        r9 = 1;
        r10 = android.os.Process.myPid();	 Catch:{ Throwable -> 0x0394 }
        r10 = java.lang.Integer.valueOf(r10);	 Catch:{ Throwable -> 0x0394 }
        r8[r9] = r10;	 Catch:{ Throwable -> 0x0394 }
        r9 = 2;
        r10 = android.os.Process.myTid();	 Catch:{ Throwable -> 0x0394 }
        r10 = java.lang.Integer.valueOf(r10);	 Catch:{ Throwable -> 0x0394 }
        r8[r9] = r10;	 Catch:{ Throwable -> 0x0394 }
        com.tencent.bugly.proguard.x.c(r0, r8);	 Catch:{ Throwable -> 0x0394 }
        if (r5 == 0) goto L_0x03d8;
    L_0x0333:
        r0 = 2;
        if (r5 != r0) goto L_0x03c0;
    L_0x0336:
        r0 = r11.q;	 Catch:{ Throwable -> 0x0030 }
        r2 = r11.r;	 Catch:{ Throwable -> 0x0030 }
        r0 = r0 + r2;
        r2 = 0;
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 <= 0) goto L_0x0356;
    L_0x0341:
        r0 = r11.i;	 Catch:{ Throwable -> 0x0030 }
        r1 = r11.t;	 Catch:{ Throwable -> 0x0030 }
        r0 = r0.a(r1);	 Catch:{ Throwable -> 0x0030 }
        r2 = r11.q;	 Catch:{ Throwable -> 0x0030 }
        r0 = r0 + r2;
        r2 = r11.r;	 Catch:{ Throwable -> 0x0030 }
        r0 = r0 + r2;
        r2 = r11.i;	 Catch:{ Throwable -> 0x0030 }
        r3 = r11.t;	 Catch:{ Throwable -> 0x0030 }
        r2.a(r0, r3);	 Catch:{ Throwable -> 0x0030 }
    L_0x0356:
        r0 = r11.i;	 Catch:{ Throwable -> 0x0030 }
        r1 = 0;
        r0.a(r5, r1);	 Catch:{ Throwable -> 0x0030 }
        r0 = "[Upload] Session ID is invalid, will try again immediately (pid=%d | tid=%d).";
        r1 = 2;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x0030 }
        r2 = 0;
        r3 = android.os.Process.myPid();	 Catch:{ Throwable -> 0x0030 }
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ Throwable -> 0x0030 }
        r1[r2] = r3;	 Catch:{ Throwable -> 0x0030 }
        r2 = 1;
        r3 = android.os.Process.myTid();	 Catch:{ Throwable -> 0x0030 }
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ Throwable -> 0x0030 }
        r1[r2] = r3;	 Catch:{ Throwable -> 0x0030 }
        com.tencent.bugly.proguard.x.a(r0, r1);	 Catch:{ Throwable -> 0x0030 }
        r0 = r11.i;	 Catch:{ Throwable -> 0x0030 }
        r1 = r11.j;	 Catch:{ Throwable -> 0x0030 }
        r2 = r11.d;	 Catch:{ Throwable -> 0x0030 }
        r3 = r11.e;	 Catch:{ Throwable -> 0x0030 }
        r4 = r11.m;	 Catch:{ Throwable -> 0x0030 }
        r5 = r11.n;	 Catch:{ Throwable -> 0x0030 }
        r6 = r11.k;	 Catch:{ Throwable -> 0x0030 }
        r7 = r11.a;	 Catch:{ Throwable -> 0x0030 }
        r8 = r11.b;	 Catch:{ Throwable -> 0x0030 }
        r9 = 1;
        r10 = r11.o;	 Catch:{ Throwable -> 0x0030 }
        r0.a(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10);	 Catch:{ Throwable -> 0x0030 }
        goto L_0x001f;
    L_0x0394:
        r0 = move-exception;
        r0 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0030 }
        r1 = "[Upload] Failed to upload for format of status header is invalid: ";
        r0.<init>(r1);	 Catch:{ Throwable -> 0x0030 }
        r1 = java.lang.Integer.toString(r5);	 Catch:{ Throwable -> 0x0030 }
        r0 = r0.append(r1);	 Catch:{ Throwable -> 0x0030 }
        r0 = r0.toString();	 Catch:{ Throwable -> 0x0030 }
        r1 = "[Upload] Failed to upload(%d): %s";
        r3 = 2;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x0030 }
        r8 = 0;
        r9 = 1;
        r9 = java.lang.Integer.valueOf(r9);	 Catch:{ Throwable -> 0x0030 }
        r3[r8] = r9;	 Catch:{ Throwable -> 0x0030 }
        r8 = 1;
        r3[r8] = r0;	 Catch:{ Throwable -> 0x0030 }
        com.tencent.bugly.proguard.x.e(r1, r3);	 Catch:{ Throwable -> 0x0030 }
        r3 = 1;
        r1 = r4;
        r0 = r2;
        goto L_0x0180;
    L_0x03c0:
        r1 = 0;
        r2 = 0;
        r3 = 1;
        r0 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0030 }
        r4 = "status of server is ";
        r0.<init>(r4);	 Catch:{ Throwable -> 0x0030 }
        r0 = r0.append(r5);	 Catch:{ Throwable -> 0x0030 }
        r4 = r0.toString();	 Catch:{ Throwable -> 0x0030 }
        r0 = r11;
        r0.a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0030 }
        goto L_0x001f;
    L_0x03d8:
        r0 = "[Upload] Received %d bytes";
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x0030 }
        r4 = 0;
        r6 = r1.length;	 Catch:{ Throwable -> 0x0030 }
        r6 = java.lang.Integer.valueOf(r6);	 Catch:{ Throwable -> 0x0030 }
        r2[r4] = r6;	 Catch:{ Throwable -> 0x0030 }
        com.tencent.bugly.proguard.x.c(r0, r2);	 Catch:{ Throwable -> 0x0030 }
        r0 = r11.s;	 Catch:{ Throwable -> 0x0030 }
        if (r0 == 0) goto L_0x044d;
    L_0x03ec:
        r0 = r1.length;	 Catch:{ Throwable -> 0x0030 }
        if (r0 != 0) goto L_0x0426;
    L_0x03ef:
        r0 = r3.entrySet();	 Catch:{ Throwable -> 0x0030 }
        r1 = r0.iterator();	 Catch:{ Throwable -> 0x0030 }
    L_0x03f7:
        r0 = r1.hasNext();	 Catch:{ Throwable -> 0x0030 }
        if (r0 == 0) goto L_0x041a;
    L_0x03fd:
        r0 = r1.next();	 Catch:{ Throwable -> 0x0030 }
        r0 = (java.util.Map.Entry) r0;	 Catch:{ Throwable -> 0x0030 }
        r2 = "[Upload] HTTP headers from server: key = %s, value = %s";
        r3 = 2;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x0030 }
        r4 = 0;
        r5 = r0.getKey();	 Catch:{ Throwable -> 0x0030 }
        r3[r4] = r5;	 Catch:{ Throwable -> 0x0030 }
        r4 = 1;
        r0 = r0.getValue();	 Catch:{ Throwable -> 0x0030 }
        r3[r4] = r0;	 Catch:{ Throwable -> 0x0030 }
        com.tencent.bugly.proguard.x.c(r2, r3);	 Catch:{ Throwable -> 0x0030 }
        goto L_0x03f7;
    L_0x041a:
        r1 = 0;
        r2 = 0;
        r3 = 1;
        r4 = "response data from server is empty";
        r5 = 0;
        r0 = r11;
        r0.a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0030 }
        goto L_0x001f;
    L_0x0426:
        r0 = r11.i;	 Catch:{ Throwable -> 0x0030 }
        r0 = r0.b(r1);	 Catch:{ Throwable -> 0x0030 }
        if (r0 != 0) goto L_0x043a;
    L_0x042e:
        r1 = 0;
        r2 = 0;
        r3 = 1;
        r4 = "failed to decrypt response from server";
        r5 = 0;
        r0 = r11;
        r0.a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0030 }
        goto L_0x001f;
    L_0x043a:
        r1 = 2;
        r0 = com.tencent.bugly.proguard.z.b(r0, r1);	 Catch:{ Throwable -> 0x0030 }
        if (r0 != 0) goto L_0x044e;
    L_0x0441:
        r1 = 0;
        r2 = 0;
        r3 = 1;
        r4 = "failed unzip(Gzip) response from server";
        r5 = 0;
        r0 = r11;
        r0.a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0030 }
        goto L_0x001f;
    L_0x044d:
        r0 = r1;
    L_0x044e:
        r1 = r11.s;	 Catch:{ Throwable -> 0x0030 }
        r1 = com.tencent.bugly.proguard.a.a(r0, r1);	 Catch:{ Throwable -> 0x0030 }
        if (r1 != 0) goto L_0x0462;
    L_0x0456:
        r1 = 0;
        r2 = 0;
        r3 = 1;
        r4 = "failed to decode response package";
        r5 = 0;
        r0 = r11;
        r0.a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0030 }
        goto L_0x001f;
    L_0x0462:
        r0 = r11.s;	 Catch:{ Throwable -> 0x0030 }
        if (r0 == 0) goto L_0x046b;
    L_0x0466:
        r0 = r11.i;	 Catch:{ Throwable -> 0x0030 }
        r0.a(r5, r1);	 Catch:{ Throwable -> 0x0030 }
    L_0x046b:
        r2 = "[Upload] Response cmd is: %d, length of sBuffer is: %d";
        r0 = 2;
        r3 = new java.lang.Object[r0];	 Catch:{ Throwable -> 0x0030 }
        r0 = 0;
        r4 = r1.b;	 Catch:{ Throwable -> 0x0030 }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ Throwable -> 0x0030 }
        r3[r0] = r4;	 Catch:{ Throwable -> 0x0030 }
        r4 = 1;
        r0 = r1.c;	 Catch:{ Throwable -> 0x0030 }
        if (r0 != 0) goto L_0x049d;
    L_0x047e:
        r0 = 0;
    L_0x047f:
        r0 = java.lang.Integer.valueOf(r0);	 Catch:{ Throwable -> 0x0030 }
        r3[r4] = r0;	 Catch:{ Throwable -> 0x0030 }
        com.tencent.bugly.proguard.x.c(r2, r3);	 Catch:{ Throwable -> 0x0030 }
        r0 = r11.f;	 Catch:{ Throwable -> 0x0030 }
        r2 = r11.g;	 Catch:{ Throwable -> 0x0030 }
        r0 = a(r1, r0, r2);	 Catch:{ Throwable -> 0x0030 }
        if (r0 != 0) goto L_0x04a1;
    L_0x0492:
        r2 = 0;
        r3 = 2;
        r4 = "failed to process response package";
        r5 = 0;
        r0 = r11;
        r0.a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0030 }
        goto L_0x001f;
    L_0x049d:
        r0 = r1.c;	 Catch:{ Throwable -> 0x0030 }
        r0 = r0.length;	 Catch:{ Throwable -> 0x0030 }
        goto L_0x047f;
    L_0x04a1:
        r2 = 1;
        r3 = 2;
        r4 = "successfully uploaded";
        r5 = 0;
        r0 = r11;
        r0.a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0030 }
        goto L_0x001f;
    L_0x04ac:
        r1 = 0;
        r2 = 0;
        r4 = "failed after many attempts";
        r5 = 0;
        r0 = r11;
        r0.a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0030 }
        goto L_0x001f;
    L_0x04b7:
        r2 = r0;
        goto L_0x01ca;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.v.run():void");
    }

    public final void a(long j) {
        this.p++;
        this.q += j;
    }

    public final void b(long j) {
        this.r += j;
    }

    private static String a(String str) {
        if (!z.a(str)) {
            try {
                str = String.format("%s?aid=%s", new Object[]{str, UUID.randomUUID().toString()});
            } catch (Throwable th) {
                x.a(th);
            }
        }
        return str;
    }
}
