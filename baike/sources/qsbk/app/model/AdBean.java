package qsbk.app.model;

import com.tencent.open.SocialConstants;
import org.json.JSONException;
import org.json.JSONObject;

public class AdBean extends QbBean {
    public int confirm;
    public String content;
    public int count = 0;
    public String id;
    public String imageUrl;
    public String optionText;
    public int pos = 5;
    public String size;
    public String title;
    public int type;
    public String url;
    public String usersNum;

    public AdBean(JSONObject jSONObject) {
        try {
            if (!jSONObject.isNull("id")) {
                this.id = jSONObject.getString("id");
            }
            if (!jSONObject.isNull("type")) {
                this.type = jSONObject.getInt("type");
            }
            if (!jSONObject.isNull("count")) {
                this.count = jSONObject.getInt("count");
            }
            if (!jSONObject.isNull("url")) {
                this.url = jSONObject.getString("url");
            }
            if (!jSONObject.isNull("confirm")) {
                this.confirm = jSONObject.getInt("confirm");
            }
            if (!jSONObject.isNull("title")) {
                this.title = jSONObject.getString("title");
            }
            if (!jSONObject.isNull("body")) {
                this.content = jSONObject.getString("body");
            }
            if (!jSONObject.isNull(SocialConstants.PARAM_IMG_URL)) {
                this.imageUrl = jSONObject.getString(SocialConstants.PARAM_IMG_URL);
            }
            if (!jSONObject.isNull("usersNum")) {
                this.usersNum = jSONObject.getString("usersNum");
            }
            if (!jSONObject.isNull("size")) {
                this.size = jSONObject.getString("size");
            }
            if (!jSONObject.isNull("optionText")) {
                this.optionText = jSONObject.getString("optionText");
            }
            if (!jSONObject.isNull("pos")) {
                this.pos = jSONObject.getInt("pos");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
