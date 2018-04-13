package qsbk.app.activity;

import org.json.JSONObject;
import qsbk.app.http.HttpCallBack;
import qsbk.app.model.GroupInfo;
import qsbk.app.utils.GroupMsgUtils;

class lx implements HttpCallBack {
    final /* synthetic */ boolean a;
    final /* synthetic */ GroupInfoActivity b;

    lx(GroupInfoActivity groupInfoActivity, boolean z) {
        this.b = groupInfoActivity;
        this.a = z;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        GroupMsgUtils.setOpenable(String.valueOf(this.b.G), this.a);
        JSONObject optJSONObject = jSONObject.optJSONObject("tribe_detail");
        if (optJSONObject != null) {
            this.b.b = new GroupInfo(optJSONObject);
            this.b.m();
        }
        this.b.hideLoading();
    }

    public void onFailure(String str, String str2) {
    }
}
