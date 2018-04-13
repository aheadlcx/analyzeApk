package com.slidingmenu.lib;

import android.view.animation.Interpolator;

final class a implements Interpolator {
    a() {
    }

    public final float getInterpolation(float f) {
        float f2 = f - 1.0f;
        return (f2 * (((f2 * f2) * f2) * f2)) + 1.0f;
    }
}
