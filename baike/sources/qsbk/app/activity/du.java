package qsbk.app.activity;

import android.app.ProgressDialog;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.utils.GroupNotifier;
import qsbk.app.utils.ToastAndDialog;

class du implements SimpleCallBack {
    final /* synthetic */ ProgressDialog a;
    final /* synthetic */ String b;
    final /* synthetic */ dt c;

    du(dt dtVar, ProgressDialog progressDialog, String str) {
        this.c = dtVar;
        this.a = progressDialog;
        this.b = str;
    }

    public void onSuccess(JSONObject jSONObject) {
        this.a.dismiss();
        ToastAndDialog.makePositiveToast(QsbkApp.mContext, "修改群名成功").show();
        GroupNotifier.updateGroupInfo(String.valueOf(this.c.c.a.id), this.b, null);
        GroupNotifier.notify(this.c.c.a.id, 1);
        this.c.c.finish();
    }

    public void onFailure(int i, String str) {
        this.a.dismiss();
        this.c.b.setText(str);
        this.c.b.setVisibility(0);
    }
}
