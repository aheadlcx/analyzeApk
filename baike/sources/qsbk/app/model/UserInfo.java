package qsbk.app.model;

import android.text.TextUtils;
import java.util.Calendar;
import org.json.JSONObject;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.im.TimeUtils;
import qsbk.app.nearby.ui.HometownDialogFragment;
import qsbk.app.thirdparty.ThirdPartyConstants;
import qsbk.app.utils.json.JsonKeyName;

public class UserInfo extends BaseUserInfo {
    public static final String STATE_ACTIVE = "active";
    public static final String STATE_BINDING = "binding";
    public static final String STATE_BONDED = "bonded";
    public static final String STATE_BONDING = "bonding";
    public static final String STATE_PENDING = "pending";
    public String adminRole;
    public String bg;
    public String bigCover = "";
    public int bigCoverEday;
    public int circleLevel;
    public String email = "";
    public String emotion;
    public int hasPayPwd;
    public int hasPwd;
    public String haunt = "";
    public String hometown = "";
    public String job = "";
    @JsonKeyName("mobile_branch")
    public String mobileBrand = "";
    public String phone = "";
    public int qiubaiAge;
    public String qq = "";
    public int qsCount;
    @JsonKeyName("created_at")
    public long regTime;
    public int remixLevel;
    public int smileCount;
    public String state;
    public String token;
    public int usrIconEday;
    public int usrNameEday;
    public String wb = "";
    public String wx = "";

