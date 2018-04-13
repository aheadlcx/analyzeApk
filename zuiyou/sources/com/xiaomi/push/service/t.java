package com.xiaomi.push.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.meizu.cloud.pushsdk.platform.pushstrategy.Strategy;
import com.meizu.cloud.pushsdk.pushtracer.constant.Parameters;
import com.umeng.analytics.b.g;
import com.xiaomi.channel.commonutils.android.d;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.service.module.PushChannelRegion;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONObject;

public class t {
    private static s a;
    private static a b;

    public interface a {
        void a();
    }

    public static synchronized s a(Context context) {
        s sVar = null;
        synchronized (t.class) {
            if (a != null) {
                sVar = a;
            } else {
                SharedPreferences sharedPreferences = context.getSharedPreferences("mipush_account", 0);
                Object string = sharedPreferences.getString("uuid", null);
                Object string2 = sharedPreferences.getString("token", null);
                Object string3 = sharedPreferences.getString("security", null);
                String string4 = sharedPreferences.getString(Strategy.APP_ID, null);
                String string5 = sharedPreferences.getString("app_token", null);
                String string6 = sharedPreferences.getString(g.e, null);
                Object string7 = sharedPreferences.getString("device_id", null);
                int i = sharedPreferences.getInt("env_type", 1);
                if (!TextUtils.isEmpty(string7) && string7.startsWith("a-")) {
                    string7 = d.i(context);
                    sharedPreferences.edit().putString("device_id", string7).commit();
                }
                if (!(TextUtils.isEmpty(string) || TextUtils.isEmpty(string2) || TextUtils.isEmpty(string3))) {
                    CharSequence i2 = d.i(context);
                    if ("com.xiaomi.xmsf".equals(context.getPackageName()) || TextUtils.isEmpty(i2) || TextUtils.isEmpty(r8) || r8.equals(i2)) {
                        a = new s(string, string2, string3, string4, string5, string6, i);
                        sVar = a;
                    } else {
                        b.d("erase the old account.");
                        c(context);
                    }
                }
            }
        }
        return sVar;
    }

    public static synchronized s a(Context context, String str, String str2, String str3) {
        s sVar = null;
        synchronized (t.class) {
            PackageInfo packageInfo;
            Map treeMap = new TreeMap();
            treeMap.put("devid", d.a(context));
            String str4 = d(context) ? "1000271" : str2;
            String str5 = d(context) ? "420100086271" : str3;
            String str6 = d(context) ? "com.xiaomi.xmsf" : str;
            treeMap.put("appid", str4);
            treeMap.put("apptoken", str5);
            try {
                packageInfo = context.getPackageManager().getPackageInfo(str6, 16384);
            } catch (Throwable e) {
                b.a(e);
                packageInfo = null;
            }
            treeMap.put("appversion", packageInfo != null ? String.valueOf(packageInfo.versionCode) : "0");
            treeMap.put("sdkversion", Integer.toString(31));
            treeMap.put("packagename", str6);
            treeMap.put("model", Build.MODEL);
            Object a = com.xiaomi.channel.commonutils.string.d.a(d.c(context));
            Object e2 = d.e(context);
            if (!TextUtils.isEmpty(e2)) {
                a = a + "," + e2;
            }
            treeMap.put("imei_md5", a);
            treeMap.put(g.p, VERSION.RELEASE + "-" + VERSION.INCREMENTAL);
            int b = d.b();
            if (b >= 0) {
                treeMap.put("space_id", Integer.toString(b));
            }
            CharSequence a2 = com.xiaomi.channel.commonutils.string.d.a(d.k(context));
            if (!TextUtils.isEmpty(a2)) {
                treeMap.put("mac_address", a2);
            }
            treeMap.put("android_id", d.b(context));
            com.xiaomi.channel.commonutils.network.b a3 = com.xiaomi.channel.commonutils.network.d.a(context, b(context), treeMap);
            String str7 = "";
            if (a3 != null) {
                str7 = a3.a();
            }
            if (!TextUtils.isEmpty(str7)) {
                JSONObject jSONObject = new JSONObject(str7);
                if (jSONObject.getInt("code") == 0) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("data");
                    sVar = new s(jSONObject2.getString(Parameters.SESSION_USER_ID) + "@xiaomi.com/an" + com.xiaomi.channel.commonutils.string.d.a(6), jSONObject2.getString("token"), jSONObject2.getString("ssecurity"), str4, str5, str6, com.xiaomi.channel.commonutils.misc.a.c());
                    a(context, sVar);
                    a = sVar;
                } else {
                    w.a(context, jSONObject.getInt("code"), jSONObject.optString("description"));
                    b.a(str7);
                }
            }
        }
        return sVar;
    }

    public static void a() {
        if (b != null) {
            b.a();
        }
    }

    public static void a(Context context, s sVar) {
        Editor edit = context.getSharedPreferences("mipush_account", 0).edit();
        edit.putString("uuid", sVar.a);
        edit.putString("security", sVar.c);
        edit.putString("token", sVar.b);
        edit.putString(Strategy.APP_ID, sVar.d);
        edit.putString(g.e, sVar.f);
        edit.putString("app_token", sVar.e);
        edit.putString("device_id", d.i(context));
        edit.putInt("env_type", sVar.g);
        edit.commit();
        a();
    }

    public static String b(Context context) {
        if (com.xiaomi.channel.commonutils.misc.a.b()) {
            return "http://" + com.xiaomi.smack.b.c + ":9085/pass/register";
        }
        if (PushChannelRegion.Global.name().equals(a.a(context).a())) {
            return "https://register.xmpush.global.xiaomi.com/pass/register";
        }
        return "https://" + (com.xiaomi.channel.commonutils.misc.a.a() ? "sandbox.xmpush.xiaomi.com" : "register.xmpush.xiaomi.com") + "/pass/register";
    }

    public static void c(Context context) {
        context.getSharedPreferences("mipush_account", 0).edit().clear().commit();
        a = null;
        a();
    }

    private static boolean d(Context context) {
        return context.getPackageName().equals("com.xiaomi.xmsf");
    }
}
