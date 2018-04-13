package cn.v6.sixrooms.presenter;

import cn.v6.sixrooms.engine.GetAuthCodeEngine.CallBack;

final class n implements CallBack {
    final /* synthetic */ RemoteLoginPresenter a;

    n(RemoteLoginPresenter remoteLoginPresenter) {
        this.a = remoteLoginPresenter;
    }

    public final void handleErrorInfo(String str, String str2) {
        this.a.a.hideLoading();
        this.a.a.handleErrorInfo(str, str2);
    }

    public final void error(int i) {
        this.a.a.hideLoading();
        this.a.a.error(i);
    }

    public final void verifyCodeSucceed(String str) {
        this.a.a.hideLoading();
        this.a.a.handleVerifySucceed(str);
    }
}
