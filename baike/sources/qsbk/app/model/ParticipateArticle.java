package qsbk.app.model;

import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.exception.QiushibaikeException;

public class ParticipateArticle extends RssArticle {
    public Comment mOwnComment;

    public ParticipateArticle(JSONObject jSONObject) throws QiushibaikeException {
        super(jSONObject);
    }

    protected void a(JSONObject jSONObject) throws QiushibaikeException {
        super.a(jSONObject);
        JSONObject optJSONObject = jSONObject.optJSONObject("comment");
        if (optJSONObject != null) {
            this.mOwnComment = Comment.newInstance(optJSONObject);
        }
    }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject toJSONObject = super.toJSONObject();
        toJSONObject.put("comment", this.mOwnComment.toJson());
        return toJSONObject;
    }

    public boolean hasOwnComment() {
        return this.mOwnComment != null;
    }
}
