package qsbk.app.activity;

import android.app.ProgressDialog;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.utils.ToastAndDialog;

class mk implements SimpleCallBack {
    final /* synthetic */ ProgressDialog a;
    final /* synthetic */ GroupInfoActivity b;

    mk(GroupInfoActivity groupInfoActivity, ProgressDialog progressDialog) {
        this.b = groupInfoActivity;
        this.a = progressDialog;
    }

    public void onSuccess(JSONObject jSONObject) {
        ToastAndDialog.makePositiveToast(QsbkApp.mContext, "成功举报群!", Integer.valueOf(0)).show();
        this.a.dismiss();
    }

    public void onFailure(int i, String str) {
        this.a.dismiss();
    }
}
