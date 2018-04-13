package qsbk.app.activity.pay;

import qsbk.app.utils.ToastUtil;

class as implements Runnable {
    final /* synthetic */ ar a;

    as(ar arVar) {
        this.a = arVar;
    }

    public void run() {
        ToastUtil.Short("支付过程中出现了问题，请重新支付");
    }
}
