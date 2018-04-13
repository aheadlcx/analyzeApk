package cn.v6.sixrooms.surfaceanim.util;

public class FPSDetectionUtil {
    private int a = 24;
    private long b;
    private int c;
    private int d = ((int) Math.ceil((double) (1000 / this.a)));
    private int e;

    public void setFPS(int i) {
        this.a = i;
        this.d = (int) Math.ceil((double) (1000 / i));
    }

    public void dropAnchor() {
        if (this.b == 0) {
            this.b = System.currentTimeMillis();
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        int i = (int) (currentTimeMillis - this.b);
        this.b = currentTimeMillis;
        int i2 = i - this.d;
        if (i2 > 0) {
            this.c = i2 + this.c;
            this.e = this.c / this.d;
            this.c -= this.e * this.d;
        }
    }

    public void reset() {
        this.b = 0;
        this.e = 0;
    }

    public int getSkipFramesCount() {
        return this.e;
    }
}
