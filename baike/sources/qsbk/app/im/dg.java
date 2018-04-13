package qsbk.app.im;

import java.util.List;

class dg implements Runnable {
    final /* synthetic */ GroupConversationActivity a;

    dg(GroupConversationActivity groupConversationActivity) {
        this.a = groupConversationActivity;
    }

    public void run() {
        List list = this.a.af.get(this.a.getToId());
        if (list != null && list.size() != 0) {
            this.a.y.post(new dh(this, list));
        }
    }
}
