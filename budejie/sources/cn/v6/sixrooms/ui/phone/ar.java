package cn.v6.sixrooms.ui.phone;

import cn.v6.sixrooms.bean.UserBean;
import cn.v6.sixrooms.engine.UserInfoEngine.CallBack;
import cn.v6.sixrooms.utils.DialogUtils;
import cn.v6.sixrooms.utils.GlobleValue;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;

final class ar implements CallBack {
    final /* synthetic */ String a;
    final /* synthetic */ ExchangeBean6ToCoin6Activity b;

    ar(ExchangeBean6ToCoin6Activity exchangeBean6ToCoin6Activity, String str) {
        this.b = exchangeBean6ToCoin6Activity;
        this.a = str;
    }

    public final void handleInfo(UserBean userBean) {
        if (!this.b.isFinishing()) {
            GlobleValue.setUserBean(userBean);
            this.b.showLoadingScreen(false);
            new DialogUtils(this.b.b).createDiaglog(this.a).show();
            this.b.a();
        }
    }

    public final void error(int i) {
        switch (i) {
            case 1006:
                if (this.b.m < 3) {
                    this.b.n.getUserInfo(SaveUserInfoUtils.getEncpass(this.b.b), "");
                    this.b.m = this.b.m + 1;
                    return;
                }
                this.b.showLoadingScreen(false);
                new DialogUtils(this.b.b).createDiaglog(this.a).show();
                this.b.m = 0;
                return;
            default:
                this.b.showLoadingScreen(false);
                new DialogUtils(this.b.b).createDiaglog(this.a).show();
                return;
        }
    }

    public final void handleErrorInfo(String str, String str2) {
        this.b.showLoadingScreen(false);
        this.b.handleErrorResult(str, str2, this.b.b);
    }
}
