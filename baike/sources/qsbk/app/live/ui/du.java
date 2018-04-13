package qsbk.app.live.ui;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import qsbk.app.core.net.Callback;

class du extends Callback {
    final /* synthetic */ LivePullActivity a;

    du(LivePullActivity livePullActivity) {
        this.a = livePullActivity;
    }

    public Map<String, String> getParams() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("query_source", Long.toString(this.a.d.author.getOrigin()));
        hashMap.put("query_source_id", Long.toString(this.a.d.author.getOriginId()));
        return hashMap;
    }

    public void onSuccess(JSONObject jSONObject) {
        this.a.d.author.is_follow = jSONObject.optBoolean("is_follow");
        if (this.a.d.author.isFollow()) {
            this.a.v.setVisibility(8);
            if (this.a.at != null && this.a.at.isShowing()) {
                this.a.at.dismiss();
            }
            if (this.a.bQ != null) {
                this.a.bQ.hideFollowBtn();
                this.a.bQ.showFollowedBtn();
            }
        }
    }
}
