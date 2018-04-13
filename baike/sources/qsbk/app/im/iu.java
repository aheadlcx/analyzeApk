package qsbk.app.im;

class iu implements Runnable {
    final /* synthetic */ QiushiNotificationCountManager a;

    iu(QiushiNotificationCountManager qiushiNotificationCountManager) {
        this.a = qiushiNotificationCountManager;
    }

    public void run() {
        this.a.c.deleteMessagesWith(QiushiNotificationCountManager.QIUSHI_PUSH_UID);
    }
}
