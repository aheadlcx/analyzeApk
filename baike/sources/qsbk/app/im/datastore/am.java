package qsbk.app.im.datastore;

import qsbk.app.exception.QiushibaikeException;
import qsbk.app.im.ChatMsg;
import qsbk.app.utils.image.issue.TaskExecutor.SimpleTask;

class am extends SimpleTask {
    final /* synthetic */ ChatMsg a;
    final /* synthetic */ Callback b;
    final /* synthetic */ DraftStore c;

    am(DraftStore draftStore, ChatMsg chatMsg, Callback callback) {
        this.c = draftStore;
        this.a = chatMsg;
        this.b = callback;
    }

    public Object proccess() throws QiushibaikeException {
        return Long.valueOf(this.c.insert(this.a));
    }

    public void success(Object obj) {
        if (this.b != null) {
            this.b.onFinished((Long) obj);
        }
    }

    public void fail(Throwable th) {
        if (this.b != null) {
            this.b.onFailure(th);
        }
    }
}
