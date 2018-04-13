package qsbk.app.activity;

import android.app.ProgressDialog;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.HttpCallBack;
import qsbk.app.utils.ToastAndDialog;

class pz implements HttpCallBack {
    final /* synthetic */ ProgressDialog a;
    final /* synthetic */ InviteFriendActivity b;

    pz(InviteFriendActivity inviteFriendActivity, ProgressDialog progressDialog) {
        this.b = inviteFriendActivity;
        this.a = progressDialog;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        ToastAndDialog.makePositiveToast(QsbkApp.mContext, "已经成功邀请糗友，对方同意后将加入群！", Integer.valueOf(0)).show();
        this.a.dismiss();
        this.b.finish();
    }

    public void onFailure(String str, String str2) {
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str2, Integer.valueOf(0)).show();
        this.a.dismiss();
    }
}
