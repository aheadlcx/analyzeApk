package cn.xiaochuankeji.tieba.background.utils.e;

import com.tencent.tauth.AuthActivity;
import org.json.JSONException;
import org.json.JSONObject;

public class a extends b {
    public String a;
    public String b;
    public long c;
    public long d;

    protected JSONObject a() {
        JSONObject a = super.a();
        try {
            a.put(AuthActivity.ACTION_KEY, "play");
            a.put("otype", "urlreport");
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("owner", this.a);
            jSONObject.put("videouri", this.b);
            if (0 != this.h) {
                jSONObject.put("tid", this.h);
            }
            jSONObject.put("pid", this.c);
            jSONObject.put("prid", this.d);
            a.put("data", jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return a;
    }
}
