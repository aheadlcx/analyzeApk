package qsbk.app.widget;

import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.HttpCallBack;
import qsbk.app.utils.ToastAndDialog;

class am implements HttpCallBack {
    final /* synthetic */ BlackReportDialog a;

    am(BlackReportDialog blackReportDialog) {
        this.a = blackReportDialog;
    }

    public void onSuccess(String str, JSONObject jSONObject) {
        ToastAndDialog.makePositiveToast(BlackReportDialog.b(this.a), "移除粉丝成功", Integer.valueOf(0)).show();
    }

    public void onFailure(String str, String str2) {
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str2, Integer.valueOf(0)).show();
    }
}
