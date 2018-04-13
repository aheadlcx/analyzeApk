package qsbk.app.activity;

import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.fragments.QiuyouCircleFragment;
import qsbk.app.http.HttpCallBack;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.ToastAndDialog;

class ei implements HttpCallBack {
    final /* synthetic */ CheckInActivity a;

    ei(CheckInActivity checkInActivity) {
        this.a = checkInActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        if (this.a.J != null && this.a.J.isShowing()) {
            this.a.J.dismiss();
        }
        int optInt = jSONObject.optInt("count");
        int optInt2 = jSONObject.optInt("days");
        this.a.S = this.a.S - optInt;
        this.a.q();
        if (optInt == this.a.h.size()) {
            this.a.g.addAll(this.a.h);
            this.a.h.clear();
            this.a.G.clearSelection();
            this.a.G.onUpdate();
            this.a.B.setVisibility(8);
        }
        SharePreferenceUtils.setSharePreferencesValue(QiuyouCircleFragment.SIGN_DAYS + QsbkApp.currentUser.userId, optInt2);
        ToastAndDialog.makeText(this.a, String.format("补签成功，已连续签到%1$d天，补签卡还剩%2$d张", new Object[]{Integer.valueOf(optInt2), Integer.valueOf(this.a.S)})).show();
    }

    public void onFailure(String str, String str2) {
        if (this.a.J != null && this.a.J.isShowing()) {
            this.a.J.dismiss();
        }
        ToastAndDialog.makeText(this.a, str2).show();
    }
}
