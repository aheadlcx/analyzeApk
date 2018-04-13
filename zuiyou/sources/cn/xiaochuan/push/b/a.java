package cn.xiaochuan.push.b;

import com.alibaba.fastjson.JSONObject;

public class a {
    public String a;
    public String b;

    public a(JSONObject jSONObject) {
        if (jSONObject != null) {
            JSONObject jSONObject2 = jSONObject.getJSONObject("alert");
            if (jSONObject2 != null) {
                this.a = jSONObject2.getString(com.umeng.analytics.a.z);
                this.b = jSONObject2.getString("title");
            }
        }
    }
}
