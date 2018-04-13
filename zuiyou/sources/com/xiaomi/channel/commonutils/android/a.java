package com.xiaomi.channel.commonutils.android;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Base64;
import com.xiaomi.channel.commonutils.logger.b;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class a {

    public enum a {
        UNKNOWN(0),
        ALLOWED(1),
        NOT_ALLOWED(2);
        
        private final int d;

        private a(int i) {
            this.d = i;
        }

        public int a() {
            return this.d;
        }
    }

    public static String a(Context context) {
        List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        List arrayList = new ArrayList();
        StringBuilder stringBuilder = new StringBuilder();
        if (runningAppProcesses != null && runningAppProcesses.size() > 0) {
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                String[] strArr = runningAppProcessInfo.pkgList;
                int i = 0;
                while (strArr != null && i < strArr.length) {
                    if (!arrayList.contains(strArr[i])) {
                        arrayList.add(strArr[i]);
                        if (arrayList.size() == 1) {
                            stringBuilder.append(((String) arrayList.get(0)).hashCode() % 100000);
                        } else {
                            stringBuilder.append("#").append(strArr[i].hashCode() % 100000);
                        }
                    }
                    i++;
                }
            }
        }
        return stringBuilder.toString();
    }

    public static String a(Context context, String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 16384);
        } catch (Throwable e) {
            b.a(e);
            packageInfo = null;
        }
        return packageInfo != null ? packageInfo.versionName : "1.0";
    }

    public static String a(String[] strArr) {
        b[] values = b.values();
        byte[] bArr = new byte[((int) Math.ceil(((double) values.length) / 8.0d))];
        if (strArr != null) {
            int length = strArr.length;
            int i = 0;
            int i2 = -1;
            while (i < length) {
                int i3;
                CharSequence charSequence = strArr[i];
                if (TextUtils.isEmpty(charSequence)) {
                    i3 = i2;
                } else if (charSequence.startsWith("android.permission.")) {
                    i3 = 0;
                    while (i3 < values.length) {
                        if (TextUtils.equals("android.permission." + values[i3].name(), charSequence)) {
                            i2 = 1;
                            break;
                        }
                        i3++;
                    }
                    i3 = i2;
                    i2 = 0;
                    if (!(i2 == 0 || i3 == -1)) {
                        i2 = i3 / 8;
                        bArr[i2] = (byte) (bArr[i2] | (1 << (7 - (i3 % 8))));
                    }
                } else {
                    i3 = i2;
                }
                i++;
                i2 = i3;
            }
            return new String(Base64.encode(bArr, 0));
        }
        b.c("AppInfoUtils.: no permissions");
        return "";
    }

    public static int b(Context context, String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 16384);
        } catch (Throwable e) {
            b.a(e);
            packageInfo = null;
        }
        return packageInfo != null ? packageInfo.versionCode : 0;
    }

    @TargetApi(19)
    public static a c(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str) || VERSION.SDK_INT < 19) {
            return a.UNKNOWN;
        }
        try {
            if (((Integer) com.xiaomi.channel.commonutils.reflect.a.a(AppOpsManager.class, "OP_POST_NOTIFICATION")) == null) {
                return a.UNKNOWN;
            }
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 0);
            Integer num = (Integer) com.xiaomi.channel.commonutils.reflect.a.a((AppOpsManager) context.getSystemService("appops"), "checkOpNoThrow", num, Integer.valueOf(applicationInfo.uid), str);
            return (num == null || num.intValue() != 0) ? a.NOT_ALLOWED : a.ALLOWED;
        } catch (Throwable th) {
            return a.UNKNOWN;
        }
    }

    public static boolean d(Context context, String str) {
        List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses != null) {
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (Arrays.asList(runningAppProcessInfo.pkgList).contains(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean e(Context context, String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 0);
        } catch (NameNotFoundException e) {
            packageInfo = null;
        }
        return packageInfo != null;
    }

    public static String f(Context context, String str) {
        try {
            return a(context.getPackageManager().getPackageInfo(str, 4096).requestedPermissions);
        } catch (NameNotFoundException e) {
            b.d(e.toString());
            return "";
        }
    }

    public static boolean g(Context context, String str) {
        return context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
    }
}
