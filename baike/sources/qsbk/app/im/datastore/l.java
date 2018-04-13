package qsbk.app.im.datastore;

import java.util.List;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.utils.image.issue.TaskExecutor.SimpleTask;

class l extends SimpleTask {
    final /* synthetic */ int a;
    final /* synthetic */ int b;
    final /* synthetic */ String c;
    final /* synthetic */ String d;
    final /* synthetic */ Callback e;
    final /* synthetic */ BaseChatMsgStore f;

    l(BaseChatMsgStore baseChatMsgStore, int i, int i2, String str, String str2, Callback callback) {
        this.f = baseChatMsgStore;
        this.a = i;
        this.b = i2;
        this.c = str;
        this.d = str2;
        this.e = callback;
    }

    public Object proccess() throws QiushibaikeException {
        return this.f.get(this.a, this.b, this.c, this.d);
    }

    public void success(Object obj) {
        if (this.e != null) {
            this.e.onFinished((List) obj);
        }
    }

    public void fail(Throwable th) {
        if (this.e != null) {
            this.e.onFailure(th);
        }
    }
}
