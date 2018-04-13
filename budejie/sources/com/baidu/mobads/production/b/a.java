package com.baidu.mobads.production.b;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;

public class a {
    public static int a(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return 0;
        }
        activeNetworkInfo = connectivityManager.getNetworkInfo(1);
        if (activeNetworkInfo != null && a(activeNetworkInfo.getState())) {
            return 100;
        }
        if (VERSION.SDK_INT >= 13) {
            activeNetworkInfo = connectivityManager.getNetworkInfo(9);
            if (activeNetworkInfo != null && a(activeNetworkInfo.getState())) {
                return 101;
            }
        }
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(0);
        if (networkInfo == null || !a(networkInfo.getState())) {
            return 999;
        }
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
                return 1;
        }
    }

    private static boolean a(State state) {
        return state != null && (state == State.CONNECTED || state == State.CONNECTING);
    }
}
