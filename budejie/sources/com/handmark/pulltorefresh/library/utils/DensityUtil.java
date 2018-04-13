package com.handmark.pulltorefresh.library.utils;

import android.content.Context;

public class DensityUtil {
    public static float getScreenDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public static int dip2px(Context context, float f) {
        return (int) ((getScreenDensity(context) * f) + 0.5f);
    }
}
