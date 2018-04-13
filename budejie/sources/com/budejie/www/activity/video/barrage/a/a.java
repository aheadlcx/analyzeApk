package com.budejie.www.activity.video.barrage.a;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import com.budejie.www.activity.video.barrage.danmaku.model.a.b;
import com.budejie.www.activity.video.barrage.danmaku.model.android.DanmakuContext;
import com.budejie.www.activity.video.barrage.danmaku.model.android.DanmakuContext.DanmakuConfigTag;
import com.budejie.www.activity.video.barrage.danmaku.model.android.c;
import com.budejie.www.activity.video.barrage.danmaku.model.android.d;
import com.budejie.www.activity.video.barrage.danmaku.model.android.f;
import com.budejie.www.activity.video.barrage.danmaku.model.e;
import com.budejie.www.activity.video.barrage.danmaku.model.j;
import com.budejie.www.activity.video.barrage.danmaku.model.k;
import com.budejie.www.activity.video.barrage.danmaku.model.m;
import tv.cjump.jni.NativeBitmapFactory;

public class a extends e {
    static final /* synthetic */ boolean a = (!a.class.desiredAssertionStatus());
    private int l = 2;
    private a m;
    private e n;
    private final Object o = new Object();

    public class a {
        public HandlerThread a;
        c b = new c(4);
        f c = new f();
        b<d> d = com.budejie.www.activity.video.barrage.danmaku.model.a.e.a(this.c, 800);
        int e = 0;
        final /* synthetic */ a f;
        private int g;
        private int h = 0;
        private int i = 3;
        private a j;
        private boolean k = false;

        public class a extends Handler {
            final /* synthetic */ a a;
            private boolean b;
            private boolean c;
            private boolean d;

            public a(a aVar, Looper looper) {
                this.a = aVar;
                super(looper);
            }

            public void a() {
                this.d = true;
            }

            public void handleMessage(Message message) {
                int i = 0;
                switch (message.what) {
                    case 1:
                        this.a.i();
                        while (i < 300) {
                            this.a.d.a(new d());
                            i++;
                        }
                        break;
                    case 2:
                        c((com.budejie.www.activity.video.barrage.danmaku.model.c) message.obj);
                        return;
                    case 3:
                        removeMessages(3);
                        boolean z = !(this.a.f.f == null || this.a.f.j) || this.c;
                        a(z);
                        if (z) {
                            this.c = false;
                        }
                        if (this.a.f.f != null && !this.a.f.j) {
                            this.a.f.f.a();
                            this.a.f.j = true;
                            return;
                        }
                        return;
                    case 4:
                        this.a.k();
                        return;
                    case 5:
                        Long l = (Long) message.obj;
                        if (l != null) {
                            this.a.f.n.a(l.longValue());
                            this.c = true;
                            this.a.i();
                            d();
                            return;
                        }
                        return;
                    case 6:
                        removeCallbacksAndMessages(null);
                        this.b = true;
                        this.a.h();
                        this.a.j();
                        getLooper().quit();
                        return;
                    case 7:
                        this.a.h();
                        this.a.f.n.a(this.a.f.h.a - this.a.f.b.t.d);
                        this.c = true;
                        return;
                    case 8:
                        this.a.a(true);
                        this.a.f.n.a(this.a.f.h.a);
                        return;
                    case 9:
                        this.a.a(true);
                        this.a.f.n.a(this.a.f.h.a);
                        this.a.f.f();
                        return;
                    case 16:
                        break;
                    default:
                        return;
                }
                long e = e();
                if (e <= 0) {
                    e = this.a.f.b.t.d / 2;
                }
                sendEmptyMessageDelayed(16, e);
            }

            private long e() {
                float d = this.a.d();
                com.budejie.www.activity.video.barrage.danmaku.model.c c = this.a.b.c();
                long j = c != null ? c.b - this.a.f.h.a : 0;
                long j2 = this.a.f.b.t.d * 2;
                if (d < 0.6f && j > this.a.f.b.t.d) {
                    this.a.f.n.a(this.a.f.h.a);
                    removeMessages(3);
                    sendEmptyMessage(3);
                } else if (d > 0.4f && j < (-j2)) {
                    removeMessages(4);
                    sendEmptyMessage(4);
                } else if (d < 0.9f) {
                    j = this.a.f.n.a - this.a.f.h.a;
                    if (j < 0) {
                        this.a.f.n.a(this.a.f.h.a);
                        sendEmptyMessage(8);
                        sendEmptyMessage(3);
                    } else if (j <= j2) {
                        removeMessages(3);
                        sendEmptyMessage(3);
                    }
                }
                return 0;
            }

