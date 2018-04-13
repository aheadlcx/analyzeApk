package com.budejie.www.activity.video.barrage.danmaku.b.a;

import com.budejie.www.activity.video.barrage.danmaku.model.j;
import com.budejie.www.activity.video.barrage.danmaku.model.l;

public class b {
    private c a = null;
    private c b = null;
    private c c = null;
    private c d = null;

    public interface e {
        boolean a(com.budejie.www.activity.video.barrage.danmaku.model.c cVar, float f, int i, boolean z);
    }

    public interface c {
        void a();

        void a(com.budejie.www.activity.video.barrage.danmaku.model.c cVar, l lVar, e eVar);
    }

    private static class d implements c {
        protected com.budejie.www.activity.video.barrage.danmaku.model.android.c b;
        protected boolean c;
        int d;
        int e;

        private d() {
            this.b = new com.budejie.www.activity.video.barrage.danmaku.model.android.c(1);
            this.c = false;
        }

        public void a(com.budejie.www.activity.video.barrage.danmaku.model.c cVar, l lVar, e eVar) {
            if (cVar.f()) {
                cVar.a.f = false;
                return;
            }
            float f;
            boolean z;
            int i = 0;
            boolean z2 = (cVar.d() || this.b.f()) ? false : true;
            boolean d = cVar.d();
            boolean z3;
            if (d) {
                z3 = z2;
                f = 0.0f;
                z2 = d;
                z = false;
            } else {
                com.budejie.www.activity.video.barrage.danmaku.model.c a;
                com.budejie.www.activity.video.barrage.danmaku.model.c cVar2;
                com.budejie.www.activity.video.barrage.danmaku.model.c cVar3;
                boolean z4;
                boolean z5;
                com.budejie.www.activity.video.barrage.danmaku.model.c cVar4;
                int i2;
                float m;
                this.c = false;
                j e = this.b.e();
                com.budejie.www.activity.video.barrage.danmaku.model.c cVar5 = null;
                int i3 = 0;
                boolean z6 = z2;
                com.budejie.www.activity.video.barrage.danmaku.model.c cVar6 = null;
                com.budejie.www.activity.video.barrage.danmaku.model.c cVar7 = null;
                while (!this.c && e.b()) {
                    int i4 = i3 + 1;
                    a = e.a();
                    if (a == cVar) {
                        cVar2 = null;
                        cVar3 = cVar7;
                        z4 = true;
                        z5 = false;
                        cVar4 = a;
                        z = false;
                        i2 = i4;
                        a = cVar6;
                        break;
                    }
                    com.budejie.www.activity.video.barrage.danmaku.model.c cVar8;
                    if (cVar7 == null) {
                        cVar8 = a;
                    } else {
                        cVar8 = cVar7;
                    }
                    if (cVar.o + a.k() > ((float) lVar.e())) {
                        z = true;
                        a = cVar6;
                        cVar2 = cVar5;
                        cVar3 = cVar8;
                        z4 = d;
                        z5 = z6;
                        i2 = i4;
                        cVar4 = null;
                        break;
                    }
                    if (cVar6 == null) {
                        cVar6 = a;
                    } else if (cVar6.l() >= a.l()) {
                        cVar6 = a;
                    }
                    z = com.budejie.www.activity.video.barrage.danmaku.c.b.a(lVar, a, cVar, cVar.a(), cVar.o().a);
                    if (!z) {
                        cVar2 = cVar5;
                        cVar3 = cVar8;
                        cVar4 = a;
                        z4 = d;
                        a = cVar6;
                        z5 = z;
                        i2 = i4;
                        z = false;
                        break;
                    }
                    cVar5 = a;
                    z6 = z;
                    cVar7 = cVar8;
                    i3 = i4;
                }
                cVar2 = cVar5;
                cVar3 = cVar7;
                z4 = d;
                z5 = z6;
                cVar4 = null;
                z = false;
                i2 = i3;
                a = cVar6;
                Object obj = 1;
                if (cVar4 != null) {
                    if (cVar2 != null) {
                        m = cVar2.m();
                    } else {
                        m = cVar4.k();
                    }
                    if (cVar4 != cVar) {
                        this.b.b(cVar4);
                        z4 = false;
                    }
                } else if (z && a != null) {
                    m = a.k();
                    obj = null;
                    z4 = false;
                } else if (cVar2 != null) {
                    m = cVar2.m();
                    z5 = false;
                } else if (cVar3 != null) {
                    m = cVar3.k();
                    this.b.b(cVar3);
                    z4 = false;
                } else {
                    m = 0.0f;
                }
                if (obj != null) {
                    z = a(z, cVar, lVar, m, cVar3, cVar2);
                } else {
                    z = false;
                }
                if (z) {
                    f = 0.0f;
                    z3 = true;
                } else {
                    z3 = z5;
                    f = m;
                }
                if (f == 0.0f) {
                    z2 = false;
                    i = i2;
                } else {
                    z2 = z4;
                    i = i2;
                }
            }
            if (eVar == null || !eVar.a(cVar, f, r6, r4)) {
                if (z) {
                    a();
                }
                cVar.a(lVar, cVar.j(), f);
                if (!z2) {
                    this.b.a(cVar);
                }
            }
        }

