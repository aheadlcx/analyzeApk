package qsbk.app.activity;

import android.content.Context;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.utils.GroupActionUtil.ProgressDialogCallBack;
import qsbk.app.utils.ToastAndDialog;

class tt extends ProgressDialogCallBack {
    final /* synthetic */ MemberManagerActivity a;

    tt(MemberManagerActivity memberManagerActivity, Context context, String str, boolean z) {
        this.a = memberManagerActivity;
        super(context, str, z);
    }

    public void onSuccess(JSONObject jSONObject) {
        super.onSuccess(jSONObject);
        this.a.h.notifyDataSetChanged();
        this.a.f.refresh();
    }

    public void onFailure(int i, String str) {
        super.onFailure(i, str);
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
    }
}
