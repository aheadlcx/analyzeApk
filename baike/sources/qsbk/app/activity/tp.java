package qsbk.app.activity;

import android.text.TextUtils;
import java.util.ArrayList;
import qsbk.app.QsbkApp;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.utils.GroupMemberManager.CallBack;
import qsbk.app.utils.ToastAndDialog;

class tp implements CallBack {
    final /* synthetic */ MemberManagerActivity a;

    tp(MemberManagerActivity memberManagerActivity) {
        this.a = memberManagerActivity;
    }

    public void onSuccess(ArrayList<BaseUserInfo> arrayList, int i) {
        this.a.hideLoading();
        this.a.h.setAtAllCount(i);
        this.a.f.refreshDone();
        this.a.handlerMembers(arrayList);
        this.a.k();
    }

    public void onFailure(int i, String str) {
        this.a.hideLoading();
        this.a.f.refreshDone();
        if (!TextUtils.isEmpty(str)) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
        }
    }
}
