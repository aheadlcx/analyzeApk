package com.umeng.commonsdk.statistics;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.proguard.a;
import com.umeng.commonsdk.proguard.g;
import com.umeng.commonsdk.proguard.l;
import com.umeng.commonsdk.proguard.u;
import com.umeng.commonsdk.statistics.common.DataHelper;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.e;
import com.umeng.commonsdk.statistics.idtracking.Envelope;
import com.umeng.commonsdk.statistics.idtracking.ImprintHandler;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.statistics.internal.StatTracer;
import com.umeng.commonsdk.statistics.noise.ImLatent;
import com.umeng.commonsdk.utils.UMUtils;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class b {
    public static String a;
    private static StatTracer b = null;
    private static ImLatent c = null;
    private static String d = null;
    private static boolean f;
    private int e = 0;

    public static long a(Context context) {
        long j = DataHelper.ENVELOPE_ENTITY_RAW_LENGTH_MAX - DataHelper.ENVELOPE_EXTRA_LENGTH;
        JSONObject b = b(context);
        if (!(b == null || b.toString() == null || b.toString().getBytes() == null)) {
            long length = (long) b.toString().getBytes().length;
            if (e.a) {
                Log.i("EnvelopeManager", "headerLen size is " + length);
            }
            j -= length;
        }
        if (e.a) {
            Log.i("EnvelopeManager", "free size is " + j);
        }
        return j;
    }

    private JSONObject a(int i, JSONObject jSONObject) {
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

    public JSONObject a(Context context, JSONObject jSONObject, JSONObject jSONObject2) {
        Iterator keys;
        JSONObject jSONObject3;
        Throwable th;
        Throwable th2;
        if (!(!e.a || jSONObject == null || jSONObject2 == null)) {
            Log.i("EnvelopeManager", "headerJSONObject size is " + jSONObject.toString().getBytes().length);
            Log.i("EnvelopeManager", "bodyJSONObject size is " + jSONObject2.toString().getBytes().length);
        }
        if (context == null || jSONObject2 == null) {
            return a(110, null);
        }
        JSONObject b;
        Object next;
        String str;
        try {
            String substring;
            JSONObject jSONObject4;
            Envelope envelope;
            b = b(context);
            if (!(b == null || jSONObject == null)) {
                b = a(b, jSONObject);
            }
            if (!(b == null || jSONObject2 == null)) {
                keys = jSONObject2.keys();
                while (keys.hasNext()) {
                    next = keys.next();
                    if (next != null && (next instanceof String)) {
                        str = (String) next;
                        if (!(str == null || jSONObject2.opt(str) == null)) {
                            try {
                                b.put(str, jSONObject2.opt(str));
                            } catch (Exception e) {
                            }
                        }
                    }
                }
            }
            if (b != null) {
                StringBuilder stringBuilder = new StringBuilder();
                if (b.length() > 0) {
                    Object optString;
                    if (b.has("push")) {
                        next = "p";
                        optString = b.optJSONObject("header").optString(g.as);
                        if (!(TextUtils.isEmpty(next) || TextUtils.isEmpty(optString))) {
                            stringBuilder.append(next).append("==").append(optString).append("&=");
                        }
                    }
                    if (b.has("share")) {
                        next = "s";
                        optString = b.optJSONObject("header").optString(g.at);
                        if (!(TextUtils.isEmpty(next) || TextUtils.isEmpty(optString))) {
                            stringBuilder.append(next).append("==").append(optString).append("&=");
                        }
                    }
                    if (b.has("analytics")) {
                        if (b.has("dplus")) {
                            next = g.an;
                        } else {
                            next = "a";
                        }
                        optString = b.optJSONObject("header").optString("sdk_version");
                        if (!(TextUtils.isEmpty(next) || TextUtils.isEmpty(optString))) {
                            stringBuilder.append(next).append("==").append(optString).append("&=");
                        }
                    }
                    if (b.has("dplus")) {
                        next = b.optJSONObject("header").optString("sdk_version");
                        if (b.has("analytics")) {
                            optString = g.an;
                            if (!(stringBuilder.toString().contains(g.an) || TextUtils.isEmpty(optString) || TextUtils.isEmpty(next))) {
                                stringBuilder.append(optString).append("==").append(next).append("&=");
                            }
                        } else {
                            optString = "d";
                            if (!(TextUtils.isEmpty(optString) || TextUtils.isEmpty(next))) {
                                stringBuilder.append(optString).append("==").append(next).append("&=");
                            }
                        }
                    }
                    if (b.has(g.ak)) {
                        next = "i";
                        optString = b.optJSONObject("header").optString(g.au);
                        if (!(TextUtils.isEmpty(next) || TextUtils.isEmpty(optString))) {
                            stringBuilder.append(next).append("==").append(optString).append("&=");
                        }
                    }
                }
                str = stringBuilder.toString();
                if (TextUtils.isEmpty(str)) {
                    return a(101, b);
                }
                substring = str.endsWith("&=") ? str.substring(0, str.length() - 2) : str;
            } else {
                substring = null;
            }
            if (b != null) {
                try {
                    com.umeng.commonsdk.statistics.idtracking.e a = com.umeng.commonsdk.statistics.idtracking.e.a(context);
                    if (a != null) {
                        a.a();
                        CharSequence encodeToString = Base64.encodeToString(new u().a(a.b()), 0);
                        if (!TextUtils.isEmpty(encodeToString)) {
                            jSONObject4 = b.getJSONObject("header");
                            jSONObject4.put(g.V, encodeToString);
                            b.put("header", jSONObject4);
                        }
                    }
                } catch (Exception e2) {
                }
            }
            if (b == null) {
                b = StatTracer.getInstance(context);
                c = ImLatent.getService(context, b);
            }
            if (c != null && c.shouldStartLatency()) {
                long delayTime = c.getDelayTime();
                long elapsedTime = c.getElapsedTime();
                if (b != null) {
                    try {
                        jSONObject3 = new JSONObject();
                        jSONObject4 = new JSONObject();
                        jSONObject4.put(g.az, elapsedTime / 1000);
                        jSONObject4.put(g.ay, delayTime);
                        jSONObject3.put(g.ax, jSONObject4);
                        jSONObject4 = b.getJSONObject("header");
                        jSONObject4.put(g.aw, jSONObject3);
                        b.put("header", jSONObject4);
                    } catch (Exception e3) {
                    }
                }
            }
            if (b != null) {
                if (DataHelper.largeThanMaxSize((long) b.toString().getBytes().length, DataHelper.ENVELOPE_ENTITY_RAW_LENGTH_MAX)) {
                    SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(context);
                    if (sharedPreferences != null) {
                        sharedPreferences.edit().putInt("serial", sharedPreferences.getInt("serial", 1) + 1).commit();
                    }
                    return a(113, b);
                }
            }
            if (b != null) {
                Envelope a2 = a(context, b.toString().getBytes());
                if (a2 == null) {
                    return a(111, b);
                }
                envelope = a2;
            } else {
                envelope = null;
            }
            if (envelope != null && DataHelper.largeThanMaxSize((long) envelope.toBinary().length, DataHelper.ENVELOPE_LENGTH_MAX)) {
                return a(114, b);
            }
            str = null;
            if (b != null) {
                str = b.optJSONObject("header").optString("app_version");
            }
            int a3 = a(context, envelope, substring, str);
            if (a3 != 0) {
                return a(a3, b);
            }
            if (e.a) {
                Log.i("EnvelopeManager", "constructHeader size is " + b.toString().getBytes().length);
            }
            return b;
        } catch (Throwable th3) {
            com.umeng.commonsdk.proguard.b.a(context, th3);
            b = null;
            if (jSONObject != null) {
                if (null == null) {
                    try {
                        b = new JSONObject();
                    } catch (Throwable th32) {
                        th = th32;
                        jSONObject3 = null;
                        th2 = th;
                        com.umeng.commonsdk.proguard.b.a(context, th2);
                        return a(110, jSONObject3);
                    }
                }
                try {
                    b.put("header", jSONObject);
                    jSONObject3 = b;
                } catch (JSONException e4) {
                    jSONObject3 = b;
                } catch (Throwable th322) {
                    th = th322;
                    jSONObject3 = b;
                    th2 = th;
                    com.umeng.commonsdk.proguard.b.a(context, th2);
                    return a(110, jSONObject3);
                }
            }
            jSONObject3 = null;
            if (jSONObject2 != null) {
                if (jSONObject3 == null) {
                    try {
                        b = new JSONObject();
                    } catch (Exception e5) {
                        th2 = e5;
                        com.umeng.commonsdk.proguard.b.a(context, th2);
                        return a(110, jSONObject3);
                    }
                }
                b = jSONObject3;
                if (jSONObject2 != null) {
                    try {
                        keys = jSONObject2.keys();
                        while (keys.hasNext()) {
                            next = keys.next();
                            if (next != null && (next instanceof String)) {
                                str = (String) next;
                                if (!(str == null || jSONObject2.opt(str) == null)) {
                                    try {
                                        b.put(str, jSONObject2.opt(str));
                                    } catch (Exception e6) {
                                    }
                                }
                            }
                        }
                    } catch (Throwable th3222) {
                        th = th3222;
                        jSONObject3 = b;
                        th2 = th;
                        com.umeng.commonsdk.proguard.b.a(context, th2);
                        return a(110, jSONObject3);
                    }
                }
                jSONObject3 = b;
            }
            return a(110, jSONObject3);
        }
    }

    private static JSONObject b(Context context) {
        JSONObject jSONObject;
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(context);
        if (TextUtils.isEmpty(d)) {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(g.o, DeviceConfig.getAppMD5Signature(context));
            jSONObject2.put(g.p, DeviceConfig.getAppSHA1Key(context));
            jSONObject2.put(g.q, DeviceConfig.getAppHashKey(context));
            jSONObject2.put("app_version", DeviceConfig.getAppVersionName(context));
            jSONObject2.put("version_code", Integer.parseInt(DeviceConfig.getAppVersionCode(context)));
            jSONObject2.put(g.u, DeviceConfig.getDeviceIdUmengMD5(context));
            jSONObject2.put(g.v, DeviceConfig.getCPU());
            CharSequence mccmnc = DeviceConfig.getMCCMNC(context);
            if (TextUtils.isEmpty(mccmnc)) {
                jSONObject2.put(g.A, "");
            } else {
                jSONObject2.put(g.A, mccmnc);
            }
            mccmnc = DeviceConfig.getSubOSName(context);
            if (!TextUtils.isEmpty(mccmnc)) {
                jSONObject2.put(g.J, mccmnc);
            }
            mccmnc = DeviceConfig.getSubOSVersion(context);
            if (!TextUtils.isEmpty(mccmnc)) {
                jSONObject2.put(g.K, mccmnc);
            }
            mccmnc = DeviceConfig.getDeviceType(context);
            if (!TextUtils.isEmpty(mccmnc)) {
                jSONObject2.put(g.af, mccmnc);
            }
            jSONObject2.put("package_name", DeviceConfig.getPackageName(context));
            jSONObject2.put(g.t, "Android");
            jSONObject2.put("device_id", DeviceConfig.getDeviceId(context));
            jSONObject2.put("device_model", Build.MODEL);
            jSONObject2.put(g.D, Build.BOARD);
            jSONObject2.put(g.E, Build.BRAND);
            jSONObject2.put(g.F, Build.TIME);
            jSONObject2.put(g.G, Build.MANUFACTURER);
            jSONObject2.put(g.H, Build.ID);
            jSONObject2.put(g.I, Build.DEVICE);
            jSONObject2.put("os", "Android");
            jSONObject2.put(g.x, VERSION.RELEASE);
            int[] resolutionArray = DeviceConfig.getResolutionArray(context);
            if (resolutionArray != null) {
                jSONObject2.put(g.y, resolutionArray[1] + "*" + resolutionArray[0]);
            }
            jSONObject2.put("mc", DeviceConfig.getMac(context));
            jSONObject2.put(g.L, DeviceConfig.getTimeZone(context));
            String[] localeInfo = DeviceConfig.getLocaleInfo(context);
            jSONObject2.put(g.N, localeInfo[0]);
            jSONObject2.put(g.M, localeInfo[1]);
            jSONObject2.put(g.O, DeviceConfig.getNetworkOperatorName(context));
            jSONObject2.put(g.r, DeviceConfig.getAppName(context));
            localeInfo = DeviceConfig.getNetworkAccessMode(context);
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
            if (!TextUtils.isEmpty(a)) {
                jSONObject2.put(g.d, a);
            }
            d = jSONObject2.toString();
            jSONObject = jSONObject2;
        } else {
            try {
                jSONObject = new JSONObject(d);
            } catch (Exception e) {
                jSONObject = null;
            }
        }
        if (jSONObject == null) {
            return null;
        }
        CharSequence deviceToken;
        try {
            jSONObject.put(g.R, sharedPreferences.getInt("successful_request", 0));
            jSONObject.put(g.S, sharedPreferences.getInt(g.S, 0));
            jSONObject.put(g.T, sharedPreferences.getInt("last_request_spent_ms", 0));
        } catch (Exception e2) {
        }
        try {
            jSONObject.put("channel", UMUtils.getChannel(context));
            jSONObject.put("appkey", UMUtils.getAppkey(context));
            deviceToken = UMUtils.getDeviceToken(context);
            if (!TextUtils.isEmpty(deviceToken)) {
                jSONObject.put(g.a, deviceToken);
            }
        } catch (Throwable e3) {
            com.umeng.commonsdk.proguard.b.a(context, e3);
        } catch (Throwable e32) {
            com.umeng.commonsdk.proguard.b.a(context, e32);
            return null;
        }
        try {
            if (d.b != 1) {
                try {
                    Class cls = Class.forName("com.umeng.commonsdk.internal.utils.SDStorageAgent");
                    if (cls != null) {
                        deviceToken = (String) cls.getMethod("getUmtt", new Class[]{Context.class}).invoke(cls, new Object[]{context});
                    } else {
                        deviceToken = null;
                    }
                } catch (ClassNotFoundException e4) {
                    deviceToken = null;
                } catch (Throwable th) {
                    deviceToken = null;
                }
                if (!TextUtils.isEmpty(deviceToken)) {
                    jSONObject.put(g.e, deviceToken);
                }
            }
        } catch (Throwable e322) {
            com.umeng.commonsdk.proguard.b.a(context, e322);
        }
        try {
            deviceToken = UMEnvelopeBuild.imprintProperty(context, g.f, null);
            if (!TextUtils.isEmpty(deviceToken)) {
                jSONObject.put(g.f, deviceToken);
            }
        } catch (Throwable e3222) {
            com.umeng.commonsdk.proguard.b.a(context, e3222);
        }
        try {
            if (!(d.b == 1 || a.b(context) == null)) {
                jSONObject.put(g.g, a.b(context));
            }
        } catch (Exception e5) {
        }
        try {
            jSONObject.put("wrapper_type", a.a);
            jSONObject.put("wrapper_version", a.b);
        } catch (Exception e6) {
        }
        try {
            l a = ImprintHandler.getImprintService(context).a();
            if (a != null) {
                jSONObject.put(g.U, Base64.encodeToString(new u().a(a), 0));
            }
        } catch (Throwable e32222) {
            com.umeng.commonsdk.proguard.b.a(context, e32222);
        }
        if (jSONObject == null) {
            return null;
        }
        if (jSONObject.length() > 0) {
            return new JSONObject().put("header", jSONObject);
        }
        return null;
    }

    private JSONObject a(JSONObject jSONObject, JSONObject jSONObject2) {
        if (!(jSONObject == null || jSONObject2 == null || jSONObject.opt("header") == null || !(jSONObject.opt("header") instanceof JSONObject))) {
            JSONObject jSONObject3 = (JSONObject) jSONObject.opt("header");
            Iterator keys = jSONObject2.keys();
            while (keys.hasNext()) {
                Object next = keys.next();
                if (next != null && (next instanceof String)) {
                    String str = (String) next;
                    if (!(str == null || jSONObject2.opt(str) == null)) {
                        try {
                            jSONObject3.put(str, jSONObject2.opt(str));
                            if (str.equals(com.umeng.analytics.pro.b.i) && (jSONObject2.opt(str) instanceof Integer)) {
                                this.e = ((Integer) jSONObject2.opt(str)).intValue();
                            }
                        } catch (Exception e) {
                        }
                    }
                }
            }
        }
        return jSONObject;
    }

    private Envelope a(Context context, byte[] bArr) {
        int i = -1;
        Object imprintProperty = UMEnvelopeBuild.imprintProperty(context, "codex", null);
        try {
            if (!TextUtils.isEmpty(imprintProperty)) {
                i = Integer.valueOf(imprintProperty).intValue();
            }
        } catch (Throwable e) {
            com.umeng.commonsdk.proguard.b.a(context, e);
        }
        if (i == 0) {
            return Envelope.genEnvelope(context, UMUtils.getAppkey(context), bArr);
        }
        if (i == 1) {
            return Envelope.genEncryptEnvelope(context, UMUtils.getAppkey(context), bArr);
        }
        if (f) {
            return Envelope.genEncryptEnvelope(context, UMUtils.getAppkey(context), bArr);
        }
        return Envelope.genEnvelope(context, UMUtils.getAppkey(context), bArr);
    }

    private int a(Context context, Envelope envelope, String str, String str2) {
        if (context == null || envelope == null || TextUtils.isEmpty(str)) {
            return 101;
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = DeviceConfig.getAppVersionName(context);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str).append("&&").append(str2).append("_").append(System.currentTimeMillis()).append("_envelope.log");
        return com.umeng.commonsdk.framework.b.a(context, stringBuilder.toString(), envelope.toBinary());
    }

    public static void a(boolean z) {
        f = z;
    }
}
