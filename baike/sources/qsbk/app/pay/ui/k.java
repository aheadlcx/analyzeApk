package qsbk.app.pay.ui;

import qsbk.app.core.utils.ToastUtil;

class k implements Runnable {
    final /* synthetic */ j a;

    k(j jVar) {
        this.a = jVar;
    }

    public void run() {
        ToastUtil.Long("调用支付接口失败，请稍后尝试");
    }
}
