package com.alibaba.mtl.log;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.alibaba.mtl.log.c.c;
import com.alibaba.mtl.log.e.b;
import com.alibaba.mtl.log.e.i;
import com.alibaba.mtl.log.e.l;
import com.alibaba.mtl.log.sign.IRequestAuth;
import com.umeng.analytics.pro.bv;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class a {
    public static String B = String.valueOf(System.currentTimeMillis());
    public static IRequestAuth a = null;
    /* renamed from: a */
    private static boolean f18a = false;
    public static long b = -1;
    public static final AtomicInteger d = new AtomicInteger(0);
    private static Context mContext;
    public static boolean o = false;
    public static boolean p = false;
    private static boolean q = (t <= s);
    public static boolean r = true;
    public static int s = 10000;
    public static int t = 0;

    public static synchronized void init(Context context) {
        synchronized (a.class) {
            if (context == null) {
                i.a("UTDC", "UTDC init failed ,context:" + context);
            } else if (!f18a) {
                f18a = true;
                mContext = context.getApplicationContext();
                com.alibaba.mtl.log.d.a.a().start();
            }
        }
    }

    public static void a(IRequestAuth iRequestAuth) {
        a = iRequestAuth;
        if (a != null) {
            b.o(a.getAppkey());
        }
    }

    public static void setChannel(String str) {
        b.n(str);
    }

    public static void l() {
        i.a("UTDC", "[onBackground]");
        o = true;
        com.alibaba.mtl.log.b.a.E();
    }

    public static void m() {
        i.a("UTDC", "[onForeground]");
        o = false;
        com.alibaba.mtl.log.d.a.a().start();
    }

    public static void a(String str, String str2, String str3, String str4, String str5, Map<String, String> map) {
        if (mContext == null) {
            i.a("UTDC", (Object) "please call UTDC.init(context) before commit log,and this log will be discarded");
        } else if (a == null) {
            i.a("UTDC", (Object) "please call UTDC.setRequestAuthentication(auth) before commit log,and this log will be discarded");
        } else {
            i.a("UTDC", "[commit] page:", str, "eventId:", str2, "arg1:", str3, "arg2:", str4, "arg3:", str5, "args:", map);
            com.alibaba.mtl.log.b.a.l(str2);
            c.a().a(new com.alibaba.mtl.log.model.a(str, str2, str3, str4, str5, map));
        }
    }

    public static Context getContext() {
        return mContext;
    }

    public static IRequestAuth a() {
        if (a == null || TextUtils.isEmpty(a.getAppkey())) {
            if (i.n()) {
                throw new RuntimeException("please Set <meta-data android:value=\"YOU KEY\" android:name=\"com.alibaba.apmplus.app_key\"></meta-data> in app AndroidManifest.xml ");
            }
            Log.w("UTDC", "please Set <meta-data android:value=\"YOU KEY\" android:name=\"com.alibaba.apmplus.app_key\"></meta-data> in app AndroidManifest.xml ");
        }
        return a;
    }

    public static String b() {
        try {
            return l.getNetworkState(getContext())[0];
        } catch (Exception e) {
            return "Unknown";
        }
    }

    public static String c() {
        try {
            String[] networkState = l.getNetworkState(getContext());
            if (networkState[0].equals(bv.c)) {
                return networkState[1];
            }
            return "Unknown";
        } catch (Exception e) {
            return "Unknown";
        }
    }

    public static String d() {
        return "";
    }

    public static String e() {
        return "";
    }

    public static void n() {
        com.alibaba.mtl.log.d.a.a().start();
    }
}
