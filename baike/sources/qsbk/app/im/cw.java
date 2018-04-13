package qsbk.app.im;

import java.util.List;
import qsbk.app.utils.comm.ArrayUtils;

class cw implements Runnable {
    final /* synthetic */ ConversationActivity a;

    cw(ConversationActivity conversationActivity) {
        this.a = conversationActivity;
    }

    public void run() {
        List list = this.a.aj.get(0, 1, this.a.getToId(), String.valueOf(Long.MAX_VALUE));
        if (ArrayUtils.isEmpty(list)) {
            ContactListItem withUser = this.a.ak.getWithUser(this.a.getToId());
            if (withUser != null) {
                withUser.mLastContent = "";
                this.a.ak.update(withUser);
                return;
            }
            return;
        }
        ChatMsg chatMsg = (ChatMsg) list.get(0);
        ContactListItem withUser2 = this.a.ak.getWithUser(this.a.getToId());
        if (withUser2 != null && chatMsg != null) {
            withUser2.msgId = chatMsg.dbid;
            withUser2.inout = chatMsg.inout;
            withUser2.mimeType = chatMsg.type;
            withUser2.mLastContent = chatMsg.getMsgTips();
            withUser2.mLastUpdateTime = chatMsg.time;
            this.a.ak.update(withUser2);
        }
    }
}
