package com.loc;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.alipay.android.phone.mrpc.core.gwprotocol.JsonSerializer;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.baidu.mobstat.Config;
import com.loc.l.a.b;
import com.loc.l.a.c;
import com.loc.l.a.d;
import com.tencent.bugly.Bugly;
import com.umeng.commonsdk.proguard.g;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import qsbk.app.core.model.CustomButton;
import qsbk.app.core.model.User;
import qsbk.app.core.utils.ACache;
import qsbk.app.utils.ListViewHelper;

public final class cv {
    private static int A = 200;
    private static boolean B = false;
    private static boolean C = true;
    private static boolean D = true;
    private static int E = -1;
    private static long F = 0;
    private static ArrayList<String> G = new ArrayList();
    private static boolean H = true;
    private static int I = -1;
    private static long J = 0;
    private static ArrayList<String> K = new ArrayList();
    private static String L;
    private static String M;
    private static boolean N = false;
    private static boolean O = false;
    private static int P = 3000;
    private static int Q = 3000;
    private static boolean R = true;
    private static long S = ListViewHelper.DEFAULT_TIPS_TO_REFRESH_INTERVAL;
    private static int T = -1;
    private static boolean U = false;
    private static boolean V = false;
    private static boolean W = false;
    private static boolean X = false;
    private static List<cy> Y = new ArrayList();
    private static boolean Z = false;
    static boolean a = true;
    private static long aa = 0;
    private static int ab = 0;
    private static int ac = 0;
    private static List<String> ad = new ArrayList();
    private static boolean ae = true;
    private static int af = 80;
    private static boolean ag = false;
    static boolean b = false;
    static int c = 1800000;
    static int d = 3600000;
    static long e = 0;
    static long f = 0;
    private static String g = "提示信息";
    private static String h = "确认";
    private static String i = "取消";
    private static String j = "";
    private static String k = "";
    private static String l = "";
    private static boolean m = false;
    private static long n = 0;
    private static long o = 0;
    private static long p = Config.BPLUS_DELAY_TIME;
    private static boolean q = false;
    private static int r = 0;
    private static boolean s = false;
    private static int t = 0;
    private static boolean u = false;
    private static int v = 3600000;
    private static int w = 0;
    private static int x = 0;
    private static boolean y = true;
    private static int z = 1000;

    static class a {
        boolean a = false;
        String b = "0";
        boolean c = false;
        int d = 5;

        a() {
        }
    }

    public static boolean A() {
        return ag;
    }

