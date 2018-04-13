package cn.v6.sixrooms.presenter;

import cn.v6.sixrooms.engine.ResetPwdEngine.ResetPwdCallBack;

final class p implements ResetPwdCallBack {
    final /* synthetic */ ResetPasswordPresenter a;

    p(ResetPasswordPresenter resetPasswordPresenter) {
        this.a = resetPasswordPresenter;
    }

    public final void result(String str) {
        ResetPasswordPresenter.a(this.a).hideLoading();
        ResetPasswordPresenter.a(this.a).resetPwdSucceed();
    }

    public final void handleErrorInfo(String str, String str2) {
        ResetPasswordPresenter.a(this.a).hideLoading();
        ResetPasswordPresenter.a(this.a).fail();
    }

    public final void error(int i) {
        ResetPasswordPresenter.a(this.a).hideLoading();
        ResetPasswordPresenter.a(this.a).error(i);
    }
}
