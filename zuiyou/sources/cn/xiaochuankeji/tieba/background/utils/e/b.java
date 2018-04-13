package cn.xiaochuankeji.tieba.background.utils.e;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class b {
    public long e;
    public long f;
    public String g;
    public long h;

    protected JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", this.e);
            jSONObject.put("oid", this.f);
            jSONObject.put("src", this.g);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
