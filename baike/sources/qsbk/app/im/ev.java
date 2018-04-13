package qsbk.app.im;

import org.json.JSONObject;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.model.CampaignInfo;

class ev implements SimpleCallBack {
    final /* synthetic */ GroupConversationActivity a;

    ev(GroupConversationActivity groupConversationActivity) {
        this.a = groupConversationActivity;
    }

    public void onSuccess(JSONObject jSONObject) {
        this.a.aw = CampaignInfo.parse(jSONObject);
        if (this.a.aw == null || this.a.aw.hasCampaigned) {
            this.a.W();
        } else {
            this.a.V();
        }
    }

    public void onFailure(int i, String str) {
        this.a.W();
    }
}
