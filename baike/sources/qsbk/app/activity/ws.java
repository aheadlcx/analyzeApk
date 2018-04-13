package qsbk.app.activity;

import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.utils.SharePreferenceUtils;

class ws implements SimpleCallBack {
    final /* synthetic */ MyInfoActivity a;

    ws(MyInfoActivity myInfoActivity) {
        this.a = myInfoActivity;
    }

    public void onSuccess(JSONObject jSONObject) {
        this.a.bQ = this.a.bP;
        QsbkApp.currentUser.bg = String.valueOf(this.a.bP);
        SharePreferenceUtils.setSharePreferencesValue("loginUser", QsbkApp.currentUser.toString());
    }

    public void onFailure(int i, String str) {
    }
}
