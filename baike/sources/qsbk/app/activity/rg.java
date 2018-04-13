package qsbk.app.activity;

import qsbk.app.im.MessageCountManager;

class rg implements Runnable {
    final /* synthetic */ rf a;

    rg(rf rfVar) {
        this.a = rfVar;
    }

    public void run() {
        MessageCountManager d = MainActivity.d(this.a.a);
        if (d != null) {
            d.addUnreadCountListener(this.a.a);
        }
        this.a.a.unread(MainActivity.d(this.a.a).getUnreadCount(), MainActivity.d(this.a.a).getUnregardedCount());
    }
}
