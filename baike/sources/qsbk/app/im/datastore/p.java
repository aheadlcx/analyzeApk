package qsbk.app.im.datastore;

import qsbk.app.exception.QiushibaikeException;
import qsbk.app.utils.image.issue.TaskExecutor.SimpleTask;

class p extends SimpleTask {
    final /* synthetic */ long[] a;
    final /* synthetic */ Callback b;
    final /* synthetic */ BaseChatMsgStore c;

    p(BaseChatMsgStore baseChatMsgStore, long[] jArr, Callback callback) {
        this.c = baseChatMsgStore;
        this.a = jArr;
        this.b = callback;
    }

    public Object proccess() throws QiushibaikeException {
        return Integer.valueOf(this.c.deleteMessagesWith(this.a));
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
