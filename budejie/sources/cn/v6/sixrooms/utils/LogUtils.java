package cn.v6.sixrooms.utils;

import android.util.Log;

public class LogUtils {
    public static int DEBUG = 4;
    public static int ERROR = 1;
    public static int INFO = 3;
    public static final int LOG_LEVEL = 0;
    public static int VERBOSE = 5;
    public static int WARN = 2;

    public static void v(String str, String str2) {
        if (VERBOSE < 0) {
            Log.v(str, str2);
        }
    }

    public static void d(String str, String str2) {
        if (DEBUG < 0) {
            Log.d(str, str2);
        }
    }

    public static void i(String str, String str2) {
        if (INFO < 0) {
            Log.i(str, str2);
        }
    }

    public static void w(String str, String str2) {
        if (WARN < 0) {
            Log.w(str, str2);
        }
    }

    public static void e(String str, String str2) {
        if (ERROR < 0) {
            Log.e(str, str2);
        }
    }
}