            private void a(com.budejie.www.activity.video.barrage.danmaku.model.c cVar, d dVar) {
                if (dVar == null) {
                    com.budejie.www.activity.video.barrage.danmaku.model.a.c cVar2 = (d) cVar.s;
                } else {
                    Object obj = dVar;
                }
                cVar.s = null;
                if (cVar2 != null) {
                    cVar2.b();
                    this.a.d.a(cVar2);
                }
            }

            private long a(boolean z) {
                long j = this.a.f.n.a;
                long e = j + (this.a.f.b.t.d * ((long) this.a.i));
                if (e < this.a.f.h.a) {
                    return 0;
                }
                long currentTimeMillis = System.currentTimeMillis();
                k kVar = null;
                int i = 0;
                Object obj = null;
                while (true) {
                    k a;
                    com.budejie.www.activity.video.barrage.danmaku.model.c c;
                    try {
                        a = this.a.f.d.a(j, e);
                    } catch (Exception e2) {
                        obj = 1;
                        SystemClock.sleep(10);
                        a = kVar;
                    }
                    i++;
                    if (i < 3 && a == null && r2 != null) {
                        kVar = a;
                    } else if (a != null) {
                        this.a.f.n.a(e);
                        return 0;
                    } else {
                        c = a.c();
                        com.budejie.www.activity.video.barrage.danmaku.model.c d = a.d();
                        if (c != null || d == null) {
                            this.a.f.n.a(e);
                            return 0;
                        }
                        long j2;
                        long min = Math.min(100, (((c.b - this.a.f.h.a) * 10) / this.a.f.b.t.d) + 30);
                        if (z) {
                            j2 = 0;
                        } else {
                            j2 = min;
                        }
                        j e3 = a.e();
                        com.budejie.www.activity.video.barrage.danmaku.model.c cVar = null;
                        int i2 = 0;
                        int a2 = a.a();
                        int i3 = 0;
                        while (!this.b && !this.d && e3.b()) {
                            cVar = e3.a();
                            if (d.b < this.a.f.h.a) {
                                break;
                            } else if (!cVar.c() && (z || (!cVar.e() && cVar.f()))) {
                                if (!cVar.h()) {
                                    this.a.f.b.s.a(cVar, i2, a2, null, true, this.a.f.b);
                                }
                                if (cVar.m != (byte) 0 || !cVar.i()) {
                                    int i4;
                                    if (cVar.n() == 1) {
                                        i4 = (int) ((cVar.b - j) / this.a.f.b.t.d);
                                        if (i3 == i4) {
                                            i2++;
                                            i4 = i3;
                                        } else {
                                            i2 = 0;
                                        }
                                    } else {
                                        i4 = i3;
                                    }
                                    if (!z) {
                                        try {
                                            synchronized (this.a.f.o) {
                                                this.a.f.o.wait(j2);
                                            }
                                        } catch (InterruptedException e4) {
                                            e4.printStackTrace();
                                        }
                                    }
                                    if (b(cVar) == (byte) 1 || (!z && System.currentTimeMillis() - currentTimeMillis >= 3800 * ((long) this.a.i))) {
                                        break;
                                    }
                                    i3 = i4;
                                }
                            }
                        }
                        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
                        if (cVar != null) {
                            this.a.f.n.a(cVar.b);
                        } else {
                            this.a.f.n.a(e);
                        }
                        return currentTimeMillis2;
                    }
                }
                if (a != null) {
                    c = a.c();
                    com.budejie.www.activity.video.barrage.danmaku.model.c d2 = a.d();
                    if (c != null) {
                    }
                    this.a.f.n.a(e);
                    return 0;
                }
                this.a.f.n.a(e);
                return 0;
            }

