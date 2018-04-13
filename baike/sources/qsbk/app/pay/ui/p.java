package qsbk.app.pay.ui;

import qsbk.app.core.net.Callback;
import qsbk.app.core.net.response.BaseResponse;
import qsbk.app.pay.ui.PayConstants.CallBack;

final class p extends Callback {
    final /* synthetic */ CallBack a;

    p(CallBack callBack) {
        this.a = callBack;
    }

    public void onSuccess(BaseResponse baseResponse) {
        PayConstants.remix_id = baseResponse.getSimpleDataLong("user_id");
        this.a.call();
    }
}