    public UserInfo(String str) {
        try {
            parseFromJSONObject(new JSONObject(str));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static UserInfo updateServerJsonNearby(UserInfo userInfo, JSONObject jSONObject) {
        if (userInfo == null) {
            userInfo = new UserInfo();
        }
        userInfo.parseBaseInfo(jSONObject);
        if (jSONObject.has("name")) {
            userInfo.userName = jSONObject.optString("name");
        }
        if (jSONObject.has("haunt")) {
            userInfo.haunt = jSONObject.optString("haunt");
        }
        if (jSONObject.has(HometownDialogFragment.KEY_HOMETOWN)) {
            userInfo.hometown = jSONObject.optString(HometownDialogFragment.KEY_HOMETOWN);
        }
        if (jSONObject.has("qb_age")) {
            userInfo.qiubaiAge = jSONObject.optInt("qb_age");
        }
        if (jSONObject.has(QsbkDatabase.CREATE_AT)) {
            userInfo.regTime = jSONObject.optLong(QsbkDatabase.CREATE_AT);
        }
        if (jSONObject.has("job")) {
            userInfo.job = jSONObject.optString("job");
        }
        if (jSONObject.has("mobile_brand")) {
            userInfo.mobileBrand = jSONObject.optString("mobile_brand");
        }
        if (jSONObject.has("qs_cnt")) {
            userInfo.qsCount = jSONObject.optInt("qs_cnt");
        }
        if (jSONObject.has("smile_cnt")) {
            userInfo.smileCount = jSONObject.optInt("smile_cnt");
        }
        if (jSONObject.has("icon_eday")) {
            userInfo.usrIconEday = jSONObject.optInt("icon_eday");
        }
        if (jSONObject.has("login_eday")) {
            userInfo.usrNameEday = jSONObject.optInt("login_eday");
        }
        if (jSONObject.has("big_cover")) {
            userInfo.bigCover = jSONObject.optString("big_cover");
        }
        if (jSONObject.has("big_cover_eday")) {
            userInfo.bigCoverEday = jSONObject.optInt("big_cover_eday");
        }
        if (jSONObject.has(QsbkDatabase.ROLE)) {
            userInfo.adminRole = jSONObject.optString(QsbkDatabase.ROLE);
        }
        if (jSONObject.has("emotion")) {
            userInfo.emotion = jSONObject.optString("emotion");
        }
        if (jSONObject.has("bg")) {
            userInfo.bg = jSONObject.optString("bg");
        }
        if (jSONObject.has("phone")) {
            userInfo.phone = jSONObject.optString("phone");
        }
        if (jSONObject.has("has_paypass")) {
            userInfo.hasPayPwd = jSONObject.optInt("has_paypass");
        }
        if (jSONObject.has("circle_level")) {
            userInfo.circleLevel = jSONObject.optInt("circle_level");
        }
        return userInfo;
    }

    public static UserInfo updateServerJson(UserInfo userInfo, JSONObject jSONObject) {
        String optString;
        if (userInfo == null) {
            userInfo = new UserInfo();
        }
        if (jSONObject.has("token")) {
            userInfo.token = jSONObject.optString("token");
        }
        JSONObject optJSONObject = jSONObject.optJSONObject("user");
        if (optJSONObject != null) {
            if (optJSONObject.has("id")) {
                userInfo.userId = optJSONObject.optString("id");
            }
            if (optJSONObject.has("icon")) {
                userInfo.userIcon = optJSONObject.optString("icon");
            }
            if (optJSONObject.has(QsbkDatabase.LOGIN)) {
                userInfo.userName = optJSONObject.optString(QsbkDatabase.LOGIN, "").replace("\"", "");
            }
            if (optJSONObject.has("state")) {
                userInfo.state = optJSONObject.optString("state");
            }
            if (optJSONObject.has("email")) {
                optString = optJSONObject.optString("email", "");
                if (TextUtils.isEmpty(optString) || optString.equals("null")) {
                    optString = "";
                }
                userInfo.email = optString;
            }
            if (optJSONObject.has(ThirdPartyConstants.THIRDPARTY_TYLE_SINA)) {
                optString = optJSONObject.optString(ThirdPartyConstants.THIRDPARTY_TYLE_SINA, "");
                if (TextUtils.isEmpty(optString) || optString.equals("null")) {
                    optString = "";
                }
                userInfo.wb = optString;
            }
            if (optJSONObject.has(ThirdPartyConstants.THIRDPARTY_TYLE_QQ)) {
                optString = optJSONObject.optString(ThirdPartyConstants.THIRDPARTY_TYLE_QQ, "");
                if (TextUtils.isEmpty(optString) || optString.equals("null")) {
                    optString = "";
                }
                userInfo.qq = optString;
            }
            if (optJSONObject.has("phone")) {
                optString = optJSONObject.optString("phone", "");
                if (TextUtils.isEmpty(optString) || optString.endsWith("null")) {
                    optString = "";
                }
                userInfo.phone = optString;
            }
            if (optJSONObject.has("has_password")) {
                userInfo.hasPwd = optJSONObject.optInt("has_password");
            }
            if (optJSONObject.has("circle_level")) {
                userInfo.circleLevel = optJSONObject.optInt("circle_level");
            }
        }
        optJSONObject = jSONObject.optJSONObject("userdata");
        if (optJSONObject != null) {
            if (optJSONObject.has(ThirdPartyConstants.THIRDPARTY_TYLE_QQ)) {
                optString = optJSONObject.optString(ThirdPartyConstants.THIRDPARTY_TYLE_QQ, "");
                if (TextUtils.isEmpty(optString) || optString.equals("null")) {
                    optString = "";
                }
                userInfo.qq = optString;
            }
            if (optJSONObject.has(ThirdPartyConstants.THIRDPARTY_TYLE_WX)) {
                optString = optJSONObject.optString(ThirdPartyConstants.THIRDPARTY_TYLE_WX, "");
                if (TextUtils.isEmpty(optString) || optString.equals("null")) {
                    optString = "";
                }
                userInfo.wx = optString;
            }
            if (optJSONObject.has(ThirdPartyConstants.THIRDPARTY_TYLE_SINA)) {
                optString = optJSONObject.optString(ThirdPartyConstants.THIRDPARTY_TYLE_SINA, "");
                if (TextUtils.isEmpty(optString) || optString.equals("null")) {
                    optString = "";
                }
                userInfo.wb = optString;
            }
            if (optJSONObject.has("phone")) {
                optString = optJSONObject.optString("phone", "");
                if (TextUtils.isEmpty(optString) || optString.endsWith("null")) {
                    optString = "";
                }
                userInfo.phone = optString;
            }
            if (optJSONObject.has("has_password")) {
                userInfo.hasPwd = optJSONObject.optInt("has_password");
            }
            if (optJSONObject.has("has_paypass")) {
                userInfo.hasPayPwd = optJSONObject.optInt("has_paypass");
            }
            if (optJSONObject.has("circle_level")) {
                userInfo.circleLevel = optJSONObject.optInt("circle_level");
            }
        }
        return userInfo;
    }

    public String toString() {
        return encodeToJsonObject().toString();
    }

    public boolean hasPwd() {
        return this.hasPwd == 1;
    }

    public boolean hasPhone() {
        return !a(this.phone);
    }

    public boolean isBindSocail() {
        return isBindWX() || isBindQQ() || isBindWeibo();
    }

    public boolean isBindWX() {
        return !a(this.wx);
    }

    public boolean isBindWeibo() {
        return !a(this.wb);
    }

    public boolean isBindQQ() {
        return !a(this.qq);
    }

    public boolean isBindAndVerifyEmail() {
        return !a(this.email) && STATE_BONDED.equalsIgnoreCase(this.state);
    }

    public boolean hasPaypass() {
        return this.hasPayPwd == 1;
    }

    public boolean isBackEndAdmin() {
        return TextUtils.equals("admin", this.adminRole) || "1".equalsIgnoreCase(this.userId);
    }

    public boolean isNewUser() {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(this.regTime * 1000);
        return !TimeUtils.isAWeekAgo(instance);
    }

    public boolean isBindTwoOfQQWXWB() {
        int i;
        if (isBindQQ()) {
            i = 1;
        } else {
            i = 0;
        }
        if (isBindWeibo()) {
            i++;
        }
        if (isBindWX()) {
            i++;
        }
        if (i >= 2) {
            return true;
        }
        return false;
    }

    private boolean a(String str) {
        return TextUtils.isEmpty(str) || str.trim().length() == 0;
    }
}
