package qsbk.app.activity;

import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.utils.ToastAndDialog;

class vn implements SimpleCallBack {
    final /* synthetic */ MyInfoActivity a;

    vn(MyInfoActivity myInfoActivity) {
        this.a = myInfoActivity;
    }

    public void onSuccess(JSONObject jSONObject) {
        this.a.ca.dismiss();
        ToastAndDialog.makePositiveToast(QsbkApp.mContext, "封禁成功!", Integer.valueOf(0)).show();
    }

    public void onFailure(int i, String str) {
        this.a.ca.dismiss();
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
    }
}
