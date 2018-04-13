package qsbk.app.im.datastore;

import qsbk.app.exception.QiushibaikeException;
import qsbk.app.im.ChatMsg;
import qsbk.app.utils.image.issue.TaskExecutor.SimpleTask;

class h extends SimpleTask {
    final /* synthetic */ ChatMsg a;
    final /* synthetic */ Callback b;
    final /* synthetic */ BaseChatMsgStore c;

    h(BaseChatMsgStore baseChatMsgStore, ChatMsg chatMsg, Callback callback) {
        this.c = baseChatMsgStore;
        this.a = chatMsg;
        this.b = callback;
    }

    public Object proccess() throws QiushibaikeException {
        return Long.valueOf(this.c.insert(this.a));
    }

    public void success(Object obj) {
        this.b.onFinished((Long) obj);
    }

    public void fail(Throwable th) {
        this.b.onFailure(th);
    }
}
