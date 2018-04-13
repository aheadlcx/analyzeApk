package qsbk.app.activity;

import android.app.ProgressDialog;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.model.CampaignInfo;
import qsbk.app.utils.ToastAndDialog;

class abg implements SimpleCallBack {
    final /* synthetic */ ProgressDialog a;
    final /* synthetic */ RunForOwnerActivity b;

    abg(RunForOwnerActivity runForOwnerActivity, ProgressDialog progressDialog) {
        this.b = runForOwnerActivity;
        this.a = progressDialog;
    }

    public void onSuccess(JSONObject jSONObject) {
        this.b.hideLoading();
        this.a.dismiss();
        this.b.a = CampaignInfo.parse(jSONObject);
        this.b.j();
    }

    public void onFailure(int i, String str) {
        this.b.hideLoading();
        this.a.dismiss();
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
    }
}
