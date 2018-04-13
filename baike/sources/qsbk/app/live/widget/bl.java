package qsbk.app.live.widget;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import qsbk.app.core.model.CustomButton;
import qsbk.app.core.net.NetworkCallback;

class bl extends NetworkCallback {
    final /* synthetic */ FollowTipsDialog a;

    bl(FollowTipsDialog followTipsDialog) {
        this.a = followTipsDialog;
    }

    public Map<String, String> getParams() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("query_source", Long.toString(this.a.c.getOrigin()));
        hashMap.put("query_source_id", Long.toString(this.a.c.getOriginId()));
        return hashMap;
    }

    public void onSuccess(JSONObject jSONObject) {
        this.a.c.origin_id = this.a.c.getOriginId();
        this.a.c.id = jSONObject.optLong("uid");
        this.a.c.name = jSONObject.optString("name");
        this.a.c.headurl = jSONObject.optString("avatar");
        this.a.c.level = jSONObject.optInt("level");
        JSONObject optJSONObject = jSONObject.optJSONObject("family_info");
        if (optJSONObject != null) {
            this.a.c.badge = optJSONObject.optString(CustomButton.POSITION_BOTTOM);
            this.a.c.family_level = optJSONObject.optInt("fl");
        }
        this.a.k();
    }

    public void onFinished() {
        this.a.h.setVisibility(8);
    }
}
