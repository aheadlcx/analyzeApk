package master.flame.danmaku.danmaku.model;

public class p extends d {
    protected float K = 0.0f;
    protected float L = -1.0f;
    protected int M;
    protected float[] N = null;
    protected float O;
    protected long P;

    public p(g gVar) {
        this.r = gVar;
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

    protected float b(m mVar, long j) {
        long s = j - s();
        if (s >= this.r.a) {
            return -this.p;
        }
        return ((float) mVar.e()) - (((float) s) * this.O);
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
        return 1;
    }

    public void a(m mVar, boolean z) {
        super.a(mVar, z);
        this.M = (int) (((float) mVar.e()) + this.p);
        this.O = ((float) this.M) / ((float) this.r.a);
    }
}
