package qsbk.app.utils;

import android.view.animation.Interpolator;

public final class ToastAndDialog$Lofter implements Interpolator {
    double[] a;

    public ToastAndDialog$Lofter() {
        double log = Math.log(0.1d / ((double) Math.abs(1.0f))) / 300.0d;
        if (log > 0.0d) {
            log *= -1.0d;
        }
        this.a = new double[300];
        for (int i = 0; i < 300; i++) {
            this.a[i] = 1.0d + (Math.cos(0.03141592653589793d * ((double) i)) * (Math.pow(2.71d, ((double) i) * log) * -1.0d));
        }
    }

    public final float getInterpolation(float f) {
        float f2 = (float) this.a[this.a.length - 1];
        int floor = (int) Math.floor((double) (((float) this.a.length) * f));
        if (floor < this.a.length) {
            return (float) this.a[floor];
        }
        return f2;
    }
}
