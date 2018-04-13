package qsbk.app.pay.ui;

import qsbk.app.core.net.Callback;
import qsbk.app.core.net.response.BaseResponse;
import qsbk.app.core.utils.AppUtils;

class c extends Callback {
    final /* synthetic */ PayActivity a;

    c(PayActivity payActivity) {
        this.a = payActivity;
    }

    public void onSuccess(BaseResponse baseResponse) {
        AppUtils.getInstance().getUserInfoProvider().setBalance(baseResponse.getSimpleDataLong("coin"));
        this.a.d = AppUtils.getInstance().getUserInfoProvider().getBalance();
        this.a.b.setBanlance(this.a.d);
    }
}
