package cn.v6.sixrooms.ui.phone;

import cn.v6.sixrooms.engine.AuthCodeEngine$VerifyAuthCodeCallBack;
import cn.v6.sixrooms.utils.DialogUtils;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;

final class ak implements AuthCodeEngine$VerifyAuthCodeCallBack {
    final /* synthetic */ aj a;

    ak(aj ajVar) {
        this.a = ajVar;
    }

    public final void success(String str) {
        SaveUserInfoUtils.saveUserCoinV(ExchangeBean6ToCoin6Activity.a(this.a.c), ExchangeBean6ToCoin6Activity.k(this.a.c), str);
        this.a.b.dismiss();
        new DialogUtils(ExchangeBean6ToCoin6Activity.a(this.a.c)).createDiaglog("验证成功").show();
    }
}
