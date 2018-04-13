package qsbk.app.activity;

import qsbk.app.im.MessageCountManager;

class ss implements Runnable {
    final /* synthetic */ MainActivity a;

    ss(MainActivity mainActivity) {
        this.a = mainActivity;
    }

    public void run() {
        MessageCountManager d = MainActivity.d(this.a);
        if (d != null) {
            d.addUnreadCountListener(this.a);
        }
        this.a.unread(MainActivity.d(this.a).getUnreadCount(), MainActivity.d(this.a).getUnregardedCount());
    }
}
