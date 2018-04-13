package qsbk.app.activity;

import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.HttpCallBack;
import qsbk.app.utils.ToastAndDialog;

class zo implements HttpCallBack {
    final /* synthetic */ PayPasswordSetActivity a;

    zo(PayPasswordSetActivity payPasswordSetActivity) {
        this.a = payPasswordSetActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
    }

    public void onFailure(String str, String str2) {
        this.a.a.stopCountDown();
        this.a.a.setEnabled(true);
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str2, Integer.valueOf(0)).show();
    }
}
