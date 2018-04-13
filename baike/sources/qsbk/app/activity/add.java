package qsbk.app.activity;

import org.json.JSONObject;
import qsbk.app.http.HttpCallBack;
import qsbk.app.utils.ToastAndDialog;

class add implements HttpCallBack {
    final /* synthetic */ SocialVerifyActivity a;

    add(SocialVerifyActivity socialVerifyActivity) {
        this.a = socialVerifyActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        ToastAndDialog.makePositiveToast(this.a, "验证成功").show();
        this.a.m();
        this.a.g();
    }

    public void onFailure(String str, String str2) {
        this.a.m();
        ToastAndDialog.makeNegativeToast(this.a, str2).show();
    }
}
