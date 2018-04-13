package qsbk.app.adapter;

import android.app.Activity;
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

class bh implements SimpleCallBack {
    final /* synthetic */ String a;
    final /* synthetic */ ContactQiuYouAdapter b;

    bh(ContactQiuYouAdapter contactQiuYouAdapter, String str) {
        this.b = contactQiuYouAdapter;
        this.a = str;
    }

    public void onSuccess(JSONObject jSONObject) {
        this.b.i.dismiss();
        if (this.b.e == 1) {
            ToastAndDialog.makePositiveToast(QsbkApp.mContext, "已取消粉...", Integer.valueOf(0)).show();
        } else if (this.b.e == 2) {
            ToastAndDialog.makePositiveToast(QsbkApp.mContext, "已取消粉...", Integer.valueOf(0)).show();
            this.b.a(this.a);
        } else if (this.b.e == 3) {
            ToastAndDialog.makePositiveToast(QsbkApp.mContext, "你们已经是好友了！", Integer.valueOf(0)).show();
        }
        Relationship relationship = null;
        Object optString = jSONObject.optString(ConversationActivity.RELATIONSHIP);
        if (!TextUtils.isEmpty(optString)) {
            relationship = Relationship.getRelationShipFromStr(optString);
        }
        this.b.a(relationship, this.a);
        RelationshipCacheMgr.getInstance(QsbkApp.currentUser.userId).putRelationship(this.a, relationship);
    }

    public void onFailure(int i, String str) {
        this.b.i.dismiss();
        if (i != -110) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
        } else if (i != -110 || this.b.e != 3) {
        } else {
            if (!(this.b.b instanceof Activity) || !((Activity) this.b.b).isFinishing()) {
                Builder title = new Builder(this.b.b).setTitle(R.string.nearby_pop_title);
                if (TextUtils.isEmpty(str)) {
                    str = "请先完善个人资料!";
                }
                title.setMessage(str).setPositiveButton("完善资料", new bj(this)).setNegativeButton("再逛逛", new bi(this)).show();
            }
        }
    }
}
