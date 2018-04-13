package master.flame.danmaku.a;

import android.graphics.Canvas;
import master.flame.danmaku.danmaku.a.a;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.DanmakuContext.DanmakuConfigTag;
import master.flame.danmaku.danmaku.model.b;
import master.flame.danmaku.danmaku.model.d;
import master.flame.danmaku.danmaku.model.f;
import master.flame.danmaku.danmaku.model.l;
import master.flame.danmaku.danmaku.model.l.c;

public class e implements h {
    static final /* synthetic */ boolean l = (!e.class.desiredAssertionStatus());
    private l a = new master.flame.danmaku.danmaku.model.android.e(4);
    protected final DanmakuContext b;
    protected final b c;
    protected l d;
    protected a e;
    h.a f;
    final master.flame.danmaku.danmaku.b.a g;
    f h;
    protected boolean i;
    protected boolean j;
    protected int k;
    private long m = 0;
    private final master.flame.danmaku.danmaku.b.a.b n = new master.flame.danmaku.danmaku.b.a.b();
    private long o;
    private long p;
    private boolean q;
    private d r;
    private master.flame.danmaku.danmaku.model.android.e s = new master.flame.danmaku.danmaku.model.android.e(4);
    private l t;
    private DanmakuContext.a u = new DanmakuContext.a(this) {
        final /* synthetic */ e a;

        {
            this.a = r1;
        }

        public boolean a(DanmakuContext danmakuContext, DanmakuConfigTag danmakuConfigTag, Object... objArr) {
            return this.a.a(danmakuContext, danmakuConfigTag, objArr);
        }
    };

    public e(f fVar, DanmakuContext danmakuContext, h.a aVar) {
        if (danmakuContext == null) {
            throw new IllegalArgumentException("context is null");
        }
        this.b = danmakuContext;
        this.c = danmakuContext.b();
        this.f = aVar;
        this.g = new master.flame.danmaku.danmaku.b.a.a(danmakuContext);
        this.g.a(new master.flame.danmaku.danmaku.b.a.a(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void a(d dVar) {
                if (this.a.f != null) {
                    this.a.f.b(dVar);
                }
            }
        });
        master.flame.danmaku.danmaku.b.a aVar2 = this.g;
        boolean z = this.b.f() || this.b.e();
        aVar2.a(z);
        a(fVar);
        Boolean valueOf = Boolean.valueOf(this.b.c());
        if (valueOf == null) {
            return;
        }
        if (valueOf.booleanValue()) {
            this.b.u.a("1017_Filter");
        } else {
            this.b.u.b("1017_Filter");
        }
    }

    protected void a(f fVar) {
        this.h = fVar;
    }

    public synchronized void a(d dVar) {
        if (this.d != null) {
            boolean a;
            if (dVar.y) {
                this.s.a(dVar);
                b(10);
            }
            dVar.s = this.d.a();
            boolean z = true;
            if (this.o <= dVar.s() && dVar.s() <= this.p) {
                synchronized (this.a) {
                    z = this.a.a(dVar);
                }
            } else if (dVar.y) {
                z = false;
            }
            synchronized (this.d) {
                a = this.d.a(dVar);
            }
            if (!(z && a)) {
                this.p = 0;
                this.o = 0;
            }
            if (a && this.f != null) {
                this.f.a(dVar);
            }
            if (this.r == null || !(dVar == null || this.r == null || dVar.s() <= this.r.s())) {
                this.r = dVar;
            }
        }
    }

    public synchronized void a(boolean z) {
        if (!(this.d == null || this.d.e())) {
            synchronized (this.d) {
                if (!z) {
                    l a = this.d.a((this.h.a - this.b.v.d) - 100, this.h.a + this.b.v.d);
                    if (a != null) {
                        this.a = a;
                    }
                }
                this.d.b();
            }
        }
    }

    protected void b(d dVar) {
    }

