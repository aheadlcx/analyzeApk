package qsbk.app.pay.ui;

import qsbk.app.core.net.Callback;
import qsbk.app.core.net.response.BaseResponse;
import qsbk.app.core.utils.AppUtils;

class g extends Callback {
    final /* synthetic */ PayActivity a;

    g(PayActivity payActivity) {
        this.a = payActivity;
    }

    public void onSuccess(BaseResponse baseResponse) {
        this.a.d = baseResponse.getSimpleDataLong("coin");
        this.a.b.setBanlance(this.a.d);
        AppUtils.getInstance().getUserInfoProvider().setBalance(this.a.d);
    }
}