            public boolean a(com.budejie.www.activity.video.barrage.danmaku.model.c cVar) {
                com.budejie.www.activity.video.barrage.danmaku.model.a.c a;
                if (!cVar.b()) {
                    cVar.b(this.a.f.c);
                }
                try {
                    try {
                        a = com.budejie.www.activity.video.barrage.danmaku.c.b.a(cVar, this.a.f.c, (d) this.a.d.a());
                        cVar.s = a;
                        return true;
                    } catch (OutOfMemoryError e) {
                        if (a != null) {
                            this.a.d.a(a);
                        }
                        cVar.s = null;
                        return false;
                    } catch (Exception e2) {
                        if (a != null) {
                            this.a.d.a(a);
                        }
                        cVar.s = null;
                        return false;
                    }
                } catch (OutOfMemoryError e3) {
                    a = null;
                    if (a != null) {
                        this.a.d.a(a);
                    }
                    cVar.s = null;
                    return false;
                } catch (Exception e4) {
                    a = null;
                    if (a != null) {
                        this.a.d.a(a);
                    }
                    cVar.s = null;
                    return false;
                }
            }

            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            private byte b(com.budejie.www.activity.video.barrage.danmaku.model.c r6) {
                /*
                r5 = this;
                r3 = 0;
                r2 = 1;
                r1 = 0;
                r0 = r6.b();
                if (r0 != 0) goto L_0x0012;
            L_0x0009:
                r0 = r5.a;
                r0 = r0.f;
                r0 = r0.c;
                r6.b(r0);
            L_0x0012:
                r0 = r5.a;	 Catch:{ OutOfMemoryError -> 0x00b4, Exception -> 0x00ba }
                r4 = 1;
                r0 = r0.a(r6, r4);	 Catch:{ OutOfMemoryError -> 0x00b4, Exception -> 0x00ba }
                if (r0 == 0) goto L_0x0020;
            L_0x001b:
                r0 = r0.s;	 Catch:{ OutOfMemoryError -> 0x00b4, Exception -> 0x00ba }
                r0 = (com.budejie.www.activity.video.barrage.danmaku.model.android.d) r0;	 Catch:{ OutOfMemoryError -> 0x00b4, Exception -> 0x00ba }
                r3 = r0;
            L_0x0020:
                if (r3 == 0) goto L_0x003a;
            L_0x0022:
                r3.k();	 Catch:{ OutOfMemoryError -> 0x00c3, Exception -> 0x00ba }
                r6.s = r3;	 Catch:{ OutOfMemoryError -> 0x00c3, Exception -> 0x00ba }
                r0 = r5.a;	 Catch:{ OutOfMemoryError -> 0x00c3, Exception -> 0x00ba }
                r0 = r0.f;	 Catch:{ OutOfMemoryError -> 0x00c3, Exception -> 0x00ba }
                r0 = r0.m;	 Catch:{ OutOfMemoryError -> 0x00c3, Exception -> 0x00ba }
                r4 = r5.a;	 Catch:{ OutOfMemoryError -> 0x00c3, Exception -> 0x00ba }
                r4 = r4.b(r6);	 Catch:{ OutOfMemoryError -> 0x00c3, Exception -> 0x00ba }
                r0.a(r6, r4);	 Catch:{ OutOfMemoryError -> 0x00c3, Exception -> 0x00ba }
                r2 = r1;
            L_0x0039:
                return r2;
            L_0x003a:
                r0 = r5.a;	 Catch:{ OutOfMemoryError -> 0x00c3, Exception -> 0x00ba }
                r4 = 0;
                r4 = r0.a(r6, r4);	 Catch:{ OutOfMemoryError -> 0x00c3, Exception -> 0x00ba }
                if (r4 == 0) goto L_0x0048;
            L_0x0043:
                r0 = r4.s;	 Catch:{ OutOfMemoryError -> 0x00c3, Exception -> 0x00ba }
                r0 = (com.budejie.www.activity.video.barrage.danmaku.model.android.d) r0;	 Catch:{ OutOfMemoryError -> 0x00c3, Exception -> 0x00ba }
                r3 = r0;
            L_0x0048:
                if (r3 == 0) goto L_0x0067;
            L_0x004a:
                r0 = 0;
                r4.s = r0;	 Catch:{ OutOfMemoryError -> 0x00c3, Exception -> 0x00ba }
                r0 = r5.a;	 Catch:{ OutOfMemoryError -> 0x00c3, Exception -> 0x00ba }
                r0 = r0.f;	 Catch:{ OutOfMemoryError -> 0x00c3, Exception -> 0x00ba }
                r0 = r0.c;	 Catch:{ OutOfMemoryError -> 0x00c3, Exception -> 0x00ba }
                r0 = com.budejie.www.activity.video.barrage.danmaku.c.b.a(r6, r0, r3);	 Catch:{ OutOfMemoryError -> 0x00c3, Exception -> 0x00ba }
                r6.s = r0;	 Catch:{ OutOfMemoryError -> 0x00c6, Exception -> 0x00c0 }
                r3 = r5.a;	 Catch:{ OutOfMemoryError -> 0x00c6, Exception -> 0x00c0 }
                r3 = r3.f;	 Catch:{ OutOfMemoryError -> 0x00c6, Exception -> 0x00c0 }
                r3 = r3.m;	 Catch:{ OutOfMemoryError -> 0x00c6, Exception -> 0x00c0 }
                r4 = 0;
                r3.a(r6, r4);	 Catch:{ OutOfMemoryError -> 0x00c6, Exception -> 0x00c0 }
                r2 = r1;
                goto L_0x0039;
            L_0x0067:
                r0 = r6.n;	 Catch:{ OutOfMemoryError -> 0x00c3, Exception -> 0x00ba }
                r0 = (int) r0;	 Catch:{ OutOfMemoryError -> 0x00c3, Exception -> 0x00ba }
                r4 = r6.o;	 Catch:{ OutOfMemoryError -> 0x00c3, Exception -> 0x00ba }
                r4 = (int) r4;	 Catch:{ OutOfMemoryError -> 0x00c3, Exception -> 0x00ba }
                r0 = com.budejie.www.activity.video.barrage.danmaku.c.b.a(r0, r4);	 Catch:{ OutOfMemoryError -> 0x00c3, Exception -> 0x00ba }
                r4 = r5.a;	 Catch:{ OutOfMemoryError -> 0x00c3, Exception -> 0x00ba }
                r4 = r4.h;	 Catch:{ OutOfMemoryError -> 0x00c3, Exception -> 0x00ba }
                r0 = r0 + r4;
                r4 = r5.a;	 Catch:{ OutOfMemoryError -> 0x00c3, Exception -> 0x00ba }
                r4 = r4.g;	 Catch:{ OutOfMemoryError -> 0x00c3, Exception -> 0x00ba }
                if (r0 > r4) goto L_0x0039;
            L_0x0080:
                r0 = r5.a;	 Catch:{ OutOfMemoryError -> 0x00c3, Exception -> 0x00ba }
                r0 = r0.d;	 Catch:{ OutOfMemoryError -> 0x00c3, Exception -> 0x00ba }
                r0 = r0.a();	 Catch:{ OutOfMemoryError -> 0x00c3, Exception -> 0x00ba }
                r0 = (com.budejie.www.activity.video.barrage.danmaku.model.android.d) r0;	 Catch:{ OutOfMemoryError -> 0x00c3, Exception -> 0x00ba }
                r3 = r5.a;	 Catch:{ OutOfMemoryError -> 0x00c6, Exception -> 0x00c0 }
                r3 = r3.f;	 Catch:{ OutOfMemoryError -> 0x00c6, Exception -> 0x00c0 }
                r3 = r3.c;	 Catch:{ OutOfMemoryError -> 0x00c6, Exception -> 0x00c0 }
                r0 = com.budejie.www.activity.video.barrage.danmaku.c.b.a(r6, r3, r0);	 Catch:{ OutOfMemoryError -> 0x00c6, Exception -> 0x00c0 }
                r6.s = r0;	 Catch:{ OutOfMemoryError -> 0x00c6, Exception -> 0x00c0 }
                r3 = r5.a;	 Catch:{ OutOfMemoryError -> 0x00c6, Exception -> 0x00c0 }
                r3 = r3.f;	 Catch:{ OutOfMemoryError -> 0x00c6, Exception -> 0x00c0 }
                r3 = r3.m;	 Catch:{ OutOfMemoryError -> 0x00c6, Exception -> 0x00c0 }
                r4 = r5.a;	 Catch:{ OutOfMemoryError -> 0x00c6, Exception -> 0x00c0 }
                r4 = r4.b(r6);	 Catch:{ OutOfMemoryError -> 0x00c6, Exception -> 0x00c0 }
                r3 = r3.a(r6, r4);	 Catch:{ OutOfMemoryError -> 0x00c6, Exception -> 0x00c0 }
                if (r3 != 0) goto L_0x00ad;
            L_0x00aa:
                r5.a(r6, r0);	 Catch:{ OutOfMemoryError -> 0x00c6, Exception -> 0x00c0 }
            L_0x00ad:
                if (r3 == 0) goto L_0x00b2;
            L_0x00af:
                r0 = r1;
            L_0x00b0:
                r2 = r0;
                goto L_0x0039;
            L_0x00b2:
                r0 = r2;
                goto L_0x00b0;
            L_0x00b4:
                r0 = move-exception;
                r0 = r3;
            L_0x00b6:
                r5.a(r6, r0);
                goto L_0x0039;
            L_0x00ba:
                r0 = move-exception;
            L_0x00bb:
                r5.a(r6, r3);
                goto L_0x0039;
            L_0x00c0:
                r1 = move-exception;
                r3 = r0;
                goto L_0x00bb;
            L_0x00c3:
                r0 = move-exception;
                r0 = r3;
                goto L_0x00b6;
            L_0x00c6:
                r1 = move-exception;
                goto L_0x00b6;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.budejie.www.activity.video.barrage.a.a.a.a.b(com.budejie.www.activity.video.barrage.danmaku.model.c):byte");
            }

            private final void c(com.budejie.www.activity.video.barrage.danmaku.model.c cVar) {
                if (!cVar.e()) {
                    if (cVar.m != (byte) 0 || !cVar.i()) {
                        if (cVar.t || !cVar.g() || cVar.b <= this.a.f.n.a + this.a.f.b.t.d) {
                            if (!cVar.c()) {
                                b(cVar);
                            }
                            if (cVar.t) {
                                this.a.f.n.a(this.a.f.h.a + (this.a.f.b.t.d * ((long) this.a.i)));
                            }
                        }
                    }
                }
            }

            public void b() {
                sendEmptyMessage(1);
                sendEmptyMessageDelayed(4, this.a.f.b.t.d);
            }

            public void c() {
                this.b = true;
                removeCallbacksAndMessages(null);
                sendEmptyMessage(6);
            }

            public void d() {
                this.d = false;
                this.b = false;
                removeMessages(16);
                sendEmptyMessage(16);
                sendEmptyMessageDelayed(4, this.a.f.b.t.d);
            }

            public void a(long j) {
                removeMessages(3);
                this.c = true;
                this.d = false;
                this.a.f.n.a(this.a.f.h.a + j);
                sendEmptyMessage(3);
            }
        }

        public a(a aVar, int i, int i2) {
            this.f = aVar;
            this.g = i;
            this.i = i2;
        }

        public void a(long j) {
            if (this.j != null) {
                this.j.a();
                this.j.removeMessages(3);
                this.j.obtainMessage(5, Long.valueOf(j)).sendToTarget();
            }
        }

        public void a(com.budejie.www.activity.video.barrage.danmaku.model.c cVar) {
            if (this.j == null) {
                return;
            }
            if (!cVar.t) {
                this.j.obtainMessage(2, cVar).sendToTarget();
            } else if (!cVar.e()) {
                this.j.a(cVar);
            }
        }

        public void a() {
            if (this.a == null) {
                this.a = new HandlerThread("DFM Cache-Building Thread");
                this.a.start();
            }
            if (this.j == null) {
                this.j = new a(this, this.a.getLooper());
            }
            this.j.b();
        }

        public void b() {
            this.k = true;
            synchronized (this.f.o) {
                this.f.o.notifyAll();
            }
            if (this.j != null) {
                this.j.c();
                this.j = null;
            }
            if (this.a != null) {
                try {
                    this.a.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.a.quit();
                this.a = null;
            }
        }

        public void c() {
            if (this.j != null) {
                this.j.d();
            } else {
                a();
            }
        }

        public float d() {
            if (this.g == 0) {
                return 0.0f;
            }
            return ((float) this.h) / ((float) this.g);
        }

        private void h() {
            if (this.b != null) {
                j e = this.b.e();
                while (e.b()) {
                    a(true, e.a(), null);
                }
                this.b.b();
            }
            this.h = 0;
        }

        private void i() {
            a(false);
        }

        private void a(boolean z) {
            if (this.b != null) {
                j e = this.b.e();
                while (e.b()) {
                    com.budejie.www.activity.video.barrage.danmaku.model.c a = e.a();
                    m mVar = a.s;
                    boolean z2 = mVar != null && mVar.f();
                    if (z && z2) {
                        if (mVar.a() != null) {
                            this.h -= mVar.c();
                            mVar.b();
                        }
                        a(true, a, null);
                        e.c();
                    } else if (!a.c() || a.f()) {
                        a(true, a, null);
                        e.c();
                    }
                }
            }
            this.h = 0;
        }

        protected void a(boolean z, com.budejie.www.activity.video.barrage.danmaku.model.c cVar, com.budejie.www.activity.video.barrage.danmaku.model.c cVar2) {
            if (cVar.s == null) {
                return;
            }
            if (cVar.s.f()) {
                cVar.s.g();
                cVar.s = null;
                return;
            }
            this.h -= b(cVar);
            cVar.s.b();
            this.d.a((d) cVar.s);
            cVar.s = null;
        }

        protected int b(com.budejie.www.activity.video.barrage.danmaku.model.c cVar) {
            if (cVar.s == null || cVar.s.f()) {
                return 0;
            }
            return cVar.s.c();
        }

        private void j() {
            while (true) {
                d dVar = (d) this.d.a();
                if (dVar != null) {
                    dVar.b();
                } else {
                    return;
                }
            }
        }

        private boolean a(com.budejie.www.activity.video.barrage.danmaku.model.c cVar, int i) {
            while (this.h + i > this.g && this.b.a() > 0) {
                com.budejie.www.activity.video.barrage.danmaku.model.c c = this.b.c();
                if (!c.e()) {
                    return false;
                }
                a(false, c, cVar);
                this.b.b(c);
            }
            this.b.a(cVar);
            this.h += i;
            return true;
        }

        private void k() {
            c(this.f.h.a);
        }

        private void c(long j) {
            j e = this.b.e();
            while (e.b() && !this.k) {
                com.budejie.www.activity.video.barrage.danmaku.model.c a = e.a();
                if (a.e()) {
                    synchronized (this.f.o) {
                        try {
                            this.f.o.wait(30);
                        } catch (InterruptedException e2) {
                            e2.printStackTrace();
                            return;
                        }
                    }
                    a(false, a, null);
                    e.c();
                } else {
                    return;
                }
            }
        }

        private com.budejie.www.activity.video.barrage.danmaku.model.c a(com.budejie.www.activity.video.barrage.danmaku.model.c cVar, boolean z) {
            j e = this.b.e();
            int i = 0;
            if (!z) {
                i = this.f.c.i() * 2;
            }
            while (e.b()) {
                com.budejie.www.activity.video.barrage.danmaku.model.c a = e.a();
                if (a.c()) {
                    if (a.n == cVar.n && a.o == cVar.o && a.i == cVar.i && a.k == cVar.k && a.e == cVar.e && a.c.equals(cVar.c)) {
                        return a;
                    }
                    if (z) {
                        continue;
                    } else if (!a.e()) {
                        break;
                    } else if (a.s.f()) {
                        continue;
                    } else {
                        float d = ((float) a.s.d()) - cVar.n;
                        float e2 = ((float) a.s.e()) - cVar.o;
                        if (d >= 0.0f && d <= ((float) r0) && e2 >= 0.0f && e2 <= ((float) r0)) {
                            return a;
                        }
                    }
                }
            }
            return null;
        }

        public void b(long j) {
            if (this.j != null) {
                this.j.a(j);
            }
        }

        public void e() {
            if (this.j != null) {
                this.j.removeMessages(3);
                this.j.a();
                this.j.removeMessages(7);
                this.j.sendEmptyMessage(7);
            }
        }

        public void f() {
            if (this.j != null) {
                this.j.removeMessages(9);
                this.j.sendEmptyMessage(9);
            }
        }

        public void g() {
            if (this.j != null) {
                this.j.removeMessages(4);
                this.j.sendEmptyMessage(4);
            }
        }

        public void a(Runnable runnable) {
            if (this.j != null) {
                this.j.post(runnable);
            }
        }
    }

    public a(e eVar, DanmakuContext danmakuContext, com.budejie.www.activity.video.barrage.a.h.a aVar, int i) {
        super(eVar, danmakuContext, aVar);
        NativeBitmapFactory.b();
        this.l = i;
        if (NativeBitmapFactory.a()) {
            this.l = i * 2;
        }
        this.m = new a(this, i, 3);
    }

    protected void a(e eVar) {
        this.h = eVar;
        this.n = new e();
        this.n.a(eVar.a);
    }

    public void a(com.budejie.www.activity.video.barrage.danmaku.model.c cVar) {
        super.a(cVar);
        if (this.m != null) {
            this.m.a(cVar);
        }
    }

    protected void b(com.budejie.www.activity.video.barrage.danmaku.model.c cVar) {
        super.b(cVar);
        if (cVar.c()) {
            if (cVar.s.f()) {
                cVar.s.g();
            } else {
                cVar.s.b();
            }
            cVar.s = null;
        }
    }

    public com.budejie.www.activity.video.barrage.danmaku.b.a.a a(com.budejie.www.activity.video.barrage.danmaku.model.a aVar) {
        com.budejie.www.activity.video.barrage.danmaku.b.a.a a = super.a(aVar);
        synchronized (this.o) {
            this.o.notify();
        }
        if (!(a == null || this.m == null || a.g >= -20)) {
            this.m.g();
            this.m.b(-this.b.t.d);
        }
        return a;
    }

    public void a() {
        if (this.g != null) {
            this.g.a();
        }
    }

    public void a(long j) {
        super.a(j);
        this.m.a(j);
    }

    public void b() {
        super.b();
        NativeBitmapFactory.b();
        if (this.m == null) {
            this.m = new a(this, this.l, 3);
            this.m.a();
            return;
        }
        this.m.c();
    }

    public void c() {
        super.c();
        a();
        if (this.m != null) {
            this.m.b();
            this.m = null;
        }
        NativeBitmapFactory.c();
    }

    public void d() {
        if (a || this.e != null) {
            a(this.e);
            this.m.a();
            return;
        }
        throw new AssertionError();
    }

    public boolean a(DanmakuContext danmakuContext, DanmakuConfigTag danmakuConfigTag, Object... objArr) {
        if (!super.b(danmakuContext, danmakuConfigTag, objArr)) {
            if (DanmakuConfigTag.SCROLL_SPEED_FACTOR.equals(danmakuConfigTag)) {
                this.c.b(this.b.c);
                f();
            } else if (danmakuConfigTag.isVisibilityRelatedTag()) {
                if (objArr != null && objArr.length > 0 && objArr[0] != null && ((!(objArr[0] instanceof Boolean) || ((Boolean) objArr[0]).booleanValue()) && this.m != null)) {
                    this.m.b(0);
                }
                f();
            } else if (DanmakuConfigTag.TRANSPARENCY.equals(danmakuConfigTag) || DanmakuConfigTag.SCALE_TEXTSIZE.equals(danmakuConfigTag) || DanmakuConfigTag.DANMAKU_STYLE.equals(danmakuConfigTag)) {
                if (DanmakuConfigTag.SCALE_TEXTSIZE.equals(danmakuConfigTag)) {
                    this.c.b(this.b.c);
                }
                if (this.m != null) {
                    this.m.e();
                    this.m.b(-this.b.t.d);
                }
            } else if (this.m != null) {
                this.m.f();
                this.m.b(0);
            }
        }
        if (!(this.f == null || this.m == null)) {
            this.m.a(new Runnable(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.f.b();
                }
            });
        }
        return true;
    }
}
