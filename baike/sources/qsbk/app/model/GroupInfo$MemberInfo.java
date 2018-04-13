package qsbk.app.model;

import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.database.QsbkDatabase;

public class GroupInfo$MemberInfo implements Serializable {
    public int age;
    public String gender;
    public String icon;
    public String login;
    public int uid;

    public GroupInfo$MemberInfo(JSONObject jSONObject) {
        a(jSONObject);
    }

    private void a(JSONObject jSONObject) {
        try {
            this.login = jSONObject.optString(QsbkDatabase.LOGIN);
            this.uid = jSONObject.getInt("uid");
            this.age = jSONObject.getInt("age");
            this.gender = jSONObject.getString("gender");
            this.icon = jSONObject.getString("icon");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public BaseUserInfo toBaseUserInfo() {
        BaseUserInfo baseUserInfo = new BaseUserInfo();
        baseUserInfo.userId = String.valueOf(this.uid);
        baseUserInfo.userName = this.login;
        baseUserInfo.userIcon = this.icon;
        baseUserInfo.age = this.age;
        baseUserInfo.gender = this.gender;
        return baseUserInfo;
    }
}
