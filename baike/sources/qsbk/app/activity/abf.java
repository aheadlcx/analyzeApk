package qsbk.app.activity;

import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.HttpCallBack;
import qsbk.app.utils.ToastAndDialog;

class abf implements HttpCallBack {
    final /* synthetic */ ResetPwdActivity a;

    abf(ResetPwdActivity resetPwdActivity) {
        this.a = resetPwdActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
    }

    public void onFailure(String str, String str2) {
        this.a.c.stopCountDown();
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str2, Integer.valueOf(0)).show();
    }
}
