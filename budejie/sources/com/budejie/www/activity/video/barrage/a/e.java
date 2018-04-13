package com.budejie.www.activity.video.barrage.a;

import android.graphics.Canvas;
import com.budejie.www.activity.video.barrage.danmaku.model.a;
import com.budejie.www.activity.video.barrage.danmaku.model.android.DanmakuContext;
import com.budejie.www.activity.video.barrage.danmaku.model.android.DanmakuContext.DanmakuConfigTag;
import com.budejie.www.activity.video.barrage.danmaku.model.android.c;
import com.budejie.www.activity.video.barrage.danmaku.model.j;
import com.budejie.www.activity.video.barrage.danmaku.model.k;

public class e implements h {
    static final /* synthetic */ boolean k = (!e.class.desiredAssertionStatus());
    private k a = new c(4);
    protected final DanmakuContext b;
    protected final a c;
    protected k d;
    protected com.budejie.www.activity.video.barrage.danmaku.a.a e;
    h.a f;
    com.budejie.www.activity.video.barrage.danmaku.b.a g;
    com.budejie.www.activity.video.barrage.danmaku.model.e h;
    protected boolean i;
    protected boolean j;
    private long l = 0;
    private com.budejie.www.activity.video.barrage.danmaku.b.a.a m = new com.budejie.www.activity.video.barrage.danmaku.b.a.a();
    private long n;
    private long o;
    private boolean p;
    private DanmakuContext.a q = new DanmakuContext.a(this) {
        final /* synthetic */ e a;

        {
            this.a = r1;
        }

        public boolean a(DanmakuContext danmakuContext, DanmakuConfigTag danmakuConfigTag, Object... objArr) {
            return this.a.a(danmakuContext, danmakuConfigTag, objArr);
        }
    };

    public e(com.budejie.www.activity.video.barrage.danmaku.model.e eVar, DanmakuContext danmakuContext, h.a aVar) {
        if (danmakuContext == null) {
            throw new IllegalArgumentException("context is null");
        }
        this.b = danmakuContext;
        this.c = danmakuContext.b();
        this.f = aVar;
        this.g = new com.budejie.www.activity.video.barrage.danmaku.b.a.a(danmakuContext);
        com.budejie.www.activity.video.barrage.danmaku.b.a aVar2 = this.g;
        boolean z = this.b.e() || this.b.d();
        aVar2.a(z);
        a(eVar);
        Boolean valueOf = Boolean.valueOf(this.b.c());
        if (valueOf == null) {
            return;
        }
        if (valueOf.booleanValue()) {
            this.b.s.a("1017_Filter");
        } else {
            this.b.s.b("1017_Filter");
        }
    }

    protected void a(com.budejie.www.activity.video.barrage.danmaku.model.e eVar) {
        this.h = eVar;
    }

    public synchronized void a(com.budejie.www.activity.video.barrage.danmaku.model.c cVar) {
        if (this.d != null) {
            boolean a;
            if (cVar.t) {
                a(10);
            }
            cVar.q = this.d.a();
            if (this.n <= cVar.b && cVar.b <= this.o) {
                synchronized (this.a) {
                    this.a.a(cVar);
                }
            } else if (cVar.t) {
                this.o = 0;
                this.n = 0;
            }
            synchronized (this.d) {
                a = this.d.a(cVar);
            }
            if (a) {
                if (this.f != null) {
                    this.f.a(cVar);
                }
            }
        }
    }

    public synchronized void e() {
        if (!(this.d == null || this.d.f())) {
            this.d.b();
        }
    }

    protected void b(com.budejie.www.activity.video.barrage.danmaku.model.c cVar) {
    }

    protected synchronized void a(int i) {
        if (this.d != null && !this.d.f()) {
            long currentTimeMillis = System.currentTimeMillis();
            j e = this.d.e();
            while (e.b()) {
                com.budejie.www.activity.video.barrage.danmaku.model.c a = e.a();
                boolean e2 = a.e();
                if (e2 && a.t) {
                    e.c();
                    b(a);
                }
                if (e2) {
                    if (System.currentTimeMillis() - currentTimeMillis > ((long) i)) {
                        break;
                    }
                }
                break;
            }
        }
    }

    public k b(long j) {
        k b = this.d.b((j - this.b.t.d) - 100, this.b.t.d + j);
        k cVar = new c();
        if (!(b == null || b.f())) {
            j e = b.e();
            while (e.b()) {
                com.budejie.www.activity.video.barrage.danmaku.model.c a = e.a();
                if (a.d() && !a.f()) {
                    cVar.a(a);
                }
            }
        }
        return cVar;
    }

