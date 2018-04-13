package cn.v6.sixrooms.presenter;

import cn.v6.sixrooms.engine.BundlePhoneEngine.CallBack;

final class u implements CallBack {
    final /* synthetic */ VerifyMessagePresenter a;

    u(VerifyMessagePresenter verifyMessagePresenter) {
        this.a = verifyMessagePresenter;
    }

    public final void handleErrorInfo(String str, String str2) {
        this.a.a.hideLoading();
        this.a.a.handleErrorInfo(str, str2);
    }

    public final void error(int i) {
        this.a.a.hideLoading();
        this.a.a.error(i);
    }

    public final void bundleSucceed(String str, String str2) {
        this.a.a.hideLoading();
        this.a.a.bindSucceed();
    }
}
