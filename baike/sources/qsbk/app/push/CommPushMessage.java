package qsbk.app.push;

import org.json.JSONObject;

public class CommPushMessage implements QBPushMessage {
    private JSONObject a;
    private String b;

    public CommPushMessage(String str) {
        this.b = str;
        try {
            this.a = new JSONObject(str);
        } catch (Exception e) {
            this.a = new JSONObject();
        }
    }

    public String getTitle() {
        return this.a.optString("title");
    }

    public String getDescription() {
        return this.a.optString("description");
    }

    public String getCustomJson() {
        return this.b;
    }
}
