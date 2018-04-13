package qsbk.app.activity;

import android.content.Context;
import android.view.View;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.model.GroupNotice;
import qsbk.app.utils.GroupActionUtil.ProgressDialogCallBack;
import qsbk.app.utils.ToastAndDialog;

class ny extends ProgressDialogCallBack {
    final /* synthetic */ View a;
    final /* synthetic */ nx b;

    ny(nx nxVar, Context context, String str, View view) {
        this.b = nxVar;
        this.a = view;
        super(context, str);
    }

    public void onSuccess(JSONObject jSONObject) {
        int i;
        int i2 = 1;
        super.onSuccess(jSONObject);
        GroupNotice a = this.b.a.c;
        if (this.a != this.b.a.n) {
            i = 1;
        } else {
            i = 2;
        }
        a.act = i;
        this.b.a.b.update(this.b.a.c);
        GroupNoticeDetailActivity groupNoticeDetailActivity = this.b.a;
        if (this.a == this.b.a.n) {
            i2 = 2;
        }
        groupNoticeDetailActivity.a(i2);
        this.b.a.setResult((this.b.a.q << 8) | this.b.a.c.act);
    }

    public void onFailure(int i, String str) {
        super.onFailure(i, str);
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
    }
}
