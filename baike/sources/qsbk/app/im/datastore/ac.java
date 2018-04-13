package qsbk.app.im.datastore;

import qsbk.app.exception.QiushibaikeException;
import qsbk.app.im.ContactListItem;
import qsbk.app.utils.image.issue.TaskExecutor.SimpleTask;

class ac extends SimpleTask {
    final /* synthetic */ ContactListItem a;
    final /* synthetic */ Callback b;
    final /* synthetic */ ContactListItemStore c;

    ac(ContactListItemStore contactListItemStore, ContactListItem contactListItem, Callback callback) {
        this.c = contactListItemStore;
        this.a = contactListItem;
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
