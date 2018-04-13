package qsbk.app.im;

import java.util.List;

class gu implements Runnable {
    final /* synthetic */ List a;
    final /* synthetic */ gt b;

    gu(gt gtVar, List list) {
        this.b = gtVar;
        this.a = list;
    }

    public void run() {
        this.b.b.b(this.a);
        MessageCountManager.getMessageCountManager(this.b.a).updateUnreadCount(this.b.b.C, this.b.b.D);
    }
}
