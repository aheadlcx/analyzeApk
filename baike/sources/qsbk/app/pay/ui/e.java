package qsbk.app.pay.ui;

import qsbk.app.core.utils.ConfigInfoUtil.UpdateConfigCallback;

class e implements UpdateConfigCallback {
    final /* synthetic */ PayActivity a;

    e(PayActivity payActivity) {
        this.a = payActivity;
    }

    public void onSuccess() {
        this.a.a(false);
    }

    public void onFailed(int i) {
    }

    public void onFinish() {
    }
}
