package com.lt.a.b;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.telephony.TelephonyManager;

public class b {
    public static int a(Context context) {
        try {
            String subscriberId = ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
            if (subscriberId.startsWith("46000") || subscriberId.startsWith("46002")) {
                return 1;
            }
            if (subscriberId.startsWith("46001") || subscriberId.startsWith("46006") || subscriberId.startsWith("46009")) {
                return 2;
            }
            if (subscriberId.startsWith("46003")) {
                return 3;
            }
            return 4;
        } catch (Exception e) {
            return 4;
        }
    }

    public static int b(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        connectivityManager.getActiveNetworkInfo();
        State state = connectivityManager.getNetworkInfo(1).getState();
        State state2 = connectivityManager.getNetworkInfo(0).getState();
        if (state != null && state2 != null && State.CONNECTED != state && State.CONNECTED == state2) {
            return 1;
        }
        if (state != null && state2 != null && State.CONNECTED != state && State.CONNECTED != state2) {
            return -1;
        }
        if (state == null || State.CONNECTED != state) {
            return -1;
        }
        return 0;
    }

    public static String c(Context context) {
        String deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        if (f.b(deviceId)) {
            return "";
        }
        return deviceId;
    }

    public static int d(Context context) {
        int type;
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            type = activeNetworkInfo.getType();
            if (type == 1) {
                type = 1;
                return type;
            } else if (type == 0) {
                switch (((TelephonyManager) context.getSystemService("phone")).getNetworkType()) {
                    case 1:
                    case 2:
                    case 4:
                    case 7:
                    case 11:
                        return 2;
                    case 3:
                    case 5:
                    case 6:
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                    case 14:
                    case 15:
                        return 3;
                    case 13:
                        return 4;
                    default:
                        return 0;
                }
            }
        }
        type = 0;
        return type;
    }
}
