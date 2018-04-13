package cn.v6.sixrooms.pay.ui.activity;

import cn.v6.sixrooms.R;
import cn.v6.sixrooms.bean.UserBean;
import cn.v6.sixrooms.engine.UserInfoEngine.CallBack;
import cn.v6.sixrooms.utils.GlobleValue;

final class r implements CallBack {
    final /* synthetic */ BanklineActivity a;

    r(BanklineActivity banklineActivity) {
        this.a = banklineActivity;
    }

    public final void handleInfo(UserBean userBean) {
        GlobleValue.setUserBean(userBean);
        this.a.a();
    }

    public final void error(int i) {
        this.a.showToast(this.a.getResources().getString(R.string.tip_network_error_title));
    }

    public final void handleErrorInfo(String str, String str2) {
        this.a.handleErrorResult(str, str2, this.a);
    }
}
