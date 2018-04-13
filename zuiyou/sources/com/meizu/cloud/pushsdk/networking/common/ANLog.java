package com.meizu.cloud.pushsdk.networking.common;

import android.util.Log;
import com.meizu.cloud.a.a;

public class ANLog {
    private static boolean IS_LOGGING_ENABLED = false;
    private static String TAG = ANConstants.ANDROID_NETWORKING;

    private ANLog() {
    }

    public static void enableLogging() {
        IS_LOGGING_ENABLED = true;
    }

    public static void disableLogging() {
        IS_LOGGING_ENABLED = false;
    }

    public static void setTag(String str) {
        if (str != null) {
            TAG = str;
        }
    }

    public static void d(String str) {
        if (IS_LOGGING_ENABLED) {
            a.b(TAG, str);
        }
    }

    public static void e(String str) {
        if (IS_LOGGING_ENABLED) {
            a.d(TAG, str);
        }
    }

    public static void i(String str) {
        if (IS_LOGGING_ENABLED) {
            a.a(TAG, str);
        }
    }

    public static void w(String str) {
        if (IS_LOGGING_ENABLED) {
            a.c(TAG, str);
        }
    }

    public static void wtf(String str) {
        if (IS_LOGGING_ENABLED) {
            Log.wtf(TAG, str);
        }
    }
}
