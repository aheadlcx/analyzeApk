package qsbk.app.im;

class jb implements Runnable {
    final /* synthetic */ QiuyouquanNotificationCountManager a;

    jb(QiuyouquanNotificationCountManager qiuyouquanNotificationCountManager) {
        this.a = qiuyouquanNotificationCountManager;
    }

    public void run() {
        this.a.c.deleteLikedMessagesWith(QiuyouquanNotificationCountManager.QIUYOUQUAN_PUSH_UID);
    }
}
