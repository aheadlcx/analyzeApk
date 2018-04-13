package com.budejie.www.activity.video.barrage.danmaku.model;

public class o extends c {
    protected float C = 0.0f;
    protected float D = -1.0f;
    protected int E;
    protected float[] F = null;
    protected float G;
    protected long H;

    public o(f fVar) {
        this.p = fVar;
    }

    public void a(l lVar, float f, float f2) {
        if (this.x != null) {
            long j = this.x.a;
            long j2 = j - this.b;
            if (j2 <= 0 || j2 >= this.p.a) {
                this.H = j;
            } else {
                this.C = b(lVar, j);
                if (!d()) {
                    this.D = f2;
                    a(true);
                }
                this.H = j;
                return;
            }
        }
        a(false);
    }

    protected float b(l lVar, long j) {
        long j2 = j - this.b;
        if (j2 >= this.p.a) {
            return -this.n;
        }
        return ((float) lVar.d()) - (((float) j2) * this.G);
    }

    public float[] a(l lVar, long j) {
        if (!b()) {
            return null;
        }
        float b = b(lVar, j);
        if (this.F == null) {
            this.F = new float[4];
        }
        this.F[0] = b;
        this.F[1] = this.D;
        this.F[2] = b + this.n;
        this.F[3] = this.D + this.o;
        return this.F;
    }

    public float j() {
        return this.C;
    }

    public float k() {
        return this.D;
    }

    public float l() {
        return this.C + this.n;
    }

    public float m() {
        return this.D + this.o;
    }

    public int n() {
        return 1;
    }

    public void b(l lVar) {
        super.b(lVar);
        this.E = (int) (((float) lVar.d()) + this.n);
        this.G = ((float) this.E) / ((float) this.p.a);
    }
}
