package cn.v6.sixrooms.presenter;

import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.engine.BundlePhoneEngine;
import cn.v6.sixrooms.engine.GetAuthCodeEngine;
import cn.v6.sixrooms.mvp.interfaces.IVerifyMessageRunnable;
import cn.v6.sixrooms.utils.LoginUtils;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;

public class VerifyMessagePresenter {
    private IVerifyMessageRunnable a;
    private GetAuthCodeEngine b = new GetAuthCodeEngine(new t(this));
    private BundlePhoneEngine c = new BundlePhoneEngine(new u(this));

    public VerifyMessagePresenter(IVerifyMessageRunnable iVerifyMessageRunnable) {
        this.a = iVerifyMessageRunnable;
    }

    public void bindPhone() {
        this.a.showLoading(true);
        String str = null;
        if (LoginUtils.isLogin()) {
            str = LoginUtils.getLoginUID();
        }
        this.c.bundlePhone(this.a.getPhoneNumber(), this.a.getCode(), SaveUserInfoUtils.getEncpass(V6Coop.getInstance().getContext()), str);
    }

    public void getBindVerifyCode() {
        this.a.showLoading(false);
        if (LoginUtils.isLogin()) {
            this.b.getBundleVerifyCode(this.a.getPhoneNumber(), this.a.getPassword(), SaveUserInfoUtils.getEncpass(V6Coop.getInstance().getContext()), "bundle");
        }
    }
}
