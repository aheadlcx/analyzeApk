package com.umeng.update.util;

import android.content.Context;

public class c {
    private static float a = 1.0f;

    public c(Context context) {
        a(context);
    }

    public static void a(Context context) {
        a = context.getResources().getDisplayMetrics().density;
    }

    public static int a(float f) {
        return (int) ((a * f) + 0.5f);
    }

    public static int b(float f) {
        return (int) ((f / a) + 0.5f);
    }

    public static int c(float f) {
        return (int) ((a * f) + 0.5f);
    }

    public static int d(float f) {
        return (int) ((f / a) + 0.5f);
    }
}
