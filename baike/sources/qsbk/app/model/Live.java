package qsbk.app.model;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.model.LiveRoom.Author;

public class Live extends QbBean {
    public Author author;
    public int gameId;
    public String image;
    public boolean isFollow;
    public String liveDes;
    public long liveId;
    public int liveNo;
    public int liveTotalNo;

    public static JSONObject toJson(Live live) {
        if (live == null || live.liveId <= 0) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", live.liveId);
            jSONObject.put("visitors_count", live.liveNo);
            jSONObject.put("thumbnail_url", live.image);
            jSONObject.put("accumulated_count", live.liveTotalNo);
            jSONObject.put("is_follow", live.isFollow);
            jSONObject.put("game_id", live.gameId);
            if (!TextUtils.isEmpty(live.liveDes)) {
                jSONObject.put("liveDes", live.liveDes);
            }
            if (live.author == null) {
                return jSONObject;
            }
            jSONObject.put("author", live.author.encodeToJsonObject());
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Live parseJson(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        try {
            Live live = new Live();
            live.liveId = jSONObject.getLong("id");
            live.liveNo = jSONObject.optInt("visitors_count");
            live.image = jSONObject.optString("thumbnail_url");
            live.liveTotalNo = jSONObject.optInt("accumulated_count");
            live.isFollow = jSONObject.optBoolean("is_follow");
            live.liveDes = jSONObject.optString("content");
            live.gameId = jSONObject.optInt("game_id");
            live.author = new Author();
            if (jSONObject.has("author")) {
                live.author.parseFromJSONObject(jSONObject.optJSONObject("author"));
            }
            return live;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
