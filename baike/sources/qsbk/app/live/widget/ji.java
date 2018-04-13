package qsbk.app.live.widget;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import qsbk.app.core.model.CustomButton;
import qsbk.app.core.model.User;
import qsbk.app.core.net.NetworkCallback;

class ji extends NetworkCallback {
    final /* synthetic */ User a;
    final /* synthetic */ UserCardDialog b;

    ji(UserCardDialog userCardDialog, User user) {
        this.b = userCardDialog;
        this.a = user;
    }

    public Map<String, String> getParams() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("query_source", Long.toString(this.a.getOrigin()));
        hashMap.put("query_source_id", Long.toString(this.a.getOriginId()));
        hashMap.put("rid", Long.toString(this.b.y));
        return hashMap;
    }

    public void onSuccess(JSONObject jSONObject) {
        User user = new User();
        user.id = jSONObject.optLong("uid");
        user.name = jSONObject.optString("name");
        user.headurl = jSONObject.optString("avatar");
        user.is_follow = jSONObject.optBoolean("is_follow");
        user.followed_count = jSONObject.optInt("followed_count");
        user.intro = jSONObject.optString("intro");
        user.level = jSONObject.optInt("level");
        JSONObject optJSONObject = jSONObject.optJSONObject("family_info");
        if (optJSONObject != null) {
            user.badge = optJSONObject.optString(CustomButton.POSITION_BOTTOM);
            user.family_level = optJSONObject.optInt("fl");
        }
        user.origin_id = this.a.getOriginId();
        user.origin = this.a.getOrigin();
        user.is_admin = jSONObject.optInt("m");
        user.nick_id = jSONObject.optLong("nick_id");
        this.b.a(user, jSONObject.optInt(CustomButton.POSITION_BOTTOM));
        if (this.b.isAnchor(this.b.x.getOriginId(), this.b.x.getOrigin())) {
            this.b.v.level = user.level;
        }
    }

    public void onFinished() {
        this.b.s.setVisibility(8);
    }

    public void onFailed(int i, String str) {
    }
}
