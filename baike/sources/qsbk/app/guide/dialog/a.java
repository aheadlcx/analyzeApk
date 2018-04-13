package qsbk.app.guide.dialog;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import qsbk.app.QsbkApp;
import qsbk.app.activity.FillUserDataActivity;
import qsbk.app.thirdparty.ThirdPartyConstants;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.ToastAndDialog;

class a extends Handler {
    final /* synthetic */ LoginGuideDialog a;

    a(LoginGuideDialog loginGuideDialog) {
        this.a = loginGuideDialog;
    }

    public void handleMessage(Message message) {
        LoginGuideDialog.b();
        if (message.what == 0) {
            LoginGuideDialog.b(LoginGuideDialog.a);
            DebugUtil.debug(LoginGuideDialog.c, "第三方登录成功，缓存用户名和密码");
            if (LoginGuideDialog.b != null) {
                LoginGuideDialog.d.startActivity(new Intent(LoginGuideDialog.d, LoginGuideDialog.b));
            }
            LoginGuideDialog.b(LoginGuideDialog.g, LoginGuideDialog.h, LoginGuideDialog.f.equals(ThirdPartyConstants.THIRDPARTY_TYLE_SINA) ? QsbkApp.currentUser.userId + "_sina_access_token" : QsbkApp.currentUser.userId + "_qq_access_token");
            ToastAndDialog.makePositiveToast(LoginGuideDialog.d, String.format("欢迎回来，%s", new Object[]{QsbkApp.currentUser.userName})).show();
            this.a.onLoginSuccess();
        } else if (message.what == 114) {
            Intent intent = new Intent(LoginGuideDialog.d, FillUserDataActivity.class);
            intent.putExtra("type", LoginGuideDialog.f);
            intent.putExtra("token", LoginGuideDialog.g);
            intent.putExtra("expires_in", LoginGuideDialog.h);
            if (ThirdPartyConstants.THIRDPARTY_TYLE_QQ.equalsIgnoreCase(LoginGuideDialog.f) || ThirdPartyConstants.THIRDPARTY_TYLE_WX.equalsIgnoreCase(LoginGuideDialog.f)) {
                intent.putExtra("openid", LoginGuideDialog.i);
            } else {
                intent.putExtra("uid", LoginGuideDialog.j);
            }
            LoginGuideDialog.d.startActivity(intent);
        } else {
            Toast.makeText(LoginGuideDialog.d, (String) message.obj, 1).show();
        }
    }
}
