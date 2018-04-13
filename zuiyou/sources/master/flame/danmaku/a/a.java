package master.flame.danmaku.a;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import master.flame.danmaku.danmaku.model.a.b;
import master.flame.danmaku.danmaku.model.a.c;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.DanmakuContext.DanmakuConfigTag;
import master.flame.danmaku.danmaku.model.android.e;
import master.flame.danmaku.danmaku.model.android.h;
import master.flame.danmaku.danmaku.model.d;
import master.flame.danmaku.danmaku.model.f;
import master.flame.danmaku.danmaku.model.k;
import master.flame.danmaku.danmaku.model.l;
import master.flame.danmaku.danmaku.model.n;
import tv.cjump.jni.NativeBitmapFactory;

public class a extends e {
    static final /* synthetic */ boolean a = (!a.class.desiredAssertionStatus());
    private int m = 2;
    private a n;
    private f o;
    private final Object p = new Object();
    private int q;

    public class a implements k {
        public HandlerThread a;
        e b = new e();
        h c = new h();
        b<master.flame.danmaku.danmaku.model.android.f> d = master.flame.danmaku.danmaku.model.a.e.a(this.c, 800);
        final /* synthetic */ a e;
        private int f;
        private int g = 0;
        private int h = 3;
        private a i;
        private boolean j = false;

        public class a extends Handler {
            final /* synthetic */ a a;
            private boolean b;
            private boolean c;
            private boolean d;
            private boolean e;

            public a(a aVar, Looper looper) {
                this.a = aVar;
                super(looper);
            }

            public void a() {
                this.e = true;
            }

            public void handleMessage(Message message) {
                long longValue;
                int i = 0;
                switch (message.what) {
                    case 1:
                        this.a.j();
                        while (i < 300) {
                            this.a.d.a(new master.flame.danmaku.danmaku.model.android.f());
                            i++;
                        }
                        break;
                    case 2:
                        b((d) message.obj);
                        return;
                    case 3:
                        removeMessages(3);
                        boolean z = !(this.a.e.f == null || this.a.e.j) || this.d;
                        b(z);
                        if (z) {
                            this.d = false;
                        }
                        if (this.a.e.f != null && !this.a.e.j) {
                            this.a.e.f.a();
                            this.a.e.j = true;
                            return;
                        }
                        return;
                    case 4:
                        this.a.l();
                        return;
                    case 5:
                        Long l = (Long) message.obj;
                        if (l != null) {
                            longValue = l.longValue();
                            long j = this.a.e.o.a;
                            this.a.e.o.a(longValue);
                            this.d = true;
                            long e = this.a.e();
                            if (longValue > j || e - longValue > this.a.e.b.v.d) {
                                this.a.j();
                            } else {
                                this.a.l();
                            }
                            b(true);
                            d();
                            return;
                        }
                        return;
                    case 6:
                        removeCallbacksAndMessages(null);
                        this.b = true;
                        this.a.i();
                        this.a.k();
                        getLooper().quit();
                        return;
                    case 7:
                        this.a.i();
                        this.a.e.o.a(this.a.e.h.a - this.a.e.b.v.d);
                        this.d = true;
                        return;
                    case 8:
                        this.a.j();
                        this.a.e.o.a(this.a.e.h.a);
                        return;
                    case 9:
                        this.a.j();
                        this.a.e.o.a(this.a.e.h.a);
                        this.a.e.e();
                        return;
                    case 16:
                        break;
                    case 17:
                        d dVar = (d) message.obj;
                        if (dVar != null) {
                            boolean z2;
                            n d = dVar.d();
                            if ((dVar.I & 1) != 0) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            if (!z2 && d != null && d.a() != null && !d.f()) {
                                dVar.x = master.flame.danmaku.danmaku.c.a.a(dVar, this.a.e.c, (master.flame.danmaku.danmaku.model.android.f) dVar.x, this.a.e.b.w.d);
                                this.a.a(dVar, 0, true);
                                return;
                            } else if (dVar.y) {
                                this.a.c(dVar);
                                a(dVar);
                                return;
                            } else {
                                if (d != null && d.f()) {
                                    d.b();
                                }
                                this.a.a(true, dVar, null);
                                b(dVar);
                                return;
                            }
                        }
                        return;
                    case 18:
                        this.e = false;
                        return;
                    default:
                        return;
                }
                longValue = e();
                if (longValue <= 0) {
                    longValue = this.a.e.b.v.d / 2;
                }
                sendEmptyMessageDelayed(16, longValue);
            }

