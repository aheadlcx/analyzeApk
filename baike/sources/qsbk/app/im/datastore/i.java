package qsbk.app.im.datastore;

import java.util.List;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.utils.image.issue.TaskExecutor.SimpleTask;

class i extends SimpleTask {
    final /* synthetic */ List a;
    final /* synthetic */ Callback b;
    final /* synthetic */ BaseChatMsgStore c;

    i(BaseChatMsgStore baseChatMsgStore, List list, Callback callback) {
        this.c = baseChatMsgStore;
        this.a = list;
        this.b = callback;
    }

    public Object proccess() throws QiushibaikeException {
        this.c.insert(this.a);
        return null;
    }

    public void success(Object obj) {
        if (this.b != null) {
            this.b.onFinished(null);
        }
    }

    public void fail(Throwable th) {
        if (this.b != null) {
            this.b.onFailure(th);
        }
    }
}
