package qsbk.app.service;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import qsbk.app.QsbkApp;
import qsbk.app.activity.MainActivity;
import qsbk.app.im.IMNotifyManager;
import qsbk.app.im.UserChatManager;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.LogUtil;

class d extends Handler {
    final /* synthetic */ VerifyUserInfoService a;

    d(VerifyUserInfoService verifyUserInfoService) {
        this.a = verifyUserInfoService;
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case 0:
                LogUtil.d("on user veryfi fail:");
                DebugUtil.debug("验证未通过,发送广播");
                if (QsbkApp.currentUser != null) {
                    UserChatManager.getUserChatManager(QsbkApp.currentUser.userId, QsbkApp.currentUser.token).destroy(false);
                }
                LocalBroadcastManager.getInstance(this.a).sendBroadcast(new Intent(MainActivity.ACTION_VERIFY));
                return;
            case 1:
                IMNotifyManager.getSettingFromCloud();
                LogUtil.d("on user veryfi success:");
                this.a.stopSelf();
                return;
            default:
                return;
        }
    }
}
