package cn.xiaochuankeji.aop.permission;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.provider.Settings;
import android.provider.Settings.System;
import android.support.v4.content.PermissionChecker;
import java.util.ArrayList;
import java.util.List;

public class f {
    public static boolean a() {
        return VERSION.SDK_INT >= 23;
    }

    @TargetApi(23)
    public static boolean a(Context context, String... strArr) {
        for (String str : strArr) {
            if (a() && str.equals("android.permission.SYSTEM_ALERT_WINDOW")) {
                if (!Settings.canDrawOverlays(context)) {
                    return false;
                }
            } else if (a() && str.equals("android.permission.WRITE_SETTINGS")) {
                if (!System.canWrite(context)) {
                    return false;
                }
            } else if (PermissionChecker.checkSelfPermission(context, str) != 0) {
                return false;
            }
        }
        return true;
    }

    @TargetApi(23)
    public static List<String> a(Activity activity, String... strArr) {
        List arrayList = new ArrayList();
        if (a()) {
            for (String str : strArr) {
                if (a() && str.equals("android.permission.SYSTEM_ALERT_WINDOW")) {
                    if (!Settings.canDrawOverlays(activity)) {
                        arrayList.add(str);
                    }
                } else if (a() && str.equals("android.permission.WRITE_SETTINGS")) {
                    if (!System.canWrite(activity)) {
                        arrayList.add(str);
                    }
                } else if (PermissionChecker.checkSelfPermission(activity, str) != 0) {
                    arrayList.add(str);
                }
            }
        }
        return arrayList;
    }
}
