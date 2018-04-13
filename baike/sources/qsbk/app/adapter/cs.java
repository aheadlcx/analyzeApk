package qsbk.app.adapter;

import qsbk.app.http.SimpleCallBack;
import qsbk.app.http.SimpleHttpTask;

class cs extends SimpleHttpTask {
    final /* synthetic */ QiuYouAdapter a;

    cs(QiuYouAdapter qiuYouAdapter, String str, SimpleCallBack simpleCallBack) {
        this.a = qiuYouAdapter;
        super(str, simpleCallBack);
    }

    protected void a() {
        super.a();
        this.a.i.show();
    }
}
