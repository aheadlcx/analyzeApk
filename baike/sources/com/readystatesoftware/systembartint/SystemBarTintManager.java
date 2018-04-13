package com.readystatesoftware.systembartint;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.FrameLayout;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import qsbk.app.core.utils.AppUtils;

public class SystemBarTintManager {
    public static final int DEFAULT_TINT_COLOR = -1728053248;
    private static String a;
    public static boolean sHasSmartbar;
    public static boolean sIsMiuiV6;
    public static boolean sPostLollipop;
    private final SystemBarConfig b;
    private boolean c;
    private boolean d;
    private boolean e;
    private boolean f;
    private View g;
    private View h;
    private Activity i;

    public static class SystemBarConfig {
        private final boolean a;
        private final boolean b;
        private final int c;
        private final int d;
        private final boolean e;
        private final int f;
        private final int g;
        private final boolean h;
        private final float i;

        private SystemBarConfig(Activity activity, boolean z, boolean z2) {
            boolean z3 = true;
            Resources resources = activity.getResources();
            this.h = resources.getConfiguration().orientation == 1;
            this.i = a(activity);
            this.c = a(resources, "status_bar_height");
            this.d = a((Context) activity);
            this.f = b(activity);
            this.g = c(activity);
            if (this.f <= 0) {
                z3 = false;
            }
            this.e = z3;
            this.a = z;
            this.b = z2;
        }

        @TargetApi(14)
        private int a(Context context) {
            if (VERSION.SDK_INT < 14) {
                return 0;
            }
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(16843499, typedValue, true);
            return TypedValue.complexToDimensionPixelSize(typedValue.data, context.getResources().getDisplayMetrics());
        }

        @TargetApi(14)
        private int b(Context context) {
            Resources resources = context.getResources();
            if (VERSION.SDK_INT < 14 || !d(context)) {
                return 0;
            }
            String str;
            if (this.h) {
                str = "navigation_bar_height";
            } else {
                str = "navigation_bar_height_landscape";
            }
            return a(resources, str);
        }

        @TargetApi(14)
        private int c(Context context) {
            Resources resources = context.getResources();
            if (VERSION.SDK_INT < 14 || !d(context)) {
                return 0;
            }
            return a(resources, "navigation_bar_width");
        }

        @TargetApi(14)
        private boolean d(Context context) {
            boolean z = true;
            Resources resources = context.getResources();
            int identifier = resources.getIdentifier("config_showNavigationBar", "bool", "android");
            if (identifier != 0) {
                boolean z2 = resources.getBoolean(identifier);
                if ("1".equals(SystemBarTintManager.a)) {
                    return false;
                }
                if ("0".equals(SystemBarTintManager.a)) {
                    return true;
                }
                return z2;
            }
            if (ViewConfiguration.get(context).hasPermanentMenuKey()) {
                z = false;
            }
            return z;
        }

        private int a(Resources resources, String str) {
            int identifier = resources.getIdentifier(str, "dimen", "android");
            if (identifier > 0) {
                return resources.getDimensionPixelSize(identifier);
            }
            return 0;
        }

        @SuppressLint({"NewApi"})
        private float a(Activity activity) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            if (VERSION.SDK_INT >= 16) {
                activity.getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
            } else {
                activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            }
            return Math.min(((float) displayMetrics.widthPixels) / displayMetrics.density, ((float) displayMetrics.heightPixels) / displayMetrics.density);
        }

        public boolean isNavigationAtBottom() {
            return this.i >= 600.0f || this.h;
        }

        public int getStatusBarHeight() {
            return this.c;
        }

        public int getActionBarHeight() {
            return this.d;
        }

        public boolean hasNavigtionBar() {
            return this.e;
        }

        public int getNavigationBarHeight() {
            return this.f;
        }

        public int getNavigationBarWidth() {
            return this.g;
        }

        public int getPixelInsetTop(boolean z) {
            int i = 0;
            int i2 = this.a ? this.c : 0;
            if (z) {
                i = this.d;
            }
            return i + i2;
        }

        public int getPixelInsetBottom() {
            if (this.b && isNavigationAtBottom()) {
                return this.f;
            }
            return 0;
        }

