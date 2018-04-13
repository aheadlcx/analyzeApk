package cn.v6.sixrooms.presenter;

import cn.v6.sixrooms.engine.ResetPwdEngine;
import cn.v6.sixrooms.mvp.interfaces.IResetPasswordRunnable;

public class ResetPasswordPresenter {
    private IResetPasswordRunnable a;
    private ResetPwdEngine b = new ResetPwdEngine(new p(this));

    public ResetPasswordPresenter(IResetPasswordRunnable iResetPasswordRunnable) {
        this.a = iResetPasswordRunnable;
    }

    public void resetPassword() {
        this.a.showLoading();
        this.b.resetPwd(this.a.getUserName(), this.a.getMobileNumber(), this.a.getAuthCode(), this.a.getUid(), this.a.getPassword(), this.a.getConfirmPassword());
    }
}
