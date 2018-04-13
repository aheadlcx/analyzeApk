package com.liulishuo.filedownloader.download;

import android.os.SystemClock;
import com.liulishuo.filedownloader.a.b;
import com.liulishuo.filedownloader.g.d;
import java.io.IOException;

public class f {
    long a;
    private final g b;
    private final int c;
    private final int d;
    private final d e;
    private final b f;
    private final boolean g;
    private final long h;
    private final long i;
    private final long j;
    private final String k;
    private com.liulishuo.filedownloader.f.a l;
    private volatile boolean m;
    private final com.liulishuo.filedownloader.b.a n;
    private volatile long o;
    private volatile long p;

    public static class a {
        d a;
        b b;
        b c;
        g d;
        String e;
        Boolean f;
        Integer g;
        Integer h;

        public a a(b bVar) {
            this.b = bVar;
            return this;
        }

        public a a(b bVar) {
            this.c = bVar;
            return this;
        }

        public a a(g gVar) {
            this.d = gVar;
            return this;
        }

        public a a(String str) {
            this.e = str;
            return this;
        }

        public a a(boolean z) {
            this.f = Boolean.valueOf(z);
            return this;
        }

        public a a(d dVar) {
            this.a = dVar;
            return this;
        }

        public a a(int i) {
            this.g = Integer.valueOf(i);
            return this;
        }

        public a b(int i) {
            this.h = Integer.valueOf(i);
            return this;
        }

        public f a() throws IllegalArgumentException {
            if (this.f != null && this.b != null && this.c != null && this.d != null && this.e != null && this.h != null && this.g != null) {
                return new f(this.b, this.c, this.a, this.h.intValue(), this.g.intValue(), this.f.booleanValue(), this.d, this.e);
            }
            throw new IllegalArgumentException();
        }
    }

    public void a() {
        this.m = true;
    }

