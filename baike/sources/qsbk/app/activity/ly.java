package qsbk.app.activity;

import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.HttpCallBack;
import qsbk.app.model.GroupInfo;
import qsbk.app.utils.ToastAndDialog;

class ly implements HttpCallBack {
    final /* synthetic */ GroupInfoActivity a;

    ly(GroupInfoActivity groupInfoActivity) {
        this.a = groupInfoActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("tribe_detail");
        if (optJSONObject != null) {
            this.a.b = new GroupInfo(optJSONObject);
            this.a.supportInvalidateOptionsMenu();
            this.a.m();
        }
        this.a.hideLoading();
    }

    public void onFailure(String str, String str2) {
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str2, Integer.valueOf(0)).show();
        this.a.hideLoading();
    }
}
