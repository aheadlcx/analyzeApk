package qsbk.app.fragments;

class io implements Runnable {
    final /* synthetic */ QiushiNotificationListFragment a;

    io(QiushiNotificationListFragment qiushiNotificationListFragment) {
        this.a = qiushiNotificationListFragment;
    }

    public void run() {
        this.a.p.post(new ip(this, this.a.c()));
    }
}
