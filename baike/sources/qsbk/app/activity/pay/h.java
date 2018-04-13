package qsbk.app.activity.pay;

import qsbk.app.utils.ToastUtil;

class h implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ e b;

    h(e eVar, String str) {
        this.b = eVar;
        this.a = str;
    }

    public void run() {
        ToastUtil.Short(this.a);
        this.b.a.e();
    }
}
