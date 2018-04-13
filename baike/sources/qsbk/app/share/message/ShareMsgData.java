package qsbk.app.share.message;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.model.CircleTopic;
import qsbk.app.utils.json.JSONAble;

public class ShareMsgData extends JSONAble {
    private String a;
    private String b;
    public CircleTopic circleTopic;
    public String descriptions;
    public String text;
    public String title;
    public String url;

    public ShareMsgData(String str, String str2, String str3, String str4, String str5, String str6, CircleTopic circleTopic) {
        this.url = str;
        this.title = str2;
        this.text = str3;
        this.descriptions = str4;
        this.a = str5;
        this.b = str6;
        this.circleTopic = circleTopic;
    }

    public static String toJsonString(ShareMsgData shareMsgData) {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(shareMsgData.url)) {
                jSONObject.put("url", shareMsgData.url);
            }
            if (!TextUtils.isEmpty(shareMsgData.title)) {
                jSONObject.put("title", shareMsgData.title);
            }
            if (!TextUtils.isEmpty(shareMsgData.text)) {
                jSONObject.put("text", shareMsgData.text);
            }
            if (!TextUtils.isEmpty(shareMsgData.descriptions)) {
                jSONObject.put("descriptions", shareMsgData.descriptions);
            }
            if (!TextUtils.isEmpty(shareMsgData.a)) {
                jSONObject.put("previewImageURL", shareMsgData.a);
            }
            if (shareMsgData.circleTopic != null) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("topicid", shareMsgData.circleTopic.id);
                jSONObject.put("ext", jSONObject2);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }

    public String getDescriptions() {
        return this.descriptions;
    }

    public void setDescriptions(String str) {
        this.descriptions = str;
    }

    public String getPreviewImageURL() {
        return this.a;
    }

    public void setPreviewImageURL(String str) {
        this.a = str;
    }

    public String getExt() {
        return this.b;
    }

    public void setExt(String str) {
        this.b = str;
    }

    public CircleTopic getCircleTopic() {
        return this.circleTopic;
    }

    public void setCircleTopic(CircleTopic circleTopic) {
        this.circleTopic = circleTopic;
    }

    public String toString() {
        return "ShareMsgData{url='" + this.url + '\'' + ", title='" + this.title + '\'' + ", text='" + this.text + '\'' + ", descriptions='" + this.descriptions + '\'' + ", previewImageURL='" + this.a + '\'' + ", ext='" + this.b + '\'' + ", circleTopic=" + this.circleTopic + '}';
    }
}
