package qsbk.app.activity;

import android.content.Context;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.utils.GroupActionUtil.ProgressDialogCallBack;
import qsbk.app.utils.GroupMemberManager;
import qsbk.app.utils.ToastAndDialog;

class tm extends ProgressDialogCallBack {
    final /* synthetic */ BaseUserInfo a;
    final /* synthetic */ MemberManagerActivity b;

    tm(MemberManagerActivity memberManagerActivity, Context context, String str, BaseUserInfo baseUserInfo) {
        this.b = memberManagerActivity;
        this.a = baseUserInfo;
        super(context, str);
    }

    public void onSuccess(JSONObject jSONObject) {
        super.onSuccess(jSONObject);
        ToastAndDialog.makePositiveToast(QsbkApp.mContext, "解除禁言成功！", Integer.valueOf(0)).show();
        this.a.silenceTime = 0;
        new GroupMemberManager(this.b.e).updateMember(this.a);
    }

    public void onFailure(int i, String str) {
        super.onFailure(i, str);
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
    }
}
