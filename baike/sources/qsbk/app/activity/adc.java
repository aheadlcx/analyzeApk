package qsbk.app.activity;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.thirdparty.ThirdPartyConstants;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.TipsManager;
import qsbk.app.utils.ToastAndDialog;

class adc implements SimpleCallBack {
    final /* synthetic */ SocialBindActivity a;

    adc(SocialBindActivity socialBindActivity) {
        this.a = socialBindActivity;
    }

    public void onSuccess(JSONObject jSONObject) {
        this.a.s();
        try {
            if (this.a.j.equals(ThirdPartyConstants.THIRDPARTY_TYLE_SINA)) {
                this.a.a.setMainText(jSONObject.getString("sns_type"));
                this.a.a.setStatus(3);
                QsbkApp.currentUser.wb = jSONObject.getString("sns_type");
                ToastAndDialog.makePositiveToast(QsbkApp.mContext, "绑定微博成功", Integer.valueOf(0)).show();
            } else if (this.a.j.equals(ThirdPartyConstants.THIRDPARTY_TYLE_QQ)) {
                this.a.b.setMainText(jSONObject.getString("sns_type"));
                this.a.b.setStatus(3);
                QsbkApp.currentUser.qq = jSONObject.getString("sns_type");
                ToastAndDialog.makePositiveToast(QsbkApp.mContext, "绑定QQ成功", Integer.valueOf(0)).show();
            } else if (this.a.j.equalsIgnoreCase(ThirdPartyConstants.THIRDPARTY_TYLE_WX)) {
                this.a.c.setMainText(jSONObject.getString("sns_type"));
                this.a.c.setStatus(3);
                QsbkApp.currentUser.wx = jSONObject.getString("sns_type");
                ToastAndDialog.makePositiveToast(QsbkApp.mContext, "绑定微信成功", Integer.valueOf(0)).show();
            }
            this.a.i();
            Intent intent = new Intent(TipsManager.SHOW_SECURITY_BIND);
            intent.putExtra(TipsManager.SHOW_SECURITY_BIND, false);
            LocalBroadcastManager.getInstance(this.a).sendBroadcast(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SharePreferenceUtils.setSharePreferencesValue("loginUser", QsbkApp.currentUser.toString());
    }

    public void onFailure(int i, String str) {
        this.a.s();
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
    }
}
