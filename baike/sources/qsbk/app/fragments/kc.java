package qsbk.app.fragments;

class kc implements Runnable {
    final /* synthetic */ QiuyouquanNotificationListFragment a;

    kc(QiuyouquanNotificationListFragment qiuyouquanNotificationListFragment) {
        this.a = qiuyouquanNotificationListFragment;
    }

    public void run() {
        this.a.t.post(new kd(this, this.a.c()));
    }
}
