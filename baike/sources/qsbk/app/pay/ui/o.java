package qsbk.app.pay.ui;

import qsbk.app.core.utils.ToastUtil;

class o implements Runnable {
    final /* synthetic */ PayActivity a;

    o(PayActivity payActivity) {
        this.a = payActivity;
    }

    public void run() {
        ToastUtil.Short("生成订单号失败，请重新充值");
    }
}
