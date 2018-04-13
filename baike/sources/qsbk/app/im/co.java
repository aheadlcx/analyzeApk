package qsbk.app.im;

import android.content.Intent;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.HttpCallBack;
import qsbk.app.model.relationship.Relationship;
import qsbk.app.utils.ToastAndDialog;

class co implements HttpCallBack {
    final /* synthetic */ ConversationActivity a;

    co(ConversationActivity conversationActivity) {
        this.a = conversationActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        if (this.a.mRelationship == Relationship.NO_REL_CHATED) {
            this.a.mRelationship = Relationship.FOLLOW_REPLIED;
        } else if (this.a.mRelationship == Relationship.FAN) {
            this.a.mRelationship = Relationship.FRIEND;
            this.a.isTemporary = false;
            ToastAndDialog.makePositiveToast(QsbkApp.mContext, "粉成功，可以互发图片咯.", Integer.valueOf(0)).show();
            if (this.a.aq != null) {
                Intent intent = new Intent("QIU_YOU_RELATION_CHANGED");
                intent.putExtra("userId", this.a.getToId());
                intent.putExtra(ConversationActivity.RELATIONSHIP, this.a.mRelationship);
                this.a.aq.sendBroadcast(intent);
            }
            this.a.Q();
        }
        RelationshipCacheMgr.getInstance(QsbkApp.currentUser.userId).putRelationship(this.a.getToId(), this.a.mRelationship);
        this.a.am.setVisibility(8);
        this.a.ap.setVisibility(8);
    }

    public void onFailure(String str, String str2) {
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str2, Integer.valueOf(0)).show();
    }
}
