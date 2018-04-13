package qsbk.app.im.datastore;

import qsbk.app.exception.QiushibaikeException;
import qsbk.app.utils.image.issue.TaskExecutor.SimpleTask;

class r extends SimpleTask {
    final /* synthetic */ Callback a;
    final /* synthetic */ BaseChatMsgStore b;

    r(BaseChatMsgStore baseChatMsgStore, Callback callback) {
        this.b = baseChatMsgStore;
        this.a = callback;
    }

    public Object proccess() throws QiushibaikeException {
        return Integer.valueOf(this.b.deleteAllMessages());
    }

    public void success(Object obj) {
        if (this.a != null) {
            this.a.onFinished((Integer) obj);
        }
    }

    public void fail(Throwable th) {
        if (this.a != null) {
            this.a.onFailure(th);
        }
    }
}
