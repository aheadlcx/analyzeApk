package qsbk.app.im;

class iv implements Runnable {
    final /* synthetic */ QiushiNotificationCountManager a;

    iv(QiushiNotificationCountManager qiushiNotificationCountManager) {
        this.a = qiushiNotificationCountManager;
    }

    public void run() {
        this.a.c.deleteSmileMessagesWith(QiushiNotificationCountManager.QIUSHI_PUSH_UID);
    }
}