            private long e() {
                if (this.a.e.o.a <= this.a.e.h.a - this.a.e.b.v.d) {
                    if (this.a.e.b.w.f != -1) {
                        this.a.j();
                    }
                    this.a.e.o.a(this.a.e.h.a);
                    sendEmptyMessage(3);
                } else {
                    float d = this.a.d();
                    d c = this.a.b.c();
                    long s = c != null ? c.s() - this.a.e.h.a : 0;
                    long j = this.a.e.b.v.d * 2;
                    if (d < 0.6f && s > this.a.e.b.v.d) {
                        this.a.e.o.a(this.a.e.h.a);
                        removeMessages(3);
                        sendEmptyMessage(3);
                    } else if (d > 0.4f && s < (-j)) {
                        removeMessages(4);
                        sendEmptyMessage(4);
                    } else if (d < 0.9f) {
                        s = this.a.e.o.a - this.a.e.h.a;
                        if (c != null && c.f() && s < (-this.a.e.b.v.d)) {
                            this.a.e.o.a(this.a.e.h.a);
                            sendEmptyMessage(8);
                            sendEmptyMessage(3);
                        } else if (s <= j) {
                            removeMessages(3);
                            sendEmptyMessage(3);
                        }
                    }
                }
                return 0;
            }

            private void a(d dVar, master.flame.danmaku.danmaku.model.android.f fVar) {
                if (fVar == null) {
                    c cVar = (master.flame.danmaku.danmaku.model.android.f) dVar.x;
                } else {
                    Object obj = fVar;
                }
                dVar.x = null;
                if (cVar != null) {
                    cVar.b();
                    this.a.d.a(cVar);
                }
            }

            private void f() {
                l lVar = null;
                try {
                    long j = this.a.e.h.a;
                    lVar = this.a.e.d.a(j - this.a.e.b.v.d, (this.a.e.b.v.d * 2) + j);
                } catch (Exception e) {
                }
                if (lVar != null && !lVar.e()) {
                    lVar.b(new l.c<d>(this) {
                        final /* synthetic */ a a;

                        {
                            this.a = r1;
                        }

                        public int a(d dVar) {
                            if (this.a.b || this.a.e) {
                                return 1;
                            }
                            if (!dVar.i()) {
                                this.a.a.e.b.u.a(dVar, 0, 0, null, true, this.a.a.e.b);
                            }
                            if (dVar.j()) {
                                return 0;
                            }
                            if (!dVar.b()) {
                                dVar.a(this.a.a.e.c, true);
                            }
                            if (dVar.c()) {
                                return 0;
                            }
                            dVar.b(this.a.a.e.c, true);
                            return 0;
                        }
                    });
                }
            }

            private long b(boolean z) {
                f();
                final long j = this.a.e.o.a - 30;
                long e = j + (this.a.e.b.v.d * ((long) this.a.h));
                if (e < this.a.e.h.a) {
                    return 0;
                }
                final long a = master.flame.danmaku.danmaku.c.b.a();
                l lVar = null;
                int i = 0;
                Object obj = null;
                while (true) {
                    l a2;
                    d c;
                    try {
                        a2 = this.a.e.d.a(j, e);
                    } catch (Exception e2) {
                        obj = 1;
                        master.flame.danmaku.danmaku.c.b.a(10);
                        a2 = lVar;
                    }
                    i++;
                    if (i < 3 && a2 == null && r2 != null) {
                        lVar = a2;
                    } else if (a2 != null) {
                        this.a.e.o.a(e);
                        return 0;
                    } else {
                        c = a2.c();
                        final d d = a2.d();
                        if (c != null || d == null) {
                            this.a.e.o.a(e);
                            return 0;
                        }
                        long s = c.s() - this.a.e.h.a;
                        if (s < 0) {
                            s = 30;
                        } else {
                            s = ((s * 10) / this.a.e.b.v.d) + 30;
                        }
                        long min = Math.min(100, s);
                        if (z) {
                            min = 0;
                        }
                        d dVar = null;
                        final int a3 = a2.a();
                        final boolean z2 = z;
                        a2.b(new l.c<d>(this) {
                            int a = 0;
                            int b = 0;
                            final /* synthetic */ a i;

                            public int a(d dVar) {
                                if (this.i.b || this.i.e || d.s() < this.i.a.e.h.a) {
                                    return 1;
                                }
                                n d = dVar.d();
                                if (d != null && d.a() != null) {
                                    return 0;
                                }
                                if (!z2 && (dVar.f() || !dVar.g())) {
                                    return 0;
                                }
                                if (!dVar.i()) {
                                    this.i.a.e.b.u.a(dVar, this.a, a3, null, true, this.i.a.e.b);
                                }
                                if (dVar.o == (byte) 0 && dVar.j()) {
                                    return 0;
                                }
                                if (dVar.o() == 1) {
                                    int s = (int) ((dVar.s() - j) / this.i.a.e.b.v.d);
                                    if (this.b == s) {
                                        this.a++;
                                    } else {
                                        this.a = 0;
                                        this.b = s;
                                    }
                                }
                                if (!(z2 || this.i.c)) {
                                    try {
                                        synchronized (this.i.a.e.p) {
                                            this.i.a.e.p.wait(min);
                                        }
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                        return 1;
                                    }
                                }
                                this.i.a(dVar, false);
                                if (!z2) {
                                    long a = master.flame.danmaku.danmaku.c.b.a() - a;
                                    master.flame.danmaku.danmaku.model.android.d dVar2 = this.i.a.e.b.v;
                                    if (a >= 3800 * ((long) this.i.a.h)) {
                                        return 1;
                                    }
                                }
                                return 0;
                            }
                        });
                        s = master.flame.danmaku.danmaku.c.b.a() - a;
                        if (dVar != null) {
                            this.a.e.o.a(dVar.r());
                            return s;
                        }
                        this.a.e.o.a(e);
                        return s;
                    }
                }
                if (a2 != null) {
                    c = a2.c();
                    final d d2 = a2.d();
                    if (c != null) {
                    }
                    this.a.e.o.a(e);
                    return 0;
                }
                this.a.e.o.a(e);
                return 0;
            }

