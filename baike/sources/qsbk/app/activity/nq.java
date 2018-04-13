package qsbk.app.activity;

import org.json.JSONObject;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.model.CampaignInfo;
import qsbk.app.utils.ToastAndDialog;

class nq implements SimpleCallBack {
    final /* synthetic */ int a;
    final /* synthetic */ GroupNoticeActivity b;

    nq(GroupNoticeActivity groupNoticeActivity, int i) {
        this.b = groupNoticeActivity;
        this.a = i;
    }

    public void onSuccess(JSONObject jSONObject) {
        this.b.c = CampaignInfo.parse(jSONObject);
        if (this.b.c == null) {
            return;
        }
        if (((long) this.b.c.endTime) * 1000 > System.currentTimeMillis()) {
            RunForOwnerActivity.launch(this.b, this.a, this.b.c);
        } else {
            ToastAndDialog.makeNegativeToast(this.b, "群大竞选已经结束").show();
        }
    }

    public void onFailure(int i, String str) {
    }
}
