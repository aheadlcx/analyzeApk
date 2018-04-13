package qsbk.app.activity;

import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.HttpCallBack;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.ToastAndDialog;

class dr implements HttpCallBack {
    final /* synthetic */ BindPhoneActivity a;

    dr(BindPhoneActivity bindPhoneActivity) {
        this.a = bindPhoneActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        QsbkApp.currentUser.phone = this.a.i;
        SharePreferenceUtils.setSharePreferencesValue("loginUser", QsbkApp.currentUser.toString());
        if (this.a != null) {
            this.a.s();
            this.a.setResult(-1, null);
            this.a.finish();
        }
    }

    public void onFailure(String str, String str2) {
        if (this.a != null) {
            this.a.s();
            this.a.a.stopCountDown();
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str2, Integer.valueOf(0)).show();
        }
    }
}
