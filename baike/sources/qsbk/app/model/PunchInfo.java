package qsbk.app.model;

import org.json.JSONException;
import org.json.JSONObject;

public class PunchInfo {
    public String amount;
    public boolean canPunch;
    public String content;
    public boolean isJoin;

    public void fromJson(String str) {
        try {
            fromJson(new JSONObject(str));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void fromJson(JSONObject jSONObject) {
        boolean z;
        boolean z2 = true;
        this.amount = jSONObject.optString("total");
        if (jSONObject.optInt("is_punch") == 1) {
            z = true;
        } else {
            z = false;
        }
        this.isJoin = z;
        if (jSONObject.optInt("can_punch") != 1) {
            z2 = false;
        }
        this.canPunch = z2;
        this.content = jSONObject.optString("content", "");
    }
}
