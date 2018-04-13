package qsbk.app.im.CollectEmotion;

import android.text.TextUtils;
import com.facebook.imagepipeline.cache.MediaVariationsIndexDatabase.IndexEntry;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.utils.json.JSONAble;

public class CollectImageLocal extends JSONAble implements Serializable {
    public int height;
    public String localUrl;
    public String netUrl;
    public int width;

    public CollectImageLocal(String str) {
        this.localUrl = str;
    }

    public CollectImageLocal(String str, String str2) {
        this.localUrl = str;
        this.netUrl = str2;
    }

    public CollectImageLocal(String str, String str2, int i, int i2) {
        this.localUrl = str;
        this.netUrl = str2;
        this.height = i;
        this.width = i2;
    }

    public CollectImageLocal(String str, boolean z) {
        parse(str);
    }

    public void parse(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("localUrl")) {
                this.localUrl = jSONObject.optString("localUrl");
            }
            if (jSONObject.has("netUrl")) {
                this.netUrl = jSONObject.optString("netUrl");
            }
            if (jSONObject.has(IndexEntry.COLUMN_NAME_HEIGHT)) {
                this.height = jSONObject.optInt(IndexEntry.COLUMN_NAME_HEIGHT);
            }
            if (jSONObject.has(IndexEntry.COLUMN_NAME_WIDTH)) {
                this.width = jSONObject.optInt(IndexEntry.COLUMN_NAME_WIDTH);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof CollectImageLocal)) {
            return false;
        }
        if ((obj instanceof CollectImageLocal) && !TextUtils.isEmpty(((CollectImageLocal) obj).localUrl) && this.localUrl.equals(((CollectImageLocal) obj).localUrl)) {
            return true;
        }
        return super.equals(obj);
    }

    public int hashCode() {
        return this.localUrl.hashCode();
    }
}
