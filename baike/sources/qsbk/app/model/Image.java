package qsbk.app.model;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.facebook.common.util.UriUtil;
import com.facebook.imagepipeline.cache.MediaVariationsIndexDatabase.IndexEntry;
import com.qq.e.comm.constants.ErrorCode$OtherError;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.video.VideoEditActivity;

public class Image extends ImageInfo implements Parcelable {
    public static final Creator<Image> CREATOR = new o();
    public int height;
    public String url;
    public int width;

    public Image(String str) {
        this.url = "file:/" + str;
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        this.width = options.outWidth;
        this.height = options.outHeight;
    }

    public Image(ImageInfo imageInfo, Context context) {
        this.url = imageInfo.getImageUrl();
        Object realPathFromUri = UriUtil.getRealPathFromUri(context.getContentResolver(), Uri.parse(imageInfo.getImageUrl()));
        if (!TextUtils.isEmpty(realPathFromUri)) {
            Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(realPathFromUri, options);
            this.width = options.outWidth;
            this.height = options.outHeight;
        }
    }

    protected Image(Parcel parcel) {
        this.url = parcel.readString();
        this.width = parcel.readInt();
        this.height = parcel.readInt();
    }

    public static Image mock() {
        Image image = new Image();
        image.width = VideoEditActivity.MAX_VIDEO_WIDTH;
        image.height = ErrorCode$OtherError.CONTENT_FORCE_EXPOSURE;
        image.url = "http://img.hb.aicdn.com/51ca0102f498c14fc63163e913b259b3d3dc1ae99831-sXCxTV_fw658";
        return image;
    }

    public static Image parse(JSONObject jSONObject) {
        Image image = new Image();
        try {
            image.url = jSONObject.optString("url");
            image.width = jSONObject.optInt(IndexEntry.COLUMN_NAME_WIDTH);
            image.height = jSONObject.optInt(IndexEntry.COLUMN_NAME_HEIGHT);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return image;
    }

    public boolean isVertical() {
        return this.height > this.width;
    }

    public float getRatio() {
        if (this.height == 0 || this.width == 0) {
            return 1.0f;
        }
        return (1.0f * ((float) this.width)) / ((float) this.height);
    }

    public String getImageUrl() {
        return this.url;
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("url", this.url);
            jSONObject.put(IndexEntry.COLUMN_NAME_WIDTH, this.width);
            jSONObject.put(IndexEntry.COLUMN_NAME_HEIGHT, this.height);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.url);
        parcel.writeInt(this.width);
        parcel.writeInt(this.height);
    }
}
