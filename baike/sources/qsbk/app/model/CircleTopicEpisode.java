package qsbk.app.model;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CircleTopicEpisode extends QbBean {
    public String episode;
    public String id;
    public String picUrl;
    public String title;

    public static CircleTopicEpisode parseJson(JSONObject jSONObject) {
        CircleTopicEpisode circleTopicEpisode = new CircleTopicEpisode();
        circleTopicEpisode.id = jSONObject.optString("id");
        circleTopicEpisode.episode = jSONObject.optString("episode");
        circleTopicEpisode.picUrl = jSONObject.optString("pic_url");
        circleTopicEpisode.title = jSONObject.optString("title");
        return circleTopicEpisode;
    }

    public static ArrayList<CircleTopicEpisode> paseJsonArray(JSONArray jSONArray) {
        ArrayList<CircleTopicEpisode> arrayList = new ArrayList();
        if (jSONArray != null && jSONArray.length() > 0) {
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    arrayList.add(parseJson(jSONArray.getJSONObject(i)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return arrayList;
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", this.id);
            jSONObject.put("episode", this.episode);
            jSONObject.put("pic_url", this.picUrl);
            jSONObject.put("title", this.title);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
