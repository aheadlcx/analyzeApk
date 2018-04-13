package cn.v6.sixrooms.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.ActivityManager.RunningTaskInfo;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import java.util.ArrayList;
import java.util.List;

public class ActivityManagerUtils {
    public static boolean isServiceWorked(String str) {
        ArrayList arrayList = (ArrayList) ((ActivityManager) V6Coop.getInstance().getContext().getSystemService("activity")).getRunningServices(30);
        for (int i = 0; i < arrayList.size(); i++) {
            if (((RunningServiceInfo) arrayList.get(i)).service.getClassName().toString().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static String getTopActivity(Activity activity) {
        List runningTasks = ((ActivityManager) V6Coop.getInstance().getContext().getSystemService("activity")).getRunningTasks(1);
        if (runningTasks == null || runningTasks.size() == 0) {
            return null;
        }
        return ((RunningTaskInfo) runningTasks.get(0)).topActivity.getShortClassName().toString();
    }

    public static String getTopPackageName(Activity activity) {
        List runningTasks = ((ActivityManager) V6Coop.getInstance().getContext().getSystemService("activity")).getRunningTasks(1);
        if (runningTasks == null || runningTasks.size() == 0) {
            return null;
        }
        return ((RunningTaskInfo) runningTasks.get(0)).topActivity.getPackageName();
    }
}
