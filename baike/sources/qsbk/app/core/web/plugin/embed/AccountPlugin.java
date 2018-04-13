package qsbk.app.core.web.plugin.embed;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.core.model.User;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.DeviceUtils;
import qsbk.app.core.web.plugin.Callback;
import qsbk.app.core.web.plugin.Plugin;
import qsbk.app.core.web.ui.WebActivity;
import qsbk.app.database.QsbkDatabase;

public class AccountPlugin extends Plugin {
    public static final String MODEL = "account";
    private static final String[] a = new String[]{"info", "open_user_profile", QsbkDatabase.LOGIN, "getInfo"};
    private String c;
    private int d = 123;
    private int e = 124;
    private Callback f = null;

    public void exec(String str, JSONObject jSONObject, Callback callback) throws JSONException {
        Activity curActivity;
        if (a[0].equalsIgnoreCase(str)) {
            if (AppUtils.getInstance().getUserInfoProvider().isLogin()) {
                callback.sendResult(0, "", b(AppUtils.getInstance().getUserInfoProvider().getUser()));
                curActivity = this.b.getCurActivity();
                if (curActivity instanceof WebActivity) {
                    ((WebActivity) curActivity).reloadUrl();
                    return;
                }
                return;
            }
            AppUtils.getInstance().getUserInfoProvider().toLogin(this.b.getCurActivity(), this.e);
            this.f = callback;
        } else if (a[1].equalsIgnoreCase(str)) {
            curActivity = this.b.getCurActivity();
            if (curActivity != null) {
                try {
                    User user = new User();
                    user.id = Long.parseLong(jSONObject.getString("platform_id"));
                    user.origin_id = Long.parseLong(jSONObject.getString("uid"));
                    user.origin = jSONObject.optLong("source");
                    AppUtils.getInstance().getUserInfoProvider().toUserPage(curActivity, user);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                callback.sendResult(0, "activity start success", "");
                return;
            }
            callback.sendResult(1, "activity is not available", "");
        } else if (a[2].equalsIgnoreCase(str)) {
            this.f = callback;
            if (jSONObject != null) {
                this.c = jSONObject.optString("url");
            }
            AppUtils.getInstance().getUserInfoProvider().toLogin(this.b.getCurActivity(), this.e);
        } else if (a[3].equalsIgnoreCase(str)) {
            this.f = callback;
            if (AppUtils.getInstance().getUserInfoProvider().isLogin()) {
                callback.sendResult(0, "", a(AppUtils.getInstance().getUserInfoProvider().getUser()));
            } else {
                callback.sendResult(1, "用户未登录", "");
            }
        }
    }

    private JSONObject a(User user) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("token", AppUtils.getInstance().getUserInfoProvider().getToken());
        jSONObject.put("uid", user.id);
        jSONObject.put("user_name", user.name);
        Object obj;
        if (!TextUtils.isEmpty(user.headurl) && user.headurl.length() > 5 && user.headurl.endsWith(".webp")) {
            obj = user.headurl.substring(0, user.headurl.length() - 5) + ".jpg";
            if (obj.startsWith("http:")) {
                obj = obj.substring(5);
            } else if (obj.startsWith("https:")) {
                obj = obj.substring(6);
            }
            jSONObject.put("user_icon", obj);
        } else if (TextUtils.isEmpty(user.headurl)) {
            jSONObject.put("user_icon", "");
        } else {
            obj = user.headurl;
            if (obj.startsWith("http:")) {
                obj = obj.substring(5);
            } else if (obj.startsWith("https:")) {
                obj = obj.substring(6);
            }
            jSONObject.put("user_icon", obj);
        }
        return jSONObject;
    }

    private JSONObject b(User user) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("token", AppUtils.getInstance().getUserInfoProvider().getToken());
        jSONObject.put("uid", user.id);
        jSONObject.put("user_name", user.name);
        jSONObject.put("user_icon", user.headurl);
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("Uuid", DeviceUtils.getAndroidId());
        jSONObject2.put("UserAgent", this.b.getCurActivity().getPackageName());
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
            Activity curActivity = this.b.getCurActivity();
            if (curActivity instanceof WebActivity) {
                ((WebActivity) curActivity).reloadUrl();
            }
        } else if (i == this.e && AppUtils.getInstance().getUserInfoProvider().isLogin()) {
            try {
                if (!(this.b.getCurActivity() == null || !(this.b.getCurActivity() instanceof WebActivity) || TextUtils.isEmpty(this.c))) {
                    ((WebActivity) this.b.getCurActivity()).reloadUrl(this.c);
                }
            } catch (Exception e) {
                e.printStackTrace();
                this.f.sendResult(1, "", "");
            }
            this.f = null;
        }
    }

    public void onDestroy() {
    }
}
