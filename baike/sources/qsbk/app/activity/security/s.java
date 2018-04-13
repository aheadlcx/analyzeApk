package qsbk.app.activity.security;

import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.utils.ToastAndDialog;

class s implements SimpleCallBack {
    final /* synthetic */ UnBindActivity a;

    s(UnBindActivity unBindActivity) {
        this.a = unBindActivity;
    }

    public void onSuccess(JSONObject jSONObject) {
        this.a.setResult(-1);
        ToastAndDialog.makePositiveToast(QsbkApp.mContext, "解绑成功！", Integer.valueOf(0)).show();
        this.a.finish();
    }

    public void onFailure(int i, String str) {
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
    }
}