    private f(b bVar, b bVar2, d dVar, int i, int i2, boolean z, g gVar, String str) {
        this.o = 0;
        this.p = 0;
        this.b = gVar;
        this.k = str;
        this.f = bVar;
        this.g = z;
        this.e = dVar;
        this.d = i2;
        this.c = i;
        this.n = c.a().c();
        this.h = bVar2.a;
        this.i = bVar2.c;
        this.a = bVar2.b;
        this.j = bVar2.d;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b() throws java.io.IOException, java.lang.IllegalAccessException, java.lang.IllegalArgumentException, com.liulishuo.filedownloader.exception.FileDownloadGiveUpRetryException {
        /*
        r12 = this;
        r0 = r12.m;
        if (r0 == 0) goto L_0x0005;
    L_0x0004:
        return;
    L_0x0005:
        r0 = r12.d;
        r1 = r12.f;
        r0 = com.liulishuo.filedownloader.g.f.c(r0, r1);
        r2 = -1;
        r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r2 != 0) goto L_0x024e;
    L_0x0013:
        r0 = r12.f;
        r0 = com.liulishuo.filedownloader.g.f.c(r0);
        r4 = r0;
    L_0x001a:
        r0 = 0;
        r0 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1));
        if (r0 != 0) goto L_0x0042;
    L_0x0020:
        r0 = new com.liulishuo.filedownloader.exception.FileDownloadGiveUpRetryException;
        r1 = "there isn't any content need to download on %d-%d with the content-length is 0";
        r2 = 2;
        r2 = new java.lang.Object[r2];
        r3 = 0;
        r4 = r12.c;
        r4 = java.lang.Integer.valueOf(r4);
        r2[r3] = r4;
        r3 = 1;
        r4 = r12.d;
        r4 = java.lang.Integer.valueOf(r4);
        r2[r3] = r4;
        r1 = com.liulishuo.filedownloader.g.f.a(r1, r2);
        r0.<init>(r1);
        throw r0;
    L_0x0042:
        r0 = r12.j;
        r2 = 0;
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 <= 0) goto L_0x00bd;
    L_0x004a:
        r0 = r12.j;
        r0 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1));
        if (r0 == 0) goto L_0x00bd;
    L_0x0050:
        r0 = r12.i;
        r2 = -1;
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 != 0) goto L_0x00a0;
    L_0x0058:
        r0 = "range[%d-)";
        r1 = 1;
        r1 = new java.lang.Object[r1];
        r2 = 0;
        r6 = r12.a;
        r3 = java.lang.Long.valueOf(r6);
        r1[r2] = r3;
        r0 = com.liulishuo.filedownloader.g.f.a(r0, r1);
    L_0x006b:
        r1 = new com.liulishuo.filedownloader.exception.FileDownloadGiveUpRetryException;
        r2 = "require %s with contentLength(%d), but the backend response contentLength is %d on downloadId[%d]-connectionIndex[%d], please ask your backend dev to fix such problem.";
        r3 = 5;
        r3 = new java.lang.Object[r3];
        r6 = 0;
        r3[r6] = r0;
        r0 = 1;
        r6 = r12.j;
        r6 = java.lang.Long.valueOf(r6);
        r3[r0] = r6;
        r0 = 2;
        r4 = java.lang.Long.valueOf(r4);
        r3[r0] = r4;
        r0 = 3;
        r4 = r12.c;
        r4 = java.lang.Integer.valueOf(r4);
        r3[r0] = r4;
        r0 = 4;
        r4 = r12.d;
        r4 = java.lang.Integer.valueOf(r4);
        r3[r0] = r4;
        r0 = com.liulishuo.filedownloader.g.f.a(r2, r3);
        r1.<init>(r0);
        throw r1;
    L_0x00a0:
        r0 = "range[%d-%d)";
        r1 = 2;
        r1 = new java.lang.Object[r1];
        r2 = 0;
        r6 = r12.a;
        r3 = java.lang.Long.valueOf(r6);
        r1[r2] = r3;
        r2 = 1;
        r6 = r12.i;
        r3 = java.lang.Long.valueOf(r6);
        r1[r2] = r3;
        r0 = com.liulishuo.filedownloader.g.f.a(r0, r1);
        goto L_0x006b;
    L_0x00bd:
        r6 = r12.a;
        r2 = 0;
        r1 = 0;
        r0 = com.liulishuo.filedownloader.download.c.a();	 Catch:{ all -> 0x00d8 }
        r0 = r0.e();	 Catch:{ all -> 0x00d8 }
        r3 = r12.e;	 Catch:{ all -> 0x00d8 }
        if (r3 == 0) goto L_0x00e9;
    L_0x00cd:
        if (r0 != 0) goto L_0x00e9;
    L_0x00cf:
        r0 = new java.lang.IllegalAccessException;	 Catch:{ all -> 0x00d8 }
        r3 = "can't using multi-download when the output stream can't support seek";
        r0.<init>(r3);	 Catch:{ all -> 0x00d8 }
        throw r0;	 Catch:{ all -> 0x00d8 }
    L_0x00d8:
        r0 = move-exception;
        if (r2 == 0) goto L_0x00de;
    L_0x00db:
        r2.close();	 Catch:{ IOException -> 0x0229 }
    L_0x00de:
        if (r1 == 0) goto L_0x00e3;
    L_0x00e0:
        r12.d();	 Catch:{ all -> 0x0235 }
    L_0x00e3:
        if (r1 == 0) goto L_0x00e8;
    L_0x00e5:
        r1.b();	 Catch:{ IOException -> 0x022f }
    L_0x00e8:
        throw r0;
    L_0x00e9:
        r3 = r12.k;	 Catch:{ all -> 0x00d8 }
        r1 = com.liulishuo.filedownloader.g.f.n(r3);	 Catch:{ all -> 0x00d8 }
        r12.l = r1;	 Catch:{ all -> 0x00d8 }
        if (r0 == 0) goto L_0x00f8;
    L_0x00f3:
        r8 = r12.a;	 Catch:{ all -> 0x00d8 }
        r1.a(r8);	 Catch:{ all -> 0x00d8 }
    L_0x00f8:
        r0 = com.liulishuo.filedownloader.g.d.a;	 Catch:{ all -> 0x00d8 }
        if (r0 == 0) goto L_0x0129;
    L_0x00fc:
        r0 = "start fetch(%d): range [%d, %d), seek to[%d]";
        r3 = 4;
        r3 = new java.lang.Object[r3];	 Catch:{ all -> 0x00d8 }
        r8 = 0;
        r9 = r12.d;	 Catch:{ all -> 0x00d8 }
        r9 = java.lang.Integer.valueOf(r9);	 Catch:{ all -> 0x00d8 }
        r3[r8] = r9;	 Catch:{ all -> 0x00d8 }
        r8 = 1;
        r10 = r12.h;	 Catch:{ all -> 0x00d8 }
        r9 = java.lang.Long.valueOf(r10);	 Catch:{ all -> 0x00d8 }
        r3[r8] = r9;	 Catch:{ all -> 0x00d8 }
        r8 = 2;
        r10 = r12.i;	 Catch:{ all -> 0x00d8 }
        r9 = java.lang.Long.valueOf(r10);	 Catch:{ all -> 0x00d8 }
        r3[r8] = r9;	 Catch:{ all -> 0x00d8 }
        r8 = 3;
        r10 = r12.a;	 Catch:{ all -> 0x00d8 }
        r9 = java.lang.Long.valueOf(r10);	 Catch:{ all -> 0x00d8 }
        r3[r8] = r9;	 Catch:{ all -> 0x00d8 }
        com.liulishuo.filedownloader.g.d.c(r12, r0, r3);	 Catch:{ all -> 0x00d8 }
    L_0x0129:
        r0 = r12.f;	 Catch:{ all -> 0x00d8 }
        r2 = r0.a();	 Catch:{ all -> 0x00d8 }
        r0 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
        r0 = new byte[r0];	 Catch:{ all -> 0x00d8 }
        r3 = r12.m;	 Catch:{ all -> 0x00d8 }
        if (r3 == 0) goto L_0x015f;
    L_0x0137:
        if (r2 == 0) goto L_0x013c;
    L_0x0139:
        r2.close();	 Catch:{ IOException -> 0x014e }
    L_0x013c:
        if (r1 == 0) goto L_0x0141;
    L_0x013e:
        r12.d();	 Catch:{ all -> 0x0153 }
    L_0x0141:
        if (r1 == 0) goto L_0x0004;
    L_0x0143:
        r1.b();	 Catch:{ IOException -> 0x0148 }
        goto L_0x0004;
    L_0x0148:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0004;
    L_0x014e:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x013c;
    L_0x0153:
        r0 = move-exception;
        if (r1 == 0) goto L_0x0159;
    L_0x0156:
        r1.b();	 Catch:{ IOException -> 0x015a }
    L_0x0159:
        throw r0;
    L_0x015a:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0159;
    L_0x015f:
        r3 = r2.read(r0);	 Catch:{ all -> 0x00d8 }
        r8 = -1;
        if (r3 != r8) goto L_0x01c2;
    L_0x0166:
        if (r2 == 0) goto L_0x016b;
    L_0x0168:
        r2.close();	 Catch:{ IOException -> 0x0211 }
    L_0x016b:
        if (r1 == 0) goto L_0x0170;
    L_0x016d:
        r12.d();	 Catch:{ all -> 0x021d }
    L_0x0170:
        if (r1 == 0) goto L_0x0175;
    L_0x0172:
        r1.b();	 Catch:{ IOException -> 0x0217 }
    L_0x0175:
        r0 = r12.a;
        r0 = r0 - r6;
        r2 = -1;
        r2 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1));
        if (r2 == 0) goto L_0x0241;
    L_0x017e:
        r2 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1));
        if (r2 == 0) goto L_0x0241;
    L_0x0182:
        r2 = new com.liulishuo.filedownloader.exception.FileDownloadGiveUpRetryException;
        r3 = "fetched length[%d] != content length[%d], range[%d, %d) offset[%d] fetch begin offset[%d]";
        r8 = 6;
        r8 = new java.lang.Object[r8];
        r9 = 0;
        r0 = java.lang.Long.valueOf(r0);
        r8[r9] = r0;
        r0 = 1;
        r1 = java.lang.Long.valueOf(r4);
        r8[r0] = r1;
        r0 = 2;
        r4 = r12.h;
        r1 = java.lang.Long.valueOf(r4);
        r8[r0] = r1;
        r0 = 3;
        r4 = r12.i;
        r1 = java.lang.Long.valueOf(r4);
        r8[r0] = r1;
        r0 = 4;
        r4 = r12.a;
        r1 = java.lang.Long.valueOf(r4);
        r8[r0] = r1;
        r0 = 5;
        r1 = java.lang.Long.valueOf(r6);
        r8[r0] = r1;
        r0 = com.liulishuo.filedownloader.g.f.a(r3, r8);
        r2.<init>(r0);
        throw r2;
    L_0x01c2:
        r8 = 0;
        r1.a(r0, r8, r3);	 Catch:{ all -> 0x00d8 }
        r8 = r12.a;	 Catch:{ all -> 0x00d8 }
        r10 = (long) r3;	 Catch:{ all -> 0x00d8 }
        r8 = r8 + r10;
        r12.a = r8;	 Catch:{ all -> 0x00d8 }
        r8 = r12.b;	 Catch:{ all -> 0x00d8 }
        r10 = (long) r3;	 Catch:{ all -> 0x00d8 }
        r8.a(r10);	 Catch:{ all -> 0x00d8 }
        r12.c();	 Catch:{ all -> 0x00d8 }
        r3 = r12.m;	 Catch:{ all -> 0x00d8 }
        if (r3 == 0) goto L_0x0201;
    L_0x01d9:
        if (r2 == 0) goto L_0x01de;
    L_0x01db:
        r2.close();	 Catch:{ IOException -> 0x01f0 }
    L_0x01de:
        if (r1 == 0) goto L_0x01e3;
    L_0x01e0:
        r12.d();	 Catch:{ all -> 0x01f5 }
    L_0x01e3:
        if (r1 == 0) goto L_0x0004;
    L_0x01e5:
        r1.b();	 Catch:{ IOException -> 0x01ea }
        goto L_0x0004;
    L_0x01ea:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0004;
    L_0x01f0:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x01de;
    L_0x01f5:
        r0 = move-exception;
        if (r1 == 0) goto L_0x01fb;
    L_0x01f8:
        r1.b();	 Catch:{ IOException -> 0x01fc }
    L_0x01fb:
        throw r0;
    L_0x01fc:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x01fb;
    L_0x0201:
        r3 = r12.g;	 Catch:{ all -> 0x00d8 }
        if (r3 == 0) goto L_0x015f;
    L_0x0205:
        r3 = com.liulishuo.filedownloader.g.f.d();	 Catch:{ all -> 0x00d8 }
        if (r3 == 0) goto L_0x015f;
    L_0x020b:
        r0 = new com.liulishuo.filedownloader.exception.FileDownloadNetworkPolicyException;	 Catch:{ all -> 0x00d8 }
        r0.<init>();	 Catch:{ all -> 0x00d8 }
        throw r0;	 Catch:{ all -> 0x00d8 }
    L_0x0211:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x016b;
    L_0x0217:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0175;
    L_0x021d:
        r0 = move-exception;
        if (r1 == 0) goto L_0x0223;
    L_0x0220:
        r1.b();	 Catch:{ IOException -> 0x0224 }
    L_0x0223:
        throw r0;
    L_0x0224:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0223;
    L_0x0229:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x00de;
    L_0x022f:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x00e8;
    L_0x0235:
        r0 = move-exception;
        if (r1 == 0) goto L_0x023b;
    L_0x0238:
        r1.b();	 Catch:{ IOException -> 0x023c }
    L_0x023b:
        throw r0;
    L_0x023c:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x023b;
    L_0x0241:
        r0 = r12.b;
        r1 = r12.e;
        r2 = r12.h;
        r4 = r12.i;
        r0.a(r1, r2, r4);
        goto L_0x0004;
    L_0x024e:
        r4 = r0;
        goto L_0x001a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liulishuo.filedownloader.download.f.b():void");
    }

    private void c() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (com.liulishuo.filedownloader.g.f.a(this.a - this.o, elapsedRealtime - this.p)) {
            d();
            this.o = this.a;
            this.p = elapsedRealtime;
        }
    }

    private void d() {
        int i;
        long uptimeMillis = SystemClock.uptimeMillis();
        try {
            this.l.a();
            i = 1;
        } catch (IOException e) {
            if (d.a) {
                d.c(this, "Because of the system cannot guarantee that all the buffers have been synchronized with physical media, or write to filefailed, we just not flushAndSync process to database too %s", e);
            }
            i = 0;
        }
        if (i != 0) {
            if (this.d >= 0) {
                i = 1;
            } else {
                i = 0;
            }
            if (i != 0) {
                this.n.a(this.c, this.d, this.a);
            } else {
                this.b.c();
            }
            if (d.a) {
                d.c(this, "require flushAndSync id[%d] index[%d] offset[%d], consume[%d]", Integer.valueOf(this.c), Integer.valueOf(this.d), Long.valueOf(this.a), Long.valueOf(SystemClock.uptimeMillis() - uptimeMillis));
            }
        }
    }
}
