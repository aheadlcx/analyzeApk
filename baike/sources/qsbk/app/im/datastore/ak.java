package qsbk.app.im.datastore;

import qsbk.app.exception.QiushibaikeException;
import qsbk.app.im.ChatMsg;
import qsbk.app.utils.image.issue.TaskExecutor.SimpleTask;

class ak extends SimpleTask {
    final /* synthetic */ String a;
    final /* synthetic */ int b;
    final /* synthetic */ Callback c;
    final /* synthetic */ DraftStore d;

    ak(DraftStore draftStore, String str, int i, Callback callback) {
        this.d = draftStore;
        this.a = str;
        this.b = i;
        this.c = callback;
    }

    public Object proccess() throws QiushibaikeException {
        return this.d.get(this.a, this.b);
    }

    public void success(Object obj) {
        if (this.c != null) {
            this.c.onFinished((ChatMsg) obj);
        }
    }

    public void fail(Throwable th) {
        if (this.c != null) {
            this.c.onFailure(th);
        }
    }
}
