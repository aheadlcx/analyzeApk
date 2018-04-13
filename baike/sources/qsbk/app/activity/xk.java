package qsbk.app.activity;

import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.HttpCallBack;
import qsbk.app.utils.ToastAndDialog;

class xk implements HttpCallBack {
    final /* synthetic */ NewFansActivity a;

    xk(NewFansActivity newFansActivity) {
        this.a = newFansActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        if (!NewFansActivity.s(this.a)) {
            ToastAndDialog.makePositiveToast(QsbkApp.mContext, "已归档至 我的糗友 -> 粉丝", Integer.valueOf(0)).show();
        }
    }

    public void onFailure(String str, String str2) {
    }
}
