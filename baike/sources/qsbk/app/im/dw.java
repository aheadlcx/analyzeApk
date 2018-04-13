package qsbk.app.im;

import java.util.List;

class dw implements Runnable {
    final /* synthetic */ GroupConversationActivity a;

    dw(GroupConversationActivity groupConversationActivity) {
        this.a = groupConversationActivity;
    }

    public void run() {
        List list = this.a.af.get(this.a.getToId());
        if (list.size() > 0) {
            this.a.aO = ((ChatMsg) list.get(0)).dbid;
        }
        this.a.y.postDelayed(new dx(this, list), 100);
    }
}
