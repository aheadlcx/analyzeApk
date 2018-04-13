package cn.v6.sixrooms.login.manager;

import cn.v6.sixrooms.login.interfaces.GetVerifyCodeCallback;
import java.util.Map;

final class a implements GetVerifyCodeCallback {
    final /* synthetic */ RegisterManager a;

    a(RegisterManager registerManager) {
        this.a = registerManager;
    }

    public final void getVerifyCodeSuccess(Map<String, String> map) {
    }

    public final void getVerifyCodeSuccess(String str) {
        RegisterManager.a(this.a).getAuthCodeSuccess(str);
    }

    public final void error(int i) {
        RegisterManager.a(this.a).error(i);
    }

    public final void handleErrorInfo(String str, String str2) {
        RegisterManager.a(this.a).handleErrorInfo(str, str2);
    }
}
