package qsbk.app.activity;

import android.content.Intent;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.http.HttpCallBack;
import qsbk.app.model.UserInfo;
import qsbk.app.model.relationship.Relationship;
import qsbk.app.utils.ToastAndDialog;

class zb implements HttpCallBack {
    final /* synthetic */ OtherInfoActivity a;

    zb(OtherInfoActivity otherInfoActivity) {
        this.a = otherInfoActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        if (Constants.URL_USER_INFO.equalsIgnoreCase(str)) {
            OtherInfoActivity.a(this.a, UserInfo.updateServerJsonNearby(null, jSONObject.optJSONObject("userdata")));
            OtherInfoActivity.j(this.a).setView(OtherInfoActivity.a(this.a), 1);
            OtherInfoActivity.a(this.a, OtherInfoActivity.a(this.a).relationship);
            OtherInfoActivity.b(this.a, OtherInfoActivity.b(this.a));
            OtherInfoActivity.k(this.a).setVisibility(0);
            if (Relationship.FRIEND == OtherInfoActivity.b(this.a) || Relationship.FOLLOW_REPLIED == OtherInfoActivity.b(this.a) || Relationship.FOLLOW_UNREPLIED == OtherInfoActivity.b(this.a)) {
                OtherInfoActivity.l(this.a).setVisibility(0);
            } else {
                OtherInfoActivity.l(this.a).setVisibility(8);
            }
        } else if (Constants.IS_UNSUBSCRIBE_TA.equalsIgnoreCase(str)) {
            OtherInfoActivity.a(this.a, jSONObject.optBoolean("unsub", false));
            OtherInfoActivity.b(this.a, true);
        } else if (Constants.SUBSCRIBE_TA.equalsIgnoreCase(str)) {
            OtherInfoActivity.a(this.a, false);
            ToastAndDialog.makePositiveToast(QsbkApp.mContext, "成功订阅TA的糗事", Integer.valueOf(0)).show();
        } else if (Constants.UNSUBSCRIBE_TA.equalsIgnoreCase(str)) {
            OtherInfoActivity.a(this.a, true);
            ToastAndDialog.makePositiveToast(QsbkApp.mContext, "已不再订阅TA的糗事，可在“TA的主页”或“设置>不订阅的人”订阅回来", Integer.valueOf(0)).show();
        } else if (Constants.REL_MOVEOFF_BLACKLIST.equalsIgnoreCase(str)) {
            OtherInfoActivity.a(this.a, Relationship.NO_REL);
            OtherInfoActivity.b(this.a, OtherInfoActivity.b(this.a));
            ToastAndDialog.makePositiveToast(QsbkApp.mContext, "成功移出黑名单...", Integer.valueOf(0)).show();
            OtherInfoActivity.c(this.a).sendBroadcast(new Intent("QIU_YOU_RELATION_CHANGED"));
        } else if ((Constants.REL_UNFOLLOW + Relationship.FRIEND.toString()).equalsIgnoreCase(str)) {
            OtherInfoActivity.a(this.a, Relationship.FAN);
            OtherInfoActivity.b(this.a, OtherInfoActivity.b(this.a));
            ToastAndDialog.makePositiveToast(QsbkApp.mContext, "已取消粉...", Integer.valueOf(0)).show();
            OtherInfoActivity.c(this.a).sendBroadcast(new Intent("QIU_YOU_RELATION_CHANGED"));
        } else if (Constants.REL_UNFOLLOW.equalsIgnoreCase(str)) {
            OtherInfoActivity.a(this.a, Relationship.NO_REL);
            OtherInfoActivity.b(this.a, OtherInfoActivity.b(this.a));
            ToastAndDialog.makePositiveToast(QsbkApp.mContext, "已取消粉...", Integer.valueOf(0)).show();
            OtherInfoActivity.m(this.a);
            OtherInfoActivity.c(this.a).sendBroadcast(new Intent("QIU_YOU_RELATION_CHANGED"));
        } else if (Constants.REL_FOLLOW.equalsIgnoreCase(str)) {
            if (OtherInfoActivity.b(this.a) == Relationship.FAN) {
                OtherInfoActivity.a(this.a, Relationship.FRIEND);
                OtherInfoActivity.c(this.a).sendBroadcast(new Intent("QIU_YOU_RELATION_CHANGED"));
            } else if (OtherInfoActivity.b(this.a) == Relationship.NO_REL_CHATED) {
                OtherInfoActivity.a(this.a, Relationship.FOLLOW_REPLIED);
            } else {
                OtherInfoActivity.a(this.a, Relationship.FOLLOW_UNREPLIED);
                OtherInfoActivity.c(this.a).sendBroadcast(new Intent("QIU_YOU_RELATION_CHANGED"));
            }
            OtherInfoActivity.b(this.a, OtherInfoActivity.b(this.a));
        }
        OtherInfoActivity.n(this.a).setVisibility(8);
    }

    public void onFailure(String str, String str2) {
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str2, Integer.valueOf(0)).show();
        if (Constants.URL_USER_INFO.equalsIgnoreCase(str)) {
            OtherInfoActivity.j(this.a).onInfoLoadingFailed(new zc(this));
        }
        OtherInfoActivity.n(this.a).setVisibility(8);
    }
}
