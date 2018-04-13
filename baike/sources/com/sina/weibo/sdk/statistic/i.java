package com.sina.weibo.sdk.statistic;

import android.content.Context;

class i {
    public static boolean ACTIVITY_DURATION_OPEN = true;
    public static final long MIN_UPLOAD_INTERVAL = 30000;
    private static String a = null;
    private static String b = null;
    private static boolean c = true;
    private static long d = 90000;
    private static long e = MIN_UPLOAD_INTERVAL;
    public static long kContinueSessionMillis = MIN_UPLOAD_INTERVAL;

    public static void setAppkey(String str) {
        a = str;
    }

    public static void setChannel(String str) {
        b = str;
    }

    public static String getAppkey(Context context) {
        if (a == null) {
            a = c.getAppKey(context);
        }
        return a;
    }

    public static String getChannel(Context context) {
        if (b == null) {
            b = c.getChannel(context);
        }
        return b;
    }

    public static long getUploadInterval() {
        return d;
    }

    public static void setUploadInterval(long j) throws Exception {
        if (j < MIN_UPLOAD_INTERVAL || j > 28800000) {
            throw new Exception("The interval must be between 30 seconds and 8 hours");
        }
        d = j;
    }

    public static boolean isNeedGizp() {
        return c;
    }

    public static void setNeedGizp(boolean z) {
        c = z;
    }

    public static long getForceUploadInterval() {
        return e;
    }

    public static void setForceUploadInterval(long j) {
        e = j;
    }
}
