package cn.v6.sixrooms.login.manager;

import com.a.a.d.a;
import org.json.JSONObject;

final class e implements a {
    final /* synthetic */ RegisterManager a;

    e(RegisterManager registerManager) {
        this.a = registerManager;
    }

    public final void gtResult(boolean z, String str) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                RegisterManager.e(this.a).setChallenge(jSONObject.getString("geetest_challenge"));
                RegisterManager.e(this.a).setValidate(jSONObject.getString("geetest_validate"));
                RegisterManager.e(this.a).setSeccode(jSONObject.getString("geetest_seccode"));
                RegisterManager.c(this.a).post(new f(this));
            } catch (Exception e) {
                e.printStackTrace();
                RegisterManager.a(this.a).error(1007);
            }
        }
    }

    public final void closeGt() {
    }
}
