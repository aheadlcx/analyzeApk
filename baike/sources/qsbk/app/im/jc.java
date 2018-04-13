package qsbk.app.im;

class jc implements Runnable {
    final /* synthetic */ QiuyouquanNotificationCountManager a;

    jc(QiuyouquanNotificationCountManager qiuyouquanNotificationCountManager) {
        this.a = qiuyouquanNotificationCountManager;
    }

    public void run() {
        this.a.c.deleteAtMessagesWith(QiuyouquanNotificationCountManager.QIUYOUQUAN_PUSH_UID);
    }
}
