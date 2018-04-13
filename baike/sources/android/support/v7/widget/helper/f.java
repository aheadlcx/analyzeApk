package android.support.v7.widget.helper;

import android.view.animation.Interpolator;

final class f implements Interpolator {
    f() {
    }

    public float getInterpolation(float f) {
        return (((f * f) * f) * f) * f;
    }
}
