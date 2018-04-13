package qsbk.app.activity;

import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.http.HttpCallBack;
import qsbk.app.utils.ToastAndDialog;

class acp implements HttpCallBack {
    final /* synthetic */ SettingPersonalBigCoverBaseActivity a;

    acp(SettingPersonalBigCoverBaseActivity settingPersonalBigCoverBaseActivity) {
        this.a = settingPersonalBigCoverBaseActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        this.a.a(jSONObject.optString("uptoken"), this.a.c);
    }

    public void onFailure(String str, String str2) {
        this.a.b();
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str2, Integer.valueOf(0)).show();
        this.a.a(this.a.getApplication().getString(R.string.upload_big_cover_fail));
    }
}
