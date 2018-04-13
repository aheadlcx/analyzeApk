package qsbk.app.live.ui;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import qsbk.app.core.net.NetworkCallback;

class bf extends NetworkCallback {
    final /* synthetic */ String a;
    final /* synthetic */ LiveBaseActivity b;

    bf(LiveBaseActivity liveBaseActivity, String str) {
        this.b = liveBaseActivity;
        this.a = str;
    }

    public Map<String, String> getParams() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("ids", this.a);
        return hashMap;
    }

    public void onSuccess(JSONObject jSONObject) {
        JSONArray optJSONArray = jSONObject.optJSONArray("a");
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (!(optJSONObject == null || this.b.aV.isEmpty())) {
                    LiveBaseActivity.v(this.b).put(optJSONObject.opt("i") + "_" + optJSONObject.opt("s"), ((String) this.b.aV.get(optJSONObject.optString("t"))).replace("$", optJSONObject.optString("a")));
                }
            }
            LiveBaseActivity.u(this.b);
        }
    }
}
