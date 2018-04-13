package android.support.v7.widget;

class bw {
    public static final int UNDEFINED = Integer.MIN_VALUE;
    private int a = 0;
    private int b = 0;
    private int c = Integer.MIN_VALUE;
    private int d = Integer.MIN_VALUE;
    private int e = 0;
    private int f = 0;
    private boolean g = false;
    private boolean h = false;

    bw() {
    }

    public int getLeft() {
        return this.a;
    }

    public int getRight() {
        return this.b;
    }

    public int getStart() {
        return this.g ? this.b : this.a;
    }

    public int getEnd() {
        return this.g ? this.a : this.b;
    }

    public void setRelative(int i, int i2) {
        this.c = i;
        this.d = i2;
        this.h = true;
        if (this.g) {
            if (i2 != Integer.MIN_VALUE) {
                this.a = i2;
            }
            if (i != Integer.MIN_VALUE) {
                this.b = i;
                return;
            }
            return;
        }
        if (i != Integer.MIN_VALUE) {
            this.a = i;
        }
        if (i2 != Integer.MIN_VALUE) {
            this.b = i2;
        }
    }

    public void setAbsolute(int i, int i2) {
        this.h = false;
        if (i != Integer.MIN_VALUE) {
            this.e = i;
            this.a = i;
        }
        if (i2 != Integer.MIN_VALUE) {
            this.f = i2;
            this.b = i2;
        }
    }

    public void setDirection(boolean z) {
        if (z != this.g) {
            this.g = z;
            if (!this.h) {
                this.a = this.e;
                this.b = this.f;
            } else if (z) {
                this.a = this.d != Integer.MIN_VALUE ? this.d : this.e;
                this.b = this.c != Integer.MIN_VALUE ? this.c : this.f;
            } else {
                this.a = this.c != Integer.MIN_VALUE ? this.c : this.e;
                this.b = this.d != Integer.MIN_VALUE ? this.d : this.f;
            }
        }
    }
}
