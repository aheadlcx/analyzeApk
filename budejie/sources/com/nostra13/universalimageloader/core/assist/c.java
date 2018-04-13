package com.nostra13.universalimageloader.core.assist;

public class c {
    private final int a;
    private final int b;

    public c(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    public c(int i, int i2, int i3) {
        if (i3 % 180 == 0) {
            this.a = i;
            this.b = i2;
            return;
        }
        this.a = i2;
        this.b = i;
    }

    public int a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public c a(int i) {
        return new c(this.a / i, this.b / i);
    }

    public c a(float f) {
        return new c((int) (((float) this.a) * f), (int) (((float) this.b) * f));
    }

    public String toString() {
        return this.a + "x" + this.b;
    }
}
