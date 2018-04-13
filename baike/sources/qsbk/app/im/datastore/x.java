package qsbk.app.im.datastore;

import qsbk.app.exception.QiushibaikeException;
import qsbk.app.utils.image.issue.TaskExecutor.SimpleTask;

class x extends SimpleTask {
    final /* synthetic */ String a;
    final /* synthetic */ int b;
    final /* synthetic */ Callback c;
    final /* synthetic */ ContactListItemStore d;

    x(ContactListItemStore contactListItemStore, String str, int i, Callback callback) {
        this.d = contactListItemStore;
        this.a = str;
        this.b = i;
        this.c = callback;
    }

    public Object proccess() throws QiushibaikeException {
        return Integer.valueOf(this.d.delete(this.a, this.b));
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
