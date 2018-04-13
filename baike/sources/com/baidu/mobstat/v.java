package com.baidu.mobstat;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Build.VERSION;
import com.tencent.connect.common.Constants;
import org.json.JSONArray;
import org.json.JSONObject;

public class v {
    public static JSONObject a(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("s", VERSION.SDK_INT);
            jSONObject.put("sv", VERSION.RELEASE);
            jSONObject.put(Config.CUID_SEC, de.a(2, context));
            jSONObject.put("w", de.b(context));
            jSONObject.put("h", de.c(context));
            jSONObject.put("ly", bc.c);
            jSONObject.put("pv", Constants.VIA_REPORT_TYPE_MAKE_FRIEND);
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                jSONObject.put(Config.PACKAGE_NAME, de.h(2, context));
                jSONObject.put("a", packageInfo.versionCode);
                jSONObject.put("n", packageInfo.versionName);
            } catch (Throwable e) {
                bd.a(e);
            }
            jSONObject.put("mc", de.b(2, context));
            jSONObject.put(Config.DEVICE_BLUETOOTH_MAC, de.f(2, context));
            jSONObject.put("m", Build.MODEL);
            jSONObject.put(Config.DEVICE_NAME, de.a(context, 2));
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(Config.TRACE_FAILED_CNT, 0);
            jSONObject2.put("send_index", 0);
            Object b = de.b();
            String str = Config.ROM;
            if (b == null) {
                b = "";
            }
            jSONObject2.put(str, b);
            jSONObject.put(Config.TRACE_PART, jSONObject2);
        } catch (Throwable e2) {
            bd.b(e2);
        }
        return jSONObject;
    }

    public static JSONObject a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        JSONObject jSONObject2;
        try {
            JSONArray jSONArray = (JSONArray) jSONObject.get("payload");
            if (jSONArray == null || jSONArray.length() <= 0) {
                jSONObject2 = null;
            } else {
                jSONObject2 = (JSONObject) jSONArray.get(0);
            }
            jSONObject2 = jSONObject2 != null ? jSONObject2.getJSONObject(Config.HEADER_PART) : null;
        } catch (Exception e) {
            jSONObject2 = null;
        }
        return jSONObject2;
    }

    public static void b(JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject(Config.TRACE_PART);
            jSONObject2.put(Config.TRACE_FAILED_CNT, jSONObject2.getLong(Config.TRACE_FAILED_CNT) + 1);
        } catch (Exception e) {
        }
    }

    public static void c(JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject(Config.TRACE_PART);
            jSONObject2.put("send_index", jSONObject2.getLong("send_index") + 1);
        } catch (Exception e) {
        }
    }
}
