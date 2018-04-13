package qsbk.app.activity;

import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.model.UserInfo;
import qsbk.app.utils.SharePreferenceUtils;

final class qh implements SimpleCallBack {
    qh() {
    }

    public void onSuccess(JSONObject jSONObject) {
        UserInfo.updateServerJson(QsbkApp.currentUser, jSONObject);
        SharePreferenceUtils.setSharePreferencesValue("loginUser", QsbkApp.currentUser.toString());
    }

    public void onFailure(int i, String str) {
    }
}
