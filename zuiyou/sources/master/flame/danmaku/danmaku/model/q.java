package master.flame.danmaku.danmaku.model;

public class q extends d {
    public float K;
    public float L;
    public float M;
    public float N;
    public float O;
    public long P;
    public long Q;
    public boolean R = false;
    public int S;
    public int T;
    public int U;
    public long V;
    public a[] W;
    private float[] X = new float[4];
    public float a;

    public class a {
        b a;
        b b;
        public long c;
        public long d;
        public long e;
        float f;
        float g;
        final /* synthetic */ q h;

        public a(q qVar) {
            this.h = qVar;
        }

        public void a(b bVar, b bVar2) {
            this.a = bVar;
            this.b = bVar2;
            this.f = bVar2.a - bVar.a;
            this.g = bVar2.b - bVar.b;
        }

        public float a() {
            return this.b.a(this.a);
        }

        public float[] b() {
            return new float[]{this.a.a, this.a.b};
        }

        public float[] c() {
            return new float[]{this.b.a, this.b.b};
        }
    }

    private class b {
        float a;
        float b;
        final /* synthetic */ q c;

        public b(q qVar, float f, float f2) {
            this.c = qVar;
            this.a = f;
            this.b = f2;
        }

        public float a(b bVar) {
            float abs = Math.abs(this.a - bVar.a);
            float abs2 = Math.abs(this.b - bVar.b);
            return (float) Math.sqrt((double) ((abs * abs) + (abs2 * abs2)));
        }
    }

    public void a(m mVar, float f, float f2) {
        a(mVar, this.D.a);
    }

    public float[] a(m mVar, long j) {
        if (!b()) {
            return null;
        }
        float f;
        long s = j - s();
        if (this.V > 0 && this.U != 0) {
            if (s >= this.V) {
                this.E = this.T;
            } else {
                this.E = ((int) ((((float) s) / ((float) this.V)) * ((float) this.U))) + this.S;
            }
        }
        float f2 = this.a;
        float f3 = this.K;
        long j2 = s - this.Q;
        if (this.P <= 0 || j2 < 0 || j2 > this.P) {
            if (j2 > this.P) {
                f2 = this.L;
                f = this.M;
            }
            f = f3;
        } else if (this.W != null) {
            a aVar;
            a[] aVarArr = this.W;
            int length = aVarArr.length;
            int i = 0;
            f = f3;
            while (i < length) {
                aVar = aVarArr[i];
                if (j2 >= aVar.d && j2 < aVar.e) {
                    break;
                }
                f2 = aVar.b.a;
                i++;
                f = aVar.b.b;
            }
            aVar = null;
            if (aVar != null) {
                float f4 = aVar.f;
                float f5 = aVar.g;
                float f6 = ((float) (s - aVar.d)) / ((float) aVar.c);
                float f7 = aVar.a.a;
                f3 = aVar.a.b;
                if (f4 != 0.0f) {
                    f2 = (f4 * f6) + f7;
                }
                if (f5 != 0.0f) {
                    f = (f5 * f6) + f3;
                }
            }
        } else {
            f = this.R ? a(j2, this.P) : ((float) j2) / ((float) this.P);
            if (this.N != 0.0f) {
                f2 = (this.N * f) + this.a;
            }
            if (this.O != 0.0f) {
                f = (f * this.O) + this.K;
            }
            f = f3;
        }
        this.X[0] = f2;
        this.X[1] = f;
        this.X[2] = f2 + this.p;
        this.X[3] = f + this.q;
        a(!g());
        return this.X;
    }

    private static final float a(long j, long j2) {
        float f = ((float) j) / ((float) j2);
        return (f - 2.0f) * ((-1.0f) * f);
    }

    public float k() {
        return this.X[0];
    }

    public float l() {
        return this.X[1];
    }

    public float m() {
        return this.X[2];
    }

    public float n() {
        return this.X[3];
    }

    public int o() {
        return 7;
    }

    public void a(float f, float f2, float f3, float f4, long j, long j2) {
        this.a = f;
        this.K = f2;
        this.L = f3;
        this.M = f4;
        this.N = f3 - f;
        this.O = f4 - f2;
        this.P = j;
        this.Q = j2;
    }

    public void a(float[][] fArr) {
        if (fArr != null) {
            int length = fArr.length;
            this.a = fArr[0][0];
            this.K = fArr[0][1];
            this.L = fArr[length - 1][0];
            this.M = fArr[length - 1][1];
            if (fArr.length > 1) {
                this.W = new a[(fArr.length - 1)];
                for (length = 0; length < this.W.length; length++) {
                    this.W[length] = new a(this);
                    this.W[length].a(new b(this, fArr[length][0], fArr[length][1]), new b(this, fArr[length + 1][0], fArr[length + 1][1]));
                }
                a[] aVarArr = this.W;
                float f = 0.0f;
                length = 0;
                while (length < aVarArr.length) {
                    length++;
                    f = aVarArr[length].a() + f;
                }
                a aVar = null;
                a[] aVarArr2 = this.W;
                int length2 = aVarArr2.length;
                int i = 0;
                while (i < length2) {
                    a aVar2 = aVarArr2[i];
                    aVar2.c = (long) ((aVar2.a() / f) * ((float) this.P));
                    aVar2.d = aVar == null ? 0 : aVar.e;
                    aVar2.e = aVar2.d + aVar2.c;
                    i++;
                    aVar = aVar2;
                }
            }
        }
    }
}
