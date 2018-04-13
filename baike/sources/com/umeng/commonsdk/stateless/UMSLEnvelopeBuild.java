package com.umeng.commonsdk.stateless;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.proguard.a;
import com.umeng.commonsdk.proguard.b;
import com.umeng.commonsdk.proguard.g;
import com.umeng.commonsdk.proguard.u;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.e;
import com.umeng.commonsdk.statistics.d;
import com.umeng.commonsdk.utils.UMUtils;
import java.util.Iterator;
import org.json.JSONObject;

public class UMSLEnvelopeBuild {
    private static String a = null;
    private static boolean b;
    public static Context mContext;
    public static String module;

    public synchronized JSONObject buildSLBaseHeader(Context context) {
        JSONObject jSONObject = null;
        synchronized (this) {
            e.a("walle", "[stateless] begin build hader, thread is " + Thread.currentThread());
            if (context != null) {
                JSONObject jSONObject2;
                JSONObject jSONObject3;
                Context applicationContext = context.getApplicationContext();
                try {
                    if (TextUtils.isEmpty(a)) {
                        jSONObject2 = new JSONObject();
                        jSONObject2.put(g.o, DeviceConfig.getAppMD5Signature(applicationContext));
                        jSONObject2.put(g.p, DeviceConfig.getAppSHA1Key(applicationContext));
                        jSONObject2.put(g.q, DeviceConfig.getAppHashKey(applicationContext));
                        jSONObject2.put("app_version", DeviceConfig.getAppVersionName(applicationContext));
                        jSONObject2.put("version_code", Integer.parseInt(DeviceConfig.getAppVersionCode(applicationContext)));
                        jSONObject2.put(g.u, DeviceConfig.getDeviceIdUmengMD5(applicationContext));
                        jSONObject2.put(g.v, DeviceConfig.getCPU());
                        CharSequence mccmnc = DeviceConfig.getMCCMNC(applicationContext);
                        if (TextUtils.isEmpty(mccmnc)) {
                            jSONObject2.put(g.A, "");
                        } else {
                            jSONObject2.put(g.A, mccmnc);
                        }
                        mccmnc = DeviceConfig.getSubOSName(applicationContext);
                        if (!TextUtils.isEmpty(mccmnc)) {
                            jSONObject2.put(g.J, mccmnc);
                        }
                        mccmnc = DeviceConfig.getSubOSVersion(applicationContext);
                        if (!TextUtils.isEmpty(mccmnc)) {
                            jSONObject2.put(g.K, mccmnc);
                        }
                        mccmnc = DeviceConfig.getDeviceType(applicationContext);
                        if (!TextUtils.isEmpty(mccmnc)) {
                            jSONObject2.put(g.af, mccmnc);
                        }
                        jSONObject2.put("package_name", DeviceConfig.getPackageName(applicationContext));
                        jSONObject2.put(g.t, "Android");
                        jSONObject2.put("device_id", DeviceConfig.getDeviceId(applicationContext));
                        jSONObject2.put("device_model", Build.MODEL);
                        jSONObject2.put(g.D, Build.BOARD);
                        jSONObject2.put(g.E, Build.BRAND);
                        jSONObject2.put(g.F, Build.TIME);
                        jSONObject2.put(g.G, Build.MANUFACTURER);
                        jSONObject2.put(g.H, Build.ID);
                        jSONObject2.put(g.I, Build.DEVICE);
                        jSONObject2.put("os", "Android");
                        jSONObject2.put(g.x, VERSION.RELEASE);
                        int[] resolutionArray = DeviceConfig.getResolutionArray(applicationContext);
                        if (resolutionArray != null) {
                            jSONObject2.put(g.y, resolutionArray[1] + "*" + resolutionArray[0]);
                        }
                        jSONObject2.put("mc", DeviceConfig.getMac(applicationContext));
                        jSONObject2.put(g.L, DeviceConfig.getTimeZone(applicationContext));
                        String[] localeInfo = DeviceConfig.getLocaleInfo(applicationContext);
                        jSONObject2.put(g.N, localeInfo[0]);
                        jSONObject2.put(g.M, localeInfo[1]);
                        jSONObject2.put(g.O, DeviceConfig.getNetworkOperatorName(applicationContext));
                        jSONObject2.put(g.r, DeviceConfig.getAppName(applicationContext));
                        localeInfo = DeviceConfig.getNetworkAccessMode(applicationContext);
                        if ("Wi-Fi".equals(localeInfo[0])) {
                            jSONObject2.put(g.P, "wifi");
                        } else if ("2G/3G".equals(localeInfo[0])) {
                            jSONObject2.put(g.P, "2G/3G");
                        } else {
                            jSONObject2.put(g.P, "unknow");
                        }
                        if (!"".equals(localeInfo[1])) {
                            jSONObject2.put(g.Q, localeInfo[1]);
                        }
                        jSONObject2.put(g.b, d.a);
                        jSONObject2.put(g.c, d.b);
                        if (!TextUtils.isEmpty(module)) {
                            jSONObject2.put(g.d, module);
                        }
                        a = jSONObject2.toString();
                        jSONObject3 = jSONObject2;
                    } else {
                        jSONObject3 = new JSONObject(a);
                    }
                } catch (Exception e) {
                    jSONObject3 = null;
                } catch (Throwable th) {
                    b.a(applicationContext, th);
                }
                if (jSONObject3 != null) {
                    CharSequence charSequence;
                    jSONObject3.put("channel", UMUtils.getChannel(applicationContext));
                    jSONObject3.put("appkey", UMUtils.getAppkey(applicationContext));
                    try {
                        if (d.b != 1) {
                            try {
                                Class cls = Class.forName("com.umeng.commonsdk.internal.utils.SDStorageAgent");
                                if (cls != null) {
                                    charSequence = (String) cls.getMethod("getUmtt", new Class[]{Context.class}).invoke(cls, new Object[]{applicationContext});
                                } else {
                                    charSequence = null;
                                }
                            } catch (ClassNotFoundException e2) {
                                charSequence = null;
                            } catch (Throwable th2) {
                                charSequence = null;
                            }
                            if (!TextUtils.isEmpty(charSequence)) {
                                jSONObject3.put(g.e, charSequence);
                            }
                        }
                    } catch (Exception e3) {
                    }
                    try {
                        charSequence = UMEnvelopeBuild.imprintProperty(applicationContext, g.f, null);
                        if (!TextUtils.isEmpty(charSequence)) {
                            jSONObject3.put(g.f, charSequence);
                        }
                    } catch (Exception e4) {
                    }
                    try {
                        if (!(d.b == 1 || a.b(applicationContext) == null)) {
                            jSONObject3.put(g.g, a.b(applicationContext));
                        }
                    } catch (Exception e5) {
                    }
                    try {
                        jSONObject3.put("wrapper_type", a.a);
                        jSONObject3.put("wrapper_version", a.b);
                    } catch (Exception e6) {
                    }
                    if (jSONObject3 != null) {
                        if (jSONObject3.length() > 0) {
                            jSONObject2 = new JSONObject();
                            e.a("walle", "[stateless] build header end , header is " + jSONObject3.toString() + ", thread is " + Thread.currentThread());
                            jSONObject = jSONObject2.put("header", jSONObject3);
                        }
                    }
                    e.a("walle", "[stateless] build header end , header is null !!! thread is " + Thread.currentThread());
                }
            }
        }
        return jSONObject;
    }

