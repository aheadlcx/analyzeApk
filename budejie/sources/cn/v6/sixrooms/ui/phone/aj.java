package cn.v6.sixrooms.ui.phone;

import cn.v6.sixrooms.engine.AuthCodeEngine;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;
import cn.v6.sixrooms.widgets.phone.MsgVerifyDialog;
import cn.v6.sixrooms.widgets.phone.MsgVerifyDialog.OnConfirmClickListener;

final class aj implements OnConfirmClickListener {
    final /* synthetic */ AuthCodeEngine a;
    final /* synthetic */ MsgVerifyDialog b;
    final /* synthetic */ ExchangeBean6ToCoin6Activity c;

    aj(ExchangeBean6ToCoin6Activity exchangeBean6ToCoin6Activity, AuthCodeEngine authCodeEngine, MsgVerifyDialog msgVerifyDialog) {
        this.c = exchangeBean6ToCoin6Activity;
        this.a = authCodeEngine;
        this.b = msgVerifyDialog;
    }

    public final void onClick(String str) {
        this.a.verifyAuthCode(this.c.d, SaveUserInfoUtils.getEncpass(this.c.b), str, new ak(this));
    }
}
