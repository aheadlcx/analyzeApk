package qsbk.app.activity.security;

import android.content.Intent;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.utils.ToastAndDialog;

class o implements SimpleCallBack {
    final /* synthetic */ EmailBindActivity a;

    o(EmailBindActivity emailBindActivity) {
        this.a = emailBindActivity;
    }

    public void onSuccess(JSONObject jSONObject) {
        this.a.hideLoading();
        Intent intent = new Intent();
        intent.putExtra("email", this.a.c.getText().toString().trim());
        this.a.setResult(20, intent);
        this.a.a(this.a.c.getText().toString().trim());
    }

    public void onFailure(int i, String str) {
        this.a.hideLoading();
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
    }
}
