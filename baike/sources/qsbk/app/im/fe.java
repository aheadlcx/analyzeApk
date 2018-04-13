package qsbk.app.im;

import java.util.List;
import qsbk.app.utils.comm.ArrayUtils;

class fe implements Runnable {
    final /* synthetic */ GroupConversationActivity a;

    fe(GroupConversationActivity groupConversationActivity) {
        this.a = groupConversationActivity;
    }

    public void run() {
        List list = this.a.af.get(0, 1, this.a.getToId(), String.valueOf(Long.MAX_VALUE));
        if (ArrayUtils.isEmpty(list)) {
            ContactListItem withGroup = this.a.ag.getWithGroup(this.a.getToId());
            if (withGroup != null) {
                withGroup.mLastContent = "";
                this.a.ag.update(withGroup);
                return;
            }
            return;
        }
        ChatMsg chatMsg = (ChatMsg) list.get(0);
        ContactListItem withGroup2 = this.a.ag.getWithGroup(this.a.getToId());
        if (withGroup2 != null && chatMsg != null) {
            withGroup2.msgId = chatMsg.dbid;
            withGroup2.inout = chatMsg.inout;
            withGroup2.mimeType = chatMsg.type;
            withGroup2.mLastContent = chatMsg.getGroupMsgTips();
            withGroup2.mLastUpdateTime = chatMsg.time;
            this.a.ag.update(withGroup2);
        }
    }
}
