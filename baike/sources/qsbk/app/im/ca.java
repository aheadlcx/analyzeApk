package qsbk.app.im;

import java.util.List;
import qsbk.app.utils.comm.ArrayUtils;

class ca implements Runnable {
    final /* synthetic */ ConversationActivity a;

    ca(ConversationActivity conversationActivity) {
        this.a = conversationActivity;
    }

    public void run() {
        List unreadMsgIds = this.a.aj.getUnreadMsgIds(this.a.getToId());
        this.a.aj.markMessagesToReadWith(Integer.parseInt(this.a.getToId()));
        if (!ArrayUtils.isEmpty(unreadMsgIds)) {
            this.a.aj.addUserTotalMsgUnread(-unreadMsgIds.size(), true);
            this.a.z.sendReadedMsg(this.a.getToId(), unreadMsgIds, this.a.s);
        }
    }
}
