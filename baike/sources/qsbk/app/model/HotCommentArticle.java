package qsbk.app.model;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.exception.QiushibaikeException;

public class HotCommentArticle extends RssArticle {
    public Comment mOwnHotComment;

    public HotCommentArticle(JSONObject jSONObject) throws QiushibaikeException {
        super(jSONObject);
    }

    protected void a(JSONObject jSONObject) throws QiushibaikeException {
        JSONObject optJSONObject = jSONObject.optJSONObject("article");
        if (optJSONObject != null) {
            super.a(optJSONObject);
        }
        this.mOwnHotComment = Comment.newInstance(jSONObject);
    }

    public boolean equals(Object obj) {
        if (obj instanceof HotCommentArticle) {
            HotCommentArticle hotCommentArticle = (HotCommentArticle) obj;
            if (TextUtils.equals(this.id, hotCommentArticle.id) && this.mOwnHotComment != null && hotCommentArticle.mOwnHotComment != null && TextUtils.equals(hotCommentArticle.mOwnHotComment.id, this.mOwnHotComment.id)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (this.mOwnHotComment == null ? 0 : this.mOwnHotComment.hashCode() * 31) + super.hashCode();
    }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject toJson = this.mOwnHotComment.toJson();
        toJson.put("article", super.toJSONObject());
        return toJson;
    }

    public boolean hasOwnHotComment() {
        return this.mOwnHotComment != null;
    }
}
