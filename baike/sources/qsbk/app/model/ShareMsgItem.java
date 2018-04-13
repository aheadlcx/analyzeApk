package qsbk.app.model;

import android.text.TextUtils;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class ShareMsgItem implements Serializable {
    public static final int TYPE_ALL = 0;
    public static final int TYPE_INTERNAL = 1;
    public static final int TYPE_OUTSIDE = 2;
    public String content;
    public String imageUrl;
    public String link;
    public int live_origin;
    public int news_id;
    public int shareFor;
    public int shareType;
    public String title;

    public static ShareMsgItem parseJson(JSONObject jSONObject) {
        ShareMsgItem shareMsgItem = new ShareMsgItem();
        try {
            shareMsgItem.shareFor = jSONObject.optInt("shareFor");
            shareMsgItem.title = jSONObject.getString("title");
            shareMsgItem.content = jSONObject.optString("content");
            if (TextUtils.isEmpty(shareMsgItem.content)) {
                shareMsgItem.content = shareMsgItem.title;
            }
            shareMsgItem.imageUrl = jSONObject.optString("imageUrl");
            shareMsgItem.link = jSONObject.getString("url");
            shareMsgItem.live_origin = jSONObject.optInt("live_origin");
            shareMsgItem.news_id = jSONObject.optInt("news_id");
            shareMsgItem.shareType = jSONObject.optInt("share_tpye");
            return shareMsgItem;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("shareFor", this.shareFor);
            jSONObject.put("title", this.title);
            jSONObject.put("content", this.content);
            jSONObject.put("imageUrl", this.imageUrl);
            jSONObject.put("url", this.link);
            jSONObject.put("live_origin", this.live_origin);
            jSONObject.put("news_id", this.news_id);
            jSONObject.put("isShareInApp", this.shareType);
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String toString() {
        return "ShareMsgItem{shareFor=" + this.shareFor + ", title='" + this.title + '\'' + ", content='" + this.content + '\'' + ", imageUrl='" + this.imageUrl + '\'' + ", link='" + this.link + '\'' + ", live_origin='" + this.live_origin + '\'' + ", isSahreInApp='" + this.shareType + '\'' + '}';
    }
}
