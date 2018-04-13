package master.flame.danmaku.danmaku.model.android;

import java.lang.reflect.Array;
import master.flame.danmaku.danmaku.model.g;
import master.flame.danmaku.danmaku.model.h;
import master.flame.danmaku.danmaku.model.i;
import master.flame.danmaku.danmaku.model.l;
import master.flame.danmaku.danmaku.model.l.c;
import master.flame.danmaku.danmaku.model.m;
import master.flame.danmaku.danmaku.model.o;
import master.flame.danmaku.danmaku.model.p;
import master.flame.danmaku.danmaku.model.q;
import master.flame.danmaku.danmaku.model.q.a;

public class d {
    public int a = 0;
    public int b = 0;
    public long c = 3800;
    public long d = 4000;
    public g e;
    public g f;
    public g g;
    public m h;
    private float i = 1.0f;
    private l j = new e();
    private DanmakuContext k;

    static d a() {
        return new d();
    }

    protected d() {
    }

    public void b() {
        this.h = null;
        this.b = 0;
        this.a = 0;
        this.j.b();
        this.e = null;
        this.f = null;
        this.g = null;
        this.d = 4000;
    }

    public void a(DanmakuContext danmakuContext) {
        this.k = danmakuContext;
        this.h = danmakuContext.b();
        a(1, danmakuContext);
    }

    public master.flame.danmaku.danmaku.model.d a(int i, DanmakuContext danmakuContext) {
        if (danmakuContext == null) {
            return null;
        }
        this.k = danmakuContext;
        this.h = danmakuContext.b();
        return a(i, this.h.e(), this.h.f(), this.i, danmakuContext.l);
    }

    public master.flame.danmaku.danmaku.model.d a(int i, int i2, int i3, float f, float f2) {
        return a(i, (float) i2, (float) i3, f, f2);
    }

    public master.flame.danmaku.danmaku.model.d a(int i, float f, float f2, float f3, float f4) {
        float f5 = 1.0f;
        int i2 = this.a;
        int i3 = this.b;
        boolean a = a(f, f2, f3);
        if (this.e == null) {
            this.e = new g(this.c);
            this.e.a(f4);
        } else if (a) {
            this.e.a(this.c);
        }
        if (this.f == null) {
            this.f = new g(3800);
        }
        if (a && f > 0.0f) {
            float f6;
            c();
            if (i2 <= 0 || i3 <= 0) {
                f6 = 1.0f;
            } else {
                f6 = f / ((float) i2);
                f5 = f2 / ((float) i3);
            }
            if (f2 > 0.0f) {
                a(f6, f5);
            }
        }
        switch (i) {
            case 1:
                return new p(this.e);
            case 4:
                return new h(this.f);
            case 5:
                return new i(this.f);
            case 6:
                return new o(this.e);
            case 7:
                master.flame.danmaku.danmaku.model.d qVar = new q();
                this.j.a(qVar);
                return qVar;
            default:
                return null;
        }
    }

    public boolean a(float f, float f2, float f3) {
        if (this.a == ((int) f) && this.b == ((int) f2) && this.i == f3) {
            return false;
        }
        this.c = (long) (3800.0f * ((f3 * f) / 682.0f));
        this.c = Math.min(9000, this.c);
        this.c = Math.max(4000, this.c);
        this.a = (int) f;
        this.b = (int) f2;
        this.i = f3;
        return true;
    }

    private synchronized void a(final float f, final float f2) {
        this.j.a(new c<master.flame.danmaku.danmaku.model.d>(this) {
            final /* synthetic */ d c;

            public int a(master.flame.danmaku.danmaku.model.d dVar) {
                q qVar = (q) dVar;
                this.c.a(qVar, qVar.a, qVar.K, qVar.L, qVar.M, qVar.P, qVar.Q, f, f2);
                a[] aVarArr = qVar.W;
                if (aVarArr != null && aVarArr.length > 0) {
                    int length = aVarArr.length;
                    float[][] fArr = (float[][]) Array.newInstance(Float.TYPE, new int[]{length + 1, 2});
                    for (int i = 0; i < length; i++) {
                        fArr[i] = aVarArr[i].b();
                        fArr[i + 1] = aVarArr[i].c();
                    }
                    d.a(qVar, fArr, f, f2);
                }
                return 0;
            }
        });
    }

    public void c() {
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
            c();
        }
    }

    public void a(master.flame.danmaku.danmaku.model.d dVar, float f, float f2, float f3, float f4, long j, long j2, float f5, float f6) {
        if (dVar.o() == 7) {
            ((q) dVar).a(f * f5, f2 * f6, f3 * f5, f4 * f6, j, j2);
            a(dVar);
        }
    }

    public static void a(master.flame.danmaku.danmaku.model.d dVar, float[][] fArr, float f, float f2) {
        if (dVar.o() == 7 && fArr.length != 0 && fArr[0].length == 2) {
            for (int i = 0; i < fArr.length; i++) {
                float[] fArr2 = fArr[i];
                fArr2[0] = fArr2[0] * f;
                fArr2 = fArr[i];
                fArr2[1] = fArr2[1] * f2;
            }
            ((q) dVar).a(fArr);
        }
    }

    private void a(master.flame.danmaku.danmaku.model.d dVar) {
        if (this.g == null || (dVar.r != null && dVar.r.a > this.g.a)) {
            this.g = dVar.r;
            c();
        }
    }
}
