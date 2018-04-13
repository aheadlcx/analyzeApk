package qsbk.app.im.datastore;

import qsbk.app.exception.QiushibaikeException;
import qsbk.app.utils.image.issue.TaskExecutor.SimpleTask;

class an extends SimpleTask {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ long c;
    final /* synthetic */ Callback d;
    final /* synthetic */ DraftStore e;

    an(DraftStore draftStore, String str, String str2, long j, Callback callback) {
        this.e = draftStore;
        this.a = str;
        this.b = str2;
        this.c = j;
        this.d = callback;
    }

    public Object proccess() throws QiushibaikeException {
        return Integer.valueOf(this.e.update(this.a, this.b, this.c));
    }

    public void success(Object obj) {
        if (this.d != null) {
            this.d.onFinished((Integer) obj);
        }
    }

    public void fail(Throwable th) {
        if (this.d != null) {
            this.d.onFailure(th);
        }
    }
}
