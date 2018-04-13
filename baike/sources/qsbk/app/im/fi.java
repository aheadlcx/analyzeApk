package qsbk.app.im;

import java.util.List;
import qsbk.app.utils.image.issue.Logger;

class fi implements Runnable {
    final /* synthetic */ List a;
    final /* synthetic */ a b;

    fi(a aVar, List list) {
        this.b = aVar;
        this.a = list;
    }

    public void run() {
        this.b.c.af.setMessageReaded(this.a);
        Logger.getInstance().debug(GroupConversationActivity.b, "run", "onMsgReceived 真正刷新界面,设置已读");
    }
}
