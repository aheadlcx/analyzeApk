package qsbk.app.im.datastore;

import qsbk.app.exception.QiushibaikeException;
import qsbk.app.utils.image.issue.TaskExecutor.SimpleTask;

class c extends SimpleTask {
    final /* synthetic */ long a;
    final /* synthetic */ int b;
    final /* synthetic */ Callback c;
    final /* synthetic */ BaseChatMsgStore d;

    c(BaseChatMsgStore baseChatMsgStore, long j, int i, Callback callback) {
        this.d = baseChatMsgStore;
        this.a = j;
        this.b = i;
        this.c = callback;
    }

    public Object proccess() throws QiushibaikeException {
        return Integer.valueOf(this.d.updateMessageState(this.a, this.b));
    }

    public void success(Object obj) {
        if (this.c != null) {
            this.c.onFinished((Integer) obj);
        }
    }

    public void fail(Throwable th) {
        if (this.c != null) {
            this.c.onFailure(th);
        }
    }
}
