package qsbk.app.activity;

import android.content.Context;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.utils.GroupActionUtil.ProgressDialogCallBack;
import qsbk.app.utils.ToastAndDialog;

class tu extends ProgressDialogCallBack {
    final /* synthetic */ String a;
    final /* synthetic */ MemberManagerActivity b;

    tu(MemberManagerActivity memberManagerActivity, Context context, String str, boolean z, String str2) {
        this.b = memberManagerActivity;
        this.a = str2;
        super(context, str, z);
    }

    public void onSuccess(JSONObject jSONObject) {
        super.onSuccess(jSONObject);
        ToastAndDialog.makePositiveToast(QsbkApp.mContext, "成功将" + this.a + "踢出本群", Integer.valueOf(0)).show();
        this.b.h.notifyDataSetChanged();
        this.b.f.refresh();
        this.b.supportInvalidateOptionsMenu();
    }

    public void onFailure(int i, String str) {
        super.onFailure(i, str);
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
    }
}
