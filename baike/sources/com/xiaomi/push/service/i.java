package com.xiaomi.push.service;

import android.os.SystemClock;
import java.util.concurrent.RejectedExecutionException;

public class i {
    private static long a;
    private static long b = a;
    private static long c;
    private final c d;
    private final a e;

    public static abstract class b implements Runnable {
        protected int f;

        public b(int i) {
            this.f = i;
        }
    }

    private static final class a {
        private final c a;

        a(c cVar) {
            this.a = cVar;
        }

        protected void finalize() {
            try {
                synchronized (this.a) {
                    this.a.e = true;
                    this.a.notify();
                }
            } finally {
                super.finalize();
            }
        }
    }

    private static final class c extends Thread {
        private volatile long a = 0;
        private volatile boolean b = false;
        private long c = 50;
        private boolean d;
        private boolean e;
        private a f = new a();

        private static final class a {
            private int a;
            private d[] b;
            private int c;
            private int d;

            private a() {
                this.a = 256;
                this.b = new d[this.a];
                this.c = 0;
                this.d = 0;
            }

            private int b(d dVar) {
                for (int i = 0; i < this.b.length; i++) {
                    if (this.b[i] == dVar) {
                        return i;
                    }
                }
                return -1;
            }

            private void d(int i) {
                int i2 = (i * 2) + 1;
                while (i2 < this.c && this.c > 0) {
                    int i3 = (i2 + 1 >= this.c || this.b[i2 + 1].c >= this.b[i2].c) ? i2 : i2 + 1;
                    if (this.b[i].c >= this.b[i3].c) {
                        d dVar = this.b[i];
                        this.b[i] = this.b[i3];
                        this.b[i3] = dVar;
                        i2 = (i3 * 2) + 1;
                        i = i3;
                    } else {
                        return;
                    }
                }
            }

            private void e() {
                int i = this.c - 1;
                for (int i2 = (i - 1) / 2; this.b[i].c < this.b[i2].c; i2 = (i2 - 1) / 2) {
                    d dVar = this.b[i];
                    this.b[i] = this.b[i2];
                    this.b[i2] = dVar;
                    i = i2;
                }
            }

            public d a() {
                return this.b[0];
            }

            public void a(int i, b bVar) {
                for (int i2 = 0; i2 < this.c; i2++) {
                    if (this.b[i2].d == bVar) {
                        this.b[i2].a();
                    }
                }
                d();
            }

            public void a(d dVar) {
                if (this.b.length == this.c) {
                    Object obj = new d[(this.c * 2)];
                    System.arraycopy(this.b, 0, obj, 0, this.c);
                    this.b = obj;
                }
                d[] dVarArr = this.b;
                int i = this.c;
                this.c = i + 1;
                dVarArr[i] = dVar;
                e();
            }

            public boolean a(int i) {
                for (int i2 = 0; i2 < this.c; i2++) {
                    if (this.b[i2].e == i) {
                        return true;
                    }
                }
                return false;
            }

            public void b(int i) {
                for (int i2 = 0; i2 < this.c; i2++) {
                    if (this.b[i2].e == i) {
                        this.b[i2].a();
                    }
                }
                d();
            }

            public boolean b() {
                return this.c == 0;
            }

            public void c() {
                this.b = new d[this.a];
                this.c = 0;
            }

            public void c(int i) {
                if (i >= 0 && i < this.c) {
                    d[] dVarArr = this.b;
                    d[] dVarArr2 = this.b;
                    int i2 = this.c - 1;
                    this.c = i2;
                    dVarArr[i] = dVarArr2[i2];
                    this.b[this.c] = null;
                    d(i);
                }
            }

            public void d() {
                int i = 0;
                while (i < this.c) {
                    if (this.b[i].b) {
                        this.d++;
                        c(i);
                        i--;
                    }
                    i++;
                }
            }
        }

        c(String str, boolean z) {
            setName(str);
            setDaemon(z);
            start();
        }

        private void a(d dVar) {
            this.f.a(dVar);
            notify();
        }

        public synchronized void a() {
            this.d = true;
            this.f.c();
            notify();
        }

