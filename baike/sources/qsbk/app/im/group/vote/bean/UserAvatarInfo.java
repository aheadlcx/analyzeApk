package qsbk.app.im.group.vote.bean;

import org.json.JSONObject;
import qsbk.app.database.QsbkDatabase;

public class UserAvatarInfo {
    public String mUserIcon;
    public int mUserId;
    public String mUserName;

    public UserAvatarInfo(int i, String str, String str2) {
        this.mUserId = i;
        this.mUserIcon = str;
        this.mUserName = str2;
    }

    public UserAvatarInfo(JSONObject jSONObject) {
        a(jSONObject);
    }

    private void a(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                this.mUserId = jSONObject.optInt("uid");
                this.mUserName = jSONObject.optString(QsbkDatabase.LOGIN);
                this.mUserIcon = jSONObject.optString("icon");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int getmUserId() {
        return this.mUserId;
    }

    public void setmUserId(int i) {
        this.mUserId = i;
    }

    public String getmUserIcon() {
        return this.mUserIcon;
    }

    public void setmUserIcon(String str) {
        this.mUserIcon = str;
    }

    public String getmUserName() {
        return this.mUserName;
    }

    public void setmUserName(String str) {
        this.mUserName = str;
    }
}
