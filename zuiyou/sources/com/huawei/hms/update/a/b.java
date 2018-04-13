package com.huawei.hms.update.a;

import android.support.v4.app.NotificationCompat;
import com.huawei.hms.support.log.a;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class b {
    private final String a;
    private String b = "";
    private String c = "";

    public b(String str) {
        this.a = str;
    }

    public String a() {
        if (!"0".equals(this.b) || this.c == null || this.c.isEmpty()) {
            return null;
        }
        return b(this.c);
    }

    public String toString() {
        try {
            return new JSONObject(this.a).toString(2);
        } catch (JSONException e) {
            return this.a;
        }
    }

    public static b a(String str) {
        b bVar = new b(str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            bVar.b = jSONObject.getString(NotificationCompat.CATEGORY_STATUS);
            if (!"0".equals(bVar.b)) {
                return bVar;
            }
            JSONArray jSONArray = jSONObject.getJSONArray("components");
            if (0 >= jSONArray.length()) {
                return bVar;
            }
            bVar.c = jSONArray.getJSONObject(0).getString("url");
            return bVar;
        } catch (JSONException e) {
            a.d("CheckResponse", "In parseResponse, Failed to parse json for check-update response." + e.getMessage());
            return new b(str);
        }
    }

    private static String b(String str) {
        int length = str.length();
        int i = -1;
        while (length > 0 && str.charAt(length - 1) == '/') {
            i = length;
            length--;
        }
        if (i == -1) {
            return str + "/";
        }
        return str.substring(0, i);
    }
}
