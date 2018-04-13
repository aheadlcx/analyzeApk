package com.danikula.videocache;

import java.lang.Thread.State;
import java.util.concurrent.atomic.AtomicInteger;

class l {
    private final n a;
    private final a b;
    private final Object c = new Object();
    private final Object d = new Object();
    private final Object e = new Object();
    private final AtomicInteger f;
    private volatile Thread g;
    private volatile boolean h;
    private volatile int i = -1;

    private class a implements Runnable {
        final /* synthetic */ l a;

        private a(l lVar) {
            this.a = lVar;
        }

        public void run() {
            this.a.e();
        }
    }

    public l(n nVar, a aVar) {
        this.a = (n) k.a((Object) nVar);
        this.b = (a) k.a((Object) aVar);
        this.f = new AtomicInteger();
    }

    public int a(byte[] bArr, long j, int i) throws ProxyCacheException {
        m.a(bArr, j, i);
        while (!this.b.d() && this.b.a() < ((long) i) + j && !this.h) {
            c();
            d();
            a();
        }
        int a = this.b.a(bArr, j, i);
        if (this.b.d() && this.i != 100) {
            this.i = 100;
            a(100);
        }
        return a;
    }

    private void a() throws ProxyCacheException {
        int i = this.f.get();
        if (i >= 1) {
            this.f.set(0);
            throw new ProxyCacheException("Error reading source " + i + " times");
        }
    }

    public void b() {
        synchronized (this.d) {
            com.izuiyou.a.a.a.b("ProxyCache", "Shutdown proxy for " + this.a);
            try {
                this.h = true;
                if (this.g != null) {
                    this.g.interrupt();
                }
                this.b.b();
            } catch (Throwable e) {
                a(e);
            }
        }
    }

    private synchronized void c() throws ProxyCacheException {
        if (!(this.h || this.b.d())) {
            Object obj = (this.g == null || this.g.getState() == State.TERMINATED) ? null : 1;
            if (obj != null) {
                synchronized (this.e) {
                    this.e.notifyAll();
                }
            } else {
                this.g = new Thread(new a(), "Source reader for " + this.a);
                this.g.start();
            }
        }
    }

    private void d() throws ProxyCacheException {
        synchronized (this.c) {
            try {
                this.c.wait(1000);
            } catch (Throwable e) {
                throw new ProxyCacheException("Waiting source data is interrupted!", e);
            }
        }
    }

    private void b(long j, long j2) {
        a(j, j2);
        synchronized (this.c) {
            this.c.notifyAll();
        }
    }

    protected void a(long j, long j2) {
        Object obj = 1;
        int i = ((j2 > 0 ? 1 : (j2 == 0 ? 0 : -1)) == 0 ? 1 : null) != null ? 100 : (int) ((((float) j) / ((float) j2)) * 100.0f);
        Object obj2;
        if (i != this.i) {
            obj2 = 1;
        } else {
            obj2 = null;
        }
        if (j2 < 0) {
            obj = null;
        }
        if (!(obj == null || r3 == null)) {
            a(i);
        }
        this.i = i;
    }

    protected void a(int i) {
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void e() {
        /*
        r11 = this;
        r10 = 1048576; // 0x100000 float:1.469368E-39 double:5.180654E-318;
        r4 = -1;
        r2 = 0;
        r0 = r11.b;	 Catch:{ Throwable -> 0x0052 }
        r2 = r0.a();	 Catch:{ Throwable -> 0x0052 }
        r0 = r11.a;	 Catch:{ Throwable -> 0x0052 }
        r0.a(r2);	 Catch:{ Throwable -> 0x0052 }
        r0 = r11.a;	 Catch:{ Throwable -> 0x0052 }
        r4 = r0.a();	 Catch:{ Throwable -> 0x0052 }
        r0 = 5120; // 0x1400 float:7.175E-42 double:2.5296E-320;
        r1 = new byte[r0];	 Catch:{ Throwable -> 0x0052 }
        r0 = 0;
    L_0x001c:
        r6 = r11.a;	 Catch:{ Throwable -> 0x0052 }
        r6 = r6.a(r1);	 Catch:{ Throwable -> 0x0052 }
        r7 = -1;
        if (r6 == r7) goto L_0x006d;
    L_0x0025:
        r7 = r11.d;	 Catch:{ Throwable -> 0x0052 }
        monitor-enter(r7);	 Catch:{ Throwable -> 0x0052 }
        r8 = r11.h();	 Catch:{ all -> 0x004f }
        if (r8 == 0) goto L_0x0036;
    L_0x002e:
        monitor-exit(r7);	 Catch:{ all -> 0x004f }
        r11.i();
        r11.b(r2, r4);
    L_0x0035:
        return;
    L_0x0036:
        r8 = r11.b;	 Catch:{ all -> 0x004f }
        r8.a(r1, r6);	 Catch:{ all -> 0x004f }
        monitor-exit(r7);	 Catch:{ all -> 0x004f }
        r8 = (long) r6;
        r2 = r2 + r8;
        r11.b(r2, r4);	 Catch:{ Throwable -> 0x0052 }
        r0 = r0 + r6;
        if (r0 < r10) goto L_0x001c;
    L_0x0044:
        r6 = r11.e;	 Catch:{ Throwable -> 0x0052 }
        monitor-enter(r6);	 Catch:{ Throwable -> 0x0052 }
        r7 = r11.e;	 Catch:{ all -> 0x0062 }
        r7.wait();	 Catch:{ all -> 0x0062 }
        monitor-exit(r6);	 Catch:{ all -> 0x0062 }
        r0 = r0 - r10;
        goto L_0x001c;
    L_0x004f:
        r0 = move-exception;
        monitor-exit(r7);	 Catch:{ all -> 0x004f }
        throw r0;	 Catch:{ Throwable -> 0x0052 }
    L_0x0052:
        r0 = move-exception;
        r1 = r11.f;	 Catch:{ all -> 0x0065 }
        r1.incrementAndGet();	 Catch:{ all -> 0x0065 }
        r11.a(r0);	 Catch:{ all -> 0x0065 }
        r11.i();
        r11.b(r2, r4);
        goto L_0x0035;
    L_0x0062:
        r0 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x0062 }
        throw r0;	 Catch:{ Throwable -> 0x0052 }
    L_0x0065:
        r0 = move-exception;
        r11.i();
        r11.b(r2, r4);
        throw r0;
    L_0x006d:
        r11.g();	 Catch:{ Throwable -> 0x0052 }
        r11.f();	 Catch:{ Throwable -> 0x0052 }
        r11.i();
        r11.b(r2, r4);
        goto L_0x0035;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.danikula.videocache.l.e():void");
    }

    private void f() {
        this.i = 100;
        a(this.i);
    }

    private void g() throws ProxyCacheException {
        synchronized (this.d) {
            if (!h() && this.b.a() == this.a.a()) {
                this.b.c();
            }
        }
    }

    private boolean h() {
        return Thread.currentThread().isInterrupted() || this.h;
    }

    private void i() {
        try {
            this.a.b();
        } catch (Throwable e) {
            a(new ProxyCacheException("Error closing source " + this.a, e));
        }
    }

    protected final void a(Throwable th) {
        if (th instanceof InterruptedProxyCacheException) {
            com.izuiyou.a.a.a.d("ProxyCache", "ProxyCache is interrupted");
        } else {
            com.izuiyou.a.a.a.d("ProxyCache", "ProxyCache error" + th);
        }
    }
}
