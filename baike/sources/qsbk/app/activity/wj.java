package qsbk.app.activity;

import android.app.AlertDialog.Builder;
import android.text.TextUtils;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.im.ConversationActivity;
import qsbk.app.im.RelationshipCacheMgr;
import qsbk.app.model.relationship.Relationship;
import qsbk.app.utils.ToastAndDialog;

class wj implements SimpleCallBack {
    final /* synthetic */ MyInfoActivity a;

    wj(MyInfoActivity myInfoActivity) {
        this.a = myInfoActivity;
    }

    public void onSuccess(JSONObject jSONObject) {
        this.a.ca.dismiss();
        if (Relationship.FRIEND == this.a.bD) {
            this.a.bD = Relationship.FAN;
            this.a.a(this.a.bD);
            ToastAndDialog.makePositiveToast(QsbkApp.mContext, "已取消粉...", Integer.valueOf(0)).show();
        } else if (Relationship.FOLLOW_REPLIED == this.a.bD) {
            this.a.bD = Relationship.NO_REL;
            this.a.a(this.a.bD);
            ToastAndDialog.makePositiveToast(QsbkApp.mContext, "已取消粉...", Integer.valueOf(0)).show();
            this.a.f();
        } else if (Relationship.FOLLOW_UNREPLIED == this.a.bD) {
            this.a.bD = Relationship.NO_REL;
            this.a.a(this.a.bD);
            ToastAndDialog.makePositiveToast(QsbkApp.mContext, "已取消粉...", Integer.valueOf(0)).show();
            this.a.f();
        } else if (Relationship.FAN == this.a.bD) {
            this.a.bD = Relationship.FRIEND;
            this.a.a(this.a.bD);
            ToastAndDialog.makePositiveToast(QsbkApp.mContext, "你们已经是好友了，点击小纸条聊天吧！", Integer.valueOf(0)).show();
        } else if (Relationship.NO_REL_CHATED == this.a.bD) {
            this.a.bD = Relationship.FOLLOW_REPLIED;
            this.a.a(this.a.bD);
            ToastAndDialog.makePositiveToast(QsbkApp.mContext, "加粉成功！", Integer.valueOf(0)).show();
        } else if (Relationship.NO_REL == this.a.bD) {
            this.a.bD = Relationship.FOLLOW_UNREPLIED;
            this.a.a(this.a.bD);
            ToastAndDialog.makePositiveToast(QsbkApp.mContext, "加粉成功！", Integer.valueOf(0)).show();
        } else if (Relationship.BLACK == this.a.bD) {
            this.a.bD = Relationship.NO_REL;
            this.a.a(this.a.bD);
            ToastAndDialog.makePositiveToast(QsbkApp.mContext, "成功移出黑名单...", Integer.valueOf(0)).show();
        }
        if (this.a.bF != null) {
            this.a.bF.relationship = this.a.bD;
        }
        Object optString = jSONObject.optString(ConversationActivity.RELATIONSHIP);
        if (TextUtils.isEmpty(optString)) {
            this.a.bD = Relationship.getRelationShipFromStr(optString);
        }
        this.a.a(this.a.bD, this.a.b);
        RelationshipCacheMgr.getInstance(QsbkApp.currentUser.userId).putRelationship(this.a.b, this.a.bD);
    }

    public void onFailure(int i, String str) {
        this.a.ca.dismiss();
        if (i != -110) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
        }
        if (i != -110) {
            return;
        }
        if ((Relationship.FAN == this.a.bD || Relationship.NO_REL_CHATED == this.a.bD || Relationship.NO_REL == this.a.bD) && !this.a.isFinishing()) {
            Builder title = new Builder(this.a).setTitle(R.string.nearby_pop_title);
            if (TextUtils.isEmpty(str)) {
                str = "请先完善个人资料!";
            }
            title.setMessage(str).setPositiveButton("完善资料", new wl(this)).setNegativeButton("再逛逛", new wk(this)).show();
        }
    }
}
