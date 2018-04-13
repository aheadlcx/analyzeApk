package qsbk.app.im;

import java.util.List;
import qsbk.app.im.datastore.BaseChatMsgStore;
import qsbk.app.utils.comm.ArrayUtils;

class hf implements Runnable {
    final /* synthetic */ BaseChatMsgStore a;
    final /* synthetic */ ContactListItem b;
    final /* synthetic */ IMMessageListFragment c;

    hf(IMMessageListFragment iMMessageListFragment, BaseChatMsgStore baseChatMsgStore, ContactListItem contactListItem) {
        this.c = iMMessageListFragment;
        this.a = baseChatMsgStore;
        this.b = contactListItem;
    }

    public void run() {
        List unreadMsgIds = this.a.getUnreadMsgIds(this.b.id);
        this.a.markMessagesToReadWith(Integer.parseInt(this.b.id));
        if (!ArrayUtils.isEmpty(unreadMsgIds)) {
            this.a.addUserTotalMsgUnread(-unreadMsgIds.size(), true);
        }
    }
}
