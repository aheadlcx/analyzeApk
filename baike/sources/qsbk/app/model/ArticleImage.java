package qsbk.app.model;

import android.text.TextUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.model.media.MediaFormat;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.Util;

public class ArticleImage extends ImageInfo {
    String a;
    public int duration;
    public String videoHighUrl;
    public String videoLowUrl;

    public ArticleImage(JSONObject jSONObject) {
        construct(jSONObject);
    }

    public ArticleImage(ImageInfo imageInfo) {
        this.a = MediaFormat.getNetAlias(imageInfo.mediaFormat);
        this.url = imageInfo.url;
        this.bigUrl = imageInfo.bigUrl;
        this.mediaFormat = imageInfo.mediaFormat;
        this.width = imageInfo.width;
        this.height = imageInfo.height;
    }

    public static String getCorrectUrl(String str) {
        if (TextUtils.isEmpty(str) || !str.startsWith("//")) {
            return str;
        }
        return "https:" + str;
    }

    public void construct(JSONObject jSONObject) {
        this.a = jSONObject.optString("format");
        this.mediaFormat = MediaFormat.getMediaFormatFromNetwork(this.a);
        JSONObject optJSONObject = jSONObject.optJSONObject("image_size");
        if (optJSONObject != null) {
            JSONArray optJSONArray = optJSONObject.optJSONArray("s");
            optJSONObject.optJSONArray("m");
            if (optJSONArray != null && optJSONArray.length() > 1) {
                this.width = optJSONArray.optInt(0);
                this.height = optJSONArray.optInt(1);
                this.duration = optJSONArray.optInt(2);
                if (this.mediaFormat == MediaFormat.IMAGE_STATIC && Util.isLongImage(this.width, this.height)) {
                    this.mediaFormat = MediaFormat.IMAGE_LONG;
                }
            }
        }
        switch (b.a[this.mediaFormat.ordinal()]) {
            case 1:
                this.url = getCorrectUrl(jSONObject.optString("pic_url"));
                return;
            case 2:
            case 3:
                this.url = getCorrectUrl(jSONObject.optString("pic_url"));
                this.bigUrl = this.url;
                this.videoLowUrl = getCorrectUrl(jSONObject.optString("low_url"));
                this.videoHighUrl = getCorrectUrl(jSONObject.optString("high_url"));
                return;
            default:
                this.url = getCorrectUrl(jSONObject.optString("low_url"));
                this.bigUrl = getCorrectUrl(jSONObject.optString("high_url"));
                return;
        }
    }

    public int getVideoDuration() {
        return this.duration;
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("format", this.a);
            switch (b.a[this.mediaFormat.ordinal()]) {
                case 1:
                    jSONObject.put("pic_url", this.url);
                    jSONObject.put("low_url", this.url);
                    jSONObject.put("high_url", this.bigUrl);
                    break;
                case 2:
                case 3:
                    jSONObject.put("pic_url", this.url);
                    jSONObject.put("low_url", this.videoLowUrl);
                    jSONObject.put("high_url", this.videoHighUrl);
                    break;
                default:
                    jSONObject.put("low_url", this.url);
                    jSONObject.put("high_url", this.bigUrl);
                    break;
            }
            if (!(this.width == 0 || this.height == 0)) {
                JSONObject jSONObject2 = new JSONObject();
                JSONArray jSONArray = new JSONArray();
                jSONArray.put(this.width);
                jSONArray.put(this.height);
                jSONArray.put(this.duration);
                jSONObject2.put("s", jSONArray);
                jSONObject.put("image_size", jSONObject2);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public String getVideoUrl() {
        return HttpUtils.isWifi(QsbkApp.mContext) ? this.videoHighUrl : this.videoLowUrl;
    }
}
