package qsbk.app.im;

class ir implements Runnable {
    final /* synthetic */ QiushiNotificationCountManager a;

    ir(QiushiNotificationCountManager qiushiNotificationCountManager) {
        this.a = qiushiNotificationCountManager;
    }

    public void run() {
        this.a.e = Integer.valueOf(this.a.c.getUnreadCountWithUser(QiushiNotificationCountManager.QIUSHI_PUSH_UID));
        ChatMsg latestMsgWithUser = this.a.c.getLatestMsgWithUser(QiushiNotificationCountManager.QIUSHI_PUSH_UID);
        if (latestMsgWithUser != null) {
            this.a.a(latestMsgWithUser);
        }
    }
}
