package qsbk.app.model;

import org.json.JSONException;
import org.json.JSONObject;

public class InviteInfo extends QbBean {
    private JSONObject a;
    public GroupInfo group;
    public String title;

    public InviteInfo(JSONObject jSONObject) {
        this.group = new GroupInfo(jSONObject.optJSONObject(Laisee.TYPE_TRIBE));
        this.a = jSONObject;
        this.title = jSONObject.optString("title");
    }

    public String toJSONString() {
        return this.a.toString();
    }

    public int getState() {
        return this.a.optInt("extra_state", -1);
    }

    public void setState(int i) {
        try {
            this.a.put("extra_state", i);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
