package qsbk.app.core.widget.toast;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.os.Build.VERSION;
import java.util.List;

public class ToastManager {
    private ToastManager() {
    }

    public static AbstractToast makeText(Context context, CharSequence charSequence, int i) {
        return makeText(context, charSequence, i, true);
    }

    public static AbstractToast makeText(Context context, int i, int i2) throws NotFoundException {
        return makeText(context, context.getResources().getText(i), i2);
    }

    public static AbstractToast makeText(Context context, CharSequence charSequence, int i, boolean z) {
        return (z && a()) ? SystemToastReflection.makeText(context, charSequence, i) : SystemToast.makeText(context, charSequence, i);
    }

    private static boolean a() {
        return VERSION.SDK_INT < 25;
    }

    public static void show(Context context, CharSequence charSequence, int i) {
        show(context, charSequence, i, true);
    }

    public static void show(Context context, CharSequence charSequence, int i, boolean z) {
        show(context, charSequence, i, z, true);
    }

    public static void show(Context context, CharSequence charSequence, int i, boolean z, boolean z2) {
        boolean z3 = true;
        if (z2) {
            z3 = isRunningForeground(context);
        }
        if (z3) {
            makeText(context, charSequence, i, z).show();
        }
    }

    public static boolean isRunningForeground(Context context) {
        if (context == null) {
            return false;
        }
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager != null) {
            List runningTasks = activityManager.getRunningTasks(1);
            if (!(runningTasks == null || runningTasks.isEmpty())) {
                ComponentName componentName = ((RunningTaskInfo) runningTasks.get(0)).topActivity;
                if (componentName != null) {
                    if (context.getPackageName().equals(componentName.getPackageName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
