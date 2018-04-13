package qsbk.app.live.ui;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import qsbk.app.core.net.NetworkCallback;

class ep extends NetworkCallback {
    final /* synthetic */ LivePushActivity a;

    ep(LivePushActivity livePushActivity) {
        this.a = livePushActivity;
    }

    public Map<String, String> getParams() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("stream_id", this.a.aA);
        return hashMap;
    }

    public void onSuccess(JSONObject jSONObject) {
        this.a.aA = null;
    }
}
