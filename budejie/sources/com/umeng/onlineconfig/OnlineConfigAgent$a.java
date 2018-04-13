package com.umeng.onlineconfig;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.onlineconfig.proguard.d;
import com.umeng.onlineconfig.proguard.g;
import com.umeng.onlineconfig.proguard.h;
import org.json.JSONObject;

class OnlineConfigAgent$a extends d {
    final /* synthetic */ OnlineConfigAgent a;
    private final String e;
    private JSONObject f;

    public OnlineConfigAgent$a(OnlineConfigAgent onlineConfigAgent, Context context) {
        this.a = onlineConfigAgent;
        super(null);
        this.e = "http://oc.umeng.com/v2/check_config_update";
        this.d = "http://oc.umeng.com/v2/check_config_update";
        this.f = a(context);
    }

    public JSONObject a() {
        return this.f;
    }

    public String b() {
        return this.d;
    }

    private JSONObject a(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", "online_config");
            jSONObject.put("appkey", TextUtils.isEmpty(OnlineConfigAgent.a(this.a)) ? g.a(context) : OnlineConfigAgent.a(this.a));
            jSONObject.put("version_code", g.b(context));
            jSONObject.put("package", g.f(context));
            jSONObject.put("sdk_version", g.a());
            jSONObject.put("idmd5", h.b(g.d(context)));
            jSONObject.put("channel", TextUtils.isEmpty(OnlineConfigAgent.b(this.a)) ? g.c(context) : OnlineConfigAgent.b(this.a));
            jSONObject.put("last_config_time", OnlineConfigAgent.a(this.a, context));
            return jSONObject;
        } catch (Exception e) {
            OnlineConfigLog.e(a.a, "exception in onlineConfigInternal");
            return null;
        }
    }
}
