package cn.v6.sixrooms.presenter;

import cn.v6.sixrooms.bean.UserBean;
import cn.v6.sixrooms.engine.UserInfoEngine.CallBack;
import cn.v6.sixrooms.utils.GlobleValue;

final class s implements CallBack {
    final /* synthetic */ UnBindMobilePresenter a;

    s(UnBindMobilePresenter unBindMobilePresenter) {
        this.a = unBindMobilePresenter;
    }

    public final void handleInfo(UserBean userBean) {
        GlobleValue.setUserBean(userBean);
        a();
    }

    private void a() {
        this.a.a.hideLoading();
        this.a.a.unbundleSucceed();
    }

    public final void handleErrorInfo(String str, String str2) {
        a();
    }

    public final void error(int i) {
        a();
    }
}
