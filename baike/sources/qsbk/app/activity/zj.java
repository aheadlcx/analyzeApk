package qsbk.app.activity;

import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.HttpCallBack;
import qsbk.app.utils.ToastAndDialog;

class zj implements HttpCallBack {
    final /* synthetic */ PayPasswordModifyActivity a;

    zj(PayPasswordModifyActivity payPasswordModifyActivity) {
        this.a = payPasswordModifyActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        if (this.a != null) {
            this.a.j();
            this.a.j();
            ToastAndDialog.makePositiveToast(QsbkApp.mContext, "修改支付密码成功", Integer.valueOf(0)).show();
            this.a.finish();
        }
    }

    public void onFailure(String str, String str2) {
        this.a.j();
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str2, Integer.valueOf(0)).show();
    }
}
