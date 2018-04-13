package cn.v6.sixrooms.presenter;

import cn.v6.sixrooms.presenter.LoginPresenter.GtAppDlgTask;
import com.a.a.d.a;
import org.json.JSONObject;

final class g implements a {
    final /* synthetic */ GtAppDlgTask a;

    g(GtAppDlgTask gtAppDlgTask) {
        this.a = gtAppDlgTask;
    }

    public final void gtResult(boolean z, String str) {
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                this.a.a.b.post(new h(this, jSONObject.getString("geetest_challenge"), jSONObject.getString("geetest_validate"), jSONObject.getString("geetest_seccode")));
            } catch (Exception e) {
                this.a.a.a.gtError(null);
            }
        }
    }

    public final void closeGt() {
    }
}