    private static a a(JSONObject jSONObject, String str) {
        Throwable th;
        if (jSONObject != null) {
            a aVar;
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject(str);
                if (jSONObject2 != null) {
                    aVar = new a();
                    try {
                        aVar.a = l.a(jSONObject2.optString(CustomButton.POSITION_BOTTOM), false);
                        aVar.b = jSONObject2.optString("t");
                        aVar.c = l.a(jSONObject2.optString(Config.STAT_SDK_TYPE), false);
                        aVar.d = jSONObject2.optInt("i", 0);
                        return aVar;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
            } catch (Throwable th3) {
                Throwable th4 = th3;
                aVar = null;
                th = th4;
                cw.a(th, "AuthUtil", "getLocateObj");
                return aVar;
            }
        }
        return null;
    }

    public static boolean a() {
        return q;
    }

    public static boolean a(long j) {
        long b = de.b();
        return m && b - o <= n && b - j >= p;
    }

    public static boolean a(Context context) {
        boolean a;
        Throwable th;
        C = true;
        try {
            l$a a2 = l.a(context, cw.b(), cw.c(context));
            if (a2 != null) {
                T = a2.b;
                a = a(context, a2);
            } else {
                a = false;
            }
            try {
                de.a(context, T);
            } catch (Throwable th2) {
                th = th2;
                cw.a(th, "AuthUtil", "getConfig");
                f = de.b();
                e = de.b();
                return a;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            a = false;
            th = th4;
            cw.a(th, "AuthUtil", "getConfig");
            f = de.b();
            e = de.b();
            return a;
        }
        f = de.b();
        e = de.b();
        return a;
    }

    public static boolean a(Context context, long j) {
        if (!O) {
            return false;
        }
        long a = de.a();
        if (a - j < ((long) P)) {
            return false;
        }
        if (Q == -1) {
            return true;
        }
        if (de.c(a, dd.b(context, "pref", "ngpsTime", 0))) {
            int b = dd.b(context, "pref", "ngpsCount", 0);
            if (b >= Q) {
                return false;
            }
            dd.a(context, "pref", "ngpsCount", b + 1);
            return true;
        }
        try {
            Editor edit = context.getSharedPreferences("pref", 0).edit();
            edit.putLong("ngpsTime", a);
            edit.putInt("ngpsCount", 0);
            dd.a(edit);
        } catch (Throwable th) {
            cw.a(th, "AuthUtil", "resetPrefsNGPS");
        }
        dd.a(context, "pref", "ngpsCount", 1);
        return true;
    }

    private static boolean a(Context context, b bVar, String str, String str2) {
        boolean z = false;
        if (bVar != null) {
            try {
                z = bVar.a;
                Object obj = bVar.b;
                Object obj2 = bVar.c;
                CharSequence charSequence = bVar.d;
                if (!(!z || TextUtils.isEmpty(obj2) || TextUtils.isEmpty(obj) || TextUtils.isEmpty(charSequence))) {
                    au.a(context, new at(obj, obj2), cw.a(str, str2));
                }
            } catch (Throwable th) {
                cw.a(th, "AuthUtil", "downLoadPluginDex");
            }
        }
        return z;
    }

    private static boolean a(Context context, l$a l_a) {
        JSONObject jSONObject;
        boolean a;
        JSONArray optJSONArray;
        int i;
        int optInt;
        try {
            jSONObject = l_a.h;
            if (jSONObject != null) {
                a = l.a(jSONObject.optString("callamapflag"), true);
                D = a;
                if (a) {
                    E = jSONObject.optInt("count", E);
                    F = jSONObject.optLong("sysTime", F);
                    optJSONArray = jSONObject.optJSONArray(IXAdRequestInfo.SN);
                    if (optJSONArray != null && optJSONArray.length() > 0) {
                        for (i = 0; i < optJSONArray.length(); i++) {
                            G.add(optJSONArray.getString(i));
                        }
                    }
                    if (!(E == -1 || F == 0)) {
                        if (!de.b(F, dd.b(context, "pref", "nowtime", 0))) {
                            i(context);
                        }
                    }
                }
            }
        } catch (Throwable th) {
            return false;
        }
        try {
            jSONObject = l_a.k;
            if (jSONObject != null) {
                a = l.a(jSONObject.optString("amappushflag"), false);
                H = a;
                if (a) {
                    I = jSONObject.optInt("count", I);
                    J = jSONObject.optLong("sysTime", J);
                    optJSONArray = jSONObject.optJSONArray(IXAdRequestInfo.SN);
                    if (optJSONArray != null && optJSONArray.length() > 0) {
                        for (i = 0; i < optJSONArray.length(); i++) {
                            K.add(optJSONArray.getString(i));
                        }
                    }
                    if (!(I == -1 || J == 0)) {
                        if (!de.b(J, dd.b(context, "pref", "pushSerTime", 0))) {
                            j(context);
                        }
                    }
                }
            }
        } catch (Throwable th2) {
            cw.a(th2, "AuthUtil", "loadConfigData_callAMapPush");
        }
        try {
            jSONObject = l_a.l;
            if (jSONObject != null) {
                N = l.a(jSONObject.optString(User.FEMALE), false);
                w = jSONObject.optInt("mco", 0);
                x = jSONObject.optInt("co", 0);
                optInt = jSONObject.optInt("it", ACache.TIME_HOUR) * 1000;
                v = optInt;
                if (optInt < 3600000) {
                    v = 3600000;
                }
                g = jSONObject.optString("a", "提示信息");
                h = jSONObject.optString(Config.OS, "确认");
                i = jSONObject.optString("c", "取消");
                j = jSONObject.optString("i", "");
                k = jSONObject.optString(User.UNDEFINED, "");
                l = jSONObject.optString("t", "");
                if (((TextUtils.isEmpty(j) || "null".equals(j)) && (TextUtils.isEmpty(k) || "null".equals(k))) || x > w) {
                    N = false;
                }
            }
        } catch (Throwable th22) {
            cw.a(th22, "AuthUtil", "loadConfigData_openAMap");
        }
        try {
            s b = cw.b();
            d dVar = l_a.u;
            c cVar;
            com.loc.l.a.a aVar;
            JSONObject jSONObject2;
            a a2;
            b bVar;
            boolean a3;
            JSONObject jSONObject3;
            JSONArray optJSONArray2;
            JSONObject optJSONObject;
            cy cyVar;
            JSONArray optJSONArray3;
            List arrayList;
            JSONObject optJSONObject2;
            Map hashMap;
            CharSequence optString;
            if (dVar != null) {
                Object obj = dVar.b;
                Object obj2 = dVar.a;
                CharSequence charSequence = dVar.c;
                if (TextUtils.isEmpty(obj) || TextUtils.isEmpty(obj2) || TextUtils.isEmpty(charSequence)) {
                    au.a(context, null, b);
                    try {
                        cVar = l_a.v;
                        if (cVar != null) {
                            L = cVar.a;
                            M = cVar.b;
                            if (!(TextUtils.isEmpty(L) || TextUtils.isEmpty(M))) {
                                new r(context, "loc", L, M).a();
                            }
                        }
                    } catch (Throwable th222) {
                        cw.a(th222, "AuthUtil", "loadConfigData_groupOffset");
                    }
                    try {
                        aVar = l_a.t;
                        if (aVar != null) {
                            y = aVar.a;
                            dd.a(context, "pref", com.umeng.analytics.pro.b.ao, y);
                            jSONObject = aVar.c;
                            z = jSONObject.optInt("fn", z);
                            optInt = jSONObject.optInt("mpn", A);
                            A = optInt;
                            if (optInt > 500) {
                                A = 500;
                            }
                            if (A < 30) {
                                A = 30;
                            }
                            B = l.a(jSONObject.optString("igu"), false);
                            bq.a(z, B);
                            dd.a(context, "pref", "fn", z);
                            dd.a(context, "pref", "mpn", A);
                            dd.a(context, "pref", "igu", B);
                        }
                    } catch (Throwable th2222) {
                        cw.a(th2222, "AuthUtil", "loadConfigData_uploadException");
                    }
                    try {
                        jSONObject2 = l_a.m;
                        if (jSONObject2 != null && l.a(jSONObject2.optString("able"), false)) {
                            a2 = a(jSONObject2, "fs");
                            if (a2 != null) {
                                q = a2.c;
                                r = Integer.parseInt(a2.b);
                            }
                            a2 = a(jSONObject2, "us");
                            if (a2 != null) {
                                s = a2.c;
                                u = a2.a;
                                try {
                                    t = Integer.parseInt(a2.b);
                                } catch (Throwable th22222) {
                                    cw.a(th22222, "AuthUtil", "loadconfig part1");
                                }
                                if (t < 2) {
                                    s = false;
                                }
                            }
                            a2 = a(jSONObject2, "rs");
                            if (a2 != null) {
                                a = a2.c;
                                m = a;
                                if (a) {
                                    o = de.b();
                                    p = (long) (a2.d * 1000);
                                }
                                try {
                                    n = (long) (Integer.parseInt(a2.b) * 1000);
                                } catch (Throwable th222222) {
                                    cw.a(th222222, "AuthUtil", "loadconfig part");
                                }
                            }
                        }
                    } catch (Throwable th2222222) {
                        cw.a(th2222222, "AuthUtil", "loadConfigData_locate");
                    }
                    try {
                        jSONObject = l_a.o;
                        if (jSONObject != null) {
                            a = l.a(jSONObject.optString("able"), false);
                            O = a;
                            if (a) {
                                optInt = jSONObject.optInt("c", 0);
                                if (optInt == 0) {
                                    P = 3000;
                                } else {
                                    P = optInt * 1000;
                                }
                                Q = jSONObject.getInt("t") / 2;
                            }
                        }
                    } catch (Throwable th22222222) {
                        cw.a(th22222222, "AuthUtil", "loadConfigData_ngps");
                    }
                    try {
                        jSONObject = l_a.p;
                        if (jSONObject != null) {
                            a = l.a(jSONObject.optString("able"), true);
                            R = a;
                            if (a) {
                                S = (long) (jSONObject.optInt("c", 300) * 1000);
                            }
                            dd.a(context, "pref", "ca", R);
                            dd.a(context, "pref", Config.EXCEPTION_CRASH_TYPE, S);
                        }
                    } catch (Throwable th222222222) {
                        cw.a(th222222222, "AuthUtil", "loadConfigData_cacheAble");
                    }
                    try {
                        bVar = l_a.w;
                        if (bVar != null) {
                            U = a(context, bVar, "Collection", JsonSerializer.VERSION);
                        }
                    } catch (Throwable th2222222222) {
                        cw.a(th2222222222, "AuthUtil", "loadConfigData_CollectorDex");
                    }
                    try {
                        bVar = l_a.y;
                        if (bVar != null) {
                            a3 = a(context, bVar, "HttpDNS", JsonSerializer.VERSION);
                            W = a3;
                            if (!a3 && db.a(context, cw.a("HttpDNS", JsonSerializer.VERSION))) {
                                dc.a(context, "HttpDNS", "config|get dnsDex able is false");
                            }
                        }
                    } catch (Throwable th22222222222) {
                        cw.a(th22222222222, "AuthUtil", "loadConfigData_dnsDex");
                    }
                    try {
                        jSONObject3 = l_a.j;
                        if (jSONObject3 != null) {
                            Z = l.a(jSONObject3.optString("able"), false);
                            aa = jSONObject3.optLong("sysTime", de.a());
                            ab = jSONObject3.optInt("n", 1);
                            ac = jSONObject3.optInt("nh", 1);
                            if (Z && (ab == -1 || ab >= ac)) {
                                optJSONArray2 = jSONObject3.optJSONArray("l");
                                for (i = 0; i < optJSONArray2.length(); i++) {
                                    try {
                                        optJSONObject = optJSONArray2.optJSONObject(i);
                                        cyVar = new cy();
                                        a = l.a(optJSONObject.optString("able", Bugly.SDK_IS_DEV), false);
                                        cyVar.a = a;
                                        if (!a) {
                                            cyVar.b = optJSONObject.optString(Config.PACKAGE_NAME, "");
                                            cyVar.c = optJSONObject.optString("cn", "");
                                            cyVar.e = optJSONObject.optString("a", "");
                                            optJSONArray3 = optJSONObject.optJSONArray(CustomButton.POSITION_BOTTOM);
                                            if (optJSONArray3 != null) {
                                                arrayList = new ArrayList();
                                                for (optInt = 0; optInt < optJSONArray3.length(); optInt++) {
                                                    optJSONObject2 = optJSONArray3.optJSONObject(optInt);
                                                    hashMap = new HashMap();
                                                    try {
                                                        hashMap.put(optJSONObject2.optString(Config.APP_KEY), optJSONObject2.optString("v"));
                                                        arrayList.add(hashMap);
                                                    } catch (Throwable th3) {
                                                    }
                                                }
                                                cyVar.d = arrayList;
                                            }
                                            cyVar.f = l.a(optJSONObject.optString(g.ac, Bugly.SDK_IS_DEV), false);
                                            Y.add(cyVar);
                                        }
                                    } catch (Throwable th4) {
                                    }
                                }
                                optJSONArray = jSONObject3.optJSONArray("sl");
                                if (optJSONArray != null) {
                                    for (i = 0; i < optJSONArray.length(); i++) {
                                        optString = optJSONArray.optJSONObject(i).optString("pan");
                                        if (TextUtils.isEmpty(optString)) {
                                            ad.add(optString);
                                        }
                                    }
                                }
                            }
                        }
                    } catch (Throwable th222222222222) {
                        cw.a(th222222222222, "AuthUtil", "loadConfigData_otherServiceList");
                    }
                    try {
                        jSONObject = l_a.i;
                        if (jSONObject != null) {
                            a = l.a(jSONObject.optString("able"), true);
                            ae = a;
                            if (a) {
                                af = jSONObject.optInt("c", af);
                            }
                        }
                    } catch (Throwable th2222222222222) {
                        cw.a(th2222222222222, "AuthUtil", "loadConfigData_gpsGeoAble");
                    }
                    try {
                        jSONObject2 = l_a.g;
                        if (jSONObject2 != null) {
                            return true;
                        }
                        try {
                            c = (jSONObject2.optInt("ht", 30) * 60) * 1000;
                            d = (jSONObject2.optInt("at", 60) * 60) * 1000;
                        } catch (Throwable th22222222222222) {
                            cw.a(th22222222222222, "AuthUtil", "requestSdkAuthInterval");
                        }
                        try {
                            a3 = l.a(jSONObject2.optString("ofl"), true);
                            a = a3;
                            bw.a = a3;
                            if (a) {
                                bVar = l_a.x;
                                if (bVar != null) {
                                    V = a(context, bVar, "OfflineLocation", JsonSerializer.VERSION);
                                    dd.a(context, "pref", "oAble", V);
                                }
                            }
                        } catch (Throwable th222222222222222) {
                            cw.a(th222222222222222, "AuthUtil", "loadConfigData_offlineLoc");
                        }
                        try {
                            ag = l.a(jSONObject2.optString("ila"), ag);
                        } catch (Throwable th2222222222222222) {
                            cw.a(th2222222222222222, "AuthUtil", "loadConfigData_indoor");
                        }
                        a3 = l.a(jSONObject2.optString("icbd"), b);
                        b = a3;
                        if (a3) {
                            return true;
                        }
                        au.a(context, "loc");
                        return true;
                    } catch (Throwable th22222222222222222) {
                        cw.a(th22222222222222222, "AuthUtil", "loadConfigData_hotUpdate");
                        return true;
                    }
                }
                au.a(context, new at(obj2, obj), b);
                cVar = l_a.v;
                if (cVar != null) {
                    L = cVar.a;
                    M = cVar.b;
                    new r(context, "loc", L, M).a();
                }
                aVar = l_a.t;
                if (aVar != null) {
                    y = aVar.a;
                    dd.a(context, "pref", com.umeng.analytics.pro.b.ao, y);
                    jSONObject = aVar.c;
                    z = jSONObject.optInt("fn", z);
                    optInt = jSONObject.optInt("mpn", A);
                    A = optInt;
                    if (optInt > 500) {
                        A = 500;
                    }
                    if (A < 30) {
                        A = 30;
                    }
                    B = l.a(jSONObject.optString("igu"), false);
                    bq.a(z, B);
                    dd.a(context, "pref", "fn", z);
                    dd.a(context, "pref", "mpn", A);
                    dd.a(context, "pref", "igu", B);
                }
                jSONObject2 = l_a.m;
                a2 = a(jSONObject2, "fs");
                if (a2 != null) {
                    q = a2.c;
                    r = Integer.parseInt(a2.b);
                }
                a2 = a(jSONObject2, "us");
                if (a2 != null) {
                    s = a2.c;
                    u = a2.a;
                    t = Integer.parseInt(a2.b);
                    if (t < 2) {
                        s = false;
                    }
                }
                a2 = a(jSONObject2, "rs");
                if (a2 != null) {
                    a = a2.c;
                    m = a;
                    if (a) {
                        o = de.b();
                        p = (long) (a2.d * 1000);
                    }
                    n = (long) (Integer.parseInt(a2.b) * 1000);
                }
                jSONObject = l_a.o;
                if (jSONObject != null) {
                    a = l.a(jSONObject.optString("able"), false);
                    O = a;
                    if (a) {
                        optInt = jSONObject.optInt("c", 0);
                        if (optInt == 0) {
                            P = optInt * 1000;
                        } else {
                            P = 3000;
                        }
                        Q = jSONObject.getInt("t") / 2;
                    }
                }
                jSONObject = l_a.p;
                if (jSONObject != null) {
                    a = l.a(jSONObject.optString("able"), true);
                    R = a;
                    if (a) {
                        S = (long) (jSONObject.optInt("c", 300) * 1000);
                    }
                    dd.a(context, "pref", "ca", R);
                    dd.a(context, "pref", Config.EXCEPTION_CRASH_TYPE, S);
                }
                bVar = l_a.w;
                if (bVar != null) {
                    U = a(context, bVar, "Collection", JsonSerializer.VERSION);
                }
                bVar = l_a.y;
                if (bVar != null) {
                    a3 = a(context, bVar, "HttpDNS", JsonSerializer.VERSION);
                    W = a3;
                    dc.a(context, "HttpDNS", "config|get dnsDex able is false");
                }
                jSONObject3 = l_a.j;
                if (jSONObject3 != null) {
                    Z = l.a(jSONObject3.optString("able"), false);
                    aa = jSONObject3.optLong("sysTime", de.a());
                    ab = jSONObject3.optInt("n", 1);
                    ac = jSONObject3.optInt("nh", 1);
                    optJSONArray2 = jSONObject3.optJSONArray("l");
                    for (i = 0; i < optJSONArray2.length(); i++) {
                        optJSONObject = optJSONArray2.optJSONObject(i);
                        cyVar = new cy();
                        a = l.a(optJSONObject.optString("able", Bugly.SDK_IS_DEV), false);
                        cyVar.a = a;
                        if (!a) {
                            cyVar.b = optJSONObject.optString(Config.PACKAGE_NAME, "");
                            cyVar.c = optJSONObject.optString("cn", "");
                            cyVar.e = optJSONObject.optString("a", "");
                            optJSONArray3 = optJSONObject.optJSONArray(CustomButton.POSITION_BOTTOM);
                            if (optJSONArray3 != null) {
                                arrayList = new ArrayList();
                                for (optInt = 0; optInt < optJSONArray3.length(); optInt++) {
                                    optJSONObject2 = optJSONArray3.optJSONObject(optInt);
                                    hashMap = new HashMap();
                                    hashMap.put(optJSONObject2.optString(Config.APP_KEY), optJSONObject2.optString("v"));
                                    arrayList.add(hashMap);
                                }
                                cyVar.d = arrayList;
                            }
                            cyVar.f = l.a(optJSONObject.optString(g.ac, Bugly.SDK_IS_DEV), false);
                            Y.add(cyVar);
                        }
                    }
                    optJSONArray = jSONObject3.optJSONArray("sl");
                    if (optJSONArray != null) {
                        for (i = 0; i < optJSONArray.length(); i++) {
                            optString = optJSONArray.optJSONObject(i).optString("pan");
                            if (TextUtils.isEmpty(optString)) {
                                ad.add(optString);
                            }
                        }
                    }
                }
                jSONObject = l_a.i;
                if (jSONObject != null) {
                    a = l.a(jSONObject.optString("able"), true);
                    ae = a;
                    if (a) {
                        af = jSONObject.optInt("c", af);
                    }
                }
                jSONObject2 = l_a.g;
                if (jSONObject2 != null) {
                    return true;
                }
                c = (jSONObject2.optInt("ht", 30) * 60) * 1000;
                d = (jSONObject2.optInt("at", 60) * 60) * 1000;
                a3 = l.a(jSONObject2.optString("ofl"), true);
                a = a3;
                bw.a = a3;
                if (a) {
                    bVar = l_a.x;
                    if (bVar != null) {
                        V = a(context, bVar, "OfflineLocation", JsonSerializer.VERSION);
                        dd.a(context, "pref", "oAble", V);
                    }
                }
                ag = l.a(jSONObject2.optString("ila"), ag);
                a3 = l.a(jSONObject2.optString("icbd"), b);
                b = a3;
                if (a3) {
                    return true;
                }
                au.a(context, "loc");
                return true;
            }
            au.a(context, null, b);
            cVar = l_a.v;
            if (cVar != null) {
                L = cVar.a;
                M = cVar.b;
                new r(context, "loc", L, M).a();
            }
            aVar = l_a.t;
            if (aVar != null) {
                y = aVar.a;
                dd.a(context, "pref", com.umeng.analytics.pro.b.ao, y);
                jSONObject = aVar.c;
                z = jSONObject.optInt("fn", z);
                optInt = jSONObject.optInt("mpn", A);
                A = optInt;
                if (optInt > 500) {
                    A = 500;
                }
                if (A < 30) {
                    A = 30;
                }
                B = l.a(jSONObject.optString("igu"), false);
                bq.a(z, B);
                dd.a(context, "pref", "fn", z);
                dd.a(context, "pref", "mpn", A);
                dd.a(context, "pref", "igu", B);
            }
            jSONObject2 = l_a.m;
            a2 = a(jSONObject2, "fs");
            if (a2 != null) {
                q = a2.c;
                r = Integer.parseInt(a2.b);
            }
            a2 = a(jSONObject2, "us");
            if (a2 != null) {
                s = a2.c;
                u = a2.a;
                t = Integer.parseInt(a2.b);
                if (t < 2) {
                    s = false;
                }
            }
            a2 = a(jSONObject2, "rs");
            if (a2 != null) {
                a = a2.c;
                m = a;
                if (a) {
                    o = de.b();
                    p = (long) (a2.d * 1000);
                }
                n = (long) (Integer.parseInt(a2.b) * 1000);
            }
            jSONObject = l_a.o;
            if (jSONObject != null) {
                a = l.a(jSONObject.optString("able"), false);
                O = a;
                if (a) {
                    optInt = jSONObject.optInt("c", 0);
                    if (optInt == 0) {
                        P = 3000;
                    } else {
                        P = optInt * 1000;
                    }
                    Q = jSONObject.getInt("t") / 2;
                }
            }
            jSONObject = l_a.p;
            if (jSONObject != null) {
                a = l.a(jSONObject.optString("able"), true);
                R = a;
                if (a) {
                    S = (long) (jSONObject.optInt("c", 300) * 1000);
                }
                dd.a(context, "pref", "ca", R);
                dd.a(context, "pref", Config.EXCEPTION_CRASH_TYPE, S);
            }
            bVar = l_a.w;
            if (bVar != null) {
                U = a(context, bVar, "Collection", JsonSerializer.VERSION);
            }
            bVar = l_a.y;
            if (bVar != null) {
                a3 = a(context, bVar, "HttpDNS", JsonSerializer.VERSION);
                W = a3;
                dc.a(context, "HttpDNS", "config|get dnsDex able is false");
            }
            jSONObject3 = l_a.j;
            if (jSONObject3 != null) {
                Z = l.a(jSONObject3.optString("able"), false);
                aa = jSONObject3.optLong("sysTime", de.a());
                ab = jSONObject3.optInt("n", 1);
                ac = jSONObject3.optInt("nh", 1);
                optJSONArray2 = jSONObject3.optJSONArray("l");
                for (i = 0; i < optJSONArray2.length(); i++) {
                    optJSONObject = optJSONArray2.optJSONObject(i);
                    cyVar = new cy();
                    a = l.a(optJSONObject.optString("able", Bugly.SDK_IS_DEV), false);
                    cyVar.a = a;
                    if (!a) {
                        cyVar.b = optJSONObject.optString(Config.PACKAGE_NAME, "");
                        cyVar.c = optJSONObject.optString("cn", "");
                        cyVar.e = optJSONObject.optString("a", "");
                        optJSONArray3 = optJSONObject.optJSONArray(CustomButton.POSITION_BOTTOM);
                        if (optJSONArray3 != null) {
                            arrayList = new ArrayList();
                            for (optInt = 0; optInt < optJSONArray3.length(); optInt++) {
                                optJSONObject2 = optJSONArray3.optJSONObject(optInt);
                                hashMap = new HashMap();
                                hashMap.put(optJSONObject2.optString(Config.APP_KEY), optJSONObject2.optString("v"));
                                arrayList.add(hashMap);
                            }
                            cyVar.d = arrayList;
                        }
                        cyVar.f = l.a(optJSONObject.optString(g.ac, Bugly.SDK_IS_DEV), false);
                        Y.add(cyVar);
                    }
                }
                optJSONArray = jSONObject3.optJSONArray("sl");
                if (optJSONArray != null) {
                    for (i = 0; i < optJSONArray.length(); i++) {
                        optString = optJSONArray.optJSONObject(i).optString("pan");
                        if (TextUtils.isEmpty(optString)) {
                            ad.add(optString);
                        }
                    }
                }
            }
            jSONObject = l_a.i;
            if (jSONObject != null) {
                a = l.a(jSONObject.optString("able"), true);
                ae = a;
                if (a) {
                    af = jSONObject.optInt("c", af);
                }
            }
            jSONObject2 = l_a.g;
            if (jSONObject2 != null) {
                return true;
            }
            c = (jSONObject2.optInt("ht", 30) * 60) * 1000;
            d = (jSONObject2.optInt("at", 60) * 60) * 1000;
            a3 = l.a(jSONObject2.optString("ofl"), true);
            a = a3;
            bw.a = a3;
            if (a) {
                bVar = l_a.x;
                if (bVar != null) {
                    V = a(context, bVar, "OfflineLocation", JsonSerializer.VERSION);
                    dd.a(context, "pref", "oAble", V);
                }
            }
            ag = l.a(jSONObject2.optString("ila"), ag);
            a3 = l.a(jSONObject2.optString("icbd"), b);
            b = a3;
            if (a3) {
                return true;
            }
            au.a(context, "loc");
            return true;
        } catch (Throwable th222222222222222222) {
            cw.a(th222222222222222222, "AuthUtil", "loadConfigData_sdkUpdate");
        }
    }

    public static int b() {
        return r;
    }

    public static boolean b(long j) {
        if (!R) {
            return false;
        }
        return S < 0 || de.a() - j < S;
    }

    public static boolean b(Context context) {
        if (!D) {
            return false;
        }
        if (E == -1 || F == 0) {
            return true;
        }
        if (de.b(F, dd.b(context, "pref", "nowtime", 0))) {
            int b = dd.b(context, "pref", "count", 0);
            if (b >= E) {
                return false;
            }
            dd.a(context, "pref", "count", b + 1);
            return true;
        }
        i(context);
        dd.a(context, "pref", "count", 1);
        return true;
    }

    public static boolean c() {
        return s;
    }

    public static boolean c(Context context) {
        if (!H) {
            return false;
        }
        if (I == -1 || J == 0) {
            return true;
        }
        if (de.b(J, dd.b(context, "pref", "pushSerTime", 0))) {
            int b = dd.b(context, "pref", "pushCount", 0);
            if (b >= I) {
                return false;
            }
            dd.a(context, "pref", "pushCount", b + 1);
            return true;
        }
        j(context);
        dd.a(context, "pref", "pushCount", 1);
        return true;
    }

    public static int d() {
        return t;
    }

    public static boolean d(Context context) {
        if (!N || x <= 0 || w <= 0 || x > w) {
            return false;
        }
        long b = dd.b(context, "abcd", "lct", 0);
        long b2 = dd.b(context, "abcd", "lst", 0);
        long b3 = de.b();
        if (b3 < b) {
            dd.a(context, "abcd", "lct", b3);
            return false;
        }
        if (b3 - b > 86400000) {
            dd.a(context, "abcd", "lct", b3);
            dd.a(context, "abcd", "t", 0);
        }
        if (b3 - b2 < ((long) v)) {
            return false;
        }
        int b4 = dd.b(context, "abcd", "t", 0) + 1;
        if (b4 > w) {
            return false;
        }
        dd.a(context, "abcd", "lst", b3);
        dd.a(context, "abcd", "t", b4);
        return true;
    }

    public static void e(Context context) {
        try {
            y = dd.b(context, "pref", com.umeng.analytics.pro.b.ao, true);
            f(context);
        } catch (Throwable th) {
            cw.a(th, "AuthUtil", "loadLastAbleState p1");
        }
        try {
            V = dd.b(context, "pref", "oAble", false);
        } catch (Throwable th2) {
            cw.a(th2, "AuthUtil", "loadLastAbleState p2");
        }
        try {
            z = dd.b(context, "pref", "fn", z);
            A = dd.b(context, "pref", "mpn", A);
            B = dd.b(context, "pref", "igu", B);
            bq.a(z, B);
        } catch (Throwable th3) {
        }
        try {
            R = dd.b(context, "pref", "ca", R);
            S = dd.b(context, "pref", Config.EXCEPTION_CRASH_TYPE, S);
        } catch (Throwable th4) {
        }
    }

    public static boolean e() {
        return u;
    }

    public static void f(Context context) {
        try {
            s b = cw.b();
            b.a(y);
            z.a(context, b);
        } catch (Throwable th) {
        }
    }

    public static boolean f() {
        bw.a = a;
        return a;
    }

    public static String g() {
        return g;
    }

    public static boolean g(Context context) {
        if (!Z || ab == 0 || ac == 0 || aa == 0 || (ab != -1 && ab < ac)) {
            return false;
        }
        if (ad != null && ad.size() > 0) {
            for (String b : ad) {
                if (de.b(context, b)) {
                    return false;
                }
            }
        }
        if (ab == -1 && ac == -1) {
            return true;
        }
        long b2 = dd.b(context, "pref", "ots", 0);
        long b3 = dd.b(context, "pref", "otsh", 0);
        int b4 = dd.b(context, "pref", "otn", 0);
        int b5 = dd.b(context, "pref", "otnh", 0);
        if (ab != -1) {
            if (!de.b(aa, b2)) {
                try {
                    Editor edit = context.getSharedPreferences("pref", 0).edit();
                    edit.putLong("ots", aa);
                    edit.putLong("otsh", aa);
                    edit.putInt("otn", 0);
                    edit.putInt("otnh", 0);
                    dd.a(edit);
                } catch (Throwable th) {
                    cw.a(th, "AuthUtil", "resetPrefsBind");
                }
                dd.a(context, "pref", "otn", 1);
                dd.a(context, "pref", "otnh", 1);
                return true;
            } else if (b4 < ab) {
                if (ac == -1) {
                    dd.a(context, "pref", "otn", b4 + 1);
                    dd.a(context, "pref", "otnh", 0);
                    return true;
                } else if (!de.a(aa, b3)) {
                    dd.a(context, "pref", "otsh", aa);
                    dd.a(context, "pref", "otn", b4 + 1);
                    dd.a(context, "pref", "otnh", 1);
                    return true;
                } else if (b5 < ac) {
                    int i = b5 + 1;
                    dd.a(context, "pref", "otn", b4 + 1);
                    dd.a(context, "pref", "otnh", i);
                    return true;
                }
            }
        }
        if (ab == -1) {
            dd.a(context, "pref", "otn", 0);
            if (ac == -1) {
                dd.a(context, "pref", "otnh", 0);
                return true;
            } else if (!de.a(aa, b3)) {
                dd.a(context, "pref", "otsh", aa);
                dd.a(context, "pref", "otnh", 1);
                return true;
            } else if (b5 < ac) {
                dd.a(context, "pref", "otnh", b5 + 1);
                return true;
            }
        }
        return false;
    }

    public static String h() {
        return h;
    }

    public static boolean h(Context context) {
        if (context == null) {
            return false;
        }
        try {
            if (de.b() - e >= ((long) c)) {
                e = de.b();
                if (de.e(context) > T) {
                    return true;
                }
            }
            return de.b() - f >= ((long) d);
        } catch (Throwable th) {
            cw.a(th, "APS", "isConfigNeedUpdate");
            return false;
        }
    }

    public static String i() {
        return i;
    }

    private static void i(Context context) {
        try {
            Editor edit = context.getSharedPreferences("pref", 0).edit();
            edit.putLong("nowtime", F);
            edit.putInt("count", 0);
            dd.a(edit);
        } catch (Throwable th) {
            cw.a(th, "AuthUtil", "resetPrefsBind");
        }
    }

    public static String j() {
        return j;
    }

    private static void j(Context context) {
        try {
            Editor edit = context.getSharedPreferences("pref", 0).edit();
            edit.putLong("pushSerTime", J);
            edit.putInt("pushCount", 0);
            dd.a(edit);
        } catch (Throwable th) {
            cw.a(th, "AuthUtil", "resetPrefsBind");
        }
    }

    public static String k() {
        return k;
    }

    public static String l() {
        return l;
    }

    public static ArrayList<String> m() {
        return G;
    }

    public static ArrayList<String> n() {
        return K;
    }

    public static boolean o() {
        return y;
    }

    public static int p() {
        return A;
    }

    public static boolean q() {
        return C;
    }

    public static void r() {
        C = false;
    }

    public static boolean s() {
        return O;
    }

    public static long t() {
        return S;
    }

    public static boolean u() {
        return R;
    }

    public static boolean v() {
        return U;
    }

    public static boolean w() {
        return V;
    }

    public static List<cy> x() {
        return Y;
    }

    public static boolean y() {
        return ae;
    }

    public static int z() {
        return af;
    }
}
