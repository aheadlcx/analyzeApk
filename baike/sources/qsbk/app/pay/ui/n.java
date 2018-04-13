package qsbk.app.pay.ui;

import qsbk.app.core.utils.ToastUtil;

class n implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ m b;

    n(m mVar, String str) {
        this.b = mVar;
        this.a = str;
    }

    public void run() {
        ToastUtil.Long(this.a);
    }
}
