package qsbk.app.activity;

import android.app.ProgressDialog;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.utils.ToastAndDialog;

class ou implements SimpleCallBack {
    final /* synthetic */ ProgressDialog a;
    final /* synthetic */ int b;
    final /* synthetic */ ImageViewer c;

    ou(ImageViewer imageViewer, ProgressDialog progressDialog, int i) {
        this.c = imageViewer;
        this.a = progressDialog;
        this.b = i;
    }

    public void onSuccess(JSONObject jSONObject) {
        this.a.dismiss();
        ToastAndDialog.makePositiveToast(QsbkApp.mContext, "删除成功！", Integer.valueOf(0)).show();
        GroupInfoActivity.sActionResult = this.b | 256;
        this.c.finish();
    }

    public void onFailure(int i, String str) {
        this.a.dismiss();
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
        this.c.finish();
    }
}
