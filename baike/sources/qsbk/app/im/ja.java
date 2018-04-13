package qsbk.app.im;

class ja implements Runnable {
    final /* synthetic */ QiuyouquanNotificationCountManager a;

    ja(QiuyouquanNotificationCountManager qiuyouquanNotificationCountManager) {
        this.a = qiuyouquanNotificationCountManager;
    }

    public void run() {
        this.a.c.markMessagesToReadWith(Integer.parseInt(QiuyouquanNotificationCountManager.QIUYOUQUAN_PUSH_UID));
    }
}
