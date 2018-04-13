package qsbk.app.activity;

import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.utils.ToastAndDialog;

class aek implements SimpleCallBack {
    final /* synthetic */ VideoFullScreenActivity a;

    aek(VideoFullScreenActivity videoFullScreenActivity) {
        this.a = videoFullScreenActivity;
    }

    public void onSuccess(JSONObject jSONObject) {
        ToastAndDialog.makePositiveToast(QsbkApp.mContext, "删除糗事成功！", Integer.valueOf(0)).show();
    }

    public void onFailure(int i, String str) {
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
    }
}
