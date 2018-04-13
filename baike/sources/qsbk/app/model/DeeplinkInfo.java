package qsbk.app.model;

import com.tencent.open.SocialConstants;
import org.json.JSONObject;

public class DeeplinkInfo {
    public String btnDesc;
    public String content;
    public String deepLink;

    public void parseJson(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("btn");
        this.content = jSONObject.optString("content");
        this.btnDesc = optJSONObject.optString(SocialConstants.PARAM_APP_DESC);
        this.deepLink = optJSONObject.optString("deep_link");
    }
}
