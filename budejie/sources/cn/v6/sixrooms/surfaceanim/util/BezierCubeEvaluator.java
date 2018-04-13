package cn.v6.sixrooms.surfaceanim.util;

import cn.v6.sixrooms.surfaceanim.protocol.PointI;

public class BezierCubeEvaluator {
    private int a;
    private int b;
    private PointI c;
    private PointI d;
    private PointI e;
    private PointI f;
    private int g;

    public BezierCubeEvaluator(PointI pointI) {
        resetEvaluator(Integer.MAX_VALUE, Integer.MAX_VALUE, pointI, pointI, pointI, pointI);
    }

    public BezierCubeEvaluator(int i, int i2, PointI pointI, PointI pointI2, PointI pointI3, PointI pointI4) {
        resetEvaluator(i, i2, pointI, pointI2, pointI3, pointI4);
    }

    public void resetEvaluator(int i, int i2, PointI pointI, PointI pointI2, PointI pointI3, PointI pointI4) {
        this.a = i;
        this.b = i2;
        this.c = pointI;
        this.d = pointI2;
        this.e = pointI3;
        this.f = pointI4;
        this.g = i2 - i;
    }

    public PointI evaluate(int i) {
        return evaluate(i, false);
    }

    public PointI evaluate(int i, boolean z) {
        float f;
        if (z) {
            if (i <= this.a) {
                return this.f;
            }
            if (i >= this.b) {
                return this.c;
            }
            f = (1.0f / ((float) this.g)) * ((float) (this.b - i));
        } else if (i <= this.a) {
            return this.c;
        } else {
            if (i >= this.b) {
                return this.f;
            }
            f = (1.0f / ((float) this.g)) * ((float) (i - this.a));
        }
        PointI pointI = new PointI();
        pointI.x = (int) ((((((double) this.c.x) * Math.pow((double) (1.0f - f), 3.0d)) + (((double) (((float) (this.d.x * 3)) * f)) * Math.pow((double) (1.0f - f), 2.0d))) + ((((double) (this.e.x * 3)) * Math.pow((double) f, 2.0d)) * ((double) (1.0f - f)))) + (((double) this.f.x) * Math.pow((double) f, 3.0d)));
        pointI.y = (int) ((((((double) this.c.y) * Math.pow((double) (1.0f - f), 3.0d)) + (((double) (((float) (this.d.y * 3)) * f)) * Math.pow((double) (1.0f - f), 2.0d))) + ((((double) (this.e.y * 3)) * Math.pow((double) f, 2.0d)) * ((double) (1.0f - f)))) + (((double) this.f.y) * Math.pow((double) f, 3.0d)));
        return pointI;
    }
}
