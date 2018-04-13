package qsbk.app.activity.pay;

import qsbk.app.utils.ToastUtil;

class ao implements Runnable {
    final /* synthetic */ an a;

    ao(an anVar) {
        this.a = anVar;
    }

    public void run() {
        ToastUtil.Short("支付过程中出现了问题，请重新支付");
    }
}
