package net.tsz.afinal;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;

class a {
    public static String a(Context context) {
        return String.valueOf(context.getResources().getDisplayMetrics().widthPixels);
    }

    public static String b(Context context) {
        return String.valueOf(context.getResources().getDisplayMetrics().heightPixels);
    }

    public static String c(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        } catch (Exception e) {
            return "000000000000";
        }
    }

    public static String d(Context context) {
        if (context == null) {
            return "";
        }
        WifiManager wifiManager = (WifiManager) context.getSystemService(IXAdSystemUtils.NT_WIFI);
        if (wifiManager == null) {
            return "";
        }
        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        if (connectionInfo == null) {
            return "";
        }
        return connectionInfo.getMacAddress();
    }

    public static String e(Context context) {
        return context.getResources().getConfiguration().locale.getLanguage();
    }

    public static String f(Context context) {
        return context.getResources().getConfiguration().locale.getCountry();
    }

    public static String a() {
        return VERSION.RELEASE;
    }

    public static String g(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return "NONE";
        }
        if (activeNetworkInfo.getType() == 1) {
            return "WIFI";
        }
        if (activeNetworkInfo.getType() != 0) {
            return "UNKNOWN";
        }
        switch (activeNetworkInfo.getSubtype()) {
            case 1:
            case 2:
            case 4:
                return "2G";
            case 3:
            case 5:
            case 6:
            case 8:
            case 12:
            case 15:
                return "3G";
            case 13:
                return "4G";
            default:
                return "UNKNOWN";
        }
    }

    public static String b() {
        if (e.a()) {
            return "1";
        }
        return "0";
    }

    public static String h(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            return "NONE";
        }
        String subscriberId = telephonyManager.getSubscriberId();
        if (subscriberId == null) {
            return "NONE";
        }
        if (subscriberId.startsWith("46000") || subscriberId.startsWith("46002")) {
            return "移动";
        }
        if (subscriberId.startsWith("46001")) {
            return "联通";
        }
        if (subscriberId.startsWith("46003")) {
            return "电信";
        }
        return "UNKNOWN";
    }
}
