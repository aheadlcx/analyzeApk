package qsbk.app.widget;

import android.content.Context;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.utils.GroupActionUtil.ProgressDialogCallBack;
import qsbk.app.utils.ToastAndDialog;

final class o extends ProgressDialogCallBack {
    o(Context context, String str) {
        super(context, str);
    }

    public void onSuccess(JSONObject jSONObject) {
        super.onSuccess(jSONObject);
        ToastAndDialog.makePositiveToast(QsbkApp.mContext, "已屏蔽TA", Integer.valueOf(1)).show();
    }

    public void onFailure(int i, String str) {
        super.onFailure(i, str);
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(1)).show();
    }
}
