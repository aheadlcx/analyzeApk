package qsbk.app.model;

import android.text.TextUtils;
import java.io.Serializable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.utils.HttpUtils;
import qsbk.app.video.VideoInListHelper;

public class AuditArticle implements Serializable {
    public int absPicHeight;
    public String absPicPath;
    public int absPicWidth;
    public String content;
    public String high;
    public String id;
    public String image;
    public ImageSizes image_size;
    public String location;
    public String low;
    public String tag;

    public AuditArticle(JSONObject jSONObject) throws QiushibaikeException {
        a(jSONObject);
    }

    private void a(JSONObject jSONObject) throws QiushibaikeException {
        try {
            JSONArray jSONArray;
            this.id = jSONObject.getString("id");
            if (!jSONObject.isNull("content")) {
                this.content = jSONObject.getString("content");
            }
            if (!jSONObject.isNull(VideoInListHelper.TAG)) {
                this.tag = jSONObject.getString(VideoInListHelper.TAG);
            }
            if (!jSONObject.isNull("image")) {
                this.image = jSONObject.getString("image");
            }
            if (!jSONObject.isNull("high_url")) {
                this.high = jSONObject.getString("high_url");
            }
            if (!jSONObject.isNull("low_url")) {
                this.low = jSONObject.getString("low_url");
            }
            if (!jSONObject.isNull("pic_size")) {
                jSONArray = jSONObject.getJSONArray("pic_size");
                this.absPicWidth = jSONArray.getInt(0);
                this.absPicHeight = jSONArray.getInt(1);
            }
            if (!jSONObject.isNull("image_size")) {
                jSONArray = jSONObject.getJSONObject("image_size").getJSONArray("m");
                this.absPicWidth = jSONArray.optInt(0);
                this.absPicHeight = jSONArray.optInt(1);
            }
            if (!jSONObject.isNull("pic_url")) {
                this.absPicPath = jSONObject.getString("pic_url");
            }
        } catch (Exception e) {
        }
    }

    public boolean isVideoArticle() {
        return (TextUtils.isEmpty(this.low) || TextUtils.isEmpty(this.high)) ? false : true;
    }

    public String getVideoUrl() {
        return HttpUtils.isWifi(QsbkApp.mContext) ? this.high : this.low;
    }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", this.id);
        jSONObject.put("content", this.content);
        jSONObject.put(VideoInListHelper.TAG, this.tag);
        jSONObject.put("image", this.image);
        if (isVideoArticle()) {
            jSONObject.put("high_url", this.high);
            jSONObject.put("low_url", this.low);
            jSONObject.put("pic_url", this.absPicPath);
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(this.absPicWidth);
            jSONArray.put(this.absPicHeight);
            jSONObject.put("pic_size", jSONArray);
        }
        return jSONObject;
    }

    public int hashCode() {
        return (this.id == null ? 0 : this.id.hashCode()) + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        AuditArticle auditArticle = (AuditArticle) obj;
        if (this.id == null) {
            if (auditArticle.id != null) {
                return false;
            }
            return true;
        } else if (this.id.equals(auditArticle.id)) {
            return true;
        } else {
            return false;
        }
    }
}
