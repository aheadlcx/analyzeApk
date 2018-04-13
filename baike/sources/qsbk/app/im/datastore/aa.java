package qsbk.app.im.datastore;

import java.util.List;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.utils.image.issue.TaskExecutor.SimpleTask;

class aa extends SimpleTask {
    final /* synthetic */ int a;
    final /* synthetic */ long b;
    final /* synthetic */ boolean c;
    final /* synthetic */ Callback d;
    final /* synthetic */ ContactListItemStore e;

    aa(ContactListItemStore contactListItemStore, int i, long j, boolean z, Callback callback) {
        this.e = contactListItemStore;
        this.a = i;
        this.b = j;
        this.c = z;
        this.d = callback;
    }

    public Object proccess() throws QiushibaikeException {
        return this.e.get(this.a, this.b, this.c);
    }

    public void success(Object obj) {
        this.d.onFinished((List) obj);
    }

    public void fail(Throwable th) {
        this.d.onFailure(th);
    }
}
