package com.alibaba.sdk.android.mns.common;

import android.util.Log;

public class MNSLog {
    private static final String TAG = "MNS-Android-SDK";
    private static boolean enableLog;

    public static void enableLog() {
        enableLog = true;
    }

    public static void disableLog() {
        enableLog = false;
    }

    public static boolean isEnableLog() {
        return enableLog;
    }

    public static void logI(String str) {
        if (enableLog) {
            Log.i(TAG, str);
        }
    }

    public static void logV(String str) {
        if (enableLog) {
            Log.v(TAG, str);
        }
    }

    public static void logW(String str) {
        if (enableLog) {
            Log.w(TAG, str);
        }
    }

    public static void logD(String str) {
        if (enableLog) {
            Log.d(TAG, str);
        }
    }

    public static void logE(String str) {
        if (enableLog) {
            Log.e(TAG, str);
        }
    }
}
