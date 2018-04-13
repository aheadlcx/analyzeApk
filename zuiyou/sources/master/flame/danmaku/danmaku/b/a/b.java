package master.flame.danmaku.danmaku.b.a;

import master.flame.danmaku.danmaku.model.m;

public class b {
    private d a = null;
    private d b = null;
    private d c = null;
    private d d = null;

    public interface f {
        boolean a(master.flame.danmaku.danmaku.model.d dVar, float f, int i, boolean z);
    }

    public interface d {
        void a();

        void a(master.flame.danmaku.danmaku.model.d dVar, m mVar, f fVar);
    }

    private static class b implements d {
        protected master.flame.danmaku.danmaku.model.android.e c;
        protected boolean d;
        protected a e;

        protected class a extends master.flame.danmaku.danmaku.model.l.b<master.flame.danmaku.danmaku.model.d, e> {
            public m a;
            int b = 0;
            public master.flame.danmaku.danmaku.model.d c = null;
            public master.flame.danmaku.danmaku.model.d d = null;
            public master.flame.danmaku.danmaku.model.d e = null;
            public master.flame.danmaku.danmaku.model.d f = null;
            public master.flame.danmaku.danmaku.model.d g = null;
            boolean h = false;
            boolean i = false;
            boolean j = false;
            final /* synthetic */ b k;

            protected a(b bVar) {
                this.k = bVar;
            }

            public /* synthetic */ Object b() {
                return a();
            }

            public void c() {
                this.b = 0;
                this.f = null;
                this.e = null;
                this.d = null;
                this.c = null;
                this.j = false;
                this.i = false;
                this.h = false;
            }

            public int a(master.flame.danmaku.danmaku.model.d dVar) {
                if (this.k.d) {
                    return 1;
                }
                this.b++;
                if (dVar == this.g) {
                    this.c = dVar;
                    this.e = null;
                    this.i = true;
                    this.j = false;
                    return 1;
                }
                if (this.d == null) {
                    this.d = dVar;
                }
                if (this.g.q + dVar.l() > ((float) this.a.f())) {
                    this.h = true;
                    return 1;
                }
                if (this.f == null) {
                    this.f = dVar;
                } else if (this.f.m() >= dVar.m()) {
                    this.f = dVar;
                }
                this.j = master.flame.danmaku.danmaku.c.a.a(this.a, dVar, this.g, this.g.a(), this.g.p().a);
                if (this.j) {
                    this.e = dVar;
                    return 0;
                }
                this.c = dVar;
                return 1;
            }

            public e a() {
                e eVar = new e();
                eVar.a = this.b;
                eVar.c = this.d;
                eVar.b = this.c;
                eVar.d = this.e;
                eVar.e = this.f;
                eVar.g = this.h;
                eVar.h = this.i;
                eVar.i = this.j;
                return eVar;
            }
        }

        private b() {
            this.c = new master.flame.danmaku.danmaku.model.android.e(1);
            this.d = false;
            this.e = new a(this);
        }