        protected boolean a(boolean z, com.budejie.www.activity.video.barrage.danmaku.model.c cVar, l lVar, float f, com.budejie.www.activity.video.barrage.danmaku.model.c cVar2, com.budejie.www.activity.video.barrage.danmaku.model.c cVar3) {
            if (f < 0.0f || ((cVar2 != null && cVar2.k() > 0.0f) || cVar.o + f > ((float) lVar.e()))) {
                return true;
            }
            return false;
        }

        public void a() {
            this.c = true;
            this.b.b();
            this.d = 0;
            this.e = -1;
        }
    }

    private static class b extends d {
        private b() {
            super();
        }

        protected boolean a(boolean z, com.budejie.www.activity.video.barrage.danmaku.model.c cVar, l lVar, float f, com.budejie.www.activity.video.barrage.danmaku.model.c cVar2, com.budejie.www.activity.video.barrage.danmaku.model.c cVar3) {
            if (cVar.o + f > ((float) lVar.e())) {
                return true;
            }
            return false;
        }
    }

    private static class a extends b {
        protected com.budejie.www.activity.video.barrage.danmaku.model.android.c a;

        private a() {
            super();
            this.a = new com.budejie.www.activity.video.barrage.danmaku.model.android.c(2);
        }

        public void a(com.budejie.www.activity.video.barrage.danmaku.model.c cVar, l lVar, e eVar) {
            if (!cVar.f()) {
                com.budejie.www.activity.video.barrage.danmaku.model.c cVar2;
                boolean z;
                float f;
                boolean d = cVar.d();
                float k = cVar.k();
                int i = 0;
                boolean z2 = (cVar.d() || this.a.f()) ? false : true;
                if (k < 0.0f) {
                    k = ((float) lVar.e()) - cVar.o;
                }
                com.budejie.www.activity.video.barrage.danmaku.model.c cVar3 = null;
                boolean z3;
                int i2;
                if (d) {
                    cVar2 = null;
                    z = false;
                    z3 = z2;
                    f = k;
                    i2 = 0;
                } else {
                    com.budejie.www.activity.video.barrage.danmaku.model.c cVar4;
                    this.c = false;
                    j e = this.a.e();
                    f = k;
                    while (!this.c && e.b()) {
                        int i3 = i + 1;
                        com.budejie.www.activity.video.barrage.danmaku.model.c a = e.a();
                        if (a == cVar) {
                            cVar4 = cVar3;
                            cVar2 = null;
                            i2 = i3;
                            z3 = false;
                            break;
                        }
                        if (cVar3 == null) {
                            if (a.m() != ((float) lVar.e())) {
                                cVar4 = a;
                                cVar2 = null;
                                i2 = i3;
                                z3 = z2;
                                break;
                            }
                            cVar3 = a;
                        }
                        if (f < 0.0f) {
                            cVar4 = cVar3;
                            cVar2 = null;
                            i2 = i3;
                            z3 = z2;
                            break;
                        }
                        z2 = com.budejie.www.activity.video.barrage.danmaku.c.b.a(lVar, a, cVar, cVar.a(), cVar.o().a);
                        if (!z2) {
                            cVar4 = cVar3;
                            cVar2 = a;
                            i2 = i3;
                            z3 = z2;
                            break;
                        }
                        f = a.k() - cVar.o;
                        i = i3;
                    }
                    cVar4 = cVar3;
                    cVar2 = null;
                    z3 = z2;
                    i2 = i;
                    z = a(false, cVar, lVar, f, cVar4, null);
                    if (z) {
                        f = ((float) lVar.e()) - cVar.o;
                        z3 = true;
                    } else if (f >= 0.0f) {
                        z3 = false;
                    }
                }
                if (eVar == null || !eVar.a(cVar, f, r12, r10)) {
                    if (z) {
                        a();
                    }
                    cVar.a(lVar, cVar.j(), f);
                    if (!d) {
                        this.a.b(cVar2);
                        this.a.a(cVar);
                    }
                }
            }
        }

        protected boolean a(boolean z, com.budejie.www.activity.video.barrage.danmaku.model.c cVar, l lVar, float f, com.budejie.www.activity.video.barrage.danmaku.model.c cVar2, com.budejie.www.activity.video.barrage.danmaku.model.c cVar3) {
            if (f < 0.0f || (cVar2 != null && cVar2.m() != ((float) lVar.e()))) {
                return true;
            }
            return false;
        }

        public void a() {
            this.c = true;
            this.a.b();
        }
    }

    public void a(com.budejie.www.activity.video.barrage.danmaku.model.c cVar, l lVar, e eVar) {
        switch (cVar.n()) {
            case 1:
                if (this.a == null) {
                    this.a = new d();
                }
                this.a.a(cVar, lVar, eVar);
                return;
            case 4:
                if (this.d == null) {
                    this.d = new a();
                }
                this.d.a(cVar, lVar, eVar);
                return;
            case 5:
                if (this.c == null) {
                    this.c = new b();
                }
                this.c.a(cVar, lVar, eVar);
                return;
            case 6:
                if (this.b == null) {
                    this.b = new d();
                }
                this.b.a(cVar, lVar, eVar);
                return;
            case 7:
                cVar.a(lVar, 0.0f, 0.0f);
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
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
    }
}
