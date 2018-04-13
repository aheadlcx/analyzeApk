package qsbk.app.im;

class it implements Runnable {
    final /* synthetic */ QiushiNotificationCountManager a;

    it(QiushiNotificationCountManager qiushiNotificationCountManager) {
        this.a = qiushiNotificationCountManager;
    }

    public void run() {
        this.a.c.markMessagesToReadWith(Integer.parseInt(QiushiNotificationCountManager.QIUSHI_PUSH_UID));
    }
}
