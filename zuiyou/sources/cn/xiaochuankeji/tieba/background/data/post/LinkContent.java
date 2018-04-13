package cn.xiaochuankeji.tieba.background.data.post;

import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class LinkContent implements Serializable {
    public String url;

    public LinkContent(JSONObject jSONObject) {
        this.url = jSONObject.optString("url");
    }

    public JSONObject serializeTo() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("url", this.url);
        return jSONObject;
    }
}