            public boolean a(d dVar) {
                c a;
                if (!dVar.b()) {
                    dVar.a(this.a.e.c, true);
                }
                try {
                    try {
                        a = master.flame.danmaku.danmaku.c.a.a(dVar, this.a.e.c, (master.flame.danmaku.danmaku.model.android.f) this.a.d.a(), this.a.e.b.w.d);
                        dVar.x = a;
                        return true;
                    } catch (OutOfMemoryError e) {
                        if (a != null) {
                            this.a.d.a(a);
                        }
                        dVar.x = null;
                        return false;
                    } catch (Exception e2) {
                        if (a != null) {
                            this.a.d.a(a);
                        }
                        dVar.x = null;
                        return false;
                    }
                } catch (OutOfMemoryError e3) {
                    a = null;
                    if (a != null) {
                        this.a.d.a(a);
                    }
                    dVar.x = null;
                    return false;
                } catch (Exception e4) {
                    a = null;
                    if (a != null) {
                        this.a.d.a(a);
                    }
                    dVar.x = null;
                    return false;
                }
            }

            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            private byte a(master.flame.danmaku.danmaku.model.d r7, boolean r8) {
                /*
                r6 = this;
                r3 = 0;
                r2 = 1;
                r1 = 0;
                r0 = r7.b();
                if (r0 != 0) goto L_0x0012;
            L_0x0009:
                r0 = r6.a;
                r0 = r0.e;
                r0 = r0.c;
                r7.a(r0, r2);
            L_0x0012:
                r0 = r6.a;	 Catch:{ OutOfMemoryError -> 0x0110, Exception -> 0x0107 }
                r4 = 1;
                r5 = r6.a;	 Catch:{ OutOfMemoryError -> 0x0110, Exception -> 0x0107 }
                r5 = r5.e;	 Catch:{ OutOfMemoryError -> 0x0110, Exception -> 0x0107 }
                r5 = r5.b;	 Catch:{ OutOfMemoryError -> 0x0110, Exception -> 0x0107 }
                r5 = r5.w;	 Catch:{ OutOfMemoryError -> 0x0110, Exception -> 0x0107 }
                r5 = r5.i;	 Catch:{ OutOfMemoryError -> 0x0110, Exception -> 0x0107 }
                r0 = r0.a(r7, r4, r5);	 Catch:{ OutOfMemoryError -> 0x0110, Exception -> 0x0107 }
                if (r0 == 0) goto L_0x002a;
            L_0x0025:
                r0 = r0.x;	 Catch:{ OutOfMemoryError -> 0x0110, Exception -> 0x0107 }
                r0 = (master.flame.danmaku.danmaku.model.android.f) r0;	 Catch:{ OutOfMemoryError -> 0x0110, Exception -> 0x0107 }
                r3 = r0;
            L_0x002a:
                if (r3 == 0) goto L_0x003f;
            L_0x002c:
                r3.k();	 Catch:{ OutOfMemoryError -> 0x00c1, Exception -> 0x0107 }
                r7.x = r3;	 Catch:{ OutOfMemoryError -> 0x00c1, Exception -> 0x0107 }
                r0 = r6.a;	 Catch:{ OutOfMemoryError -> 0x00c1, Exception -> 0x0107 }
                r0 = r0.e;	 Catch:{ OutOfMemoryError -> 0x00c1, Exception -> 0x0107 }
                r0 = r0.n;	 Catch:{ OutOfMemoryError -> 0x00c1, Exception -> 0x0107 }
                r4 = 0;
                r0.a(r7, r4, r8);	 Catch:{ OutOfMemoryError -> 0x00c1, Exception -> 0x0107 }
                r2 = r1;
            L_0x003e:
                return r2;
            L_0x003f:
                r0 = r6.a;	 Catch:{ OutOfMemoryError -> 0x00c1, Exception -> 0x0107 }
                r4 = 0;
                r5 = r6.a;	 Catch:{ OutOfMemoryError -> 0x00c1, Exception -> 0x0107 }
                r5 = r5.e;	 Catch:{ OutOfMemoryError -> 0x00c1, Exception -> 0x0107 }
                r5 = r5.b;	 Catch:{ OutOfMemoryError -> 0x00c1, Exception -> 0x0107 }
                r5 = r5.w;	 Catch:{ OutOfMemoryError -> 0x00c1, Exception -> 0x0107 }
                r5 = r5.j;	 Catch:{ OutOfMemoryError -> 0x00c1, Exception -> 0x0107 }
                r4 = r0.a(r7, r4, r5);	 Catch:{ OutOfMemoryError -> 0x00c1, Exception -> 0x0107 }
                if (r4 == 0) goto L_0x0057;
            L_0x0052:
                r0 = r4.x;	 Catch:{ OutOfMemoryError -> 0x00c1, Exception -> 0x0107 }
                r0 = (master.flame.danmaku.danmaku.model.android.f) r0;	 Catch:{ OutOfMemoryError -> 0x00c1, Exception -> 0x0107 }
                r3 = r0;
            L_0x0057:
                if (r3 == 0) goto L_0x0080;
            L_0x0059:
                r0 = 0;
                r4.x = r0;	 Catch:{ OutOfMemoryError -> 0x00c1, Exception -> 0x0107 }
                r0 = r6.a;	 Catch:{ OutOfMemoryError -> 0x00c1, Exception -> 0x0107 }
                r0 = r0.e;	 Catch:{ OutOfMemoryError -> 0x00c1, Exception -> 0x0107 }
                r0 = r0.c;	 Catch:{ OutOfMemoryError -> 0x00c1, Exception -> 0x0107 }
                r4 = r6.a;	 Catch:{ OutOfMemoryError -> 0x00c1, Exception -> 0x0107 }
                r4 = r4.e;	 Catch:{ OutOfMemoryError -> 0x00c1, Exception -> 0x0107 }
                r4 = r4.b;	 Catch:{ OutOfMemoryError -> 0x00c1, Exception -> 0x0107 }
                r4 = r4.w;	 Catch:{ OutOfMemoryError -> 0x00c1, Exception -> 0x0107 }
                r4 = r4.d;	 Catch:{ OutOfMemoryError -> 0x00c1, Exception -> 0x0107 }
                r0 = master.flame.danmaku.danmaku.c.a.a(r7, r0, r3, r4);	 Catch:{ OutOfMemoryError -> 0x00c1, Exception -> 0x0107 }
                r7.x = r0;	 Catch:{ OutOfMemoryError -> 0x0113, Exception -> 0x010d }
                r3 = r6.a;	 Catch:{ OutOfMemoryError -> 0x0113, Exception -> 0x010d }
                r3 = r3.e;	 Catch:{ OutOfMemoryError -> 0x0113, Exception -> 0x010d }
                r3 = r3.n;	 Catch:{ OutOfMemoryError -> 0x0113, Exception -> 0x010d }
                r4 = 0;
                r3.a(r7, r4, r8);	 Catch:{ OutOfMemoryError -> 0x0113, Exception -> 0x010d }
                r2 = r1;
                goto L_0x003e;
            L_0x0080:
                r0 = r7.p;	 Catch:{ OutOfMemoryError -> 0x00c1, Exception -> 0x0107 }
                r0 = (int) r0;	 Catch:{ OutOfMemoryError -> 0x00c1, Exception -> 0x0107 }
                r4 = r7.q;	 Catch:{ OutOfMemoryError -> 0x00c1, Exception -> 0x0107 }
                r4 = (int) r4;	 Catch:{ OutOfMemoryError -> 0x00c1, Exception -> 0x0107 }
                r5 = r6.a;	 Catch:{ OutOfMemoryError -> 0x00c1, Exception -> 0x0107 }
                r5 = r5.e;	 Catch:{ OutOfMemoryError -> 0x00c1, Exception -> 0x0107 }
                r5 = r5.b;	 Catch:{ OutOfMemoryError -> 0x00c1, Exception -> 0x0107 }
                r5 = r5.w;	 Catch:{ OutOfMemoryError -> 0x00c1, Exception -> 0x0107 }
                r5 = r5.d;	 Catch:{ OutOfMemoryError -> 0x00c1, Exception -> 0x0107 }
                r5 = r5 / 8;
                r0 = master.flame.danmaku.danmaku.c.a.a(r0, r4, r5);	 Catch:{ OutOfMemoryError -> 0x00c1, Exception -> 0x0107 }
                r4 = r0 * 2;
                r5 = r6.a;	 Catch:{ OutOfMemoryError -> 0x00c1, Exception -> 0x0107 }
                r5 = r5.e;	 Catch:{ OutOfMemoryError -> 0x00c1, Exception -> 0x0107 }
                r5 = r5.m;	 Catch:{ OutOfMemoryError -> 0x00c1, Exception -> 0x0107 }
                if (r4 > r5) goto L_0x003e;
            L_0x00a2:
                if (r8 != 0) goto L_0x00c8;
            L_0x00a4:
                r4 = r6.a;	 Catch:{ OutOfMemoryError -> 0x00c1, Exception -> 0x0107 }
                r4 = r4.g;	 Catch:{ OutOfMemoryError -> 0x00c1, Exception -> 0x0107 }
                r4 = r4 + r0;
                r5 = r6.a;	 Catch:{ OutOfMemoryError -> 0x00c1, Exception -> 0x0107 }
                r5 = r5.f;	 Catch:{ OutOfMemoryError -> 0x00c1, Exception -> 0x0107 }
                if (r4 <= r5) goto L_0x00c8;
            L_0x00b3:
                r1 = r6.a;	 Catch:{ OutOfMemoryError -> 0x00c1, Exception -> 0x0107 }
                r1 = r1.e;	 Catch:{ OutOfMemoryError -> 0x00c1, Exception -> 0x0107 }
                r1 = r1.n;	 Catch:{ OutOfMemoryError -> 0x00c1, Exception -> 0x0107 }
                r4 = 0;
                r1.a(r0, r4);	 Catch:{ OutOfMemoryError -> 0x00c1, Exception -> 0x0107 }
                goto L_0x003e;
            L_0x00c1:
                r0 = move-exception;
                r0 = r3;
            L_0x00c3:
                r6.a(r7, r0);
                goto L_0x003e;
            L_0x00c8:
                r0 = r6.a;	 Catch:{ OutOfMemoryError -> 0x00c1, Exception -> 0x0107 }
                r0 = r0.d;	 Catch:{ OutOfMemoryError -> 0x00c1, Exception -> 0x0107 }
                r0 = r0.a();	 Catch:{ OutOfMemoryError -> 0x00c1, Exception -> 0x0107 }
                r0 = (master.flame.danmaku.danmaku.model.android.f) r0;	 Catch:{ OutOfMemoryError -> 0x00c1, Exception -> 0x0107 }
                r3 = r6.a;	 Catch:{ OutOfMemoryError -> 0x0113, Exception -> 0x010d }
                r3 = r3.e;	 Catch:{ OutOfMemoryError -> 0x0113, Exception -> 0x010d }
                r3 = r3.c;	 Catch:{ OutOfMemoryError -> 0x0113, Exception -> 0x010d }
                r4 = r6.a;	 Catch:{ OutOfMemoryError -> 0x0113, Exception -> 0x010d }
                r4 = r4.e;	 Catch:{ OutOfMemoryError -> 0x0113, Exception -> 0x010d }
                r4 = r4.b;	 Catch:{ OutOfMemoryError -> 0x0113, Exception -> 0x010d }
                r4 = r4.w;	 Catch:{ OutOfMemoryError -> 0x0113, Exception -> 0x010d }
                r4 = r4.d;	 Catch:{ OutOfMemoryError -> 0x0113, Exception -> 0x010d }
                r0 = master.flame.danmaku.danmaku.c.a.a(r7, r3, r0, r4);	 Catch:{ OutOfMemoryError -> 0x0113, Exception -> 0x010d }
                r7.x = r0;	 Catch:{ OutOfMemoryError -> 0x0113, Exception -> 0x010d }
                r3 = r6.a;	 Catch:{ OutOfMemoryError -> 0x0113, Exception -> 0x010d }
                r3 = r3.e;	 Catch:{ OutOfMemoryError -> 0x0113, Exception -> 0x010d }
                r3 = r3.n;	 Catch:{ OutOfMemoryError -> 0x0113, Exception -> 0x010d }
                r4 = r6.a;	 Catch:{ OutOfMemoryError -> 0x0113, Exception -> 0x010d }
                r4 = r4.b(r7);	 Catch:{ OutOfMemoryError -> 0x0113, Exception -> 0x010d }
                r3 = r3.a(r7, r4, r8);	 Catch:{ OutOfMemoryError -> 0x0113, Exception -> 0x010d }
                if (r3 != 0) goto L_0x00ff;
            L_0x00fc:
                r6.a(r7, r0);	 Catch:{ OutOfMemoryError -> 0x0113, Exception -> 0x010d }
            L_0x00ff:
                if (r3 == 0) goto L_0x0105;
            L_0x0101:
                r0 = r1;
            L_0x0102:
                r2 = r0;
                goto L_0x003e;
            L_0x0105:
                r0 = r2;
                goto L_0x0102;
            L_0x0107:
                r0 = move-exception;
            L_0x0108:
                r6.a(r7, r3);
                goto L_0x003e;
            L_0x010d:
                r1 = move-exception;
                r3 = r0;
                goto L_0x0108;
            L_0x0110:
                r0 = move-exception;
                r0 = r3;
                goto L_0x00c3;
            L_0x0113:
                r1 = move-exception;
                goto L_0x00c3;
                */
                throw new UnsupportedOperationException("Method not decompiled: master.flame.danmaku.a.a.a.a.a(master.flame.danmaku.danmaku.model.d, boolean):byte");
            }

