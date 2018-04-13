package qsbk.app.activity;

import android.app.ProgressDialog;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.model.GroupNotice;
import qsbk.app.utils.ToastAndDialog;

class nj implements SimpleCallBack {
    final /* synthetic */ GroupNotice a;
    final /* synthetic */ ProgressDialog b;
    final /* synthetic */ GroupNoticeActivity c;

    nj(GroupNoticeActivity groupNoticeActivity, GroupNotice groupNotice, ProgressDialog progressDialog) {
        this.c = groupNoticeActivity;
        this.a = groupNotice;
        this.b = progressDialog;
    }

    public void onSuccess(JSONObject jSONObject) {
        this.c.g.remove(this.a);
        this.c.d.notifyDataSetChanged();
        this.b.dismiss();
    }

    public void onFailure(int i, String str) {
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
        this.b.dismiss();
    }
}
