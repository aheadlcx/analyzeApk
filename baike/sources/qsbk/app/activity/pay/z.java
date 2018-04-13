package qsbk.app.activity.pay;

import qsbk.app.utils.ToastUtil;

class z implements Runnable {
    final /* synthetic */ y a;

    z(y yVar) {
        this.a = yVar;
    }

    public void run() {
        ToastUtil.Short("支付过程中出现了问题，请重新支付");
    }
}
