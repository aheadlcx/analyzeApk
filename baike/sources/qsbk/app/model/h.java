package qsbk.app.model;

import org.json.JSONObject;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.share.QYQShareInfo;

class h implements SimpleCallBack {
    final /* synthetic */ SimpleCallBack a;
    final /* synthetic */ EditorMsg b;

    h(EditorMsg editorMsg, SimpleCallBack simpleCallBack) {
        this.b = editorMsg;
        this.a = simpleCallBack;
    }

    public void onSuccess(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject(QYQShareInfo.TYPE_VOTE);
        if (!(optJSONObject == null || this.b == null || this.b.vote == null)) {
            this.b.vote.up = optJSONObject.optString("up");
            this.b.vote.down = optJSONObject.optString("down");
            this.b.vote.my = optJSONObject.optString("my");
        }
        this.a.onSuccess(jSONObject);
    }

    public void onFailure(int i, String str) {
        this.a.onFailure(i, str);
    }
}
