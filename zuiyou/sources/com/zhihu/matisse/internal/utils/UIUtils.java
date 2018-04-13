package com.zhihu.matisse.internal.utils;

import android.content.Context;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class UIUtils {
    public static int spanCount(Context context, int i) {
        int round = Math.round(((float) context.getResources().getDisplayMetrics().widthPixels) / ((float) i));
        if (round == 0) {
            return 1;
        }
        return round;
    }

    public static void setStatusBar(Window window, boolean z) {
        if (VERSION.SDK_INT >= 23) {
            window.addFlags(Integer.MIN_VALUE);
            window.clearFlags(67108864);
            window.setStatusBarColor(z ? Color.parseColor("#1f1f2a") : Color.parseColor("#ffffff"));
            setDarkMode(window, z);
        }
    }

    private static void setDarkMode(Window window, boolean z) {
        if (VERSION.SDK_INT >= 23) {
            View decorView = window.getDecorView();
            if (decorView != null) {
                int systemUiVisibility = decorView.getSystemUiVisibility();
                if (z) {
                    systemUiVisibility &= -8193;
                } else {
                    systemUiVisibility |= 8192;
                }
                decorView.setSystemUiVisibility(systemUiVisibility);
            }
            String romInfo = Platform.getRomInfo();
            if (romInfo.equals(Platform.MIUI)) {
                MIUISetStatusBarLightMode(window, z);
            } else if (romInfo.equals(Platform.FLYME)) {
                FlymeSetStatusBarLightMode(window, z);
            }
        }
    }

    private static boolean MIUISetStatusBarLightMode(Window window, boolean z) {
        if (window == null) {
            return false;
        }
        Class cls = window.getClass();
        try {
            Class cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            int i = cls2.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(cls2);
            Method method = cls.getMethod("setExtraFlags", new Class[]{Integer.TYPE, Integer.TYPE});
            if (z) {
                method.invoke(window, new Object[]{Integer.valueOf(0), Integer.valueOf(i)});
                return true;
            }
            method.invoke(window, new Object[]{Integer.valueOf(i), Integer.valueOf(i)});
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean FlymeSetStatusBarLightMode(Window window, boolean z) {
        if (window == null) {
            return false;
        }
        try {
            LayoutParams attributes = window.getAttributes();
            Field declaredField = LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
            Field declaredField2 = LayoutParams.class.getDeclaredField("meizuFlags");
            declaredField.setAccessible(true);
            declaredField2.setAccessible(true);
            int i = declaredField.getInt(null);
            int i2 = declaredField2.getInt(attributes);
            if (z) {
                i = (i ^ -1) & i2;
            } else {
                i |= i2;
            }
            declaredField2.setInt(attributes, i);
            window.setAttributes(attributes);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
