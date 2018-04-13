package qsbk.app.im;

class iy implements Runnable {
    final /* synthetic */ QiuyouquanNotificationCountManager a;

    iy(QiuyouquanNotificationCountManager qiuyouquanNotificationCountManager) {
        this.a = qiuyouquanNotificationCountManager;
    }

    public void run() {
        this.a.e = Integer.valueOf(this.a.c.getUnreadCountWithUser(QiuyouquanNotificationCountManager.QIUYOUQUAN_PUSH_UID));
        ChatMsg latestMsgWithUser = this.a.c.getLatestMsgWithUser(QiuyouquanNotificationCountManager.QIUYOUQUAN_PUSH_UID);
        if (latestMsgWithUser != null) {
            this.a.a(latestMsgWithUser);
        }
    }
}
