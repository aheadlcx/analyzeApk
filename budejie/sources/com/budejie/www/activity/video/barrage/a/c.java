package com.budejie.www.activity.video.barrage.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.video.barrage.danmaku.model.android.DanmakuContext;
import com.budejie.www.activity.video.barrage.danmaku.model.e;
import com.budejie.www.activity.video.barrage.danmaku.model.k;
import com.umeng.analytics.MobclickAgent;
import java.util.LinkedList;
import tv.cjump.jni.DeviceUtils;

public class c extends Handler {
    private boolean A;
    private boolean B;
    public h a;
    private DanmakuContext b;
    private long c = 0;
    private boolean d = true;
    private long e;
    private boolean f;
    private a g;
    private e h = new e();
    private com.budejie.www.activity.video.barrage.danmaku.a.a i;
    private g j;
    private boolean k = true;
    private com.budejie.www.activity.video.barrage.danmaku.model.a l;
    private final com.budejie.www.activity.video.barrage.danmaku.b.a.a m = new com.budejie.www.activity.video.barrage.danmaku.b.a.a();
    @SuppressLint({"Deprecated"})
    private int n;
    private LinkedList<Long> o = new LinkedList();
    private i p;
    private final boolean q;
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

        void a(e eVar);
    }

    public c(Looper looper, g gVar, boolean z) {
        boolean z2 = true;
        super(looper);
        this.q = Runtime.getRuntime().availableProcessors() > 3;
        if (DeviceUtils.f()) {
            z2 = false;
        }
        this.B = z2;
        a(gVar);
        if (z) {
            a(null);
        } else {
            a(false);
        }
        this.k = z;
    }

    private void a(g gVar) {
        this.j = gVar;
    }

    public void a(DanmakuContext danmakuContext) {
        this.b = danmakuContext;
    }

    public void a(com.budejie.www.activity.video.barrage.danmaku.a.a aVar) {
        this.i = aVar;
    }

    public void a(a aVar) {
        this.g = aVar;
    }

    public void a() {
        sendEmptyMessage(6);
    }

    public boolean b() {
        return this.d;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void handleMessage(android.os.Message r10) {
        /*
        r9 = this;
        r8 = 6;
        r7 = 2;
        r6 = 3;
        r5 = 1;
        r4 = 0;
        r1 = r10.what;
        switch(r1) {
            case 1: goto L_0x0027;
            case 2: goto L_0x00ae;
            case 3: goto L_0x0033;
            case 4: goto L_0x006f;
            case 5: goto L_0x000b;
            case 6: goto L_0x0156;
            case 7: goto L_0x0153;
            case 8: goto L_0x00da;
            case 9: goto L_0x0121;
            case 10: goto L_0x00bc;
            case 11: goto L_0x019d;
            case 12: goto L_0x01a2;
            case 13: goto L_0x01b9;
            default: goto L_0x000a;
        };
    L_0x000a:
        return;
    L_0x000b:
        r0 = r9.i;
        if (r0 == 0) goto L_0x0017;
    L_0x000f:
        r0 = r9.j;
        r0 = r0.f();
        if (r0 != 0) goto L_0x001e;
    L_0x0017:
        r0 = 5;
        r2 = 100;
        r9.sendEmptyMessageDelayed(r0, r2);
        goto L_0x000a;
    L_0x001e:
        r0 = new com.budejie.www.activity.video.barrage.a.c$1;
        r0.<init>(r9);
        r9.a(r0);
        goto L_0x000a;
    L_0x0027:
        r0 = r10.obj;
        r0 = (java.lang.Long) r0;
        if (r0 == 0) goto L_0x0064;
    L_0x002d:
        r0 = r0.longValue();
        r9.c = r0;
    L_0x0033:
        r9.d = r4;
        r0 = r9.f;
        if (r0 == 0) goto L_0x0069;
    L_0x0039:
        r0 = r9.m;
        r0.a();
        r0 = r9.o;
        r0.clear();
        r0 = java.lang.System.currentTimeMillis();
        r2 = r9.c;
        r0 = r0 - r2;
        r9.e = r0;
        r0 = r9.h;
        r2 = r9.c;
        r0.a(r2);
        r9.removeMessages(r6);
        r9.sendEmptyMessage(r7);
        r0 = r9.a;
        r0.b();
        r9.n();
        r9.w = r4;
        goto L_0x000a;
    L_0x0064:
        r0 = 0;
        r9.c = r0;
        goto L_0x0033;
    L_0x0069:
        r0 = 100;
        r9.sendEmptyMessageDelayed(r6, r0);
        goto L_0x000a;
    L_0x006f:
        r9.d = r5;
        r9.i();
        r0 = r10.obj;
        r0 = (java.lang.Long) r0;
        r0 = r0.longValue();
        r2 = r9.h;
        r2 = r2.a;
        r0 = r0 - r2;
        r2 = r9.e;
        r0 = r2 - r0;
        r9.e = r0;
        r0 = r9.h;
        r2 = java.lang.System.currentTimeMillis();
        r4 = r9.e;
        r2 = r2 - r4;
        r0.a(r2);
        r0 = r9.a;
        if (r0 == 0) goto L_0x00a0;
    L_0x0097:
        r0 = r9.a;
        r1 = r9.h;
        r2 = r1.a;
        r0.a(r2);
    L_0x00a0:
        r0 = r9.h;
        r0 = r0.a;
        r9.c = r0;
        r9.removeMessages(r6);
        r9.sendEmptyMessage(r6);
        goto L_0x000a;
    L_0x00ae:
        r0 = r9.q;
        if (r0 == 0) goto L_0x00b7;
    L_0x00b2:
        r9.k();
        goto L_0x000a;
    L_0x00b7:
        r9.j();
        goto L_0x000a;
    L_0x00bc:
        r0 = r9.b;
        r0 = r0.t;
        r1 = r9.b;
        r0.a(r1);
        r0 = r10.obj;
        r0 = (java.lang.Boolean) r0;
        if (r0 == 0) goto L_0x000a;
    L_0x00cb:
        r0 = r0.booleanValue();
        if (r0 == 0) goto L_0x000a;
    L_0x00d1:
        r0 = r9.b;
        r0 = r0.r;
        r0.c();
        goto L_0x000a;
    L_0x00da:
        r0 = r10.obj;
        r0 = (java.lang.Long) r0;
        r1 = r9.a;
        if (r1 == 0) goto L_0x00f2;
    L_0x00e2:
        if (r0 != 0) goto L_0x0106;
    L_0x00e4:
        r0 = r9.h;
        r2 = r9.g();
        r0.a(r2);
        r0 = r9.a;
        r0.f();
    L_0x00f2:
        r9.k = r5;
        r0 = r9.d;
        if (r0 == 0) goto L_0x0101;
    L_0x00f8:
        r0 = r9.j;
        if (r0 == 0) goto L_0x0101;
    L_0x00fc:
        r0 = r9.j;
        r0.g();
    L_0x0101:
        r9.n();
        goto L_0x000a;
    L_0x0106:
        r1 = r9.a;
        r1.b();
        r1 = r9.a;
        r2 = r0.longValue();
        r1.a(r2);
        r1 = r9.a;
        r1.f();
        r0 = r9.obtainMessage(r5, r0);
        r0.sendToTarget();
        goto L_0x00f2;
    L_0x0121:
        r9.k = r4;
        r0 = r9.j;
        if (r0 == 0) goto L_0x012c;
    L_0x0127:
        r0 = r9.j;
        r0.h();
    L_0x012c:
        r0 = r9.a;
        if (r0 == 0) goto L_0x013a;
    L_0x0130:
        r0 = r9.a;
        r0.f();
        r0 = r9.a;
        r0.h();
    L_0x013a:
        r0 = r10.obj;
        r0 = (java.lang.Boolean) r0;
        r2 = r0.booleanValue();
        if (r2 == 0) goto L_0x014d;
    L_0x0144:
        r2 = r9.a;
        if (r2 == 0) goto L_0x014d;
    L_0x0148:
        r2 = r9.a;
        r2.c();
    L_0x014d:
        r0 = r0.booleanValue();
        if (r0 == 0) goto L_0x000a;
    L_0x0153:
        r9.removeMessages(r7);
    L_0x0156:
        if (r1 != r8) goto L_0x015c;
    L_0x0158:
        r0 = 0;
        r9.removeCallbacksAndMessages(r0);
    L_0x015c:
        r9.d = r5;
        r9.l();
        r9.n = r4;
        r0 = r9.p;
        if (r0 == 0) goto L_0x016d;
    L_0x0167:
        r9.n();
        r9.i();
    L_0x016d:
        r0 = r9.h;
        r2 = r0.a;
        r9.c = r2;
        if (r1 != r8) goto L_0x0198;
    L_0x0175:
        r0 = r9.a;
        if (r0 == 0) goto L_0x017e;
    L_0x0179:
        r0 = r9.a;
        r0.c();
    L_0x017e:
        r0 = r9.i;
        if (r0 == 0) goto L_0x0187;
    L_0x0182:
        r0 = r9.i;
        r0.f();
    L_0x0187:
        r0 = r9.getLooper();
        r1 = android.os.Looper.getMainLooper();
        if (r0 == r1) goto L_0x0198;
    L_0x0191:
        r0 = r9.getLooper();
        r0.quit();
    L_0x0198:
        r0 = 0;
        r9.j = r0;
        goto L_0x000a;
    L_0x019d:
        r9.n();
        goto L_0x000a;
    L_0x01a2:
        r0 = r9.d;
        if (r0 == 0) goto L_0x000a;
    L_0x01a6:
        r0 = r9.j;
        if (r0 == 0) goto L_0x000a;
    L_0x01aa:
        r0 = r9.a;
        r0.f();
        r0 = r9.j;
        r0.g();
        r9.n();
        goto L_0x000a;
    L_0x01b9:
        r0 = r9.a;
        if (r0 == 0) goto L_0x000a;
    L_0x01bd:
        r0 = r9.a;
        r2 = r9.g();
        r0.c(r2);
        goto L_0x000a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.budejie.www.activity.video.barrage.a.c.handleMessage(android.os.Message):void");
    }

    private void i() {
        if (this.p != null) {
            synchronized (this.a) {
                this.a.notifyAll();
            }
            this.p.a();
            try {
                this.p.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.p = null;
        }
    }

    private void j() {
        if (!this.d) {
            long a = a(System.currentTimeMillis());
            if (a < 0) {
                removeMessages(2);
                sendEmptyMessageDelayed(2, 60 - a);
                return;
            }
            a = this.j.g();
            removeMessages(2);
            if (this.k) {
                if (this.m.k && this.B) {
                    long j = this.m.j - this.h.a;
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

    private void k() {
        if (this.p == null) {
            this.p = new i(this, "DFM Update") {
                final /* synthetic */ c a;

                public void run() {
                    long currentTimeMillis = System.currentTimeMillis();
                    while (!b() && !this.a.d) {
                        long currentTimeMillis2 = System.currentTimeMillis();
                        if (this.a.t - (System.currentTimeMillis() - currentTimeMillis) > 1) {
                            SystemClock.sleep(1);
                        } else {
                            currentTimeMillis = this.a.a(currentTimeMillis2);
                            if (currentTimeMillis < 0) {
                                SystemClock.sleep(60 - currentTimeMillis);
                                currentTimeMillis = currentTimeMillis2;
                            } else {
                                this.a.j.g();
                                if (!this.a.k) {
                                    this.a.b(10000000);
                                } else if (this.a.m.k && this.a.B) {
                                    currentTimeMillis = this.a.m.j - this.a.h.a;
                                    if (currentTimeMillis > 500) {
                                        this.a.n();
                                        this.a.b(currentTimeMillis - 10);
                                    }
                                }
                                currentTimeMillis = currentTimeMillis2;
                            }
                        }
                    }
                }
            };
            this.p.start();
        }
    }

    private final long a(long j) {
        long j2 = 0;
        if (this.w || this.z) {
            return 0;
        }
        this.z = true;
        long j3 = j - this.e;
        if (!this.k || this.m.k || this.A) {
            this.h.a(j3);
            this.y = 0;
            j3 = 0;
        } else {
            j3 -= this.h.a;
            long max = Math.max(this.t, o());
            if (j3 <= 2000 && this.m.h <= this.r && max <= this.r) {
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
            this.h.b(j3);
        }
        if (this.g != null) {
            this.g.a(this.h);
        }
        this.z = false;
        return j3;
    }

    private void l() {
        if (this.A) {
            a(System.currentTimeMillis());
        }
    }

    private void m() {
        this.r = Math.max(33, (long) (((float) 16) * 2.5f));
        this.s = this.r * 2;
        this.t = Math.max(16, (16 / 15) * 15);
        this.u = this.t + 3;
    }

    private void a(final Runnable runnable) {
        if (this.a != null) {
            this.a.c();
        }
        this.a = a(this.j.i(), this.h, this.j.getContext().getApplicationContext(), this.j.getWidth(), this.j.getHeight(), this.j.isHardwareAccelerated(), new com.budejie.www.activity.video.barrage.a.h.a(this) {
            final /* synthetic */ c b;

            public void a() {
                this.b.m();
                try {
                    runnable.run();
                } catch (InternalError e) {
                    MobclickAgent.onEvent(BudejieApplication.b().getApplicationContext(), "E07_A02", "InternalError DrawHandler prepare()");
                }
            }

            public void a(com.budejie.www.activity.video.barrage.danmaku.model.c cVar) {
                this.b.obtainMessage(11).sendToTarget();
            }

            public void b() {
                if (this.b.d && this.b.k) {
                    this.b.obtainMessage(12).sendToTarget();
                }
            }
        });
    }

    private h a(boolean z, e eVar, Context context, int i, int i2, boolean z2, com.budejie.www.activity.video.barrage.a.h.a aVar) {
        this.l = this.b.b();
        this.l.a(i, i2);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.l.a(displayMetrics.density, displayMetrics.densityDpi, displayMetrics.scaledDensity);
        this.l.b(this.b.c);
        this.l.a(z2);
        h aVar2 = z ? new a(eVar, this.b, aVar, (1048576 * com.budejie.www.activity.video.barrage.danmaku.c.a.a(context)) / 3) : new e(eVar, this.b, aVar);
        aVar2.b(this.i);
        aVar2.d();
        obtainMessage(10, Boolean.valueOf(false)).sendToTarget();
        return aVar2;
    }

    public void a(com.budejie.www.activity.video.barrage.danmaku.model.c cVar) {
        if (this.a != null) {
            cVar.B = this.b.r;
            cVar.a(this.h);
            this.a.a(cVar);
            obtainMessage(11).sendToTarget();
        }
    }

    public void c() {
        sendEmptyMessage(3);
    }

    public void d() {
        sendEmptyMessage(5);
    }

    public void a(Long l) {
        if (!this.k) {
            removeMessages(8);
            removeMessages(9);
            obtainMessage(8, l).sendToTarget();
        }
    }

    public long a(boolean z) {
        if (!this.k) {
            return this.h.a;
        }
        removeMessages(8);
        removeMessages(9);
        obtainMessage(9, Boolean.valueOf(z)).sendToTarget();
        return this.h.a;
    }

    public com.budejie.www.activity.video.barrage.danmaku.b.a.a a(Canvas canvas) {
        if (this.a == null) {
            return this.m;
        }
        this.l.a((Object) canvas);
        this.m.a(this.a.a(this.l));
        p();
        return this.m;
    }

    private void n() {
        if (this.A) {
            if (this.a != null) {
                this.a.f();
            }
            this.n = 0;
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
        this.m.l = System.currentTimeMillis();
        this.A = true;
        if (this.q) {
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
        } else if (j == 10000000) {
            removeMessages(11);
            removeMessages(2);
        } else {
            removeMessages(11);
            removeMessages(2);
            sendEmptyMessageDelayed(11, j);
        }
    }

    private synchronized long o() {
        long j;
        int size = this.o.size();
        if (size <= 0 || com.budejie.www.goddubbing.c.a.a(this.o)) {
            j = 0;
        } else {
            j = (((Long) this.o.getLast()).longValue() - ((Long) this.o.getFirst()).longValue()) / ((long) size);
        }
        return j;
    }

    private synchronized void p() {
        this.o.addLast(Long.valueOf(System.currentTimeMillis()));
        if (this.o.size() > 500) {
            this.o.removeFirst();
        }
    }

    public void a(int i, int i2) {
        if (this.l != null) {
            if (this.l.d() != i || this.l.e() != i2) {
                this.l.a(i, i2);
                obtainMessage(10, Boolean.valueOf(true)).sendToTarget();
            }
        }
    }

    public void e() {
        if (this.a != null) {
            this.a.e();
        }
    }

    public k f() {
        if (this.a != null) {
            return this.a.b(g());
        }
        return null;
    }

    public long g() {
        if (this.w) {
            return this.x;
        }
        if (this.d || !this.A) {
            return this.h.a - this.y;
        }
        return System.currentTimeMillis() - this.e;
    }

    public DanmakuContext h() {
        return this.b;
    }
}
