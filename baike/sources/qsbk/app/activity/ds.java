package qsbk.app.activity;

import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.HttpCallBack;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.ToastAndDialog;

class ds implements HttpCallBack {
    final /* synthetic */ BindPhoneActivity a;

    ds(BindPhoneActivity bindPhoneActivity) {
        this.a = bindPhoneActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        QsbkApp.currentUser.phone = this.a.i;
        SharePreferenceUtils.setSharePreferencesValue("loginUser", QsbkApp.currentUser.toString());
        if (this.a != null) {
            this.a.s();
            this.a.setResult(-1, null);
            this.a.s();
            ToastAndDialog.makePositiveToast(QsbkApp.mContext, "绑定手机成功", Integer.valueOf(0)).show();
            this.a.finish();
        }
    }

    public void onFailure(String str, String str2) {
        this.a.s();
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str2, Integer.valueOf(0)).show();
    }
}
