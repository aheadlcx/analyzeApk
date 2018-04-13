package qsbk.app.activity;

import android.text.TextUtils;
import java.util.ArrayList;
import qsbk.app.QsbkApp;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.utils.GroupMemberManager.CallBack;
import qsbk.app.utils.ToastAndDialog;

class tk implements CallBack {
    final /* synthetic */ MemberChooseActivity a;

    tk(MemberChooseActivity memberChooseActivity) {
        this.a = memberChooseActivity;
    }

    public void onSuccess(ArrayList<BaseUserInfo> arrayList, int i) {
        this.a.d.refreshDone();
        this.a.q = i;
        this.a.f.setAtAllCount(i);
        this.a.handlerMember(arrayList);
        this.a.i();
        this.a.f.notifyDataSetChanged();
        this.a.hideLoading();
    }

    public void onFailure(int i, String str) {
        this.a.hideLoading();
        this.a.d.refreshDone();
        if (!TextUtils.isEmpty(str)) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
        }
    }
}
