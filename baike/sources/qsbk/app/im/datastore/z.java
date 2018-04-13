package qsbk.app.im.datastore;

import java.util.List;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.utils.image.issue.TaskExecutor.SimpleTask;

class z extends SimpleTask {
    final /* synthetic */ Callback a;
    final /* synthetic */ ContactListItemStore b;

    z(ContactListItemStore contactListItemStore, Callback callback) {
        this.b = contactListItemStore;
        this.a = callback;
    }

    public Object proccess() throws QiushibaikeException {
        return this.b.getAll();
    }

    public void success(Object obj) {
        if (this.a != null) {
            this.a.onFinished((List) obj);
        }
    }

    public void fail(Throwable th) {
        if (this.a != null) {
            this.a.onFailure(th);
        }
    }
}
