package qsbk.app.im.datastore;

import java.util.List;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.utils.image.issue.TaskExecutor.SimpleTask;

class g extends SimpleTask {
    final /* synthetic */ int a;
    final /* synthetic */ int b;
    final /* synthetic */ String c;
    final /* synthetic */ Callback d;
    final /* synthetic */ BaseChatMsgStore e;

    g(BaseChatMsgStore baseChatMsgStore, int i, int i2, String str, Callback callback) {
        this.e = baseChatMsgStore;
        this.a = i;
        this.b = i2;
        this.c = str;
        this.d = callback;
    }

    public Object proccess() throws QiushibaikeException {
        return this.e.get(this.a, this.b, this.c);
    }

    public void success(Object obj) {
        if (this.d != null) {
            this.d.onFinished((List) obj);
        }
    }

    public void fail(Throwable th) {
        if (this.d != null) {
            this.d.onFailure(th);
        }
    }
}
