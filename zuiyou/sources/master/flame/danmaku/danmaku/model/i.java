package master.flame.danmaku.danmaku.model;

public class i extends d {
    private float K = 0.0f;
    private float[] L = null;
    private float M;
    private float N;
    private int O;
    protected float a = -1.0f;

    public i(g gVar) {
        this.r = gVar;
    }

    public void a(m mVar, float f, float f2) {
        if (this.D != null) {
            long s = this.D.a - s();
            if (s <= 0 || s >= this.r.a) {
                a(false);
                this.a = -1.0f;
                this.K = (float) mVar.e();
            } else if (!e()) {
                this.K = b(mVar);
                this.a = f2;
                a(true);
            }
        }
    }

    protected float b(m mVar) {
        if (this.O == mVar.e() && this.N == this.p) {
            return this.M;
        }
        float e = (((float) mVar.e()) - this.p) / 2.0f;
        this.O = mVar.e();
        this.N = this.p;
        this.M = e;
        return e;
    }

    public float[] a(m mVar, long j) {
        if (!b()) {
            return null;
        }
        float b = b(mVar);
        if (this.L == null) {
            this.L = new float[4];
        }
        this.L[0] = b;
        this.L[1] = this.a;
        this.L[2] = b + this.p;
        this.L[3] = this.a + this.q;
        return this.L;
    }

    public float k() {
        return this.K;
    }

    public float l() {
        return this.a;
    }

    public float m() {
        return this.K + this.p;
    }

    public float n() {
        return this.a + this.q;
    }

    public int o() {
        return 5;
    }
}
