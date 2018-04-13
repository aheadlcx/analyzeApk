package com.budejie.www.activity.view;

import android.view.animation.Interpolator;

class OutlineContainer$1 implements Interpolator {
    final /* synthetic */ OutlineContainer a;

    OutlineContainer$1(OutlineContainer outlineContainer) {
        this.a = outlineContainer;
    }

    public float getInterpolation(float f) {
        float f2 = f - 1.0f;
        return (f2 * (f2 * f2)) + 1.0f;
    }
}
