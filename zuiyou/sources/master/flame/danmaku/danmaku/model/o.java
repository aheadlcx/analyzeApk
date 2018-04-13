package master.flame.danmaku.danmaku.model;

public class o extends p {
    public o(g gVar) {
        super(gVar);
    }

    public void a(m mVar, float f, float f2) {
        if (this.D != null) {
            long j = this.D.a;
            long s = j - s();
            if (s <= 0 || s >= this.r.a) {
                this.P = j;
            } else {
                this.K = b(mVar, j);
                if (!e()) {
                    this.L = f2;
                    a(true);
                }
                this.P = j;
                return;
            }
        }
        a(false);
    }

    public float[] a(m mVar, long j) {
        if (!b()) {
            return null;
        }
        float b = b(mVar, j);
        if (this.N == null) {
            this.N = new float[4];
        }
        this.N[0] = b;
        this.N[1] = this.L;
        this.N[2] = b + this.p;
        this.N[3] = this.L + this.q;
        return this.N;
    }

    protected float b(m mVar, long j) {
        long s = j - s();
        if (s >= this.r.a) {
            return (float) mVar.e();
        }
        return (((float) s) * this.O) - this.p;
    }

    public float k() {
        return this.K;
    }

    public float l() {
        return this.L;
    }

    public float m() {
        return this.K + this.p;
    }

    public float n() {
        return this.L + this.q;
    }

    public int o() {
        return 6;
    }
}
