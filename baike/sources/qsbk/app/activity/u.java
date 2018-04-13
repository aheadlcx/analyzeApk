package qsbk.app.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import qsbk.app.QsbkApp;
import qsbk.app.thirdparty.ThirdPartyConstants;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.ToastAndDialog;

class u extends Handler {
    final /* synthetic */ ActionBarLoginActivity a;

    u(ActionBarLoginActivity actionBarLoginActivity) {
        this.a = actionBarLoginActivity;
    }

    public void handleMessage(Message message) {
        ActionBarLoginActivity.b(this.a);
        ActionBarLoginActivity.b(this.a, false);
        if (message.what == 0) {
            this.a.handleToken(this.a.b);
            LogUtil.d("第三方登录成功，缓存用户名和密码");
            this.a.f();
            if (this.a.c != null) {
                this.a.a.startActivity(new Intent(this.a.a, this.a.c));
            }
            ActionBarLoginActivity.a(this.a, ActionBarLoginActivity.d(this.a), ActionBarLoginActivity.e(this.a), ActionBarLoginActivity.c(this.a).equals(ThirdPartyConstants.THIRDPARTY_TYLE_SINA) ? QsbkApp.currentUser.userId + "_sina_access_token" : QsbkApp.currentUser.userId + "_qq_access_token");
            ToastAndDialog.makePositiveToast(this.a, String.format("欢迎回来，%s", new Object[]{QsbkApp.currentUser.userName})).show();
            this.a.onLoginSuccess();
            this.a.setResult(-1);
            this.a.finish();
        } else if (message.what == 114) {
            Intent intent = new Intent(this.a, FillUserDataActivity.class);
            intent.putExtra("type", ActionBarLoginActivity.c(this.a));
            intent.putExtra("token", ActionBarLoginActivity.d(this.a));
            intent.putExtra("expires_in", ActionBarLoginActivity.e(this.a));
            if (ThirdPartyConstants.THIRDPARTY_TYLE_QQ.equalsIgnoreCase(ActionBarLoginActivity.c(this.a)) || ThirdPartyConstants.THIRDPARTY_TYLE_WX.equalsIgnoreCase(ActionBarLoginActivity.c(this.a))) {
                intent.putExtra("openid", ActionBarLoginActivity.f(this.a));
            } else {
                intent.putExtra("uid", ActionBarLoginActivity.g(this.a));
            }
            this.a.startActivity(intent);
            this.a.finish();
        } else {
            ToastAndDialog.makeNegativeToast(this.a.a, (String) message.obj, Integer.valueOf(1)).show();
        }
    }
}
