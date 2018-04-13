package com.budejie.www.activity.video.barrage.danmaku.model;

public class p extends c {
    public float C;
    public float D;
    public float E;
    public float F;
    public float G;
    public float H;
    public long I;
    public long J;
    public int K;
    public int L;
    public int M;
    public long N;
    public a[] O;
    private float[] P = new float[4];

    public class a {
        b a;
        b b;
        public long c;
        public long d;
        public long e;
        float f;
        float g;
        final /* synthetic */ p h;

        public a(p pVar) {
            this.h = pVar;
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
        final /* synthetic */ p c;

        public b(p pVar, float f, float f2) {
            this.c = pVar;
            this.a = f;
            this.b = f2;
        }

        public float a(b bVar) {
            float abs = Math.abs(this.a - bVar.a);
            float abs2 = Math.abs(this.b - bVar.b);
            return (float) Math.sqrt((double) ((abs * abs) + (abs2 * abs2)));
        }
    }

    public void a(l lVar, float f, float f2) {
        a(lVar, this.x.a);
    }

    public float[] a(l lVar, long j) {
        if (!b()) {
            return null;
        }
        long j2 = j - this.b;
        if (this.N > 0 && this.M != 0) {
            if (j2 >= this.N) {
                this.y = this.L;
            } else {
                this.y = ((int) ((((float) j2) / ((float) this.N)) * ((float) this.M))) + this.K;
            }
        }
        float f = this.C;
        float f2 = this.D;
        long j3 = j2 - this.J;
        float f3;
        if (this.I <= 0 || j3 < 0 || j3 > this.I) {
            if (j3 > this.I) {
                f2 = this.E;
                f = this.F;
            }
            f3 = f2;
            f2 = f;
            f = f3;
        } else {
            float f4 = ((float) j3) / ((float) this.I);
            if (this.O != null) {
                a aVar;
                float f5;
                a[] aVarArr = this.O;
                int length = aVarArr.length;
                int i = 0;
                f3 = f2;
                f2 = f;
                f = f3;
                while (i < length) {
                    aVar = aVarArr[i];
                    if (j3 >= aVar.d && j3 < aVar.e) {
                        break;
                    }
                    f5 = aVar.b.a;
                    i++;
                    f = aVar.b.b;
                    f2 = f5;
                }
                aVar = null;
                if (aVar != null) {
                    float f6 = aVar.f;
                    float f7 = aVar.g;
                    f5 = ((float) (j2 - aVar.d)) / ((float) aVar.c);
                    float f8 = aVar.a.a;
                    f4 = aVar.a.b;
                    if (f6 != 0.0f) {
                        f2 = (f6 * f5) + f8;
                    }
                    if (f7 != 0.0f) {
                        f = (f7 * f5) + f4;
                    }
                }
            } else {
                if (this.G != 0.0f) {
                    f = (this.G * f4) + this.C;
                }
                if (this.H != 0.0f) {
                    f2 = f;
                    f = (this.H * f4) + this.D;
                }
                f3 = f2;
                f2 = f;
                f = f3;
            }
        }
        this.P[0] = f2;
        this.P[1] = f;
        this.P[2] = f2 + this.n;
        this.P[3] = f + this.o;
        a(!f());
        return this.P;
    }

    public float j() {
        return this.P[0];
    }

    public float k() {
        return this.P[1];
    }

    public float l() {
        return this.P[2];
    }

    public float m() {
        return this.P[3];
    }

    public int n() {
        return 7;
    }

    public void a(float f, float f2, float f3, float f4, long j, long j2) {
        this.C = f;
        this.D = f2;
        this.E = f3;
        this.F = f4;
        this.G = f3 - f;
        this.H = f4 - f2;
        this.I = j;
        this.J = j2;
    }

    public void a(int i, int i2, long j) {
        this.K = i;
        this.L = i2;
        this.M = i2 - i;
        this.N = j;
        if (this.M != 0 && i != b.a) {
            this.y = i;
        }
    }

    public void a(float[][] fArr) {
        if (fArr != null) {
            int length = fArr.length;
            this.C = fArr[0][0];
            this.D = fArr[0][1];
            this.E = fArr[length - 1][0];
            this.F = fArr[length - 1][1];
            if (fArr.length > 1) {
                this.O = new a[(fArr.length - 1)];
                for (length = 0; length < this.O.length; length++) {
                    this.O[length] = new a(this);
                    this.O[length].a(new b(this, fArr[length][0], fArr[length][1]), new b(this, fArr[length + 1][0], fArr[length + 1][1]));
                }
                a[] aVarArr = this.O;
                float f = 0.0f;
                length = 0;
                while (length < aVarArr.length) {
                    length++;
                    f = aVarArr[length].a() + f;
                }
                a aVar = null;
                a[] aVarArr2 = this.O;
                int length2 = aVarArr2.length;
                int i = 0;
                while (i < length2) {
                    a aVar2 = aVarArr2[i];
                    aVar2.c = (long) ((aVar2.a() / f) * ((float) this.I));
                    aVar2.d = aVar == null ? 0 : aVar.e;
                    aVar2.e = aVar2.d + aVar2.c;
                    i++;
                    aVar = aVar2;
                }
            }
        }
    }
}
