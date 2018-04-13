package com.budejie.www.activity.video.barrage.danmaku.a;

import com.budejie.www.activity.video.barrage.danmaku.model.android.DanmakuContext;
import com.budejie.www.activity.video.barrage.danmaku.model.android.c;
import com.budejie.www.activity.video.barrage.danmaku.model.f;
import com.budejie.www.activity.video.barrage.danmaku.model.g;
import com.budejie.www.activity.video.barrage.danmaku.model.h;
import com.budejie.www.activity.video.barrage.danmaku.model.j;
import com.budejie.www.activity.video.barrage.danmaku.model.k;
import com.budejie.www.activity.video.barrage.danmaku.model.l;
import com.budejie.www.activity.video.barrage.danmaku.model.n;
import com.budejie.www.activity.video.barrage.danmaku.model.o;
import com.budejie.www.activity.video.barrage.danmaku.model.p;
import com.budejie.www.activity.video.barrage.danmaku.model.p.a;
import java.lang.reflect.Array;

public class b {
    public int a = 0;
    public int b = 0;
    public long c = 3800;
    public long d = 5000;
    public f e;
    public f f;
    public f g;
    public k h = new c();
    public l i;
    private float j = 1.0f;
    private DanmakuContext k;

    public void a() {
        this.i = null;
        this.b = 0;
        this.a = 0;
        this.h.b();
        this.e = null;
        this.f = null;
        this.g = null;
        this.d = 5000;
    }

    public void a(DanmakuContext danmakuContext) {
        this.k = danmakuContext;
        this.i = danmakuContext.b();
        a(1, danmakuContext);
    }

    public com.budejie.www.activity.video.barrage.danmaku.model.c a(int i) {
        return a(i, this.k);
    }

    public com.budejie.www.activity.video.barrage.danmaku.model.c a(int i, DanmakuContext danmakuContext) {
        if (danmakuContext == null) {
            return null;
        }
        this.k = danmakuContext;
        this.i = danmakuContext.b();
        return a(i, this.i.d(), this.i.e(), this.j, danmakuContext.k);
    }

    public com.budejie.www.activity.video.barrage.danmaku.model.c a(int i, int i2, int i3, float f, float f2) {
        return a(i, (float) i2, (float) i3, f, f2);
    }

    public com.budejie.www.activity.video.barrage.danmaku.model.c a(int i, float f, float f2, float f3, float f4) {
        float f5 = 1.0f;
        boolean a = a(f, f2, f3);
        if (this.e == null) {
            this.e = new f(this.c);
            this.e.a(f4);
        } else if (a) {
            this.e.a(this.c);
        }
        if (this.f == null) {
            this.f = new f(3800);
        }
        if (a && f > 0.0f) {
            float f6;
            b();
            if (this.a <= 0 || this.b <= 0) {
                f6 = 1.0f;
            } else {
                f6 = f / ((float) this.a);
                f5 = f2 / ((float) this.b);
            }
            if (f2 > 0.0f) {
                a(f6, f5);
            }
        }
        switch (i) {
            case 1:
                return new o(this.e);
            case 4:
                return new g(this.f);
            case 5:
                return new h(this.f);
            case 6:
                return new n(this.e);
            case 7:
                com.budejie.www.activity.video.barrage.danmaku.model.c pVar = new p();
                this.h.a(pVar);
                return pVar;
            default:
                return null;
        }
    }

    public boolean a(float f, float f2, float f3) {
        if (this.a == ((int) f) && this.b == ((int) f2) && this.j == f3) {
            return false;
        }
        this.c = (long) (3800.0f * ((f3 * f) / 682.0f));
        this.c = Math.min(9000, this.c);
        this.c = Math.max(5000, this.c);
        this.a = (int) f;
        this.b = (int) f2;
        this.j = f3;
        return true;
    }

    private void a(float f, float f2) {
        j e = this.h.e();
        while (e.b()) {
            com.budejie.www.activity.video.barrage.danmaku.model.c cVar = (p) e.a();
            a(cVar, cVar.C, cVar.D, cVar.E, cVar.F, cVar.I, cVar.J, f, f2);
            a[] aVarArr = cVar.O;
            if (aVarArr != null && aVarArr.length > 0) {
                int length = aVarArr.length;
                float[][] fArr = (float[][]) Array.newInstance(Float.TYPE, new int[]{length + 1, 2});
                for (int i = 0; i < length; i++) {
                    fArr[i] = aVarArr[i].b();
                    fArr[i + 1] = aVarArr[i].c();
                }
                a(cVar, fArr, f, f2);
            }
        }
    }

    public void b() {
        long j = 0;
        long j2 = this.e == null ? 0 : this.e.a;
        long j3 = this.f == null ? 0 : this.f.a;
        if (this.g != null) {
            j = this.g.a;
        }
        this.d = Math.max(j2, j3);
        this.d = Math.max(this.d, j);
        this.d = Math.max(3800, this.d);
        this.d = Math.max(this.c, this.d);
    }

    public void a(float f) {
        if (this.e != null && this.f != null) {
            this.e.a(f);
            b();
        }
    }

    public void a(com.budejie.www.activity.video.barrage.danmaku.model.c cVar, float f, float f2, float f3, float f4, long j, long j2, float f5, float f6) {
        if (cVar.n() == 7) {
            ((p) cVar).a(f * f5, f2 * f6, f3 * f5, f4 * f6, j, j2);
            a(cVar);
        }
    }

    public static void a(com.budejie.www.activity.video.barrage.danmaku.model.c cVar, float[][] fArr, float f, float f2) {
        if (cVar.n() == 7 && fArr.length != 0 && fArr[0].length == 2) {
            for (int i = 0; i < fArr.length; i++) {
                float[] fArr2 = fArr[i];
                fArr2[0] = fArr2[0] * f;
                fArr2 = fArr[i];
                fArr2[1] = fArr2[1] * f2;
            }
            ((p) cVar).a(fArr);
        }
    }

    public void a(com.budejie.www.activity.video.barrage.danmaku.model.c cVar, int i, int i2, long j) {
        if (cVar.n() == 7) {
            ((p) cVar).a(i, i2, j);
            a(cVar);
        }
    }

    private void a(com.budejie.www.activity.video.barrage.danmaku.model.c cVar) {
        if (this.g == null || (cVar.p != null && cVar.p.a > this.g.a)) {
            this.g = cVar.p;
            b();
        }
    }
}
