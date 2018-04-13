package com.qiushibaike.statsdk;

import android.content.Context;

public class StatSDK {
    private static String a = null;
    private static LogServerReporter b = LogServerReporter.getInstance();
    private static LogLocalReporter c = LogLocalReporter.getInstance();

    private StatSDK() {
    }

    public static void init(String str, Context context) {
        a = str;
        a(context);
        b.init(context, a);
    }

    public static void setDebug(boolean z) {
        L.setDebug(z);
    }

    public static void setAppInfo(String str, String str2, String str3) {
        DataObjConstructor.getInstance().setAppChannel(str2);
        DataObjConstructor.getInstance().setAppVersionName(str);
        DataObjConstructor.getInstance().setExtra(str3);
    }

    public static void onEvent(Context context, String str, String str2) {
        onEvent(context, str, str2, 1);
    }

    public static void onEvent(Context context, String str, String str2, String str3, String str4, String str5) {
        onEvent(context, str, str2, 1, str3, str4, str5);
    }

    public static void onEvent(Context context, String str, String str2, int i) {
        if (a(str)) {
            L.d("event id cannot be null. ");
        } else if (!a(context, "")) {
            c.putEvent(context, str, str2, i);
        }
    }

    public static void onEvent(Context context, String str, String str2, int i, String str3, String str4, String str5) {
        if (a(str)) {
            L.d("event id cannot be null. ");
        } else if (!a(context, "")) {
            c.putEventWithExtra(context, str, str2, i, str3, str4, str5);
        }
    }

    public static void onEventDuration(Context context, String str, String str2, long j) {
        if (a(str)) {
            L.d("event id cannot be null. ");
        } else if (!a(context, "")) {
            if (j < 0) {
                L.d("duration cannot be negative.");
            } else {
                c.putEventDuration(context, str, str2, j);
            }
        }
    }

    public static void onEventDuration(Context context, String str, String str2, long j, String str3, String str4, String str5) {
        if (a(str)) {
            L.d("event id cannot be null. ");
        } else if (!a(context, "")) {
            if (j < 0) {
                L.d("duration cannot be negative.");
            } else {
                c.putEventDurationWithExtra(context, str, str2, j, str3, str4, str5);
            }
        }
    }

    private static void a(Context context) {
        CacheLoader.getInstance().checkLoadStarted(context);
    }

    public static void forceReport(Context context) {
        b.forceReportLog(context);
    }

    public static void onResume(Context context) {
    }

    public static void onPause(Context context) {
    }

    public static void onPageStart(Context context, String str) {
    }

    public static void onPageEnd(Context context, String str) {
    }

    private static boolean a(Context context, String str) {
        boolean z = context == null;
        if (z) {
            L.d("context is null");
        }
        return z;
    }

    private static boolean a(String str) {
        return str == null || str.length() == 0;
    }
}
