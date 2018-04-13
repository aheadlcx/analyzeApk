package qsbk.app.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.utils.ToastAndDialog;

class bi implements SimpleCallBack {
    final /* synthetic */ String a;
    final /* synthetic */ ProgressDialog b;
    final /* synthetic */ ApplyForOwnerActivity c;

    bi(ApplyForOwnerActivity applyForOwnerActivity, String str, ProgressDialog progressDialog) {
        this.c = applyForOwnerActivity;
        this.a = str;
        this.b = progressDialog;
    }

    public void onSuccess(JSONObject jSONObject) {
        ToastAndDialog.makePositiveToast(QsbkApp.mContext, "提交成功!").show();
        Intent intent = new Intent();
        intent.putExtra("text", this.a);
        this.c.setResult(1, intent);
        this.b.dismiss();
        this.c.finish();
    }

    public void onFailure(int i, String str) {
        this.b.dismiss();
        ToastAndDialog.makePositiveToast(QsbkApp.mContext, str).show();
    }
}
