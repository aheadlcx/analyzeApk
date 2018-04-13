package qsbk.app.activity;

import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.HttpCallBack;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.ToastAndDialog;

class zp implements HttpCallBack {
    final /* synthetic */ PayPasswordSetActivity a;

    zp(PayPasswordSetActivity payPasswordSetActivity) {
        this.a = payPasswordSetActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        QsbkApp.currentUser.hasPayPwd = 1;
        SharePreferenceUtils.setSharePreferencesValue("loginUser", QsbkApp.currentUser.toString());
        if (!this.a.isFinishing()) {
            this.a.k();
            this.a.k();
            ToastAndDialog.makePositiveToast(QsbkApp.mContext, "设置支付密码成功", Integer.valueOf(0)).show();
            this.a.finish();
        }
    }

    public void onFailure(String str, String str2) {
        this.a.k();
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str2, Integer.valueOf(0)).show();
    }
}
