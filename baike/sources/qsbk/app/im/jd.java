package qsbk.app.im;

class jd implements Runnable {
    final /* synthetic */ QiuyouquanNotificationCountManager a;

    jd(QiuyouquanNotificationCountManager qiuyouquanNotificationCountManager) {
        this.a = qiuyouquanNotificationCountManager;
    }

    public void run() {
        this.a.c.deleteCircleCommentLikeMessagesWith(QiuyouquanNotificationCountManager.QIUYOUQUAN_PUSH_UID);
    }
}
