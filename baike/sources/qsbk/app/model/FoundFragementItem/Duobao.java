package qsbk.app.model.FoundFragementItem;

import org.json.JSONObject;

public class Duobao {
    public String link;
    public boolean show;
    public long timestamp;
    public String title;
    public boolean userToken;

    public Duobao(JSONObject jSONObject) {
        if (jSONObject != null) {
            a(jSONObject);
        }
    }

    private void a(JSONObject jSONObject) {
        boolean z = true;
        try {
            this.title = jSONObject.optString("title");
            this.link = jSONObject.optString("link");
            this.show = jSONObject.optInt("show") == 1;
            this.timestamp = jSONObject.optLong("timestamp");
            if (jSONObject.optInt("user") != 1) {
                z = false;
            }
            this.userToken = z;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
