package android.support.v4.view;

import android.view.animation.Interpolator;

final class l implements Interpolator {
    l() {
    }

    public float getInterpolation(float f) {
        float f2 = f - 1.0f;
        return (f2 * (((f2 * f2) * f2) * f2)) + 1.0f;
    }
}
