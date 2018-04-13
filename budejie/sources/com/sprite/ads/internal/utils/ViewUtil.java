package com.sprite.ads.internal.utils;

import android.content.res.Resources;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.view.View;
import java.util.concurrent.atomic.AtomicInteger;

public class ViewUtil {
    public static Float DENSITY = Float.valueOf(Resources.getSystem().getDisplayMetrics().density);
    public static int SCREEN_HEIGHT = Resources.getSystem().getDisplayMetrics().heightPixels;
    public static int SCREEN_WIDTH = Resources.getSystem().getDisplayMetrics().widthPixels;
    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    public static int dip2px(float f) {
        return (int) ((Resources.getSystem().getDisplayMetrics().density * f) + 0.5f);
    }

    public static int generateViewId() {
        if (VERSION.SDK_INT >= 17) {
            return View.generateViewId();
        }
        int i;
        int i2;
        do {
            i = sNextGeneratedId.get();
            i2 = i + 1;
            if (i2 > ViewCompat.MEASURED_SIZE_MASK) {
                i2 = 1;
            }
        } while (!sNextGeneratedId.compareAndSet(i, i2));
        return i;
    }

    public static int px2dip(float f) {
        return (int) ((f / Resources.getSystem().getDisplayMetrics().density) + 0.5f);
    }

    public static int sp2px(float f) {
        return (int) (Resources.getSystem().getDisplayMetrics().scaledDensity * f);
    }
}
