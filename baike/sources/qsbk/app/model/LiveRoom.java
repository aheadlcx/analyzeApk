package qsbk.app.model;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.utils.json.JSONAble;

public class LiveRoom {
    public String accumulated_count;
    public Author author;
    public String content;
    public long created_at;
    public String distance;
    public int game_id;
    public String hdl_live_url;
    public boolean is_follow;
    public String live_id;
    public int live_type;
    public String location;
    public int mic_status;
    public String room_id;
    public String rtmp_live_url;
    public Share share;
    public String status;
    public String stream_id;
    public String thumbnail_url;
    public long update_at;
    public String visitors_count = "0";

    public static class Author extends JSONAble {
        public String avatar_dec_url;
        public String badge;
        public String gender;
        public String headurl;
        public String id;
        public String intro;
        public int level;
        public String list_dec_url;
        public String name;
        public long nick_id;
        public long origin;
        public long origin_id;
        public long platform_id;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Author author = (Author) obj;
            if (this.id != null) {
                return this.id.equals(author.id);
            }
            if (author.id != null) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return this.id != null ? this.id.hashCode() : 0;
        }
    }

    public static class Share extends JSONAble {
        public String caption;
        public String url;
        public String wb_info;
    }

    public static LiveRoom parse(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        LiveRoom liveRoom = new LiveRoom();
        liveRoom.live_id = jSONObject.optString("id", "0");
        liveRoom.accumulated_count = jSONObject.optString("accumulated_count", "0");
        liveRoom.content = jSONObject.optString("content");
        liveRoom.created_at = jSONObject.optLong(QsbkDatabase.CREATE_AT);
        liveRoom.is_follow = jSONObject.optBoolean("is_follow");
        liveRoom.room_id = jSONObject.optString("room_id", "0");
        liveRoom.rtmp_live_url = jSONObject.optString("rtmp_live_url");
        liveRoom.hdl_live_url = jSONObject.optString("hdl_live_url");
        liveRoom.status = jSONObject.optString("status");
        liveRoom.stream_id = jSONObject.optString("stream_id");
        liveRoom.thumbnail_url = jSONObject.optString("thumbnail_url");
        liveRoom.update_at = jSONObject.optLong("update_at");
        liveRoom.visitors_count = jSONObject.optString("visitors_count", "0");
        liveRoom.location = jSONObject.optString("location");
        liveRoom.live_type = jSONObject.optInt("live_type");
        liveRoom.game_id = jSONObject.optInt("game_id");
        liveRoom.distance = jSONObject.optString("distance");
        liveRoom.mic_status = jSONObject.optInt("mic_status");
        liveRoom.author = new Author();
        if (jSONObject.has("author")) {
            liveRoom.author.parseFromJSONObject(jSONObject.optJSONObject("author"));
        }
        liveRoom.share = new Share();
        if (!jSONObject.has("share")) {
            return liveRoom;
        }
        liveRoom.share.parseFromJSONObject(jSONObject.optJSONObject("share"));
        return liveRoom;
    }

    public static JSONObject toJSONObject(LiveRoom liveRoom) {
        if (liveRoom == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", liveRoom.live_id);
            jSONObject.put("accumulated_count", liveRoom.accumulated_count);
            jSONObject.put("content", liveRoom.content);
            jSONObject.put(QsbkDatabase.CREATE_AT, liveRoom.created_at);
            jSONObject.put("is_follow", liveRoom.is_follow);
            jSONObject.put("room_id", liveRoom.room_id);
            jSONObject.put("rtmp_live_url", liveRoom.rtmp_live_url);
            jSONObject.put("hdl_live_url", liveRoom.hdl_live_url);
            jSONObject.put("status", liveRoom.status);
            jSONObject.put("stream_id", liveRoom.stream_id);
            jSONObject.put("thumbnail_url", liveRoom.thumbnail_url);
            jSONObject.put("update_at", liveRoom.update_at);
            jSONObject.put("visitors_count", liveRoom.visitors_count);
            jSONObject.put("share", liveRoom.share.encodeToJsonObject());
            jSONObject.put("author", liveRoom.author.encodeToJsonObject());
            jSONObject.put("location", liveRoom.location);
            jSONObject.put("game_id", liveRoom.game_id);
            jSONObject.put("mic_status", liveRoom.mic_status);
            return jSONObject;
        } catch (JSONException e) {
            e.fillInStackTrace();
            return jSONObject;
        }
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof LiveRoom)) {
            if (!TextUtils.isEmpty(this.room_id) && !TextUtils.isEmpty(((LiveRoom) obj).room_id) && TextUtils.equals(this.room_id, ((LiveRoom) obj).room_id) && !"0".equals(this.room_id)) {
                return true;
            }
            if (!(this.author == null || ((LiveRoom) obj).author == null)) {
                return this.author.equals(((LiveRoom) obj).author);
            }
        }
        return false;
    }

    public boolean isLiveBegin() {
        int parseInt;
        try {
            parseInt = Integer.parseInt(this.status);
        } catch (Exception e) {
            e.printStackTrace();
            parseInt = 0;
        }
        if (parseInt != 1 || TextUtils.isEmpty(this.live_id)) {
            return false;
        }
        return true;
    }

    public boolean isMicMode() {
        return this.mic_status == 2 || this.mic_status == 3;
    }
}
