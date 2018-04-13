package com.meizu.cloud.pushsdk.base;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.meizu.cloud.a.a;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class DeviceUtils {
    private static final String TAG = "DeviceUtils";
    private static String sDeviceId = "";
    private static String sMacAddr = "";

    public static boolean isPhone() {
        Object obj = SystemProperties.get("ro.target.product");
        if (TextUtils.isEmpty(obj)) {
            a.a(TAG, "current product is phone");
            return true;
        }
        a.a(TAG, "current product is " + obj);
        return false;
    }

    public static String getDeviceType() {
        String str = SystemProperties.get("ro.target.product");
        if (!TextUtils.isEmpty(str)) {
            a.a(TAG, "current product is " + str);
        }
        return str;
    }

    public static String getDeviceId(Context context) {
        if (TextUtils.isEmpty(sDeviceId)) {
            if (isPhone()) {
                sDeviceId = MzTelephoneManager.getDeviceId(context);
            } else if (TextUtils.isEmpty(sDeviceId)) {
                StringBuilder stringBuilder = new StringBuilder();
                Object obj = Build.SERIAL;
                a.a(TAG, "device serial " + obj);
                if (TextUtils.isEmpty(obj)) {
                    return null;
                }
                stringBuilder.append(obj);
                obj = getMACAddress(context);
                a.d(TAG, "mac address " + obj);
                if (TextUtils.isEmpty(obj)) {
                    return null;
                }
                stringBuilder.append(obj.replace(":", "").toUpperCase());
                sDeviceId = stringBuilder.toString();
            }
        }
        return sDeviceId;
    }

    public static String getMACAddress(Context context) {
        String str = null;
        if (!TextUtils.isEmpty(sMacAddr)) {
            return sMacAddr;
        }
        if (VERSION.SDK_INT >= 23) {
            String macAddressWithIfName;
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager != null) {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo == null) {
                    macAddressWithIfName = getMacAddressWithIfName("wlan0");
                    if (TextUtils.isEmpty(macAddressWithIfName)) {
                        macAddressWithIfName = getMacAddressWithIfName("eth0");
                    }
                } else if (activeNetworkInfo.getType() == 1) {
                    macAddressWithIfName = getMacAddressWithIfName("wlan0");
                } else if (activeNetworkInfo.getType() == 9) {
                    macAddressWithIfName = getMacAddressWithIfName("eth0");
                }
                str = macAddressWithIfName;
            }
            macAddressWithIfName = null;
            str = macAddressWithIfName;
        } else {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (wifiManager != null) {
                WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                if (connectionInfo != null) {
                    str = connectionInfo.getMacAddress();
                }
            }
        }
        sMacAddr = str;
        return sMacAddr;
    }

    private static String getMacAddressWithIfName(String str) {
        String str2 = null;
        try {
            InputStream fileInputStream = new FileInputStream("/sys/class/net/" + str + "/address");
            Scanner scanner = new Scanner(fileInputStream);
            if (scanner.hasNextLine()) {
                str2 = scanner.nextLine().trim();
            }
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            a.d(TAG, "getMacAddressWithIfName File not found Exception");
        } catch (IOException e2) {
            a.d(TAG, "getMacAddressWithIfName IOException");
        }
        return str2;
    }
}
