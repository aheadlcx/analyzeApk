package qsbk.app.activity;

import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.utils.ToastAndDialog;

class agn implements SimpleCallBack {
    final /* synthetic */ WeiboLoginActivity a;

    agn(WeiboLoginActivity weiboLoginActivity) {
        this.a = weiboLoginActivity;
    }

    public void onSuccess(JSONObject jSONObject) {
        WeiboLoginActivity.b(jSONObject);
        WeiboLoginActivity.b(this.a.b, this.a.c, QsbkApp.currentUser.userId + "_sina_access_token");
        ToastAndDialog.makePositiveToast(QsbkApp.mContext, String.format("欢迎回来，%s", new Object[]{QsbkApp.currentUser.userName}), Integer.valueOf(0)).show();
        this.a.c();
    }

    public void onFailure(int i, String str) {
        if (i == 114) {
            this.a.a();
        } else {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
        }
    }
}
