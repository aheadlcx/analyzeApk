package qsbk.app.im.datastore;

import qsbk.app.exception.QiushibaikeException;
import qsbk.app.im.ChatMsg;
import qsbk.app.utils.image.issue.TaskExecutor.SimpleTask;

class ao extends SimpleTask {
    final /* synthetic */ ChatMsg a;
    final /* synthetic */ Callback b;
    final /* synthetic */ DraftStore c;

    ao(DraftStore draftStore, ChatMsg chatMsg, Callback callback) {
        this.c = draftStore;
        this.a = chatMsg;
        this.b = callback;
    }

    public Object proccess() throws QiushibaikeException {
        return Integer.valueOf(this.c.update(this.a));
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
