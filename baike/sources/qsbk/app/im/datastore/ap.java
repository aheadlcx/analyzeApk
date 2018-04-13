package qsbk.app.im.datastore;

import qsbk.app.exception.QiushibaikeException;
import qsbk.app.utils.image.issue.TaskExecutor.SimpleTask;

class ap extends SimpleTask {
    final /* synthetic */ String a;
    final /* synthetic */ Callback b;
    final /* synthetic */ DraftStore c;

    ap(DraftStore draftStore, String str, Callback callback) {
        this.c = draftStore;
        this.a = str;
        this.b = callback;
    }

    public Object proccess() throws QiushibaikeException {
        return Integer.valueOf(this.c.delete(this.a));
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
