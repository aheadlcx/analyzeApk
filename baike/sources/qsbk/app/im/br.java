package qsbk.app.im;

import java.util.Locale;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.HttpCallBack;
import qsbk.app.model.relationship.Relationship;

class br implements HttpCallBack {
    final /* synthetic */ ConversationActivity a;

    br(ConversationActivity conversationActivity) {
        this.a = conversationActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        this.a.mRelationship = Relationship.valueOf(jSONObject.optString(ConversationActivity.RELATIONSHIP).toUpperCase(Locale.US));
        RelationshipCacheMgr.getInstance(QsbkApp.currentUser.userId).putRelationship(this.a.getToId(), this.a.mRelationship);
        this.a.J();
    }

    public void onFailure(String str, String str2) {
    }
}
