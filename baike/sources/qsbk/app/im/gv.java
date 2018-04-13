package qsbk.app.im;

import qsbk.app.im.datastore.BaseChatMsgStore;
import qsbk.app.utils.GroupMsgUtils;

class gv implements Runnable {
    final /* synthetic */ BaseChatMsgStore a;
    final /* synthetic */ ContactListItem b;
    final /* synthetic */ IMMessageListFragment c;

    gv(IMMessageListFragment iMMessageListFragment, BaseChatMsgStore baseChatMsgStore, ContactListItem contactListItem) {
        this.c = iMMessageListFragment;
        this.a = baseChatMsgStore;
        this.b = contactListItem;
    }

    public void run() {
        boolean z = true;
        try {
            int unReadCountWith = this.a.getUnReadCountWith(this.b.id);
            if (unReadCountWith > 0) {
                BaseChatMsgStore baseChatMsgStore = this.a;
                unReadCountWith = -unReadCountWith;
                if (this.b.isGroupMsg() && !GroupMsgUtils.isOpen(this.b.id, true)) {
                    z = false;
                }
                baseChatMsgStore.addUserTotalMsgUnread(unReadCountWith, z);
            }
            this.a.deleteMessagesWith(this.b.id);
            this.c.n.delete(this.b.id, this.b.type);
        } finally {
            this.c.f.post(new gw(this));
        }
    }
}
