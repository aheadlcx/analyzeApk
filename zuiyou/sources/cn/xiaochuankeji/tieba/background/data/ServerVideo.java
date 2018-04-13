package cn.xiaochuankeji.tieba.background.data;

import android.text.TextUtils;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class ServerVideo implements Serializable {
    public static final int PRIORITY_EXT_URL = 1;
    public static final int PRIORITY_FINAL_URL = 4;
    public static final int PRIORITY_H5_URL = 2;
    public static final int PRIORITY_SRC_URL = 3;
    public String downloadUrl;
    private long duration;
    public String extUrl;
    public H5VideoInfo h5Video;
    public long imageId;
    private int playCount;
    public int priority;
    public String srcUrl;
    private String url;
    public long videoId;

    public static class H5VideoInfo implements Serializable {
        public int topCoverHeight;
        public String url;
        public int videoHeight;

        public H5VideoInfo(JSONObject jSONObject) {
            if (jSONObject != null) {
                this.url = jSONObject.optString("url");
                this.topCoverHeight = jSONObject.optInt("topheight");
                this.videoHeight = jSONObject.optInt("height");
            }
        }

        public JSONObject serializeTo() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("url", this.url);
            jSONObject.put("topheight", this.topCoverHeight);
            jSONObject.put("height", this.videoHeight);
            return jSONObject;
        }
    }

    public ServerVideo(JSONObject jSONObject) {
        if (jSONObject.has("urlsrc")) {
            this.url = jSONObject.optString("url");
            this.srcUrl = jSONObject.optString("urlsrc");
        } else {
            this.url = jSONObject.optString("url");
            this.srcUrl = this.url;
        }
        this.duration = jSONObject.optLong("dur");
        this.priority = jSONObject.optInt("priority", 1);
        this.h5Video = new H5VideoInfo(jSONObject.optJSONObject("webinfo"));
        this.extUrl = jSONObject.optString("urlext");
        this.playCount = jSONObject.optInt("playcnt");
        this.downloadUrl = jSONObject.optString("urlwm");
        if (TextUtils.isEmpty(this.downloadUrl)) {
            this.downloadUrl = this.srcUrl;
        }
        this.videoId = jSONObject.optLong("id", 0);
        this.imageId = jSONObject.optLong("imageid", 0);
    }

    public JSONObject serializeTo() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("url", this.url);
        jSONObject.put("dur", this.duration);
        jSONObject.put("priority", this.priority);
        jSONObject.put("webinfo", this.h5Video.serializeTo());
        jSONObject.put("urlext", this.extUrl);
        jSONObject.put("urlsrc", this.srcUrl);
        jSONObject.put("playcnt", this.playCount);
        jSONObject.put("urlwm", this.downloadUrl);
        return jSONObject;
    }

    public String getUrl() {
        return this.url;
    }

    public long getDuration() {
        return this.duration;
    }

    public int getPlayCount() {
        return this.playCount;
    }
}
