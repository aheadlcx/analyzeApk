package master.flame.danmaku.danmaku.model;

public abstract class d {
    public int A = 0;
    public String B;
    public boolean C;
    protected f D;
    protected int E = c.a;
    public int F = 0;
    public int G = -1;
    public j H = null;
    public int I = 0;
    public int J = -1;
    private int K = 0;
    private long a;
    public long b;
    public CharSequence c;
    public String[] d;
    public Object e;
    public Object f;
    public int g;
    public float h;
    public float i;
    public int j;
    public int k = 0;
    public float l = -1.0f;
    public int m = 0;
    public int n = 0;
    public byte o = (byte) 0;
    public float p = -1.0f;
    public float q = -1.0f;
    public g r;
    public int s;
    public int t;
    public int u = 0;
    public int v = 0;
    public int w = -1;
    public n<?> x;
    public boolean y;
    public boolean z;

    public abstract void a(m mVar, float f, float f2);

    public abstract float[] a(m mVar, long j);

    public abstract float k();

    public abstract float l();

    public abstract float m();

    public abstract float n();

    public abstract int o();

    public long a() {
        return this.r.a;
    }

    public int a(m mVar) {
        return mVar.a(this);
    }

    public boolean b() {
        return this.p > -1.0f && this.q > -1.0f && this.u == this.H.a;
    }

    public void a(m mVar, boolean z) {
        mVar.b(this, z);
        this.u = this.H.a;
    }

    public boolean c() {
        return this.w == this.H.f;
    }

    public void b(m mVar, boolean z) {
        mVar.a(this, z);
        this.w = this.H.f;
    }

    public n<?> d() {
        return this.x;
    }

    public boolean e() {
        return this.t == 1 && this.K == this.H.b;
    }

    public boolean f() {
        return this.D == null || a(this.D.a);
    }

    public boolean a(long j) {
        return j - s() >= this.r.a;
    }

    public boolean g() {
        return this.D == null || b(this.D.a);
    }

    public boolean b(long j) {
        long s = j - s();
        return s <= 0 || s >= this.r.a;
    }

    public boolean h() {
        return this.D == null || this.D.a < s();
    }

    public boolean i() {
        if (this.G == this.H.c) {
            return true;
        }
        this.F = 0;
        return false;
    }

    public boolean j() {
        return this.G == this.H.c && this.F != 0;
    }

    public void a(boolean z) {
        if (z) {
            this.K = this.H.b;
            this.t = 1;
            return;
        }
        this.t = 0;
    }

    public f p() {
        return this.D;
    }

    public void a(f fVar) {
        this.D = fVar;
    }

    public int q() {
        return this.E;
    }

    public void c(long j) {
        this.b = j;
        this.v = this.H.e;
    }

    public void d(long j) {
        this.a = j;
        this.b = 0;
    }

    public long r() {
        return this.a;
    }

    public long s() {
        if (this.H != null && this.H.e == this.v) {
            return this.a + this.b;
        }
        this.b = 0;
        return this.a;
    }

    public boolean t() {
        if (this.H == null || this.H.e != this.v) {
            this.b = 0;
            return false;
        } else if (this.b != 0) {
            return true;
        } else {
            return false;
        }
    }
}
