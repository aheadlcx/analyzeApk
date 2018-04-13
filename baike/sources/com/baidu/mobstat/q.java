package com.baidu.mobstat;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import org.json.JSONArray;
import org.json.JSONObject;

class q {
    static q a = new q();

    q() {
    }

    public void a(Context context, String str, String str2) {
        String a;
        PackageManager packageManager = context.getPackageManager();
        Object obj = "unkown";
        if (!"android.intent.action.PACKAGE_REMOVED".equals(str)) {
            try {
                obj = packageManager.getPackageInfo(str2, 8192).versionName;
            } catch (Throwable e) {
                bd.a(e);
            }
        }
        String str3 = "";
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("n", str2);
            jSONObject.put("a", str);
            jSONObject.put("v", obj);
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(jSONObject);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(System.currentTimeMillis());
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("app_change", jSONArray);
            jSONObject2.put("meta-data", stringBuilder.toString());
            a = cs.a(jSONObject2.toString().getBytes());
        } catch (Exception e2) {
            bd.b(e2.getMessage());
            a = str3;
        }
        if (!TextUtils.isEmpty(a)) {
            y.APP_CHANGE.a(System.currentTimeMillis(), a);
        }
    }
}
