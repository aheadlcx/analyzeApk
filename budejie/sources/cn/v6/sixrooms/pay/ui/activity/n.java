package cn.v6.sixrooms.pay.ui.activity;

import cn.v6.sixrooms.bean.UserBean;
import cn.v6.sixrooms.engine.UserInfoEngine.CallBack;
import cn.v6.sixrooms.utils.GlobleValue;
import cn.v6.sixrooms.utils.LogUtils;

final class n implements CallBack {
    final /* synthetic */ AlipayActivity a;

    n(AlipayActivity alipayActivity) {
        this.a = alipayActivity;
    }

    public final void handleInfo(UserBean userBean) {
        GlobleValue.setUserBean(userBean);
        LogUtils.i("AlipayActivity", "获取到用户信息, 开始更新用户信息");
        this.a.a();
    }

    public final void error(int i) {
    }

    public final void handleErrorInfo(String str, String str2) {
        this.a.handleErrorResult(str, str2, this.a);
    }
}
