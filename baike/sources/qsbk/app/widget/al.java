package qsbk.app.widget;

import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.HttpCallBack;
import qsbk.app.utils.ToastAndDialog;

class al implements HttpCallBack {
    final /* synthetic */ BlackReportDialog a;

    al(BlackReportDialog blackReportDialog) {
        this.a = blackReportDialog;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
    }

    public void onFailure(String str, String str2) {
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str2, Integer.valueOf(0)).show();
    }
}
