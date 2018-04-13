package qsbk.app.activity;

import qsbk.app.http.SimpleCallBack;
import qsbk.app.http.SimpleHttpTask;

class vo extends SimpleHttpTask {
    final /* synthetic */ MyInfoActivity a;

    vo(MyInfoActivity myInfoActivity, String str, SimpleCallBack simpleCallBack) {
        this.a = myInfoActivity;
        super(str, simpleCallBack);
    }

    protected void a() {
        super.a();
        this.a.ca.show();
    }
}
