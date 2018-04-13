package com.xiaomi.push.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.android.e;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.channel.commonutils.string.d;
import com.xiaomi.mipush.sdk.Constants;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONObject;

public class o {
    private static n a;
    private static a b;

    public interface a {
        void a();
    }

    public static synchronized n a(Context context) {
        n nVar = null;
        synchronized (o.class) {
            if (a != null) {
                nVar = a;
            } else {
                SharedPreferences sharedPreferences = context.getSharedPreferences("mipush_account", 0);
                Object string = sharedPreferences.getString("uuid", null);
                Object string2 = sharedPreferences.getString("token", null);
                Object string3 = sharedPreferences.getString("security", null);
                String string4 = sharedPreferences.getString("app_id", null);
                String string5 = sharedPreferences.getString("app_token", null);
                String string6 = sharedPreferences.getString("package_name", null);
                Object string7 = sharedPreferences.getString("device_id", null);
                int i = sharedPreferences.getInt("env_type", 1);
                if (!TextUtils.isEmpty(string7) && string7.startsWith("a-")) {
                    string7 = e.e(context);
                    sharedPreferences.edit().putString("device_id", string7).commit();
                }
                if (!(TextUtils.isEmpty(string) || TextUtils.isEmpty(string2) || TextUtils.isEmpty(string3))) {
                    CharSequence e = e.e(context);
                    if ("com.xiaomi.xmsf".equals(context.getPackageName()) || TextUtils.isEmpty(e) || TextUtils.isEmpty(r8) || r8.equals(e)) {
                        a = new n(string, string2, string3, string4, string5, string6, i);
                        nVar = a;
                    } else {
                        b.d("erase the old account.");
                        b(context);
                    }
                }
            }
        }
        return nVar;
    }

    public static synchronized n a(Context context, String str, String str2, String str3) {
        n nVar = null;
        synchronized (o.class) {
            PackageInfo packageInfo;
            Map treeMap = new TreeMap();
            treeMap.put("devid", e.a(context));
            String str4 = c(context) ? "1000271" : str2;
            String str5 = c(context) ? "420100086271" : str3;
            String str6 = c(context) ? "com.xiaomi.xmsf" : str;
            treeMap.put("appid", str4);
            treeMap.put("apptoken", str5);
            try {
                packageInfo = context.getPackageManager().getPackageInfo(str6, 16384);
            } catch (Throwable e) {
                b.a(e);
                packageInfo = null;
            }
            treeMap.put("appversion", packageInfo != null ? String.valueOf(packageInfo.versionCode) : "0");
            treeMap.put("sdkversion", Integer.toString(26));
            treeMap.put("packagename", str6);
            treeMap.put("model", Build.MODEL);
            treeMap.put(Constants.EXTRA_KEY_IMEI_MD5, d.a(e.c(context)));
            treeMap.put("os", VERSION.RELEASE + Constants.ACCEPT_TIME_SEPARATOR_SERVER + VERSION.INCREMENTAL);
            int b = e.b();
            if (b >= 0) {
                treeMap.put("space_id", Integer.toString(b));
            }
            CharSequence a = d.a(e.g(context));
            if (!TextUtils.isEmpty(a)) {
                treeMap.put("mac_address", a);
            }
            treeMap.put("android_id", e.b(context));
            com.xiaomi.channel.commonutils.network.b a2 = com.xiaomi.channel.commonutils.network.d.a(context, a(), treeMap);
            String str7 = "";
            if (a2 != null) {
                str7 = a2.a();
            }
            if (!TextUtils.isEmpty(str7)) {
                JSONObject jSONObject = new JSONObject(str7);
                if (jSONObject.getInt("code") == 0) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("data");
                    nVar = new n(jSONObject2.getString("userId") + "@xiaomi.com/an" + d.a(6), jSONObject2.getString("token"), jSONObject2.getString("ssecurity"), str4, str5, str6, com.xiaomi.channel.commonutils.misc.a.c());
                    a(context, nVar);
                    a = nVar;
                } else {
                    r.a(context, jSONObject.getInt("code"), jSONObject.optString("description"));
                    b.a(str7);
                }
            }
        }
        return nVar;
    }

    public static String a() {
        if (com.xiaomi.channel.commonutils.misc.a.b()) {
            return "http://" + com.xiaomi.smack.b.c + ":9085/pass/register";
        }
        return "https://" + (com.xiaomi.channel.commonutils.misc.a.a() ? "sandbox.xmpush.xiaomi.com" : "register.xmpush.xiaomi.com") + "/pass/register";
    }

    public static void a(Context context, n nVar) {
        Editor edit = context.getSharedPreferences("mipush_account", 0).edit();
        edit.putString("uuid", nVar.a);
        edit.putString("security", nVar.c);
        edit.putString("token", nVar.b);
        edit.putString("app_id", nVar.d);
        edit.putString("package_name", nVar.f);
        edit.putString("app_token", nVar.e);
        edit.putString("device_id", e.e(context));
        edit.putInt("env_type", nVar.g);
        edit.commit();
        b();
    }

    public static void b() {
        if (b != null) {
            b.a();
        }
    }

    public static void b(Context context) {
        context.getSharedPreferences("mipush_account", 0).edit().clear().commit();
        a = null;
        b();
    }

    private static boolean c(Context context) {
        return context.getPackageName().equals("com.xiaomi.xmsf");
    }
}
