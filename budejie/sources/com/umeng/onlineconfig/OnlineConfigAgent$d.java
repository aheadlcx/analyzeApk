package com.umeng.onlineconfig;

import com.umeng.onlineconfig.proguard.e;
import org.json.JSONObject;

public class OnlineConfigAgent$d extends e {
    public long a = -1;
    public long b = -1;

    public OnlineConfigAgent$d(JSONObject jSONObject) {
        super(jSONObject);
        a(jSONObject);
    }

    private void a(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                this.a = jSONObject.optLong("last_config_time", -1);
                this.b = (jSONObject.optLong("oc_interval", -1) * 60) * 1000;
            } catch (Exception e) {
                OnlineConfigLog.w(a.a, "fail to parce online config response", e);
            }
        }
    }
}
