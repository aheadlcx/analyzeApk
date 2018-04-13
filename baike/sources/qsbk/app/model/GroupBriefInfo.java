package qsbk.app.model;

import org.json.JSONException;
import org.json.JSONObject;

public class GroupBriefInfo extends QbBean {
    public int active;
    public String description;
    public int distance;
    public String icon;
    public int id;
    public boolean isOwner;
    public int joinStatus;
    public int level;
    public int limitMember;
    public String location;
    public int memberNum;
    public String name;
    public int rank;
    public int right;
    public int status;
    public int statusUpdatedAt;

    public GroupBriefInfo(JSONObject jSONObject) {
        a(jSONObject);
    }

    private void a(JSONObject jSONObject) {
        try {
            this.id = jSONObject.getInt("id");
            this.status = jSONObject.optInt("status");
            this.isOwner = jSONObject.optBoolean("is_owner");
            this.description = jSONObject.optString("description");
            this.level = jSONObject.optInt("level", -1);
            this.memberNum = jSONObject.optInt("member_num");
            this.statusUpdatedAt = jSONObject.optInt("status_updated_at");
            this.distance = jSONObject.optInt("distance");
            this.limitMember = jSONObject.optInt("limit_member");
            this.joinStatus = jSONObject.optInt("join_status");
            this.name = jSONObject.getString("name");
            this.icon = jSONObject.getString("icon");
            if (!(this.icon == null || this.icon.endsWith("/format/webp"))) {
                this.icon += "/format/webp";
            }
            this.location = jSONObject.optString("location");
            this.active = jSONObject.optInt("active");
            this.right = jSONObject.optInt("right");
            this.rank = jSONObject.optInt("rank", -1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean equals(Object obj) {
        if ((obj instanceof GroupBriefInfo) && ((GroupBriefInfo) obj).id == this.id) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.id;
    }
}
