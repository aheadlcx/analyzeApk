package qsbk.app.im;

import java.util.List;

class bt implements Runnable {
    final /* synthetic */ ConversationActivity a;

    bt(ConversationActivity conversationActivity) {
        this.a = conversationActivity;
    }

    public void run() {
        List list = this.a.aj.get(this.a.getToId());
        if (list.size() != 0) {
            this.a.y.post(new bu(this, list));
        }
    }
}
