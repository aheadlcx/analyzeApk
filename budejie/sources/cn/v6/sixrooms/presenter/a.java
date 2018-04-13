package cn.v6.sixrooms.presenter;

import cn.v6.sixrooms.engine.GetAuthCodeEngine.CallBack;
import cn.v6.sixrooms.utils.ToastUtils;

final class a implements CallBack {
    final /* synthetic */ FindUserOrPwdPresenter a;

    a(FindUserOrPwdPresenter findUserOrPwdPresenter) {
        this.a = findUserOrPwdPresenter;
    }

    public final void verifyCodeSucceed(String str) {
        FindUserOrPwdPresenter.a(this.a).hideLoading();
        ToastUtils.showToast(str);
    }

    public final void handleErrorInfo(String str, String str2) {
        FindUserOrPwdPresenter.a(this.a).hideLoading();
        FindUserOrPwdPresenter.a(this.a).handleErrorInfo(str, str2);
    }

    public final void error(int i) {
        FindUserOrPwdPresenter.a(this.a).hideLoading();
        FindUserOrPwdPresenter.a(this.a).error(i);
    }
}
