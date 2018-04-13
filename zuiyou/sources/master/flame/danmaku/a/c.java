package master.flame.danmaku.a;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.Choreographer;
import android.view.Choreographer.FrameCallback;
import java.util.LinkedList;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.d;
import master.flame.danmaku.danmaku.model.f;
import master.flame.danmaku.danmaku.model.l;
import tv.cjump.jni.DeviceUtils;

public class c extends Handler {
    private boolean A;
    private boolean B;
    public h a;
    private DanmakuContext b;
    private b c;
    private long d = 0;
    private boolean e = true;
    private long f;
    private boolean g;
    private a h;
    private f i = new f();
    private master.flame.danmaku.danmaku.a.a j;
    private g k;
    private boolean l = true;
    private master.flame.danmaku.danmaku.model.b m;
    private final master.flame.danmaku.danmaku.b.a.b n = new master.flame.danmaku.danmaku.b.a.b();
    private LinkedList<Long> o = new LinkedList();
    private i p;
    private boolean q;
    private long r = 30;
    private long s = 60;
    private long t = 16;
    private long u;
    private long v;
    private boolean w;
    private long x;
    private long y;
    private boolean z;

    public interface a {
        void a();

        void a(d dVar);

        void a(f fVar);

        void b();
    }

    @TargetApi(16)
    private class b implements FrameCallback {
        final /* synthetic */ c a;

        private b(c cVar) {
            this.a = cVar;
        }

        public void doFrame(long j) {
            this.a.sendEmptyMessage(2);
        }
    }

    public c(Looper looper, g gVar, boolean z) {
        boolean z2 = true;
        super(looper);
        if (DeviceUtils.f()) {
            z2 = false;
        }
        this.B = z2;
        a(gVar);
        if (z) {
            b(null);
        } else {
            a(false);
        }
        this.l = z;
    }

    private void a(g gVar) {
        this.k = gVar;
    }

    public void a(DanmakuContext danmakuContext) {
        this.b = danmakuContext;
    }

    public void a(master.flame.danmaku.danmaku.a.a aVar) {
        this.j = aVar;
    }

    public void a(a aVar) {
        this.h = aVar;
    }

    public void a() {
        this.e = true;
        sendEmptyMessage(6);
    }

