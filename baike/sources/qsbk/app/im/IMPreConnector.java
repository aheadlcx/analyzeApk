package qsbk.app.im;

import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.utils.GroupMsgUtils;

public class IMPreConnector {
    private static SimpleCallBack a;
    private UserChatManager b;

    public void preConnect(String str) {
        if (QsbkApp.currentUser != null) {
            if (this.b == null) {
                this.b = UserChatManager.getUserChatManager(QsbkApp.currentUser.userId, QsbkApp.currentUser.token);
                if (this.b.isConnected()) {
                    this.b.removeDisconnentEvent();
                    return;
                }
            }
            if (a == null) {
                a = new ib(this, str);
                GroupMsgUtils.loadAll(a);
            }
        }
    }

    private void a(String str) {
        MessageCountManager.getMessageCountManager(QsbkApp.currentUser.userId);
        if (str == null) {
            str = "onPushNotify";
        }
        if (this.b == null) {
            this.b = UserChatManager.getUserChatManager(QsbkApp.currentUser.userId, QsbkApp.currentUser.token);
        }
        if (this.b.isConnected()) {
            this.b.removeDisconnentEvent();
        } else {
            this.b.getConnectHost(new id(this, str));
        }
    }
}
