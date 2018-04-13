package cn.v6.sixrooms.pay.ui.activity;

import cn.v6.sixrooms.bean.UserBean;
import cn.v6.sixrooms.engine.UserInfoEngine.CallBack;
import cn.v6.sixrooms.utils.GlobleValue;
import cn.v6.sixrooms.utils.LogUtils;

final class bg implements CallBack {
    final /* synthetic */ WeixinPayActivity a;

    bg(WeixinPayActivity weixinPayActivity) {
        this.a = weixinPayActivity;
    }

    public final void handleInfo(UserBean userBean) {
        GlobleValue.setUserBean(userBean);
        LogUtils.i("WeixinPayActivity", "获取到用户信息, 开始更新用户信息");
        this.a.a();
        this.a.b();
    }

    public final void error(int i) {
        this.a.b();
    }

    public final void handleErrorInfo(String str, String str2) {
        this.a.b();
        this.a.handleErrorResult(str, str2, this.a);
    }
}
