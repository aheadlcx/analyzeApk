package qsbk.app.im;

import java.util.List;
import qsbk.app.utils.image.issue.Logger;

class db implements Runnable {
    final /* synthetic */ List a;
    final /* synthetic */ a b;

    db(a aVar, List list) {
        this.b = aVar;
        this.a = list;
    }

    public void run() {
        this.b.c.aj.setMessageReadedBatch(this.a);
        Logger.getInstance().debug(ConversationActivity.ad, "run", "onMsgReceived 真正刷新界面,然后设置已读..");
    }
}