    protected synchronized void b(final int i) {
        if (!(this.d == null || this.d.e() || this.s.e())) {
            this.s.a(new c<d>(this) {
                long a = master.flame.danmaku.danmaku.c.b.a();
                final /* synthetic */ e c;

                public int a(d dVar) {
                    boolean f = dVar.f();
                    if (master.flame.danmaku.danmaku.c.b.a() - this.a > ((long) i) || !f) {
                        return 1;
                    }
                    this.c.d.b(dVar);
                    this.c.b(dVar);
                    return 2;
                }
            });
        }
    }

    public l b(long j) {
        long j2 = (j - this.b.v.d) - 100;
        long j3 = j + this.b.v.d;
        l lVar = null;
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (i >= 3) {
                break;
            }
            try {
                lVar = this.d.a(j2, j3);
                break;
            } catch (Exception e) {
                i = i2;
            }
        }
        final l eVar = new master.flame.danmaku.danmaku.model.android.e();
        if (!(lVar == null || lVar.e())) {
            lVar.a(new c<d>(this) {
                final /* synthetic */ e b;

                public int a(d dVar) {
                    if (dVar.e() && !dVar.g()) {
                        eVar.a(dVar);
                    }
                    return 0;
                }
            });
        }
        return eVar;
    }

    public synchronized master.flame.danmaku.danmaku.b.a.b a(b bVar) {
        return a(bVar, this.h);
    }

    public void d() {
        if (this.a != null) {
            this.a = new master.flame.danmaku.danmaku.model.android.e();
        }
        if (this.g != null) {
            this.g.a();
        }
    }

    public void a(long j) {
        d();
        this.b.t.b();
        this.b.t.e();
        this.b.t.f();
        this.b.t.g();
        this.t = new master.flame.danmaku.danmaku.model.android.e(4);
        if (j < 1000) {
            j = 0;
        }
        this.m = j;
        this.n.a();
        this.n.o = this.m;
        this.p = 0;
        this.o = 0;
        if (this.d != null) {
            d d = this.d.d();
            if (d != null && !d.f()) {
                this.r = d;
            }
        }
    }

    public void c(long j) {
        d();
        this.b.t.b();
        this.b.t.e();
        this.m = j;
    }

    public void a() {
        this.b.a(this.u);
    }

    public void b() {
        this.b.g();
        if (this.g != null) {
            this.g.c();
        }
    }

    public void c() {
        if (l || this.e != null) {
            a(this.e);
            this.p = 0;
            this.o = 0;
            if (this.f != null) {
                this.f.a();
                this.j = true;
                return;
            }
            return;
        }
        throw new AssertionError();
    }

    public void a(int i) {
        this.k = i;
    }

    protected void a(a aVar) {
        this.d = aVar.a(this.b).a(this.c).a(this.h).a(new a.a(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }
        }).c();
        this.b.t.a();
        if (this.d != null) {
            this.r = this.d.d();
        }
    }

    public void b(a aVar) {
        this.e = aVar;
        this.j = false;
    }

    protected master.flame.danmaku.danmaku.b.a.b a(b bVar, f fVar) {
        if (this.i) {
            this.g.b();
            this.i = false;
        }
        if (this.d == null) {
            return null;
        }
        d.a((Canvas) bVar.a());
        if (this.q) {
            return this.n;
        }
        long j;
        long j2;
        master.flame.danmaku.danmaku.b.a.b bVar2 = this.n;
        long j3 = (fVar.a - this.b.v.d) - 100;
        long j4 = this.b.v.d + fVar.a;
        l lVar = this.a;
        if (this.o > j3 || fVar.a > this.p) {
            lVar = this.d.b(j3, j4);
            if (lVar != null) {
                this.a = lVar;
            }
            this.o = j3;
            this.p = j4;
            j = j4;
            j2 = j3;
        } else {
            j3 = this.o;
            j = this.p;
            j2 = j3;
        }
        l lVar2 = this.t;
        a(bVar2, lVar2, lVar);
        if (!(lVar2 == null || lVar2.e())) {
            this.n.a = true;
            this.g.a(bVar, lVar2, 0, this.n);
        }
        this.n.a = false;
        if (lVar == null || lVar.e()) {
            bVar2.p = true;
            bVar2.n = j2;
            bVar2.o = j;
            return bVar2;
        }
        this.g.a(this.c, lVar, this.m, bVar2);
        a(bVar2);
        if (bVar2.p) {
            if (this.r != null && this.r.f()) {
                this.r = null;
                if (this.f != null) {
                    this.f.b();
                }
            }
            if (bVar2.n == -1) {
                bVar2.n = j2;
            }
            if (bVar2.o == -1) {
                bVar2.o = j;
            }
        }
        return bVar2;
    }

    public void e() {
        this.p = 0;
        this.o = 0;
        this.q = false;
    }

    public void f() {
        this.i = true;
    }

    public void a(long j, long j2, final long j3) {
        l b = this.n.b();
        this.t = b;
        b.a(new c<d>(this) {
            final /* synthetic */ e b;

            public int a(d dVar) {
                if (dVar.g()) {
                    return 2;
                }
                dVar.c(j3 + dVar.b);
                if (dVar.b != 0) {
                    return 0;
                }
                return 2;
            }
        });
        this.m = j2;
    }

    public boolean a(DanmakuContext danmakuContext, DanmakuConfigTag danmakuConfigTag, Object... objArr) {
        boolean b = b(danmakuContext, danmakuConfigTag, objArr);
        if (this.f != null) {
            this.f.c();
        }
        return b;
    }

    protected boolean b(DanmakuContext danmakuContext, DanmakuConfigTag danmakuConfigTag, Object[] objArr) {
        boolean z = false;
        if (danmakuConfigTag == null || DanmakuConfigTag.MAXIMUM_NUMS_IN_SCREEN.equals(danmakuConfigTag)) {
            return true;
        }
        Boolean bool;
        if (DanmakuConfigTag.DUPLICATE_MERGING_ENABLED.equals(danmakuConfigTag)) {
            boolean z2;
            bool = (Boolean) objArr[0];
            if (bool != null) {
                if (bool.booleanValue()) {
                    this.b.u.a("1017_Filter");
                } else {
                    this.b.u.b("1017_Filter");
                }
                z2 = true;
            } else {
                z2 = false;
            }
            return z2;
        } else if (DanmakuConfigTag.SCALE_TEXTSIZE.equals(danmakuConfigTag) || DanmakuConfigTag.SCROLL_SPEED_FACTOR.equals(danmakuConfigTag) || DanmakuConfigTag.DANMAKU_MARGIN.equals(danmakuConfigTag)) {
            f();
            return false;
        } else if (!DanmakuConfigTag.MAXIMUN_LINES.equals(danmakuConfigTag) && !DanmakuConfigTag.OVERLAPPING_ENABLE.equals(danmakuConfigTag)) {
            if (DanmakuConfigTag.ALIGN_BOTTOM.equals(danmakuConfigTag)) {
                bool = (Boolean) objArr[0];
                if (bool != null) {
                    if (this.g == null) {
                        return true;
                    }
                    this.g.b(bool.booleanValue());
                    return true;
                }
            }
            return false;
        } else if (this.g == null) {
            return true;
        } else {
            master.flame.danmaku.danmaku.b.a aVar = this.g;
            if (this.b.f() || this.b.e()) {
                z = true;
            }
            aVar.a(z);
            return true;
        }
    }

    public void g() {
        this.q = true;
    }

    private void a(master.flame.danmaku.danmaku.b.a.b bVar, l lVar, l lVar2) {
        int a;
        int i = 0;
        bVar.a();
        bVar.b.a(master.flame.danmaku.danmaku.c.b.a());
        bVar.c = 0;
        if (lVar != null) {
            a = lVar.a();
        } else {
            a = 0;
        }
        if (lVar2 != null) {
            i = lVar2.a();
        }
        bVar.d = i + a;
    }

    private void a(master.flame.danmaku.danmaku.b.a.b bVar) {
        long s;
        bVar.p = bVar.k == 0;
        if (bVar.p) {
            bVar.n = -1;
        }
        d dVar = bVar.e;
        bVar.e = null;
        if (dVar != null) {
            s = dVar.s();
        } else {
            s = -1;
        }
        bVar.o = s;
        bVar.m = bVar.b.a(master.flame.danmaku.danmaku.c.b.a());
    }
}
