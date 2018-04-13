package qsbk.app.adapter;

import qsbk.app.http.SimpleCallBack;
import qsbk.app.http.SimpleHttpTask;

class bk extends SimpleHttpTask {
    final /* synthetic */ ContactQiuYouAdapter a;

    bk(ContactQiuYouAdapter contactQiuYouAdapter, String str, SimpleCallBack simpleCallBack) {
        this.a = contactQiuYouAdapter;
        super(str, simpleCallBack);
    }

    protected void a() {
        super.a();
        this.a.i.show();
    }
}
