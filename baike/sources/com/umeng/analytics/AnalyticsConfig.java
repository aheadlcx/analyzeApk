package com.umeng.analytics;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.analytics.pro.t;
import com.umeng.commonsdk.utils.UMUtils;

public class AnalyticsConfig {
    public static boolean ACTIVITY_DURATION_OPEN = true;
    public static boolean CATCH_EXCEPTION = true;
    public static boolean FLAG_DPLUS = false;
    public static String GPU_RENDERER = "";
    public static String GPU_VENDER = "";
    static double[] a = null;
    private static String b = null;
    private static String c = null;
    private static String d = null;
    private static int e = 0;
    public static long kContinueSessionMillis = i.MIN_UPLOAD_INTERVAL;
    public static String mWrapperType = null;
    public static String mWrapperVersion = null;

    public static String getAppkey(Context context) {
        return UMUtils.getAppkey(context);
    }

    public static String getChannel(Context context) {
        return UMUtils.getChannel(context);
    }

    public static double[] getLocation() {
        return a;
    }

    static void a(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            d = str;
            t.a(context).a(d);
        }
    }

    public static String getSecretKey(Context context) {
        if (TextUtils.isEmpty(d)) {
            d = t.a(context).c();
        }
        return d;
    }

    static void a(Context context, int i) {
        e = i;
        t.a(context).a(e);
    }

    public static int getVerticalType(Context context) {
        if (e == 0) {
            e = t.a(context).d();
        }
        return e;
    }
}
