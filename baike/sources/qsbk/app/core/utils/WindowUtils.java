package qsbk.app.core.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import qsbk.app.core.R;

public class WindowUtils {
    public static int getScreenWidth() {
        return AppUtils.getInstance().getAppContext().getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return AppUtils.getInstance().getAppContext().getResources().getDisplayMetrics().heightPixels;
    }

    public static int getScreenExactHeight() {
        if (VERSION.SDK_INT < 17) {
            return AppUtils.getInstance().getAppContext().getResources().getDisplayMetrics().heightPixels;
        }
        Context appContext = AppUtils.getInstance().getAppContext();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) appContext.getSystemService("window")).getDefaultDisplay().getRealMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public static int getScreenExactWidth() {
        if (VERSION.SDK_INT < 17) {
            return AppUtils.getInstance().getAppContext().getResources().getDisplayMetrics().widthPixels;
        }
        Context appContext = AppUtils.getInstance().getAppContext();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) appContext.getSystemService("window")).getDefaultDisplay().getRealMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static int dp2Px(int i) {
        return Math.round(AppUtils.getInstance().getAppContext().getResources().getDisplayMetrics().density * ((float) i));
    }

    public static int getStatusBarHeight() {
        Resources resources = AppUtils.getInstance().getAppContext().getResources();
        int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return resources.getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static int getNavigationBarHeight() {
        Resources resources = AppUtils.getInstance().getAppContext().getResources();
        int identifier = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (identifier > 0) {
            int dimensionPixelSize = resources.getDimensionPixelSize(identifier);
            if (dimensionPixelSize > 0) {
                return dimensionPixelSize;
            }
        }
        return 0;
    }

    public static void setTransparentNavigationBar(Activity activity, boolean z) {
        if (z) {
            setTransparentNavigationBar(activity);
        } else {
            setNonTransparentNavigationBar(activity);
        }
    }

    public static void setTransparentNavigationBar(Activity activity) {
        if (activity != null) {
            Window window = activity.getWindow();
            if (window != null) {
                addSupportTransparentNavigationBar(window);
            }
        }
    }

    public static void hideNavigationBar(Window window) {
        int i = 0;
        if (VERSION.SDK_INT >= 16) {
            i = 770;
            if (VERSION.SDK_INT >= 19) {
                i = 2818;
            }
        }
        if (i != 0) {
            window.getDecorView().setSystemUiVisibility(i);
        }
    }

    public static void addSupportTransparentNavigationBar(Window window) {
        if (window != null && AppUtils.isSupportForTransparentStatusBar()) {
            if (isXiaoMiMix()) {
                setBlackTranslucentNavigationBar(window);
            } else {
                setTransparentNavigationBar(window);
            }
        }
    }

    public static void setNonTransparentNavigationBar(Activity activity) {
        if (activity != null) {
            Window window = activity.getWindow();
            if (!AppUtils.isSupportForTransparentStatusBar()) {
                return;
            }
            if (isXiaoMiMix()) {
                setWhiteNavigationBar(activity, window);
            } else {
                setBlackNavigationBar(activity, window);
            }
        }
    }

    public static boolean isXiaoMiMix() {
        return "MIX".equals(DeviceUtils.getDeviceModel());
    }

    public static void setBlackNavigationBar(Activity activity, Window window) {
        setNavigationBarColor(window, activity.getResources().getColor(R.color.black));
    }

    public static void setWhiteNavigationBar(Activity activity, Window window) {
        setNavigationBarColor(window, activity.getResources().getColor(R.color.white));
    }

    public static void setBlackTranslucentNavigationBar(Window window) {
        setNavigationBarColor(window, AppUtils.getInstance().getAppContext().getResources().getColor(R.color.black_30_percent_transparent));
    }

    public static void setTransparentNavigationBar(Window window) {
        setNavigationBarColor(window, AppUtils.getInstance().getAppContext().getResources().getColor(R.color.transparent));
    }

    public static void setStatusBarColor(Activity activity, Window window, int i, boolean z) {
        if (VERSION.SDK_INT >= 21 && z) {
            window.addFlags(Integer.MIN_VALUE);
            window.clearFlags(67108864);
            window.setStatusBarColor(i);
        } else if (AppUtils.isSupportForTransparentStatusBar()) {
            window.addFlags(67108864);
            SystemBarTintManager systemBarTintManager = new SystemBarTintManager(activity);
            systemBarTintManager.setStatusBarTintEnabled(true);
            systemBarTintManager.setStatusBarTintColor(i);
        }
    }

    public static void setStatusBarColor(Activity activity, Window window, int i) {
        setStatusBarColor(activity, window, i, true);
    }

    public static void setNavigationBarColor(Window window, int i, boolean z) {
        if (VERSION.SDK_INT >= 21 && z) {
            window.addFlags(Integer.MIN_VALUE);
            window.clearFlags(134217728);
            window.setNavigationBarColor(i);
        }
    }

    public static void fullscreen(Window window) {
        int i = 0;
        window.addFlags(1024);
        if (VERSION.SDK_INT >= 16) {
            i = 774;
            if (VERSION.SDK_INT >= 19) {
                i = 2822;
            }
        }
        if (i != 0) {
            window.getDecorView().setSystemUiVisibility(i);
        }
    }

    public static void setNavigationBarColor(Window window, int i) {
        setNavigationBarColor(window, i, true);
    }

    public static int px2sp(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    public static int sp2px(Context context, float f) {
        return (int) ((context.getResources().getDisplayMetrics().scaledDensity * f) + 0.5f);
    }

    public static int px2dp(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
