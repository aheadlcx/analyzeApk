package qsbk.app.model;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import qsbk.app.QsbkApp;
import qsbk.app.activity.FillUserDataActivity;
import qsbk.app.thirdparty.ThirdPartyConstants;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.ToastAndDialog;

final class p extends Handler {
    p() {
    }

    public void handleMessage(Message message) {
        UserLoginGuideCard.b();
        if (message.what == 0) {
            UserLoginGuideCard.handleToken(UserLoginGuideCard.a);
            DebugUtil.debug(UserLoginGuideCard.c, "第三方登录成功，缓存用户名和密码");
            if (UserLoginGuideCard.b != null) {
                UserLoginGuideCard.d.startActivity(new Intent(UserLoginGuideCard.d, UserLoginGuideCard.b));
            }
            UserLoginGuideCard.c(UserLoginGuideCard.k, UserLoginGuideCard.l, UserLoginGuideCard.j.equals(ThirdPartyConstants.THIRDPARTY_TYLE_SINA) ? QsbkApp.currentUser.userId + "_sina_access_token" : QsbkApp.currentUser.userId + "_qq_access_token");
            ToastAndDialog.makePositiveToast(UserLoginGuideCard.d, String.format("欢迎回来，%s", new Object[]{QsbkApp.currentUser.userName})).show();
            UserLoginGuideCard.onLoginSuccess();
        } else if (message.what == 114) {
            Intent intent = new Intent(UserLoginGuideCard.d, FillUserDataActivity.class);
            intent.putExtra("type", UserLoginGuideCard.j);
            intent.putExtra("token", UserLoginGuideCard.k);
            intent.putExtra("expires_in", UserLoginGuideCard.l);
            if (ThirdPartyConstants.THIRDPARTY_TYLE_QQ.equalsIgnoreCase(UserLoginGuideCard.j) || ThirdPartyConstants.THIRDPARTY_TYLE_WX.equalsIgnoreCase(UserLoginGuideCard.j)) {
                intent.putExtra("openid", UserLoginGuideCard.n);
            } else {
                intent.putExtra("uid", UserLoginGuideCard.o);
            }
            UserLoginGuideCard.d.startActivity(intent);
        } else {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, (String) message.obj, Integer.valueOf(0)).show();
        }
    }
}
