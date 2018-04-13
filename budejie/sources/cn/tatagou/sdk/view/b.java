package cn.tatagou.sdk.view;

import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import cn.tatagou.sdk.R;
import cn.tatagou.sdk.pojo.Config;
import cn.tatagou.sdk.pojo.TtgTitleBar;
import com.alibaba.baichuan.android.trade.constants.AlibcConstants;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class b {
    public static void onSetStatusBarColor(Activity activity) {
        if (activity != null && TtgTitleBar.getInstance().getStatusBarBgColor() != -1) {
            a(activity, activity.findViewById(R.id.ttg_status_view));
        }
    }

    public static void onSetStatusBarColor(Activity activity, View view) {
        if (activity != null && TtgTitleBar.getInstance().getStatusBarBgColor() != -1 && view != null) {
            a(activity, view.findViewById(R.id.ttg_status_view));
        }
    }

    private static void a(Activity activity, View view) {
        if (view != null) {
            if (VERSION.SDK_INT >= 21) {
                Window window = activity.getWindow();
                window.clearFlags(67108864);
                window.getDecorView().setSystemUiVisibility(1280);
                window.addFlags(Integer.MIN_VALUE);
                window.setStatusBarColor(0);
                b(activity, view);
            } else if (VERSION.SDK_INT >= 19) {
                activity.getWindow().addFlags(67108864);
                b(activity, view);
            } else {
                view.setVisibility(8);
            }
            StatusBarLightMode(activity);
        }
    }

    private static void b(Activity activity, View view) {
        view.setVisibility(0);
        if (Config.getInstance().getPhoneSys() == -1) {
            StatusBarLightMode(activity);
        } else {
            StatusBarLightMode(activity, Config.getInstance().getPhoneSys());
        }
        if (Config.getInstance().getPhoneSys() == 0) {
            TtgTitleBar.getInstance().setStatusBarBgColor(-16777216);
        }
        view.setBackgroundColor(TtgTitleBar.getInstance().getStatusBarBgColor());
    }

    public static void onSetSpStatusBarColor(Activity activity, View view) {
        if (activity != null && view != null) {
            if (VERSION.SDK_INT >= 21) {
                activity.getWindow().addFlags(67108864);
                activity.getWindow().addFlags(134217728);
                view.setVisibility(0);
                view.setBackgroundColor(TtgTitleBar.getInstance().getStatusBarBgColor());
                return;
            }
            view.setVisibility(8);
        }
    }

    public static boolean MIUISetStatusBarLightMode(Window window, boolean z) {
        if (window == null) {
            return false;
        }
        Class cls = window.getClass();
        try {
            Class cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            int i = cls2.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(cls2);
            Method method = cls.getMethod("setExtraFlags", new Class[]{Integer.TYPE, Integer.TYPE});
            if (z) {
                method.invoke(window, new Object[]{Integer.valueOf(i), Integer.valueOf(i)});
                return true;
            }
            method.invoke(window, new Object[]{Integer.valueOf(0), Integer.valueOf(i)});
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean FlymeSetStatusBarLightMode(Window window, boolean z) {
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

    public static int StatusBarLightMode(Activity activity) {
        int i = 1;
        if (TtgTitleBar.getInstance().isChangeStatusBarFontColor()) {
            if (VERSION.SDK_INT >= 19) {
                if (!MIUISetStatusBarLightMode(activity.getWindow(), true)) {
                    if (FlymeSetStatusBarLightMode(activity.getWindow(), true)) {
                        i = 2;
                    } else if (VERSION.SDK_INT >= 23) {
                        activity.getWindow().getDecorView().setSystemUiVisibility(9216);
                        i = 3;
                    }
                }
                Config.getInstance().setPhoneSys(i);
                return i;
            }
            i = 0;
            Config.getInstance().setPhoneSys(i);
            return i;
        }
        Config.getInstance().setPhoneSys(4);
        return 4;
    }

    public static void StatusBarLightMode(Activity activity, int i) {
        switch (i) {
            case 1:
                MIUISetStatusBarLightMode(activity.getWindow(), true);
                return;
            case 2:
                FlymeSetStatusBarLightMode(activity.getWindow(), true);
                return;
            case 3:
                if (VERSION.SDK_INT >= 23) {
                    activity.getWindow().getDecorView().setSystemUiVisibility(9216);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public static void StatusBarDarkMode(Activity activity, int i) {
        if (i == 1) {
            MIUISetStatusBarLightMode(activity.getWindow(), false);
        } else if (i == 2) {
            FlymeSetStatusBarLightMode(activity.getWindow(), false);
        } else if (i == 3) {
            activity.getWindow().getDecorView().setSystemUiVisibility(0);
        }
    }

    public static int getStatusBarHeight(Context context) {
        int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", AlibcConstants.PF_ANDROID);
        if (identifier > 0) {
            return context.getResources().getDimensionPixelOffset(identifier);
        }
        return 0;
    }
}
