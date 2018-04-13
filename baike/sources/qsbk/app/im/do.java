package qsbk.app.im;

import java.util.List;
import qsbk.app.utils.GroupMsgUtils;
import qsbk.app.utils.comm.ArrayUtils;

class do implements Runnable {
    final /* synthetic */ GroupConversationActivity a;

    do(GroupConversationActivity groupConversationActivity) {
        this.a = groupConversationActivity;
    }

    public void run() {
        List unreadMsgIds = this.a.af.getUnreadMsgIds(this.a.getToId());
        this.a.af.markMessagesToReadWith(Integer.parseInt(this.a.getToId()));
        if (!ArrayUtils.isEmpty(unreadMsgIds)) {
            this.a.af.addUserTotalMsgUnread(-unreadMsgIds.size(), GroupMsgUtils.isOpen(this.a.getToId(), true));
        }
    }
}