            private final void b(d dVar) {
                if (!dVar.f()) {
                    if (dVar.s() > this.a.e.o.a + this.a.e.b.v.d && !dVar.y) {
                        return;
                    }
                    if (dVar.o != (byte) 0 || !dVar.j()) {
                        n d = dVar.d();
                        if (d == null || d.a() == null) {
                            a(dVar, true);
                        }
                    }
                }
            }

            public void b() {
                sendEmptyMessage(1);
                sendEmptyMessageDelayed(4, this.a.e.b.v.d);
            }

            public void c() {
                this.b = true;
                removeCallbacksAndMessages(null);
                sendEmptyMessage(6);
            }

            public void d() {
                sendEmptyMessage(18);
                this.b = false;
                removeMessages(16);
                sendEmptyMessage(16);
                sendEmptyMessageDelayed(4, this.a.e.b.v.d);
            }

            public void a(long j) {
                removeMessages(3);
                this.d = true;
                sendEmptyMessage(18);
                this.a.e.o.a(this.a.e.h.a + j);
                sendEmptyMessage(3);
            }

            public void a(boolean z) {
                this.c = !z;
            }
        }

        public a(a aVar, int i, int i2) {
            this.e = aVar;
            this.f = i;
            this.h = i2;
        }

        public void a(long j) {
            if (this.i != null) {
                this.i.a();
                this.i.removeMessages(3);
                this.i.obtainMessage(5, Long.valueOf(j)).sendToTarget();
            }
        }

