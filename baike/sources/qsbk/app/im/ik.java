package qsbk.app.im;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import com.baidu.mobstat.StatService;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.http.HttpCallBack;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.image.issue.Logger;

class ik implements HttpCallBack {
    final /* synthetic */ OfficialInfoActivity a;

    ik(OfficialInfoActivity officialInfoActivity) {
        this.a = officialInfoActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        if (Constants.OFFICIAL_INFO.equalsIgnoreCase(str)) {
            this.a.j = jSONObject.optString("pname", this.a.j);
            this.a.c.setText(this.a.j);
            this.a.d.setText(this.a.j);
            this.a.e.setText(jSONObject.optString("brief"));
            FrescoImageloader.displayAvatar(this.a.f, QsbkApp.absoluteUrlOfLargeUserIcon(jSONObject.optString("avatar"), this.a.i));
            if (jSONObject.optInt("subscribe", 0) == 1) {
                this.a.l = true;
            } else {
                this.a.l = false;
            }
            if (jSONObject.optInt("cancel", 0) == 1) {
                this.a.m = true;
            } else {
                this.a.m = false;
            }
        } else if ((Constants.OFFICIAL_SUBSCRIBE + "CANCEL").equalsIgnoreCase(str)) {
            ToastAndDialog.makePositiveToast(QsbkApp.mContext, "已取消收听，可去 设置>糗百官号 再次收听", Integer.valueOf(0)).show();
            StatService.onEvent(this.a, "OFFICIAL_UNFOLLOW", "unit_" + this.a.i);
            Logger.getInstance().debug(OfficialInfoActivity.a, "cancelSubscribe", "unit_" + this.a.i);
            r0 = new Intent();
            r0.setAction(IMMessageListFragment.ACTION_DELETE_CONTACT_ITEM);
            r0.putExtra(IMMessageListFragment.ACTION_DELETE_CONTACT_ITEM, this.a.i);
            LocalBroadcastManager.getInstance(this.a).sendBroadcast(r0);
            this.a.setResult(-1);
            r0 = new Intent();
            r0.setAction(OfficialSubscribeListActivity.INIT_ADAPTER);
            LocalBroadcastManager.getInstance(this.a).sendBroadcast(r0);
            this.a.l = false;
        } else if (Constants.OFFICIAL_SUBSCRIBE.equalsIgnoreCase(str)) {
            ToastAndDialog.makePositiveToast(QsbkApp.mContext, "欢迎收听" + this.a.j + "官号，官号消息将出现在您的小纸条里", Integer.valueOf(0)).show();
            this.a.l = true;
            this.a.m = true;
            r0 = new Intent();
            r0.setAction(OfficialSubscribeListActivity.INIT_ADAPTER);
            LocalBroadcastManager.getInstance(this.a).sendBroadcast(r0);
        }
        this.a.c();
        this.a.b.setVisibility(8);
        this.a.g.setVisibility(0);
    }

    public void onFailure(String str, String str2) {
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str2, Integer.valueOf(0)).show();
        this.a.b.setVisibility(8);
    }
}
