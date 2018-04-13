package qsbk.app.service;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.model.UserInfo;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.SharePreferenceUtils;

class h extends Thread {
    final /* synthetic */ JSONObject a;
    final /* synthetic */ VerifyUserInfoService b;

    h(VerifyUserInfoService verifyUserInfoService, String str, JSONObject jSONObject) {
        this.b = verifyUserInfoService;
        this.a = jSONObject;
        super(str);
    }

    public void run() {
        try {
            String string;
            JSONObject jSONObject = this.a.getJSONObject("user");
            UserInfo userInfo = new UserInfo(SharePreferenceUtils.getSharePreferencesValue("loginUser"));
            userInfo.userId = jSONObject.getString("id");
            userInfo.userName = jSONObject.getString(QsbkDatabase.LOGIN);
            userInfo.userIcon = jSONObject.getString("icon");
            if (jSONObject.has("emotion")) {
                string = jSONObject.getString("emotion");
                if (TextUtils.isEmpty(string) || string.equals("null")) {
                    string = "";
                }
                userInfo.emotion = string;
            }
            if (jSONObject.has("bg")) {
                string = jSONObject.getString("bg");
                if (TextUtils.isEmpty(string) || string.equals("null")) {
                    string = "";
                }
                userInfo.bg = string;
            }
            if (jSONObject.has("email")) {
                string = jSONObject.getString("email");
                if (TextUtils.isEmpty(string) || string.equals("null")) {
                    string = "";
                }
                userInfo.email = string;
            }
            QsbkApp.currentUser = userInfo;
            SharePreferenceUtils.setSharePreferencesValue("loginUser", userInfo.toString());
            DebugUtil.debug("新用户保存成功");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
