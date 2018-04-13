package cn.v6.sixrooms.ui.phone;

import cn.v6.sixrooms.engine.ExchangeBean6ToCoin6Engine.CallBack;
import cn.v6.sixrooms.utils.DialogUtils;

final class aq implements CallBack {
    final /* synthetic */ ExchangeBean6ToCoin6Activity a;

    aq(ExchangeBean6ToCoin6Activity exchangeBean6ToCoin6Activity) {
        this.a = exchangeBean6ToCoin6Activity;
    }

    public final void result(String str) {
        this.a.updateUserInfo(str);
    }

    public final void handleErrorInfo(String str, String str2) {
        if ("503".equals(str)) {
            ExchangeBean6ToCoin6Activity.e(this.a);
            return;
        }
        this.a.showLoadingScreen(false);
        this.a.handleErrorResult(str, str2, this.a.b);
    }

    public final void error(int i) {
        new DialogUtils(this.a.b).createDiaglog(this.a.getErrorCodeString(i)).show();
        this.a.showLoadingScreen(false);
    }
}
