package com.umeng.commonsdk.internal;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.inputmethod.InputMethodInfo;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.baidu.mobstat.Config;
import com.umeng.analytics.pro.b;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMLogDataProtocol.UMBusinessType;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.internal.utils.a;
import com.umeng.commonsdk.internal.utils.j;
import com.umeng.commonsdk.internal.utils.k;
import com.umeng.commonsdk.internal.utils.l;
import com.umeng.commonsdk.proguard.g;
import com.umeng.commonsdk.proguard.u;
import com.umeng.commonsdk.stateless.UMSLEnvelopeBuild;
import com.umeng.commonsdk.stateless.f;
import com.umeng.commonsdk.statistics.common.e;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.core.model.User;

public class d {
    public static void a(Context context) {
        try {
            e.a("walle", "[internal] workEvent send envelope");
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(g.au, a.d);
            jSONObject = UMEnvelopeBuild.buildEnvelopeWithExtHeader(context, jSONObject, e(context));
            if (jSONObject != null && !jSONObject.has(b.ao)) {
                e.a("walle", "[internal] workEvent send envelope back, result is ok");
                a.f(context);
                j.d(context);
            }
        } catch (Throwable e) {
            com.umeng.commonsdk.proguard.b.a(context, e);
        }
    }

    public static void b(Context context) {
        e.a("walle", "[internal] begin by stateful--->>>");
        if (context != null) {
            try {
                if (UMEnvelopeBuild.isReadyBuild(context, UMBusinessType.U_INTERNAL)) {
                    UMWorkDispatch.sendEvent(context, a.e, b.a(context).a(), null);
                }
            } catch (Throwable th) {
                com.umeng.commonsdk.proguard.b.a(context, th);
            }
        }
    }

