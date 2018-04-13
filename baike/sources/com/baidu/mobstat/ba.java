package com.baidu.mobstat;

import android.content.Context;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class ba {
    public static final ba a = new ba();

    public void a(Context context, JSONObject jSONObject) {
        bd.a("startDataAnynalyzed start");
        a(jSONObject);
        az a = az.a(context);
        boolean a2 = a.a();
        bd.a("is data collect closed:" + a2);
        if (!a2) {
            if (!y.AP_LIST.b(10000)) {
                c(context);
            }
            if (!y.APP_LIST.b(10000)) {
                d(context);
            }
            if (!y.APP_TRACE.b(10000)) {
                e(context);
            }
            if (bc.e && !y.APP_APK.b(10000)) {
                f(context);
            }
            a2 = de.n(context);
            if (a2 && a.l()) {
                bd.a("sendLog");
                g(context);
            } else if (a2) {
                bd.a("can not sendLog due to time stratergy");
            } else {
                bd.a("isWifiAvailable = false, will not sendLog");
            }
        }
        bd.a("startDataAnynalyzed finished");
    }

    private void a(JSONObject jSONObject) {
        be beVar = new be(jSONObject);
        bc.b = beVar.a;
        bc.c = beVar.b;
        bc.d = beVar.c;
    }

    private void c(Context context) {
        bd.a("collectAPWithStretegy 1");
        az a = az.a(context);
        long a2 = a.a(u.AP_LIST);
        long currentTimeMillis = System.currentTimeMillis();
        long e = a.e();
        bd.a("now time: " + currentTimeMillis + ": last time: " + a2 + "; time interval: " + e);
        if (a2 == 0 || currentTimeMillis - a2 > e) {
            bd.a("collectAPWithStretegy 2");
            n.a(context);
        }
    }

    private void d(Context context) {
        bd.a("collectAPPListWithStretegy 1");
        long currentTimeMillis = System.currentTimeMillis();
        az a = az.a(context);
        long a2 = a.a(u.APP_USER_LIST);
        long f = a.f();
        bd.a("now time: " + currentTimeMillis + ": last time: " + a2 + "; userInterval : " + f);
        if (a2 == 0 || currentTimeMillis - a2 > f || !a.a(a2)) {
            bd.a("collectUserAPPListWithStretegy 2");
            n.a(context, false);
        }
        a2 = a.a(u.APP_SYS_LIST);
        long g = a.g();
        bd.a("now time: " + currentTimeMillis + ": last time: " + a2 + "; sysInterval : " + g);
        if (a2 == 0 || currentTimeMillis - a2 > g) {
            bd.a("collectSysAPPListWithStretegy 2");
            n.a(context, true);
        }
    }

    private void e(Context context) {
        bd.a("collectAPPTraceWithStretegy 1");
        long currentTimeMillis = System.currentTimeMillis();
        az a = az.a(context);
        long a2 = a.a(u.APP_TRACE_HIS);
        long i = a.i();
        bd.a("now time: " + currentTimeMillis + ": last time: " + a2 + "; time interval: " + i);
        if (a2 == 0 || currentTimeMillis - a2 > i) {
            bd.a("collectAPPTraceWithStretegy 2");
            n.b(context, false);
        }
    }

    private void f(Context context) {
        bd.a("collectAPKWithStretegy 1");
        long currentTimeMillis = System.currentTimeMillis();
        az a = az.a(context);
        long a2 = a.a(u.APP_APK);
        long h = a.h();
        bd.a("now time: " + currentTimeMillis + ": last time: " + a2 + "; interval : " + h);
        if (a2 == 0 || currentTimeMillis - a2 > h) {
            bd.a("collectAPKWithStretegy 2");
            n.b(context);
        }
    }

    public void a(Context context, String str) {
        az.a(context).a(str);
    }

    public void b(Context context, String str) {
        az.a(context).b(str);
    }

    public void a(Context context, long j) {
        az.a(context).a(u.LAST_UPDATE, j);
    }

    private void g(Context context) {
        az.a(context).a(u.LAST_SEND, System.currentTimeMillis());
        JSONObject a = v.a(context);
        bd.a("header: " + a);
        int i = 0;
        while (a()) {
            int i2 = i + 1;
            if (i > 0) {
                v.c(a);
            }
            b(context, a);
            i = i2;
        }
    }

    private boolean a() {
        if (y.AP_LIST.b() && y.APP_LIST.b() && y.APP_TRACE.b() && y.APP_CHANGE.b() && y.APP_APK.b()) {
            return false;
        }
        return true;
    }

    private void b(Context context, JSONObject jSONObject) {
        JSONArray jSONArray;
        int length;
        List<String> a;
        JSONArray jSONArray2;
        JSONObject jSONObject2;
        int i = 0;
        JSONObject jSONObject3 = new JSONObject();
        try {
            jSONObject3.put(Config.HEADER_PART, jSONObject);
            i = 0 + jSONObject.toString().length();
        } catch (Throwable e) {
            bd.a(e);
        }
        bd.a("APP_MEM");
        if (!az.a(context).b()) {
            String t = de.t(context);
            jSONArray = new JSONArray();
            bd.a(t);
            jSONArray.put(t);
            if (jSONArray.length() > 0) {
                try {
                    jSONObject3.put("app_mem3", jSONArray);
                    length = i + jSONArray.toString().length();
                } catch (Throwable e2) {
                    bd.a(e2);
                }
                bd.a("APP_APK");
                a = y.APP_APK.a(20480);
                jSONArray = new JSONArray();
                for (String str : a) {
                    bd.a(str);
                    jSONArray.put(str);
                }
                if (jSONArray.length() > 0) {
                    try {
                        jSONObject3.put("app_apk3", jSONArray);
                        length += jSONArray.toString().length();
                    } catch (Throwable e3) {
                        bd.a(e3);
                    }
                }
                bd.a("APP_CHANGE");
                a = y.APP_CHANGE.a(10240);
                jSONArray = new JSONArray();
                for (String str2 : a) {
                    bd.a(str2);
                    jSONArray.put(str2);
                }
                if (jSONArray.length() > 0) {
                    try {
                        jSONObject3.put("app_change3", jSONArray);
                        length += jSONArray.toString().length();
                    } catch (Throwable e32) {
                        bd.a(e32);
                    }
                }
                bd.a("APP_TRACE");
                a = y.APP_TRACE.a(15360);
                jSONArray = new JSONArray();
                for (String str22 : a) {
                    bd.a(str22);
                    jSONArray.put(str22);
                }
                if (jSONArray.length() > 0) {
                    try {
                        jSONObject3.put("app_trace3", jSONArray);
                        length += jSONArray.toString().length();
                    } catch (Throwable e322) {
                        bd.a(e322);
                    }
                }
                bd.a("APP_LIST");
                a = y.APP_LIST.a(46080);
                jSONArray = new JSONArray();
                for (String str222 : a) {
                    bd.a(str222);
                    jSONArray.put(str222);
                }
                if (jSONArray.length() > 0) {
                    try {
                        jSONObject3.put("app_list3", jSONArray);
                        length += jSONArray.toString().length();
                    } catch (Throwable e3222) {
                        bd.a(e3222);
                    }
                }
                bd.a("AP_LIST");
                a = y.AP_LIST.a(184320 - length);
                jSONArray = new JSONArray();
                for (String str2222 : a) {
                    bd.a(str2222);
                    jSONArray.put(str2222);
                }
                if (jSONArray.length() > 0) {
                    try {
                        jSONObject3.put("ap_list3", jSONArray);
                        length += jSONArray.toString().length();
                    } catch (Throwable e32222) {
                        bd.a(e32222);
                    }
                }
                bd.a("log in bytes is almost :" + length);
                jSONArray2 = new JSONArray();
                jSONArray2.put(jSONObject3);
                jSONObject2 = new JSONObject();
                jSONObject2.put("payload", jSONArray2);
                al.a().a(context, jSONObject2.toString());
            }
        }
        length = i;
        bd.a("APP_APK");
        a = y.APP_APK.a(20480);
        jSONArray = new JSONArray();
        for (String str22222 : a) {
            bd.a(str22222);
            jSONArray.put(str22222);
        }
        if (jSONArray.length() > 0) {
            jSONObject3.put("app_apk3", jSONArray);
            length += jSONArray.toString().length();
        }
        bd.a("APP_CHANGE");
        a = y.APP_CHANGE.a(10240);
        jSONArray = new JSONArray();
        for (String str222222 : a) {
            bd.a(str222222);
            jSONArray.put(str222222);
        }
        if (jSONArray.length() > 0) {
            jSONObject3.put("app_change3", jSONArray);
            length += jSONArray.toString().length();
        }
        bd.a("APP_TRACE");
        a = y.APP_TRACE.a(15360);
        jSONArray = new JSONArray();
        for (String str2222222 : a) {
            bd.a(str2222222);
            jSONArray.put(str2222222);
        }
        if (jSONArray.length() > 0) {
            jSONObject3.put("app_trace3", jSONArray);
            length += jSONArray.toString().length();
        }
        bd.a("APP_LIST");
        a = y.APP_LIST.a(46080);
        jSONArray = new JSONArray();
        for (String str22222222 : a) {
            bd.a(str22222222);
            jSONArray.put(str22222222);
        }
        if (jSONArray.length() > 0) {
            jSONObject3.put("app_list3", jSONArray);
            length += jSONArray.toString().length();
        }
        bd.a("AP_LIST");
        a = y.AP_LIST.a(184320 - length);
        jSONArray = new JSONArray();
        for (String str222222222 : a) {
            bd.a(str222222222);
            jSONArray.put(str222222222);
        }
        if (jSONArray.length() > 0) {
            jSONObject3.put("ap_list3", jSONArray);
            length += jSONArray.toString().length();
        }
        bd.a("log in bytes is almost :" + length);
        jSONArray2 = new JSONArray();
        jSONArray2.put(jSONObject3);
        jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("payload", jSONArray2);
            al.a().a(context, jSONObject2.toString());
        } catch (Throwable e322222) {
            bd.a(e322222);
        }
    }

    public boolean a(Context context) {
        az a = az.a(context);
        long a2 = a.a(u.LAST_UPDATE);
        long c = a.c();
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - a2 > c) {
            bd.a("need to update, checkWithLastUpdateTime lastUpdateTime =" + a2 + "nowTime=" + currentTimeMillis + ";timeInteveral=" + c);
            return true;
        }
        bd.a("no need to update, checkWithLastUpdateTime lastUpdateTime =" + a2 + "nowTime=" + currentTimeMillis + ";timeInteveral=" + c);
        return false;
    }

    public boolean b(Context context) {
        return !az.a(context).a() || a(context);
    }
}
