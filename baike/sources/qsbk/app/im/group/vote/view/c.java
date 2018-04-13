package qsbk.app.im.group.vote.view;

import android.app.Activity;
import android.app.ProgressDialog;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.utils.ToastAndDialog;

class c implements SimpleCallBack {
    final /* synthetic */ ProgressDialog a;
    final /* synthetic */ GroupManagerVoteView b;

    c(GroupManagerVoteView groupManagerVoteView, ProgressDialog progressDialog) {
        this.b = groupManagerVoteView;
        this.a = progressDialog;
    }

    public void onSuccess(JSONObject jSONObject) {
        ToastAndDialog.makePositiveToast(QsbkApp.mContext, "投票成功！", Integer.valueOf(0)).show();
        this.b.setVoteButtonStyle(2);
        this.a.dismiss();
        if (this.b.a != null && (this.b.a instanceof Activity)) {
            ((Activity) this.b.a).finish();
        }
    }

    public void onFailure(int i, String str) {
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
        this.a.dismiss();
    }
}
