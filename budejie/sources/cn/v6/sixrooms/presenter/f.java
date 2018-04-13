package cn.v6.sixrooms.presenter;

import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.engine.PassportLoginEngine.CallBack;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;

final class f implements CallBack {
    final /* synthetic */ LoginPresenter a;

    f(LoginPresenter loginPresenter) {
        this.a = loginPresenter;
    }

    public final void loginSuccess(int i, String str) {
        if (this.a.a.getActivity() == null || !this.a.a.getActivity().isFinishing()) {
            this.a.a.hideLoading();
            this.a.a.hideSystemInput();
            if (i == 1000) {
                this.a.a.clearUsernamePassword();
                SaveUserInfoUtils.saveEncpass(V6Coop.getInstance().getContext(), str);
                this.a.a.loginSuccess("login");
                return;
            }
            this.a.resetPreLoginState();
            this.a.a.requestCode(i);
        }
    }

    public final void error(int i) {
        this.a.resetPreLoginState();
        this.a.a.hideLoading();
        this.a.a.loginError(i);
    }

    public final void preLogin(Boolean bool, boolean z, String str, String str2, String str3) {
        this.a.updatePreLoginState();
        this.a.a.hideLoading();
        this.a.gt = str;
        this.a.challenge = str2;
        if (!bool.booleanValue() && z) {
            this.a.a.showLoading();
            this.a.a();
        }
    }

    public final void otherPlaceLogin(String str, String str2) {
        this.a.a.hideLoading();
        this.a.a.remoteLogin(true, str, str2);
    }
}
