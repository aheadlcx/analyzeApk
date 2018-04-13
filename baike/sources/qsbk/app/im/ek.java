package qsbk.app.im;

import android.content.Context;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.utils.GroupActionUtil.ProgressDialogCallBack;
import qsbk.app.utils.GroupMemberManager;
import qsbk.app.utils.ToastAndDialog;

class ek extends ProgressDialogCallBack {
    final /* synthetic */ ej a;

    ek(ej ejVar, Context context, String str, boolean z) {
        this.a = ejVar;
        super(context, str, z);
    }

    public void onSuccess(JSONObject jSONObject) {
        super.onSuccess(jSONObject);
        this.a.d.aN.loadMemberFromServer(GroupMemberManager.EMPTY_CALLBACK);
    }

    public void onFailure(int i, String str) {
        super.onFailure(i, str);
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
    }
}