        public void a(d dVar) {
            if (this.i == null) {
                return;
            }
            if (!dVar.y || !dVar.z) {
                this.i.obtainMessage(2, dVar).sendToTarget();
            } else if (!dVar.f()) {
                this.i.a(dVar);
            }
        }

        public void a() {
            this.j = false;
            if (this.a == null) {
                this.a = new HandlerThread("DFM Cache-Building Thread");
                this.a.start();
            }
            if (this.i == null) {
                this.i = new a(this, this.a.getLooper());
            }
            this.i.b();
        }

        public void b() {
            this.j = true;
            synchronized (this.e.p) {
                this.e.p.notifyAll();
            }
            if (this.i != null) {
                this.i.c();
                this.i = null;
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
            if (this.i != null) {
                this.i.d();
            } else {
                a();
            }
        }

        public void a(int i) {
            boolean z = true;
            if (this.i != null) {
                a aVar = this.i;
                if (i != 1) {
                    z = false;
                }
                aVar.a(z);
            }
        }

        public float d() {
            if (this.f == 0) {
                return 0.0f;
            }
            return ((float) this.g) / ((float) this.f);
        }

        private void i() {
            if (this.b != null) {
                this.b.b(new l.c<d>(this) {
                    final /* synthetic */ a a;

                    {
                        this.a = r1;
                    }

                    public int a(d dVar) {
                        this.a.a(true, dVar, null);
                        return 0;
                    }
                });
                this.b.b();
            }
            this.g = 0;
        }

        private void j() {
            if (this.b != null) {
                this.b.b(new l.c<d>(this) {
                    final /* synthetic */ a a;

                    {
                        this.a = r1;
                    }

                    public int a(d dVar) {
                        if (!dVar.g()) {
                            return 0;
                        }
                        this.a.a(true, dVar, null);
                        return 2;
                    }
                });
            }
        }

        protected void a(boolean z, d dVar, d dVar2) {
            n d = dVar.d();
            if (d != null) {
                long c = c(dVar);
                if (dVar.f()) {
                    this.e.b.b().d().b(dVar);
                }
                if (c > 0) {
                    this.g = (int) (((long) this.g) - c);
                    this.d.a((master.flame.danmaku.danmaku.model.android.f) d);
                }
            }
        }

        private long c(d dVar) {
            n nVar = dVar.x;
            if (nVar == null) {
                return 0;
            }
            if (nVar.f()) {
                nVar.g();
                dVar.x = null;
                return 0;
            }
            long b = (long) b(dVar);
            nVar.b();
            dVar.x = null;
            return b;
        }

        protected int b(d dVar) {
            if (dVar.x == null || dVar.x.f()) {
                return 0;
            }
            return dVar.x.c();
        }

        private void k() {
            while (true) {
                master.flame.danmaku.danmaku.model.android.f fVar = (master.flame.danmaku.danmaku.model.android.f) this.d.a();
                if (fVar != null) {
                    fVar.b();
                } else {
                    return;
                }
            }
        }

        private boolean a(d dVar, int i, boolean z) {
            if (i > 0) {
                a(i, z);
            }
            this.b.a(dVar);
            this.g += i;
            return true;
        }

        private void l() {
            this.b.b(new l.c<d>(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public int a(d dVar) {
                    if (!dVar.f()) {
                        return 1;
                    }
                    n nVar = dVar.x;
                    if (this.a.e.b.w.f == -1 && nVar != null && !nVar.f() && ((float) nVar.c()) / ((float) this.a.e.m) < this.a.e.b.w.g) {
                        return 0;
                    }
                    synchronized (this.a.e.p) {
                        try {
                            this.a.e.p.wait(30);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            return 1;
                        }
                    }
                    this.a.a(false, dVar, null);
                    return 2;
                }
            });
        }

        private d a(d dVar, boolean z, int i) {
            int i2 = 0;
            if (!z) {
                i2 = this.e.c.j() * 2;
            }
            final int i3 = i2 + this.e.b.w.h;
            final int i4 = i;
            final d dVar2 = dVar;
            final boolean z2 = z;
            l.b anonymousClass4 = new l.b<d, d>(this) {
                int a = 0;
                d b;
                final /* synthetic */ a g;

                public /* synthetic */ Object b() {
                    return a();
                }

                public d a() {
                    return this.b;
                }

                public int a(d dVar) {
                    int i = this.a;
                    this.a = i + 1;
                    if (i >= i4) {
                        return 1;
                    }
                    n d = dVar.d();
                    if (d == null || d.a() == null) {
                        return 0;
                    }
                    if (dVar.p == dVar2.p && dVar.q == dVar2.q && dVar.k == dVar2.k && dVar.m == dVar2.m && dVar.g == dVar2.g && dVar.c.equals(dVar2.c) && dVar.f == dVar2.f) {
                        this.b = dVar;
                        return 1;
                    } else if (z2) {
                        return 0;
                    } else {
                        if (!dVar.f()) {
                            return 1;
                        }
                        if (d.f()) {
                            return 0;
                        }
                        float d2 = ((float) d.d()) - dVar2.p;
                        float e = ((float) d.e()) - dVar2.q;
                        if (d2 < 0.0f || d2 > ((float) i3) || e < 0.0f || e > ((float) i3)) {
                            return 0;
                        }
                        this.b = dVar;
                        return 1;
                    }
                }
            };
            this.b.b(anonymousClass4);
            return (d) anonymousClass4.b();
        }

        private void a(int i, boolean z) {
            d c = this.b.c();
            while (this.g + i > this.f && c != null) {
                if (c.f() || c.j()) {
                    a(false, c, null);
                    this.b.b(c);
                    c = this.b.c();
                } else if (z) {
                    return;
                }
            }
        }

        public long e() {
            if (this.b == null || this.b.a() <= 0) {
                return 0;
            }
            d c = this.b.c();
            if (c == null) {
                return 0;
            }
            return c.s();
        }

        public void b(long j) {
            if (this.i != null) {
                this.i.a(j);
            }
        }

        public void f() {
            if (this.i != null) {
                this.i.removeMessages(3);
                this.i.removeMessages(18);
                this.i.a();
                this.i.removeMessages(7);
                this.i.sendEmptyMessage(7);
            }
        }

        public void g() {
            if (this.i != null) {
                this.i.removeMessages(9);
                this.i.sendEmptyMessage(9);
            }
        }

        public void h() {
            if (this.i != null) {
                this.i.removeMessages(4);
                this.i.sendEmptyMessage(4);
            }
        }

        public void a(Runnable runnable) {
            if (this.i != null) {
                this.i.post(runnable);
            }
        }
    }

