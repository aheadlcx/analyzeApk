package qsbk.app.im.datastore;

import qsbk.app.exception.QiushibaikeException;
import qsbk.app.im.ContactListItem;
import qsbk.app.utils.image.issue.TaskExecutor.SimpleTask;

class ae extends SimpleTask {
    final /* synthetic */ ContactListItem a;
    final /* synthetic */ Callback b;
    final /* synthetic */ ContactListItemStore c;

    ae(ContactListItemStore contactListItemStore, ContactListItem contactListItem, Callback callback) {
        this.c = contactListItemStore;
        this.a = contactListItem;
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
