package com.xiaomi.smack;

import android.os.SystemClock;
import android.support.v4.view.ViewCompat;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.channel.commonutils.network.d;
import com.xiaomi.network.Fallback;
import com.xiaomi.network.HostManager;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.smack.util.e;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public abstract class h extends a {
    protected Exception p = null;
    protected Socket q;
    String r = null;
    protected XMPushService s;
    protected volatile long t = 0;
    protected volatile long u = 0;
    protected volatile long v = 0;
    private String w;
    private int x;

    public h(XMPushService xMPushService, b bVar) {
        super(xMPushService, bVar);
        this.s = xMPushService;
    }

    private void a(b bVar) {
        a(bVar.e(), bVar.d());
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(java.lang.String r17, int r18) {
        /*
        r16 = this;
        r4 = 0;
        r2 = 0;
        r0 = r16;
        r0.p = r2;
        r3 = new java.util.ArrayList;
        r3.<init>();
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r5 = "get bucket for host : ";
        r2 = r2.append(r5);
        r0 = r17;
        r2 = r2.append(r0);
        r2 = r2.toString();
        r2 = com.xiaomi.channel.commonutils.logger.b.e(r2);
        r5 = r2.intValue();
        r2 = r16.b(r17);
        r5 = java.lang.Integer.valueOf(r5);
        com.xiaomi.channel.commonutils.logger.b.a(r5);
        if (r2 == 0) goto L_0x003b;
    L_0x0036:
        r3 = 1;
        r3 = r2.a(r3);
    L_0x003b:
        r5 = r3.isEmpty();
        if (r5 == 0) goto L_0x0046;
    L_0x0041:
        r0 = r17;
        r3.add(r0);
    L_0x0046:
        r6 = 0;
        r0 = r16;
        r0.v = r6;
        r0 = r16;
        r5 = r0.s;
        r10 = com.xiaomi.channel.commonutils.network.d.k(r5);
        r11 = new java.lang.StringBuilder;
        r11.<init>();
        r12 = r3.iterator();
    L_0x005d:
        r3 = r12.hasNext();
        if (r3 == 0) goto L_0x0248;
    L_0x0063:
        r3 = r12.next();
        r3 = (java.lang.String) r3;
        r14 = java.lang.System.currentTimeMillis();
        r0 = r16;
        r5 = r0.b;
        r5 = r5 + 1;
        r0 = r16;
        r0.b = r5;
        r5 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0114, l -> 0x0185, Throwable -> 0x01f4 }
        r5.<init>();	 Catch:{ IOException -> 0x0114, l -> 0x0185, Throwable -> 0x01f4 }
        r6 = "begin to connect to ";
        r5 = r5.append(r6);	 Catch:{ IOException -> 0x0114, l -> 0x0185, Throwable -> 0x01f4 }
        r5 = r5.append(r3);	 Catch:{ IOException -> 0x0114, l -> 0x0185, Throwable -> 0x01f4 }
        r5 = r5.toString();	 Catch:{ IOException -> 0x0114, l -> 0x0185, Throwable -> 0x01f4 }
        com.xiaomi.channel.commonutils.logger.b.a(r5);	 Catch:{ IOException -> 0x0114, l -> 0x0185, Throwable -> 0x01f4 }
        r5 = r16.u();	 Catch:{ IOException -> 0x0114, l -> 0x0185, Throwable -> 0x01f4 }
        r0 = r16;
        r0.q = r5;	 Catch:{ IOException -> 0x0114, l -> 0x0185, Throwable -> 0x01f4 }
        r0 = r18;
        r5 = com.xiaomi.network.Host.b(r3, r0);	 Catch:{ IOException -> 0x0114, l -> 0x0185, Throwable -> 0x01f4 }
        r0 = r16;
        r6 = r0.q;	 Catch:{ IOException -> 0x0114, l -> 0x0185, Throwable -> 0x01f4 }
        r7 = 8000; // 0x1f40 float:1.121E-41 double:3.9525E-320;
        r6.connect(r5, r7);	 Catch:{ IOException -> 0x0114, l -> 0x0185, Throwable -> 0x01f4 }
        r5 = "tcp connected";
        com.xiaomi.channel.commonutils.logger.b.a(r5);	 Catch:{ IOException -> 0x0114, l -> 0x0185, Throwable -> 0x01f4 }
        r0 = r16;
        r5 = r0.q;	 Catch:{ IOException -> 0x0114, l -> 0x0185, Throwable -> 0x01f4 }
        r6 = 1;
        r5.setTcpNoDelay(r6);	 Catch:{ IOException -> 0x0114, l -> 0x0185, Throwable -> 0x01f4 }
        r0 = r16;
        r0.w = r3;	 Catch:{ IOException -> 0x0114, l -> 0x0185, Throwable -> 0x01f4 }
        r16.c();	 Catch:{ IOException -> 0x0114, l -> 0x0185, Throwable -> 0x01f4 }
        r9 = 1;
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ IOException -> 0x0242, l -> 0x023f, Throwable -> 0x023b }
        r4 = r4 - r14;
        r0 = r16;
        r0.c = r4;	 Catch:{ IOException -> 0x0242, l -> 0x023f, Throwable -> 0x023b }
        r0 = r16;
        r0.k = r10;	 Catch:{ IOException -> 0x0242, l -> 0x023f, Throwable -> 0x023b }
        if (r2 == 0) goto L_0x00d3;
    L_0x00ca:
        r0 = r16;
        r4 = r0.c;	 Catch:{ IOException -> 0x0242, l -> 0x023f, Throwable -> 0x023b }
        r6 = 0;
        r2.b(r3, r4, r6);	 Catch:{ IOException -> 0x0242, l -> 0x023f, Throwable -> 0x023b }
    L_0x00d3:
        r4 = android.os.SystemClock.elapsedRealtime();	 Catch:{ IOException -> 0x0242, l -> 0x023f, Throwable -> 0x023b }
        r0 = r16;
        r0.v = r4;	 Catch:{ IOException -> 0x0242, l -> 0x023f, Throwable -> 0x023b }
        r4 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0242, l -> 0x023f, Throwable -> 0x023b }
        r4.<init>();	 Catch:{ IOException -> 0x0242, l -> 0x023f, Throwable -> 0x023b }
        r5 = "connected to ";
        r4 = r4.append(r5);	 Catch:{ IOException -> 0x0242, l -> 0x023f, Throwable -> 0x023b }
        r4 = r4.append(r3);	 Catch:{ IOException -> 0x0242, l -> 0x023f, Throwable -> 0x023b }
        r5 = " in ";
        r4 = r4.append(r5);	 Catch:{ IOException -> 0x0242, l -> 0x023f, Throwable -> 0x023b }
        r0 = r16;
        r6 = r0.c;	 Catch:{ IOException -> 0x0242, l -> 0x023f, Throwable -> 0x023b }
        r4 = r4.append(r6);	 Catch:{ IOException -> 0x0242, l -> 0x023f, Throwable -> 0x023b }
        r4 = r4.toString();	 Catch:{ IOException -> 0x0242, l -> 0x023f, Throwable -> 0x023b }
        com.xiaomi.channel.commonutils.logger.b.a(r4);	 Catch:{ IOException -> 0x0242, l -> 0x023f, Throwable -> 0x023b }
    L_0x0101:
        r2 = com.xiaomi.network.HostManager.getInstance();
        r2.persist();
        if (r9 != 0) goto L_0x0238;
    L_0x010a:
        r2 = new com.xiaomi.smack.l;
        r3 = r11.toString();
        r2.<init>(r3);
        throw r2;
    L_0x0114:
        r8 = move-exception;
        r9 = r4;
    L_0x0116:
        if (r2 == 0) goto L_0x0122;
    L_0x0118:
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x0239 }
        r4 = r4 - r14;
        r6 = 0;
        r2.b(r3, r4, r6, r8);	 Catch:{ all -> 0x0239 }
    L_0x0122:
        r0 = r16;
        r0.p = r8;	 Catch:{ all -> 0x0239 }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0239 }
        r4.<init>();	 Catch:{ all -> 0x0239 }
        r5 = "SMACK: Could not connect to:";
        r4 = r4.append(r5);	 Catch:{ all -> 0x0239 }
        r4 = r4.append(r3);	 Catch:{ all -> 0x0239 }
        r4 = r4.toString();	 Catch:{ all -> 0x0239 }
        com.xiaomi.channel.commonutils.logger.b.d(r4);	 Catch:{ all -> 0x0239 }
        r4 = "SMACK: Could not connect to ";
        r4 = r11.append(r4);	 Catch:{ all -> 0x0239 }
        r4 = r4.append(r3);	 Catch:{ all -> 0x0239 }
        r5 = " port:";
        r4 = r4.append(r5);	 Catch:{ all -> 0x0239 }
        r0 = r18;
        r4 = r4.append(r0);	 Catch:{ all -> 0x0239 }
        r5 = " ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x0239 }
        r5 = r8.getMessage();	 Catch:{ all -> 0x0239 }
        r4 = r4.append(r5);	 Catch:{ all -> 0x0239 }
        r5 = "\n";
        r4.append(r5);	 Catch:{ all -> 0x0239 }
        if (r9 != 0) goto L_0x0181;
    L_0x016c:
        r0 = r16;
        r4 = r0.p;
        com.xiaomi.stats.h.a(r3, r4);
        r0 = r16;
        r3 = r0.s;
        r3 = com.xiaomi.channel.commonutils.network.d.k(r3);
        r3 = android.text.TextUtils.equals(r10, r3);
        if (r3 == 0) goto L_0x0101;
    L_0x0181:
        r3 = r9;
    L_0x0182:
        r4 = r3;
        goto L_0x005d;
    L_0x0185:
        r8 = move-exception;
        r9 = r4;
    L_0x0187:
        if (r2 == 0) goto L_0x0193;
    L_0x0189:
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x0239 }
        r4 = r4 - r14;
        r6 = 0;
        r2.b(r3, r4, r6, r8);	 Catch:{ all -> 0x0239 }
    L_0x0193:
        r0 = r16;
        r0.p = r8;	 Catch:{ all -> 0x0239 }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0239 }
        r4.<init>();	 Catch:{ all -> 0x0239 }
        r5 = "SMACK: Could not connect to:";
        r4 = r4.append(r5);	 Catch:{ all -> 0x0239 }
        r4 = r4.append(r3);	 Catch:{ all -> 0x0239 }
        r4 = r4.toString();	 Catch:{ all -> 0x0239 }
        com.xiaomi.channel.commonutils.logger.b.d(r4);	 Catch:{ all -> 0x0239 }
        r4 = "SMACK: Could not connect to ";
        r4 = r11.append(r4);	 Catch:{ all -> 0x0239 }
        r4 = r4.append(r3);	 Catch:{ all -> 0x0239 }
        r5 = " port:";
        r4 = r4.append(r5);	 Catch:{ all -> 0x0239 }
        r0 = r18;
        r4 = r4.append(r0);	 Catch:{ all -> 0x0239 }
        r5 = " ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x0239 }
        r5 = r8.getMessage();	 Catch:{ all -> 0x0239 }
        r4 = r4.append(r5);	 Catch:{ all -> 0x0239 }
        r5 = "\n";
        r4.append(r5);	 Catch:{ all -> 0x0239 }
        if (r9 != 0) goto L_0x0181;
    L_0x01dd:
        r0 = r16;
        r4 = r0.p;
        com.xiaomi.stats.h.a(r3, r4);
        r0 = r16;
        r3 = r0.s;
        r3 = com.xiaomi.channel.commonutils.network.d.k(r3);
        r3 = android.text.TextUtils.equals(r10, r3);
        if (r3 != 0) goto L_0x0181;
    L_0x01f2:
        goto L_0x0101;
    L_0x01f4:
        r5 = move-exception;
    L_0x01f5:
        r6 = new java.lang.Exception;	 Catch:{ all -> 0x021e }
        r7 = "abnormal exception";
        r6.<init>(r7, r5);	 Catch:{ all -> 0x021e }
        r0 = r16;
        r0.p = r6;	 Catch:{ all -> 0x021e }
        com.xiaomi.channel.commonutils.logger.b.a(r5);	 Catch:{ all -> 0x021e }
        if (r4 != 0) goto L_0x0245;
    L_0x0206:
        r0 = r16;
        r5 = r0.p;
        com.xiaomi.stats.h.a(r3, r5);
        r0 = r16;
        r3 = r0.s;
        r3 = com.xiaomi.channel.commonutils.network.d.k(r3);
        r3 = android.text.TextUtils.equals(r10, r3);
        if (r3 != 0) goto L_0x0245;
    L_0x021b:
        r9 = r4;
        goto L_0x0101;
    L_0x021e:
        r2 = move-exception;
        r9 = r4;
    L_0x0220:
        if (r9 != 0) goto L_0x0237;
    L_0x0222:
        r0 = r16;
        r4 = r0.p;
        com.xiaomi.stats.h.a(r3, r4);
        r0 = r16;
        r3 = r0.s;
        r3 = com.xiaomi.channel.commonutils.network.d.k(r3);
        r3 = android.text.TextUtils.equals(r10, r3);
        if (r3 == 0) goto L_0x0101;
    L_0x0237:
        throw r2;
    L_0x0238:
        return;
    L_0x0239:
        r2 = move-exception;
        goto L_0x0220;
    L_0x023b:
        r4 = move-exception;
        r5 = r4;
        r4 = r9;
        goto L_0x01f5;
    L_0x023f:
        r8 = move-exception;
        goto L_0x0187;
    L_0x0242:
        r8 = move-exception;
        goto L_0x0116;
    L_0x0245:
        r3 = r4;
        goto L_0x0182;
    L_0x0248:
        r9 = r4;
        goto L_0x0101;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smack.h.a(java.lang.String, int):void");
    }

    protected synchronized void a(int i, Exception exception) {
        if (n() != 2) {
            a(2, i, exception);
            this.j = "";
            try {
                this.q.close();
            } catch (Throwable th) {
            }
            this.t = 0;
            this.u = 0;
        }
    }

    protected void a(Exception exception) {
        if (SystemClock.elapsedRealtime() - this.v >= 300000) {
            this.x = 0;
        } else if (d.c(this.s)) {
            this.x++;
            if (this.x >= 2) {
                String e = e();
                b.a("max short conn time reached, sink down current host:" + e);
                a(e, 0, exception);
                this.x = 0;
            }
        }
    }

    protected void a(String str, long j, Exception exception) {
        Fallback fallbacksByHost = HostManager.getInstance().getFallbacksByHost(b.b(), false);
        if (fallbacksByHost != null) {
            fallbacksByHost.b(str, j, 0, exception);
            HostManager.getInstance().persist();
        }
    }

    protected abstract void a(boolean z);

    public void a(com.xiaomi.slim.b[] bVarArr) {
        throw new l("Don't support send Blob");
    }

    Fallback b(String str) {
        Fallback fallbacksByHost = HostManager.getInstance().getFallbacksByHost(str, false);
        if (!fallbacksByHost.b()) {
            e.a(new k(this, str));
        }
        this.f = 0;
        try {
            byte[] address = InetAddress.getByName(fallbacksByHost.f).getAddress();
            this.f = address[0] & 255;
            this.f |= (address[1] << 8) & 65280;
            this.f |= (address[2] << 16) & 16711680;
            this.f = ((address[3] << 24) & ViewCompat.MEASURED_STATE_MASK) | this.f;
        } catch (UnknownHostException e) {
        }
        return fallbacksByHost;
    }

    public void b(int i, Exception exception) {
        a(i, exception);
        if ((exception != null || i == 18) && this.v != 0) {
            a(exception);
        }
    }

    public void b(boolean z) {
        long currentTimeMillis = System.currentTimeMillis();
        a(z);
        if (!z) {
            this.s.a(new i(this, 13, currentTimeMillis), 10000);
        }
    }

    protected synchronized void c() {
    }

    public void c(int i, Exception exception) {
        this.s.a(new j(this, 2, i, exception));
    }

    public String e() {
        return this.w;
    }

    public String s() {
        return this.j;
    }

    public synchronized void t() {
        try {
            if (l() || k()) {
                b.a("WARNING: current xmpp has connected");
            } else {
                a(0, 0, null);
                a(this.m);
            }
        } catch (Throwable e) {
            throw new l(e);
        }
    }

    public Socket u() {
        return new Socket();
    }

    public void v() {
        this.t = SystemClock.elapsedRealtime();
    }

    public void w() {
        this.u = SystemClock.elapsedRealtime();
    }
}
