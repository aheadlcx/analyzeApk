package com.izuiyou.a.a.a;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.support.annotation.WorkerThread;
import android.support.v4.app.NotificationCompat;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class a {
    @WorkerThread
    public static JSONObject a(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096);
            String[] strArr = packageInfo.requestedPermissions;
            int[] iArr = packageInfo.requestedPermissionsFlags;
            if (iArr.length != strArr.length) {
                jSONObject.put(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(-2));
                jSONObject.put("msg", (Object) "error length");
            } else {
                Object jSONArray = new JSONArray();
                for (int i = 0; i < strArr.length; i++) {
                    int i2;
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("name", strArr[i]);
                    String str = "enable";
                    if ((iArr[i] & 2) != 0) {
                        i2 = 1;
                    } else {
                        i2 = 0;
                    }
                    jSONObject2.put(str, Integer.valueOf(i2));
                    jSONArray.add(jSONObject2);
                }
                jSONObject.put(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(1));
                jSONObject.put("data", jSONArray);
            }
        } catch (Exception e) {
            jSONObject.put(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(-1));
            jSONObject.put("msg", e.getMessage());
        }
        return jSONObject;
    }
}
