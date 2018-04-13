package qsbk.app.im;

class iw implements Runnable {
    final /* synthetic */ QiushiNotificationCountManager a;

    iw(QiushiNotificationCountManager qiushiNotificationCountManager) {
        this.a = qiushiNotificationCountManager;
    }

    public void run() {
        this.a.c.deleteCommentLikeMessagesWith(QiushiNotificationCountManager.QIUSHI_PUSH_UID);
    }
}
