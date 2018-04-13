package com.umeng.analytics;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.a.d;
import com.umeng.a.g;
import com.umeng.a.k;

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
        String m = d.m(context);
        if (TextUtils.isEmpty(m)) {
            Object c = k.a(context).c();
            if (TextUtils.isEmpty(c)) {
                k.a(context).a(str);
            } else if (!c.equals(str)) {
                g.c("Appkey和上次配置的不一致 ");
                k.a(context).a(str);
            }
            b = str;
            return;
        }
        b = m;
        if (!m.equals(str)) {
            g.c("Appkey和AndroidManifest.xml中配置的不一致 ");
        }
    }

    static void a(String str) {
        c = str;
    }

    public static String getAppkey(Context context) {
        if (TextUtils.isEmpty(b)) {
            b = d.m(context);
            if (TextUtils.isEmpty(b)) {
                b = k.a(context).c();
            }
        }
        return b;
    }

    public static String getChannel(Context context) {
        if (TextUtils.isEmpty(c)) {
            c = d.p(context);
        }
        return c;
    }

    public static double[] getLocation() {
        return a;
    }

    static void b(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            d = str;
            k.a(context).b(d);
        }
    }

    public static String getSecretKey(Context context) {
        if (TextUtils.isEmpty(d)) {
            d = k.a(context).d();
        }
        return d;
    }

    static void a(Context context, int i) {
        e = i;
        k.a(context).a(e);
    }

    public static int getVerticalType(Context context) {
        if (e == 0) {
            e = k.a(context).e();
        }
        return e;
    }

    public static String getSDKVersion(Context context) {
        return "6.1.0";
    }
}
