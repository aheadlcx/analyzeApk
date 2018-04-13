package com.umeng.analytics.pro;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.b;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.utils.UMUtils;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;
import org.json.JSONObject;

public class o {
    private static String c = null;
    private static Context d = null;
    private final String a;
    private final String b;

    private static class a {
        private static final o a = new o();
    }

    private o() {
        this.a = "a_start_time";
        this.b = "a_end_time";
    }

    public static o a() {
        return a.a;
    }

    public boolean a(Context context) {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(context);
        String string = sharedPreferences.getString("session_id", null);
        if (string == null) {
            return false;
        }
        long j = sharedPreferences.getLong("session_start_time", 0);
        long j2 = sharedPreferences.getLong("session_end_time", 0);
        if (j2 == 0 || Math.abs(j2 - j) > 86400000) {
            try {
            } catch (Throwable th) {
                return false;
            }
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("__ii", string);
        jSONObject.put("__e", j);
        jSONObject.put(com.umeng.analytics.pro.c.e.a.g, j2);
        double[] location = AnalyticsConfig.getLocation();
        if (location != null) {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("lat", location[0]);
            jSONObject2.put("lng", location[1]);
            jSONObject2.put("ts", System.currentTimeMillis());
            jSONObject.put(com.umeng.analytics.pro.c.e.a.e, jSONObject2);
        }
        Class cls = Class.forName("android.net.TrafficStats");
        Method method = cls.getMethod("getUidRxBytes", new Class[]{Integer.TYPE});
        Method method2 = cls.getMethod("getUidTxBytes", new Class[]{Integer.TYPE});
        if (context.getApplicationInfo().uid == -1) {
            return false;
        }
        long longValue = ((Long) method.invoke(null, new Object[]{Integer.valueOf(context.getApplicationInfo().uid)})).longValue();
        j = ((Long) method2.invoke(null, new Object[]{Integer.valueOf(r6)})).longValue();
        if (longValue > 0 && j > 0) {
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put(b.C, longValue);
            jSONObject3.put(b.B, j);
            jSONObject.put(com.umeng.analytics.pro.c.e.a.d, jSONObject3);
        }
        g.a(context).a(string, jSONObject, com.umeng.analytics.pro.g.a.NEWSESSION);
        p.a(d);
        h.a(d);
        a(sharedPreferences);
        return true;
    }

    private void a(SharedPreferences sharedPreferences) {
        Editor edit = sharedPreferences.edit();
        edit.remove("session_start_time");
        edit.remove("session_end_time");
        edit.remove("a_start_time");
        edit.remove("a_end_time");
        edit.commit();
    }

    public String b(Context context) {
        String deviceId = DeviceConfig.getDeviceId(context);
        String appkey = UMUtils.getAppkey(context);
        long currentTimeMillis = System.currentTimeMillis();
        if (appkey == null) {
            throw new RuntimeException("Appkey is null or empty, Please check!");
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(currentTimeMillis).append(appkey).append(deviceId);
        c = UMUtils.MD5(stringBuilder.toString());
        return c;
    }

    public void a(Context context, Object obj) {
        try {
            if (d == null && context != null) {
                d = context.getApplicationContext();
            }
            long longValue = ((Long) obj).longValue();
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(d);
            if (sharedPreferences != null) {
                Editor edit = sharedPreferences.edit();
                String string = sharedPreferences.getString(b.al, "");
                String appVersionName = UMUtils.getAppVersionName(d);
                if (!TextUtils.isEmpty(string) && !string.equals(appVersionName)) {
                    int i = sharedPreferences.getInt("versioncode", 0);
                    String string2 = sharedPreferences.getString("pre_date", "");
                    String string3 = sharedPreferences.getString("pre_version", "");
                    String string4 = sharedPreferences.getString(b.al, "");
                    edit.putString("vers_date", string2);
                    edit.putString("vers_pre_version", string3);
                    edit.putString("cur_version", string4);
                    edit.putString("dp_vers_date", string2);
                    edit.putString("dp_vers_pre_version", string3);
                    edit.putString("dp_cur_version", string4);
                    edit.putInt("vers_code", i);
                    edit.putString("vers_name", string);
                    edit.putInt("dp_vers_code", i);
                    edit.putString("dp_vers_name", string);
                    edit.putLong("a_end_time", 0);
                    edit.commit();
                    if (c(context) == null) {
                        c = a(context, sharedPreferences, longValue);
                    }
                    a(d, longValue);
                    b(d, longValue);
                } else if (a(sharedPreferences, longValue)) {
                    c = a(context, sharedPreferences, longValue);
                    MLog.i("Start new session: " + c);
                } else {
                    c = sharedPreferences.getString("session_id", null);
                    edit.putLong("a_start_time", longValue);
                    edit.putLong("a_end_time", 0);
                    edit.commit();
                    MLog.i("Extend current session: " + c);
                    d(context);
                    i.a(d).a(false);
                    i.a(d).d();
                }
            }
        } catch (Throwable th) {
        }
    }

    public void b(Context context, Object obj) {
        try {
            if (d == null && context != null) {
                d = context.getApplicationContext();
            }
            long longValue = ((Long) obj).longValue();
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(context);
            if (sharedPreferences != null) {
                if (sharedPreferences.getLong("a_start_time", 0) == 0 && AnalyticsConfig.ACTIVITY_DURATION_OPEN) {
                    MLog.e("onPause called before onResume");
                    return;
                }
                Editor edit = sharedPreferences.edit();
                edit.putLong("a_end_time", longValue);
                edit.putLong("session_end_time", longValue);
                edit.commit();
            }
        } catch (Throwable th) {
        }
    }

    public boolean b() {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(d);
        if (sharedPreferences == null) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long j = sharedPreferences.getLong("a_start_time", 0);
        long j2 = sharedPreferences.getLong("a_end_time", 0);
        if ((j == 0 || currentTimeMillis - j >= AnalyticsConfig.kContinueSessionMillis) && currentTimeMillis - j2 > AnalyticsConfig.kContinueSessionMillis) {
            return true;
        }
        return false;
    }

    private boolean a(SharedPreferences sharedPreferences, long j) {
        long j2 = sharedPreferences.getLong("a_start_time", 0);
        long j3 = sharedPreferences.getLong("a_end_time", 0);
        if (j2 != 0 && j - j2 < AnalyticsConfig.kContinueSessionMillis) {
            MLog.e("onResume called before onPause");
            return false;
        } else if (j - j3 <= AnalyticsConfig.kContinueSessionMillis) {
            return false;
        } else {
            try {
                String string = sharedPreferences.getString("session_id", "-1");
                long j4 = sharedPreferences.getLong("session_end_time", 0);
                if (!"-1".equals(string)) {
                    if (j4 == 0) {
                        j4 = System.currentTimeMillis();
                    }
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(com.umeng.analytics.pro.c.e.a.g, j4);
                    g.a(d).a(string, jSONObject, com.umeng.analytics.pro.g.a.END);
                    if (AnalyticsConfig.FLAG_DPLUS) {
                        jSONObject = new JSONObject();
                        JSONObject i = b.a().i();
                        if (i.length() > 0) {
                            jSONObject.put(b.ab, i);
                        }
                        jSONObject.put(b.af, string);
                        jSONObject.put("__ii", string);
                        jSONObject.put(b.ag, j4);
                        JSONObject g = b.a().g(d);
                        if (g != null && g.length() > 0) {
                            Iterator keys = g.keys();
                            if (keys != null) {
                                while (keys.hasNext()) {
                                    try {
                                        string = keys.next().toString();
                                        if (!Arrays.asList(b.au).contains(string)) {
                                            jSONObject.put(string, g.get(string));
                                        }
                                    } catch (Exception e) {
                                    }
                                }
                            }
                        }
                        i.a(d).a(jSONObject, 3, false);
                    }
                }
            } catch (Throwable th) {
            }
            return true;
        }
    }

    private String a(Context context, SharedPreferences sharedPreferences, long j) {
        if (d == null && context != null) {
            d = context.getApplicationContext();
        }
        String b = b(d);
        try {
            d(context);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("__e", j);
            g.a(d).a(b, jSONObject, com.umeng.analytics.pro.g.a.BEGIN);
            a(d);
            Editor edit = sharedPreferences.edit();
            edit.putString("session_id", b);
            edit.putLong("session_start_time", j);
            edit.putLong("session_end_time", 0);
            edit.putLong("a_start_time", j);
            edit.putLong("a_end_time", 0);
            edit.putInt("versioncode", Integer.parseInt(UMUtils.getAppVersionCode(context)));
            edit.putString(b.al, UMUtils.getAppVersionName(context));
            edit.commit();
            Object jSONObject2 = new JSONObject();
            JSONObject i = b.a().i();
            if (i.length() > 0) {
                jSONObject2.put(b.ab, i);
            }
            jSONObject2.put(b.ad, b);
            jSONObject2.put("__ii", b);
            jSONObject2.put(b.ae, j);
            i.a(d).c(jSONObject2);
        } catch (Throwable th) {
        }
        return b;
    }

    private void d(Context context) {
        i.a(context).b(context);
        i.a(context).a();
    }

    public boolean a(Context context, long j) {
        boolean z = false;
        try {
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(context);
            if (sharedPreferences == null) {
                return false;
            }
            String string = sharedPreferences.getString("session_id", null);
            if (string == null) {
                return false;
            }
            long j2 = sharedPreferences.getLong("a_start_time", 0);
            long j3 = sharedPreferences.getLong("a_end_time", 0);
            if (j2 > 0 && j3 == 0) {
                b(d, Long.valueOf(j));
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(com.umeng.analytics.pro.c.e.a.g, j);
                    g.a(context).a(string, jSONObject, com.umeng.analytics.pro.g.a.END);
                    i.a(d).b();
                    if (AnalyticsConfig.FLAG_DPLUS) {
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject = b.a().c();
                        if (jSONObject == null || jSONObject.length() < 1) {
                            jSONObject = b.a().i();
                        }
                        if (jSONObject.length() > 0) {
                            jSONObject2.put(b.ab, jSONObject);
                        }
                        jSONObject2.put(b.af, string);
                        jSONObject2.put("__ii", string);
                        jSONObject2.put(b.ag, j);
                        jSONObject = b.a().g(d);
                        if (jSONObject != null && jSONObject.length() > 0) {
                            Iterator keys = jSONObject.keys();
                            if (keys != null) {
                                while (keys.hasNext()) {
                                    try {
                                        String obj = keys.next().toString();
                                        if (!Arrays.asList(b.au).contains(obj)) {
                                            jSONObject2.put(obj, jSONObject.get(obj));
                                        }
                                    } catch (Exception e) {
                                    }
                                }
                            }
                        }
                        i.a(d).a(jSONObject2, 3, true);
                    }
                    z = true;
                } catch (Throwable th) {
                    z = true;
                }
            }
            a(context);
            return z;
        } catch (Throwable th2) {
            return z;
        }
    }

    public void b(Context context, long j) {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(context);
        if (sharedPreferences != null) {
            c = b(context);
            try {
                Editor edit = sharedPreferences.edit();
                edit.putString("session_id", c);
                edit.putLong("session_start_time", j);
                edit.putLong("session_end_time", 0);
                edit.putLong("a_start_time", j);
                edit.putLong("a_end_time", 0);
                edit.putInt("versioncode", Integer.parseInt(UMUtils.getAppVersionCode(d)));
                edit.putString(b.al, UMUtils.getAppVersionName(d));
                edit.commit();
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("__e", j);
                g.a(d).a(c, jSONObject, com.umeng.analytics.pro.g.a.BEGIN);
                Object jSONObject2 = new JSONObject();
                JSONObject i = b.a().i();
                if (i.length() > 0) {
                    jSONObject2.put(b.ab, i);
                }
                jSONObject2.put(b.ad, c);
                jSONObject2.put("__ii", c);
                jSONObject2.put(b.ae, j);
                i.a(d).b(jSONObject2);
            } catch (Throwable th) {
            }
        }
    }

    public String c() {
        return c;
    }

    public String c(Context context) {
        try {
            if (c == null) {
                return PreferenceWrapper.getDefault(context).getString("session_id", null);
            }
        } catch (Throwable th) {
        }
        return c;
    }

    public String d() {
        return c(d);
    }
}
