package com.umeng.analytics;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.analytics.pro.bs;
import com.umeng.analytics.pro.bv;
import com.umeng.analytics.pro.by;
import com.umeng.analytics.pro.cc;

public class AnalyticsConfig {
    public static boolean ACTIVITY_DURATION_OPEN = true;
    public static boolean CATCH_EXCEPTION = true;
    public static String GPU_RENDERER = "";
    public static String GPU_VENDER = "";
    static double[] a = null;
    private static String b = null;
    private static String c = null;
    private static String d = null;
    private static int e = 0;
    public static long kContinueSessionMillis = 30000;
    public static String mWrapperType = null;
    public static String mWrapperVersion = null;
    public static boolean sEncrypt = false;
    public static int sLatentWindow;

    static void a(boolean z) {
        sEncrypt = z;
    }

    static void a(Context context, String str) {
        if (context == null) {
            b = str;
            return;
        }
        String p = bv.p(context);
        if (TextUtils.isEmpty(p)) {
            Object c = cc.a(context).c();
            if (TextUtils.isEmpty(c)) {
                cc.a(context).a(str);
            } else if (!c.equals(str)) {
                by.d("Appkey和上次配置的不一致 ");
                cc.a(context).a(str);
            }
            b = str;
            return;
        }
        b = p;
        if (!p.equals(str)) {
            by.d("Appkey和AndroidManifest.xml中配置的不一致 ");
        }
    }

    static void a(String str) {
        c = str;
    }

    public static String getAppkey(Context context) {
        if (TextUtils.isEmpty(b)) {
            b = bv.p(context);
            if (TextUtils.isEmpty(b)) {
                b = cc.a(context).c();
            }
        }
        return b;
    }

    public static String getChannel(Context context) {
        if (TextUtils.isEmpty(c)) {
            c = bv.s(context);
        }
        return c;
    }

    public static double[] getLocation() {
        return a;
    }

    static void b(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            d = str;
            cc.a(context).c(d);
        }
    }

    public static String getSecretKey(Context context) {
        if (TextUtils.isEmpty(d)) {
            d = cc.a(context).e();
        }
        return d;
    }

    static void a(Context context, int i) {
        e = i;
        cc.a(context).a(e);
    }

    public static int getVerticalType(Context context) {
        if (e == 0) {
            e = cc.a(context).f();
        }
        return e;
    }

    public static String getSDKVersion(Context context) {
        return bs.a;
    }
}
