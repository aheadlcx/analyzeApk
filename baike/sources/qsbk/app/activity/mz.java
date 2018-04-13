package qsbk.app.activity;

import android.content.DialogInterface;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.utils.ToastAndDialog;

class mz implements SimpleCallBack {
    final /* synthetic */ DialogInterface a;
    final /* synthetic */ my b;

    mz(my myVar, DialogInterface dialogInterface) {
        this.b = myVar;
        this.a = dialogInterface;
    }

    public void onSuccess(JSONObject jSONObject) {
        this.a.dismiss();
        ToastAndDialog.makePositiveToast(QsbkApp.mContext, "修改简介成功！", Integer.valueOf(0)).show();
        GroupInfoActivity.launch(this.b.b.a, this.b.b.a.d, this.b.b.a.g, false);
        this.b.b.a.finish();
    }

    public void onFailure(int i, String str) {
        this.a.dismiss();
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "修改简介失败,请重试！", Integer.valueOf(0)).show();
        GroupInfoActivity.launch(this.b.b.a, this.b.b.a.d, this.b.b.a.g, false);
        this.b.b.a.finish();
    }
}
