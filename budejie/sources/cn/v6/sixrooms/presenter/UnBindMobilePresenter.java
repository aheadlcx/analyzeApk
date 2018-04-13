package cn.v6.sixrooms.presenter;

import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.engine.GetAuthCodeEngine;
import cn.v6.sixrooms.engine.UnbundlePhoneEngine;
import cn.v6.sixrooms.engine.UserInfoEngine;
import cn.v6.sixrooms.mvp.interfaces.IUnBindMobileRunnable;
import cn.v6.sixrooms.utils.LoginUtils;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;

public class UnBindMobilePresenter {
    private IUnBindMobileRunnable a;
    private UnbundlePhoneEngine b = new UnbundlePhoneEngine(new q(this));
    private GetAuthCodeEngine c = new GetAuthCodeEngine(new r(this));
    private UserInfoEngine d = new UserInfoEngine(new s(this));

    public UnBindMobilePresenter(IUnBindMobileRunnable iUnBindMobileRunnable) {
        this.a = iUnBindMobileRunnable;
    }

    public void getUnBindVerifyCode() {
        this.a.showLoading(true);
        if (LoginUtils.isLogin()) {
            this.c.getVerifyCode(SaveUserInfoUtils.getEncpass(V6Coop.getInstance().getContext()), "unbundle");
        }
    }

    public void unbindPhone() {
        this.a.showLoading(true);
        String str = null;
        if (LoginUtils.isLogin()) {
            str = LoginUtils.getLoginUID();
        }
        this.b.unbundlePhone(this.a.getCode(), SaveUserInfoUtils.getEncpass(V6Coop.getInstance().getContext()), str);
    }
}
