package qsbk.app.im.datastore;

import java.util.List;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.utils.image.issue.TaskExecutor.SimpleTask;

class ab extends SimpleTask {
    final /* synthetic */ int a;
    final /* synthetic */ int b;
    final /* synthetic */ Callback c;
    final /* synthetic */ ContactListItemStore d;

    ab(ContactListItemStore contactListItemStore, int i, int i2, Callback callback) {
        this.d = contactListItemStore;
        this.a = i;
        this.b = i2;
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
