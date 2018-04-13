package qsbk.app.activity;

import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.HttpCallBack;
import qsbk.app.utils.ToastAndDialog;

class zv implements HttpCallBack {
    final /* synthetic */ PayPwdResetActivity a;

    zv(PayPwdResetActivity payPwdResetActivity) {
        this.a = payPwdResetActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
    }

    public void onFailure(String str, String str2) {
        this.a.c.stopCountDown();
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str2, Integer.valueOf(0)).show();
    }
}
