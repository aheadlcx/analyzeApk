package qsbk.app.activity.pay;

import qsbk.app.model.Payment;
import qsbk.app.utils.ToastUtil;

class al implements a {
    final /* synthetic */ Payment a;
    final /* synthetic */ LaiseePaymentActivity b;

    al(LaiseePaymentActivity laiseePaymentActivity, Payment payment) {
        this.b = laiseePaymentActivity;
        this.a = payment;
    }

    public void onLaiseeIdGet() {
        switch (this.a.mId) {
            case 2:
                this.b.h();
                return;
            case 3:
                this.b.i();
                return;
            default:
                return;
        }
    }

    public void onError(String str) {
        this.b.k();
        ToastUtil.Short(str);
    }
}
