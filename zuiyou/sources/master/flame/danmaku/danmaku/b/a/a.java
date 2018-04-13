package master.flame.danmaku.danmaku.b.a;

import master.flame.danmaku.danmaku.b.b;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.d;
import master.flame.danmaku.danmaku.model.f;
import master.flame.danmaku.danmaku.model.k;
import master.flame.danmaku.danmaku.model.l;
import master.flame.danmaku.danmaku.model.l.c;
import master.flame.danmaku.danmaku.model.m;
import master.flame.danmaku.danmaku.model.n;

public class a extends b {
    private f a;
    private final DanmakuContext b;
    private b.f c;
    private final b.f d = new b.f(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public boolean a(d dVar, float f, int i, boolean z) {
            if (dVar.o != (byte) 0) {
                return false;
            }
            if (!this.a.b.u.b(dVar, i, 0, this.a.a, z, this.a.b)) {
                return false;
            }
            dVar.a(false);
            return true;
        }
    };
    private final b e;
    private k f;
    private master.flame.danmaku.danmaku.b.a.a g;
    private a h = new a();

    private class a extends c<d> {
        public m a;
        public master.flame.danmaku.danmaku.b.a.b b;
        public long c;
        final /* synthetic */ a d;
        private d e;

        private a(a aVar) {
            this.d = aVar;
        }

        public int a(d dVar) {
            this.e = dVar;
            if (dVar.f()) {
                this.a.b(dVar);
                if (this.b.a) {
                    return 2;
                }
                return 0;
            } else if (!this.b.a && dVar.t()) {
                return 0;
            } else {
                if (!dVar.i()) {
                    this.d.b.u.a(dVar, this.b.c, this.b.d, this.b.b, false, this.d.b);
                }
                if (dVar.s() < this.c) {
                    return 0;
                }
                if (dVar.o == (byte) 0 && dVar.j()) {
                    return 0;
                }
                if (dVar.h()) {
                    n d = dVar.d();
                    if (this.d.f != null && (d == null || d.a() == null)) {
                        this.d.f.a(dVar);
                    }
                    return 1;
                }
                master.flame.danmaku.danmaku.b.a.b bVar;
                if (dVar.o() == 1) {
                    bVar = this.b;
                    bVar.c++;
                }
                if (!dVar.b()) {
                    dVar.a(this.a, false);
                }
                if (!dVar.c()) {
                    dVar.b(this.a, false);
                }
                this.d.e.a(dVar, this.a, this.d.c);
                if (!dVar.e()) {
                    return 0;
                }
                if (dVar.d == null && dVar.n() > ((float) this.a.f())) {
                    return 0;
                }
                int a = dVar.a(this.a);
                if (a == 1) {
                    bVar = this.b;
                    bVar.r++;
                } else if (a == 2) {
                    bVar = this.b;
                    bVar.s++;
                    if (this.d.f != null) {
                        this.d.f.a(dVar);
                    }
                }
                this.b.a(dVar.o(), 1);
                this.b.a(1);
                this.b.a(dVar);
                if (this.d.g == null || dVar.J == this.d.b.t.d) {
                    return 0;
                }
                dVar.J = this.d.b.t.d;
                this.d.g.a(dVar);
                return 0;
            }
        }

        public void d() {
            this.b.e = this.e;
            super.d();
        }
    }

    public a(DanmakuContext danmakuContext) {
        this.b = danmakuContext;
        this.e = new b(danmakuContext.d());
    }

    public void a() {
        b();
        this.b.u.a();
    }

    public void b() {
        this.e.a();
    }

    public void c() {
        this.e.b();
        this.b.u.a();
    }

    public void a(boolean z) {
        this.c = z ? this.d : null;
    }

    public void a(m mVar, l lVar, long j, master.flame.danmaku.danmaku.b.a.b bVar) {
        this.a = bVar.b;
        this.h.a = mVar;
        this.h.b = bVar;
        this.h.c = j;
        lVar.a(this.h);
    }

    public void a(k kVar) {
        this.f = kVar;
    }

    public void a(master.flame.danmaku.danmaku.b.a.a aVar) {
        this.g = aVar;
    }

    public void b(boolean z) {
        if (this.e != null) {
            this.e.a(z);
        }
    }
}
