package qsbk.app.activity.pay;

import qsbk.app.utils.ToastUtil;

class g implements Runnable {
    final /* synthetic */ f a;

    g(f fVar) {
        this.a = fVar;
    }

    public void run() {
        ToastUtil.Short("支付过程中出现了问题，请重新支付");
    }
}
