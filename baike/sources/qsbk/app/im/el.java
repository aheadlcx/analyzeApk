package qsbk.app.im;

import android.content.Context;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.utils.GroupActionUtil.ProgressDialogCallBack;
import qsbk.app.utils.ToastAndDialog;

class el extends ProgressDialogCallBack {
    final /* synthetic */ ej a;

    el(ej ejVar, Context context, String str) {
        this.a = ejVar;
        super(context, str);
    }

    public void onSuccess(JSONObject jSONObject) {
        super.onSuccess(jSONObject);
        this.a.c.silenceTime = 0;
        this.a.d.aN.updateMember(this.a.c);
    }

    public void onFailure(int i, String str) {
        super.onFailure(i, str);
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
    }
}
