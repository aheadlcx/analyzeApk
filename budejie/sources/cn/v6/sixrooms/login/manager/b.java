package cn.v6.sixrooms.login.manager;

import cn.v6.sixrooms.login.engines.GtAuthEngine.CallBack;

final class b implements CallBack {
    final /* synthetic */ RegisterManager a;

    b(RegisterManager registerManager) {
        this.a = registerManager;
    }

    public final void success(int i, String str, String str2) {
        if (i == 0) {
            RegisterManager.a(this.a, false);
            if (RegisterManager.b(this.a) != null) {
                RegisterManager.b(this.a).startRunCountdown();
                return;
            }
            return;
        }
        RegisterManager.a(this.a, str);
        RegisterManager.b(this.a, str2);
        RegisterManager.c(this.a).post(this.a);
    }

    public final void serverError(String str, String str2) {
        RegisterManager.a(this.a).handleErrorInfo(str, str2);
    }

    public final void phoneError(int i) {
        RegisterManager.a(this.a).error(i);
    }
}
