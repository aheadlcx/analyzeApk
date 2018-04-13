package qsbk.app.model.FoundFragementItem;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FoundChicken {
    public ArrayList<Chicken> chickens;
    public String description;
    public String link;
    public String name;
    public boolean show;
    public long timestamp;

    public FoundChicken() {
        this.show = true;
    }

    public FoundChicken(JSONObject jSONObject) {
        this.show = true;
        this.chickens = new ArrayList();
        if (jSONObject != null) {
            a(jSONObject);
        }
    }

    private void a(JSONObject jSONObject) {
        boolean z = true;
        int i = 0;
        try {
            if (jSONObject.optInt("show") != 1) {
                z = false;
            }
            this.show = z;
            this.timestamp = jSONObject.optLong("timestamp");
            this.link = jSONObject.optString("link");
            this.description = jSONObject.optString("description");
            this.name = jSONObject.optString("name");
            JSONArray optJSONArray = jSONObject.optJSONArray("videos");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                while (i < optJSONArray.length()) {
                    this.chickens.add(new Chicken(optJSONArray.getJSONObject(i)));
                    i++;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
