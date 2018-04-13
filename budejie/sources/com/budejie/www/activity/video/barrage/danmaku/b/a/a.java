package com.budejie.www.activity.video.barrage.danmaku.b.a;

import com.budejie.www.activity.video.barrage.danmaku.b.b;
import com.budejie.www.activity.video.barrage.danmaku.model.android.DanmakuContext;
import com.budejie.www.activity.video.barrage.danmaku.model.c;
import com.budejie.www.activity.video.barrage.danmaku.model.e;
import com.budejie.www.activity.video.barrage.danmaku.model.j;
import com.budejie.www.activity.video.barrage.danmaku.model.k;
import com.budejie.www.activity.video.barrage.danmaku.model.l;

public class a extends b {
    private final e a = new e();
    private final com.budejie.www.activity.video.barrage.danmaku.b.a.a b = new com.budejie.www.activity.video.barrage.danmaku.b.a.a();
    private final DanmakuContext c;
    private b.e d;
    private final b.e e = new b.e(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public boolean a(c cVar, float f, int i, boolean z) {
            if (cVar.m != (byte) 0) {
                return false;
            }
            if (!this.a.c.s.b(cVar, i, 0, this.a.a, z, this.a.c)) {
                return false;
            }
            cVar.a(false);
            return true;
        }
    };
    private final b f;

    public a(DanmakuContext danmakuContext) {
        this.c = danmakuContext;
        this.f = new b();
    }

    public void a() {
        b();
        this.c.s.a();
    }

    public void b() {
        this.f.a();
    }

    public void c() {
        this.f.b();
        this.c.s.a();
    }

    public void a(boolean z) {
        this.d = z ? this.e : null;
    }

    public com.budejie.www.activity.video.barrage.danmaku.b.a.a a(l lVar, k kVar, long j) {
        int i = this.b.f;
        this.b.a();
        j e = kVar.e();
        int i2 = 0;
        this.a.a(System.currentTimeMillis());
        int a = kVar.a();
        c cVar = null;
        while (e.b()) {
            cVar = e.a();
            if (cVar.g()) {
                break;
            }
            if (!cVar.h()) {
                this.c.s.a(cVar, i2, a, this.a, false, this.c);
            }
            if (cVar.b >= j && !(cVar.m == (byte) 0 && cVar.i())) {
                if (cVar.n() == 1) {
                    i2++;
                }
                if (!cVar.b()) {
                    cVar.b(lVar);
                }
                this.f.a(cVar, lVar, this.d);
                if (!cVar.f() && cVar.d()) {
                    if (cVar.d != null || cVar.m() <= ((float) lVar.e())) {
                        int a2 = cVar.a(lVar);
                        com.budejie.www.activity.video.barrage.danmaku.b.a.a aVar;
                        if (a2 == 1) {
                            aVar = this.b;
                            aVar.m++;
                        } else if (a2 == 2) {
                            aVar = this.b;
                            aVar.n++;
                        }
                        this.b.a(cVar.n(), 1);
                        this.b.a(1);
                    }
                }
            }
        }
        this.b.k = this.b.f == 0;
        this.b.j = cVar != null ? cVar.b : -1;
        if (this.b.k) {
            this.b.i = -1;
        }
        this.b.g = this.b.f - i;
        this.b.h = this.a.a(System.currentTimeMillis());
        return this.b;
    }
}
