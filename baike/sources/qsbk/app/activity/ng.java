package qsbk.app.activity;

import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.HttpCallBack;
import qsbk.app.utils.ToastAndDialog;

class ng implements HttpCallBack {
    final /* synthetic */ GroupMsgActivity a;

    ng(GroupMsgActivity groupMsgActivity) {
        this.a = groupMsgActivity;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        ToastAndDialog.makePositiveToast(QsbkApp.mContext, "修改成功！", Integer.valueOf(0)).show();
        this.a.c = null;
    }

    public void onFailure(String str, String str2) {
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str2, Integer.valueOf(0)).show();
        this.a.c = null;
    }
}