    private synchronized JSONObject a(int i, JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                jSONObject.put(com.umeng.analytics.pro.b.ao, i);
            } catch (Exception e) {
            }
        } else {
            jSONObject = new JSONObject();
            try {
                jSONObject.put(com.umeng.analytics.pro.b.ao, i);
            } catch (Exception e2) {
            }
        }
        return jSONObject;
    }

    public synchronized JSONObject buildSLEnvelope(Context context, JSONObject jSONObject, JSONObject jSONObject2, String str) {
        e.a("walle", "[stateless] build envelope, heade is " + jSONObject.toString());
        e.a("walle", "[stateless] build envelope, body is " + jSONObject2.toString());
        e.a("walle", "[stateless] build envelope, thread is " + Thread.currentThread());
        if (context == null || jSONObject == null || jSONObject2 == null || str == null) {
            e.a("walle", "[stateless] build envelope, context is null or header is null or body is null");
            jSONObject = a(110, null);
        } else {
            try {
                c a;
                context = context.getApplicationContext();
                if (!(jSONObject == null || jSONObject2 == null)) {
                    Iterator keys = jSONObject2.keys();
                    while (keys.hasNext()) {
                        Object next = keys.next();
                        if (next != null && (next instanceof String)) {
                            String str2 = (String) next;
                            if (!(str2 == null || jSONObject2.opt(str2) == null)) {
                                try {
                                    jSONObject.put(str2, jSONObject2.opt(str2));
                                } catch (Exception e) {
                                }
                            }
                        }
                    }
                }
                if (jSONObject != null) {
                    try {
                        com.umeng.commonsdk.statistics.idtracking.e a2 = com.umeng.commonsdk.statistics.idtracking.e.a(context);
                        if (a2 != null) {
                            a2.a();
                            CharSequence encodeToString = Base64.encodeToString(new u().a(a2.b()), 0);
                            if (!TextUtils.isEmpty(encodeToString)) {
                                JSONObject jSONObject3 = jSONObject.getJSONObject("header");
                                jSONObject3.put(g.V, encodeToString);
                                jSONObject.put("header", jSONObject3);
                            }
                        }
                    } catch (Exception e2) {
                    }
                }
                if (jSONObject != null) {
                    if (f.a((long) jSONObject.toString().getBytes().length, a.c)) {
                        e.a("walle", "[stateless] build envelope, json overstep!!!! size is " + jSONObject.toString().getBytes().length);
                        jSONObject = a(113, jSONObject);
                    }
                }
                e.a("walle", "[stateless] build envelope, json size is " + jSONObject.toString().getBytes().length);
                if (jSONObject != null) {
                    a = a(context, jSONObject.toString().getBytes());
                    if (a == null) {
                        e.a("walle", "[stateless] build envelope, envelope is null !!!!");
                        jSONObject = a(111, jSONObject);
                    }
                } else {
                    a = null;
                }
                if (a != null && f.a((long) a.b().length, a.d)) {
                    e.a("walle", "[stateless] build envelope, envelope overstep!!!! size is " + a.b().length);
                    jSONObject = a(114, jSONObject);
                } else if (f.a(context, Base64.encodeToString(str.getBytes(), 0), Base64.encodeToString((str + "_" + System.currentTimeMillis()).getBytes(), 0), a.b())) {
                    e.a("walle", "[stateless] build envelope, save ok ----->>>>>");
                    e.a("walle", "[stateless] envelope file size is " + jSONObject.toString().getBytes().length);
                    d dVar = new d(context);
                    d.b(273);
                    e.a("walle", "[stateless] build envelope end, thread is " + Thread.currentThread());
                } else {
                    e.a("walle", "[stateless] build envelope, save fail ----->>>>>");
                    jSONObject = a(101, jSONObject);
                }
            } catch (Throwable th) {
                b.a(context, th);
                e.a("walle", "build envelope end, thread is " + Thread.currentThread());
                jSONObject = a(110, null);
            }
        }
        return jSONObject;
    }

    private synchronized c a(Context context, byte[] bArr) {
        c a;
        int i = -1;
        Object imprintProperty = UMEnvelopeBuild.imprintProperty(context, "slcodex", null);
        e.a("walle", "[stateless] build envelope, codexStr is " + imprintProperty);
        try {
            if (!TextUtils.isEmpty(imprintProperty)) {
                i = Integer.valueOf(imprintProperty).intValue();
            }
        } catch (Throwable e) {
            b.a(context, e);
        }
        if (i == 0) {
            e.a("walle", "[stateless] build envelope, codexValue is 0");
            a = c.a(context, UMUtils.getAppkey(context), bArr);
        } else if (i == 1) {
            e.a("walle", "[stateless] build envelope, codexValue is 1");
            a = c.b(context, UMUtils.getAppkey(context), bArr);
        } else if (b) {
            e.a("walle", "[stateless] build envelope, isEncryptEnabled is true");
            a = c.b(context, UMUtils.getAppkey(context), bArr);
        } else {
            e.a("walle", "[stateless] build envelope, isEncryptEnabled is false");
            a = c.a(context, UMUtils.getAppkey(context), bArr);
        }
        return a;
    }

    public static void setEncryptEnabled(boolean z) {
        b = z;
    }
}