    public a(f fVar, DanmakuContext danmakuContext, master.flame.danmaku.a.h.a aVar) {
        super(fVar, danmakuContext, aVar);
        NativeBitmapFactory.a();
        this.m = (int) Math.max(4194304.0f, ((float) Runtime.getRuntime().maxMemory()) * danmakuContext.w.e);
        this.n = new a(this, this.m, 3);
        this.g.a(this.n);
    }

    protected void a(f fVar) {
        this.h = fVar;
        this.o = new f();
        this.o.a(fVar.a);
    }

    public void a(d dVar) {
        super.a(dVar);
        if (this.n != null) {
            this.n.a(dVar);
        }
    }

    public void a(boolean z) {
        super.a(z);
        if (this.n != null) {
            this.n.f();
        }
    }

    protected void b(d dVar) {
        super.b(dVar);
        if (this.n != null) {
            int i = this.q + 1;
            this.q = i;
            if (i > 5) {
                this.n.h();
                this.q = 0;
                return;
            }
            return;
        }
        n d = dVar.d();
        if (d != null) {
            if (d.f()) {
                d.g();
            } else {
                d.b();
            }
            dVar.x = null;
        }
    }

    public master.flame.danmaku.danmaku.b.a.b a(master.flame.danmaku.danmaku.model.b bVar) {
        master.flame.danmaku.danmaku.b.a.b a = super.a(bVar);
        synchronized (this.p) {
            this.p.notify();
        }
        if (!(a == null || this.n == null || a.k - a.l >= -20)) {
            this.n.h();
            this.n.b(-this.b.v.d);
        }
        return a;
    }

