package qsbk.app.im.datastore;

import java.util.List;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.utils.image.issue.TaskExecutor.SimpleTask;

class al extends SimpleTask {
    final /* synthetic */ int a;
    final /* synthetic */ String[] b;
    final /* synthetic */ Callback c;
    final /* synthetic */ DraftStore d;

    al(DraftStore draftStore, int i, String[] strArr, Callback callback) {
        this.d = draftStore;
        this.a = i;
        this.b = strArr;
        this.c = callback;
    }

    public Object proccess() throws QiushibaikeException {
        return this.d.get(this.a, this.b);
    }

    public void success(Object obj) {
        if (this.c != null) {
            this.c.onFinished((List) obj);
        }
    }

    public void fail(Throwable th) {
        if (this.c != null) {
            this.c.onFailure(th);
        }
    }
}
