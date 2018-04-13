package qsbk.app.model;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import qsbk.app.QsbkApp;
import qsbk.app.activity.FillUserDataActivity;
import qsbk.app.thirdparty.ThirdPartyConstants;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.ToastAndDialog;

final class x extends Handler {
    x() {
    }

    public void handleMessage(Message message) {
        WelcomeCard.b();
        if (message.what == 0) {
            WelcomeCard.handleToken(WelcomeCard.a);
            DebugUtil.debug(WelcomeCard.c, "第三方登录成功，缓存用户名和密码");
            if (WelcomeCard.b != null) {
                WelcomeCard.e.startActivity(new Intent(WelcomeCard.e, WelcomeCard.b));
            }
            WelcomeCard.c(WelcomeCard.g, WelcomeCard.h, WelcomeCard.f.equals(ThirdPartyConstants.THIRDPARTY_TYLE_SINA) ? QsbkApp.currentUser.userId + "_sina_access_token" : QsbkApp.currentUser.userId + "_qq_access_token");
            ToastAndDialog.makePositiveToast(WelcomeCard.e, String.format("欢迎回来，%s", new Object[]{QsbkApp.currentUser.userName})).show();
            WelcomeCard.onLoginSuccess();
        } else if (message.what == 114) {
            Intent intent = new Intent(WelcomeCard.e, FillUserDataActivity.class);
            intent.putExtra("type", WelcomeCard.f);
            intent.putExtra("token", WelcomeCard.g);
            intent.putExtra("expires_in", WelcomeCard.h);
            if (ThirdPartyConstants.THIRDPARTY_TYLE_QQ.equalsIgnoreCase(WelcomeCard.f) || ThirdPartyConstants.THIRDPARTY_TYLE_WX.equalsIgnoreCase(WelcomeCard.f)) {
                intent.putExtra("openid", WelcomeCard.i);
            } else {
                intent.putExtra("uid", WelcomeCard.n);
            }
            WelcomeCard.e.startActivity(intent);
        } else {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, (String) message.obj, Integer.valueOf(0)).show();
        }
    }
}
