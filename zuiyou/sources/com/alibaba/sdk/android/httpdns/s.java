package com.alibaba.sdk.android.httpdns;

import android.support.v4.app.NotificationCompat;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class s {
    private boolean enabled = true;
    private String[] f;

    s(String str) {
        boolean z = true;
        try {
            JSONObject jSONObject = new JSONObject(str);
            f.d("Schedule center response:" + jSONObject.toString());
            if (jSONObject.has(NotificationCompat.CATEGORY_SERVICE)) {
                if (!jSONObject.getString(NotificationCompat.CATEGORY_SERVICE).equals("true")) {
                    z = false;
                }
                this.enabled = z;
            }
            if (jSONObject.has("service_ip")) {
                JSONArray jSONArray = jSONObject.getJSONArray("service_ip");
                this.f = new String[jSONArray.length()];
                for (int i = 0; i < jSONArray.length(); i++) {
                    this.f[i] = (String) jSONArray.get(i);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String[] c() {
        return this.f;
    }

    public boolean isEnabled() {
        return this.enabled;
    }
}
