package qsbk.app.im;

class je implements Runnable {
    final /* synthetic */ QiuyouquanNotificationCountManager a;

    je(QiuyouquanNotificationCountManager qiuyouquanNotificationCountManager) {
        this.a = qiuyouquanNotificationCountManager;
    }

    public void run() {
        this.a.c.deleteCircleForwardMessageWith(QiuyouquanNotificationCountManager.QIUYOUQUAN_PUSH_UID);
    }
}
