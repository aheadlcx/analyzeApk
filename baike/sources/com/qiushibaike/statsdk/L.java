package com.qiushibaike.statsdk;

import android.util.Log;

public final class L {
    public static final String TAG = "QsbkStatSDK";
    private static boolean a = false;

    public boolean isDebug() {
        return a;
    }

    public static void setDebug(boolean z) {
        a = z;
    }

    private static String a(Object[] objArr) {
        if (objArr == null || objArr.length == 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Object append : objArr) {
            stringBuilder.append(append).append("  ");
        }
        return stringBuilder.toString();
    }

    public static int d(Object[] objArr) {
        return d(a(objArr));
    }

    public static int d(String str) {
        return d(TAG, str);
    }

    public static int d(String str, Throwable th) {
        return d(str, "", th);
    }

    public static int d(String str, String str2, Object... objArr) {
        try {
            return d(str, String.format(str2, objArr));
        } catch (Exception e) {
            return -1;
        }
    }

    public static int d(String str, String str2, Throwable th) {
        if (a) {
            return Log.d(str, str2, th);
        }
        return -1;
    }

    public static int d(Throwable th) {
        return d(TAG, th);
    }

    public static int d(String str, String str2) {
        if (a) {
            return Log.d(str, str2);
        }
        return -1;
    }

    public static int w(String str, String str2) {
        if (a) {
            return Log.w(str, str2);
        }
        return -1;
    }

    public static int e(String str, String str2) {
        if (a) {
            return Log.e(str, str2);
        }
        return -1;
    }
}
