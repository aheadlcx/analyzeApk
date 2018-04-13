package com.crashlytics.android.internal;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

final class bh {
    bh() {
    }

    public final byte[] a(bf bfVar) throws IOException {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("appBundleId", bfVar.a);
            jSONObject.put("executionId", bfVar.b);
            jSONObject.put("installationId", bfVar.c);
            jSONObject.put("androidId", bfVar.d);
            jSONObject.put("osVersion", bfVar.e);
            jSONObject.put("deviceModel", bfVar.f);
            jSONObject.put("appVersionCode", bfVar.g);
            jSONObject.put("appVersionName", bfVar.h);
            jSONObject.put("timestamp", bfVar.i);
            jSONObject.put("type", bfVar.j.toString());
            jSONObject.put("details", a(bfVar.k));
            return jSONObject.toString().getBytes("UTF-8");
        } catch (JSONException e) {
            throw new IOException(e.getMessage());
        }
    }

    private static JSONObject a(Map<String, String> map) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (Entry entry : map.entrySet()) {
            jSONObject.put((String) entry.getKey(), entry.getValue());
        }
        return jSONObject;
    }
}
