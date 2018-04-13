package cn.v6.sixrooms.login.manager;

import cn.v6.sixrooms.login.interfaces.PassportRegisterCallback;

final class c implements PassportRegisterCallback {
    final /* synthetic */ RegisterManager a;

    c(RegisterManager registerManager) {
        this.a = registerManager;
    }

    public final void getTicketSuccess(String str) {
        RegisterManager.d(this.a);
        RegisterManager.f(this.a).loginClientOfRegister(str, RegisterManager.e(this.a).getIdentifyingCode());
        RegisterManager.a(this.a).getTicketSuccess(str);
    }

    public final void getTicketError(int i) {
        RegisterManager.a(this.a).getTicketError(i);
    }

    public final void perRegisterSuccess(boolean z) {
        if (z) {
            RegisterManager.g(this.a).register();
        }
        RegisterManager.a(this.a).perRegisterSuccess(z);
    }

    public final void perRegisterError(int i) {
        RegisterManager.a(this.a).perRegisterError(i);
    }

    public final void error(int i) {
        RegisterManager.a(this.a).error(i);
    }
}
