package qsbk.app.model;

import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.exception.QiushibaikeException;

public class PostedArticle extends RssArticle {
    public Ban ban;

    public static class Ban extends QbBean {
        public String reason;
        public String repeatId;

        public static Ban newInstance(JSONObject jSONObject) {
            Ban ban = new Ban();
            ban.repeatId = jSONObject.optString("aid");
            ban.reason = jSONObject.optString("reason");
            return ban;
        }

        public JSONObject toJson() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("aid", this.repeatId);
                jSONObject.put("reason", this.reason);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jSONObject;
        }
    }

    public PostedArticle(JSONObject jSONObject) throws QiushibaikeException {
        super(jSONObject);
    }

    public boolean isBan() {
        return "private".equals(this.state) && this.ban != null;
    }

    protected void a(JSONObject jSONObject) throws QiushibaikeException {
        super.a(jSONObject);
        JSONObject optJSONObject = jSONObject.optJSONObject("ban");
        if (optJSONObject != null) {
            this.ban = Ban.newInstance(optJSONObject);
        }
    }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject toJSONObject = super.toJSONObject();
        if (this.ban != null) {
            toJSONObject.put("ban", this.ban.toJson());
        }
        return toJSONObject;
    }
}
