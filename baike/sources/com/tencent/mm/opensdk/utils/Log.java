package com.tencent.mm.opensdk.utils;

public class Log {
    private static ILog a;

    public static void d(String str, String str2) {
        if (a == null) {
            android.util.Log.d(str, str2);
        } else {
            a.d(str, str2);
        }
    }

    public static void e(String str, String str2) {
        if (a == null) {
            android.util.Log.e(str, str2);
        } else {
            a.e(str, str2);
        }
    }

    public static void i(String str, String str2) {
        if (a == null) {
            android.util.Log.i(str, str2);
        } else {
            a.i(str, str2);
        }
    }

    public static void setLogImpl(ILog iLog) {
        a = iLog;
    }

    public static void v(String str, String str2) {
        if (a == null) {
            android.util.Log.v(str, str2);
        } else {
            a.v(str, str2);
        }
    }

    public static void w(String str, String str2) {
        if (a == null) {
            android.util.Log.w(str, str2);
        } else {
            a.w(str, str2);
        }
    }
}
