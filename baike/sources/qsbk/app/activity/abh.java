package qsbk.app.activity;

import android.app.ProgressDialog;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.utils.ToastAndDialog;

class abh implements SimpleCallBack {
    final /* synthetic */ ProgressDialog a;
    final /* synthetic */ RunForOwnerActivity b;

    abh(RunForOwnerActivity runForOwnerActivity, ProgressDialog progressDialog) {
        this.b = runForOwnerActivity;
        this.a = progressDialog;
    }

    public void onSuccess(JSONObject jSONObject) {
        ToastAndDialog.makePositiveToast(QsbkApp.mContext, "退选成功", Integer.valueOf(0)).show();
        this.b.a.hasCampaigned = false;
        this.b.j();
        this.a.dismiss();
    }

    public void onFailure(int i, String str) {
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
        this.a.dismiss();
    }
}
