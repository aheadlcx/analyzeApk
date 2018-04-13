package qsbk.app.im.CollectEmotion;

import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.utils.json.JSONAble;

public class CollectImageDomain extends JSONAble implements Serializable {
    public String md5Str;
    public String netUrl;
    public String url;

    public CollectImageDomain(String str) {
        this.url = str;
        this.md5Str = str.split("//.")[0];
    }

    public CollectImageDomain(String str, String str2, String str3) {
        this.url = str;
        this.md5Str = str2;
        this.netUrl = str3;
    }

    public CollectImageDomain(String str, String str2) {
        this.url = str;
        this.md5Str = str2;
    }

    public CollectImageDomain(String str, boolean z) {
        parse(str);
    }

    public void parse(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("url")) {
                this.url = jSONObject.optString("url");
            }
            if (jSONObject.has("md5Str")) {
                this.md5Str = jSONObject.optString("md5Str");
            }
            if (jSONObject.has("netUrl")) {
                this.netUrl = jSONObject.optString("netUrl");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof CollectImageDomain)) {
            return false;
        }
        if (((CollectImageDomain) obj).netUrl.equals(this.netUrl)) {
        }
        if (((CollectImageDomain) obj).md5Str.equals(this.md5Str)) {
            return true;
        }
        return super.equals(obj);
    }

    public int hashCode() {
        return this.md5Str.hashCode();
    }
}
