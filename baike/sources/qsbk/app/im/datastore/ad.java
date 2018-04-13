package qsbk.app.im.datastore;

import qsbk.app.exception.QiushibaikeException;
import qsbk.app.utils.image.issue.TaskExecutor.SimpleTask;

class ad extends SimpleTask {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ long c;
    final /* synthetic */ int d;
    final /* synthetic */ Callback e;
    final /* synthetic */ ContactListItemStore f;

    ad(ContactListItemStore contactListItemStore, String str, String str2, long j, int i, Callback callback) {
        this.f = contactListItemStore;
        this.a = str;
        this.b = str2;
        this.c = j;
        this.d = i;
        this.e = callback;
    }

    public Object proccess() throws QiushibaikeException {
        return Integer.valueOf(this.f.updateData(this.a, this.b, this.c, this.d));
    }

    public void success(Object obj) {
        if (this.e != null) {
            this.e.onFinished((Integer) obj);
        }
    }

    public void fail(Throwable th) {
        if (this.e != null) {
            this.e.onFailure(th);
        }
    }
}
