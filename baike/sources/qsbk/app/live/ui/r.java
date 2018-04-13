package qsbk.app.live.ui;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import qsbk.app.core.model.User;
import qsbk.app.core.net.NetworkCallback;
import qsbk.app.live.R;

class r extends NetworkCallback {
    final /* synthetic */ User a;
    final /* synthetic */ boolean b;
    final /* synthetic */ LiveBaseActivity c;

    r(LiveBaseActivity liveBaseActivity, User user, boolean z) {
        this.c = liveBaseActivity;
        this.a = user;
        this.b = z;
    }

    public Map<String, String> getParams() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("f_source", this.a.getOrigin() + "");
        hashMap.put("f_uid", this.a.getOriginId() + "");
        return hashMap;
    }

    public void onSuccess(JSONObject jSONObject) {
        if (this.b) {
            this.c.a(R.string.live_follow_success);
        }
        this.c.onFollowAnchorSuccess(this.a);
    }

    public void onFailed(int i, String str) {
        this.c.v.setVisibility(0);
        this.c.f(str);
    }
}