    public void a(long j) {
        super.a(j);
        if (this.n == null) {
            a();
        }
        this.n.a(j);
    }

    public void a() {
        super.a();
        NativeBitmapFactory.a();
        if (this.n == null) {
            this.n = new a(this, this.m, 3);
            this.n.a();
            this.g.a(this.n);
            return;
        }
        this.n.c();
    }

    public void b() {
        super.b();
        d();
        this.g.a(null);
        if (this.n != null) {
            this.n.b();
            this.n = null;
        }
        NativeBitmapFactory.b();
    }

    public void c() {
        if (a || this.e != null) {
            a(this.e);
            this.n.a();
            return;
        }
        throw new AssertionError();
    }

    public void a(int i) {
        super.a(i);
        if (this.n != null) {
            this.n.a(i);
        }
    }

    public void a(long j, long j2, long j3) {
        super.a(j, j2, j3);
        if (this.n != null) {
            this.n.a(j2);
        }
    }

    public boolean a(DanmakuContext danmakuContext, DanmakuConfigTag danmakuConfigTag, Object... objArr) {
        if (!super.b(danmakuContext, danmakuConfigTag, objArr)) {
            if (DanmakuConfigTag.SCROLL_SPEED_FACTOR.equals(danmakuConfigTag)) {
                this.c.b(this.b.c);
                e();
            } else if (danmakuConfigTag.isVisibilityRelatedTag()) {
                if (objArr != null && objArr.length > 0 && objArr[0] != null && ((!(objArr[0] instanceof Boolean) || ((Boolean) objArr[0]).booleanValue()) && this.n != null)) {
                    this.n.b(0);
                }
                e();
            } else if (DanmakuConfigTag.TRANSPARENCY.equals(danmakuConfigTag) || DanmakuConfigTag.SCALE_TEXTSIZE.equals(danmakuConfigTag) || DanmakuConfigTag.DANMAKU_STYLE.equals(danmakuConfigTag)) {
                if (DanmakuConfigTag.SCALE_TEXTSIZE.equals(danmakuConfigTag)) {
                    this.c.b(this.b.c);
                }
                if (this.n != null) {
                    this.n.f();
                    this.n.b(-this.b.v.d);
                }
            } else if (this.n != null) {
                this.n.g();
                this.n.b(0);
            }
        }
        if (!(this.f == null || this.n == null)) {
            this.n.a(new Runnable(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.f.c();
                }
            });
        }
        return true;
    }
}
