package cn.v6.sixrooms.surfaceanim.util;

import cn.v6.sixrooms.surfaceanim.protocol.PointF;

public class BezierSquareFloatEvaluator {
    private PointF[] a = new PointF[3];
    private PointF[] b;
    private int c;
    private int d;

    public BezierSquareFloatEvaluator(PointF pointF, PointF pointF2, PointF pointF3, int i, int i2) {
        this.a[0] = pointF;
        this.a[1] = pointF2;
        this.a[2] = pointF3;
        this.c = i;
        this.d = i2;
        a();
    }

    public void resetEvaluator(PointF pointF, PointF pointF2, PointF pointF3, int i, int i2) {
        this.a[0] = pointF;
        this.a[1] = pointF2;
        this.a[2] = pointF3;
        this.c = i;
        this.d = i2;
        a();
    }

    private void a() {
        int i = this.d - this.c;
        this.b = new PointF[(i + 1)];
        float f = 1.0f / ((float) i);
        float f2 = 0.0f;
        for (int i2 = 0; i2 <= i; i2++) {
            this.b[i2] = new PointF(a(this.a[0].x, this.a[1].x, this.a[2].x, f2), a(this.a[0].y, this.a[1].y, this.a[2].y, f2));
            f2 += f;
        }
    }

    public PointF evaluate(int i) {
        int i2 = i - this.c;
        if (this.b == null || i2 < 0 || i2 >= this.b.length) {
            return null;
        }
        return this.b[i2];
    }

    private static float a(float f, float f2, float f3, float f4) {
        return (((float) (((1.0d - ((double) f4)) * (1.0d - ((double) f4))) * ((double) f))) + ((float) (((2.0d * ((double) f4)) * ((double) (1.0f - f4))) * ((double) f2)))) + ((f4 * f4) * f3);
    }
}
