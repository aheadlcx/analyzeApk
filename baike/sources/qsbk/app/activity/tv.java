package qsbk.app.activity;

import android.content.Context;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.utils.GroupActionUtil.ProgressDialogCallBack;
import qsbk.app.utils.ToastAndDialog;

class tv extends ProgressDialogCallBack {
    final /* synthetic */ MemberManagerActivity a;

    tv(MemberManagerActivity memberManagerActivity, Context context, String str, boolean z) {
        this.a = memberManagerActivity;
        super(context, str, z);
    }

    public void onSuccess(JSONObject jSONObject) {
        super.onSuccess(jSONObject);
        ToastAndDialog.makePositiveToast(QsbkApp.mContext, "禁言成功", Integer.valueOf(0));
        this.a.h.notifyDataSetChanged();
        this.a.f.refresh();
        this.a.supportInvalidateOptionsMenu();
    }

    public void onFailure(int i, String str) {
        super.onFailure(i, str);
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
    }
}
