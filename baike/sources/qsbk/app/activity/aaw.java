package qsbk.app.activity;

import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.HttpCallBack;
import qsbk.app.utils.ToastAndDialog;

class aaw implements HttpCallBack {
    final /* synthetic */ ReBindPhoneActivity a;

    aaw(ReBindPhoneActivity reBindPhoneActivity) {
        this.a = reBindPhoneActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
    }

    public void onFailure(String str, String str2) {
        this.a.b.stopCountDown();
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str2, Integer.valueOf(0)).show();
    }
}
