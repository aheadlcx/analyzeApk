package cn.v6.sixrooms.surfaceanim.util;

public class AnimFloatEvaluator {
    private int a;
    private int b;
    private float c;
    private float d;
    private int e = 1;
    private int f;
    private float g;

    public AnimFloatEvaluator(float f) {
        resetEvaluator(Integer.MAX_VALUE, Integer.MAX_VALUE, f, f);
    }

    public AnimFloatEvaluator(int i, int i2, float f, float f2) {
        resetEvaluator(i, i2, f, f2);
    }

    public AnimFloatEvaluator(int i, int i2, float f, float f2, int i3) {
        resetEvaluator(i, i2, f, f2, i3);
    }

    public void resetEvaluator(int i, int i2, float f, float f2) {
        this.a = i;
        this.b = i2;
        this.c = f;
        this.d = f2;
        this.f = i2 - i;
        this.g = f2 - f;
    }

    public void resetEvaluator(int i, int i2, float f, float f2, int i3) {
        this.a = i;
        this.b = i2;
        this.c = f;
        this.d = f2;
        if (this.e > 1) {
            this.e = i3;
        }
        this.f = (i2 - i) / this.e;
        this.g = f2 - f;
    }

    public void skewingValue(float f) {
        this.c += f;
        this.d += f;
    }

    public float evaluate(int i) {
        if (i <= this.a) {
            return this.c;
        }
        if (i >= this.b) {
            return this.d;
        }
        float f = ((float) ((i - this.a) / this.e)) / ((float) this.f);
        return (f * this.g) + this.c;
    }
}
