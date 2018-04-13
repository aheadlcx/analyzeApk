package qsbk.app.push;

import org.json.JSONObject;

public class BaiduPushMessage implements QBPushMessage {
    public static final String KEY_CUSTOM_CONTENT = "custom_content";
    private String a;
    private JSONObject b = new JSONObject();

    public BaiduPushMessage(String str) {
        this.a = str;
        try {
            this.b = new JSONObject(str);
        } catch (Exception e) {
        }
    }

    public String getTitle() {
        return this.b.optString("title");
    }

    public String getDescription() {
        return this.b.optString("description");
    }

    public String getCustomJson() {
        return this.b.optString(KEY_CUSTOM_CONTENT);
    }
}
