package qsbk.app.im.datastore;

import qsbk.app.exception.QiushibaikeException;
import qsbk.app.utils.image.issue.TaskExecutor.SimpleTask;

class d extends SimpleTask {
    final /* synthetic */ int a;
    final /* synthetic */ Callback b;
    final /* synthetic */ BaseChatMsgStore c;

    d(BaseChatMsgStore baseChatMsgStore, int i, Callback callback) {
        this.c = baseChatMsgStore;
        this.a = i;
        this.b = callback;
    }

    public Object proccess() throws QiushibaikeException {
        this.c.markMessagesToReadWith(this.a);
        return null;
    }

    public void success(Object obj) {
        if (this.b != null) {
            this.b.onFinished((Integer) obj);
        }
    }

    public void fail(Throwable th) {
        if (this.b != null) {
            this.b.onFailure(th);
        }
    }
}
