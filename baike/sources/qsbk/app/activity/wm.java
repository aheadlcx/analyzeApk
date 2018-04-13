package qsbk.app.activity;

import qsbk.app.http.SimpleCallBack;
import qsbk.app.http.SimpleHttpTask;

class wm extends SimpleHttpTask {
    final /* synthetic */ MyInfoActivity a;

    wm(MyInfoActivity myInfoActivity, String str, SimpleCallBack simpleCallBack) {
        this.a = myInfoActivity;
        super(str, simpleCallBack);
    }

    protected void b() {
        super.b();
        this.a.ca.dismiss();
    }
}