    public boolean b() {
        return this.e;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void handleMessage(android.os.Message r11) {
        /*
        r10 = this;
        r4 = 6;
        r9 = 3;
        r8 = 2;
        r1 = 0;
        r2 = 1;
        r3 = r11.what;
        switch(r3) {
            case 1: goto L_0x005a;
            case 2: goto L_0x0106;
            case 3: goto L_0x00a4;
            case 4: goto L_0x0066;
            case 5: goto L_0x000b;
            case 6: goto L_0x0192;
            case 7: goto L_0x0183;
            case 8: goto L_0x002d;
            case 9: goto L_0x0151;
            case 10: goto L_0x0127;
            case 11: goto L_0x01d4;
            case 12: goto L_0x01d9;
            case 13: goto L_0x01f0;
            default: goto L_0x000a;
        };
    L_0x000a:
        return;
    L_0x000b:
        r0 = master.flame.danmaku.danmaku.c.b.a();
        r10.f = r0;
        r0 = r10.j;
        if (r0 == 0) goto L_0x001d;
    L_0x0015:
        r0 = r10.k;
        r0 = r0.f();
        if (r0 != 0) goto L_0x0024;
    L_0x001d:
        r0 = 5;
        r2 = 100;
        r10.sendEmptyMessageDelayed(r0, r2);
        goto L_0x000a;
    L_0x0024:
        r0 = new master.flame.danmaku.a.c$1;
        r0.<init>(r10);
        r10.a(r0);
        goto L_0x000a;
    L_0x002d:
        r10.l = r2;
        r0 = r11.obj;
        r0 = (java.lang.Long) r0;
        r4 = r10.a;
        if (r4 == 0) goto L_0x01ff;
    L_0x0037:
        if (r0 != 0) goto L_0x00e3;
    L_0x0039:
        r0 = r10.i;
        r4 = r10.h();
        r0.a(r4);
        r0 = r10.a;
        r0.e();
        r0 = r1;
    L_0x0048:
        r4 = r10.e;
        if (r4 == 0) goto L_0x0055;
    L_0x004c:
        r4 = r10.k;
        if (r4 == 0) goto L_0x0055;
    L_0x0050:
        r4 = r10.k;
        r4.g();
    L_0x0055:
        r10.r();
        if (r0 == 0) goto L_0x000a;
    L_0x005a:
        r0 = r11.obj;
        r0 = (java.lang.Long) r0;
        if (r0 == 0) goto L_0x00f9;
    L_0x0060:
        r4 = r0.longValue();
        r10.d = r4;
    L_0x0066:
        r0 = 4;
        if (r3 != r0) goto L_0x00a4;
    L_0x0069:
        r10.e = r2;
        r10.k();
        r0 = r11.obj;
        r0 = (java.lang.Long) r0;
        r4 = r0.longValue();
        r3 = r10.i;
        r6 = r3.a;
        r4 = r4 - r6;
        r6 = r10.f;
        r4 = r6 - r4;
        r10.f = r4;
        r3 = r10.i;
        r4 = r0.longValue();
        r3.a(r4);
        r3 = r10.b;
        r3 = r3.t;
        r3.c();
        r3 = r10.a;
        if (r3 == 0) goto L_0x009e;
    L_0x0095:
        r3 = r10.a;
        r4 = r0.longValue();
        r3.a(r4);
    L_0x009e:
        r4 = r0.longValue();
        r10.d = r4;
    L_0x00a4:
        r0 = 7;
        r10.removeMessages(r0);
        r10.e = r1;
        r0 = r10.g;
        if (r0 == 0) goto L_0x00ff;
    L_0x00ae:
        r0 = r10.n;
        r0.a();
        r0 = r10.o;
        r0.clear();
        r4 = master.flame.danmaku.danmaku.c.b.a();
        r6 = r10.d;
        r4 = r4 - r6;
        r10.f = r4;
        r0 = r10.i;
        r4 = r10.d;
        r0.a(r4);
        r10.removeMessages(r9);
        r10.sendEmptyMessage(r8);
        r0 = r10.a;
        r0.a();
        r10.r();
        r10.w = r1;
        r0 = r10.a;
        if (r0 == 0) goto L_0x000a;
    L_0x00dc:
        r0 = r10.a;
        r0.a(r2);
        goto L_0x000a;
    L_0x00e3:
        r4 = r10.a;
        r4.a();
        r4 = r10.a;
        r6 = r0.longValue();
        r4.a(r6);
        r0 = r10.a;
        r0.e();
        r0 = r2;
        goto L_0x0048;
    L_0x00f9:
        r4 = 0;
        r10.d = r4;
        goto L_0x0066;
    L_0x00ff:
        r0 = 100;
        r10.sendEmptyMessageDelayed(r9, r0);
        goto L_0x000a;
    L_0x0106:
        r0 = r10.b;
        r0 = r0.x;
        if (r0 != 0) goto L_0x0111;
    L_0x010c:
        r10.n();
        goto L_0x000a;
    L_0x0111:
        r0 = r10.b;
        r0 = r0.x;
        if (r0 != r2) goto L_0x011c;
    L_0x0117:
        r10.m();
        goto L_0x000a;
    L_0x011c:
        r0 = r10.b;
        r0 = r0.x;
        if (r0 != r8) goto L_0x000a;
    L_0x0122:
        r10.l();
        goto L_0x000a;
    L_0x0127:
        r0 = r10.b;
        r0 = r0.v;
        r1 = r10.b;
        r0.a(r1);
        r0 = r11.obj;
        r0 = (java.lang.Boolean) r0;
        if (r0 == 0) goto L_0x000a;
    L_0x0136:
        r0 = r0.booleanValue();
        if (r0 == 0) goto L_0x000a;
    L_0x013c:
        r0 = r10.b;
        r0 = r0.t;
        r0.c();
        r0 = r10.b;
        r0 = r0.t;
        r0.b();
        r0 = r10.a;
        r0.f();
        goto L_0x000a;
    L_0x0151:
        r10.l = r1;
        r0 = r10.k;
        if (r0 == 0) goto L_0x015c;
    L_0x0157:
        r0 = r10.k;
        r0.h();
    L_0x015c:
        r0 = r10.a;
        if (r0 == 0) goto L_0x016a;
    L_0x0160:
        r0 = r10.a;
        r0.e();
        r0 = r10.a;
        r0.g();
    L_0x016a:
        r0 = r11.obj;
        r0 = (java.lang.Boolean) r0;
        r1 = r0.booleanValue();
        if (r1 == 0) goto L_0x017d;
    L_0x0174:
        r1 = r10.a;
        if (r1 == 0) goto L_0x017d;
    L_0x0178:
        r1 = r10.a;
        r1.b();
    L_0x017d:
        r0 = r0.booleanValue();
        if (r0 == 0) goto L_0x000a;
    L_0x0183:
        r10.removeMessages(r9);
        r10.removeMessages(r8);
        r0 = r10.a;
        if (r0 == 0) goto L_0x0192;
    L_0x018d:
        r0 = r10.a;
        r0.a(r8);
    L_0x0192:
        if (r3 != r4) goto L_0x0198;
    L_0x0194:
        r0 = 0;
        r10.removeCallbacksAndMessages(r0);
    L_0x0198:
        r10.e = r2;
        r10.o();
        r0 = r10.i;
        r0 = r0.a;
        r10.d = r0;
        r0 = r10.p;
        if (r0 == 0) goto L_0x01ad;
    L_0x01a7:
        r10.r();
        r10.k();
    L_0x01ad:
        if (r3 != r4) goto L_0x000a;
    L_0x01af:
        r0 = r10.a;
        if (r0 == 0) goto L_0x01b8;
    L_0x01b3:
        r0 = r10.a;
        r0.b();
    L_0x01b8:
        r0 = r10.j;
        if (r0 == 0) goto L_0x01c1;
    L_0x01bc:
        r0 = r10.j;
        r0.e();
    L_0x01c1:
        r0 = r10.getLooper();
        r1 = android.os.Looper.getMainLooper();
        if (r0 == r1) goto L_0x000a;
    L_0x01cb:
        r0 = r10.getLooper();
        r0.quit();
        goto L_0x000a;
    L_0x01d4:
        r10.r();
        goto L_0x000a;
    L_0x01d9:
        r0 = r10.e;
        if (r0 == 0) goto L_0x000a;
    L_0x01dd:
        r0 = r10.k;
        if (r0 == 0) goto L_0x000a;
    L_0x01e1:
        r0 = r10.a;
        r0.e();
        r0 = r10.k;
        r0.g();
        r10.r();
        goto L_0x000a;
    L_0x01f0:
        r0 = r10.a;
        if (r0 == 0) goto L_0x000a;
    L_0x01f4:
        r0 = r10.a;
        r2 = r10.h();
        r0.c(r2);
        goto L_0x000a;
    L_0x01ff:
        r0 = r1;
        goto L_0x0048;
        */
        throw new UnsupportedOperationException("Method not decompiled: master.flame.danmaku.a.c.handleMessage(android.os.Message):void");
    }

    private void k() {
        if (this.p != null) {
            i iVar = this.p;
            this.p = null;
            synchronized (this.a) {
                this.a.notifyAll();
            }
            iVar.a();
            try {
                iVar.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void l() {
        if (!this.e) {
            long a = a(master.flame.danmaku.danmaku.c.b.a());
            if (a < 0) {
                removeMessages(2);
                sendEmptyMessageDelayed(2, 60 - a);
                return;
            }
            a = this.k.g();
            removeMessages(2);
            if (a > this.s) {
                this.i.b(a);
                this.o.clear();
            }
            if (this.l) {
                if (this.n.p && this.B) {
                    long j = this.n.o - this.i.a;
                    if (j > 500) {
                        b(j - 10);
                        return;
                    }
                }
                if (a < this.t) {
                    sendEmptyMessageDelayed(2, this.t - a);
                    return;
                } else {
                    sendEmptyMessage(2);
                    return;
                }
            }
            b(10000000);
        }
    }

    private void m() {
        if (this.p == null) {
            this.p = new i(this, "DFM Update") {
                final /* synthetic */ c a;

                public void run() {
                    long a = master.flame.danmaku.danmaku.c.b.a();
                    while (!b() && !this.a.e) {
                        long a2 = master.flame.danmaku.danmaku.c.b.a();
                        if (this.a.t - (master.flame.danmaku.danmaku.c.b.a() - a) > 1) {
                            master.flame.danmaku.danmaku.c.b.a(1);
                        } else {
                            a = this.a.a(a2);
                            if (a < 0) {
                                master.flame.danmaku.danmaku.c.b.a(60 - a);
                                a = a2;
                            } else {
                                a = this.a.k.g();
                                if (a > this.a.s) {
                                    this.a.i.b(a);
                                    this.a.o.clear();
                                }
                                if (!this.a.l) {
                                    this.a.b(10000000);
                                } else if (this.a.n.p && this.a.B) {
                                    a = this.a.n.o - this.a.i.a;
                                    if (a > 500) {
                                        this.a.r();
                                        this.a.b(a - 10);
                                    }
                                }
                                a = a2;
                            }
                        }
                    }
                }
            };
            this.p.start();
        }
    }

    @TargetApi(16)
    private void n() {
        if (!this.e) {
            Choreographer.getInstance().postFrameCallback(this.c);
            if (a(master.flame.danmaku.danmaku.c.b.a()) < 0) {
                removeMessages(2);
                return;
            }
            long g = this.k.g();
            removeMessages(2);
            if (g > this.s) {
                this.i.b(g);
                this.o.clear();
            }
            if (!this.l) {
                b(10000000);
            } else if (this.n.p && this.B) {
                g = this.n.o - this.i.a;
                if (g > 500) {
                    b(g - 10);
                }
            }
        }
    }

    private final long a(long j) {
        long j2 = 0;
        if (this.w || this.z) {
            return 0;
        }
        this.z = true;
        long j3 = j - this.f;
        if (!this.l || this.n.p || this.A) {
            this.i.a(j3);
            this.y = 0;
            j3 = 0;
        } else {
            j3 -= this.i.a;
            long max = Math.max(this.t, s());
            if (j3 <= 2000 && this.n.m <= this.r && max <= this.r) {
                j2 = Math.min(this.r, Math.max(this.t, (j3 / this.t) + max));
                max = j2 - this.v;
                if (max > 3 && max < 8 && this.v >= this.t && this.v <= this.r) {
                    j2 = this.v;
                }
                j3 -= j2;
                this.v = j2;
                long j4 = j3;
                j3 = j2;
                j2 = j4;
            }
            this.y = j2;
            this.i.b(j3);
        }
        if (this.h != null) {
            this.h.a(this.i);
        }
        this.z = false;
        return j3;
    }

    private void o() {
        if (this.A) {
            a(master.flame.danmaku.danmaku.c.b.a());
        }
    }

    private void p() {
        this.r = Math.max(33, (long) (((float) 16) * 2.5f));
        this.s = (long) (((float) this.r) * 2.5f);
        this.t = Math.max(16, (16 / 15) * 15);
        this.u = this.t + 3;
    }

    private void a(final Runnable runnable) {
        if (this.a == null) {
            this.a = a(this.k.i(), this.i, this.k.getContext(), this.k.getWidth(), this.k.getHeight(), this.k.isHardwareAccelerated(), new master.flame.danmaku.a.h.a(this) {
                final /* synthetic */ c b;

                public void a() {
                    this.b.p();
                    runnable.run();
                }

                public void a(d dVar) {
                    if (!dVar.f()) {
                        long s = dVar.s() - this.b.h();
                        if (s < this.b.b.v.d && (this.b.A || this.b.n.p)) {
                            this.b.r();
                        } else if (s > 0 && s <= this.b.b.v.d) {
                            this.b.sendEmptyMessageDelayed(11, s);
                        }
                    }
                }

                public void b(d dVar) {
                    if (this.b.h != null) {
                        this.b.h.a(dVar);
                    }
                }

                public void b() {
                    if (this.b.h != null) {
                        this.b.h.b();
                    }
                }

                public void c() {
                    this.b.q();
                }
            });
        } else {
            runnable.run();
        }
    }

    public boolean c() {
        return this.g;
    }

    private h a(boolean z, f fVar, Context context, int i, int i2, boolean z2, master.flame.danmaku.a.h.a aVar) {
        this.m = this.b.b();
        this.m.a(i, i2);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.m.a(displayMetrics.density, displayMetrics.densityDpi, displayMetrics.scaledDensity);
        this.m.b(this.b.c);
        this.m.a(z2);
        h aVar2 = z ? new a(fVar, this.b, aVar) : new e(fVar, this.b, aVar);
        aVar2.b(this.j);
        aVar2.c();
        obtainMessage(10, Boolean.valueOf(false)).sendToTarget();
        return aVar2;
    }

    public void a(Long l) {
        this.w = true;
        this.x = l.longValue();
        removeMessages(2);
        removeMessages(3);
        removeMessages(4);
        obtainMessage(4, l).sendToTarget();
    }

    public void a(d dVar) {
        if (this.a != null) {
            dVar.H = this.b.t;
            dVar.a(this.i);
            this.a.a(dVar);
            obtainMessage(11).sendToTarget();
        }
    }

    public void d() {
        removeMessages(7);
        sendEmptyMessage(3);
    }

    public void e() {
        boolean z = true;
        this.g = false;
        if (VERSION.SDK_INT < 16 && this.b.x == (byte) 0) {
            this.b.x = (byte) 2;
        }
        if (this.b.x == (byte) 0) {
            this.c = new b();
        }
        if (this.b.x != (byte) 1) {
            z = false;
        }
        this.q = z;
        sendEmptyMessage(5);
    }

    public void f() {
        removeMessages(3);
        o();
        sendEmptyMessage(7);
    }

    public void b(Long l) {
        if (!this.l) {
            this.l = true;
            removeMessages(8);
            removeMessages(9);
            obtainMessage(8, l).sendToTarget();
        }
    }

    public long a(boolean z) {
        if (!this.l) {
            return this.i.a;
        }
        this.l = false;
        removeMessages(8);
        removeMessages(9);
        obtainMessage(9, Boolean.valueOf(z)).sendToTarget();
        return this.i.a;
    }

    public master.flame.danmaku.danmaku.b.a.b a(Canvas canvas) {
        if (this.a == null) {
            return this.n;
        }
        if (!this.A) {
            master.flame.danmaku.danmaku.model.a aVar = this.b.o;
            if (aVar != null) {
                boolean d = aVar.d();
                if (d || !this.e) {
                    int b = aVar.b();
                    if (b == 2) {
                        long j = this.i.a;
                        long a = aVar.a();
                        long j2 = a - j;
                        if (Math.abs(j2) > aVar.c()) {
                            if (d && this.e) {
                                d();
                            }
                            this.a.a(j, a, j2);
                            this.i.a(a);
                            this.f -= j2;
                            this.y = 0;
                        }
                    } else if (b == 1 && d && !this.e) {
                        f();
                    }
                }
            }
        }
        this.m.a((Object) canvas);
        this.n.a(this.a.a(this.m));
        t();
        return this.n;
    }

    private void q() {
        if (this.e && this.l) {
            removeMessages(12);
            sendEmptyMessageDelayed(12, 100);
        }
    }

    private void r() {
        if (this.A) {
            if (this.a != null) {
                this.a.e();
            }
            if (this.q) {
                synchronized (this) {
                    this.o.clear();
                }
                synchronized (this.a) {
                    this.a.notifyAll();
                }
            } else {
                this.o.clear();
                removeMessages(2);
                sendEmptyMessage(2);
            }
            this.A = false;
        }
    }

    private void b(long j) {
        if (!b() && c() && !this.w) {
            this.n.q = master.flame.danmaku.danmaku.c.b.a();
            this.A = true;
            if (this.q) {
                if (this.p != null) {
                    try {
                        synchronized (this.a) {
                            if (j == 10000000) {
                                this.a.wait();
                            } else {
                                this.a.wait(j);
                            }
                            sendEmptyMessage(11);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else if (j == 10000000) {
                removeMessages(11);
                removeMessages(2);
            } else {
                removeMessages(11);
                removeMessages(2);
                sendEmptyMessageDelayed(11, j);
            }
        }
    }

    private synchronized long s() {
        long j;
        int size = this.o.size();
        if (size <= 0) {
            j = 0;
        } else {
            Long l = (Long) this.o.peekFirst();
            Long l2 = (Long) this.o.peekLast();
            if (l == null || l2 == null) {
                j = 0;
            } else {
                j = (l2.longValue() - l.longValue()) / ((long) size);
            }
        }
        return j;
    }

    private synchronized void t() {
        this.o.addLast(Long.valueOf(master.flame.danmaku.danmaku.c.b.a()));
        if (this.o.size() > 500) {
            this.o.removeFirst();
        }
    }

    public void a(int i, int i2) {
        if (this.m != null) {
            if (this.m.e() != i || this.m.f() != i2) {
                this.m.a(i, i2);
                obtainMessage(10, Boolean.valueOf(true)).sendToTarget();
            }
        }
    }

    public void b(boolean z) {
        if (this.a != null) {
            this.a.a(z);
        }
    }

    public l g() {
        if (this.a != null) {
            return this.a.b(h());
        }
        return null;
    }

    public long h() {
        if (!this.g) {
            return 0;
        }
        if (this.w) {
            return this.x;
        }
        if (this.e || !this.A) {
            return this.i.a - this.y;
        }
        return master.flame.danmaku.danmaku.c.b.a() - this.f;
    }

    public void i() {
        obtainMessage(13).sendToTarget();
    }

    public DanmakuContext j() {
        return this.b;
    }
}
