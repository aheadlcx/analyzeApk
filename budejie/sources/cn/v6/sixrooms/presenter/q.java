package cn.v6.sixrooms.presenter;

import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.engine.UnbundlePhoneEngine.CallBack;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;

final class q implements CallBack {
    final /* synthetic */ UnBindMobilePresenter a;

    q(UnBindMobilePresenter unBindMobilePresenter) {
        this.a = unBindMobilePresenter;
    }

    public final void unbundleSucceed(String str) {
        this.a.a.hideLoading();
        this.a.d.getUserInfo(SaveUserInfoUtils.getEncpass(V6Coop.getInstance().getContext()), "");
    }

    public final void handleErrorInfo(String str, String str2) {
        this.a.a.hideLoading();
        this.a.a.handleErrorInfo(str, str2);
    }

    public final void error(int i) {
        this.a.a.hideLoading();
        this.a.a.error(i);
    }
}
