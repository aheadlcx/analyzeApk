package qsbk.app.model;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.facebook.common.util.UriUtil;
import java.io.File;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.model.media.MediaFormat;
import qsbk.app.utils.Util;

public class ImageInfo extends QbBean {
    public static final int MAX_GIF_SIZE = 5242880;
    public String bigUrl;
    public int height;
    public int id;
    public MediaFormat mediaFormat;
    public int size;
    public String url;
    public int width;

    public ImageInfo() {
        this.id = -1;
        this.mediaFormat = MediaFormat.IMAGE_STATIC;
    }

    public ImageInfo(String str, int i, int i2) {
        this.id = -1;
        this.mediaFormat = MediaFormat.IMAGE_STATIC;
        this.url = str;
        this.width = i;
        this.height = i2;
    }

    public ImageInfo(String str) {
        this(str, MediaFormat.IMAGE_STATIC);
    }

    public ImageInfo(String str, MediaFormat mediaFormat) {
        this.id = -1;
        this.mediaFormat = MediaFormat.IMAGE_STATIC;
        this.url = str;
        this.bigUrl = str;
        this.mediaFormat = mediaFormat;
    }

    public ImageInfo(String str, String str2) {
        this.id = -1;
        this.mediaFormat = MediaFormat.IMAGE_STATIC;
        this.url = str;
        this.bigUrl = str2;
    }

    public ImageInfo(int i, String str, String str2) {
        this.id = -1;
        this.mediaFormat = MediaFormat.IMAGE_STATIC;
        this.id = i;
        this.url = str;
        this.bigUrl = str;
        this.mediaFormat = MediaFormat.getMediaFormatFromMimeType(str2);
    }

    public ImageInfo(int i, String str, String str2, int i2, int i3) {
        this.id = -1;
        this.mediaFormat = MediaFormat.IMAGE_STATIC;
        this.id = i;
        this.url = str;
        this.bigUrl = str;
        this.mediaFormat = MediaFormat.getMediaFormatFromMimeType(str2);
        this.width = i2;
        this.height = i3;
        if (this.mediaFormat == MediaFormat.IMAGE_STATIC && Util.isLongImage(i2, i3)) {
            this.mediaFormat = MediaFormat.IMAGE_LONG;
        }
    }

    public static boolean isUrlFile(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Uri parse = Uri.parse(str);
        File file = new File(parse.getPath());
        if (!file.exists()) {
            file = new File(parse.getEncodedPath());
        }
        return file.exists();
    }

    public String getImageUrl() {
        return this.url;
    }

    public String getBigImageUrl() {
        if (TextUtils.isEmpty(this.bigUrl)) {
            return this.url;
        }
        return this.bigUrl;
    }

    public float getAspectRatio() {
        if (this.height <= 0 || this.width <= 0) {
            return 1.0f;
        }
        return (1.0f * ((float) this.width)) / ((float) this.height);
    }

    public String getFilePath(Context context) {
        if (TextUtils.isEmpty(this.url)) {
            return null;
        }
        return UriUtil.getRealPathFromUri(context.getContentResolver(), Uri.parse(this.url));
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", this.id);
            jSONObject.put("url", this.url);
            jSONObject.put(IndexEntry.COLUMN_NAME_WIDTH, this.width);
            jSONObject.put(IndexEntry.COLUMN_NAME_HEIGHT, this.height);
            jSONObject.put("mediaformat", this.mediaFormat.mimeType);
            jSONObject.put("size", this.size);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public void fromJson(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.id = jSONObject.optInt("id");
            this.url = jSONObject.optString("url");
            this.width = jSONObject.optInt(IndexEntry.COLUMN_NAME_WIDTH);
            this.height = jSONObject.optInt(IndexEntry.COLUMN_NAME_HEIGHT);
            this.mediaFormat = MediaFormat.getMediaFormatFromMimeType(jSONObject.optString("mediaformat"));
            this.size = jSONObject.optInt("size");
        }
    }

    public String getVideoUrl() {
        return null;
    }

    public boolean isOverSize() {
        if (this.mediaFormat != MediaFormat.IMAGE_GIF || this.size <= MAX_GIF_SIZE) {
            return false;
        }
        return true;
    }

    public String getMaxSize() {
        if (this.mediaFormat == MediaFormat.IMAGE_GIF) {
            return "5MB";
        }
        return "unknow";
    }

    public int hashCode() {
        if (TextUtils.isEmpty(this.url)) {
            return super.hashCode();
        }
        return this.url.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ImageInfo)) {
            return false;
        }
        if (((ImageInfo) obj).id < 0 && this.url.equals(((ImageInfo) obj).url)) {
            return true;
        }
        if (((ImageInfo) obj).id == this.id && this.url.equals(((ImageInfo) obj).url)) {
            return true;
        }
        return false;
    }
}
