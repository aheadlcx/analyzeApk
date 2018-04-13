package qsbk.app.im;

import qsbk.app.utils.GroupMsgUtils;

class jm implements Runnable {
    final /* synthetic */ ChatMsg a;
    final /* synthetic */ a b;

    jm(a aVar, ChatMsg chatMsg) {
        this.b = aVar;
        this.a = chatMsg;
    }

    public void run() {
        boolean c = this.b.c.f(this.a);
        if (this.a.isContentMsg() && GroupMsgUtils.isOpen(this.a.gid, true)) {
            this.b.c.a(this.a, c);
        }
    }
}
