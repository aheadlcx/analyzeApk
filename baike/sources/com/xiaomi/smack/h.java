package com.xiaomi.smack;

import android.os.SystemClock;
import android.support.v4.view.MotionEventCompat;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.channel.commonutils.network.d;
import com.xiaomi.network.Fallback;
import com.xiaomi.network.HostManager;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.smack.util.e;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import qsbk.app.utils.ListViewHelper;

public abstract class h extends a {
    protected Exception o = null;
    protected Socket p;
    String q = null;
    protected XMPushService r;
    protected volatile long s = 0;
    protected volatile long t = 0;
    protected volatile long u = 0;
    private String v;
    private int w;

    public h(XMPushService xMPushService, b bVar) {
        super(xMPushService, bVar);
        this.r = xMPushService;
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
        r0.o = r2;
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
        if (r2 == 0) goto L_0x003a;
    L_0x0035:
        r3 = 1;
        r3 = r2.a(r3);
    L_0x003a:
        r5 = r3.isEmpty();
        if (r5 == 0) goto L_0x0045;
    L_0x0040:
        r0 = r17;
        r3.add(r0);
    L_0x0045:
        r6 = 0;
        r0 = r16;
        r0.u = r6;
        r0 = r16;
        r5 = r0.r;
        r10 = com.xiaomi.channel.commonutils.network.d.k(r5);
        r11 = new java.lang.StringBuilder;
        r11.<init>();
        r12 = r3.iterator();
    L_0x005c:
        r3 = r12.hasNext();
        if (r3 == 0) goto L_0x0238;
    L_0x0062:
        r3 = r12.next();
        r3 = (java.lang.String) r3;
        r14 = java.lang.System.currentTimeMillis();
        r0 = r16;
        r5 = r0.b;
        r5 = r5 + 1;
        r0 = r16;
        r0.b = r5;
        r5 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x010f, l -> 0x017b, Throwable -> 0x01e5 }
        r5.<init>();	 Catch:{ IOException -> 0x010f, l -> 0x017b, Throwable -> 0x01e5 }
        r6 = "begin to connect to ";
        r5 = r5.append(r6);	 Catch:{ IOException -> 0x010f, l -> 0x017b, Throwable -> 0x01e5 }
        r5 = r5.append(r3);	 Catch:{ IOException -> 0x010f, l -> 0x017b, Throwable -> 0x01e5 }
        r5 = r5.toString();	 Catch:{ IOException -> 0x010f, l -> 0x017b, Throwable -> 0x01e5 }
        com.xiaomi.channel.commonutils.logger.b.a(r5);	 Catch:{ IOException -> 0x010f, l -> 0x017b, Throwable -> 0x01e5 }
        r5 = r16.s();	 Catch:{ IOException -> 0x010f, l -> 0x017b, Throwable -> 0x01e5 }
        r0 = r16;
        r0.p = r5;	 Catch:{ IOException -> 0x010f, l -> 0x017b, Throwable -> 0x01e5 }
        r0 = r18;
        r5 = com.xiaomi.network.Host.b(r3, r0);	 Catch:{ IOException -> 0x010f, l -> 0x017b, Throwable -> 0x01e5 }
        r0 = r16;
        r6 = r0.p;	 Catch:{ IOException -> 0x010f, l -> 0x017b, Throwable -> 0x01e5 }
        r7 = 8000; // 0x1f40 float:1.121E-41 double:3.9525E-320;
        r6.connect(r5, r7);	 Catch:{ IOException -> 0x010f, l -> 0x017b, Throwable -> 0x01e5 }
        r5 = "tcp connected";
        com.xiaomi.channel.commonutils.logger.b.a(r5);	 Catch:{ IOException -> 0x010f, l -> 0x017b, Throwable -> 0x01e5 }
        r0 = r16;
        r5 = r0.p;	 Catch:{ IOException -> 0x010f, l -> 0x017b, Throwable -> 0x01e5 }
        r6 = 1;
        r5.setTcpNoDelay(r6);	 Catch:{ IOException -> 0x010f, l -> 0x017b, Throwable -> 0x01e5 }
        r0 = r16;
        r0.v = r3;	 Catch:{ IOException -> 0x010f, l -> 0x017b, Throwable -> 0x01e5 }
        r16.b();	 Catch:{ IOException -> 0x010f, l -> 0x017b, Throwable -> 0x01e5 }
        r9 = 1;
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ IOException -> 0x0232, l -> 0x022f, Throwable -> 0x022b }
        r4 = r4 - r14;
        r0 = r16;
        r0.c = r4;	 Catch:{ IOException -> 0x0232, l -> 0x022f, Throwable -> 0x022b }
        r0 = r16;
        r0.k = r10;	 Catch:{ IOException -> 0x0232, l -> 0x022f, Throwable -> 0x022b }
        if (r2 == 0) goto L_0x00d0;
    L_0x00c7:
        r0 = r16;
        r4 = r0.c;	 Catch:{ IOException -> 0x0232, l -> 0x022f, Throwable -> 0x022b }
        r6 = 0;
        r2.b(r3, r4, r6);	 Catch:{ IOException -> 0x0232, l -> 0x022f, Throwable -> 0x022b }
    L_0x00d0:
        r4 = android.os.SystemClock.elapsedRealtime();	 Catch:{ IOException -> 0x0232, l -> 0x022f, Throwable -> 0x022b }
        r0 = r16;
        r0.u = r4;	 Catch:{ IOException -> 0x0232, l -> 0x022f, Throwable -> 0x022b }
        r4 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0232, l -> 0x022f, Throwable -> 0x022b }
        r4.<init>();	 Catch:{ IOException -> 0x0232, l -> 0x022f, Throwable -> 0x022b }
        r5 = "connected to ";
        r4 = r4.append(r5);	 Catch:{ IOException -> 0x0232, l -> 0x022f, Throwable -> 0x022b }
        r4 = r4.append(r3);	 Catch:{ IOException -> 0x0232, l -> 0x022f, Throwable -> 0x022b }
        r5 = " in ";
        r4 = r4.append(r5);	 Catch:{ IOException -> 0x0232, l -> 0x022f, Throwable -> 0x022b }
        r0 = r16;
        r6 = r0.c;	 Catch:{ IOException -> 0x0232, l -> 0x022f, Throwable -> 0x022b }
        r4 = r4.append(r6);	 Catch:{ IOException -> 0x0232, l -> 0x022f, Throwable -> 0x022b }
        r4 = r4.toString();	 Catch:{ IOException -> 0x0232, l -> 0x022f, Throwable -> 0x022b }
        com.xiaomi.channel.commonutils.logger.b.a(r4);	 Catch:{ IOException -> 0x0232, l -> 0x022f, Throwable -> 0x022b }
    L_0x00fc:
        r2 = com.xiaomi.network.HostManager.getInstance();
        r2.persist();
        if (r9 != 0) goto L_0x0228;
    L_0x0105:
        r2 = new com.xiaomi.smack.l;
        r3 = r11.toString();
        r2.<init>(r3);
        throw r2;
    L_0x010f:
        r8 = move-exception;
        r9 = r4;
    L_0x0111:
        if (r2 == 0) goto L_0x011d;
    L_0x0113:
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x0229 }
        r4 = r4 - r14;
        r6 = 0;
        r2.b(r3, r4, r6, r8);	 Catch:{ all -> 0x0229 }
    L_0x011d:
        r0 = r16;
        r0.o = r8;	 Catch:{ all -> 0x0229 }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0229 }
        r4.<init>();	 Catch:{ all -> 0x0229 }
        r5 = "SMACK: Could not connect to:";
        r4 = r4.append(r5);	 Catch:{ all -> 0x0229 }
        r4 = r4.append(r3);	 Catch:{ all -> 0x0229 }
        r4 = r4.toString();	 Catch:{ all -> 0x0229 }
        com.xiaomi.channel.commonutils.logger.b.d(r4);	 Catch:{ all -> 0x0229 }
        r4 = "SMACK: Could not connect to ";
        r4 = r11.append(r4);	 Catch:{ all -> 0x0229 }
        r4 = r4.append(r3);	 Catch:{ all -> 0x0229 }
        r5 = " port:";
        r4 = r4.append(r5);	 Catch:{ all -> 0x0229 }
        r0 = r18;
        r4 = r4.append(r0);	 Catch:{ all -> 0x0229 }
        r5 = " ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x0229 }
        r5 = r8.getMessage();	 Catch:{ all -> 0x0229 }
        r4 = r4.append(r5);	 Catch:{ all -> 0x0229 }
        r5 = "\n";
        r4.append(r5);	 Catch:{ all -> 0x0229 }
        if (r9 != 0) goto L_0x0177;
    L_0x0162:
        r0 = r16;
        r4 = r0.o;
        com.xiaomi.stats.h.a(r3, r4);
        r0 = r16;
        r3 = r0.r;
        r3 = com.xiaomi.channel.commonutils.network.d.k(r3);
        r3 = android.text.TextUtils.equals(r10, r3);
        if (r3 == 0) goto L_0x00fc;
    L_0x0177:
        r3 = r9;
    L_0x0178:
        r4 = r3;
        goto L_0x005c;
    L_0x017b:
        r8 = move-exception;
        r9 = r4;
    L_0x017d:
        if (r2 == 0) goto L_0x0189;
    L_0x017f:
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x0229 }
        r4 = r4 - r14;
        r6 = 0;
        r2.b(r3, r4, r6, r8);	 Catch:{ all -> 0x0229 }
    L_0x0189:
        r0 = r16;
        r0.o = r8;	 Catch:{ all -> 0x0229 }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0229 }
        r4.<init>();	 Catch:{ all -> 0x0229 }
        r5 = "SMACK: Could not connect to:";
        r4 = r4.append(r5);	 Catch:{ all -> 0x0229 }
        r4 = r4.append(r3);	 Catch:{ all -> 0x0229 }
        r4 = r4.toString();	 Catch:{ all -> 0x0229 }
        com.xiaomi.channel.commonutils.logger.b.d(r4);	 Catch:{ all -> 0x0229 }
        r4 = "SMACK: Could not connect to ";
        r4 = r11.append(r4);	 Catch:{ all -> 0x0229 }
        r4 = r4.append(r3);	 Catch:{ all -> 0x0229 }
        r5 = " port:";
        r4 = r4.append(r5);	 Catch:{ all -> 0x0229 }
        r0 = r18;
        r4 = r4.append(r0);	 Catch:{ all -> 0x0229 }
        r5 = " ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x0229 }
        r5 = r8.getMessage();	 Catch:{ all -> 0x0229 }
        r4 = r4.append(r5);	 Catch:{ all -> 0x0229 }
        r5 = "\n";
        r4.append(r5);	 Catch:{ all -> 0x0229 }
        if (r9 != 0) goto L_0x0177;
    L_0x01ce:
        r0 = r16;
        r4 = r0.o;
        com.xiaomi.stats.h.a(r3, r4);
        r0 = r16;
        r3 = r0.r;
        r3 = com.xiaomi.channel.commonutils.network.d.k(r3);
        r3 = android.text.TextUtils.equals(r10, r3);
        if (r3 != 0) goto L_0x0177;
    L_0x01e3:
        goto L_0x00fc;
    L_0x01e5:
        r5 = move-exception;
    L_0x01e6:
        r6 = new java.lang.Exception;	 Catch:{ all -> 0x020e }
        r7 = "abnormal exception";
        r6.<init>(r7, r5);	 Catch:{ all -> 0x020e }
        r0 = r16;
        r0.o = r6;	 Catch:{ all -> 0x020e }
        com.xiaomi.channel.commonutils.logger.b.a(r5);	 Catch:{ all -> 0x020e }
        if (r4 != 0) goto L_0x0235;
    L_0x01f6:
        r0 = r16;
        r5 = r0.o;
        com.xiaomi.stats.h.a(r3, r5);
        r0 = r16;
        r3 = r0.r;
        r3 = com.xiaomi.channel.commonutils.network.d.k(r3);
        r3 = android.text.TextUtils.equals(r10, r3);
        if (r3 != 0) goto L_0x0235;
    L_0x020b:
        r9 = r4;
        goto L_0x00fc;
    L_0x020e:
        r2 = move-exception;
        r9 = r4;
    L_0x0210:
        if (r9 != 0) goto L_0x0227;
    L_0x0212:
        r0 = r16;
        r4 = r0.o;
        com.xiaomi.stats.h.a(r3, r4);
        r0 = r16;
        r3 = r0.r;
        r3 = com.xiaomi.channel.commonutils.network.d.k(r3);
        r3 = android.text.TextUtils.equals(r10, r3);
        if (r3 == 0) goto L_0x00fc;
    L_0x0227:
        throw r2;
    L_0x0228:
        return;
    L_0x0229:
        r2 = move-exception;
        goto L_0x0210;
    L_0x022b:
        r4 = move-exception;
        r5 = r4;
        r4 = r9;
        goto L_0x01e6;
    L_0x022f:
        r8 = move-exception;
        goto L_0x017d;
    L_0x0232:
        r8 = move-exception;
        goto L_0x0111;
    L_0x0235:
        r3 = r4;
        goto L_0x0178;
    L_0x0238:
        r9 = r4;
        goto L_0x00fc;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smack.h.a(java.lang.String, int):void");
    }

    protected synchronized void a(int i, Exception exception) {
        if (m() != 2) {
            a(2, i, exception);
            this.j = "";
            try {
                this.p.close();
            } catch (Throwable th) {
            }
            this.s = 0;
            this.t = 0;
        }
    }

    protected void a(Exception exception) {
        if (SystemClock.elapsedRealtime() - this.u >= ListViewHelper.DEFAULT_TIPS_TO_REFRESH_INTERVAL) {
            this.w = 0;
        } else if (d.d(this.r)) {
            this.w++;
            if (this.w >= 2) {
                String d = d();
                b.a("max short conn time reached, sink down current host:" + d);
                a(d, 0, exception);
                this.w = 0;
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

    public void a(com.xiaomi.smack.packet.d[] dVarArr) {
        for (com.xiaomi.smack.packet.d a : dVarArr) {
            a(a);
        }
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
            this.f |= (address[1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK;
            this.f |= (address[2] << 16) & 16711680;
            this.f = ((address[3] << 24) & -16777216) | this.f;
        } catch (UnknownHostException e) {
        }
        return fallbacksByHost;
    }

    protected synchronized void b() {
    }

    public void b(int i, Exception exception) {
        a(i, exception);
        if ((exception != null || i == 18) && this.u != 0) {
            a(exception);
        }
    }

    public void b(boolean z) {
        long currentTimeMillis = System.currentTimeMillis();
        a(z);
        if (!z) {
            this.r.a(new i(this, 13, currentTimeMillis), 10000);
        }
    }

    public void c(int i, Exception exception) {
        this.r.a(new j(this, 2, i, exception));
    }

    public String d() {
        return this.v;
    }

    public String q() {
        return this.j;
    }

    public synchronized void r() {
        try {
            if (k() || j()) {
                b.a("WARNING: current xmpp has connected");
            } else {
                a(0, 0, null);
                a(this.m);
            }
        } catch (Throwable e) {
            throw new l(e);
        }
    }

    public Socket s() {
        return new Socket();
    }

    public void t() {
        this.s = SystemClock.elapsedRealtime();
    }

    public void u() {
        this.t = SystemClock.elapsedRealtime();
    }
}
