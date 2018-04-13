package cn.xiaochuankeji.tieba.background.data;

import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class CommentSound implements Serializable {
    private int dur;
    private String url;

    public CommentSound(JSONObject jSONObject) {
        this.url = jSONObject.optString("url");
        this.dur = jSONObject.optInt("dur");
    }

    public JSONObject getJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("url", this.url);
            jSONObject.put("dur", this.dur);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public String getUrl() {
        return this.url;
    }

    public int getDur() {
        return this.dur;
    }
}
