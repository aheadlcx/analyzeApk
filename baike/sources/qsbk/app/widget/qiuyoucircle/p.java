package qsbk.app.widget.qiuyoucircle;

import android.app.AlertDialog.Builder;
import android.text.TextUtils;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.im.ConversationActivity;
import qsbk.app.im.RelationshipCacheMgr;
import qsbk.app.model.CircleArticle;
import qsbk.app.model.relationship.Relationship;
import qsbk.app.utils.ToastAndDialog;

class p implements SimpleCallBack {
    final /* synthetic */ String a;
    final /* synthetic */ BaseUserCell b;

    p(BaseUserCell baseUserCell, String str) {
        this.b = baseUserCell;
        this.a = str;
    }

    public void onSuccess(JSONObject jSONObject) {
        if (this.b.a != null) {
            this.b.a.dismiss();
        }
        CircleArticle circleArticle = (CircleArticle) this.b.getItem();
        Object optString = jSONObject.optString(ConversationActivity.RELATIONSHIP);
        if (TextUtils.equals(optString, Relationship.FRIEND.mRelationship) || TextUtils.equals(optString, Relationship.FOLLOW_REPLIED.mRelationship) || TextUtils.equals(optString, Relationship.FOLLOW_UNREPLIED.mRelationship)) {
            ToastAndDialog.makePositiveToast(QsbkApp.mContext, "加粉成功！", Integer.valueOf(0)).show();
        } else {
            ToastAndDialog.makePositiveToast(QsbkApp.mContext, "请求错误，请稍后再试！", Integer.valueOf(0)).show();
        }
        Relationship relationShipFromStr = Relationship.getRelationShipFromStr(optString);
        if (relationShipFromStr != null) {
            circleArticle.user.relationship = relationShipFromStr;
        }
        this.b.a(relationShipFromStr, this.a);
        RelationshipCacheMgr.getInstance(QsbkApp.currentUser.userId).putRelationship(this.a, relationShipFromStr);
    }

    public void onFailure(int i, String str) {
        Relationship relationship;
        if (this.b.a != null) {
            this.b.a.dismiss();
        }
        if (i != -110) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str).show();
        }
        CircleArticle circleArticle = (CircleArticle) this.b.getItem();
        if (circleArticle != null) {
            relationship = circleArticle.user.relationship;
        } else {
            relationship = null;
        }
        if (i != -110) {
            return;
        }
        if (Relationship.FAN == relationship || Relationship.NO_REL_CHATED == relationship || Relationship.NO_REL == relationship) {
            Builder title = new Builder(this.b.getContext()).setTitle(R.string.nearby_pop_title);
            if (TextUtils.isEmpty(str)) {
                str = "请先完善个人资料!";
            }
            title.setMessage(str).setPositiveButton("完善资料", new r(this)).setNegativeButton("再逛逛", new q(this)).show();
        }
    }
}
