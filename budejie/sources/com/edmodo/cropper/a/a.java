package com.edmodo.cropper.a;

import android.graphics.Rect;

public class a {
    public static float a(float f, float f2, float f3, float f4) {
        return (f3 - f) / (f4 - f2);
    }

    public static float a(Rect rect) {
        return ((float) rect.width()) / ((float) rect.height());
    }

    public static float b(float f, float f2, float f3, float f4) {
        return f2 - ((f3 - f) * f4);
    }

    public static float c(float f, float f2, float f3, float f4) {
        return f3 - ((f2 - f) / f4);
    }

    public static float d(float f, float f2, float f3, float f4) {
        return ((f3 - f2) * f4) + f;
    }

    public static float e(float f, float f2, float f3, float f4) {
        return ((f3 - f) / f4) + f2;
    }

    public static float a(float f, float f2, float f3) {
        return (f2 - f) * f3;
    }

    public static float b(float f, float f2, float f3) {
        return (f2 - f) / f3;
    }
}
