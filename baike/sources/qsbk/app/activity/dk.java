package qsbk.app.activity;

import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.HttpCallBack;
import qsbk.app.utils.ToastAndDialog;

class dk implements HttpCallBack {
    final /* synthetic */ BindPhoneActivity a;

    dk(BindPhoneActivity bindPhoneActivity) {
        this.a = bindPhoneActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
    }

    public void onFailure(String str, String str2) {
        this.a.a.stopCountDown();
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str2, Integer.valueOf(0)).show();
    }
}
