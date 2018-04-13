package qsbk.app.core.utils;

import android.util.Log;

public class LogUtils {
    public static final String DEFAULT_TAG = "qsbk";
    public static boolean LOGGABLE = true;

    public static void d(String str, String str2) {
        if (LOGGABLE) {
            Log.d(str, str2 + "");
        }
    }

    public static void d(String str) {
        if (LOGGABLE) {
            Log.d(DEFAULT_TAG, str + "");
        }
    }

    public static void w(String str, String str2) {
        if (LOGGABLE) {
            Log.w(str, str2 + "");
        }
    }

    public static void w(String str) {
        if (LOGGABLE) {
            Log.w(DEFAULT_TAG, str + "");
        }
    }

    public static void e(String str, String str2) {
        if (LOGGABLE) {
            Log.e(str, str2 + "");
        }
    }

    public static void e(String str, String str2, Throwable th) {
        if (LOGGABLE) {
            Log.e(str, str2 + "", th);
        }
    }

    public static void e(String str) {
        if (LOGGABLE) {
            Log.e(DEFAULT_TAG, str + "");
        }
    }

    public static void i(String str, String str2) {
        if (LOGGABLE) {
            Log.i(str, str2 + "");
        }
    }

    public static void i(String str) {
        if (LOGGABLE) {
            Log.i(DEFAULT_TAG, str + "");
        }
    }

    public static void v(String str, String str2) {
        if (LOGGABLE) {
            Log.v(str, str2 + "");
        }
    }

    public static void v(String str) {
        if (LOGGABLE) {
            Log.v(DEFAULT_TAG, str + "");
        }
    }
}
