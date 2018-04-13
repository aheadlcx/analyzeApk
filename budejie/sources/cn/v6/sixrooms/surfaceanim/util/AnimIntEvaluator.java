package cn.v6.sixrooms.surfaceanim.util;

public class AnimIntEvaluator {
    private int a;
    private int b;
    private int c;
    private int d;
    private int e = 1;
    private int f;
    private int g;

    public AnimIntEvaluator(int i) {
        resetEvaluator(Integer.MAX_VALUE, Integer.MAX_VALUE, i, i);
    }

    public AnimIntEvaluator(int i, int i2, int i3, int i4) {
        resetEvaluator(i, i2, i3, i4);
    }

    public AnimIntEvaluator(int i, int i2, int i3, int i4, int i5) {
        resetEvaluator(i, i2, i3, i4, i5);
    }

    public void resetEvaluator(int i, int i2, int i3, int i4) {
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.f = i2 - i;
        this.g = i4 - i3;
    }

    public void resetEvaluator(int i, int i2, int i3, int i4, int i5) {
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        if (this.e > 1) {
            this.e = i5;
        }
        this.f = (i2 - i) / this.e;
        this.g = i4 - i3;
    }

    public void skewingValue(int i) {
        this.c += i;
        this.d += i;
    }

    public int evaluate(int i) {
        if (i <= this.a) {
            return this.c;
        }
        if (i >= this.b) {
            return this.d;
        }
        float f = ((float) ((i - this.a) / this.e)) / ((float) this.f);
        return (int) ((this.g < 0 ? Math.floor((double) (f * ((float) this.g))) : Math.ceil((double) (f * ((float) this.g)))) + ((double) this.c));
    }
}
