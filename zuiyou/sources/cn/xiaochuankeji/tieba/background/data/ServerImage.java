package cn.xiaochuankeji.tieba.background.data;

import android.os.Build.VERSION;
import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class ServerImage implements Serializable {
    private static final long serialVersionUID = -4055674185795951211L;
    @JSONField(name = "dancnt")
    public int danmakuCount = 0;
    @JSONField(name = "fmt")
    public String fmt;
    @JSONField(name = "h")
    public int height;
    @JSONField(name = "mp4")
    public int mp4Id;
    @JSONField(name = "id")
    public long postImageId;
    @JSONField(name = "r")
    public int rotate;
    @JSONField(name = "video")
    public int video;
    @JSONField(name = "dur")
    public long videoDuration;
    @JSONField(name = "playcnt")
    public int videoPlayCount;
    @JSONField(name = "url")
    public String videoUrl;
    @JSONField(name = "w")
    public int width;

    public ServerImage(JSONObject jSONObject) {
        a(jSONObject);
    }

    private void a(JSONObject jSONObject) {
        this.postImageId = jSONObject.optLong("id");
        this.width = jSONObject.optInt("w");
        this.height = jSONObject.optInt("h");
        this.rotate = jSONObject.optInt("r");
        this.fmt = jSONObject.optString("fmt");
        this.mp4Id = jSONObject.optInt("mp4", -1);
        this.video = jSONObject.optInt("video");
        this.danmakuCount = jSONObject.optInt("dancnt");
    }

    public void parseVideoJSONObject(JSONObject jSONObject) {
        this.videoUrl = jSONObject.optString("url");
        this.videoDuration = jSONObject.optLong("dur");
        this.videoPlayCount = jSONObject.optInt("playcnt");
    }

    public JSONObject serializeTo() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", this.postImageId);
        jSONObject.put("w", this.width);
        jSONObject.put("h", this.height);
        jSONObject.put("r", this.rotate);
        jSONObject.put("fmt", this.fmt);
        jSONObject.put("mp4", this.mp4Id);
        jSONObject.put("video", this.video);
        jSONObject.put("dancnt", this.danmakuCount);
        return jSONObject;
    }

    public boolean isImage() {
        return (isVideo() || isMP4() || isGif()) ? false : true;
    }

    public boolean isVideo() {
        return 1 == this.video;
    }

    public boolean isMP4() {
        return this.mp4Id > 0 && VERSION.SDK_INT > 11;
    }

    public boolean isGif() {
        return "gif".equalsIgnoreCase(this.fmt);
    }

    public boolean isLongImage() {
        if (this.width <= 0 || this.height <= 0 || ((double) ((((float) this.height) * 1.0f) / ((float) this.width))) <= 2.5d) {
            return false;
        }
        return true;
    }
}
