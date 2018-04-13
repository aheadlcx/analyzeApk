package qsbk.app.model;

import com.facebook.imagepipeline.cache.MediaVariationsIndexDatabase.IndexEntry;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.model.media.MediaFormat;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.Util;

public class PicUrl extends ImageInfo {
    public int createAt;
    public String format;
    public String highUrl;
    public boolean isLong;
    public String lowUrl;
    public int status;

    public PicUrl(int i) {
        this.createAt = i;
    }

    public boolean isGIFVideo() {
        return MediaFormat.IMAGE_GIF_VIDEO == this.mediaFormat;
    }

    public boolean isImage() {
        return !isGIFVideo();
    }

    public String getImageUrl() {
        if (this.mediaFormat == MediaFormat.IMAGE_ORIGIN) {
            return this.url;
        }
        return QsbkApp.absoluteUrlOfCircleWebpImage(this.url, this.createAt);
    }

    public String getBigImageUrl() {
        if (this.mediaFormat == MediaFormat.IMAGE_ORIGIN) {
            return this.url;
        }
        String absoluteUrlOfCircleWebpImage = QsbkApp.absoluteUrlOfCircleWebpImage(this.url, this.createAt);
        int indexOf = absoluteUrlOfCircleWebpImage.indexOf("?");
        if (indexOf != -1) {
            return absoluteUrlOfCircleWebpImage.substring(0, indexOf);
        }
        return absoluteUrlOfCircleWebpImage;
    }

    public void constructJson(JSONObject jSONObject) {
        this.url = jSONObject.optString("pic_url");
        this.status = jSONObject.optInt("status");
        this.format = jSONObject.optString("format");
        this.lowUrl = jSONObject.optString("low_url");
        this.highUrl = jSONObject.optString("high_url");
        this.width = jSONObject.optInt(IndexEntry.COLUMN_NAME_WIDTH);
        this.height = jSONObject.optInt(IndexEntry.COLUMN_NAME_HEIGHT);
        this.mediaFormat = MediaFormat.getMediaFormatFromNetwork(this.format);
        if (this.mediaFormat == MediaFormat.IMAGE_STATIC && Util.isLongImage(this.width, this.height)) {
            this.mediaFormat = MediaFormat.IMAGE_LONG;
        }
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("pic_url", this.url);
            jSONObject.put("status", this.status);
            jSONObject.put("format", this.format);
            jSONObject.put("low_url", this.lowUrl);
            jSONObject.put("high_url", this.highUrl);
            jSONObject.put(IndexEntry.COLUMN_NAME_WIDTH, this.width);
            jSONObject.put(IndexEntry.COLUMN_NAME_HEIGHT, this.height);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public String getVideoUrl() {
        return HttpUtils.isWifi(QsbkApp.mContext) ? this.highUrl : this.lowUrl;
    }
}
