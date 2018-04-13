package qsbk.app.im;

class ix implements Runnable {
    final /* synthetic */ QiushiNotificationCountManager a;

    ix(QiushiNotificationCountManager qiushiNotificationCountManager) {
        this.a = qiushiNotificationCountManager;
    }

    public void run() {
        this.a.c.deleteAtInfoMessagesWith(QiushiNotificationCountManager.QIUSHI_PUSH_UID);
    }
}
