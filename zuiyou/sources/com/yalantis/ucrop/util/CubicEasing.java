package com.yalantis.ucrop.util;

public final class CubicEasing {
    public static float easeOut(float f, float f2, float f3, float f4) {
        float f5 = (f / f4) - 1.0f;
        return (((f5 * (f5 * f5)) + 1.0f) * f3) + f2;
    }

    public static float easeIn(float f, float f2, float f3, float f4) {
        float f5 = f / f4;
        return (f5 * ((f3 * f5) * f5)) + f2;
    }

    public static float easeInOut(float f, float f2, float f3, float f4) {
        float f5 = f / (f4 / 2.0f);
        if (f5 < 1.0f) {
            return (f5 * (((f3 / 2.0f) * f5) * f5)) + f2;
        }
        f5 -= 2.0f;
        return (((f5 * (f5 * f5)) + 2.0f) * (f3 / 2.0f)) + f2;
    }
}
