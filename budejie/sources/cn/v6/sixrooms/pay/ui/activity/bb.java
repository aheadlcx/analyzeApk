package cn.v6.sixrooms.pay.ui.activity;

import cn.v6.sixrooms.bean.UserBean;
import cn.v6.sixrooms.engine.UserInfoEngine.CallBack;
import cn.v6.sixrooms.utils.GlobleValue;

final class bb implements CallBack {
    final /* synthetic */ PayCardActivity a;

    bb(PayCardActivity payCardActivity) {
        this.a = payCardActivity;
    }

    public final void handleInfo(UserBean userBean) {
        GlobleValue.setUserBean(userBean);
        this.a.a();
    }

    public final void error(int i) {
    }

    public final void handleErrorInfo(String str, String str2) {
        this.a.handleErrorResult(str, str2, this.a);
    }
}
