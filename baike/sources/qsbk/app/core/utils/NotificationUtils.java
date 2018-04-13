package qsbk.app.core.utils;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build.VERSION;
import java.lang.reflect.InvocationTargetException;

public class NotificationUtils {
    private NotificationUtils() {
    }

    public static final boolean isNotificationAllow(Context context) {
        if (context == null || VERSION.SDK_INT < 19) {
            return true;
        }
        Context applicationContext = context.getApplicationContext();
        AppOpsManager appOpsManager = (AppOpsManager) applicationContext.getSystemService("appops");
        ApplicationInfo applicationInfo = applicationContext.getApplicationInfo();
        String packageName = applicationContext.getPackageName();
        int i = applicationInfo.uid;
        try {
            return ((Integer) Class.forName(AppOpsManager.class.getName()).getMethod("checkOpNoThrow", new Class[]{Integer.TYPE, Integer.TYPE, String.class}).invoke(appOpsManager, new Object[]{Integer.valueOf(((Integer) Class.forName(AppOpsManager.class.getName()).getDeclaredField("OP_POST_NOTIFICATION").get(Integer.class)).intValue()), Integer.valueOf(i), packageName})).intValue() == 0;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return true;
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
            return true;
        } catch (NoSuchFieldException e3) {
            e3.printStackTrace();
            return true;
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
            return true;
        } catch (IllegalAccessException e5) {
            e5.printStackTrace();
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            return true;
        }
    }
}
