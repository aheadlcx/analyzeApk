package com.xiaomi.channel.commonutils.android;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.channel.commonutils.reflect.a;
import com.xiaomi.channel.commonutils.string.d;

public class e {
    private static String a = null;
    private static String b = null;
    private static String c = null;

    public static String a() {
        return VERSION.SDK_INT > 8 ? Build.SERIAL : null;
    }

    public static String a(Context context) {
        if (b == null) {
            String c = c(context);
            String b = b(context);
            b = "a-" + d.b(c + b + a());
        }
        return b;
    }

    @TargetApi(17)
    public static int b() {
        if (VERSION.SDK_INT < 17) {
            return -1;
        }
        Object a = a.a("android.os.UserHandle", "myUserId", new Object[0]);
        return a != null ? ((Integer) Integer.class.cast(a)).intValue() : -1;
    }

    public static String b(Context context) {
        String str = null;
        try {
            str = Secure.getString(context.getContentResolver(), "android_id");
        } catch (Throwable th) {
            b.a(th);
        }
        return str;
    }

    public static String c(Context context) {
        int i = 10;
        String d = d(context);
        while (d == null) {
            int i2 = i - 1;
            if (i <= 0) {
                break;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
            d = d(context);
            i = i2;
        }
        return d;
    }

    public static String d(Context context) {
        if (a != null) {
            return a;
        }
        try {
            String str;
            if (g.a()) {
                str = "miui.telephony.TelephonyManager";
                Object a = a.a("miui.telephony.TelephonyManager", "getDefault", new Object[0]);
                if (a != null) {
                    a = a.a(a, "getMiuiDeviceId", new Object[0]);
                    if (a != null && (a instanceof String)) {
                        str = (String) String.class.cast(a);
                        if (str == null && h(context)) {
                            str = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
                        }
                        if (str != null) {
                            return str;
                        }
                        a = str;
                        return str;
                    }
                }
            }
            str = null;
            str = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
            if (str != null) {
                return str;
            }
            a = str;
            return str;
        } catch (Throwable th) {
            b.a(th);
            return null;
        }
    }

    public static synchronized String e(Context context) {
        String str;
        synchronized (e.class) {
            if (c != null) {
                str = c;
            } else {
                str = b(context);
                c = d.b(str + a());
                str = c;
            }
        }
        return str;
    }

    public static String f(Context context) {
        return ((TelephonyManager) context.getSystemService("phone")).getSimOperatorName();
    }

    public static String g(Context context) {
        try {
            return ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
        } catch (Throwable e) {
            b.a(e);
            return null;
        }
    }

    private static boolean h(Context context) {
        String str = "android.permission.READ_PHONE_STATE";
        return context.getPackageManager().checkPermission("android.permission.READ_PHONE_STATE", context.getPackageName()) == 0;
    }
}
