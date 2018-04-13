package qsbk.app.im;

import org.json.JSONObject;

public class OfficialSubscribeListItem {
    public boolean canCancel;
    public String icon;
    public String id;
    public boolean isSubscribe;
    public String name;

    public OfficialSubscribeListItem(JSONObject jSONObject) {
        boolean z;
        boolean z2 = true;
        this.id = jSONObject.optString("pid");
        this.name = jSONObject.optString("pname");
        this.icon = jSONObject.optString("pcover");
        if (jSONObject.optInt("subscribe") == 1) {
            z = true;
        } else {
            z = false;
        }
        this.isSubscribe = z;
        if (jSONObject.optInt("cancel") != 1) {
            z2 = false;
        }
        this.canCancel = z2;
    }
}
