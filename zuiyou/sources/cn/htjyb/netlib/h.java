package cn.htjyb.netlib;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.izuiyou.a.a.b;

public class h {
    public static boolean a(Context context) {
        if (context == null) {
            return false;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity");
        if (connectivityManager == null) {
            b.d("get CONNECTIVITY_SERVICE failed");
            return false;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
            NetworkInfo[] allNetworkInfo = connectivityManager.getAllNetworkInfo();
            if (allNetworkInfo != null) {
                for (NetworkInfo networkInfo : allNetworkInfo) {
                    if (networkInfo.isConnected()) {
                        b.a(networkInfo.getTypeName() + " net is connected");
                        return true;
                    }
                }
            }
            b.a((Object) "no net is connected");
            return false;
        }
        b.a(activeNetworkInfo.getTypeName() + " net is connected");
        return true;
    }

    public static void b(Context context) {
        Intent intent;
        if (VERSION.SDK_INT >= 11) {
            intent = new Intent("android.settings.WIRELESS_SETTINGS");
        } else {
            intent = new Intent();
            intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.WirelessSettings"));
            intent.setAction("android.intent.action.VIEW");
        }
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String c(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return null;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return null;
        }
        if (1 == activeNetworkInfo.getType()) {
            return "wifi";
        }
        int subtype = activeNetworkInfo.getSubtype();
        if (1 == subtype) {
            return "联通2G";
        }
        if (2 == subtype) {
            return "移动2G";
        }
        if (4 == subtype) {
            return "电信2G";
        }
        if (3 == subtype || 8 == subtype || 8 == subtype || 9 == subtype || 10 == subtype || 15 == subtype) {
            return "联通3G-" + activeNetworkInfo.getSubtypeName();
        }
        if (5 == subtype || 6 == subtype || 7 == subtype || 12 == subtype) {
            return "电信3G-" + activeNetworkInfo.getSubtypeName();
        }
        String typeName = activeNetworkInfo.getTypeName();
        if (TextUtils.isEmpty(activeNetworkInfo.getSubtypeName())) {
            return typeName;
        }
        return typeName + "-" + activeNetworkInfo.getSubtypeName();
    }
}
