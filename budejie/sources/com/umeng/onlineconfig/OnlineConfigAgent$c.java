package com.umeng.onlineconfig;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.onlineconfig.proguard.d;
import com.umeng.onlineconfig.proguard.g;
import org.json.JSONObject;

class OnlineConfigAgent$c extends d {
    final /* synthetic */ OnlineConfigAgent a;
    private final String e;
    private JSONObject f;

    public OnlineConfigAgent$c(OnlineConfigAgent onlineConfigAgent, Context context) {
        this.a = onlineConfigAgent;
        super(null);
        this.e = "http://oc.umeng.com/v2/get_update_time";
        this.d = "http://oc.umeng.com/v2/get_update_time";
        this.f = a(context);
    }

    public JSONObject a() {
        return this.f;
    }

    private JSONObject a(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("appkey", TextUtils.isEmpty(OnlineConfigAgent.a(this.a)) ? g.a(context) : OnlineConfigAgent.a(this.a));
            jSONObject.put("version_code", g.b(context));
            return jSONObject;
        } catch (Exception e) {
            OnlineConfigLog.e(a.a, "exception in onlineConfigInternal");
            return null;
        }
    }

    public String b() {
        return this.d;
    }
}
