package qsbk.app.im;

import org.json.JSONObject;
import qsbk.app.http.SimpleCallBack;

class et implements SimpleCallBack {
    final /* synthetic */ GroupConversationActivity a;

    et(GroupConversationActivity groupConversationActivity) {
        this.a = groupConversationActivity;
    }

    public void onSuccess(JSONObject jSONObject) {
        boolean optBoolean = jSONObject.optBoolean("can_vote");
        this.a.ax = jSONObject.toString();
        if (optBoolean) {
            this.a.V();
        } else {
            this.a.W();
        }
    }

    public void onFailure(int i, String str) {
        this.a.W();
    }
}
