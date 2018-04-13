package qsbk.app.activity;

import android.content.Context;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.model.GroupNotice;
import qsbk.app.utils.GroupActionUtil.ProgressDialogCallBack;
import qsbk.app.utils.ToastAndDialog;

class nv extends ProgressDialogCallBack {
    final /* synthetic */ GroupNotice a;
    final /* synthetic */ nu b;

    nv(nu nuVar, Context context, String str, GroupNotice groupNotice) {
        this.b = nuVar;
        this.a = groupNotice;
        super(context, str);
    }

    public void onSuccess(JSONObject jSONObject) {
        super.onSuccess(jSONObject);
        this.a.act = 1;
        this.b.b.a.b.update(this.a);
        this.b.a.mButton.setEnabled(false);
        this.b.a.mButton.setText("已同意");
    }

    public void onFailure(int i, String str) {
        super.onFailure(i, str);
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
    }
}
