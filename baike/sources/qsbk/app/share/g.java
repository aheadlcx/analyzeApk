package qsbk.app.share;

import android.content.Intent;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.activity.FillUserDataActivity;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.thirdparty.ThirdPartyConstants;
import qsbk.app.utils.ToastAndDialog;

class g implements SimpleCallBack {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ String d;
    final /* synthetic */ String e;
    final /* synthetic */ LiveLoginActivity f;

    g(LiveLoginActivity liveLoginActivity, String str, String str2, String str3, String str4, String str5) {
        this.f = liveLoginActivity;
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
    }

    public void onSuccess(JSONObject jSONObject) {
        this.f.handleToken(jSONObject);
        this.f.a(QsbkApp.currentUser.userName);
        ToastAndDialog.makePositiveToast(this.f, String.format("欢迎回来，%s", new Object[]{QsbkApp.currentUser.userName})).show();
        this.f.a(this.b, this.c, this.a.equals(ThirdPartyConstants.THIRDPARTY_TYLE_SINA) ? QsbkApp.currentUser.userId + "_sina_access_token" : QsbkApp.currentUser.userId + "_qq_access_token", this.a);
        this.f.onLoginSuccess();
        this.f.setResult(-1);
        this.f.finish();
    }

    public void onFailure(int i, String str) {
        if (i == 114) {
            Intent intent = new Intent(this.f, FillUserDataActivity.class);
            intent.putExtra("type", this.a);
            intent.putExtra("token", this.b);
            intent.putExtra("expires_in", this.c);
            if (ThirdPartyConstants.THIRDPARTY_TYLE_QQ.equalsIgnoreCase(this.a) || ThirdPartyConstants.THIRDPARTY_TYLE_WX.equalsIgnoreCase(this.a)) {
                intent.putExtra("openid", this.d);
            } else if (ThirdPartyConstants.THIRDPARTY_TYLE_SINA.equalsIgnoreCase(this.a)) {
                intent.putExtra("uid", this.e);
            }
            this.f.startActivityForResult(intent, 99);
            return;
        }
        ToastAndDialog.makeNegativeToast(this.f, str, Integer.valueOf(1)).show();
        this.f.setResult(0);
    }
}
