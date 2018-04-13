package com.scwang.smartrefresh.layout.f;

import android.content.res.Resources;

public class b {
    float a = Resources.getSystem().getDisplayMetrics().density;

    public static int a(float f) {
        return (int) (0.5f + (Resources.getSystem().getDisplayMetrics().density * f));
    }

    public static float b(float f) {
        return f / Resources.getSystem().getDisplayMetrics().density;
    }

    public int c(float f) {
        return (int) (0.5f + (this.a * f));
    }
}
