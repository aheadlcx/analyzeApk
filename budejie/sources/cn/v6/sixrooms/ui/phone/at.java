package cn.v6.sixrooms.ui.phone;

import cn.v6.sixrooms.engine.AuthCodeEngine.OnRequestFailedListener;
import cn.v6.sixrooms.widgets.phone.MsgVerifyDialog;

final class at implements OnRequestFailedListener {
    final /* synthetic */ MsgVerifyDialog a;
    final /* synthetic */ ExchangeBean6ToCoin6Activity b;

    at(ExchangeBean6ToCoin6Activity exchangeBean6ToCoin6Activity, MsgVerifyDialog msgVerifyDialog) {
        this.b = exchangeBean6ToCoin6Activity;
        this.a = msgVerifyDialog;
    }

    public final void handleErrorInfo(String str, String str2) {
        this.b.b.handleErrorResult(str, str2, this.b.b);
        if (!"401".equals(str) && this.a.isShowing()) {
            this.a.dismiss();
        }
        this.b.showLoadingScreen(false);
    }

    public final void error(int i) {
        this.b.showErrorToast(i);
        if (this.a.isShowing()) {
            this.a.dismiss();
        }
        this.b.showLoadingScreen(false);
    }
}
