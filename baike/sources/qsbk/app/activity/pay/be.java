package qsbk.app.activity.pay;

import qsbk.app.utils.ToastUtil;

class be implements a {
    final /* synthetic */ String a;
    final /* synthetic */ LaiseePaymentActivity b;

    be(LaiseePaymentActivity laiseePaymentActivity, String str) {
        this.b = laiseePaymentActivity;
        this.a = str;
    }

    public void onLaiseeIdGet() {
        this.b.a(0, "输入密码", this.b.x, "pocket", this.a);
    }

    public void onError(String str) {
        ToastUtil.Short(str);
    }
}
