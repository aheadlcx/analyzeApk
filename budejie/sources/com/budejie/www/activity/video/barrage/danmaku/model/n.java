package com.budejie.www.activity.video.barrage.danmaku.model;

public class n extends o {
    public n(f fVar) {
        super(fVar);
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

    protected float b(l lVar, long j) {
        long j2 = j - this.b;
        if (j2 >= this.p.a) {
            return (float) lVar.d();
        }
        return (((float) j2) * this.G) - this.n;
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
        return 6;
    }
}
