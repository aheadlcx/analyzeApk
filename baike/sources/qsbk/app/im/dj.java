package qsbk.app.im;

import java.util.List;
import qsbk.app.utils.GroupMsgUtils;
import qsbk.app.utils.comm.ArrayUtils;

class dj implements Runnable {
    final /* synthetic */ List a;
    final /* synthetic */ GroupConversationActivity b;

    dj(GroupConversationActivity groupConversationActivity, List list) {
        this.b = groupConversationActivity;
        this.a = list;
    }

    public void run() {
        List filter = ArrayUtils.filter(this.a, new dk(this));
        ArrayUtils.each(filter, new dl(this));
        if (!ArrayUtils.isEmpty(filter)) {
            this.b.af.addUserTotalMsgUnread(-filter.size(), GroupMsgUtils.isOpen(this.b.getToId(), true));
            ArrayUtils.map(filter, new dm(this));
            this.b.af.setMessageReaded(ArrayUtils.map(filter, new dn(this)));
        }
    }
}
