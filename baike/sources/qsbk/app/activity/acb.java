package qsbk.app.activity;

import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.activity.security.EmailBindActivity;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.utils.ToastAndDialog;

class acb implements SimpleCallBack {
    final /* synthetic */ SecurityBindActivity a;

    acb(SecurityBindActivity securityBindActivity) {
        this.a = securityBindActivity;
    }

    public void onSuccess(JSONObject jSONObject) {
        if (!this.a.isFinishing()) {
            this.a.i();
            this.a.b(EmailBindActivity.ACTION_BINDED);
        }
    }

    public void onFailure(int i, String str) {
        if (!this.a.isFinishing()) {
            this.a.i();
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
        }
    }
}
