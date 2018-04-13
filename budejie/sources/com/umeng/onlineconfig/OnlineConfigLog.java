package com.umeng.onlineconfig;

import android.util.Log;

public class OnlineConfigLog {
    public static boolean LOG = false;

    public static void i(String str, String str2) {
        if (LOG) {
            Log.i(str, str2);
        }
    }

    public static void i(String str, String str2, Exception exception) {
        if (LOG) {
            Log.i(str, exception.toString() + ":  [" + str2 + "]");
        }
    }

    public static void e(String str, String str2) {
        if (LOG) {
            Log.e(str, str2);
        }
    }

    public static void e(String str, String str2, Exception exception) {
        if (LOG) {
            Log.e(str, exception.toString() + ":  [" + str2 + "]");
            for (StackTraceElement stackTraceElement : exception.getStackTrace()) {
                Log.e(str, "        at\t " + stackTraceElement.toString());
            }
        }
    }

    public static void d(String str, String str2) {
        if (LOG) {
            Log.d(str, str2);
        }
    }

    public static void d(String str, String str2, Exception exception) {
        if (LOG) {
            Log.d(str, exception.toString() + ":  [" + str2 + "]");
        }
    }

    public static void v(String str, String str2) {
        if (LOG) {
            Log.v(str, str2);
        }
    }

    public static void v(String str, String str2, Exception exception) {
        if (LOG) {
            Log.v(str, exception.toString() + ":  [" + str2 + "]");
        }
    }

    public static void w(String str, String str2) {
        if (LOG) {
            Log.w(str, str2);
        }
    }

    public static void w(String str, String str2, Exception exception) {
        if (LOG) {
            Log.w(str, exception.toString() + ":  [" + str2 + "]");
            for (StackTraceElement stackTraceElement : exception.getStackTrace()) {
                Log.w(str, "        at\t " + stackTraceElement.toString());
            }
        }
    }
}
