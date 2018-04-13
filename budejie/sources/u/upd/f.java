package u.upd;

import org.json.JSONObject;

public class f extends h {
    public a a;

    public enum a {
        a,
        FAIL
    }

    public f(JSONObject jSONObject) {
        super(jSONObject);
        if ("ok".equalsIgnoreCase(jSONObject.optString("status")) || "ok".equalsIgnoreCase(jSONObject.optString("success"))) {
            this.a = a.a;
        } else {
            this.a = a.FAIL;
        }
    }
}
