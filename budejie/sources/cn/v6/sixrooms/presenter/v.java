package cn.v6.sixrooms.presenter;

import cn.v6.sixrooms.engine.GetAuthCodeEngine.CallBack;
import cn.v6.sixrooms.utils.ToastUtils;

final class v implements CallBack {
    final /* synthetic */ VerifyPhonePresenter a;

    v(VerifyPhonePresenter verifyPhonePresenter) {
        this.a = verifyPhonePresenter;
    }

    public final void handleErrorInfo(String str, String str2) {
        this.a.a.hideLoading();
        this.a.a.handleErrorResult(str, str2);
    }

    public final void error(int i) {
        this.a.a.hideLoading();
        this.a.a.error(i);
    }

    public final void verifyCodeSucceed(String str) {
        ToastUtils.showToast(str);
        this.a.a.hideLoading();
        this.a.a.verifyPhoneSucceed();
    }
}
