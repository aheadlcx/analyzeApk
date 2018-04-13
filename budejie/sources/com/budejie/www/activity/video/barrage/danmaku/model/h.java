package com.budejie.www.activity.video.barrage.danmaku.model;

public class h extends c {
    protected float C = -1.0f;
    private float D = 0.0f;
    private float[] E = null;
    private float F;
    private float G;
    private int H;

    public h(f fVar) {
        this.p = fVar;
    }

    public void a(l lVar, float f, float f2) {
        if (this.x != null) {
            long j = this.x.a - this.b;
            if (j <= 0 || j >= this.p.a) {
                a(false);
                this.C = -1.0f;
                this.D = (float) lVar.d();
            } else if (!d()) {
                this.D = c(lVar);
                this.C = f2;
                a(true);
            }
        }
    }

    protected float c(l lVar) {
        if (this.H == lVar.d() && this.G == this.n) {
            return this.F;
        }
        float d = (((float) lVar.d()) - this.n) / 2.0f;
        this.H = lVar.d();
        this.G = this.n;
        this.F = d;
        return d;
    }

    public float[] a(l lVar, long j) {
        if (!b()) {
            return null;
        }
        float c = c(lVar);
        if (this.E == null) {
            this.E = new float[4];
        }
        this.E[0] = c;
        this.E[1] = this.C;
        this.E[2] = c + this.n;
        this.E[3] = this.C + this.o;
        return this.E;
    }

    public float j() {
        return this.D;
    }

    public float k() {
        return this.C;
    }

    public float l() {
        return this.D + this.n;
    }

    public float m() {
        return this.C + this.o;
    }

    public int n() {
        return 5;
    }
}
