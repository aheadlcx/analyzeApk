package qsbk.app.open.auth;

import android.text.TextUtils;
import org.json.JSONObject;
import qsbk.app.utils.SharePreferenceUtils;

public class AuthorizedUser {
    public static String KEY_AUTH_CODE = "auth_code";
    public static String KEY_EXPIRED_IN = "expired_in";
    public static String KEY_ICON = "user_icon";
    public static String KEY_NICK = "user_name";
    public static final String KEY_SAVED_AUTH = "open.auth.user_auth_app";
    public JSONObject mObject;

    public AuthorizedUser(JSONObject jSONObject) {
        this.mObject = jSONObject;
    }

    public static AuthorizedUser getAuthorizedUser(String str) {
        Object sharePreferencesValue = SharePreferenceUtils.getSharePreferencesValue("open.auth.user_auth_app_" + str);
        if (TextUtils.isEmpty(sharePreferencesValue)) {
            return null;
        }
        try {
            return new AuthorizedUser(new JSONObject(sharePreferencesValue));
        } catch (Exception e) {
            return null;
        }
    }

    public static void setAuthorizedUser(String str, JSONObject jSONObject) {
        SharePreferenceUtils.setSharePreferencesValue("open.auth.user_auth_app_" + str, jSONObject.toString());
    }

    public String getNickName() {
        return this.mObject.optString(KEY_NICK);
    }

    public String getIconUrl() {
        return this.mObject.optString(KEY_ICON);
    }

    public String getRespCode() {
        return this.mObject.optString(KEY_AUTH_CODE);
    }

    public String getRespCodeExpireTime() {
        return this.mObject.optString(KEY_EXPIRED_IN);
    }

    public boolean isAuthCodeValid() {
        try {
            return System.currentTimeMillis() + 36000000 > ((long) Integer.parseInt(getRespCodeExpireTime()));
        } catch (Exception e) {
            return false;
        }
    }
}
