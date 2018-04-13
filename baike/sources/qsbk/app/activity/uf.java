package qsbk.app.activity;

import android.text.TextUtils;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.HttpCallBack;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.ToastAndDialog;

class uf implements HttpCallBack {
    final /* synthetic */ ModifyPwdActivity a;

    uf(ModifyPwdActivity modifyPwdActivity) {
        this.a = modifyPwdActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        Object optString = jSONObject.optString("token", null);
        if (!TextUtils.isEmpty(optString)) {
            QsbkApp.currentUser.token = optString;
            SharePreferenceUtils.setSharePreferencesValue("loginUser", QsbkApp.currentUser.toString());
        }
        if (this.a != null) {
            this.a.k();
            ToastAndDialog.makePositiveToast(QsbkApp.mContext, "密码修改成功", Integer.valueOf(0)).show();
            this.a.finish();
        }
    }

    public void onFailure(String str, String str2) {
        this.a.k();
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str2, Integer.valueOf(0)).show();
    }
}
