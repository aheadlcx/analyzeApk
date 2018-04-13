package com.qiniu.android.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public final class AndroidNetwork {
    public static boolean isNetWorkReady() {
        Context applicationContext = ContextGetter.applicationContext();
        if (applicationContext == null) {
            return true;
        }
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) applicationContext.getSystemService("connectivity")).getActiveNetworkInfo();
            boolean z = activeNetworkInfo != null && activeNetworkInfo.isConnected();
            return z;
        } catch (Exception e) {
            return true;
        }
    }
}
