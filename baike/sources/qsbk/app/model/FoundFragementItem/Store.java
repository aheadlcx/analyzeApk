package qsbk.app.model.FoundFragementItem;

import org.json.JSONObject;

public class Store {
    public String image;
    public String link;
    public String name;
    public int oldPrice;
    public int price;

    public Store(JSONObject jSONObject) {
        a(jSONObject);
    }

    private void a(JSONObject jSONObject) {
        try {
            this.image = jSONObject.optString("image");
            this.name = jSONObject.optString("name");
            this.price = jSONObject.optInt("price");
            this.oldPrice = jSONObject.optInt("mktPrice");
            this.link = jSONObject.optString("link");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
