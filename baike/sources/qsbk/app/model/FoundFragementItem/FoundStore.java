package qsbk.app.model.FoundFragementItem;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FoundStore {
    public String link;
    public boolean show;
    public ArrayList<Store> stores;

    public FoundStore() {
        this.show = true;
    }

    public FoundStore(JSONObject jSONObject) {
        this.show = true;
        this.stores = new ArrayList();
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
            this.link = jSONObject.optString("link");
            JSONArray optJSONArray = jSONObject.optJSONArray("items");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                while (i < optJSONArray.length()) {
                    this.stores.add(new Store(optJSONArray.getJSONObject(i)));
                    i++;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
