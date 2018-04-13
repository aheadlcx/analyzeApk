package cn.v6.sixrooms.ui.phone;

import cn.v6.sixrooms.engine.AuthCodeEngine.GetAuthCodeCallBack;
import cn.v6.sixrooms.widgets.phone.MsgVerifyDialog;

final class al implements GetAuthCodeCallBack {
    final /* synthetic */ MsgVerifyDialog a;
    final /* synthetic */ ExchangeBean6ToCoin6Activity b;

    al(ExchangeBean6ToCoin6Activity exchangeBean6ToCoin6Activity, MsgVerifyDialog msgVerifyDialog) {
        this.b = exchangeBean6ToCoin6Activity;
        this.a = msgVerifyDialog;
    }

    public final void success(String str) {
        this.a.notifyRecieveCode(str);
        this.a.show();
    }
}
