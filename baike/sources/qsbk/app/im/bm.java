package qsbk.app.im;

import qsbk.app.QsbkApp;

final class bm implements Runnable {
    bm() {
    }

    public void run() {
        UserChatManager.getUserChatManager(QsbkApp.currentUser.userId, QsbkApp.currentUser.token).mockReceiveGroupMessage();
    }
}
