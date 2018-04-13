package qsbk.app.activity;

import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.http.HttpCallBack;
import qsbk.app.utils.ToastAndDialog;

class aco implements HttpCallBack {
    final /* synthetic */ acn a;

    aco(acn acn) {
        this.a = acn;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        this.a.a.b();
        ToastAndDialog.makePositiveToast(QsbkApp.mContext, this.a.a.getResources().getString(R.string.upload_big_cover_success), Integer.valueOf(0)).show();
        this.a.a.a(this.a.a.c);
    }

    public void onFailure(String str, String str2) {
        this.a.a.b();
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str2, Integer.valueOf(0)).show();
        this.a.a.a(this.a.a.getApplication().getString(R.string.upload_big_cover_fail));
    }
}
