package cn.v6.sixrooms.ui.phone;

import android.view.View;
import android.view.View.OnClickListener;
import cn.v6.sixrooms.engine.AuthCodeEngine;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;
import cn.v6.sixrooms.widgets.phone.MsgVerifyDialog;

final class ah implements OnClickListener {
    final /* synthetic */ MsgVerifyDialog a;
    final /* synthetic */ AuthCodeEngine b;
    final /* synthetic */ ExchangeBean6ToCoin6Activity c;

    ah(ExchangeBean6ToCoin6Activity exchangeBean6ToCoin6Activity, MsgVerifyDialog msgVerifyDialog, AuthCodeEngine authCodeEngine) {
        this.c = exchangeBean6ToCoin6Activity;
        this.a = msgVerifyDialog;
        this.b = authCodeEngine;
    }

    public final void onClick(View view) {
        this.a.switchCodeBtnClickable(false);
        this.b.getAuthCode(this.c.d, SaveUserInfoUtils.getEncpass(this.c.b), new ai(this));
    }
}
