package qsbk.app.share;

import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.utils.ToastAndDialog;

final class r implements SimpleCallBack {
    r() {
    }

    public void onSuccess(JSONObject jSONObject) {
        int optInt = jSONObject.optInt("score");
        ToastAndDialog.makePositiveToast(QsbkApp.mContext, "评论成功" + (optInt > 0 ? "，积分+" + optInt : ""), Integer.valueOf(0)).show();
    }

    public void onFailure(int i, String str) {
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str).show();
    }
}
