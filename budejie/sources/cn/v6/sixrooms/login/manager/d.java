package cn.v6.sixrooms.login.manager;

import cn.v6.sixrooms.login.interfaces.LoginClientCallback;

final class d implements LoginClientCallback {
    final /* synthetic */ RegisterManager a;

    d(RegisterManager registerManager) {
        this.a = registerManager;
    }

    public final void loginOtherPlace(String str) {
    }

    public final void loginClientSuccess(String str, String str2) {
        RegisterManager.a(this.a).loginClientSuccess(str, str2);
    }

    public final void handleErrorInfo(String str, String str2) {
        RegisterManager.a(this.a).handleErrorInfo(str, str2);
    }

    public final void error(int i) {
        RegisterManager.a(this.a).error(i);
    }
}
