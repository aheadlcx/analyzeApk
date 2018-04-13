package qsbk.app.cafe.plugin;

import android.app.Activity;
import android.content.Intent;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.activity.ActionBarLoginActivity;
import qsbk.app.activity.MyInfoActivity;
import qsbk.app.activity.SimpleWebActivity;
import qsbk.app.core.web.plugin.Callback;
import qsbk.app.core.web.plugin.Plugin;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.http.EncryptDecryptUtil;
import qsbk.app.model.UserInfo;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.HttpClient;

public class AccountPlugin extends Plugin {
    public static final String MODEL = "account";
    private static final String[] a = new String[]{"info", "open_user_profile", QsbkDatabase.LOGIN, "getInfo"};
    private static JSONObject c;
    private int d = 123;
    private int e = 124;
    private Callback f = null;

    public void exec(String str, JSONObject jSONObject, Callback callback) throws JSONException {
        Intent intent;
        if (a[0].equalsIgnoreCase(str)) {
            QsbkApp.getInstance();
            if (QsbkApp.currentUser != null) {
                callback.sendResult(0, "", a(QsbkApp.currentUser));
                Activity curActivity = this.b.getCurActivity();
                if (curActivity instanceof SimpleWebActivity) {
                    ((SimpleWebActivity) curActivity).reloadUrl();
                    return;
                }
                return;
            }
            intent = new Intent(this.b.getCurActivity(), ActionBarLoginActivity.class);
            this.f = callback;
            this.b.startActivityForResult(this, intent, this.e);
        } else if (a[1].equalsIgnoreCase(str)) {
            if (this.b.getCurActivity() != null) {
                MyInfoActivity.launch(this.b.getCurActivity(), jSONObject.getString("uid"), MyInfoActivity.FANS_ORIGINS[5]);
                callback.sendResult(0, "activity start success", "");
                return;
            }
            callback.sendResult(1, "activity is not available", "");
        } else if (a[2].equalsIgnoreCase(str)) {
            intent = new Intent(this.b.getCurActivity(), ActionBarLoginActivity.class);
            this.f = callback;
            this.b.startActivityForResult(this, intent, this.d);
        } else if (a[3].equalsIgnoreCase(str)) {
            this.f = callback;
            if (QsbkApp.currentUser != null) {
                callback.sendResult(0, "", a(QsbkApp.currentUser));
            } else {
                callback.sendResult(1, "用户未登录", "");
            }
        }
    }

    private JSONObject a(UserInfo userInfo) throws JSONException {
        if (c == null) {
            c = EncryptDecryptUtil.processJsonEncrypt(DeviceUtils.getAndroidId());
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("token", userInfo.token);
        jSONObject.put("uid", userInfo.userId);
        jSONObject.put("user_name", userInfo.userName);
        jSONObject.put("user_icon", userInfo.userIcon);
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("Source", "android_" + Constants.localVersionName);
        jSONObject2.put("Uuid", DeviceUtils.getAndroidId());
        jSONObject2.put("imageUrl", c);
        jSONObject2.put("UserAgent", HttpClient.getUserAgent());
        jSONObject.put("system_info", jSONObject2);
        jSONObject.put("features", "jsprompt_call,open_activity,location,imgview");
        return jSONObject;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == this.d) {
            if (this.f != null) {
                if (i2 == -1) {
                    this.f.sendResult(0, "", "");
                } else {
                    this.f.sendResult(1, "", "");
                }
                this.f = null;
            }
        } else if (i == this.e && this.f != null && i2 == -1) {
            try {
                this.f.sendResult(0, "", a(QsbkApp.currentUser));
            } catch (JSONException e) {
                e.printStackTrace();
                this.f.sendResult(1, "", "");
            }
            this.f = null;
        }
    }

    public void onDestroy() {
    }
}