        public void a(master.flame.danmaku.danmaku.model.d dVar, m mVar, f fVar) {
            if (!dVar.g()) {
                master.flame.danmaku.danmaku.model.d dVar2;
                boolean z;
                float n = (float) mVar.n();
                int i = 0;
                boolean e = dVar.e();
                boolean z2 = (e || this.c.e()) ? false : true;
                int m = mVar.m();
                boolean z3;
                if (e) {
                    z3 = z2;
                    dVar2 = null;
                    z = e;
                    e = false;
                } else {
                    int i2;
                    master.flame.danmaku.danmaku.model.d dVar3;
                    master.flame.danmaku.danmaku.model.d dVar4;
                    boolean z4;
                    boolean z5;
                    master.flame.danmaku.danmaku.model.d dVar5;
                    float n2;
                    int i3;
                    this.d = false;
                    master.flame.danmaku.danmaku.model.d dVar6 = null;
                    this.e.a = mVar;
                    this.e.g = dVar;
                    this.c.a(this.e);
                    e a = this.e.a();
                    if (a != null) {
                        i2 = a.a;
                        dVar6 = a.b;
                        dVar3 = a.c;
                        dVar4 = a.d;
                        dVar2 = a.e;
                        e = a.g;
                        z4 = a.h;
                        z5 = a.i;
                        dVar5 = dVar2;
                    } else {
                        z4 = e;
                        e = false;
                        dVar3 = null;
                        i2 = 0;
                        dVar5 = null;
                        dVar4 = null;
                        z5 = z2;
                    }
                    Object obj = 1;
                    if (dVar6 != null) {
                        if (dVar4 != null) {
                            n2 = dVar4.n() + ((float) m);
                        } else {
                            n2 = dVar6.l();
                        }
                        if (dVar6 != dVar) {
                            z4 = false;
                        } else {
                            dVar6 = null;
                        }
                    } else if (e && dVar5 != null) {
                        n2 = dVar5.l();
                        obj = null;
                        z4 = false;
                        dVar6 = null;
                    } else if (dVar4 != null) {
                        n2 = dVar4.n() + ((float) m);
                        z5 = false;
                        dVar6 = null;
                    } else if (dVar3 != null) {
                        n2 = dVar3.l();
                        z4 = false;
                        dVar6 = dVar3;
                    } else {
                        n2 = (float) mVar.n();
                        dVar6 = null;
                    }
                    if (obj != null) {
                        z3 = a(e, dVar, mVar, n2, dVar3, dVar4);
                    } else {
                        z3 = false;
                    }
                    if (z3) {
                        n2 = (float) mVar.n();
                        z2 = true;
                        i3 = 1;
                    } else if (dVar6 != null) {
                        i3 = i2 - 1;
                        z2 = z5;
                    } else {
                        z2 = z5;
                        i3 = i2;
                    }
                    if (n2 == ((float) mVar.n())) {
                        z = false;
                        n = n2;
                        i = i3;
                        e = z3;
                        z3 = z2;
                        dVar2 = dVar6;
                    } else {
                        z = z4;
                        n = n2;
                        i = i3;
                        e = z3;
                        z3 = z2;
                        dVar2 = dVar6;
                    }
                }
                if (fVar == null || !fVar.a(dVar, n, r6, r4)) {
                    if (e) {
                        a();
                    }
                    dVar.a(mVar, dVar.k(), n);
                    if (!z) {
                        this.c.b(dVar2);
                        this.c.a(dVar);
                    }
                }
            }
        }

        protected boolean a(boolean z, master.flame.danmaku.danmaku.model.d dVar, m mVar, float f, master.flame.danmaku.danmaku.model.d dVar2, master.flame.danmaku.danmaku.model.d dVar3) {
            if (f < ((float) mVar.n()) || ((dVar2 != null && dVar2.l() > 0.0f) || dVar.q + f > ((float) mVar.f()))) {
                return true;
            }
            return false;
        }

        public void a() {
            this.d = true;
            this.c.b();
        }
    }

    private static class c extends b {
        private c() {
            super();
        }

        protected boolean a(boolean z, master.flame.danmaku.danmaku.model.d dVar, m mVar, float f, master.flame.danmaku.danmaku.model.d dVar2, master.flame.danmaku.danmaku.model.d dVar3) {
            if (dVar.q + f > ((float) mVar.f())) {
                return true;
            }
            return false;
        }
    }

    private static class a extends c {
        protected a a;
        protected master.flame.danmaku.danmaku.model.android.e b;

        protected class a extends master.flame.danmaku.danmaku.model.l.b<master.flame.danmaku.danmaku.model.d, e> {
            public m a;
            int b = 0;
            public master.flame.danmaku.danmaku.model.d c = null;
            public master.flame.danmaku.danmaku.model.d d = null;
            public master.flame.danmaku.danmaku.model.d e = null;
            boolean f = false;
            float g;
            final /* synthetic */ a h;

            protected a(a aVar) {
                this.h = aVar;
            }

            public /* synthetic */ Object b() {
                return a();
            }

            public void c() {
                this.b = 0;
                this.d = null;
                this.c = null;
                this.f = false;
            }

            public int a(master.flame.danmaku.danmaku.model.d dVar) {
                if (this.h.d) {
                    return 1;
                }
                this.b++;
                if (dVar == this.e) {
                    this.c = null;
                    this.f = false;
                    return 1;
                }
                if (this.d == null) {
                    this.d = dVar;
                    if (this.d.n() != ((float) this.a.f())) {
                        return 1;
                    }
                }
                if (this.g < ((float) this.a.n())) {
                    this.c = null;
                    return 1;
                }
                this.f = master.flame.danmaku.danmaku.c.a.a(this.a, dVar, this.e, this.e.a(), this.e.p().a);
                if (this.f) {
                    this.g = (dVar.l() - ((float) this.a.m())) - this.e.q;
                    return 0;
                }
                this.c = dVar;
                return 1;
            }

            public e a() {
                e eVar = new e();
                eVar.a = this.b;
                eVar.c = this.d;
                eVar.f = this.c;
                eVar.i = this.f;
                return eVar;
            }
        }

