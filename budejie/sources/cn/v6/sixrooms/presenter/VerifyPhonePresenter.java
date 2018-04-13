package cn.v6.sixrooms.presenter;

import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.engine.GetAuthCodeEngine;
import cn.v6.sixrooms.mvp.interfaces.IVerifyPhoneRunnable;
import cn.v6.sixrooms.utils.LoginUtils;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;

public class VerifyPhonePresenter {
    private IVerifyPhoneRunnable a;
    private GetAuthCodeEngine b = new GetAuthCodeEngine(new v(this));

    public VerifyPhonePresenter(IVerifyPhoneRunnable iVerifyPhoneRunnable) {
        this.a = iVerifyPhoneRunnable;
    }

    public void getBundleVerifyCode() {
        this.a.showLoading();
        if (LoginUtils.isLogin()) {
            this.b.getBundleVerifyCode(this.a.getPhoneNumber(), this.a.getPassword(), SaveUserInfoUtils.getEncpass(V6Coop.getInstance().getContext()), "bundle");
        }
    }
}