    public static void c(Context context) {
        if (context != null) {
            try {
                JSONObject jSONObject;
                e.a("walle", "[internal] begin by not stateful--->>>");
                context = context.getApplicationContext();
                f.a(context, context.getFilesDir() + MqttTopic.TOPIC_LEVEL_SEPARATOR + com.umeng.commonsdk.stateless.a.e + MqttTopic.TOPIC_LEVEL_SEPARATOR + Base64.encodeToString(a.a.getBytes(), 0), 10);
                UMSLEnvelopeBuild uMSLEnvelopeBuild = new UMSLEnvelopeBuild();
                JSONObject buildSLBaseHeader = uMSLEnvelopeBuild.buildSLBaseHeader(context);
                if (buildSLBaseHeader != null && buildSLBaseHeader.has("header")) {
                    try {
                        jSONObject = (JSONObject) buildSLBaseHeader.opt("header");
                        if (jSONObject != null) {
                            jSONObject.put(g.au, a.d);
                        }
                    } catch (Exception e) {
                    }
                }
                e.a("walle", "[internal] header is " + buildSLBaseHeader.toString());
                e.a("walle", "[internal] body is " + d(context).toString());
                jSONObject = uMSLEnvelopeBuild.buildSLEnvelope(context, buildSLBaseHeader, jSONObject, a.a);
                e.a("walle", jSONObject.toString());
            } catch (Throwable th) {
                com.umeng.commonsdk.proguard.b.a(context, th);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.json.JSONObject d(android.content.Context r5) {
        /*
        r1 = new org.json.JSONObject;
        r1.<init>();
        r2 = new org.json.JSONObject;
        r2.<init>();
        if (r5 == 0) goto L_0x0044;
    L_0x000c:
        r3 = r5.getApplicationContext();
        r0 = p(r3);	 Catch:{ Exception -> 0x0045 }
        if (r0 == 0) goto L_0x0021;
    L_0x0016:
        r4 = r0.length();	 Catch:{ Exception -> 0x0045 }
        if (r4 <= 0) goto L_0x0021;
    L_0x001c:
        r4 = "run_server";
        r2.put(r4, r0);	 Catch:{ Exception -> 0x0045 }
    L_0x0021:
        r0 = com.umeng.commonsdk.internal.utils.a.k(r3);	 Catch:{ Exception -> 0x004c }
        r4 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x004c }
        if (r4 != 0) goto L_0x0030;
    L_0x002b:
        r4 = "imsi";
        r2.put(r4, r0);	 Catch:{ Exception -> 0x004c }
    L_0x0030:
        r0 = com.umeng.commonsdk.internal.utils.a.l(r3);	 Catch:{ Exception -> 0x0051 }
        r4 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x0051 }
        if (r4 != 0) goto L_0x003f;
    L_0x003a:
        r4 = "meid";
        r2.put(r4, r0);	 Catch:{ Exception -> 0x0051 }
    L_0x003f:
        r0 = "internal";
        r1.put(r0, r2);	 Catch:{ JSONException -> 0x0056 }
    L_0x0044:
        return r1;
    L_0x0045:
        r0 = move-exception;
        com.umeng.commonsdk.proguard.b.a(r3, r0);	 Catch:{ Exception -> 0x004a }
        goto L_0x0021;
    L_0x004a:
        r0 = move-exception;
        goto L_0x0044;
    L_0x004c:
        r0 = move-exception;
        com.umeng.commonsdk.proguard.b.a(r3, r0);	 Catch:{ Exception -> 0x004a }
        goto L_0x0030;
    L_0x0051:
        r0 = move-exception;
        com.umeng.commonsdk.proguard.b.a(r3, r0);	 Catch:{ Exception -> 0x004a }
        goto L_0x003f;
    L_0x0056:
        r0 = move-exception;
        com.umeng.commonsdk.proguard.b.a(r3, r0);	 Catch:{ Exception -> 0x004a }
        goto L_0x0044;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.internal.d.d(android.content.Context):org.json.JSONObject");
    }

    public static JSONObject e(Context context) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        if (context != null) {
            JSONArray p;
            JSONObject a;
            JSONArray g;
            Context applicationContext = context.getApplicationContext();
            try {
                p = p(applicationContext);
                if (p != null && p.length() > 0) {
                    jSONObject2.put("rs", p);
                }
            } catch (Throwable e) {
                com.umeng.commonsdk.proguard.b.a(applicationContext, e);
            }
            try {
                p = q(applicationContext);
                if (p != null && p.length() > 0) {
                    jSONObject2.put("bstn", p);
                }
            } catch (Throwable e2) {
                com.umeng.commonsdk.proguard.b.a(applicationContext, e2);
            }
            try {
                p = r(applicationContext);
                if (p != null && p.length() > 0) {
                    jSONObject2.put("by", p);
                }
            } catch (Throwable e22) {
                com.umeng.commonsdk.proguard.b.a(applicationContext, e22);
            }
            try {
                a(applicationContext, jSONObject2);
            } catch (Throwable e222) {
                com.umeng.commonsdk.proguard.b.a(applicationContext, e222);
            }
            try {
                b(applicationContext, jSONObject2);
            } catch (Throwable e2222) {
                com.umeng.commonsdk.proguard.b.a(applicationContext, e2222);
            }
            try {
                a = a();
                if (a != null && a.length() > 0) {
                    jSONObject2.put("sd", a);
                }
            } catch (Throwable e22222) {
                com.umeng.commonsdk.proguard.b.a(applicationContext, e22222);
            }
            try {
                a = b();
                if (a != null && a.length() > 0) {
                    jSONObject2.put("build", a);
                }
            } catch (Throwable e222222) {
                com.umeng.commonsdk.proguard.b.a(applicationContext, e222222);
            }
            try {
                a = new JSONObject();
                g = g(applicationContext);
                if (g != null && g.length() > 0) {
                    try {
                        a.put("a_sr", g);
                    } catch (JSONException e3) {
                    }
                }
                g = j.c(applicationContext);
                if (g != null && g.length() > 0) {
                    try {
                        a.put("stat", g);
                    } catch (JSONException e4) {
                    }
                }
                jSONObject2.put("sr", a);
            } catch (Throwable e2222222) {
                com.umeng.commonsdk.proguard.b.a(applicationContext, e2222222);
            }
            try {
                a = h(applicationContext);
                if (a != null && a.length() > 0) {
                    jSONObject2.put("scr", a);
                }
            } catch (Throwable e22222222) {
                com.umeng.commonsdk.proguard.b.a(applicationContext, e22222222);
            }
            try {
                a = i(applicationContext);
                if (a != null && a.length() > 0) {
                    jSONObject2.put("sinfo", a);
                }
            } catch (Throwable e222222222) {
                com.umeng.commonsdk.proguard.b.a(applicationContext, e222222222);
            }
            try {
                a = new JSONObject();
                g = a.e(applicationContext);
                if (g != null && g.length() > 0) {
                    try {
                        a.put("wl", g);
                    } catch (JSONException e5) {
                    }
                }
                g = j(applicationContext);
                if (g != null && g.length() > 0) {
                    try {
                        a.put("a_wls", g);
                    } catch (JSONException e6) {
                    }
                }
                jSONObject2.put("winfo", a);
            } catch (Throwable e2222222222) {
                com.umeng.commonsdk.proguard.b.a(applicationContext, e2222222222);
            }
            try {
                p = k(applicationContext);
                if (p != null && p.length() > 0) {
                    jSONObject2.put("input", p);
                }
            } catch (Throwable e22222222222) {
                com.umeng.commonsdk.proguard.b.a(applicationContext, e22222222222);
            }
            try {
                a = a.o(applicationContext);
                if (a != null && a.length() > 0) {
                    jSONObject2.put("bt", a);
                }
            } catch (Throwable e222222222222) {
                com.umeng.commonsdk.proguard.b.a(applicationContext, e222222222222);
            }
            try {
                p = l(applicationContext);
                if (p != null && p.length() > 0) {
                    jSONObject2.put("cam", p);
                }
            } catch (Throwable e2222222222222) {
                com.umeng.commonsdk.proguard.b.a(applicationContext, e2222222222222);
            }
            try {
                p = m(applicationContext);
                if (p != null && p.length() > 0) {
                    jSONObject2.put("appls", p);
                }
            } catch (Throwable e22222222222222) {
                com.umeng.commonsdk.proguard.b.a(applicationContext, e22222222222222);
            }
            try {
                a = n(applicationContext);
                if (a != null && a.length() > 0) {
                    jSONObject2.put(Config.EXCEPTION_MEMORY, a);
                }
            } catch (Throwable e222222222222222) {
                com.umeng.commonsdk.proguard.b.a(applicationContext, e222222222222222);
            }
            try {
                a = o(applicationContext);
                if (a != null && a.length() > 0) {
                    jSONObject2.put("lbs", a);
                }
            } catch (Throwable e2222222222222222) {
                com.umeng.commonsdk.proguard.b.a(applicationContext, e2222222222222222);
            }
            try {
                a = d();
                if (a != null && a.length() > 0) {
                    jSONObject2.put(g.v, a);
                }
            } catch (Exception e7) {
            }
            try {
                a = c();
                if (a != null && a.length() > 0) {
                    jSONObject2.put(Config.ROM, a);
                }
            } catch (Exception e8) {
            }
            try {
                jSONObject.put(g.ak, jSONObject2);
            } catch (Throwable e22222222222222222) {
                com.umeng.commonsdk.proguard.b.a(applicationContext, e22222222222222222);
            }
        }
        return jSONObject;
    }

    private static JSONObject c() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("tot_s", a.h());
            jSONObject.put("ava_s", a.i());
            jSONObject.put("ts", System.currentTimeMillis());
        } catch (Exception e) {
        }
        return jSONObject;
    }

    private static JSONObject d() {
        JSONObject jSONObject = new JSONObject();
        try {
            com.umeng.commonsdk.internal.utils.d.a a = com.umeng.commonsdk.internal.utils.d.a();
            if (a != null) {
                jSONObject.put("pro", a.a);
                jSONObject.put("pla", a.b);
                jSONObject.put("cpus", a.c);
                jSONObject.put("fea", a.d);
                jSONObject.put("imp", a.e);
                jSONObject.put("arc", a.f);
                jSONObject.put("var", a.g);
                jSONObject.put("par", a.h);
                jSONObject.put("rev", a.i);
                jSONObject.put("har", a.j);
                jSONObject.put("rev", a.k);
                jSONObject.put("ser", a.l);
                jSONObject.put("cur_cpu", com.umeng.commonsdk.internal.utils.d.d());
                jSONObject.put("max_cpu", com.umeng.commonsdk.internal.utils.d.b());
                jSONObject.put("min_cpu", com.umeng.commonsdk.internal.utils.d.c());
                jSONObject.put("ts", System.currentTimeMillis());
            }
        } catch (Exception e) {
        }
        return jSONObject;
    }

    private static JSONObject o(Context context) {
        if (context != null) {
            return com.umeng.commonsdk.proguard.e.b(context.getApplicationContext());
        }
        return null;
    }

    private static JSONArray p(Context context) {
        JSONArray jSONArray = null;
        if (context == null) {
            return null;
        }
        ActivityManager activityManager = (ActivityManager) context.getApplicationContext().getSystemService("activity");
        if (activityManager == null) {
            return null;
        }
        List runningServices = activityManager.getRunningServices(Integer.MAX_VALUE);
        if (runningServices == null || runningServices.isEmpty()) {
            return null;
        }
        int i = 0;
        while (i < runningServices.size()) {
            JSONArray jSONArray2;
            if (runningServices.get(i) == null || ((RunningServiceInfo) runningServices.get(i)).service == null || ((RunningServiceInfo) runningServices.get(i)).service.getClassName() == null || ((RunningServiceInfo) runningServices.get(i)).service.getPackageName() == null) {
                jSONArray2 = jSONArray;
            } else {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(IXAdRequestInfo.SN, ((RunningServiceInfo) runningServices.get(i)).service.getClassName().toString());
                    jSONObject.put(Config.PACKAGE_NAME, ((RunningServiceInfo) runningServices.get(i)).service.getPackageName().toString());
                    if (jSONArray == null) {
                        jSONArray2 = new JSONArray();
                    } else {
                        jSONArray2 = jSONArray;
                    }
                    try {
                        jSONArray2.put(jSONObject);
                    } catch (JSONException e) {
                    } catch (Throwable th) {
                        Throwable th2 = th;
                        jSONArray = jSONArray2;
                        Throwable th3 = th2;
                    }
                } catch (JSONException e2) {
                    jSONArray2 = jSONArray;
                }
            }
            i++;
            jSONArray = jSONArray2;
        }
        if (jSONArray == null) {
            return jSONArray;
        }
        JSONObject jSONObject2;
        JSONObject jSONObject3 = new JSONObject();
        try {
            jSONObject3.put("ts", System.currentTimeMillis());
            jSONObject3.put("ls", jSONArray);
        } catch (JSONException e3) {
        }
        try {
            jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("sers", jSONObject3);
            } catch (JSONException e4) {
            }
            jSONArray2 = new JSONArray();
        } catch (Throwable th4) {
            th3 = th4;
            com.umeng.commonsdk.proguard.b.a(context, th3);
            return jSONArray;
        }
        try {
            jSONArray2.put(jSONObject2);
            return jSONArray2;
        } catch (Throwable th5) {
            th2 = th5;
            jSONArray = jSONArray2;
            th3 = th2;
            com.umeng.commonsdk.proguard.b.a(context, th3);
            return jSONArray;
        }
    }

    private static JSONArray q(Context context) {
        JSONArray jSONArray = new JSONArray();
        JSONObject d = k.d(context);
        if (d != null) {
            try {
                CharSequence e = k.e(context);
                if (!TextUtils.isEmpty(e)) {
                    d.put("sig", e);
                }
                jSONArray.put(d);
            } catch (Exception e2) {
            }
        }
        return jSONArray;
    }

    private static JSONArray r(Context context) {
        JSONArray jSONArray = new JSONArray();
        Object f = k.f(context);
        if (!TextUtils.isEmpty(f)) {
            try {
                JSONObject jSONObject = new JSONObject(f);
                if (jSONObject != null) {
                    jSONArray.put(jSONObject);
                }
            } catch (Exception e) {
            }
        }
        return jSONArray;
    }

    private static void a(Context context, JSONObject jSONObject) {
        if (context != null) {
            PackageManager packageManager = context.getApplicationContext().getPackageManager();
            if (packageManager != null) {
                if (jSONObject == null) {
                    jSONObject = new JSONObject();
                }
                a(jSONObject, "gp", packageManager.hasSystemFeature("android.hardware.location.gps"));
                a(jSONObject, "to", packageManager.hasSystemFeature("android.hardware.touchscreen"));
                a(jSONObject, "mo", packageManager.hasSystemFeature("android.hardware.telephony"));
                a(jSONObject, "ca", packageManager.hasSystemFeature("android.hardware.camera"));
                a(jSONObject, "fl", packageManager.hasSystemFeature("android.hardware.camera.flash"));
            }
        }
    }

    private static void a(JSONObject jSONObject, String str, boolean z) {
        if (jSONObject != null && !TextUtils.isEmpty(str)) {
            if (z) {
                try {
                    jSONObject.put(str, 1);
                    return;
                } catch (Exception e) {
                    return;
                }
            }
            jSONObject.put(str, 0);
        }
    }

    private static void b(Context context, JSONObject jSONObject) {
        if (context != null) {
            Object a = l.a(context);
            if (!TextUtils.isEmpty(a)) {
                try {
                    JSONObject jSONObject2 = new JSONObject(a);
                    if (jSONObject2 != null) {
                        if (jSONObject == null) {
                            jSONObject = new JSONObject();
                        }
                        if (jSONObject2.has(l.d)) {
                            jSONObject.put(l.d, jSONObject2.opt(l.d));
                        }
                        if (jSONObject2.has(l.c)) {
                            jSONObject.put(l.c, jSONObject2.opt(l.c));
                        }
                        if (jSONObject2.has(l.b)) {
                            jSONObject.put(l.b, jSONObject2.opt(l.b));
                        }
                    }
                } catch (Exception e) {
                }
            }
        }
    }

    public static String f(Context context) {
        try {
            com.umeng.commonsdk.statistics.idtracking.e a = com.umeng.commonsdk.statistics.idtracking.e.a(context);
            if (a != null) {
                a.a();
                Object encodeToString = Base64.encodeToString(new u().a(a.b()), 0);
                if (!TextUtils.isEmpty(encodeToString)) {
                    return encodeToString;
                }
            }
        } catch (Throwable e) {
            com.umeng.commonsdk.proguard.b.a(context, e);
        }
        return null;
    }

    public static JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(User.FEMALE, a.c());
            jSONObject.put("t", a.d());
            jSONObject.put("ts", System.currentTimeMillis());
        } catch (Exception e) {
        }
        return jSONObject;
    }

    public static JSONObject b() {
        int i = 0;
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray;
            jSONObject.put("a_pr", Build.PRODUCT);
            jSONObject.put("a_bl", Build.BOOTLOADER);
            if (VERSION.SDK_INT >= 14) {
                jSONObject.put("a_rv", Build.getRadioVersion());
            }
            jSONObject.put("a_fp", Build.FINGERPRINT);
            jSONObject.put("a_hw", Build.HARDWARE);
            jSONObject.put("a_host", Build.HOST);
            if (VERSION.SDK_INT >= 21) {
                jSONArray = new JSONArray();
                for (Object put : Build.SUPPORTED_32_BIT_ABIS) {
                    jSONArray.put(put);
                }
                if (jSONArray != null && jSONArray.length() > 0) {
                    jSONObject.put("a_s32", jSONArray);
                }
            }
            if (VERSION.SDK_INT >= 21) {
                jSONArray = new JSONArray();
                for (Object put2 : Build.SUPPORTED_64_BIT_ABIS) {
                    jSONArray.put(put2);
                }
                if (jSONArray != null && jSONArray.length() > 0) {
                    jSONObject.put("a_s64", jSONArray);
                }
            }
            if (VERSION.SDK_INT >= 21) {
                JSONArray jSONArray2 = new JSONArray();
                while (i < Build.SUPPORTED_ABIS.length) {
                    jSONArray2.put(Build.SUPPORTED_ABIS[i]);
                    i++;
                }
                if (jSONArray2 != null && jSONArray2.length() > 0) {
                    jSONObject.put("a_sa", jSONArray2);
                }
            }
            jSONObject.put("a_ta", Build.TAGS);
            jSONObject.put("a_uk", "unknown");
            jSONObject.put("a_user", Build.USER);
            jSONObject.put("a_cpu1", Build.CPU_ABI);
            jSONObject.put("a_cpu2", Build.CPU_ABI2);
            jSONObject.put("a_ra", Build.RADIO);
            if (VERSION.SDK_INT >= 23) {
                jSONObject.put("a_bos", VERSION.BASE_OS);
                jSONObject.put("a_pre", VERSION.PREVIEW_SDK_INT);
                jSONObject.put("a_sp", VERSION.SECURITY_PATCH);
            }
            jSONObject.put("a_cn", VERSION.CODENAME);
            jSONObject.put("a_intl", VERSION.INCREMENTAL);
        } catch (Exception e) {
        }
        return jSONObject;
    }

    public static JSONArray g(Context context) {
        if (context != null) {
            return k.g(context.getApplicationContext());
        }
        return null;
    }

    public static JSONObject h(Context context) {
        JSONObject jSONObject = new JSONObject();
        if (context != null) {
            try {
                jSONObject.put("a_st_h", a.h(context));
                jSONObject.put("a_nav_h", a.i(context));
                if (context.getResources() != null) {
                    DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
                    if (displayMetrics != null) {
                        jSONObject.put("a_den", (double) displayMetrics.density);
                        jSONObject.put("a_dpi", displayMetrics.densityDpi);
                    }
                }
            } catch (Throwable e) {
                com.umeng.commonsdk.proguard.b.a(context, e);
            }
        }
        return jSONObject;
    }

    public static JSONObject i(Context context) {
        JSONObject jSONObject = new JSONObject();
        if (context != null) {
            Context applicationContext = context.getApplicationContext();
            String packageName = applicationContext.getPackageName();
            jSONObject.put("a_fit", a.a(applicationContext, packageName));
            jSONObject.put("a_alut", a.b(applicationContext, packageName));
            jSONObject.put("a_c", a.c(applicationContext, packageName));
            jSONObject.put("a_uid", a.d(applicationContext, packageName));
            if (a.a()) {
                jSONObject.put("a_root", 1);
            } else {
                jSONObject.put("a_root", 0);
            }
            jSONObject.put("tf", a.b());
            jSONObject.put("s_fs", (double) a.a(applicationContext));
            jSONObject.put("a_meid", a.l(applicationContext));
            jSONObject.put("a_imsi", a.k(applicationContext));
            jSONObject.put(Config.STAT_SDK_TYPE, a.f());
            CharSequence b = k.b(applicationContext);
            if (!TextUtils.isEmpty(b)) {
                try {
                    jSONObject.put("a_iccid", b);
                } catch (Exception e) {
                }
            }
            try {
                b = k.c(applicationContext);
                if (!TextUtils.isEmpty(b)) {
                    try {
                        jSONObject.put("a_simei", b);
                    } catch (Exception e2) {
                    }
                }
                jSONObject.put("hn", a.g());
                jSONObject.put("ts", System.currentTimeMillis());
            } catch (Throwable e3) {
                com.umeng.commonsdk.proguard.b.a(applicationContext, e3);
            }
        }
        return jSONObject;
    }

    public static JSONArray j(Context context) {
        JSONArray jSONArray = new JSONArray();
        if (context != null) {
            Context applicationContext = context.getApplicationContext();
            List<ScanResult> b = a.b(applicationContext);
            if (b != null && b.size() > 0) {
                for (ScanResult scanResult : b) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("a_bssid", scanResult.BSSID);
                        jSONObject.put("a_ssid", scanResult.BSSID);
                        jSONObject.put("a_cap", scanResult.capabilities);
                        jSONObject.put("a_fcy", scanResult.frequency);
                        jSONObject.put("ts", System.currentTimeMillis());
                        if (VERSION.SDK_INT >= 23) {
                            jSONObject.put("a_c0", scanResult.centerFreq0);
                            jSONObject.put("a_c1", scanResult.centerFreq1);
                            jSONObject.put("a_cw", scanResult.channelWidth);
                            if (scanResult.is80211mcResponder()) {
                                jSONObject.put("a_is80211", 1);
                            } else {
                                jSONObject.put("a_is80211", 0);
                            }
                            if (scanResult.isPasspointNetwork()) {
                                jSONObject.put("a_isppn", 1);
                            } else {
                                jSONObject.put("a_isppn", 0);
                            }
                            jSONObject.put("a_ofn", scanResult.operatorFriendlyName);
                            jSONObject.put("a_vn", scanResult.venueName);
                        }
                        jSONObject.put("a_dc", scanResult.describeContents());
                        if (jSONObject != null) {
                            jSONArray.put(jSONObject);
                        }
                    } catch (Throwable e) {
                        com.umeng.commonsdk.proguard.b.a(applicationContext, e);
                    }
                }
            }
        }
        return jSONArray;
    }

    public static JSONArray k(Context context) {
        JSONArray jSONArray = new JSONArray();
        if (context != null) {
            Context applicationContext = context.getApplicationContext();
            List<InputMethodInfo> m = a.m(applicationContext);
            if (m != null) {
                for (InputMethodInfo inputMethodInfo : m) {
                    try {
                        CharSequence loadLabel = inputMethodInfo.loadLabel(applicationContext.getPackageManager());
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("a_la", loadLabel);
                        jSONObject.put("a_pn", inputMethodInfo.getPackageName());
                        jSONObject.put("ts", System.currentTimeMillis());
                        if (jSONObject != null) {
                            jSONArray.put(jSONObject);
                        }
                    } catch (Throwable e) {
                        com.umeng.commonsdk.proguard.b.a(applicationContext, e);
                    }
                }
            }
        }
        return jSONArray;
    }

    public static JSONArray l(Context context) {
        JSONArray jSONArray = new JSONArray();
        if (context != null) {
            Context applicationContext = context.getApplicationContext();
            List<j.a> e = j.e(applicationContext);
            if (!(e == null || e.isEmpty())) {
                for (j.a aVar : e) {
                    if (aVar != null) {
                        try {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("a_w", aVar.a);
                            jSONObject.put("a_h", aVar.b);
                            jSONObject.put("ts", System.currentTimeMillis());
                            if (jSONObject != null) {
                                jSONArray.put(jSONObject);
                            }
                        } catch (Throwable e2) {
                            com.umeng.commonsdk.proguard.b.a(applicationContext, e2);
                        }
                    }
                }
            }
        }
        return jSONArray;
    }

    public static JSONArray m(Context context) {
        JSONArray jSONArray = new JSONArray();
        if (context != null) {
            Context applicationContext = context.getApplicationContext();
            List<a.a> p = a.p(applicationContext);
            if (!(p == null || p.isEmpty())) {
                for (a.a aVar : p) {
                    if (aVar != null) {
                        try {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("a_pn", aVar.a);
                            jSONObject.put("a_la", aVar.b);
                            jSONObject.put("ts", System.currentTimeMillis());
                            if (jSONObject != null) {
                                jSONArray.put(jSONObject);
                            }
                        } catch (Throwable e) {
                            com.umeng.commonsdk.proguard.b.a(applicationContext, e);
                        }
                    }
                }
            }
        }
        return jSONArray;
    }

    public static JSONObject n(Context context) {
        JSONObject jSONObject = new JSONObject();
        if (context != null) {
            Context applicationContext = context.getApplicationContext();
            MemoryInfo q = a.q(applicationContext);
            if (q != null) {
                try {
                    if (VERSION.SDK_INT >= 16) {
                        jSONObject.put("t", q.totalMem);
                    }
                    jSONObject.put(User.FEMALE, q.availMem);
                    jSONObject.put("ts", System.currentTimeMillis());
                } catch (Throwable e) {
                    com.umeng.commonsdk.proguard.b.a(applicationContext, e);
                }
            }
        }
        return jSONObject;
    }
}
