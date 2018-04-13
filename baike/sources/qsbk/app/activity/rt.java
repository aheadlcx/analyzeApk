package qsbk.app.activity;

import qsbk.app.QsbkApp;
import qsbk.app.im.QiuyouquanNotificationCountManager;
import qsbk.app.im.QiuyouquanNotificationCountManager.NotificationModel;

class rt implements Runnable {
    final /* synthetic */ MainActivity a;

    rt(MainActivity mainActivity) {
        this.a = mainActivity;
    }

    public void run() {
        if (QsbkApp.currentUser != null) {
            NotificationModel lastNotification = QiuyouquanNotificationCountManager.getInstance(QsbkApp.currentUser.userId).getLastNotification();
            if (lastNotification == null || lastNotification.getUnReadCount() <= 0) {
                this.a.hideTips(MainActivity.TAB_QIUYOUCIRCLE_ID);
                return;
            }
            int unReadCount = lastNotification.getUnReadCount();
            this.a.setTips(MainActivity.TAB_QIUYOUCIRCLE_ID, unReadCount > 99 ? "99+" : unReadCount + "");
        }
    }
}
