package com.meizu.cloud.pushsdk.util;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Process;
import android.text.TextUtils;
import com.meizu.cloud.a.a;
import com.meizu.cloud.pushsdk.base.BuildExt;
import com.meizu.cloud.pushsdk.base.DeviceUtils;
import com.meizu.cloud.pushsdk.base.SystemProperties;
import java.util.List;

public class MzSystemUtils {
    private static final String TAG = "MzSystemUtils";

    private static String getServicesByPackageName(Context context, String str) {
        ServiceInfo[] serviceInfoArr;
        try {
            serviceInfoArr = context.getPackageManager().getPackageInfo(str, 4).services;
        } catch (NameNotFoundException e) {
            serviceInfoArr = null;
        }
        if (serviceInfoArr == null) {
            return null;
        }
        for (int i = 0; i < serviceInfoArr.length; i++) {
            if ("com.meizu.cloud.pushsdk.pushservice.MzPushService".equals(serviceInfoArr[i].name)) {
                return serviceInfoArr[i].processName;
            }
        }
        return null;
    }

    public static String getMzPushServicePackageName(Context context) {
        String packageName = context.getPackageName();
        try {
            Object servicesByPackageName = getServicesByPackageName(context, "com.meizu.cloud");
            if (!TextUtils.isEmpty(servicesByPackageName) && servicesByPackageName.contains("mzservice_v1")) {
                return "com.meizu.cloud";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        a.a("SystemUtils", "startservice package name " + packageName);
        return packageName;
    }

    public static String getAppVersionName(Context context, String str) {
        String str2 = "";
        try {
            str2 = context.getPackageManager().getPackageInfo(str, 0).versionName;
            if (str2 == null || str2.length() <= 0) {
                return "";
            }
            return str2;
        } catch (Exception e) {
            a.d("VersionInfo", "Exception message " + e.getMessage());
            return "";
        }
    }

    public static boolean compareVersion(String str, String str2) {
        String[] split = str.split("\\.");
        String[] split2 = str2.split("\\.");
        int min = Math.min(split.length, split2.length);
        int i = 0;
        for (int i2 = 0; i2 < min; i2++) {
            i = split[i2].length() - split2[i2].length();
            if (i != 0) {
                break;
            }
            i = split[i2].compareTo(split2[i2]);
            if (i != 0) {
                break;
            }
        }
        if (i == 0) {
            i = split.length - split2.length;
        }
        if (i >= 0) {
            return true;
        }
        return false;
    }

    public static String findReceiver(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        Intent intent = new Intent(str);
        intent.setPackage(str2);
        List queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 0);
        if (queryBroadcastReceivers == null || queryBroadcastReceivers.size() <= 0) {
            return null;
        }
        return ((ResolveInfo) queryBroadcastReceivers.get(0)).activityInfo.name;
    }

    public static String getDeviceId(Context context) {
        return DeviceUtils.getDeviceId(context);
    }

    public static boolean isBrandMeizu() {
        return !TextUtils.isEmpty(SystemProperties.get("ro.meizu.product.model")) || "meizu".equalsIgnoreCase(Build.BRAND) || "22c4185e".equalsIgnoreCase(Build.BRAND);
    }

    public static boolean isInternational() {
        return BuildExt.isInternational().ok ? ((Boolean) BuildExt.isInternational().value).booleanValue() : false;
    }

    public static boolean isIndiaLocal() {
        return "india".equals(SystemProperties.get("ro.meizu.locale.region"));
    }

    public static boolean isApplicationDebug(Context context) {
        try {
            if ((context.getApplicationInfo().flags & 2) != 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getAppVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            return "";
        }
    }

    public static boolean compatApi(int i) {
        return VERSION.SDK_INT >= i;
    }

    public static String getSn() {
        Object obj = SystemProperties.get("ro.serialno");
        return !TextUtils.isEmpty(obj) ? obj : Build.SERIAL;
    }

    public static String getProcessName(Context context) {
        int myPid = Process.myPid();
        String str = "";
        for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getApplicationContext().getSystemService("activity")).getRunningAppProcesses()) {
            a.a(TAG, "processName " + runningAppProcessInfo.processName);
            if (runningAppProcessInfo.pid == myPid) {
                return runningAppProcessInfo.processName;
            }
        }
        return str;
    }

    public static boolean isPackageInstalled(Context context, String str) {
        try {
            context.getPackageManager().getPackageInfo(str, 0);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }
}