        private a() {
            super();
            this.a = new a(this);
            this.b = new master.flame.danmaku.danmaku.model.android.e(2);
        }

        public void a(master.flame.danmaku.danmaku.model.d dVar, m mVar, f fVar) {
            boolean z = true;
            boolean z2 = false;
            if (!dVar.g()) {
                boolean z3;
                master.flame.danmaku.danmaku.model.d dVar2;
                boolean z4;
                boolean e = dVar.e();
                float l = e ? dVar.l() : -1.0f;
                if (e || this.b.e()) {
                    z3 = false;
                } else {
                    z3 = true;
                }
                if (l < ((float) mVar.n())) {
                    l = ((float) mVar.f()) - dVar.q;
                }
                int i;
                if (e) {
                    dVar2 = null;
                    z = z3;
                    i = 0;
                    z4 = e;
                } else {
                    master.flame.danmaku.danmaku.model.d dVar3;
                    boolean z5;
                    this.d = false;
                    this.a.g = l;
                    this.a.a = mVar;
                    this.a.e = dVar;
                    this.b.a(this.a);
                    e a = this.a.a();
                    l = this.a.g;
                    if (a != null) {
                        int i2 = a.a;
                        dVar3 = a.c;
                        master.flame.danmaku.danmaku.model.d dVar4 = a.f;
                        boolean z6 = a.h;
                        z5 = a.i;
                        i = i2;
                        z4 = z6;
                        dVar2 = dVar4;
                    } else {
                        dVar3 = null;
                        dVar2 = null;
                        z5 = z3;
                        i = 0;
                        z4 = e;
                    }
                    z3 = a(false, dVar, mVar, l, dVar3, null);
                    if (z3) {
                        l = ((float) mVar.f()) - dVar.q;
                        z2 = z3;
                        i = 1;
                    } else {
                        if (l >= ((float) mVar.n())) {
                            z = false;
                        } else {
                            z = z5;
                        }
                        if (dVar2 != null) {
                            i--;
                            z2 = z3;
                        } else {
                            z2 = z3;
                        }
                    }
                }
                if (fVar == null || !fVar.a(dVar, l, r10, r7)) {
                    if (z2) {
                        a();
                    }
                    dVar.a(mVar, dVar.k(), l);
                    if (!z4) {
                        this.b.b(dVar2);
                        this.b.a(dVar);
                    }
                }
            }
        }

        protected boolean a(boolean z, master.flame.danmaku.danmaku.model.d dVar, m mVar, float f, master.flame.danmaku.danmaku.model.d dVar2, master.flame.danmaku.danmaku.model.d dVar3) {
            if (f < ((float) mVar.n()) || (dVar2 != null && dVar2.n() != ((float) mVar.f()))) {
                return true;
            }
            return false;
        }

        public void a() {
            this.d = true;
            this.b.b();
        }
    }

    private static class e {
        public int a;
        public master.flame.danmaku.danmaku.model.d b;
        public master.flame.danmaku.danmaku.model.d c;
        public master.flame.danmaku.danmaku.model.d d;
        public master.flame.danmaku.danmaku.model.d e;
        public master.flame.danmaku.danmaku.model.d f;
        public boolean g;
        public boolean h;
        public boolean i;

        private e() {
            this.a = 0;
            this.b = null;
            this.c = null;
            this.d = null;
            this.e = null;
            this.f = null;
            this.g = false;
            this.h = false;
            this.i = false;
        }
    }

    public b(boolean z) {
        a(z);
    }

    public void a(boolean z) {
        this.a = z ? new a() : new b();
        this.b = z ? new a() : new b();
        if (this.c == null) {
            this.c = new c();
        }
        if (this.d == null) {
            this.d = new a();
        }
    }

    public void a(master.flame.danmaku.danmaku.model.d dVar, m mVar, f fVar) {
        switch (dVar.o()) {
            case 1:
                this.a.a(dVar, mVar, fVar);
                return;
            case 4:
                this.d.a(dVar, mVar, fVar);
                return;
            case 5:
                this.c.a(dVar, mVar, fVar);
                return;
            case 6:
                this.b.a(dVar, mVar, fVar);
                return;
            case 7:
                dVar.a(mVar, 0.0f, 0.0f);
                return;
            default:
                return;
        }
    }

    public void a() {
        if (this.a != null) {
            this.a.a();
        }
        if (this.b != null) {
            this.b.a();
        }
        if (this.c != null) {
            this.c.a();
        }
        if (this.d != null) {
            this.d.a();
        }
    }

    public void b() {
        a();
    }
}
