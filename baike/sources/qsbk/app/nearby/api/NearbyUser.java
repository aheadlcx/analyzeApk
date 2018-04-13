package qsbk.app.nearby.api;

import java.util.Locale;
import org.json.JSONObject;
import qsbk.app.im.ConversationActivity;
import qsbk.app.model.UserInfo;
import qsbk.app.model.relationship.Relationship;
import qsbk.app.utils.DateUtil;

public class NearbyUser extends UserInfo {
    public int mDistance;
    public int mLastVisitTime = 0;
    public String recentCircle;
    public Relationship relationship;

    public static String getDistanceStr(int i) {
        if (i <= 100) {
            return "0.1km";
        }
        return String.format("%.1fkm", new Object[]{Double.valueOf(((double) Math.round(((double) i) / 100.0d)) / 10.0d)});
    }

    public String getTimePost() {
        return DateUtil.getNearByTimePostStr(((int) (System.currentTimeMillis() / 1000)) - this.mLastVisitTime);
    }

    public String getDistanceStr() {
        return getDistanceStr(this.mDistance);
    }

    public String getLastLogin() {
        return String.format("%s • %s", new Object[]{getDistanceStr(), getTimePost()});
    }

    public void parseJson(JSONObject jSONObject) {
        updateServerJsonNearby(this, jSONObject);
        this.mDistance = jSONObject.optInt("dis");
        this.mLastVisitTime = jSONObject.optInt("last_visit_time");
        this.recentCircle = jSONObject.optString("recent_circle");
        if (jSONObject.has(ConversationActivity.RELATIONSHIP)) {
            this.relationship = Relationship.valueOf(jSONObject.optString(ConversationActivity.RELATIONSHIP).toUpperCase(Locale.US));
        }
    }

    public boolean equals(Object obj) {
        if ((obj instanceof NearbyUser) && this.userId != null && ((NearbyUser) obj).userId.equals(this.userId)) {
            return true;
        }
        return false;
    }
}
