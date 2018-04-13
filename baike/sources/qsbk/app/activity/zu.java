package qsbk.app.activity;

import android.text.TextUtils;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.HttpCallBack;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;

class zu implements HttpCallBack {
    final /* synthetic */ PayPwdResetActivity a;

    zu(PayPwdResetActivity payPwdResetActivity) {
        this.a = payPwdResetActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        Object optString = jSONObject.optString("token", null);
        if (!(TextUtils.isEmpty(optString) || QsbkApp.currentUser == null)) {
            QsbkApp.currentUser.token = optString;
            SharePreferenceUtils.setSharePreferencesValue("loginUser", QsbkApp.currentUser.toString());
        }
        if (this.a != null) {
            ToastAndDialog.makePositiveToast(QsbkApp.mContext, "密码重置成功", Integer.valueOf(0)).show();
            UIHelper.hideKeyboard(this.a);
            this.a.finish();
            this.a.i();
        }
    }

    public void onFailure(String str, String str2) {
        this.a.i();
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str2, Integer.valueOf(0)).show();
    }
}
