package qsbk.app.activity;

import qsbk.app.im.MessageCountManager;

class sa implements Runnable {
    final /* synthetic */ MainActivity a;

    sa(MainActivity mainActivity) {
        this.a = mainActivity;
    }

    public void run() {
        if (MainActivity.d(this.a) != null) {
            MessageCountManager d = MainActivity.d(this.a);
            if (d != null) {
                d.addUnreadCountListener(this.a);
            }
            this.a.unread(MainActivity.d(this.a).getUnreadCount(), MainActivity.d(this.a).getUnregardedCount());
        }
    }
}
