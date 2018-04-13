package qsbk.app.model.FoundFragementItem;

import org.json.JSONObject;

public class Chicken {
    public String description;
    public String image;
    public String link;
    public String subject;

    public Chicken(JSONObject jSONObject) {
        a(jSONObject);
    }

    private void a(JSONObject jSONObject) {
        try {
            this.subject = jSONObject.optString("subject");
            this.description = jSONObject.optString("description");
            this.image = jSONObject.optString("image");
            this.link = jSONObject.optString("link");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
