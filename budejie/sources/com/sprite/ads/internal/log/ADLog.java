package com.sprite.ads.internal.log;

import android.util.Log;

public class ADLog {
    public static final boolean DEBUG = Log.isLoggable(LOG_TAG, 3);
    public static final boolean ERROR = Log.isLoggable(LOG_TAG, 6);
    private static final String LOG_TAG = "spriteAD";

    public static void d(String str) {
        if (DEBUG) {
            Log.d(LOG_TAG, str);
        }
    }

    public static void d(String str, String str2) {
        Log.d(str, str2);
    }

    public static void e(String str) {
        if (ERROR) {
            Log.e(LOG_TAG, str);
        }
    }

    public static void e(String str, String str2) {
        Log.e(str, str2);
    }
}