    public synchronized com.budejie.www.activity.video.barrage.danmaku.b.a.a a(a aVar) {
        return a(aVar, this.h);
    }

    public void a() {
        if (this.a != null) {
            this.a.b();
        }
        if (this.g != null) {
            this.g.a();
        }
    }

    public void a(long j) {
        a();
        this.b.r.b();
        if (j < 1000) {
            j = 0;
        }
        this.l = j;
    }

    public void c(long j) {
        a();
        this.b.r.b();
        this.l = j;
    }

    public void b() {
        this.b.a(this.q);
    }

    public void c() {
        this.b.f();
        if (this.g != null) {
            this.g.c();
        }
    }

    public void d() {
        if (k || this.e != null) {
            a(this.e);
            if (this.f != null) {
                this.f.a();
                this.j = true;
                return;
            }
            return;
        }
        throw new AssertionError();
    }

    protected void a(com.budejie.www.activity.video.barrage.danmaku.a.a aVar) {
        this.d = aVar.a(this.b).a(this.c).a(this.h).c();
        if (!(this.d == null || this.d.f() || this.d.c().B != null)) {
            j e = this.d.e();
            while (e.b()) {
                com.budejie.www.activity.video.barrage.danmaku.model.c a = e.a();
                if (a != null) {
                    a.B = this.b.r;
                }
            }
        }
        this.b.r.a();
    }

    public void b(com.budejie.www.activity.video.barrage.danmaku.a.a aVar) {
        this.e = aVar;
        this.j = false;
    }

    protected com.budejie.www.activity.video.barrage.danmaku.b.a.a a(a aVar, com.budejie.www.activity.video.barrage.danmaku.model.e eVar) {
        if (this.i) {
            this.g.b();
            this.i = false;
        }
        if (this.d == null) {
            return null;
        }
        d.a((Canvas) aVar.a());
        if (this.p) {
            return this.m;
        }
        long j = (eVar.a - this.b.t.d) - 100;
        long j2 = eVar.a + this.b.t.d;
        if (this.n > j || eVar.a > this.o) {
            k b = this.d.b(j, j2);
            if (b != null) {
                this.a = b;
            } else {
                this.a.b();
            }
            this.n = j;
            this.o = j2;
        } else {
            j = this.n;
            j2 = this.o;
        }
        if (this.a == null || this.a.f()) {
            this.m.k = true;
            this.m.i = j;
            this.m.j = j2;
            return this.m;
        }
        com.budejie.www.activity.video.barrage.danmaku.b.a.a a = this.g.a(this.c, this.a, this.l);
        this.m = a;
        if (a.k) {
            if (a.i == -1) {
                a.i = j;
            }
            if (a.j == -1) {
                a.j = j2;
            }
        }
        return a;
    }

    public void f() {
        this.o = 0;
        this.n = 0;
        this.p = false;
    }

    public void g() {
        this.i = true;
    }

    public boolean a(DanmakuContext danmakuContext, DanmakuConfigTag danmakuConfigTag, Object... objArr) {
        boolean b = b(danmakuContext, danmakuConfigTag, objArr);
        if (this.f != null) {
            this.f.b();
        }
        return b;
    }

    protected boolean b(DanmakuContext danmakuContext, DanmakuConfigTag danmakuConfigTag, Object[] objArr) {
        boolean z = false;
        if (danmakuConfigTag == null || DanmakuConfigTag.MAXIMUM_NUMS_IN_SCREEN.equals(danmakuConfigTag)) {
            return true;
        }
        if (DanmakuConfigTag.DUPLICATE_MERGING_ENABLED.equals(danmakuConfigTag)) {
            boolean z2;
            Boolean bool = (Boolean) objArr[0];
            if (bool != null) {
                if (bool.booleanValue()) {
                    this.b.s.a("1017_Filter");
                } else {
                    this.b.s.b("1017_Filter");
                }
                z2 = true;
            } else {
                z2 = false;
            }
            return z2;
        } else if (DanmakuConfigTag.SCALE_TEXTSIZE.equals(danmakuConfigTag) || DanmakuConfigTag.SCROLL_SPEED_FACTOR.equals(danmakuConfigTag)) {
            g();
            return false;
        } else if (!DanmakuConfigTag.MAXIMUN_LINES.equals(danmakuConfigTag) && !DanmakuConfigTag.OVERLAPPING_ENABLE.equals(danmakuConfigTag)) {
            return false;
        } else {
            if (this.g != null) {
                com.budejie.www.activity.video.barrage.danmaku.b.a aVar = this.g;
                if (this.b.e() || this.b.d()) {
                    z = true;
                }
                aVar.a(z);
            }
            return true;
        }
    }

    public void h() {
        this.p = true;
    }
}
