package com.xiaomi.metoknlp.a;

import android.content.Context;
import android.telephony.TelephonyManager;

public class d {
    private static TelephonyManager a;
    private static Context b;

    public static String a() {
        return a != null ? a.getNetworkOperator() : null;
    }

    public static void a(Context context) {
        b = context;
        a = (TelephonyManager) context.getSystemService("phone");
    }

    public static String b() {
        String str = null;
        try {
            if (!(b == null || b.getPackageManager().checkPermission("android.permission.READ_PHONE_STATE", b.getPackageName()) != 0 || a == null)) {
                str = a.getDeviceId();
            }
        } catch (Exception e) {
        }
        return str != null ? str : "UNKNOWN";
    }
}
