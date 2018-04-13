package cn.v6.sixrooms.surfaceanim.util;

import cn.v6.sixrooms.surfaceanim.protocol.PointI;

public class BezierSquareEvaluator {
    private PointI[] a = new PointI[3];
    private PointI[] b;
    private int c;
    private int d;

    public BezierSquareEvaluator(PointI pointI, PointI pointI2, PointI pointI3, int i, int i2) {
        this.a[0] = pointI;
        this.a[1] = pointI2;
        this.a[2] = pointI3;
        this.c = i;
        this.d = i2;
        a();
    }

    public void resetEvaluator(PointI pointI, PointI pointI2, PointI pointI3, int i, int i2) {
        this.a[0] = pointI;
        this.a[1] = pointI2;
        this.a[2] = pointI3;
        this.c = i;
        this.d = i2;
        a();
    }

    private void a() {
        int i = this.d - this.c;
        this.b = new PointI[(i + 1)];
        float f = 1.0f / ((float) i);
        float f2 = 0.0f;
        for (int i2 = 0; i2 <= i; i2++) {
            this.b[i2] = new PointI(a(this.a[0].x, this.a[1].x, this.a[2].x, f2), a(this.a[0].y, this.a[1].y, this.a[2].y, f2));
            f2 += f;
        }
    }

    public PointI evaluate(int i) {
        int i2 = i - this.c;
        if (this.b == null || i2 < 0 || i2 >= this.b.length) {
            return null;
        }
        return this.b[i2];
    }

    private static int a(int i, int i2, int i3, float f) {
        return (((int) (((1.0d - ((double) f)) * (1.0d - ((double) f))) * ((double) i))) + ((int) (((2.0d * ((double) f)) * ((double) (1.0f - f))) * ((double) i2)))) + ((int) ((f * f) * ((float) i3)));
    }
}
