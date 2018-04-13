package cn.xiaochuankeji.tieba.background.utils.a;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.meizu.cloud.pushsdk.notification.model.TimeDisplaySetting;
import com.tencent.tauth.AuthActivity;

public class f {
    public long a;
    public long b;
    public long c;
    public int d;
    public String e;
    public String f;
    public String g;

    protected JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(AuthActivity.ACTION_KEY, this.g);
            jSONObject.put("otype", "post");
            jSONObject.put("src", this.f);
            jSONObject.put("id", Long.valueOf(this.a));
            jSONObject.put("oid", Long.valueOf(0));
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("pid", Long.valueOf(this.a));
            jSONObject2.put(TimeDisplaySetting.START_SHOW_TIME, Long.valueOf(this.b));
            jSONObject2.put("et", Long.valueOf(this.c));
            jSONObject2.put("dur", Long.valueOf(this.c - this.b));
            jSONObject.put("data", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
