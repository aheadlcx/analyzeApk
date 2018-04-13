package qsbk.app.video;

import android.os.Handler;

public abstract class LightActuator implements Runnable {
    public static final int DARK_COLOR = 0;
    private final int a;
    private final int b;
    private Handler c;
    private int d;
    private boolean e;
    public final int max;

    public abstract void onColorUpdate(int i);

    public LightActuator() {
        this(10, 30);
    }

    public LightActuator(int i, int i2) {
        this.c = new Handler();
        this.a = i;
        this.max = i2;
        this.b = (i2 - i) / 15;
        this.d = i;
    }

    public int getBrightness() {
        return this.d;
    }

    public void reset() {
        this.c.removeCallbacks(this);
        this.d = this.a;
        onColorUpdate(((((100 - this.d) * 255) / 100) << 24) | 0);
    }

    public void light() {
        this.e = true;
        run();
    }

    public void offLight() {
        this.e = false;
        run();
    }

    public boolean isMaxLight() {
        return this.d >= this.max;
    }

    public void run() {
        this.c.removeCallbacks(this);
        if (this.e) {
            this.d += this.b;
            if (this.d < this.max) {
                this.c.postDelayed(this, 16);
            } else {
                this.d = this.max;
            }
        } else {
            this.d -= this.b;
            if (this.d > this.a) {
                this.c.postDelayed(this, 16);
            } else {
                this.d = this.a;
            }
        }
        onColorUpdate(((((100 - this.d) * 255) / 100) << 24) | 0);
    }
}
