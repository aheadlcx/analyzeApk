package qsbk.app.pay.ui;

import qsbk.app.core.utils.ToastUtil;

class l implements Runnable {
    final /* synthetic */ j a;

    l(j jVar) {
        this.a = jVar;
    }

    public void run() {
        ToastUtil.Long("支付过程中出现了问题，请重新支付");
    }
}
