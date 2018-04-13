package qsbk.app.activity;

import android.content.DialogInterface;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.utils.ToastAndDialog;

class aei implements SimpleCallBack {
    final /* synthetic */ DialogInterface a;
    final /* synthetic */ aeh b;

    aei(aeh aeh, DialogInterface dialogInterface) {
        this.b = aeh;
        this.a = dialogInterface;
    }

    public void onSuccess(JSONObject jSONObject) {
        this.b.b.a.setResult(1, this.b.b.a.f);
        ToastAndDialog.makePositiveToast(QsbkApp.mContext, "申诉成功！", Integer.valueOf(0)).show();
        a();
    }

    public void onFailure(int i, String str) {
        this.b.b.a.setResult(0, this.b.b.a.f);
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "申诉失败,请重试!", Integer.valueOf(0)).show();
        a();
    }

    void a() {
        this.a.dismiss();
        this.b.b.a.finish();
    }
}