        public boolean b() {
            return this.b && SystemClock.uptimeMillis() - this.a > 600000;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
            r8 = this;
        L_0x0000:
            monitor-enter(r8);
            r0 = r8.d;	 Catch:{ all -> 0x0015 }
            if (r0 == 0) goto L_0x0007;
        L_0x0005:
            monitor-exit(r8);	 Catch:{ all -> 0x0015 }
        L_0x0006:
            return;
        L_0x0007:
            r0 = r8.f;	 Catch:{ all -> 0x0015 }
            r0 = r0.b();	 Catch:{ all -> 0x0015 }
            if (r0 == 0) goto L_0x001d;
        L_0x000f:
            r0 = r8.e;	 Catch:{ all -> 0x0015 }
            if (r0 == 0) goto L_0x0018;
        L_0x0013:
            monitor-exit(r8);	 Catch:{ all -> 0x0015 }
            goto L_0x0006;
        L_0x0015:
            r0 = move-exception;
            monitor-exit(r8);	 Catch:{ all -> 0x0015 }
            throw r0;
        L_0x0018:
            r8.wait();	 Catch:{ InterruptedException -> 0x00c4 }
        L_0x001b:
            monitor-exit(r8);	 Catch:{ all -> 0x0015 }
            goto L_0x0000;
        L_0x001d:
            r0 = com.xiaomi.push.service.i.a();	 Catch:{ all -> 0x0015 }
            r2 = r8.f;	 Catch:{ all -> 0x0015 }
            r2 = r2.a();	 Catch:{ all -> 0x0015 }
            r3 = r2.a;	 Catch:{ all -> 0x0015 }
            monitor-enter(r3);	 Catch:{ all -> 0x0015 }
            r4 = r2.b;	 Catch:{ all -> 0x0062 }
            if (r4 == 0) goto L_0x0037;
        L_0x002e:
            r0 = r8.f;	 Catch:{ all -> 0x0062 }
            r1 = 0;
            r0.c(r1);	 Catch:{ all -> 0x0062 }
            monitor-exit(r3);	 Catch:{ all -> 0x0062 }
            monitor-exit(r8);	 Catch:{ all -> 0x0015 }
            goto L_0x0000;
        L_0x0037:
            r4 = r2.c;	 Catch:{ all -> 0x0062 }
            r0 = r4 - r0;
            monitor-exit(r3);	 Catch:{ all -> 0x0062 }
            r4 = 0;
            r3 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
            if (r3 <= 0) goto L_0x0065;
        L_0x0042:
            r2 = r8.c;	 Catch:{ all -> 0x0015 }
            r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
            if (r2 <= 0) goto L_0x004a;
        L_0x0048:
            r0 = r8.c;	 Catch:{ all -> 0x0015 }
        L_0x004a:
            r2 = r8.c;	 Catch:{ all -> 0x0015 }
            r4 = 50;
            r2 = r2 + r4;
            r8.c = r2;	 Catch:{ all -> 0x0015 }
            r2 = r8.c;	 Catch:{ all -> 0x0015 }
            r4 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
            r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
            if (r2 <= 0) goto L_0x005d;
        L_0x0059:
            r2 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
            r8.c = r2;	 Catch:{ all -> 0x0015 }
        L_0x005d:
            r8.wait(r0);	 Catch:{ InterruptedException -> 0x00c7 }
        L_0x0060:
            monitor-exit(r8);	 Catch:{ all -> 0x0015 }
            goto L_0x0000;
        L_0x0062:
            r0 = move-exception;
            monitor-exit(r3);	 Catch:{ all -> 0x0062 }
            throw r0;	 Catch:{ all -> 0x0015 }
        L_0x0065:
            r0 = 50;
            r8.c = r0;	 Catch:{ all -> 0x0015 }
            r1 = r2.a;	 Catch:{ all -> 0x0015 }
            monitor-enter(r1);	 Catch:{ all -> 0x0015 }
            r0 = 0;
            r3 = r8.f;	 Catch:{ all -> 0x00be }
            r3 = r3.a();	 Catch:{ all -> 0x00be }
            r4 = r3.c;	 Catch:{ all -> 0x00be }
            r6 = r2.c;	 Catch:{ all -> 0x00be }
            r3 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
            if (r3 == 0) goto L_0x0081;
        L_0x007b:
            r0 = r8.f;	 Catch:{ all -> 0x00be }
            r0 = r0.b(r2);	 Catch:{ all -> 0x00be }
        L_0x0081:
            r3 = r2.b;	 Catch:{ all -> 0x00be }
            if (r3 == 0) goto L_0x0094;
        L_0x0085:
            r0 = r8.f;	 Catch:{ all -> 0x00be }
            r3 = r8.f;	 Catch:{ all -> 0x00be }
            r2 = r3.b(r2);	 Catch:{ all -> 0x00be }
            r0.c(r2);	 Catch:{ all -> 0x00be }
            monitor-exit(r1);	 Catch:{ all -> 0x00be }
            monitor-exit(r8);	 Catch:{ all -> 0x0015 }
            goto L_0x0000;
        L_0x0094:
            r4 = r2.c;	 Catch:{ all -> 0x00be }
            r2.a(r4);	 Catch:{ all -> 0x00be }
            r3 = r8.f;	 Catch:{ all -> 0x00be }
            r3.c(r0);	 Catch:{ all -> 0x00be }
            r4 = 0;
            r2.c = r4;	 Catch:{ all -> 0x00be }
            monitor-exit(r1);	 Catch:{ all -> 0x00be }
            monitor-exit(r8);	 Catch:{ all -> 0x0015 }
            r0 = android.os.SystemClock.uptimeMillis();	 Catch:{ all -> 0x00b7 }
            r8.a = r0;	 Catch:{ all -> 0x00b7 }
            r0 = 1;
            r8.b = r0;	 Catch:{ all -> 0x00b7 }
            r0 = r2.d;	 Catch:{ all -> 0x00b7 }
            r0.run();	 Catch:{ all -> 0x00b7 }
            r0 = 0;
            r8.b = r0;	 Catch:{ all -> 0x00b7 }
            goto L_0x0000;
        L_0x00b7:
            r0 = move-exception;
            monitor-enter(r8);
            r1 = 1;
            r8.d = r1;	 Catch:{ all -> 0x00c1 }
            monitor-exit(r8);	 Catch:{ all -> 0x00c1 }
            throw r0;
        L_0x00be:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x00be }
            throw r0;	 Catch:{ all -> 0x0015 }
        L_0x00c1:
            r0 = move-exception;
            monitor-exit(r8);	 Catch:{ all -> 0x00c1 }
            throw r0;
        L_0x00c4:
            r0 = move-exception;
            goto L_0x001b;
        L_0x00c7:
            r0 = move-exception;
            goto L_0x0060;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.i.c.run():void");
        }
    }

    static class d {
        final Object a = new Object();
        boolean b;
        long c;
        b d;
        int e;
        private long f;

        d() {
        }

        void a(long j) {
            synchronized (this.a) {
                this.f = j;
            }
        }

        public boolean a() {
            boolean z = true;
            synchronized (this.a) {
                if (this.b || this.c <= 0) {
                    z = false;
                }
                this.b = true;
            }
            return z;
        }
    }

    static {
        long j = 0;
        if (SystemClock.elapsedRealtime() > 0) {
            j = SystemClock.elapsedRealtime();
        }
        a = j;
    }

    public i() {
        this(false);
    }

    public i(String str) {
        this(str, false);
    }

    public i(String str, boolean z) {
        if (str == null) {
            throw new NullPointerException("name == null");
        }
        this.d = new c(str, z);
        this.e = new a(this.d);
    }

    public i(boolean z) {
        this("Timer-" + e(), z);
    }

    static synchronized long a() {
        long elapsedRealtime;
        synchronized (i.class) {
            elapsedRealtime = SystemClock.elapsedRealtime();
            if (elapsedRealtime > b) {
                a += elapsedRealtime - b;
            }
            b = elapsedRealtime;
            elapsedRealtime = a;
        }
        return elapsedRealtime;
    }

    private void b(b bVar, long j) {
        synchronized (this.d) {
            if (this.d.d) {
                throw new IllegalStateException("Timer was canceled");
            }
            long a = a() + j;
            if (a < 0) {
                throw new IllegalArgumentException("Illegal delay to start the TimerTask: " + a);
            }
            d dVar = new d();
            dVar.e = bVar.f;
            dVar.d = bVar;
            dVar.c = a;
            this.d.a(dVar);
        }
    }

    private static synchronized long e() {
        long j;
        synchronized (i.class) {
            j = c;
            c = 1 + j;
        }
        return j;
    }

    public void a(int i, b bVar) {
        synchronized (this.d) {
            this.d.f.a(i, bVar);
        }
    }

    public void a(b bVar) {
        if (com.xiaomi.channel.commonutils.logger.b.a() >= 1 || Thread.currentThread() == this.d) {
            bVar.run();
        } else {
            com.xiaomi.channel.commonutils.logger.b.d("run job outside job job thread");
            throw new RejectedExecutionException("Run job outside job thread");
        }
    }

    public void a(b bVar, long j) {
        if (j < 0) {
            throw new IllegalArgumentException("delay < 0: " + j);
        }
        b(bVar, j);
    }

    public boolean a(int i) {
        boolean a;
        synchronized (this.d) {
            a = this.d.f.a(i);
        }
        return a;
    }

    public void b() {
        this.d.a();
    }

    public void b(int i) {
        synchronized (this.d) {
            this.d.f.b(i);
        }
    }

    public void c() {
        synchronized (this.d) {
            this.d.f.c();
        }
    }

    public boolean d() {
        return this.d.b();
    }
}
