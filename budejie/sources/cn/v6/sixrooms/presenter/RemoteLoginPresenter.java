package cn.v6.sixrooms.presenter;

import cn.v6.sixrooms.engine.GetAuthCodeEngine;
import cn.v6.sixrooms.engine.OtherPlaceLoginEngine;
import cn.v6.sixrooms.engine.UserInfoEngine;
import cn.v6.sixrooms.mvp.interfaces.IRemoteLoginRunnable;

public class RemoteLoginPresenter {
    private IRemoteLoginRunnable a;
    private OtherPlaceLoginEngine b = new OtherPlaceLoginEngine(new m(this));
    private GetAuthCodeEngine c = new GetAuthCodeEngine(new n(this));
    private UserInfoEngine d = new UserInfoEngine(new o(this));
    public boolean isRemoteLogin;
    public String msg;
    public boolean qqRemoteLogin;

    public RemoteLoginPresenter(IRemoteLoginRunnable iRemoteLoginRunnable) {
        this.a = iRemoteLoginRunnable;
    }

    public void getLoginVerifyCode() {
        this.a.showLoading(false);
        this.c.getLoginVerifyCode(this.a.getTicket(), "login");
    }

    public void remoteLogin() {
        this.a.showLoading(true);
        this.b.otherPlaceLogin(this.a.getCode(), this.a.getTicket());
    }

    public void onDetach() {
        this.isRemoteLogin = false;
    }
}
