package com.sina.weibo.sdk.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.DetailedState;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import java.util.List;

public class NetworkHelper {
    public static boolean hasInternetPermission(Context context) {
        if (context == null || context.checkCallingOrSelfPermission("android.permission.INTERNET") == 0) {
            return true;
        }
        return false;
    }

    public static boolean isNetworkAvailable(Context context) {
        if (context == null) {
            return false;
        }
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo(context);
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return false;
        }
        return true;
    }

    public static boolean isWifiValid(Context context) {
        if (context == null) {
            return false;
        }
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo(context);
        if (activeNetworkInfo != null && 1 == activeNetworkInfo.getType() && activeNetworkInfo.isConnected()) {
            return true;
        }
        return false;
    }

    public static boolean isMobileNetwork(Context context) {
        if (context == null) {
            return false;
        }
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo(context);
        if (activeNetworkInfo != null && activeNetworkInfo != null && activeNetworkInfo.getType() == 0 && activeNetworkInfo.isConnected()) {
            return true;
        }
        return false;
    }

    public static NetworkInfo getActiveNetworkInfo(Context context) {
        return ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
    }

    public static NetworkInfo getNetworkInfo(Context context, int i) {
        return ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(i);
    }

    public static int getNetworkType(Context context) {
        if (context == null) {
            return -1;
        }
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo(context);
        if (activeNetworkInfo == null) {
            return -1;
        }
        return activeNetworkInfo.getType();
    }

    public static int getWifiState(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
        if (wifiManager == null) {
            return 4;
        }
        return wifiManager.getWifiState();
    }

    public static DetailedState getWifiConnectivityState(Context context) {
        NetworkInfo networkInfo = getNetworkInfo(context, 1);
        return networkInfo == null ? DetailedState.FAILED : networkInfo.getDetailedState();
    }

    public static boolean wifiConnection(Context context, String str, String str2) {
        WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
        String str3 = "\"" + str + "\"";
        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        if (connectionInfo != null && (str.equals(connectionInfo.getSSID()) || str3.equals(connectionInfo.getSSID()))) {
            return true;
        }
        List scanResults = wifiManager.getScanResults();
        if (!(scanResults == null || scanResults.size() == 0)) {
            for (int size = scanResults.size() - 1; size >= 0; size--) {
                String str4 = ((ScanResult) scanResults.get(size)).SSID;
                if (str.equals(str4) || str3.equals(str4)) {
                    WifiConfiguration wifiConfiguration = new WifiConfiguration();
                    wifiConfiguration.SSID = str3;
                    wifiConfiguration.preSharedKey = "\"" + str2 + "\"";
                    wifiConfiguration.status = 2;
                    return wifiManager.enableNetwork(wifiManager.addNetwork(wifiConfiguration), false);
                }
            }
        }
        return false;
    }

    public static void clearCookies(Context context) {
        CookieSyncManager.createInstance(context);
        CookieManager.getInstance().removeAllCookie();
        CookieSyncManager.getInstance().sync();
    }

    public static String generateUA(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Android");
        stringBuilder.append("__");
        stringBuilder.append("weibo");
        stringBuilder.append("__");
        stringBuilder.append("sdk");
        stringBuilder.append("__");
        try {
            stringBuilder.append(context.getPackageManager().getPackageInfo(context.getPackageName(), 16).versionName.replaceAll("\\s+", "_"));
        } catch (Exception e) {
            stringBuilder.append("unknown");
        }
        return stringBuilder.toString();
    }
}
