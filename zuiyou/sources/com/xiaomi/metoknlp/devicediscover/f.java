package com.xiaomi.metoknlp.devicediscover;

import com.meizu.cloud.pushsdk.notification.model.AdvanceSetting;
import com.meizu.cloud.pushsdk.pushtracer.constant.Parameters;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class f {
    private final String a;
    private final String b;
    private final String c;
    private final String d;
    private final double e;
    private final double f;
    private final String g;
    private final String h;
    private final long i;
    private final long j;
    private final String k;
    private final String l;
    private final List m;

    private f(d dVar) {
        this.a = dVar.a;
        this.b = dVar.b;
        this.c = dVar.c;
        this.d = dVar.d;
        this.e = dVar.e;
        this.f = dVar.f;
        this.g = dVar.g;
        this.h = dVar.h;
        this.i = dVar.i;
        this.j = dVar.j;
        this.k = dVar.k;
        this.l = dVar.l;
        this.m = dVar.m;
    }

    private void a(JSONObject jSONObject, String str, Object obj) {
        if (jSONObject != null && obj != null) {
            if (!(obj instanceof String)) {
                try {
                    jSONObject.put(str, obj);
                } catch (JSONException e) {
                }
            } else if (!((String) obj).isEmpty()) {
                try {
                    jSONObject.put(str, obj);
                } catch (JSONException e2) {
                }
            }
        }
    }

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        a(jSONObject, "m", this.a);
        a(jSONObject, "i", this.b);
        a(jSONObject, "a", this.c);
        a(jSONObject, "o", this.d);
        a(jSONObject, "lg", Double.valueOf(this.e));
        a(jSONObject, Parameters.LONGITUDE, Double.valueOf(this.f));
        a(jSONObject, "am", this.g);
        a(jSONObject, AdvanceSetting.ADVANCE_SETTING, this.h);
        a(jSONObject, "ast", Long.valueOf(this.i));
        a(jSONObject, "ad", Long.valueOf(this.j));
        a(jSONObject, "ds", this.k);
        a(jSONObject, Parameters.DEVICE_MODEL, this.l);
        JSONArray jSONArray = new JSONArray();
        for (String put : this.m) {
            jSONArray.put(put);
        }
        a(jSONObject, "devices", jSONArray);
        return jSONObject;
    }
}
