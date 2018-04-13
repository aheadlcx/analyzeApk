package com.spriteapp.booklibrary.util;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;

public class DeviceUtil {
    public static String getDeviceId(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        } catch (Exception e) {
            return "";
        }
    }

    public static String getMacAddress(Context context) {
        String str = "000000000000";
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(IXAdSystemUtils.NT_WIFI);
        if (wifiManager == null) {
            return str;
        }
        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        if (connectionInfo == null) {
            return str;
        }
        return connectionInfo.getMacAddress();
    }
}
