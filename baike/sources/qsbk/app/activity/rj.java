package qsbk.app.activity;

import java.util.List;
import qsbk.app.im.datastore.BaseChatMsgStore;
import qsbk.app.utils.comm.ArrayUtils;

class rj implements Runnable {
    final /* synthetic */ BaseChatMsgStore a;
    final /* synthetic */ String b;
    final /* synthetic */ MainActivity c;

    rj(MainActivity mainActivity, BaseChatMsgStore baseChatMsgStore, String str) {
        this.c = mainActivity;
        this.a = baseChatMsgStore;
        this.b = str;
    }

    public void run() {
        List unreadMsgIds = this.a.getUnreadMsgIds(this.b);
        this.a.markMessagesToReadWith(Integer.parseInt(this.b));
        if (!ArrayUtils.isEmpty(unreadMsgIds)) {
            this.a.addUserTotalMsgUnread(-unreadMsgIds.size(), true);
            this.c.e = null;
            this.c.d = false;
        }
    }
}
