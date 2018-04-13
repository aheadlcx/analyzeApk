package com.liulishuo.filedownloader.download;

import com.liulishuo.filedownloader.d.b;
import com.liulishuo.filedownloader.g.f;

public class d implements Runnable {
    final int a;
    private final a b;
    private final g c;
    private final String d;
    private final boolean e;
    private f f;
    private volatile boolean g;
    private final int h;

    public static class a {
        private final a a = new a();
        private g b;
        private String c;
        private Boolean d;
        private Integer e;

        public a a(g gVar) {
            this.b = gVar;
            return this;
        }

        public a a(int i) {
            this.a.a(i);
            return this;
        }

        public a a(String str) {
            this.a.a(str);
            return this;
        }

        public a b(String str) {
            this.a.b(str);
            return this;
        }

        public a a(b bVar) {
            this.a.a(bVar);
            return this;
        }

        public a a(b bVar) {
            this.a.a(bVar);
            return this;
        }

        public a c(String str) {
            this.c = str;
            return this;
        }

        public a a(boolean z) {
            this.d = Boolean.valueOf(z);
            return this;
        }

        public a a(Integer num) {
            this.e = num;
            return this;
        }

        public d a() {
            if (this.b == null || this.c == null || this.d == null || this.e == null) {
                throw new IllegalArgumentException(f.a("%s %s %B", this.b, this.c, this.d));
            }
            a a = this.a.a();
            return new d(a.a, this.e.intValue(), a, this.b, this.d.booleanValue(), this.c);
        }
    }

    private d(int i, int i2, a aVar, g gVar, boolean z, String str) {
        this.h = i;
        this.a = i2;
        this.g = false;
        this.c = gVar;
        this.d = str;
        this.b = aVar;
        this.e = z;
    }

    public void a() {
        this.g = true;
        if (this.f != null) {
            this.f.a();
        }
    }

