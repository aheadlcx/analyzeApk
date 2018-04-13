package com.umeng.commonsdk.proguard;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.umeng.commonsdk.internal.a;
import org.json.JSONObject;

public class e {
    public static final String a = "lng";
    public static final String b = "lat";
    public static final String c = "ts";
    private static Object d = new Object();

    public static void a(Context context) {
        com.umeng.commonsdk.statistics.common.e.a("UMSysLocationCache", "begin location");
        if (context != null) {
            try {
                new Thread(new bc(context)).start();
            } catch (Throwable e) {
                com.umeng.commonsdk.statistics.common.e.a("UMSysLocationCache", "e is " + e);
                b.a(context, e);
            }
        }
    }

    public static JSONObject b(Context context) {
        JSONObject jSONObject = null;
        synchronized (d) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(a.o, 0);
            if (sharedPreferences == null) {
            } else {
                try {
                    JSONObject jSONObject2;
                    Object string = sharedPreferences.getString(a.p, "");
                    if (TextUtils.isEmpty(string)) {
                        jSONObject2 = null;
                    } else {
                        jSONObject2 = new JSONObject(string);
                    }
                    jSONObject = jSONObject2;
                } catch (Throwable e) {
                    com.umeng.commonsdk.statistics.common.e.a("UMSysLocationCache", "e is " + e);
                    b.a(context, e);
                } catch (Throwable e2) {
                    com.umeng.commonsdk.statistics.common.e.a("UMSysLocationCache", "e is " + e2);
                    b.a(context, e2);
                }
                if (jSONObject != null) {
                    com.umeng.commonsdk.statistics.common.e.a("UMSysLocationCache", "json str is " + jSONObject.toString());
                }
            }
        }
        return jSONObject;
    }
}
