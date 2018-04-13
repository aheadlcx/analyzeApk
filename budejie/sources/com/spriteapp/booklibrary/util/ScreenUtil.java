package com.spriteapp.booklibrary.util;

public class ScreenUtil {
    public static int getScreenWidth() {
        return AppUtil.getAppContext().getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return AppUtil.getAppContext().getResources().getDisplayMetrics().heightPixels;
    }

    public static float dpToPx(float f) {
        return AppUtil.getAppContext().getResources().getDisplayMetrics().density * f;
    }

    public static int dpToPxInt(float f) {
        return (int) (dpToPx(f) + 0.5f);
    }

    public static float pxToDp(float f) {
        return f / AppUtil.getAppContext().getResources().getDisplayMetrics().density;
    }

    public static int pxToDpInt(float f) {
        return (int) (pxToDp(f) + 0.5f);
    }

    public static float pxToSp(float f) {
        return f / AppUtil.getAppContext().getResources().getDisplayMetrics().scaledDensity;
    }

    public static float spToPx(float f) {
        return AppUtil.getAppContext().getResources().getDisplayMetrics().scaledDensity * f;
    }
}
