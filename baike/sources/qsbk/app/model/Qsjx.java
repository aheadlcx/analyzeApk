package qsbk.app.model;

import android.text.TextUtils;
import com.facebook.imagepipeline.cache.MediaVariationsIndexDatabase.IndexEntry;
import com.xiaomi.mipush.sdk.Constants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Qsjx extends QbBean {
    public String[] articleIds;
    public long date;
    public String detail;
    public String link;
    public String msgId;
    public String picUrl;
    public String shareTitle;
    public String title;

    public Qsjx(String str, long j, String str2, String[] strArr) {
        this.title = str;
        this.date = j;
        this.picUrl = str2;
        this.articleIds = strArr;
    }

    public Qsjx(String str, long j, String str2, String[] strArr, String str3, String str4, String str5, String str6) {
        this.title = str;
        this.date = j;
        this.picUrl = str2;
        this.articleIds = strArr;
        this.msgId = str3;
        this.detail = str4;
        this.link = str5;
        this.shareTitle = str6;
    }

    public void fromJsonObject(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.title = jSONObject.optString("title");
            this.date = jSONObject.optLong(IndexEntry.COLUMN_NAME_DATE);
            this.picUrl = jSONObject.optString("pic");
            JSONArray optJSONArray = jSONObject.optJSONArray("article_ids");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                this.articleIds = new String[optJSONArray.length()];
                for (int i = 0; i < optJSONArray.length(); i++) {
                    this.articleIds[i] = optJSONArray.optString(i);
                }
            }
        }
    }

    public void fromMsgJson(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.msgId = jSONObject.optString("msg_id");
            this.title = jSONObject.optString("title");
            String optString = jSONObject.optString("push_ids");
            if (optString != null && optString.length() > 0) {
                this.articleIds = optString.split(Constants.ACCEPT_TIME_SEPARATOR_SP);
            }
            this.picUrl = jSONObject.optString("push_image");
            this.detail = jSONObject.optString("push_detail");
            this.link = jSONObject.optString("link");
        }
    }

    public JSONObject encodeToJsonObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(this.title)) {
                jSONObject.put("title", this.title);
            }
            if (!TextUtils.isEmpty(this.picUrl)) {
                jSONObject.put("pic", this.picUrl);
            }
            jSONObject.put(IndexEntry.COLUMN_NAME_DATE, this.date);
            if (this.articleIds != null && this.articleIds.length > 0) {
                JSONArray jSONArray = new JSONArray();
                for (Object put : this.articleIds) {
                    jSONArray.put(put);
                }
                jSONObject.put("article_ids", jSONArray);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public String toImShareJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", 36);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("msg_id", this.msgId);
            jSONObject2.put("title", this.title);
            jSONObject2.put("push_image", this.picUrl);
            jSONObject2.put("push_detail", this.detail);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < this.articleIds.length; i++) {
                stringBuilder.append(this.articleIds[i]);
                if (i < this.articleIds.length - 1) {
                    stringBuilder.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                }
            }
            jSONObject2.put("push_ids", stringBuilder.toString());
            jSONObject2.put("link", this.link);
            jSONObject.put("data", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    public String toImDataJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("msg_id", this.msgId);
            jSONObject.put("title", this.title);
            jSONObject.put("push_image", this.picUrl);
            jSONObject.put("push_detail", this.detail);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < this.articleIds.length; i++) {
                stringBuilder.append(this.articleIds[i]);
                if (i < this.articleIds.length - 1) {
                    stringBuilder.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                }
            }
            jSONObject.put("push_ids", stringBuilder.toString());
            jSONObject.put("link", this.link);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    public String toString() {
        return encodeToJsonObject().toString();
    }

    public String getShareTitle() {
        if (TextUtils.isEmpty(this.shareTitle)) {
            return "糗事精选：" + this.title;
        }
        return this.shareTitle + "：" + this.title;
    }
}
