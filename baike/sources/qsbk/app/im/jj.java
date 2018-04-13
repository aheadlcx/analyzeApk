package qsbk.app.im;

import qsbk.app.model.GroupNotice;
import qsbk.app.utils.GroupNotifier;

class jj implements Runnable {
    final /* synthetic */ GroupNotice a;
    final /* synthetic */ UserChatManager b;

    jj(UserChatManager userChatManager, GroupNotice groupNotice) {
        this.b = userChatManager;
        this.a = groupNotice;
    }

    public void run() {
        GroupNotifier.notify(this.a.gid, 2);
    }
}
