package qsbk.app.model;

import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

public class Announcement {
    public String content;
    public long end;
    public boolean hasClick = false;
    public String link;
    public int location;
    public long start;

    public static Announcement parseJson(JSONObject jSONObject) {
        Announcement announcement = new Announcement();
        announcement.location = jSONObject.optInt("location");
        announcement.content = jSONObject.optString("content");
        announcement.link = jSONObject.optString("link");
        announcement.start = jSONObject.optLong("started_at");
        announcement.end = jSONObject.optLong("ended_at");
        announcement.hasClick = jSONObject.optBoolean("hasClick");
        return announcement;
    }

    public static String toJsonString(Announcement announcement) {
        String str = null;
        if (!(announcement == null || announcement.hasClick)) {
            if (announcement.end <= new Date().getTime()) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("location", announcement.location);
                    jSONObject.put("content", announcement.content);
                    jSONObject.put("link", announcement.link);
                    jSONObject.put("started_at", announcement.start);
                    jSONObject.put("ended_at", announcement.end);
                    jSONObject.put("hasClick", announcement.hasClick);
                    str = jSONObject.toString();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return str;
    }
}
