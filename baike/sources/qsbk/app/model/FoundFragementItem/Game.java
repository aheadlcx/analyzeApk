package qsbk.app.model.FoundFragementItem;

import com.tencent.open.SocialConstants;
import org.json.JSONObject;

public class Game {
    public String act;
    public String androidDownloadUrl;
    public String description;
    public String image;
    public String iosDownloadUrl;
    public String link;
    public String name;
    public int orientation;
    public int pos;
    public boolean shouldShowDivider;
    public String showGift;
    public String showPlayersNum;
    public int starStore;

    public Game(JSONObject jSONObject) {
        a(jSONObject);
    }

    private void a(JSONObject jSONObject) {
        try {
            this.link = jSONObject.optString("link");
            this.image = jSONObject.optString("image");
            this.name = jSONObject.optString("name");
            this.description = jSONObject.optString("description");
            this.act = jSONObject.optString(SocialConstants.PARAM_ACT);
            this.orientation = jSONObject.optInt("orientation", 0);
            this.showGift = jSONObject.optString("show_gift");
            this.starStore = jSONObject.optInt("star_score");
            this.showPlayersNum = jSONObject.optString("show_players_num");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
