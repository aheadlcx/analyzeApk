package qsbk.app.im.datastore;

import qsbk.app.exception.QiushibaikeException;
import qsbk.app.utils.image.issue.TaskExecutor.SimpleTask;

class f extends SimpleTask {
    final /* synthetic */ String[] a;
    final /* synthetic */ Callback b;
    final /* synthetic */ BaseChatMsgStore c;

    f(BaseChatMsgStore baseChatMsgStore, String[] strArr, Callback callback) {
        this.c = baseChatMsgStore;
        this.a = strArr;
        this.b = callback;
    }

    public Object proccess() throws QiushibaikeException {
        return this.c.getUnreadCountWithIds(this.a);
    }

    public void success(Object obj) {
        if (this.b != null) {
            this.b.onFinished((int[]) obj);
        }
    }

    public void fail(Throwable th) {
        if (this.b != null) {
            this.b.onFailure(th);
        }
    }
}
