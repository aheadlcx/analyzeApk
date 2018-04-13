package qsbk.app.activity;

import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.utils.ToastAndDialog;

class acv implements SimpleCallBack {
    final /* synthetic */ SocialBindActivity a;

    acv(SocialBindActivity socialBindActivity) {
        this.a = socialBindActivity;
    }

    public void onSuccess(JSONObject jSONObject) {
        this.a.s();
        SocialBindActivity.fillUserDataFromServer(jSONObject);
        this.a.i();
    }

    public void onFailure(int i, String str) {
        this.a.s();
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
        this.a.finish();
    }
}
