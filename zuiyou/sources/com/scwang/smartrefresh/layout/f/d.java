package com.scwang.smartrefresh.layout.f;

import android.view.animation.Interpolator;

public class d implements Interpolator {
    private static final float a = (1.0f / a(1.0f));
    private static final float b = (1.0f - (a * a(1.0f)));

    private static float a(float f) {
        float f2 = 8.0f * f;
        if (f2 < 1.0f) {
            return f2 - (1.0f - ((float) Math.exp((double) (-f2))));
        }
        return ((1.0f - ((float) Math.exp((double) (1.0f - f2)))) * (1.0f - 0.36787945f)) + 0.36787945f;
    }

    public float getInterpolation(float f) {
        float a = a * a(f);
        if (a > 0.0f) {
            return a + b;
        }
        return a;
    }
}
