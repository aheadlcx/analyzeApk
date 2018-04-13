package qsbk.app.im.datastore;

import qsbk.app.im.ChatMsg;
import qsbk.app.utils.image.issue.Logger;

class bb implements Callback<Long> {
    final /* synthetic */ ChatMsg a;
    final /* synthetic */ StoreTest b;

    bb(StoreTest storeTest, ChatMsg chatMsg) {
        this.b = storeTest;
        this.a = chatMsg;
    }

    public void onFinished(Long l) {
        this.a.dbid = l.longValue();
        this.b.c.updateMessageStateAsync(this.a.dbid, 5, new a("Step3, update "));
        this.b.c.deleteMessagesWithDbIdsAsync(new a("Step4, delete "), this.a.dbid);
    }

    public void onFailure(Throwable th) {
        Logger.getInstance().debug(StoreTest.a, "testChatMsgStoreAsync", "Step1，insert one chatmsg " + th.toString());
    }
}
