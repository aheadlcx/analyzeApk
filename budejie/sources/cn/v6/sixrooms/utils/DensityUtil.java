package cn.v6.sixrooms.utils;

import android.app.Activity;
import android.graphics.Rect;
import cn.v6.sdk.sixrooms.coop.V6Coop;

public class DensityUtil {
    private static int a;
    private static float b;
    private static float c;

    public static float getScreentScaledDensity() {
        return V6Coop.getInstance().getContext().getResources().getDisplayMetrics().scaledDensity;
    }

    public static int sp2px(float f) {
        return (int) ((getScreentScaledDensity() * f) + 0.5f);
    }

    public static int px2sp(float f) {
        return (int) ((f / getScreentScaledDensity()) + 0.5f);
    }

    public static int dip2px(float f) {
        if (c == 0.0f) {
            c = getScreenDensity();
        }
        return (int) ((c * f) + 0.5f);
    }

    public static int px2dip(float f) {
        if (c == 0.0f) {
            c = getScreenDensity();
        }
        return (int) ((f / c) + 0.5f);
    }

    public static int getScreenWidth() {
        return V6Coop.getInstance().getContext().getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return V6Coop.getInstance().getContext().getResources().getDisplayMetrics().heightPixels;
    }

    public static float getScreenDensity() {
        if (c == 0.0f) {
            c = V6Coop.getInstance().getContext().getResources().getDisplayMetrics().density;
        }
        return c;
    }

    public static float getScreenDensityDpi() {
        if (b == 0.0f) {
            b = (float) V6Coop.getInstance().getContext().getResources().getDisplayMetrics().densityDpi;
        }
        return b;
    }

    public static int getStatusBarHeight(Activity activity) {
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        if (a == 0) {
            a = rect.top;
        }
        return a;
    }

    public static int getAbsoluteScreenHeight(Activity activity) {
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return rect.bottom;
    }
}
