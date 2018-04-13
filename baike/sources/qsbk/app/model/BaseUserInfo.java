package qsbk.app.model;

import android.text.TextUtils;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.im.ConversationActivity;
import qsbk.app.model.relationship.Relationship;
import qsbk.app.utils.json.JSONAble;

public class BaseUserInfo extends JSONAble implements Serializable {
    public static final String ANONYMOUS_UID = "63443";
    public static final String ANONYMOUS_USER_ICON = "20160223104221.jpg";
    public static final String ANONYMOUS_USER_ID = "63443";
    public static final String ANONYMOUS_USER_NAME = "匿名用户";
    public static final String FEMALE = "F";
    public static final String GENDER_UNKONW = "U";
    public static final String MALE = "M";
    public static final HashMap<String, String> MAP = new d();
    public int age;
    public boolean alreadyInGroup = false;
    public String astrology;
    public String baseHaunt;
    public long birthday;
    public String comeFrom;
    public String gender = GENDER_UNKONW;
    public boolean isAdmin;
    public boolean isMe = false;
    public boolean isOwner;
    public int nickStatus;
    public Relationship relationship;
    public int role;
    public long silenceTime;
    public String userIcon;
    public String userId = "";
    public String userName;

    private static void a(JSONObject jSONObject, String str, Object obj) throws JSONException {
        if (obj != null) {
            jSONObject.put(str, obj);
        }
    }

    public static BaseUserInfo createAnonymous() {
        BaseUserInfo baseUserInfo = new BaseUserInfo();
        baseUserInfo.userId = "63443";
        baseUserInfo.userIcon = ANONYMOUS_USER_ICON;
        baseUserInfo.userName = ANONYMOUS_USER_NAME;
        baseUserInfo.gender = GENDER_UNKONW;
        baseUserInfo.age = 0;
        if (QsbkApp.currentUser != null) {
            baseUserInfo.gender = QsbkApp.currentUser.gender;
            baseUserInfo.age = QsbkApp.currentUser.age;
        }
        return baseUserInfo;
    }

    public void parseBaseInfo(JSONObject jSONObject) {
        if (jSONObject.has("uid")) {
            this.userId = jSONObject.optString("uid");
        }
        if (jSONObject.has("id")) {
            this.userId = jSONObject.optString("id");
        }
        if (jSONObject.has(QsbkDatabase.LOGIN)) {
            this.userName = jSONObject.optString(QsbkDatabase.LOGIN, "").replace("\"", "");
        }
        if (jSONObject.has("birthday")) {
            this.birthday = jSONObject.optLong("birthday");
        }
        if (jSONObject.has("age")) {
            this.age = jSONObject.optInt("age");
        }
        if (jSONObject.has("astrology")) {
            this.astrology = jSONObject.optString("astrology");
        }
        if (jSONObject.has("icon")) {
            this.userIcon = jSONObject.optString("icon");
        }
        if (jSONObject.has("gender")) {
            this.gender = jSONObject.optString("gender");
        }
        if (jSONObject.has("relation")) {
            try {
                this.relationship = Relationship.valueOf(jSONObject.optString("relation").toUpperCase(Locale.US));
            } catch (IllegalArgumentException e) {
                switch (jSONObject.optInt("relation")) {
                    case 0:
                        this.relationship = Relationship.MYSELF;
                        break;
                    case 1:
                        this.relationship = Relationship.FRIEND;
                        break;
                    case 2:
                        this.relationship = Relationship.FAN;
                        break;
                    default:
                        break;
                }
            }
        }
        if (jSONObject.has(ConversationActivity.RELATIONSHIP)) {
            this.relationship = Relationship.valueOf(jSONObject.optString(ConversationActivity.RELATIONSHIP).toUpperCase(Locale.US));
        }
        if (jSONObject.has("already_in")) {
            this.alreadyInGroup = jSONObject.optBoolean("already_in");
        }
        if (jSONObject.has("haunt")) {
            this.baseHaunt = jSONObject.optString("haunt");
        }
        if (jSONObject.has("_silenceTime")) {
            this.silenceTime = jSONObject.optLong("_silenceTime");
        }
        if (jSONObject.has("silence")) {
            this.silenceTime = (((long) jSONObject.optInt("silence")) * 1000) + System.currentTimeMillis();
        }
        if (jSONObject.has("come_from")) {
            this.comeFrom = jSONObject.optString("come_from");
        }
        this.nickStatus = jSONObject.optInt("nick_status");
        this.isAdmin = jSONObject.optInt("admin") != 0;
        this.role = jSONObject.optInt("title", 0);
    }

    public JSONObject toJSONObject() throws JSONException {
        int i;
        int i2 = 1;
        JSONObject jSONObject = new JSONObject();
        a(jSONObject, "_silenceTime", Long.valueOf(this.silenceTime));
        a(jSONObject, "nick_status", Integer.valueOf(this.nickStatus));
        a(jSONObject, "haunt", this.baseHaunt);
        a(jSONObject, "already_in", Boolean.valueOf(this.alreadyInGroup));
        if (this.relationship != null) {
            a(jSONObject, ConversationActivity.RELATIONSHIP, this.relationship.name());
            a(jSONObject, "relation", this.relationship.name());
        }
        a(jSONObject, "gender", this.gender);
        a(jSONObject, "icon", this.userIcon);
        a(jSONObject, "astrology", this.astrology);
        a(jSONObject, "age", Integer.valueOf(this.age));
        a(jSONObject, "birthday", Long.valueOf(this.birthday));
        a(jSONObject, QsbkDatabase.LOGIN, this.userName);
        a(jSONObject, "id", this.userId);
        a(jSONObject, "uid", this.userId);
        String str = "admin";
        if (this.isAdmin) {
            i = 1;
        } else {
            i = 0;
        }
        a(jSONObject, str, Integer.valueOf(i));
        String str2 = "_owner";
        if (!this.isOwner) {
            i2 = 0;
        }
        a(jSONObject, str2, Integer.valueOf(i2));
        a(jSONObject, "title", Integer.valueOf(this.role));
        return jSONObject;
    }

    public String getRoleTitle(String[] strArr) {
        if (this.isOwner) {
            return "群大";
        }
        if (this.isAdmin) {
            return "管理员";
        }
        if (strArr == null || this.role <= 0 || this.role > strArr.length) {
            return null;
        }
        return strArr[this.role - 1];
    }

    public boolean isAnonymous() {
        return "63443".equals(this.userId);
    }

    public int hashCode() {
        return this.userId.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof BaseUserInfo)) {
            return false;
        }
        if (obj instanceof BaseUserInfo) {
            BaseUserInfo baseUserInfo = (BaseUserInfo) obj;
            if (TextUtils.equals(this.userId, ((BaseUserInfo) obj).userId)) {
                return true;
            }
        }
        return super.equals(obj);
    }
}