    public void b() {
        a();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r11 = this;
        r3 = 1;
        r2 = 0;
        r0 = 10;
        android.os.Process.setThreadPriority(r0);
        r4 = 0;
        r0 = r11.b;
        r0 = r0.e();
        r0 = r0.b;
        r1 = r2;
    L_0x0011:
        r0 = r11.g;	 Catch:{ IllegalAccessException -> 0x0174, IOException -> 0x017f, FileDownloadGiveUpRetryException -> 0x0191, IllegalArgumentException -> 0x0188, all -> 0x016c }
        if (r0 == 0) goto L_0x001b;
    L_0x0015:
        if (r4 == 0) goto L_0x001a;
    L_0x0017:
        r4.f();
    L_0x001a:
        return;
    L_0x001b:
        r0 = r11.b;	 Catch:{ IllegalAccessException -> 0x0177, IOException -> 0x0181, FileDownloadGiveUpRetryException -> 0x0193, IllegalArgumentException -> 0x018a, all -> 0x016c }
        r1 = r0.a();	 Catch:{ IllegalAccessException -> 0x0177, IOException -> 0x0181, FileDownloadGiveUpRetryException -> 0x0193, IllegalArgumentException -> 0x018a, all -> 0x016c }
        r0 = r1.e();	 Catch:{ IllegalAccessException -> 0x0095, IOException -> 0x0151, FileDownloadGiveUpRetryException -> 0x015a, IllegalArgumentException -> 0x0163 }
        r4 = com.liulishuo.filedownloader.g.d.a;	 Catch:{ IllegalAccessException -> 0x0095, IOException -> 0x0151, FileDownloadGiveUpRetryException -> 0x015a, IllegalArgumentException -> 0x0163 }
        if (r4 == 0) goto L_0x0054;
    L_0x0029:
        r4 = "the connection[%d] for %d, is connected %s with code[%d]";
        r5 = 4;
        r5 = new java.lang.Object[r5];	 Catch:{ IllegalAccessException -> 0x0095, IOException -> 0x0151, FileDownloadGiveUpRetryException -> 0x015a, IllegalArgumentException -> 0x0163 }
        r6 = 0;
        r7 = r11.a;	 Catch:{ IllegalAccessException -> 0x0095, IOException -> 0x0151, FileDownloadGiveUpRetryException -> 0x015a, IllegalArgumentException -> 0x0163 }
        r7 = java.lang.Integer.valueOf(r7);	 Catch:{ IllegalAccessException -> 0x0095, IOException -> 0x0151, FileDownloadGiveUpRetryException -> 0x015a, IllegalArgumentException -> 0x0163 }
        r5[r6] = r7;	 Catch:{ IllegalAccessException -> 0x0095, IOException -> 0x0151, FileDownloadGiveUpRetryException -> 0x015a, IllegalArgumentException -> 0x0163 }
        r6 = 1;
        r7 = r11.h;	 Catch:{ IllegalAccessException -> 0x0095, IOException -> 0x0151, FileDownloadGiveUpRetryException -> 0x015a, IllegalArgumentException -> 0x0163 }
        r7 = java.lang.Integer.valueOf(r7);	 Catch:{ IllegalAccessException -> 0x0095, IOException -> 0x0151, FileDownloadGiveUpRetryException -> 0x015a, IllegalArgumentException -> 0x0163 }
        r5[r6] = r7;	 Catch:{ IllegalAccessException -> 0x0095, IOException -> 0x0151, FileDownloadGiveUpRetryException -> 0x015a, IllegalArgumentException -> 0x0163 }
        r6 = 2;
        r7 = r11.b;	 Catch:{ IllegalAccessException -> 0x0095, IOException -> 0x0151, FileDownloadGiveUpRetryException -> 0x015a, IllegalArgumentException -> 0x0163 }
        r7 = r7.e();	 Catch:{ IllegalAccessException -> 0x0095, IOException -> 0x0151, FileDownloadGiveUpRetryException -> 0x015a, IllegalArgumentException -> 0x0163 }
        r5[r6] = r7;	 Catch:{ IllegalAccessException -> 0x0095, IOException -> 0x0151, FileDownloadGiveUpRetryException -> 0x015a, IllegalArgumentException -> 0x0163 }
        r6 = 3;
        r7 = java.lang.Integer.valueOf(r0);	 Catch:{ IllegalAccessException -> 0x0095, IOException -> 0x0151, FileDownloadGiveUpRetryException -> 0x015a, IllegalArgumentException -> 0x0163 }
        r5[r6] = r7;	 Catch:{ IllegalAccessException -> 0x0095, IOException -> 0x0151, FileDownloadGiveUpRetryException -> 0x015a, IllegalArgumentException -> 0x0163 }
        com.liulishuo.filedownloader.g.d.c(r11, r4, r5);	 Catch:{ IllegalAccessException -> 0x0095, IOException -> 0x0151, FileDownloadGiveUpRetryException -> 0x015a, IllegalArgumentException -> 0x0163 }
    L_0x0054:
        r4 = 206; // 0xce float:2.89E-43 double:1.02E-321;
        if (r0 == r4) goto L_0x00c2;
    L_0x0058:
        r4 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r0 == r4) goto L_0x00c2;
    L_0x005c:
        r4 = new java.net.SocketException;	 Catch:{ IllegalAccessException -> 0x0095, IOException -> 0x0151, FileDownloadGiveUpRetryException -> 0x015a, IllegalArgumentException -> 0x0163 }
        r5 = "Connection failed with request[%s] response[%s] http-state[%d] on task[%d-%d], which is changed after verify connection, so please try again.";
        r6 = 5;
        r6 = new java.lang.Object[r6];	 Catch:{ IllegalAccessException -> 0x0095, IOException -> 0x0151, FileDownloadGiveUpRetryException -> 0x015a, IllegalArgumentException -> 0x0163 }
        r7 = 0;
        r8 = r11.b;	 Catch:{ IllegalAccessException -> 0x0095, IOException -> 0x0151, FileDownloadGiveUpRetryException -> 0x015a, IllegalArgumentException -> 0x0163 }
        r8 = r8.d();	 Catch:{ IllegalAccessException -> 0x0095, IOException -> 0x0151, FileDownloadGiveUpRetryException -> 0x015a, IllegalArgumentException -> 0x0163 }
        r6[r7] = r8;	 Catch:{ IllegalAccessException -> 0x0095, IOException -> 0x0151, FileDownloadGiveUpRetryException -> 0x015a, IllegalArgumentException -> 0x0163 }
        r7 = 1;
        r8 = r1.c();	 Catch:{ IllegalAccessException -> 0x0095, IOException -> 0x0151, FileDownloadGiveUpRetryException -> 0x015a, IllegalArgumentException -> 0x0163 }
        r6[r7] = r8;	 Catch:{ IllegalAccessException -> 0x0095, IOException -> 0x0151, FileDownloadGiveUpRetryException -> 0x015a, IllegalArgumentException -> 0x0163 }
        r7 = 2;
        r0 = java.lang.Integer.valueOf(r0);	 Catch:{ IllegalAccessException -> 0x0095, IOException -> 0x0151, FileDownloadGiveUpRetryException -> 0x015a, IllegalArgumentException -> 0x0163 }
        r6[r7] = r0;	 Catch:{ IllegalAccessException -> 0x0095, IOException -> 0x0151, FileDownloadGiveUpRetryException -> 0x015a, IllegalArgumentException -> 0x0163 }
        r0 = 3;
        r7 = r11.h;	 Catch:{ IllegalAccessException -> 0x0095, IOException -> 0x0151, FileDownloadGiveUpRetryException -> 0x015a, IllegalArgumentException -> 0x0163 }
        r7 = java.lang.Integer.valueOf(r7);	 Catch:{ IllegalAccessException -> 0x0095, IOException -> 0x0151, FileDownloadGiveUpRetryException -> 0x015a, IllegalArgumentException -> 0x0163 }
        r6[r0] = r7;	 Catch:{ IllegalAccessException -> 0x0095, IOException -> 0x0151, FileDownloadGiveUpRetryException -> 0x015a, IllegalArgumentException -> 0x0163 }
        r0 = 4;
        r7 = r11.a;	 Catch:{ IllegalAccessException -> 0x0095, IOException -> 0x0151, FileDownloadGiveUpRetryException -> 0x015a, IllegalArgumentException -> 0x0163 }
        r7 = java.lang.Integer.valueOf(r7);	 Catch:{ IllegalAccessException -> 0x0095, IOException -> 0x0151, FileDownloadGiveUpRetryException -> 0x015a, IllegalArgumentException -> 0x0163 }
        r6[r0] = r7;	 Catch:{ IllegalAccessException -> 0x0095, IOException -> 0x0151, FileDownloadGiveUpRetryException -> 0x015a, IllegalArgumentException -> 0x0163 }
        r0 = com.liulishuo.filedownloader.g.f.a(r5, r6);	 Catch:{ IllegalAccessException -> 0x0095, IOException -> 0x0151, FileDownloadGiveUpRetryException -> 0x015a, IllegalArgumentException -> 0x0163 }
        r4.<init>(r0);	 Catch:{ IllegalAccessException -> 0x0095, IOException -> 0x0151, FileDownloadGiveUpRetryException -> 0x015a, IllegalArgumentException -> 0x0163 }
        throw r4;	 Catch:{ IllegalAccessException -> 0x0095, IOException -> 0x0151, FileDownloadGiveUpRetryException -> 0x015a, IllegalArgumentException -> 0x0163 }
    L_0x0095:
        r0 = move-exception;
        r4 = r1;
        r1 = r2;
    L_0x0098:
        r10 = r0;
        r0 = r1;
        r1 = r4;
        r4 = r10;
    L_0x009c:
        r5 = r11.c;	 Catch:{ all -> 0x014a }
        r5 = r5.a(r4);	 Catch:{ all -> 0x014a }
        if (r5 == 0) goto L_0x013e;
    L_0x00a4:
        if (r0 == 0) goto L_0x011d;
    L_0x00a6:
        r5 = r11.f;	 Catch:{ all -> 0x014a }
        if (r5 != 0) goto L_0x011d;
    L_0x00aa:
        r0 = "it is valid to retry and connection is valid but create fetch-data-task failed, so give up directly with %s";
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ all -> 0x014a }
        r3 = 0;
        r2[r3] = r4;	 Catch:{ all -> 0x014a }
        com.liulishuo.filedownloader.g.d.d(r11, r0, r2);	 Catch:{ all -> 0x014a }
        r0 = r11.c;	 Catch:{ all -> 0x014a }
        r0.b(r4);	 Catch:{ all -> 0x014a }
        if (r1 == 0) goto L_0x001a;
    L_0x00bd:
        r1.f();
        goto L_0x001a;
    L_0x00c2:
        r0 = new com.liulishuo.filedownloader.download.f$a;	 Catch:{ IllegalAccessException -> 0x016f, IOException -> 0x017b, FileDownloadGiveUpRetryException -> 0x018d, IllegalArgumentException -> 0x0184 }
        r0.<init>();	 Catch:{ IllegalAccessException -> 0x016f, IOException -> 0x017b, FileDownloadGiveUpRetryException -> 0x018d, IllegalArgumentException -> 0x0184 }
        r4 = r11.g;	 Catch:{ IllegalAccessException -> 0x016f, IOException -> 0x017b, FileDownloadGiveUpRetryException -> 0x018d, IllegalArgumentException -> 0x0184 }
        if (r4 == 0) goto L_0x00d2;
    L_0x00cb:
        if (r1 == 0) goto L_0x001a;
    L_0x00cd:
        r1.f();
        goto L_0x001a;
    L_0x00d2:
        r4 = r11.h;	 Catch:{ IllegalAccessException -> 0x016f, IOException -> 0x017b, FileDownloadGiveUpRetryException -> 0x018d, IllegalArgumentException -> 0x0184 }
        r0 = r0.b(r4);	 Catch:{ IllegalAccessException -> 0x016f, IOException -> 0x017b, FileDownloadGiveUpRetryException -> 0x018d, IllegalArgumentException -> 0x0184 }
        r4 = r11.a;	 Catch:{ IllegalAccessException -> 0x016f, IOException -> 0x017b, FileDownloadGiveUpRetryException -> 0x018d, IllegalArgumentException -> 0x0184 }
        r0 = r0.a(r4);	 Catch:{ IllegalAccessException -> 0x016f, IOException -> 0x017b, FileDownloadGiveUpRetryException -> 0x018d, IllegalArgumentException -> 0x0184 }
        r4 = r11.c;	 Catch:{ IllegalAccessException -> 0x016f, IOException -> 0x017b, FileDownloadGiveUpRetryException -> 0x018d, IllegalArgumentException -> 0x0184 }
        r0 = r0.a(r4);	 Catch:{ IllegalAccessException -> 0x016f, IOException -> 0x017b, FileDownloadGiveUpRetryException -> 0x018d, IllegalArgumentException -> 0x0184 }
        r0 = r0.a(r11);	 Catch:{ IllegalAccessException -> 0x016f, IOException -> 0x017b, FileDownloadGiveUpRetryException -> 0x018d, IllegalArgumentException -> 0x0184 }
        r4 = r11.e;	 Catch:{ IllegalAccessException -> 0x016f, IOException -> 0x017b, FileDownloadGiveUpRetryException -> 0x018d, IllegalArgumentException -> 0x0184 }
        r0 = r0.a(r4);	 Catch:{ IllegalAccessException -> 0x016f, IOException -> 0x017b, FileDownloadGiveUpRetryException -> 0x018d, IllegalArgumentException -> 0x0184 }
        r0 = r0.a(r1);	 Catch:{ IllegalAccessException -> 0x016f, IOException -> 0x017b, FileDownloadGiveUpRetryException -> 0x018d, IllegalArgumentException -> 0x0184 }
        r4 = r11.b;	 Catch:{ IllegalAccessException -> 0x016f, IOException -> 0x017b, FileDownloadGiveUpRetryException -> 0x018d, IllegalArgumentException -> 0x0184 }
        r4 = r4.e();	 Catch:{ IllegalAccessException -> 0x016f, IOException -> 0x017b, FileDownloadGiveUpRetryException -> 0x018d, IllegalArgumentException -> 0x0184 }
        r0 = r0.a(r4);	 Catch:{ IllegalAccessException -> 0x016f, IOException -> 0x017b, FileDownloadGiveUpRetryException -> 0x018d, IllegalArgumentException -> 0x0184 }
        r4 = r11.d;	 Catch:{ IllegalAccessException -> 0x016f, IOException -> 0x017b, FileDownloadGiveUpRetryException -> 0x018d, IllegalArgumentException -> 0x0184 }
        r0 = r0.a(r4);	 Catch:{ IllegalAccessException -> 0x016f, IOException -> 0x017b, FileDownloadGiveUpRetryException -> 0x018d, IllegalArgumentException -> 0x0184 }
        r0 = r0.a();	 Catch:{ IllegalAccessException -> 0x016f, IOException -> 0x017b, FileDownloadGiveUpRetryException -> 0x018d, IllegalArgumentException -> 0x0184 }
        r11.f = r0;	 Catch:{ IllegalAccessException -> 0x016f, IOException -> 0x017b, FileDownloadGiveUpRetryException -> 0x018d, IllegalArgumentException -> 0x0184 }
        r0 = r11.f;	 Catch:{ IllegalAccessException -> 0x016f, IOException -> 0x017b, FileDownloadGiveUpRetryException -> 0x018d, IllegalArgumentException -> 0x0184 }
        r0.b();	 Catch:{ IllegalAccessException -> 0x016f, IOException -> 0x017b, FileDownloadGiveUpRetryException -> 0x018d, IllegalArgumentException -> 0x0184 }
        r0 = r11.g;	 Catch:{ IllegalAccessException -> 0x016f, IOException -> 0x017b, FileDownloadGiveUpRetryException -> 0x018d, IllegalArgumentException -> 0x0184 }
        if (r0 == 0) goto L_0x0116;
    L_0x0111:
        r0 = r11.f;	 Catch:{ IllegalAccessException -> 0x016f, IOException -> 0x017b, FileDownloadGiveUpRetryException -> 0x018d, IllegalArgumentException -> 0x0184 }
        r0.a();	 Catch:{ IllegalAccessException -> 0x016f, IOException -> 0x017b, FileDownloadGiveUpRetryException -> 0x018d, IllegalArgumentException -> 0x0184 }
    L_0x0116:
        if (r1 == 0) goto L_0x001a;
    L_0x0118:
        r1.f();
        goto L_0x001a;
    L_0x011d:
        r5 = r11.f;	 Catch:{ all -> 0x014a }
        if (r5 == 0) goto L_0x0130;
    L_0x0121:
        r6 = r11.c();	 Catch:{ all -> 0x014a }
        r8 = 0;
        r5 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r5 <= 0) goto L_0x0130;
    L_0x012b:
        r5 = r11.b;	 Catch:{ all -> 0x014a }
        r5.a(r6);	 Catch:{ all -> 0x014a }
    L_0x0130:
        r5 = r11.c;	 Catch:{ all -> 0x014a }
        r5.c(r4);	 Catch:{ all -> 0x014a }
        if (r1 == 0) goto L_0x013a;
    L_0x0137:
        r1.f();
    L_0x013a:
        r4 = r1;
        r1 = r0;
        goto L_0x0011;
    L_0x013e:
        r0 = r11.c;	 Catch:{ all -> 0x014a }
        r0.b(r4);	 Catch:{ all -> 0x014a }
        if (r1 == 0) goto L_0x001a;
    L_0x0145:
        r1.f();
        goto L_0x001a;
    L_0x014a:
        r0 = move-exception;
    L_0x014b:
        if (r1 == 0) goto L_0x0150;
    L_0x014d:
        r1.f();
    L_0x0150:
        throw r0;
    L_0x0151:
        r0 = move-exception;
        r4 = r1;
        r1 = r2;
    L_0x0154:
        r10 = r0;
        r0 = r1;
        r1 = r4;
        r4 = r10;
        goto L_0x009c;
    L_0x015a:
        r0 = move-exception;
        r4 = r1;
        r1 = r2;
    L_0x015d:
        r10 = r0;
        r0 = r1;
        r1 = r4;
        r4 = r10;
        goto L_0x009c;
    L_0x0163:
        r0 = move-exception;
        r4 = r1;
        r1 = r2;
    L_0x0166:
        r10 = r0;
        r0 = r1;
        r1 = r4;
        r4 = r10;
        goto L_0x009c;
    L_0x016c:
        r0 = move-exception;
        r1 = r4;
        goto L_0x014b;
    L_0x016f:
        r0 = move-exception;
        r4 = r1;
        r1 = r3;
        goto L_0x0098;
    L_0x0174:
        r0 = move-exception;
        goto L_0x0098;
    L_0x0177:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0098;
    L_0x017b:
        r0 = move-exception;
        r4 = r1;
        r1 = r3;
        goto L_0x0154;
    L_0x017f:
        r0 = move-exception;
        goto L_0x0154;
    L_0x0181:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0154;
    L_0x0184:
        r0 = move-exception;
        r4 = r1;
        r1 = r3;
        goto L_0x0166;
    L_0x0188:
        r0 = move-exception;
        goto L_0x0166;
    L_0x018a:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0166;
    L_0x018d:
        r0 = move-exception;
        r4 = r1;
        r1 = r3;
        goto L_0x015d;
    L_0x0191:
        r0 = move-exception;
        goto L_0x015d;
    L_0x0193:
        r0 = move-exception;
        r1 = r2;
        goto L_0x015d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liulishuo.filedownloader.download.d.run():void");
    }

    private long c() {
        com.liulishuo.filedownloader.b.a c = c.a().c();
        if (this.a < 0) {
            return c.b(this.h).g();
        }
        for (com.liulishuo.filedownloader.d.a aVar : c.c(this.h)) {
            if (aVar.b() == this.a) {
                return aVar.d();
            }
        }
        return 0;
    }
}
