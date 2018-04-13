package com.qiushibaike.statsdk.common;

import android.content.Context;

public final class CommonParam {
    private static String a;
    private static String b;

    public static String getCUID(Context context) {
        if (a == null) {
            String deviceId = getDeviceId(context);
            String imei = DeviceId.getIMEI(context);
            if (imei == null || imei.length() == 0) {
                imei = "0";
            }
            a = deviceId + "|" + new StringBuffer(imei).reverse().toString();
        }
        return a;
    }

    public static String getDeviceId(Context context) {
        if (b == null) {
            b = DeviceId.getDeviceID(context);
        }
        return b;
    }
}
