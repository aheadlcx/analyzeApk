package qsbk.app.activity;

import android.content.Intent;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.ToastAndDialog;

class t implements SimpleCallBack {
    final /* synthetic */ ActionBarLoginActivity a;

    t(ActionBarLoginActivity actionBarLoginActivity) {
        this.a = actionBarLoginActivity;
    }

    public void onSuccess(JSONObject jSONObject) {
        ActionBarLoginActivity.b(this.a);
        this.a.handleToken(jSONObject);
        LogUtil.d("登录注册成功,缓存登录的用户名密码");
        this.a.f();
        ActionBarLoginActivity.b(this.a);
        Intent intent = new Intent();
        intent.putExtra(ActionBarLoginActivity.SIGNED_IN, Boolean.TRUE);
        this.a.setResult(-1, intent);
        ToastAndDialog.makePositiveToast(this.a, String.format("欢迎回来，%s", new Object[]{QsbkApp.currentUser.userName})).show();
        this.a.finish();
        if (this.a.c != null) {
            this.a.a.startActivity(new Intent(this.a.a, this.a.c));
        }
        this.a.onLoginSuccess();
    }

    public void onFailure(int i, String str) {
        ActionBarLoginActivity.b(this.a);
        ToastAndDialog.makeNegativeToast(this.a, str, Integer.valueOf(0)).show();
    }
}
