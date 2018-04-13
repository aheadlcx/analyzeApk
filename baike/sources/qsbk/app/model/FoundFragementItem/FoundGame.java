package qsbk.app.model.FoundFragementItem;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.ConfigManager;

public class FoundGame {
    public String description;
    public ArrayList<Game> games;
    public String link;
    public String name;
    public boolean show;
    public long timestamp;

    public FoundGame() {
        this.show = !"73".equals(ConfigManager.getInstance().getChannel());
    }

    public FoundGame(JSONObject jSONObject) {
        boolean z;
        if ("73".equals(ConfigManager.getInstance().getChannel())) {
            z = false;
        } else {
            z = true;
        }
        this.show = z;
        this.games = new ArrayList();
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
            JSONArray optJSONArray = jSONObject.optJSONArray("games");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                while (i < optJSONArray.length()) {
                    this.games.add(new Game(optJSONArray.getJSONObject(i)));
                    i++;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
