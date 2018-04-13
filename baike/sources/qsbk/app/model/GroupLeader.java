package qsbk.app.model;

import org.json.JSONObject;
import qsbk.app.database.QsbkDatabase;

public class GroupLeader extends QbBean {
    public int age;
    public long downDate;
    public int downMember;
    public String gender;
    public String icon;
    public int id;
    public String login;
    public String teamNum;
    public int tribeId;
    public long upDate;
    public int upMember;
    public int userId;

    private GroupLeader() {
    }

    public static GroupLeader parseFromJsonObject(JSONObject jSONObject) {
        GroupLeader groupLeader = new GroupLeader();
        if (!jSONObject.isNull("id")) {
            groupLeader.id = jSONObject.optInt("id");
        }
        if (!jSONObject.isNull("tribe_id")) {
            groupLeader.tribeId = jSONObject.optInt("tribe_id");
        }
        if (!jSONObject.isNull("user_id")) {
            groupLeader.userId = jSONObject.optInt("user_id");
        }
        if (!jSONObject.isNull("icon")) {
            groupLeader.icon = jSONObject.optString("icon");
        }
        if (!jSONObject.isNull(QsbkDatabase.LOGIN)) {
            groupLeader.login = jSONObject.optString(QsbkDatabase.LOGIN);
        }
        if (!jSONObject.isNull("team_num")) {
            groupLeader.teamNum = jSONObject.optString("team_num");
        }
        if (!jSONObject.isNull("gender")) {
            groupLeader.gender = jSONObject.optString("gender");
        }
        if (!jSONObject.isNull("age")) {
            groupLeader.age = jSONObject.optInt("age");
        }
        if (!jSONObject.isNull("up_date")) {
            groupLeader.upDate = jSONObject.optLong("up_date", -1);
        }
        if (!jSONObject.isNull("up_member")) {
            groupLeader.upMember = jSONObject.optInt("up_member");
        }
        if (!jSONObject.isNull("down_date")) {
            groupLeader.downDate = jSONObject.optLong("down_date", -1);
        }
        if (!jSONObject.isNull("down_member")) {
            groupLeader.downMember = jSONObject.optInt("down_member");
        }
        return groupLeader;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GroupLeader)) {
            return false;
        }
        GroupLeader groupLeader = (GroupLeader) obj;
        if (this.id == groupLeader.id && this.userId == groupLeader.userId && this.tribeId == groupLeader.tribeId) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((((this.id + 527) * 31) + this.userId) * 31) + this.tribeId;
    }
}
