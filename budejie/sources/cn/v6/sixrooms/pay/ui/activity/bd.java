package cn.v6.sixrooms.pay.ui.activity;

import cn.v6.sixrooms.bean.UserBean;
import cn.v6.sixrooms.engine.UserInfoEngine.CallBack;
import cn.v6.sixrooms.utils.GlobleValue;
import cn.v6.sixrooms.utils.LogUtils;

final class bd implements CallBack {
    final /* synthetic */ RechargeActivity a;

    bd(RechargeActivity rechargeActivity) {
        this.a = rechargeActivity;
    }

    public final void handleInfo(UserBean userBean) {
        if (userBean != null) {
            GlobleValue.setUserBean(userBean);
            LogUtils.i("LogTool", "saveUserId:" + userBean.getId());
        }
        this.a.a();
    }

    public final void error(int i) {
        this.a.showErrorToast(i);
        this.a.finish();
    }

    public final void handleErrorInfo(String str, String str2) {
        this.a.finish();
    }
}
