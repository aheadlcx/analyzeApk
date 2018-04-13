package qsbk.app.im;

import java.util.List;
import qsbk.app.utils.comm.ArrayUtils;

class bv implements Runnable {
    final /* synthetic */ List a;
    final /* synthetic */ ConversationActivity b;

    bv(ConversationActivity conversationActivity, List list) {
        this.b = conversationActivity;
        this.a = list;
    }

    public void run() {
        List filter = ArrayUtils.filter(this.a, new bw(this));
        ArrayUtils.each(filter, new bx(this));
        if (!ArrayUtils.isEmpty(filter)) {
            this.b.aj.addUserTotalMsgUnread(-filter.size(), true);
            List map = ArrayUtils.map(filter, new by(this));
            filter = ArrayUtils.map(filter, new bz(this));
            this.b.z.sendReadedMsg(this.b.getToId(), map, this.b.s);
            this.b.aj.setMessageReadedBatch(filter);
        }
    }
}
