package com.umeng.onlineconfig;

import com.umeng.onlineconfig.proguard.e;
import java.util.Locale;
import org.json.JSONObject;

public class c extends e {
    public JSONObject a = null;
    boolean b = false;
    int c = -1;
    int d = -1;
    private final String e = "config_update";
    private final String f = "report_policy";
    private final String g = "online_params";
    private final String h = "report_interval";

    public c(JSONObject jSONObject) {
        super(jSONObject);
        if (jSONObject != null) {
            a(jSONObject);
            a();
        }
    }

    private void a(JSONObject jSONObject) {
        try {
            if (jSONObject.has("config_update") && !jSONObject.getString("config_update").toLowerCase(Locale.US).equals("no")) {
                if (jSONObject.has("report_policy")) {
                    this.c = jSONObject.getInt("report_policy");
                    this.d = jSONObject.optInt("report_interval") * 1000;
                } else {
                    OnlineConfigLog.w(a.a, " online config fetch no report policy");
                }
                this.a = jSONObject.optJSONObject("online_params");
                this.b = true;
            }
        } catch (Exception e) {
            OnlineConfigLog.w(a.a, "fail to parce online config response", e);
        }
    }

    private void a() {
        if (!b.a(this.c)) {
            this.c = 1;
        }
    }
}