        public int getPixelInsetRight() {
            if (!this.b || isNavigationAtBottom()) {
                return 0;
            }
            return this.g;
        }
    }

    static {
        boolean z;
        if (AppUtils.isSupportForTransparentStatusBar()) {
            try {
                Class cls = Class.forName("android.os.SystemProperties");
                Method declaredMethod = cls.getDeclaredMethod("get", new Class[]{String.class});
                declaredMethod.setAccessible(true);
                a = (String) declaredMethod.invoke(null, new Object[]{"qemu.hw.mainkeys"});
                sIsMiuiV6 = "V6".equals(declaredMethod.invoke(cls, new Object[]{"ro.miui.ui.version.name"}));
                sHasSmartbar = b();
            } catch (Throwable th) {
                a = null;
            }
        }
        if (VERSION.SDK_INT >= 21) {
            z = true;
        } else {
            z = false;
        }
        sPostLollipop = z;
    }

    @TargetApi(19)
    public SystemBarTintManager(Activity activity) {
        this.i = activity;
        Window window = activity.getWindow();
        ViewGroup viewGroup = (ViewGroup) window.getDecorView();
        if (AppUtils.isSupportForTransparentStatusBar()) {
            TypedArray obtainStyledAttributes = activity.obtainStyledAttributes(new int[]{16843759, 16843760});
            try {
                this.c = obtainStyledAttributes.getBoolean(0, false);
                this.d = obtainStyledAttributes.getBoolean(1, false);
                LayoutParams attributes = window.getAttributes();
                if ((attributes.flags & 67108864) != 0) {
                    this.c = true;
                }
                if ((attributes.flags & 134217728) != 0) {
                    this.d = true;
                }
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
        if (sPostLollipop) {
            window.addFlags(Integer.MIN_VALUE);
            window.clearFlags(67108864);
        }
        this.b = new SystemBarConfig(activity, this.c, this.d);
        if (!this.b.hasNavigtionBar()) {
            this.d = false;
        }
        if (this.c) {
            a(activity, viewGroup);
        }
        if (this.d) {
            b(activity, viewGroup);
        }
    }

    public void setStatusBarDarkMode(boolean z, Activity activity) {
        int i = 0;
        if (sIsMiuiV6) {
            Class cls = activity.getWindow().getClass();
            try {
                Class cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                int i2 = cls2.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(cls2);
                Method method = cls.getMethod("setExtraFlags", new Class[]{Integer.TYPE, Integer.TYPE});
                Window window = activity.getWindow();
                Object[] objArr = new Object[2];
                if (z) {
                    i = i2;
                }
                objArr[0] = Integer.valueOf(i);
                objArr[1] = Integer.valueOf(i2);
                method.invoke(window, objArr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (isMeizuMobile()) {
            setStatusBarDarkMode4Meizu(activity.getWindow(), z);
        }
    }

    public static boolean isMeizuMobile() {
        return "meizu".equalsIgnoreCase(Build.BRAND);
    }

    private static boolean b() {
        try {
            return ((Boolean) Class.forName("android.os.Build").getMethod("hasSmartBar", new Class[0]).invoke(null, new Object[0])).booleanValue();
        } catch (Exception e) {
            if (Build.DEVICE.equals("mx2")) {
                return true;
            }
            if (Build.DEVICE.equals("mx") || Build.DEVICE.equals("m9")) {
                return false;
            }
            return false;
        }
    }

    public static boolean setStatusBarDarkMode4Meizu(Window window, boolean z) {
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
                i |= i2;
            } else {
                i = (i ^ -1) & i2;
            }
            declaredField2.setInt(attributes, i);
            window.setAttributes(attributes);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void setStatusBarTintEnabled(boolean z) {
        this.e = z;
        if (this.c) {
            this.g.setVisibility(z ? 0 : 8);
        }
    }

    public void setNavigationBarTintEnabled(boolean z) {
        this.f = z;
        if (this.d) {
            this.h.setVisibility(z ? 0 : 8);
        }
    }

    public void setTintColor(int i) {
        setStatusBarTintColor(i);
        setNavigationBarTintColor(i);
    }

    public void setTintResource(int i) {
        setStatusBarTintResource(i);
        a(i);
    }

    public void setTintDrawable(Drawable drawable) {
        a(drawable);
        b(drawable);
    }

    public void setTintAlpha(float f) {
        setStatusBarAlpha(f);
        setNavigationBarAlpha(f);
    }

    @SuppressLint({"NewApi"})
    public void setStatusBarTintColor(int i) {
        if (sPostLollipop && this.i != null) {
            this.i.getWindow().setStatusBarColor(i);
        } else if (this.c) {
            this.g.setBackgroundColor(i);
        }
    }

    public void setStatusBarTintResource(int i) {
        if (this.c) {
            this.g.setBackgroundResource(i);
        }
    }

    private void a(Drawable drawable) {
        if (this.c) {
            this.g.setBackgroundDrawable(drawable);
        }
    }

    @TargetApi(11)
    public void setStatusBarAlpha(float f) {
        if (this.c && VERSION.SDK_INT >= 11) {
            this.g.setAlpha(f);
        }
    }

    public void setNavigationBarTintColor(int i) {
        if (this.d) {
            this.h.setBackgroundColor(i);
        }
    }

    private void a(int i) {
        if (this.d) {
            this.h.setBackgroundResource(i);
        }
    }

    private void b(Drawable drawable) {
        if (this.d) {
            this.h.setBackgroundDrawable(drawable);
        }
    }

    @TargetApi(11)
    public void setNavigationBarAlpha(float f) {
        if (this.d && VERSION.SDK_INT >= 11) {
            this.h.setAlpha(f);
        }
    }

    public SystemBarConfig getConfig() {
        return this.b;
    }

    public boolean isStatusBarTintEnabled() {
        return this.e;
    }

    public boolean isNavBarTintEnabled() {
        return this.f;
    }

    private void a(Context context, ViewGroup viewGroup) {
        this.g = new View(context);
        ViewGroup.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, this.b.getStatusBarHeight());
        layoutParams.gravity = 48;
        if (this.d && !this.b.isNavigationAtBottom()) {
            layoutParams.rightMargin = this.b.getNavigationBarWidth();
        }
        this.g.setLayoutParams(layoutParams);
        this.g.setBackgroundColor(DEFAULT_TINT_COLOR);
        this.g.setVisibility(8);
        viewGroup.addView(this.g);
    }

    private void b(Context context, ViewGroup viewGroup) {
        ViewGroup.LayoutParams layoutParams;
        this.h = new View(context);
        if (this.b.isNavigationAtBottom()) {
            layoutParams = new FrameLayout.LayoutParams(-1, this.b.getNavigationBarHeight());
            layoutParams.gravity = 80;
        } else {
            layoutParams = new FrameLayout.LayoutParams(this.b.getNavigationBarWidth(), -1);
            layoutParams.gravity = 5;
        }
        this.h.setLayoutParams(layoutParams);
        this.h.setBackgroundColor(DEFAULT_TINT_COLOR);
        this.h.setVisibility(8);
        viewGroup.addView(this.h);
    }
}
